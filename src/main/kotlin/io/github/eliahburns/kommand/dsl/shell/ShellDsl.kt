package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.Kommand

annotation class ShellDsl

val DEFAULT_ENV = mapOf<String, String>()

val DEFAULT_COMMANDS = listOf<Kommand>()

typealias Kommands = List<Kommand>

fun mutableEnv() = mutableMapOf<String, String>()

data class KommandShell(
    val env: ShellEnvironment = DEFAULT_ENV,
    val commands: Kommands = DEFAULT_COMMANDS
)

@ShellDsl
class KommandShellBuilder {
    private var env = mutableEnv()

    fun env(block: ShellEnvironmentBuilder.() -> Unit): ShellEnvironment {
        env = ShellEnvironmentBuilder().apply(block).build().toMutableMap()
        return env
    }

    fun build() = KommandShell(env = env.toMap())
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


// Example usage
val s = shell {
    env {
        "thus" to "that"
        "other" to "either"
    }
}




