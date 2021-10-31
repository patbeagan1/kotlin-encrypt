plugins {
    kotlin("jvm")
    java
    `maven-publish`
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/patbeagan1/kotlin-encrypt")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
        mavenLocal()
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "dev.patbeagan1"
            artifactId = rootProject.name
            version = "0.1.2-SNAPSHOT"
        }
    }
}