package io.github.eliahburns.kommand.util.secure

import io.github.eliahburns.kommand.Kommand
import io.github.eliahburns.kommand.internalKommand
import io.github.eliahburns.kommand.shell.KommandShell

/** TODO: consider removing */
sealed class Scp {
    object RemoteToLocal : Scp()
    object LocalToRemote : Scp()
    object RemoteToRemote : Scp()
}

/** Files are copied from A -> B */
fun KommandShell.scp(
    userA: String = "",
    userB: String = "",
    hostA: String = "",
    hostB: String = "",
    pathA: String = "",
    pathB: String = ""
): Kommand = internalKommand("scp", workingDirectory.current, environment) {
    val a = if (userA.isNotEmpty() && hostA.isNotEmpty()) "$userA@$hostA:$pathA" else pathA
    val b = if (userB.isNotEmpty() && hostB.isNotEmpty()) "$userB@$hostB:$pathB" else pathB
    +a
    +b
}
