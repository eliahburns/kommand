package io.github.eliahburns.kommand

import io.github.eliahburns.kommand.dsl.ls
import io.github.eliahburns.kommand.dsl.rm
import io.github.eliahburns.kommand.dsl.touch
import io.github.eliahburns.kommand.shell.out
import io.github.eliahburns.kommand.shell.shell
import io.github.eliahburns.kommand.util.currentWorkingDir
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking


class KommandTest : WordSpec() {
    init {

        "touching and removing a file" should {
            "pass basic test" {
                runBlocking {
                    val fileName = "test.txt"
                    shell { }
                        .ls { add(fileName) }
                        .out()
                        .toList()
                        .also {
                            it.size shouldBe 1
                            it.first() shouldBe "ls: test.txt: No such file or directory"
                        }
                    shell { }
                        .touch { add(fileName) }
                        .out()
                        .collect()
                    shell { }
                        .ls { add(fileName) }
                        .out()
                        .toList()
                        .also {
                            it.size shouldBe 1
                            it.first() shouldBe "test.txt"
                        }
                    shell { }
                        .rm { add(fileName) }
                        .out()
                        .collect()
                    shell { }
                        .ls { add(fileName) }
                        .out()
                        .toList()
                        .also {
                            it.size shouldBe 1
                            it.first() shouldBe "ls: test.txt: No such file or directory"
                        }
                }
            }
        }


    }
}