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
    implementation(project(mapOf("path" to ":output-boundary")))
    implementation(project(mapOf("path" to ":entities")))
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    implementation("org.springframework.data:spring-data-commons:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.2.4")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("org.springframework.boot:spring-boot-starter-security:3.1.10")
}

tasks.test {
    useJUnitPlatform()
}