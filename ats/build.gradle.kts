plugins {
    alias(libs.plugins.kotlin.jpa) apply true
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
}

dependencies {
    implementation(rootProject.libs.springdoc.openapi.starter)
    implementation(rootProject.libs.spring.boot.starter.data.jpa)
    runtimeOnly(rootProject.libs.mysql.connector.j)
}