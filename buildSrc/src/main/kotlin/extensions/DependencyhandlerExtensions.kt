package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.Dependencies

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.runtimeOnly(dependencyNotation: String): Dependency? =
    add("runtimeOnly", dependencyNotation)

fun DependencyHandler.runtimeOnly(dependencyNotation: Any): Dependency? =
    add("runtimeOnly", dependencyNotation)

fun DependencyHandler.developmentOnly(dependencyNotation: String): Dependency? =
    add("developmentOnly", dependencyNotation)

fun DependencyHandler.developmentOnly(dependencyNotation: Any): Dependency? =
    add("developmentOnly", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: String): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: Any): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.addAllStarters() {
    implementation(Dependencies.SPRING_BOOT_WEB)
    implementation(Dependencies.SPRING_BOOT_JPA)
    implementation(Dependencies.SPRING_BOOT_REST)
    developmentOnly(Dependencies.SPRING_BOOT_DEV_TOOLS)
    annotationProcessor(Dependencies.SPRING_BOOT_CONFIG_PROCESSOR)
    testImplementation(Dependencies.SPRING_BOOT_TEST)
}

fun DependencyHandler.addKotlinDependencies() {
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.KOTLIN_STD)
}

fun DependencyHandler.addJackson() {
    implementation(Dependencies.JACKSON)
}

fun DependencyHandler.addHelpers() {
    implementation(Dependencies.LOMBOK)
    annotationProcessor(Dependencies.LOMBOK)
}

fun DependencyHandler.addDatabases() {
    runtimeOnly(Dependencies.H2_DATA_BASE)
    runtimeOnly(Dependencies.POSTGRE_SQL_DATA_BASE)
}