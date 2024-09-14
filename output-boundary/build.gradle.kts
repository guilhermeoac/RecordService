
plugins {
    id("java")
}

group = "com.ntd.recordservice"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("org.springframework.data:spring-data-commons:3.1.10")
}
