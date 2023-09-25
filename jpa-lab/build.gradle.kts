plugins {
    id("java")
}

group = "jpa-basic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Hibernate
    implementation("org.hibernate:hibernate-entitymanager:5.3.10.Final")

    // MySQL JDBC 드라이버
    implementation("mysql:mysql-connector-java:8.0.27")

    implementation("org.slf4j:slf4j-log4j12:1.5.2")
    implementation("javax.xml.bind:jaxb-api:2.3.0")
}

tasks.test {
    useJUnitPlatform()
}