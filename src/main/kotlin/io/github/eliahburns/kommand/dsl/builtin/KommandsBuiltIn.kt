package io.github.eliahburns.kommand.dsl.builtin

import io.github.eliahburns.kommand.KommandArgsBuilder
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

fun KommandShell.env() = System.getenv().entries.map { it.key to it.value }
