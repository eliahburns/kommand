package io.github.eliahburns.kommand

sealed class ExitStatus {

    abstract val value: Int

    object OK : ExitStatus() {
        override val value: Int = 0
    }

    data class Code(override val value: Int) : ExitStatus()
}