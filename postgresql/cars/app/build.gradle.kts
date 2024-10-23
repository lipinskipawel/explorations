plugins {
    application
    id("com.revolut.jooq-docker") version "0.3.12"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(libs.io.javalin)
    implementation("org.jooq:jooq:3.19.11")
    implementation(libs.org.flyway.core)
    runtimeOnly(libs.org.flyway.postgresql)
    jdbc(libs.org.postgresql.driver)

    implementation(libs.com.zaxxer.hikariCP)

    testImplementation(libs.testing.junit.api)
    testImplementation(libs.testing.junit.engine)
    testImplementation(libs.testing.assertj)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(libs.org.postgresql.driver)
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:postgresql:1.20.1")

    testImplementation("org.apache.groovy:groovy-all:4.0.23")
    testImplementation("org.spockframework:spock-core:2.4-M4-groovy-4.0")
}

tasks {
    generateJooqClasses {
        schemas = arrayOf("public")
        basePackageName = "com.github.lipinskipawel.jooq"
        outputDirectory.set(project.layout.buildDirectory.dir("generated-sources"))
        excludeFlywayTable = true
        customizeGenerator {
            database.withForcedTypes(
                org.jooq.meta.jaxb.ForcedType()
                    .withUserType("java.time.Instant")
                    .withConverter("com.github.lipinskipawel.db.JooqInstantConverter")
                    .withTypes("TIMESTAMP")
            )
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.github.lipinskipawel.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
