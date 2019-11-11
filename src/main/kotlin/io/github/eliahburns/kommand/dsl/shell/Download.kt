package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.simple.KommandArgsBuilder
import io.github.eliahburns.kommand.simple.Kommands
import io.github.eliahburns.kommand.simple.kommand
import io.github.eliahburns.kommand.simple.kommands

// TODO: include host
inline fun ping(host: String = "", block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "ping"
    args = KommandArgsBuilder().apply(block).build()
}

// TODO: include host
inline fun Kommands.ping(host: String = "", crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "ping"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun wget(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "wget"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.wget(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "wget"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun curl(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "curl"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.curl(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "curl"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun tar(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "tar"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.tar(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "tar"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun gzip(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "gzip"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.gzip(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "gzip"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun gunzip(block: KommandArgsBuilder.() -> Unit) = kommands {
    name = "gunzip"
    args = KommandArgsBuilder().apply(block).build()
}

inline fun Kommands.gunzip(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "gunzip"
                args = KommandArgsBuilder().apply(block).build()
            }
)
