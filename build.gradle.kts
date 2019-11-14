import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    java
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.1"
}

group = "io.github.eliahburns"
version = "0.1.0"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation("io.github.microutils:kotlin-logging:1.7.6")
    testImplementation("org.slf4j:slf4j-simple:1.6.1")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        register(rootProject.name, MavenPublication::class) {
            from(components["kotlin"])
            groupId = project.group as String
            artifactId = rootProject.name
            version = project.version as String
            pom {
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/eliahburns/kommand/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        name.set("Eliah Burns")
                        email.set("eli.s.burns@gmail.com")
                    }

                }
                scm {
                    url.set("https://github.com/eliahburns/kommand")
                    connection.set("scm:git:https://github.com/eliahburns/kommand")
                }
            }
        }
    }
    repositories {
        maven(url = "https://bintray.com/eliahburns")
    }
}

configure<BintrayExtension> {
    user = findProperty("BINTRAY_USER") as? String
    key = findProperty("BINTRAY_API_KEY") as? String
    setPublications(rootProject.name)
    publish = true
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "kommand"
        desc = "Kotlin command line DSL"
        vcsUrl = "https://github.com/eliahburns/kommand.git"
        websiteUrl = "https://eliahburns.github.io/kommand/"
        setLicenses("MIT")
        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            name = project.version as String
        })
    })
}