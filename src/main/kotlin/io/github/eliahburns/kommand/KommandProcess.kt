package io.github.eliahburns.kommand

import java.io.InputStream
import java.io.OutputStream


class KommandProcess(val process: Process) {
    fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun exitValue(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun waitFor(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val inputStream: InputStream get() = process.inputStream
    val errorStream: InputStream get() = process.errorStream
    val outputStream: OutputStream get() = process.outputStream
}

fun KommandProcess.o() = process.inputStream.use { it.bufferedReader().lines() }
fun KommandProcess.allOutput() = process.inputStream.use { String(it.readAllBytes()) }
