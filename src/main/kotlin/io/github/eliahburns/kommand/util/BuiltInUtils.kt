package io.github.eliahburns.kommand.util


data class WorkingDirectory(val current: String = currentWorkingDir(), val previous: String = currentWorkingDir())

fun WorkingDirectory.chdir(path: String): WorkingDirectory = when (path) {
    ".." -> copy(previous = current, current = current.substringBeforeLast("/"))
    "-" -> {
        val (newCurrent, newPrevious) = previous to current
        copy(current = newCurrent, previous = newPrevious)
    }
    else -> copy(previous = current, current = path)
}



