package io.github.eliahburns.kommand

import io.github.eliahburns.kommand.dsl.shell.grep
import io.github.eliahburns.kommand.dsl.shell.ls
import io.github.eliahburns.kommand.dsl.shell.ping
import io.github.eliahburns.kommand.dsl.shell.wc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import kotlin.properties.Delegates

private val logger = KotlinLogging.logger {  }

data class Kommand(
    val command: String,
    val args: List<KommandArg>
)

fun Kommand.toKommands() = Kommands(listOf(this), mapOf())

data class Kommands(
    val commands: List<Kommand>,
    val envVars: Map<String, String>
)

class KommandBuilder {
    var name by Delegates.notNull<String>()
    var args = listOf<KommandArg>()

    fun build() = Kommand(name, args)
}

fun Kommand.toCommandString(): String {
    return "$command ${args.joinToString(" ") { "-$it" }}"
}

inline fun kommand(block: KommandBuilder.() -> Unit) = KommandBuilder().apply(block).build()

inline fun kommands(block: KommandBuilder.() -> Unit) = KommandBuilder().apply(block).build().toKommands()

fun Kommand.toProcessBuilder(): ProcessBuilder = ProcessBuilder()
    .command(command, *args.toArgs().toTypedArray())
    .redirectErrorStream(true)

fun Kommands.startPipeline(): List<Process> {
    return ProcessBuilder.startPipeline(commands.map { it.toProcessBuilder() })
}

fun Kommands.out(): Flow<String> = channelFlow {

    val processes = this@out.startPipeline()
    processes.forEach { proc -> logger.debug { "started process with pid ${proc.pid()} on invocation of 'out()'" } }

    launch(Dispatchers.IO) {
        processes
            .last()
            .inputStream
            .bufferedReader()
            .useLines {  lines ->
                lines.forEach {  line ->
                    send(line)
                }
            }
    }

    invokeOnClose { t ->
        t?.let { logger.debug { "closing 'out()' after $t" } }
        processes.forEach { proc ->
            logger.debug { "stopping process with pid ${proc.pid()} on 'out()' close" }
            proc.destroy()
        }
    }
}

fun <T : Kommand> Flow<T>.env(block: MutableMap<String, String>.() -> Unit): Flow<Kommand> {
    TODO()
}

data class KommandArg(val arg: String, val useDash: Boolean = true)

typealias KommandArgs = List<KommandArg>

fun KommandArgs.toArgs() = map { if (it.useDash) "-${it.arg}" else it.arg }.also { logger.debug { it } }

open class KommandArgsBuilder {
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


// TODO: clean up and move into real tests
fun main() = runBlocking {

    ls { -"a" }
        .grep { -"e" of "gradle" }
        .wc { -"w" }
        .out()
        .collect { println(it) }

    ping(host = "google.com") { }
        .out()
        .take(3)
        .collect { println(it) }

}
