package io.github.eliahburns.kommand

data class KommandBuilder(var command: String) {

    private val args = mutableListOf(command)

    operator fun String.unaryPlus() {
        args += this
    }

    infix fun and(arg: String): KommandBuilder {
        args += arg
        return this
    }

    fun build(): KommandProcess {
        return KommandProcess(ProcessBuilder().command(args).start())
    }
}

inline fun kommand(command: String, block: KommandBuilder.() -> Unit): KommandProcess {
    return KommandBuilder(command).apply(block).build()
}

fun main() {

    val k = kommand("ls") { this and "-a" and "-TRL" }

    println(k.allOutput())

//    val p = ProcessBuilder().command(listOf("ls", "-a")).start()
//    println(p.inputStream.use { istream -> String(istream.readAllBytes()) })

}