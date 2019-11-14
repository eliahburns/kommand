package io.github.eliahburns.kommand.dsl

import io.github.eliahburns.kommand.internalKommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.util.args.KommandArgsBuilder
import io.github.eliahburns.kommand.util.copy

inline fun KommandShell.ls(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ls", workingDirectory.current, environment, block)
}

inline fun KommandShell.grep(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("grep", workingDirectory.current, environment, block)
}

inline fun KommandShell.tail(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("tail", workingDirectory.current, environment, block)
}

inline fun KommandShell.head(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("head", workingDirectory.current, environment, block)
}

inline fun KommandShell.wc(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("wc", workingDirectory.current, environment, block)
}

inline fun KommandShell.ps(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ps", workingDirectory.current, environment, block)
}

inline fun KommandShell.kill(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("kill", workingDirectory.current, environment, block)
}

inline fun KommandShell.disown(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("disown", workingDirectory.current, environment, block)
}

inline fun KommandShell.su(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("su", workingDirectory.current, environment, block)
}

inline fun KommandShell.sudo(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("sudo", workingDirectory.current, environment, block)
}

inline fun KommandShell.chmod(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("chmod", workingDirectory.current, environment, block)
}

inline fun KommandShell.users(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("users", workingDirectory.current, environment, block)
}

inline fun KommandShell.useradd(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("useradd", workingDirectory.current, environment, block)
}

inline fun KommandShell.userdel(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("userdel", workingDirectory.current, environment, block)
}

inline fun KommandShell.groups(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("groups", workingDirectory.current, environment, block)
}

inline fun KommandShell.groupadd(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("groupadd", workingDirectory.current, environment, block)
}

inline fun KommandShell.groupdel(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("groupdel", workingDirectory.current, environment, block)
}

// TODO: include host as first argument
inline fun KommandShell.ping(host: String = "", crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ping", workingDirectory.current, environment, block)
}

inline fun KommandShell.wget(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("wget", workingDirectory.current, environment, block)
}

inline fun KommandShell.curl(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("curl", workingDirectory.current, environment, block)
}

inline fun KommandShell.tar(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("tar", workingDirectory.current, environment, block)
}

inline fun KommandShell.gzip(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("gzip", workingDirectory.current, environment, block)
}

inline fun KommandShell.gunzip(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("gunzip", workingDirectory.current, environment, block)
}

inline fun KommandShell.uniq(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("uniq", workingDirectory.current, environment, block)
}

inline fun KommandShell.sort(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("sort", workingDirectory.current, environment, block)
}

inline fun KommandShell.diff(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("diff", workingDirectory.current, environment, block)
}

inline fun KommandShell.comp(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("comp", workingDirectory.current, environment, block)
}

inline fun KommandShell.cut(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("cut", workingDirectory.current, environment, block)
}

inline fun KommandShell.sed(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("sed", workingDirectory.current, environment, block)
}

inline fun KommandShell.mkdir(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("mkdir", workingDirectory.current, environment, block)
}

inline fun KommandShell.rm(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("rm", workingDirectory.current, environment, block)
}

inline fun KommandShell.du(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("du", workingDirectory.current, environment, block)
}

inline fun KommandShell.df(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("df", workingDirectory.current, environment, block)
}

inline fun KommandShell.tree(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("tree", workingDirectory.current, environment, block)
}

inline fun KommandShell.ssh(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ssh", workingDirectory.current, environment, block)
}

inline fun KommandShell.scp(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("scp", workingDirectory.current, environment, block)
}

inline fun KommandShell.touch(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("touch", workingDirectory.current, environment, block)
}

inline fun KommandShell.source(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("source", workingDirectory.current, environment, block)
}

inline fun KommandShell.which(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("which", workingDirectory.current, environment, block)
}

inline fun KommandShell.locate(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("locate", workingDirectory.current, environment, block)
}

inline fun KommandShell.find(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("find", workingDirectory.current, environment, block)
}

