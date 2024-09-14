plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":input-boundary"))
    implementation(project(":output-boundary"))
    implementation(project(":web"))
    implementation(project(":usecase"))
    implementation(project(":repository"))
    implementation(project(":configuration"))

    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    implementation("org.springframework.data:spring-data-commons:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}

