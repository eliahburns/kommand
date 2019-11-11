package io.github.eliahburns.kommand.simple

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException
import kotlin.properties.Delegates


sealed class Kommand {

    abstract val command: String

    abstract val envVars: Map<String, String>

    abstract val args: List<KommandArg>

    data class Base(
        override val command: String,
        override val envVars: Map<String, String>,
        override val args: List<KommandArg>
    ) : Kommand() {

    }

    data class Piped(
        val commands: List<Kommand>,
        override val envVars: Map<String, String> = mutableMapOf(),
        override val command: String = "pipe",
        override val args: List<KommandArg> = mutableListOf()
    ) : Kommand()
}

class KommandBaseBuilder {
    var name by Delegates.notNull<String>()
    var envVars = mapOf<String, String>()
    var args = listOf<KommandArg>()

    fun build() = Kommand.Base(name, envVars, args)
}

class KommandPipedBuilder {
    var commands by Delegates.notNull<List<Kommand>>()
    var name by Delegates.notNull<String>()
    var envVars = mapOf<String, String>()
    var args = listOf<KommandArg>()

    fun build() = Kommand.Piped(commands, envVars, name, args)
}

fun Kommand.toCommandString(): String {
    return "$command ${args.joinToString(" ") { "-$it" }}"
}

inline fun kommand(block: KommandBaseBuilder.() -> Unit) = KommandBaseBuilder().apply(block).build()
inline fun piped(block: KommandPipedBuilder.() -> Unit) = KommandPipedBuilder().apply(block).build()


fun Kommand.pipe(): Kommand.Piped {
    return when (this) {
        is Kommand.Piped -> {
            return this
        }
        else -> {
            Kommand.Piped(
                commands = mutableListOf(this),
                envVars = this.envVars
            )
        }
    }
}


inline fun ls(block: KommandArgsBuilder.() -> Unit) = kommand {
    name = "ls"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommand.Piped.ls(crossinline block: KommandArgsBuilder.() -> Unit): Kommand = copy(
    commands = commands.plus(
        kommand {
            name = "ls"
            args = KommandArgsBuilder().apply(block).build()
        }
    )
)

inline fun Kommand.Piped.grep(crossinline block: KommandArgsBuilder.() -> Unit): Kommand = copy(
    commands = commands.plus(
        kommand {
            name = "grep"
            args = KommandArgsBuilder().apply(block).build()
        }
    )
)

inline fun Kommand.Piped.wc(crossinline block: KommandArgsBuilder.() -> Unit): Kommand = copy(
    commands = commands.plus(
        kommand {
            name = "wc"
            args = KommandArgsBuilder().apply(block).build()
        }
    )
)

fun Kommand.Base.toProcessBuilder(): ProcessBuilder = ProcessBuilder()
    .command(command, *args.toArgs().toTypedArray())
    .redirectErrorStream(true)

fun Kommand.Piped.startPipeline(): List<Process> {
    return ProcessBuilder.startPipeline(commands.map { (it as Kommand.Base).toProcessBuilder() })
}

inline fun <reified T : Kommand> T.out(): Flow<String> = channelFlow {
    val k = this@out
    when (k::class) {
        Kommand.Base::class -> launch(Dispatchers.IO) {
            (k as Kommand.Base)
                .toProcessBuilder()
                .start()
                .inputStream
                .bufferedReader()
                .useLines {  lines ->
                    lines.forEach {  line ->
                        send(line)
                    }
                }

        }
        Kommand.Piped::class -> launch(Dispatchers.IO) {
            (k as Kommand.Piped)
                .startPipeline()
                .last()
                .inputStream
                .bufferedReader()
                .useLines {  lines ->
                    lines.forEach {  line ->
                        send(line)
                    }
                }
        }
        else -> throw RuntimeException("unimplemented for ${this::class}")
    }
}

fun <T : Kommand> Flow<T>.env(block: MutableMap<String, String>.() -> Unit): Flow<Kommand> {
    TODO()
}

data class KommandArg(val arg: String, val useDash: Boolean = true)

typealias KommandArgs = List<KommandArg>

fun KommandArgs.toArgs() = map { if (it.useDash) "-${it.arg}" else it.arg }.also { println(it) }

class KommandArgsBuilder {
    private val args = mutableListOf<KommandArg>()

    infix fun KommandArg.of(param: String): Pair<KommandArg, KommandArg> {
        val p = this to KommandArg(param, false)
        args += p.second
        return p
    }

    operator fun String.unaryMinus(): KommandArg {
        val kArg = KommandArg(this)
        args += kArg
        return kArg
    }

    fun add(flag: String): KommandArg {
        val kArg = KommandArg(flag, false)
        args += kArg
        return kArg
    }

    fun build(): KommandArgs = args.toList()
}


fun main() = runBlocking {

    ls { -"a" }
        .pipe()
        .grep { -"e" of "gradle" }
        .pipe()
        .wc { -"w" }
        .out()
        .collect { println(it) }


    Unit
}
