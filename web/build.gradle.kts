
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
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-security:3.1.10")
}

tasks.test {
    useJUnitPlatform()
}