plugins {
    application
    id("java")
    id("org.graalvm.buildtools.native") version "0.10.1"
}

group = "rodrigoschonardt"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass = "rodrigoschonardt.Main"
}