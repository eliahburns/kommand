package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.simple.KommandArgsBuilder
import io.github.eliahburns.kommand.simple.Kommands
import io.github.eliahburns.kommand.simple.kommand
import io.github.eliahburns.kommand.simple.kommands


inline fun ssh(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "ssh"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.ssh(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "ssh"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun scp(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "scp"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.scp(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "scp"
                args = KommandArgsBuilder().apply(block).build()
            }
)
