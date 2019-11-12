package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy

inline fun KommandShell.cd(dir: String? = null, crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    val newDir = if (dir != null) {
        dir
    } else {
        val ka = KommandArgsBuilder().apply(block).also{ b -> dir?.let { b.add(it) } }.build()
        check (ka.count() == 1) { "no directory provided for cd (chdir)" }
        ka.first().arg
    }
    cd(newDir)
}

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
