package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.simple.KommandArgsBuilder
import io.github.eliahburns.kommand.simple.Kommands
import io.github.eliahburns.kommand.simple.kommand
import io.github.eliahburns.kommand.simple.kommands


inline fun mkdir(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "mkdir"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.mkdir(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "mkdir"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun rm(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "rm"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.rm(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "rm"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun du(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "du"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.du(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "du"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun df(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "df"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.df(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "df"
                args = KommandArgsBuilder().apply(block).build()
            }
)


inline fun tree(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "tree"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.tree(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "tree"
                args = KommandArgsBuilder().apply(block).build()
            }
)
