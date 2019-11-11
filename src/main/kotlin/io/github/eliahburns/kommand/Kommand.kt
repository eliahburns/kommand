package io.github.eliahburns.kommand

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import java.io.Closeable
import java.io.InputStream
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


private val logger = KotlinLogging.logger {  }

class Kommand internal constructor(val args: List<String>) : Closeable {

    private val p by lazy {
        ProcessBuilder()
            .command(args)
            .redirectErrorStream(true)
            .start()
    }
    private val br by lazy { p.inputStream.bufferedReader() }

    fun execute(): Kommand {
        logger.info { "executing command ${args.first()} with pid: ${p.pid()}" }
        return this
    }

    override fun close() {
        p.destroy()
    }

    fun kill() = close()
    fun forceKill() = p.destroyForcibly()

    private val processInputChannel = Channel<String>(Channel.BUFFERED)
    private val processInput get() = p.outputStream

    val input get() = processInputChannel as SendChannel<String>


    suspend fun collectInputWith(context: CoroutineContext): Kommand = withContext(context) {
        launch {
            processInput.use { pi ->
                pi.bufferedWriter().use { writer ->
                    for (nextInput in processInputChannel) {
                        logger.info { "writing $nextInput to command ${args.first()}" }
                        writer.write(nextInput)
                        writer.flush()
                    }
                }
            }
        }
        this@Kommand
    }

    suspend fun exitValue() = suspendCoroutine<Int> { cont ->
        val exit = p.onExit().thenAcceptAsync {
            try {
                cont.resume(it.exitValue())
            } catch (e: IllegalStateException) {
                cont.resumeWithException(e)
            }
        }
    }

    fun output(): Flow<String> = br
        .lineSequence()
        .asFlow()
        .onStart {
            logger.info { "collecting output from process with pid: ${p.pid()}" }
        }
        .onCompletion {
            logger.info { "completing output from process with pid: ${p.pid()}" }
            logger.info { "closing buffered used for output of process with pid: ${p.pid()}" }
            br.close()
            kill()
        }
}



class PipedKommand {

}

fun Flow<Kommand>.pipe(): PipedKommand {

    TODO()
}

fun Flow<List<PipedKommand>>.out(): Flow<String> {
    TODO()
}

fun Flow<Kommand>.env(block: MutableMap<String, String>.() -> Unit): Flow<Kommand> {
    TODO()
}








internal fun InputStream.lineSequence(): Sequence<String> {
    return this.bufferedReader()
        .lineSequence()
}


//fun Kommand.outputLines() =
//    process.inputStream
//        .use { stream ->
//            stream.bufferedReader()
//                .use { reader ->
//                    reader.lines().asSequence()
//                }
//        }
//
//fun Kommand.outputLinesFlow() = outputLines().asFlow()
//
//
//fun Kommand.allOutput() = process.inputStream.use { String(it.readAllBytes()) }
