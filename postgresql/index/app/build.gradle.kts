import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.SyntheticObjectsType
import org.jooq.meta.jaxb.SyntheticPrimaryKeyType

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.revolut.jooq-docker") version "0.3.12"
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
    implementation(libs.org.flyway.core)
    implementation(libs.org.postgresql.driver)
    jdbc(libs.org.postgresql.driver)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")

    implementation(libs.com.zaxxer.hikariCP)
    implementation("org.slf4j:slf4j-simple:2.0.7")

    testImplementation(libs.testing.junit.api)
    testImplementation(libs.testing.junit.engine)
    testImplementation(libs.testing.assertj)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.4.1")

    testImplementation(libs.org.postgresql.driver)
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:postgresql:1.20.1")
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    generateJooqClasses {
        schemas = arrayOf("public")
        basePackageName = "com.github.lipinskipawel.jooq"
        outputDirectory.set(project.layout.buildDirectory.dir("generated-sources"))
        excludeFlywayTable = true
        customizeGenerator {
            database
                .withForcedTypes(
                    ForcedType()
                        .withUserType("java.time.Instant")
                        .withConverter("com.github.lipinskipawel.db.converter.JooqInstantConverter")
                        .withTypes("TIMESTAMP"),
                    ForcedType()
                        .withUserType("java.lang.String")
                        .withConverter("com.github.lipinskipawel.db.converter.JooqJsonbConverter")
                        .withTypes("JSONB")
                )
                .withSyntheticObjects(
                    SyntheticObjectsType()
                        .withPrimaryKeys(SyntheticPrimaryKeyType().withTables(".*\\.CARS").withFields("ID"))
                )
        }
    }

    shadowJar {
        archiveBaseName.set("cars")
        archiveClassifier.set("")
        archiveVersion.set("")
        isZip64 = true
        mergeServiceFiles()
        manifest {
            attributes("Main-Class" to "com.github.lipinskipawel.App")
        }
    }

    test {
        useJUnitPlatform()
    }
}
