package io.github.eliahburns.kommand

import io.github.eliahburns.kommand.dsl.ls
import io.github.eliahburns.kommand.dsl.rm
import io.github.eliahburns.kommand.dsl.touch
import io.github.eliahburns.kommand.shell.out
import io.github.eliahburns.kommand.shell.shell
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking


class KommandTest : WordSpec() {
    init {

        "touching and removing a file" should {
            "pass basic test" {
                runBlocking {
                    val fileName = "test.txt"
                    shell { }
                        .ls { +fileName }
                        .out()
                        .toList()
                        .also {
                            it.size shouldBe 1
                            it.first() shouldBe "ls: test.txt: No such file or directory"
                        }
                    shell { }
                        .touch { +fileName }
                        .out()
                        .collect()
                    shell { }
                        .ls { +fileName }
                        .out()
                        .toList()
                        .also {
                            it.size shouldBe 1
                            it.first() shouldBe "test.txt"
                        }
                    shell { }
                        .rm { +fileName }
                        .out()
                        .collect()
                    shell { }
                        .ls { +fileName }
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