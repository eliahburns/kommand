package io.github.eliahburns.kommand.util

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.internalKommand


data class WorkingDirectory(val current: String = currentWorkingDir(), val previous: String = currentWorkingDir())

fun WorkingDirectory.chdir(path: String): WorkingDirectory = when (path) {
    ".." -> copy(previous = current, current = current.substringBeforeLast("/"))
    "." -> copy()
    "-" -> {
        val (newCurrent, newPrevious) = previous to current
        copy(current = newCurrent, previous = newPrevious)
    }
    "" -> copy(previous = current, current = userHomeDir())
    else -> copy(previous = current, current = path)
}







