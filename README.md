# Kommand ~ Kotlin Shell DSL

Kommand is essentially Java's `Process` class and its associated libs wrapped up
in a nicer package. 

Commands can be easily piped into each other, by calling another command on top of 
the other. And no process is created until `out()` is called. `out()` returns a 
`Flow` of output from the pipeline of commands, which can then be operated on with
any method that extends a Kotlin `Flow<String>` or whatever type you decide to 
transform it into.

It has all of the hits, but may be missing something you're looking for. In that case, 
feel free to extend it and submit a pull request. 
 

#### Examplezz:
```kotlin
fun main() = runBlocking {

    // check the number of gradle files in the current directory
    ls { -"a" }
        .grep { -"e" of "gradle" }
        .wc { -"w" }
        .out()
        .collect { println(it) }

    // print the output from pinging google.com to check our network connection
    ping(host = "google.com")
        .out()
        .collect { println(it) }

    // run a custom script and observe the output
    cd(dir = "$HOME/bash-scripts")
        .kommands { add("./some_bash_script.sh") } 
        .out()
        .collect { println(it) }

}
```
