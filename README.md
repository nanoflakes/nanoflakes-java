# Nanoflakes - Java

[![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fmaven.cafeteria.dev%2Freleases%2Fcom%2Fgithub%2Fnanoflakes%2Fnanoflakes-java%2Fmaven-metadata.xml)](https://maven.cafeteria.dev/releases/com/github/nanoflakes/nanoflakes-java)
[![GitHub issues](https://img.shields.io/github/issues/nanoflakes/nanoflakes-java)](https://github.com/nanoflakes/nanoflakes-java/issues)
[![License](https://img.shields.io/github/license/nanoflakes/nanoflakes-java)](https://github.com/nanoflakes/nanoflakes-java/tree/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Fgithub.com%2Fnanoflakes%2Fnanoflakes-java)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2Fnanoflakes%2Fnanoflakes-java)

Reference implementation of [nanoflakes](https://github.com/nanoflakes/nanoflakes) for Java.

Licensed under the [MIT License](https://github.com/nanoflakes/nanoflakes-java/blob/master/LICENSE).

### Installation

Using in Gradle:

```gradle
repositories {
  maven { url = 'https://maven.cafeteria.dev' }
}

dependencies {
  implementation 'com.github.nanoflakes:nanoflakes-java:VERSION'
}
```

### Env variables required to build
mcdUsername and mcdPassword are needed for maven repository authentication. They are stored in gradle.properties

Using in Maven:

```xml
<repositories>
    <repository>
        <id>cafeteria</id>
        <name>cafeteria</name>
        <url>https://maven.cafeteria.dev</url>
    </repository>
</repositories>

<dependencies>
<dependency>
    <groupId>com.github.nanoflakes</groupId>
    <artifactId>nanoflakes-java</artifactId>
    <version>VERSION</version>
</dependency>
</dependencies>
```

### Usage

- The `Ǹanoflakes` class contains utility methods for handling with nanoflakes.
- Use `Nanoflakes.localGenerator(epoch, generatorId)` to create a local nanoflake generator.
    - You can get an epoch by calling `System.currentTimeMillis()` in a Java shell (`jjs`, `jshell`, etc).
    - A generator ID must be in the 0-1023 range.
- Use `NanoflakeGenerator.next()` to get a new nanoflake.
- The `Nanoflake` class is the result type `NanoflakeGenerator.next()`. It can be used as-is, or getting it's raw or encoded value. It also features utility methods such as getting the creation time of the nanoflake.

### Support

Extra support is given on [Aru's Discord Server](https://discord.gg/URPghxg).

[![Aru's Discord Server](https://discordapp.com/api/guilds/403934661627215882/embed.png?style=banner2)](https://discord.gg/URPghxg)
