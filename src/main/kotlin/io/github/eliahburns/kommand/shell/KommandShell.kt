package io.github.eliahburns.kommand.shell

import io.github.eliahburns.kommand.util.currentWorkingDir
import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.util.mutableEnv
import io.github.eliahburns.kommand.util.process.startPipeline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

annotation class KommandShellDsl

val DEFAULT_ENV = mapOf<String, String>()

val DEFAULT_COMMANDS = listOf<Kommand>()

typealias Kommands = List<Kommand>

typealias ShellEnvironment = Map<String, String>

data class KommandShell(
    val env: ShellEnvironment = DEFAULT_ENV,
    val commands: Kommands = DEFAULT_COMMANDS,
    val currentWorkingDir: String = currentWorkingDir(),
    val previousWorkingDir: String = currentWorkingDir()
)

@KommandShellDsl
class KommandShellBuilder(
    private val env: MutableMap<String, String> = mutableEnv(),
    private val commands: Kommands = DEFAULT_COMMANDS,
    private var currentWorkingDir: String = currentWorkingDir(),
    private var previousWorkingDir: String = currentWorkingDir()
) {
    @PublishedApi
    internal val additionalCommands = mutableListOf<Kommand>()

    /** Add environment variables to the shell */
    fun env(block: ShellEnvironmentBuilder.() -> Unit): ShellEnvironment {
        val se = ShellEnvironmentBuilder().apply(block).build().toMutableMap()
        env.putAll(se)
        return env
    }

    /** change the working directory of the shell */
    fun cd(path: String) {
        when (path) {
            ".." -> currentWorkingDir = currentWorkingDir.substringBeforeLast("/").also { previousWorkingDir = currentWorkingDir }
            "-" -> currentWorkingDir = previousWorkingDir.also { previousWorkingDir = currentWorkingDir }
            else -> currentWorkingDir = path.also { previousWorkingDir = currentWorkingDir }
        }
    }

    /** Add another [Kommand] to the shell */
    operator fun Kommand.unaryPlus() {
        additionalCommands.add(this)
    }

    fun build() =
        KommandShell(env.toMap(),commands + additionalCommands, currentWorkingDir, previousWorkingDir)
}

@KommandShellDsl
class ShellEnvironmentBuilder {
    private val env = mutableEnv()

    /** Add a pair of strings to a shell's environment, in which the first is the variable name and second is its value */
    infix fun String.to(value: String): Pair<String, String> {
        env[this] = value
        return Pair(this, value)
    }

    fun build(): ShellEnvironment = env.toMap()
}

fun shell(block: KommandShellBuilder.() -> Unit) = KommandShellBuilder().apply(block).build()

/** Fetch the output from the pipeline of [Kommand]s in the [KommandShell] and start their underlying processes */
fun KommandShell.out(): Flow<String> = channelFlow {

    val processes = this@out.commands.startPipeline(this@out.currentWorkingDir)
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
