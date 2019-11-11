package io.github.eliahburns.kommand

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import java.io.OutputStream

private val logger = KotlinLogging.logger {  }

data class KommandBuilder(var command: String) {

    private val args = mutableListOf(command)

    var input: Flow<String>? = null

    operator fun String.unaryPlus() {
        args += this
    }

    infix fun and(arg: String): KommandBuilder {
        args += arg
        return this
    }

    fun build(): Kommand {
        return Kommand(args)
    }
}

inline fun kommand(command: String, block: KommandBuilder.() -> Unit): Kommand {
    return KommandBuilder(command).apply(block).build()
}

suspend fun Flow<String>.pipeTo(command: String, block: KommandBuilder.() -> Unit): Kommand = withContext(Dispatchers.IO) {
    val k = KommandBuilder(command).apply(block).build()
    this@pipeTo.collect { out ->
        logger.info { "piping $out to $command" }
        k.input.send(out)
    }
    //k.launchIn(this)
    k
}

fun main() = runBlocking {

    val k = kommand("ls") { this and "-a"}
        .output()
        .pipeTo("sort") { }
        .output()
        .onEach { println(it) }
        .count()
        .also { println(it) }


    //println(k.allOutput())

//    println(p.inputStream.use { istream -> String(istream.readAllBytes()) })

    val p = ProcessBuilder().command("echo")
        .redirectErrorStream(true)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .start()

    p.outputStream.bufferedWriter().let { writer ->
        listOf("one", "two", "three")
            .forEach {
                println("writing: $it")
                writer.write(it)
                writer.flush()
            }
        }

    delay(1_000)
    p.onExit().get()
    Unit
}