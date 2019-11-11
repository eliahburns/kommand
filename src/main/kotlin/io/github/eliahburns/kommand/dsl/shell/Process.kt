package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.simple.KommandArgsBuilder
import io.github.eliahburns.kommand.simple.Kommands
import io.github.eliahburns.kommand.simple.kommand
import io.github.eliahburns.kommand.simple.kommands


inline fun ps(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "ps"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.ps(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "ps"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun kill(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "kill"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.kill(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "kill"
                args = KommandArgsBuilder().apply(block).build()
            }
)
