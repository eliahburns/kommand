package io.github.eliahburns.kommand

import io.github.eliahburns.kommand.dsl.builtin.cd
import io.github.eliahburns.kommand.dsl.export
import io.github.eliahburns.kommand.dsl.grep
import io.github.eliahburns.kommand.dsl.ls
import io.github.eliahburns.kommand.dsl.wc
import io.github.eliahburns.kommand.process.out
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.ShellDsl
import io.github.eliahburns.kommand.shell.copy
import io.github.eliahburns.kommand.shell.shell
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import kotlin.properties.Delegates

private val logger = KotlinLogging.logger {  }

data class Kommand(
    val command: String,
    val args: List<KommandArg>
)

@ShellDsl
class KommandBuilder {
    var name by Delegates.notNull<String>()
    var args = listOf<KommandArg>()

    fun build() = Kommand(name, args)
}

fun Kommand.toCommandString(): String {
    return "$command ${args.joinToString(" ") { "-$it" }}"
}


data class KommandArg(val arg: String, val useDash: Boolean = true)

typealias KommandArgs = List<KommandArg>

fun KommandArgs.toArgs() = map { if (it.useDash) "-${it.arg}" else it.arg }.also { logger.debug { it } }

@ShellDsl
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

inline fun kommand(crossinline block: KommandBuilder.() -> Unit) = KommandBuilder().apply(block).build()

/** To add [Kommand]s to the [KommandShell] that don't have concrete extension methods.*/
inline fun KommandShell.kommand(command: String, crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = command
        args = KommandArgsBuilder().apply(block).build()
    }
}

// TODO: clean up and move into real tests
fun main() = runBlocking {

    shell { }
        .ls { -"a" }
        .grep { -"e" of "gradle" }
        .wc { -"w" }
        .out()
        .collect { println(it) }

    shell { }
        .cd(dir = "..") { }
        .ls { }
        .kommand("wc") { -"w" }
        .out()
        .collect { println(it) }

//    shell { }
//        .ping(host = "google.com") { }
//        .out()
//        .take(3)
//        .collect { println(it) }

    shell {
        env {
            "LD_LIBRARY_PATH" to "LD_LIBRARY_PATH:/some/other/dynamic/lib"
            "JAVA_HOME" to "path/to/java/we/want/to/use"
        }
    }
        .export { "KRB5CC" to "/dev/null" }
        .env
        .entries
        .forEach { println(it) }




}
