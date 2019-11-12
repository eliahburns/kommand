package io.github.eliahburns.kommand.dsl

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy

inline fun KommandShell.ping(host: String = "", crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "ping"
        args = KommandArgsBuilder().also { if (host.isNotEmpty()) it.add(host) }.apply(block).build()
    }
}

inline fun KommandShell.wget(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "wget"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.curl(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "curl"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.tar(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "tar"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.gzip(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "gzip"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.gunzip(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "gunzip"
        args = KommandArgsBuilder().apply(block).build()
    }
}
