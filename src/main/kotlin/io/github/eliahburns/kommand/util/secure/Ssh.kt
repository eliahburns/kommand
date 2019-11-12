package io.github.eliahburns.kommand.util.secure

import io.github.eliahburns.kommand.internalKommand
import io.github.eliahburns.kommand.util.toArgs
import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.util.args.KommandArgsBuilder


/** Builds an SSH [Kommand] that can be run in a process */
fun ssh(
    user: String,
    host: String,
    remoteKommand: KommandArgsBuilder.() -> Unit
) = internalKommand("ssh") {
    +"$user@$host"
    +"""'${KommandArgsBuilder().apply(remoteKommand).build().toArgs().joinToString(" ")}'"""
}

val x = ssh(user = "u", host = "h") {
    +"source ~/.bashrc "
}


