package io.github.eliahburns.kommand.dsl.shell

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy


inline fun KommandShell.su(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "su"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.sudo(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "sudo"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.chmod(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "chmod"
        args = KommandArgsBuilder().apply(block).build()
    }
}


inline fun KommandShell.users(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "users"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.useradd(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "useradd"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.userdel(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "userdel"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.groups(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "groups"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.groupadd(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "groupadd"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.groupdel(crossinline block: KommandArgsBuilder.() -> Unit): KommandShell = copy {
    +kommand {
        name = "groupdel"
        args = KommandArgsBuilder().apply(block).build()
    }
}
