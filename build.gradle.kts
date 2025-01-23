plugins {
    alias(libs.plugins.kotlin.jvm) apply true
    alias(libs.plugins.kotlin.spring) apply true
    alias(libs.plugins.spring.boot) apply true
    alias(libs.plugins.spring.dependency.management) apply true
}

allprojects {
    apply(plugin = rootProject.libs.plugins.kotlin.jvm.get().pluginId)
    apply(plugin = rootProject.libs.plugins.kotlin.spring.get().pluginId)
    apply(plugin = rootProject.libs.plugins.spring.boot.get().pluginId)
    apply(plugin = rootProject.libs.plugins.spring.dependency.management.get().pluginId)

    repositories {
        mavenCentral()
    }
}

subprojects {
    dependencies {
        implementation(rootProject.libs.kotlin.stblib)
        implementation(rootProject.libs.kotlin.reflect)
        implementation(rootProject.libs.kotlin.coroutines.test)
        implementation(rootProject.libs.kotlin.coroutines.core)

        implementation(rootProject.libs.spring.boot.starter)
        implementation(rootProject.libs.spring.boot.starter.web)
        implementation(rootProject.libs.spring.boot.starter.test)
        implementation(rootProject.libs.jackson.kotlin)

        implementation(rootProject.libs.spring.boot.autoconfigure)

        testImplementation(rootProject.libs.junit.api)
        testImplementation(rootProject.libs.junit.engine.jupiter)
        testImplementation(rootProject.libs.assertj)
        testImplementation(rootProject.libs.kotlin.junit.test)


        testRuntimeOnly(rootProject.libs.junit.platform.launcher)
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks {
        jar {
            enabled = true
        }
        bootJar {
            enabled = false
        }
    }
}