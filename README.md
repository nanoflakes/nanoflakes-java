# Nanoflakes - Java

Reference implementation of [nanoflakes](https://github.com/nanoflakes/nanoflakes) for Java.

Licensed under the [MIT License](https://github.com/nanoflakes/nanoflakes-java/blob/master/LICENSE).

### Installation

![Latest Version](https://api.bintray.com/packages/arudiscord/maven/snowflake/images/download.svg)

Using in Gradle:

```gradle
repositories {
  jcenter()
}

dependencies {
  compile 'com.github.nanoflakes:nanoflakes-java:LATEST' // replace LATEST with the version above
}
```

Using in Maven:

```xml
<repositories>
  <repository>
    <id>central</id>
    <name>bintray</name>
    <url>http://jcenter.bintray.com</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.nanoflakes</groupId>
    <artifactId>nanoflakes-java</artifactId>
    <version>LATEST</version> <!-- replace LATEST with the version above -->
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
