package io.github.eliahburns.kommand.util

sealed class File {
    object Directory : File()
    object Regular : File()
    object SymbolicLink : File()
    object Socket : File()
    object Device : File()
    object Door : File()
    object FIFO : File()
}

val String.isRegularFile get() = java.io.File(this).isFile

val String.isDirectory get() = java.io.File(this).isDirectory


