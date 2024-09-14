
plugins {
    id("java")
}

group = "com.ntd.recordservice"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation(project(mapOf("path" to ":output-boundary")))
    implementation(project(mapOf("path" to ":entities")))
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.4")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}
