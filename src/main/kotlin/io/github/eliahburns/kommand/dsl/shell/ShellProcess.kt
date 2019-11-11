package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.toArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }


fun Kommand.toProcessBuilder(): ProcessBuilder = ProcessBuilder()
    .command(command, *args.toArgs().toTypedArray())
    .redirectErrorStream(true)


fun List<Kommand>.startPipeline(): List<Process> {
    return ProcessBuilder.startPipeline(map { it.toProcessBuilder() })
}

fun KommandShell.out(): Flow<String> = channelFlow {

    val processes = this@out.commands.startPipeline()
    processes.forEach { proc -> logger.debug { "started process with pid ${proc.pid()} on invocation of 'out()'" } }

    launch(Dispatchers.IO) {
        processes
            .last()
            .inputStream
            .bufferedReader()
            .useLines {  lines ->
                lines.forEach {  line ->
                    send(line)
                }
            }
    }

    invokeOnClose { t ->
        t?.let { logger.debug { "closing 'out()' after $t" } }
        processes.forEach { proc ->
            logger.debug { "stopping process with pid ${proc.pid()} on 'out()' close" }
            proc.destroy()
        }
    }
}
