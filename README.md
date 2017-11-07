# ExcelJ [![](https://jitpack.io/v/erafaelmanuel/excelj.svg)](https://jitpack.io/#erafaelmanuel/excelj)
Excelj is a small framework for binding a java objects into a spreadsheet with adding annotation in a class. <br />
For more questions or suggestions please contact me at erafaelmanuel@gmail.com
<br />

<b><h1>Usage</h1></b>

Annotations
```
 @Sheet - add it to your class to make it available for binding.
 @Column - mark the instance variables as spreadsheet column 
```

<b>Example</b>

Dog.class
```js
 @Sheet
 class Dog {
     @Column(name="Dog Name")
     String name;
    
     ...
 }
```
Main.class
```js
 ...
 
 //Create a factory
 ExcelJ factory = new ExcelJ();
 
 //File "Dog.xlxs" will create on your project's root directory 
 //Note the class name is the default file name
 
 factory.save(new Dog("Kelvin Datu"));
 
 //Or save even a list
 
 factory.save(Arrays.asList(new Dog("Kelvin Datu"), new Dog("Ralen Mandap")));
```

<b><h1>Questions</h1></b>

* How to hide the logs
```js
  ...
  
  Initializer.hideLogs();
```

* How to change the file and directory of a sheet
```js
  ...
  
  //You can configure it on @Sheet
  @Sheet(name="[FILE_NAME]", dir="[DIRECTORY]")
```

<b><h1>Downloads</h1></b>

* Gradle

```js

//Add the following to your project level build.gradle:

allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

//Add this to your app build.gradle:

dependencies {
   ...
   compile 'com.github.erafaelmanuel:excelj:v1.0'
}
```

* Maven

```html
//Add the following to your pom.xml:

<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>

...

<dependency>
 <groupId>com.github.erafaelmanuel</groupId>
 <artifactId>excelj</artifactId>
 <version>v1.0</version>
</dependency>

```
