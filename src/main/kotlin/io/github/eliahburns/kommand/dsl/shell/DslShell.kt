package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.*
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy


inline fun KommandShell.ls(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "ls"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.grep(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "grep"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.wc(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "wc"
        args = KommandArgsBuilder().apply(block).build()
    }
}
typealias Export = Pair<String, String>

fun Export.isEmpty() = first.isEmpty() && second.isEmpty()

class ExportBuilder {

    var export: Export? = null

    infix fun String.to(value: String): Export {
        export = this to value
        return export!!
    }
    fun build() = export ?: Export("", "")
}

inline fun KommandShell.export(crossinline block: ExportBuilder.() -> Unit) = copy(
    env = env + ExportBuilder().apply(block).build()
)
