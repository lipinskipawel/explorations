plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":app"))

    testImplementation(libs.testing.junit.api)
    testImplementation(libs.testing.junit.engine)
    testImplementation(libs.testing.assertj)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.register<JavaExec>("generateCars") {
    classpath(sourceSets.main.get().runtimeClasspath)
    mainClass.set("com.github.lipinskipawel.Application")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
