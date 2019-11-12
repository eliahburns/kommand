package io.github.eliahburns.kommand

import io.github.eliahburns.kommand.shell.*
import io.github.eliahburns.kommand.util.args.KommandArg
import io.github.eliahburns.kommand.util.args.KommandArgsBuilder
import io.github.eliahburns.kommand.util.copy
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

