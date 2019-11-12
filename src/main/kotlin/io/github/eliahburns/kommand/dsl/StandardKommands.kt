package io.github.eliahburns.kommand.dsl

import io.github.eliahburns.kommand.internalKommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.util.args.KommandArgsBuilder
import io.github.eliahburns.kommand.util.copy

inline fun KommandShell.ls(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ls", block)
}

inline fun KommandShell.grep(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("grep", block)
}

inline fun KommandShell.tail(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("tail", block)
}

inline fun KommandShell.head(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("head", block)
}

inline fun KommandShell.wc(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("wc", block)
}

inline fun KommandShell.ps(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ps", block)
}

inline fun KommandShell.kill(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("kill", block)
}

inline fun KommandShell.disown(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("disown", block)
}

inline fun KommandShell.su(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("su", block)
}

inline fun KommandShell.sudo(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("sudo", block)
}

inline fun KommandShell.chmod(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("chmod", block)
}

inline fun KommandShell.users(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("users", block)
}

inline fun KommandShell.useradd(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("useradd", block)
}

inline fun KommandShell.userdel(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("userdel", block)
}

inline fun KommandShell.groups(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("groups", block)
}

inline fun KommandShell.groupadd(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("groupadd", block)
}

inline fun KommandShell.groupdel(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +internalKommand("groupdel", block)
}

inline fun KommandShell.ping(host: String = "", crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ping", block)
}

inline fun KommandShell.wget(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("wget", block)
}

inline fun KommandShell.curl(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("curl", block)
}

inline fun KommandShell.tar(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("tar", block)
}

inline fun KommandShell.gzip(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("gzip", block)
}

inline fun KommandShell.gunzip(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("gunzip", block)
}

inline fun KommandShell.uniq(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("uniq", block)
}

inline fun KommandShell.sort(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("sort", block)
}

inline fun KommandShell.diff(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("diff", block)
}

inline fun KommandShell.comp(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("comp", block)
}

inline fun KommandShell.cut(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("cut", block)
}

inline fun KommandShell.sed(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("sed", block)
}

inline fun KommandShell.mkdir(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("mkdir", block)
}

inline fun KommandShell.rm(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("rm", block)
}

inline fun KommandShell.du(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("du", block)
}

inline fun KommandShell.df(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("df", block)
}

inline fun KommandShell.tree(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("tree", block)
}

inline fun KommandShell.ssh(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +internalKommand("ssh", block)
}

inline fun KommandShell.scp(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("scp", block)
}

inline fun KommandShell.touch(crossinline block: KommandArgsBuilder.() -> Unit)= copy {
    +internalKommand("touch", block)
}
