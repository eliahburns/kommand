package io.github.eliahburns.kommand.dsl

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy


inline fun KommandShell.ssh(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "ssh"
        args = KommandArgsBuilder().apply(block).build()
    }
}


inline fun KommandShell.scp(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +kommand {
        name = "scp"
        args = KommandArgsBuilder().apply(block).build()
    }
}
