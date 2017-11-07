# excelj
Excelj is a small framework for binding a java objects into a spreadsheet with adding annotation in a class.

<b><h1>About</h1></b>

<b><h1>Usage</h1></b>
<b>Gradle dependency:</b>

Add the following to your project level build.gradle:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add this to your app build.gradle:

```
dependencies {
	compile 'com.github.erafaelmanuel:jexcel:596f8f7e5d'
}
```

<b>Maven:</b>

Add the following to the <repositories> section of your pom.xml:

```
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Add the following to the <dependencies> section of your pom.xml:

```
<dependency>
    <groupId>com.github.erafaelmanuel</groupId>
    <artifactId>jexcel</artifactId>
    <version>596f8f7e5d</version>
</dependency>
```
