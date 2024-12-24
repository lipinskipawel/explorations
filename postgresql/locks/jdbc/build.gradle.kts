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

    implementation(libs.com.zaxxer.hikariCP)
    implementation("org.slf4j:slf4j-simple:2.0.7")

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
