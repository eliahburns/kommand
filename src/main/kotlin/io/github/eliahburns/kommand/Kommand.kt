package io.github.eliahburns.kommand

import io.github.eliahburns.kommand.dsl.builtin.cd
import io.github.eliahburns.kommand.dsl.builtin.export
import io.github.eliahburns.kommand.dsl.grep
import io.github.eliahburns.kommand.dsl.ls
import io.github.eliahburns.kommand.dsl.wc
import io.github.eliahburns.kommand.shell.*
import io.github.eliahburns.kommand.util.args.KommandArg
import io.github.eliahburns.kommand.util.args.KommandArgsBuilder
import io.github.eliahburns.kommand.util.copy
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import kotlin.properties.Delegates

private val logger = KotlinLogging.logger {  }

/** Representation of some command, generally executed through a shell, or in our case a [KommandShell]. [command] is
 * the name of the command, which would be requested with the given arguments of [args] */
data class Kommand(
    val command: String,
    val args: List<KommandArg>
)

@KommandShellDsl
class KommandBuilder {
    var name by Delegates.notNull<String>()
    var args = listOf<KommandArg>()

    fun build() = Kommand(name, args)
}

inline fun kommand(crossinline block: KommandBuilder.() -> Unit) = KommandBuilder().apply(block).build()

/** Internal method to reduce verbosity and code duplication when implementing wrappers for shell commands */
@PublishedApi
internal inline fun internalKommand(command: String, crossinline block: KommandArgsBuilder.() -> Unit) = kommand {
    name = command
    args = KommandArgsBuilder().apply(block).build()
}

/** To add [Kommand]s to the [KommandShell] that don't have concrete extension methods.*/
inline fun KommandShell.kommand(command: String, crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand(command, block)
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
