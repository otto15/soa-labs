import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.8.0"
}

group = "com.soa"
version = "0.0.1-SNAPSHOT"

val jaxwsSourceDir = layout.buildDirectory.dir("generated/sources/jaxws")

// Configurations
val jaxws by configurations.creating

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	jaxws("com.sun.xml.ws:jaxws-tools:3.0.0")
	jaxws("jakarta.xml.ws:jakarta.xml.ws-api:3.0.0")
	jaxws("jakarta.xml.bind:jakarta.xml.bind-api:3.0.0")
	jaxws("jakarta.activation:jakarta.activation-api:2.0.0")
	jaxws("com.sun.xml.ws:jaxws-rt:3.0.0")

}

// Task for wsimport
tasks.register("wsimport") {
	description = "Generate classes from wsdl using wsimport"

	doLast {
		project.mkdir(jaxwsSourceDir.get().asFile)
		ant.withGroovyBuilder {
			"taskdef"("name" to "wsimport",
				"classname" to "com.sun.tools.ws.ant.WsImport",
				"classpath" to configurations["jaxws"].asPath)
			"wsimport"(
				"keep" to true,
				"destdir" to jaxwsSourceDir.get().asFile,
				"extension" to "true",
				"verbose" to true,
				"wsdl" to "http://localhost:1616/services/products?wsdl",
				"xnocompile" to true,
				"package" to "com.example.consumingwebservice.wsdl"
			) {
				"xjcarg"("value" to "-XautoNameResolution")
			}
		}
	}
}

sourceSets {
	named("main") {
		java.srcDir(jaxwsSourceDir)
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

configure<SourceSetContainer> {
	named("main") {
		java.srcDir("src/main/kotlin")
	}
}

sourceSets {
	main {
		kotlin {
			srcDirs("${layout.buildDirectory.get().asFile}/generated/openapi/src/main/kotlin")
		}
	}
}

tasks.withType<KotlinCompile> {
	dependsOn(tasks.openApiGenerate)
}

openApiGenerate {
	generatorName.set("kotlin-spring")
	inputSpec.set("$projectDir/../../../contract/products-api/api.yaml")
	outputDir.set("${layout.buildDirectory.get().asFile}/generated/openapi")
	apiPackage.set("generated.soa.products.rest.adapter.controller")
	modelPackage.set("generated.soa.products.rest.adapter.dto")
	invokerPackage.set("generated.soa.products.rest.adapter")
	configOptions.set(
		mapOf(
			"useSpringBoot3" to "true",
			"dateLibrary" to "java8",
			"generateApis" to "true",
			"generateApiTests" to "false",
			"generateModels" to "true",
			"generateModelTests" to "false",
			"generateModelDocumentation" to "false",
			"generateSupportingFiles" to "false",
			"hideGenerationTimestamp" to "true",
			"interfaceOnly" to "true",
			"library" to "spring-boot",
			"serializableModel" to "true",
			"useBeanValidation" to "true",
			"useTags" to "true",
			"implicitHeaders" to "true",
			"openApiNullable" to "false",
		)
	)
}
