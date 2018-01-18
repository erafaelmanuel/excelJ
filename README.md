# ExcelJ [![](https://jitpack.io/v/erafaelmanuel/excelj.svg)](https://jitpack.io/#erafaelmanuel/excelj)
Excelj is a very small library for binding a java object into a spreadsheet using java reflection <br />
For more questions or suggestions please contact me at erafaelmanuel@gmail.com
<br />

## Usage

```java
 @Sheet
 public class Dog {
     @Column(name="Dog Name")
     String name;
     ...
 }
```

```java
 ExcelJ factory = new ExcelJ();
```
To save an object
```java
 factory.save(new Dog("Kelvin Datu"));
```
To save a list of objects
```java
 factory.save(Arrays.asList(new Dog("Kelvin Datu"), new Dog("Ralen Mandap")));
```
Binding a spreadsheet to a java object
```java
 //get dog at row [n=1]
 Dog dog = factory.get(Dog.class, n);
```
```java
 //get all dogs
 List<Dog> dogs = factory.load(Dog.class);
```

## FAQs

How to hide the logs
```js
  Initializer.hideLogs();
```
How to change the file name and directory of a sheet
```js
  //add the following
  @Sheet(name="[FILE_NAME]", dir="[DIRECTORY]")
```

<b><h1>Downloads</h1></b>

Download the latest jar [here](https://jitpack.io/#erafaelmanuel/excelj) or via:

* Gradle

```js
allprojects {
  repositories {

    maven { url 'https://jitpack.io' }
  }
}
```

```js
dependencies {
   compile 'com.github.erafaelmanuel:excelj:v1.1.0'
}
```

* Maven

```html
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

```html
<dependencies>
  <dependency>
    <groupId>com.github.erafaelmanuel</groupId>
    <artifactId>excelj</artifactId>
    <version>v1.1.0</version>
  </dependency>
</dependencies>
```

<b><h1>License</h1></b>

```
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
