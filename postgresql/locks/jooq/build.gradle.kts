plugins {
    id("java-library")
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

dependencies {

    implementation(libs.io.javalin)
    implementation("org.jooq:jooq:3.19.11")

    testImplementation(libs.testing.junit.api)
    testImplementation(libs.testing.junit.engine)
    testImplementation(libs.testing.assertj)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(libs.org.postgresql.driver)
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:postgresql:1.20.1")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
