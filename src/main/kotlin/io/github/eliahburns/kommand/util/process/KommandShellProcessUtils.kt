package io.github.eliahburns.kommand.util.process

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.util.toArgs
import java.io.File

fun Kommand.toProcessBuilder(workingDirectory: String? = null): ProcessBuilder = ProcessBuilder()
    .command(command, *args.toArgs().toTypedArray())
    .redirectErrorStream(true)
    .also { pb -> workingDirectory?.let { wd -> pb.directory(File(wd)) } }

fun List<Kommand>.startPipeline(workingDirectory: String? = null): List<Process> {
    return ProcessBuilder.startPipeline(map { it.toProcessBuilder(workingDirectory) })
}

