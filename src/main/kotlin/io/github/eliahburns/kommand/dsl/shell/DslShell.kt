package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.simple.KommandArgsBuilder
import io.github.eliahburns.kommand.simple.Kommands
import io.github.eliahburns.kommand.simple.kommand
import io.github.eliahburns.kommand.simple.kommands


inline fun ls(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "ls"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.ls(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
        kommand {
            name = "ls"
            args = KommandArgsBuilder().apply(block).build()
        }
)

// TODO: include dir
inline fun cd(dir: String = "", block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "cd"
    args = KommandArgsBuilder().apply(block).build()
}

// TODO: include dir
inline fun Kommands.cd(dir: String = "", crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
        kommand {
            name = "cd"
            args = KommandArgsBuilder().apply(block).build()
        }
)

inline fun grep(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "grep"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.grep(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
        kommand {
            name = "grep"
            args = KommandArgsBuilder().apply(block).build()
        }

)

inline fun wc(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "wc"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.wc(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
        kommand {
            name = "wc"
            args = KommandArgsBuilder().apply(block).build()
        }
)



