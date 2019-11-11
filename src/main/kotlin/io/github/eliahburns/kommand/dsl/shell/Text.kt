package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.simple.KommandArgsBuilder
import io.github.eliahburns.kommand.simple.Kommands
import io.github.eliahburns.kommand.simple.kommand
import io.github.eliahburns.kommand.simple.kommands


inline fun uniq(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "uniq"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.uniq(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "uniq"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun sort(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "sort"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.sort(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "sort"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun diff(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "diff"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.diff(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "diff"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun comp(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "comp"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.comp(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "comp"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun cut(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "cut"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.cut(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "cut"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun sed(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "sed"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.sed(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "sed"
                args = KommandArgsBuilder().apply(block).build()
            }
)
