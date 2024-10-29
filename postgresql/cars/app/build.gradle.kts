plugins {
    id("java-library")
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
    runtimeOnly(libs.org.flyway.postgresql)
    jdbc(libs.org.postgresql.driver)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")

    implementation(libs.com.zaxxer.hikariCP)

    testImplementation(libs.testing.junit.api)
    testImplementation(libs.testing.junit.engine)
    testImplementation(libs.testing.assertj)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(libs.org.postgresql.driver)
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:postgresql:1.20.1")
}

tasks {
    build {
        dependsOn("uberJar")
    }

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

    create<Jar>("uberJar") {
        archiveClassifier = "uber"

        from(sourceSets.main.get().output)

        dependsOn(configurations.runtimeClasspath)
        from(configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
        )

        manifest {
            attributes("Main-Class" to "com.github.lipinskipawel.App")
        }

        // default in https://github.com/johnrengelman/shadow
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        exclude("META-INF/INDEX.LIST", "META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
        // mergeServiceFiles()
    }

    test {
        useJUnitPlatform()
    }
}
