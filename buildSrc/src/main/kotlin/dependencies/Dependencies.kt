package dependencies

object Dependencies {

    // Starters
    const val SPRING_BOOT_WEB = "org.springframework.boot:spring-boot-starter-web"
    const val SPRING_BOOT_REST = "org.springframework.boot:spring-boot-starter-data-rest"
    const val SPRING_BOOT_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val SPRING_BOOT_CONFIG_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"
    const val SPRING_BOOT_TEST = "org.springframework.boot:spring-boot-starter-test"

    // JSON Converters
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // Kotlin
    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"

    // Helpers
    const val LOMBOK = "org.projectlombok:lombok"

    // Dev tools
    const val SPRING_BOOT_DEV_TOOLS = "org.springframework.boot:spring-boot-devtools"

    // Data bases
    const val H2_DATA_BASE = "com.h2database:h2"
    const val POSTGRE_SQL_DATA_BASE = "org.postgresql:postgresql"

}