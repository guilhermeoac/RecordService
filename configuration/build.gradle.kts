
plugins {
    id("java")
}

group = "com.ntd.recordservice"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation(project(mapOf("path" to ":input-boundary")))
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    implementation("org.springframework.data:spring-data-commons:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}