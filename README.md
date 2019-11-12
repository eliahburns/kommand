# Kommand ~ Kotlin Command Line DSL

Kommand is essentially Java's `Process` class and its associated libs wrapped up
in a nicer package. 

Commands can be easily piped into each other, by calling another command on top of 
the other. And no process is created until `out()` is called. `out()` returns a 
`Flow` of output from the pipeline of commands, which can then be operated on with
any method that extends a Kotlin `Flow<String>` or whatever type you decide to 
transform it into.

It has all of the Unix command line hits, but may be missing something you're looking for. In that case, 
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
        .kommand { +"./some_bash_script.sh" } 
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

### Argument Syntax

There are currently three main styles to using arguments with a given command: 
- using a dash, as in `-"l"`, which is equivalent to typing `-l` at the command line
    - note that a `-` is inserted before the argument string with this method
- using a plus, as in `+"--some-flag"` or `+"file.txt"`, which is equivalent to typing `--some-flag` or 
`file.txt` at the command line
    - note that no `-` is inserted before the argument string here 
- using a dashed-string with infix `of` followed by another string, as in `-"e" of "*"`, which is 
equivalent to typing `-e *` at the command line when using `grep -e *` for example and `*` is a pattern
provided in conjunction with the `-e` arg (but this can be used any place that requires the same format)
    - the actual method signature for this is `infix fun String.of(value: String): KommandArg`
  
   
Given the above, we can assume that a command such as 
```kotlin
    shell { }
        .grep {   
            -"i" 
            +"mr_tube" 
            +"users.txt"
        }
``` 
would be equivalent to entering `grep -i mr_tube users.txt` at the command line.
   
    
Some `Kommand` methods have explicit parameters, such as `cd(dir: String)`--this is
mostly only currently in the spots where it (usually) doesn't make sense to use the 
command without that argument. 

    
