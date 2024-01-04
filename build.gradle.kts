plugins {
    kotlin("jvm") version "1.9.21"
    id("kotlinx-serialization")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    `maven-publish`
    signing
}

group = "com.zhufucdev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core-jvm:2.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

nexusPublishing {
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

val publicationName = "maven"

publishing {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>(publicationName) {
                from(components["java"])

                pom {
                    name = "${project.group}:${project.name}"
                    description = "Steve's SDK, Kotlin implementation"
                    url = "https://github.com/zhufucdev/sdk"
                    licenses {
                        license {
                            name = "Apache 2.0"
                            url = "https://www.apache.org/licenses/LICENSE-2.0"
                        }
                    }
                    developers {
                        developer {
                            id = "zhufucdev"
                            name = "Steve Reed"
                            email = "29772292+zhufucdev@users.noreply.github.com"
                            organization = "zhufucdev"
                            organizationUrl = "https://zhufucdev.com"
                        }
                    }
                    scm {
                        connection = "scm:git@github.com:zhufucdev/sdk.git"
                        developerConnection = "scm:git@github.com:zhufucdev/sdk.git"
                        url = "https://github.com/zhufucdev/sdk"
                    }
                }
            }
        }
    }
}

signing {
    sign(publicationName)
}