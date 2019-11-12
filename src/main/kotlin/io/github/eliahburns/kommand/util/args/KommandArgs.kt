package io.github.eliahburns.kommand.util.args

import io.github.eliahburns.kommand.shell.KommandShellDsl
import io.github.eliahburns.kommand.Kommand

/** Representation of an argument that belongs to some [Kommand] */
data class KommandArg(val arg: String, val useDash: Boolean = true)

/** The total collections of argument that would belong to a [Kommand] */
typealias KommandArgs = List<KommandArg>

@KommandShellDsl
open class KommandArgsBuilder {
    @PublishedApi
    internal val args = mutableListOf<KommandArg>()


    /** Add an argument to a [Kommand] that would be given WITH a dash, followed by some parameter [param] */
    infix fun KommandArg.of(param: String): Pair<KommandArg, KommandArg> {
        val p = this to KommandArg(param, false)
        args += p.second
        return p
    }

    /** Add an argument to a [Kommand] that would be given WITH a dash [-] */
    inline operator fun String.unaryMinus(): KommandArg {
        val kArg = KommandArg(this)
        args += kArg
        return kArg
    }

    /** Add an argument to a [Kommand] that would be given WITHOUT a dash [-] */
    fun add(flag: String): KommandArg {
        val kArg = KommandArg(flag, false)
        args += kArg
        return kArg
    }

    fun build(): KommandArgs = args.toList()
}
