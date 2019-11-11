package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.Kommands
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.kommands


inline fun su(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "su"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.su(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "su"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun sudo(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "sudo"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.sudo(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "sudo"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun chmod(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "chmod"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.chmod(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "chmod"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun users(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "users"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.users(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "users"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun useradd(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "useradd"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.useradd(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "useradd"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun userdel(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "userdel"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.userdel(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "userdel"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun groups(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "groups"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.groups(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "groups"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun groupadd(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "groupadd"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.groupadd(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "groupadd"
                args = KommandArgsBuilder().apply(block).build()
            }
)

inline fun groupdel(block: KommandArgsBuilder.() -> Unit) =
    kommands {
        name = "groupdel"
        args = KommandArgsBuilder().apply(block).build()
    }

inline fun Kommands.groupdel(crossinline block: KommandArgsBuilder.() -> Unit): Kommands = copy(
    commands = commands +
            kommand {
                name = "groupdel"
                args = KommandArgsBuilder().apply(block).build()
            }
)
