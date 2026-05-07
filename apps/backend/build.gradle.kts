plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.spring") version "2.3.20"
    kotlin("plugin.jpa") version "2.3.20"
    id("org.springframework.boot") version "4.1.0-RC1"
    id("io.spring.dependency-management") version "1.1.7"

    id("org.graalvm.buildtools.native") version "1.1.0"
    id("com.diffplug.spotless") version "8.4.0"
    idea
}

group = "org.tobynguyen"

version = "1.0.0"

description = "A simple URL shortener"

java { toolchain { languageVersion = JavaLanguageVersion.of(25) } }

repositories { mavenCentral() }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("tools.jackson.module:jackson-module-kotlin")
    implementation("org.sqids:sqids-kotlin:0.1.1")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.security:spring-security-crypto")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.bouncycastle:bcprov-jdk18on:1.84")
    implementation("io.github.wimdeblauwe:error-handling-spring-boot-starter:5.1.1")
    implementation("com.bucket4j:bucket4j_jdk17-core:8.18.0")
    implementation("com.bucket4j:bucket4j_jdk17-lettuce:8.18.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    runtimeOnly("org.postgresql:postgresql")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> { useJUnitPlatform() }

spotless {
    encoding("UTF-8")

    kotlin {
        ktfmt("0.62").kotlinlangStyle().configure {
            it.setMaxWidth(100)
            it.setBlockIndent(4)
            it.setContinuationIndent(4)
            it.setRemoveUnusedImports(true)
        }
        endWithNewline()
        toggleOffOn()
    }

    kotlinGradle {
        target("*.gradle.kts")
        ktfmt().kotlinlangStyle()
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
