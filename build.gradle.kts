plugins {
    alias(libs.plugins.kotlin.jvm) apply true
    alias(libs.plugins.kotlin.spring) apply true
    alias(libs.plugins.kotlin.jpa) apply true
    alias(libs.plugins.spring.boot) apply true
    alias(libs.plugins.spring.dependency.management) apply true

}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
}


group = "org.github.swszz"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stblib)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.kotlin.coroutines.core)

    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.spring.kafka)

    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.data.elasticsearch)
    implementation(libs.spring.boot.autoconfigure)
    runtimeOnly(libs.mysql.connector.j)

    implementation(libs.flink.java)
    implementation(libs.flink.clients)
    implementation(libs.flink.streaming.java)
    implementation(libs.flink.runtime.web)
    implementation(libs.flink.connector.base)
    implementation(libs.flink.connector.files)
    implementation(libs.flink.connector.kafka)

    testImplementation(libs.junit.api)
    testImplementation(libs.junit.engine.jupiter)
    testImplementation(libs.assertj)
    testImplementation(libs.kotlin.junit.test)


    testRuntimeOnly(libs.junit.platform.launcher)
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
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }
}