buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0") // Asegúrate de que la versión de Gradle sea la correcta
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Agregar esta línea para el plugin de Kotlin
        classpath("com.google.gms:google-services:4.3.15")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}
