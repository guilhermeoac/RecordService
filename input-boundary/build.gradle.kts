
plugins {
    id("java")
}

group = "com.ntd.recordservice"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    implementation("org.springframework.data:spring-data-commons:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-security:3.1.10")
}

tasks.test {
    useJUnitPlatform()
}