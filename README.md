# 1source-codegen-client-api-adapters
1Source Client API Adapter Library to help translate between JSON, Java Objects, and back.

## Requirements

Building the API client adapter library requires:
1. Java 17+
2. Maven/Gradle

## Installation

To install the API client adapter library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.os</groupId>
  <artifactId>1source-api-client-adapters</artifactId>
  <version>1.2.1.2</version>
  <scope>compile</scope>
</dependency>
```

Also, to use the GitHub Packages repository for downloading SNAPSHOT artifacts, enable SNAPSHOTS in the POM of the consuming project or your ~/.m2/settings.xml file. Replace USERNAME with your GitHub username, and TOKEN with your personal access token that has read:packages permission.

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/Equilend/1source-codegen-client-api-adapters</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.os:1source-api-client:1.2.1.2"
```

Add the repository to your build.gradle file (Gradle Groovy). Replace USERNAME with your GitHub username, and TOKEN with your personal access token that has read:packages permission.

```repositories {
    maven {
        url = uri("https://maven.pkg.github.com/Equilend/1source-codegen-client-api-adapters")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
   }
}
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/1source-api-client-adapters-1.2.1.2.jar`
* `target/lib/*.jar`

