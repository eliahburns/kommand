# Kommand ~ Kotlin Command Line DSL

Kommand is essentially Java's `Process` class and its associated libs wrapped up
in a nicer package. 

Commands can be easily piped into each other, by calling another command on top of 
the other. And no process is created until `out()` is called. `out()` returns a 
`Flow` of output from the pipeline of commands, which can then be operated on with
any method that extends a Kotlin `Flow<String>` or whatever type you decide to 
transform it into.

It has all of the hits, but may be missing something you're looking for. In that case, 
feel free to extend it and submit a pull request. 
 
## Examples:
 
#### check the number of gradle files in the current directory
```kotlin
fun main() = runBlocking {

    shell { }
        .ls { -"a" }
        .grep { -"e" of "gradle" }
        .wc { -"w" }
        .out()
        .collect { println(it) }
}
```

#### Print the output from pinging google.com to check our network connection:
```kotlin
    shell { }
        .ping(host = "google.com") { }
        .out()
        .collect { println(it) }
```

#### Run a custom script and observe the output:
```kotlin
    shell { }
        .cd(dir = "some/path/to/bash-scripts") { }
        .kommand { add("./some_bash_script.sh") } 
        .out()
        .collect { println(it) }
```

#### Go up one directory and run ls:
```kotlin
    shell { }
        .cd(dir = "..") { }
        .ls { }
        .out()
        .collect { println(it) }
```
    
#### Execute a command without a special extension method using the generic `kommmand()` method:
```kotlin
    shell { }
        .ls { }
        .kommand("wc") { -"w" }
        .out()
        .collect { println(it) }
```

#### Add/update variables in environment and print out for sanity check:
```kotlin
    shell { 
        env {
            "LD_LIBRARY_PATH" to "LD_LIBRARY_PATH:/some/other/dynamic/lib"
            "JAVA_HOME" to "path/to/java/we/want/to/use" 
        } 
    }
        .env
        .entries
        .forEach { println(it) }
```
```bash
# Output: 
# LD_LIBRARY_PATH=LD_LIBRARY_PATH:/some/other/dynamic/lib
# JAVA_HOME=path/to/java/we/want/to/use
```

#### Add/update variables on the fly with the `export()` method:
```kotlin
    shell {
        env {
            "LD_LIBRARY_PATH" to "LD_LIBRARY_PATH:/some/other/dynamic/lib"
            "JAVA_HOME" to "path/to/java/we/want/to/use"
        }
    }
        .export { "KRB5CC" to "/dev/null" }
        .env
        .entries
        .forEach { println(it) }
```
```bash
# Output: 
# LD_LIBRARY_PATH=LD_LIBRARY_PATH:/some/other/dynamic/lib
# JAVA_HOME=path/to/java/we/want/to/use
# KRB5CC=/dev/null 
```


#### Take a certain number of outputs and then automatically destroy all the processes that were used to get them:
```kotlin
    shell { }
        .cd(dir = "../log") { }
        .ls { }
        .sort { }
        .out()
        .take(10)
        .toList()
        .collect { 
            println("top ten log files: $it")
        }
```
