package io.github.eliahburns.kommand.util.process

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.util.toArgs
import java.io.File

fun Kommand.toProcessBuilder(): ProcessBuilder = ProcessBuilder()
    .command(command, *args.toArgs().toTypedArray())
    .redirectErrorStream(true)
    .directory(File(dir))
    .also { pb -> pb.environment().putAll(env.entries.map { e -> e.key to e.value }) }

fun List<Kommand>.startPipeline(): List<Process> = ProcessBuilder.startPipeline(map { it.toProcessBuilder() })

