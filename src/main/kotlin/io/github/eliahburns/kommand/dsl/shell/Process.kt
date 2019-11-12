package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy

inline fun KommandShell.ps(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "ps"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.kill(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "kill"
        args = KommandArgsBuilder().apply(block).build()
    }
}
