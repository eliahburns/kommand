package io.github.eliahburns.kommand.dsl

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy


inline fun KommandShell.mkdir(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "mkdir"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.rm(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "rm"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.du(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "du"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.df(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "df"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.tree(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "tree"
        args = KommandArgsBuilder().apply(block).build()
    }
}
