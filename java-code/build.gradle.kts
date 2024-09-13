import org.jooq.meta.jaxb.ForcedType

plugins {
    `java-library`
    id("com.revolut.jooq-docker") version ("0.3.12")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jooq:jooq:3.19.11")
    jdbc("org.postgresql:postgresql:42.7.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
            dependencies {
                implementation("org.testcontainers:postgresql:1.20.1")
                implementation("org.postgresql:postgresql:42.7.4")
                implementation("org.flywaydb:flyway-core:10.17.1")
                implementation("org.flywaydb:flyway-database-postgresql:10.17.1")
            }
        }
    }
}

tasks {
    generateJooqClasses {
        schemas = arrayOf("public")
        basePackageName = "org.jooq.generated"
        inputDirectory.setFrom(project.files("src/main/resources/db/migration"))
        outputDirectory.set(project.layout.buildDirectory.dir("generated-jooq"))
        flywayProperties = mapOf("flyway.placeholderReplacement" to "false")
        excludeFlywayTable = true
        outputSchemaToDefault = setOf("public")
        customizeGenerator {
            database.withForcedTypes(
                ForcedType()
                    .withUserType("java.time.Instant")
                    .withConverter("com.github.lipinskipawel.dbTransactions.JooqInstantConverter")
                    // SQLDataType
                    .withTypes("TIMESTAMP")
            )
        }
    }
}

tasks.named("compileJava") {
    dependsOn("generateJooqClasses")
}
