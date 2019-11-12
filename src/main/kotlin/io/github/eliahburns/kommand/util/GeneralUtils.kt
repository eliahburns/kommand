package io.github.eliahburns.kommand.util

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.KommandShellBuilder
import io.github.eliahburns.kommand.util.args.KommandArgs
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

fun currentWorkingDir(): String = System.getProperty("user.dir")

fun Kommand.toCommandString(): String {
    return "$command ${args.joinToString(" ") { "-$it" }}"
}

fun KommandShell.systemEnv() = System.getenv().entries.map { it.key to it.value }

fun mutableEnv() = mutableMapOf<String, String>()

fun KommandShell.toBuilder(dir: String? = null) =
    KommandShellBuilder(env.toMutableMap(), commands, dir ?: currentWorkingDir, previousWorkingDir)

fun KommandShell.copy(workingDirectory: String? = null, block: KommandShellBuilder.() -> Unit) =
    toBuilder(dir = workingDirectory).apply(block).build()

fun KommandArgs.toArgs() = map { if (it.useDash) "-${it.arg}" else it.arg }.also { logger.debug { it } }

