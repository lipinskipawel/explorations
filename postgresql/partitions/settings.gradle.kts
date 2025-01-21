plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "partitions"

include("car-state")
include("car-started-date")

include(":random:car-state")
project(":random:car-state").projectDir = file("random/car-state")
include(":random:car-started-date")
project(":random:car-started-date").projectDir = file("random/car-started-date")
