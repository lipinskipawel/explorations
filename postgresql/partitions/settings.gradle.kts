plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "partitions"

include("car-state")
include("car-started-date")

include(":random:r-car-state")
project(":random:r-car-state").projectDir = file("random/r-car-state")
include(":random:r-car-started-date")
project(":random:r-car-started-date").projectDir = file("random/r-car-started-date")
