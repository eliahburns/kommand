package io.github.eliahburns.kommand.shell

import io.github.eliahburns.kommand.Kommand

fun currentWorkingDir(): String = System.getProperty("user.dir")

annotation class ShellDsl

val DEFAULT_ENV = mapOf<String, String>()

val DEFAULT_COMMANDS = listOf<Kommand>()

typealias Kommands = List<Kommand>

fun mutableEnv() = mutableMapOf<String, String>()

data class KommandShell(
    val env: ShellEnvironment = DEFAULT_ENV,
    val commands: Kommands = DEFAULT_COMMANDS,
    val currentWorkingDir: String = currentWorkingDir(),
    val previousWorkingDir: String = currentWorkingDir()
)

fun KommandShell.toBuilder(dir: String? = null) =
    KommandShellBuilder(env.toMutableMap(), commands, dir ?: currentWorkingDir, previousWorkingDir)

fun KommandShell.copy(workingDirectory: String? = null, block: KommandShellBuilder.() -> Unit) =
    toBuilder(dir = workingDirectory).apply(block).build()

@ShellDsl
class KommandShellBuilder(
    private val env: MutableMap<String, String> = mutableEnv(),
    private val commands: Kommands = DEFAULT_COMMANDS,
    private var currentWorkingDir: String = currentWorkingDir(),
    private var previousWorkingDir: String = currentWorkingDir()
) {
    @PublishedApi
    internal val additionalCommands = mutableListOf<Kommand>()

    fun env(block: ShellEnvironmentBuilder.() -> Unit): ShellEnvironment {
        val se = ShellEnvironmentBuilder().apply(block).build().toMutableMap()
        env.putAll(se)
        return env
    }

    fun cd(path: String) {
        when (path) {
            ".." -> currentWorkingDir = currentWorkingDir.substringBeforeLast("/").also { previousWorkingDir = currentWorkingDir }
            "-" -> currentWorkingDir = previousWorkingDir.also { previousWorkingDir = currentWorkingDir }
            else -> currentWorkingDir = path.also { previousWorkingDir = currentWorkingDir }
        }
    }

    operator fun Kommand.unaryPlus() {
        additionalCommands.add(this)
    }

    fun build() =
        KommandShell(env.toMap(),commands + additionalCommands, currentWorkingDir, previousWorkingDir)
}

typealias ShellEnvironment = Map<String, String>

@ShellDsl
class ShellEnvironmentBuilder {
    private val env = mutableEnv()

    infix fun String.to(value: String): Pair<String, String> {
        env[this] = value
        return Pair(this, value)
    }

    fun build(): ShellEnvironment = env.toMap()
}

fun shell(block: KommandShellBuilder.() -> Unit) = KommandShellBuilder().apply(block).build()

