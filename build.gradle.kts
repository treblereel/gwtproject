import java.time.Year

plugins {
    id("java-library")
    id("maven")
    id("net.ltgt.errorprone-javacplugin") version "0.5"
    id("com.github.sherter.google-java-format") version "0.6"
    id("com.github.hierynomus.license") version "0.14.0"
    id("local.ktlint")
    id("local.maven-publish")
}

group = "org.gwtproject.user.history"
version = "HEAD-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.3.1")
    errorproneJavac("com.google.errorprone:javac:9+181-r4173-1")

    api("org.gwtproject.event:gwt-logical-event:HEAD-SNAPSHOT")
    implementation("org.gwtproject.user.window:gwt-window:HEAD-SNAPSHOT")
    implementation("com.google.elemental2:elemental2-dom:1.0.0-RC1")
    implementation("com.google.elemental2:elemental2-core:1.0.0-RC1")

    testImplementation("junit:junit:4.12")
    testImplementation("com.google.gwt:gwt-user:2.8.2")
    testImplementation("com.google.gwt:gwt-dev:2.8.2")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(arrayOf("-Werror", "-Xlint:all"))
}

tasks {
    "jar"(Jar::class) {
        from(java.sourceSets["main"].allJava)
    }

    "test"(Test::class) {
        val warDir = file("$buildDir/gwt/www-test")
        val workDir = file("$buildDir/gwt/work")
        val cacheDir = file("$buildDir/gwt/cache")
        doFirst {
            mkdir(warDir)
            mkdir(workDir)
            mkdir(cacheDir)
        }

        classpath += java.sourceSets["main"].allJava.sourceDirectories + java.sourceSets["test"].allJava.sourceDirectories
        include("**/*Suite.class")
        systemProperty(
            "gwt.args", "-ea -draftCompile -batch module -war \"$warDir\" -workDir \"$workDir\" " +
                "-runStyle ${project.findProperty("test.gwt.runStyle") ?: "HtmlUnit:Chrome"}"
        )
        systemProperty("gwt.persistentunitcachedir", cacheDir)
        testLogging {
            events("STANDARD_OUT")
        }
    }

    "validateGwtModule"(JavaExec::class) {
        val workDir = file("$buildDir/gwt/work")
        val cacheDir = file("$buildDir/gwt/cache")
        val outFile = file("$buildDir/gwt/validateGwtModule")
        doFirst {
            mkdir(workDir)
            mkdir(cacheDir)

            standardOutput = outFile.outputStream()
            errorOutput = standardOutput
        }
        doLast {
            standardOutput.close()
        }

        inputs.files(java.sourceSets["main"].allJava)
        outputs.file(outFile)

        main = "com.google.gwt.dev.Compiler"
        classpath = java.sourceSets["test"].runtimeClasspath + java.sourceSets["main"].allJava.sourceDirectories
        args("-strict", "-validateOnly", "-workDir", workDir, "org.gwtproject.user.history.History")
        systemProperty("gwt.persistentunitcachedir", cacheDir)
    }
    "check" { dependsOn("validateGwtModule") }

    "javadoc"(Javadoc::class) {
        (options as CoreJavadocOptions).apply {
            addBooleanOption("Xdoclint:all,-missing", true)
            // Workaround for https://github.com/gradle/gradle/issues/5630
            addStringOption("sourcepath", "")
        }
    }
}

googleJavaFormat {
    toolVersion = "1.6"
}

license {
    header = rootProject.file("LICENSE.header")
    encoding = "UTF-8"
    skipExistingHeaders = true
    mapping("java", "SLASHSTAR_STYLE")

    (this as ExtensionAware).extra["year"] = Year.now()
    (this as ExtensionAware).extra["name"] = "The GWT Project Authors"
}
