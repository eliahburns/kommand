package io.github.eliahburns.kommand.dsl.builtin

import io.github.eliahburns.kommand.shell.KommandShell

typealias Export = Pair<String, String>

fun Export.isEmpty() = first.isEmpty() && second.isEmpty()

class ExportBuilder {

    var export: Export? = null

    infix fun String.to(value: String): Export {
        export = Pair(this, value)
        return export!!
    }
    fun build() = export ?: Export("", "")
}

inline fun KommandShell.export(crossinline block: ExportBuilder.() -> Unit) = copy(
    env = env + ExportBuilder().apply(block).build()
)
