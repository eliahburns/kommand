package io.github.eliahburns.kommand.dsl

import io.github.eliahburns.kommand.KommandArgsBuilder
import io.github.eliahburns.kommand.kommand
import io.github.eliahburns.kommand.shell.KommandShell
import io.github.eliahburns.kommand.shell.copy


inline fun KommandShell.uniq(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "uniq"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.sort(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "sort"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.diff(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "diff"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.comp(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "comp"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.cut(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "cut"
        args = KommandArgsBuilder().apply(block).build()
    }
}

inline fun KommandShell.sed(crossinline block: KommandArgsBuilder.() -> Unit) = copy {
    +kommand {
        name = "sed"
        args = KommandArgsBuilder().apply(block).build()
    }
}
