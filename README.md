# JavaSound ResourceLoader SPI
This library provides an extended ServiceProvider Interface for JavaSound to load audio from resources.

## Add the library to your project (gradle)
1. Add the Maven Central repository (if not exist) to your build file:
```groovy
repositories {
    ...
    mavenCentral()
}
```

2. Add the dependency:
```groovy
dependencies {
    ...
    implementation 'com.tianscar.javasound:javasound-res-spi:0.1.0'
}
```

## Usage
[JavaDoc](https://docs.tianscar.com/javasound-res-spi)  
[Tests](/src/test/java/com/tianscar/javasound/resspi/test)

Note you need to download test audios [here](https://github.com/Tianscar/fbodemo1) and put them to /src/test/resources to run the test code properly!

## License
[MIT](/LICENSE)
