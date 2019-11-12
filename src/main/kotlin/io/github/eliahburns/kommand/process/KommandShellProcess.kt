package io.github.eliahburns.kommand.process

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.toArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {  }


fun Kommand.toProcessBuilder(workingDirectory: String? = null): ProcessBuilder = ProcessBuilder()
    .command(command, *args.toArgs().toTypedArray())
    .redirectErrorStream(true)
    .also { pb -> workingDirectory?.let { wd -> pb.directory(File(wd)) } }


fun List<Kommand>.startPipeline(workingDirectory: String? = null): List<Process> {
    return ProcessBuilder.startPipeline(map { it.toProcessBuilder(workingDirectory) })
}

fun KommandShell.out(): Flow<String> = channelFlow {

    val processes = this@out.commands.startPipeline(this@out.currentWorkingDir)
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
