import extensions.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("application")
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
    kotlin("plugin.jpa") version "1.5.20"
}

group = Config.GROUP
version = Config.VERSION
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    addAllStarters()

    addJackson()

    addKotlinDependencies()

    addDatabases()

    addHelpers()
}

application {
    mainClass.set("com.ferum_bot.quotesapi.application.QuotesApiApplication")
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "com.ferum_bot.quotesapi.application.QuotesApiApplication")
    }
}

tasks.withType<BootJar> {
    manifest {
        attributes("Main-Class" to "com.ferum_bot.quotesapi.application.QuotesApiApplication")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
