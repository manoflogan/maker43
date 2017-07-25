[![Build
Status](https://travis-ci.org/manoflogan/maker43.png)](https://travis-ci.org/manoflogan/maker43)

To play the poker game from the file, the players need to execute the game as follows. To run the
game, you need to have the following softwares installed on your system.

1. Java 8
2. Maven

The project assumes a directory structure as given below.
```
+-- target
|   +-- mark43-1.0-SNAPSHOT.jar
+-- tests
|   +-- 01
|   +-- 02
|   +-- 03
|   +-- 04
|   +--  ... <Many more files>
```
where ```tests``` directory contain the test files relative to the java archive path.

To execute and test the function, following steps are recommended.

1. Navigate to the root of the unarchived directory
2. Invoke the following command below

```
mvn clean install && java -jar target/mark43-1.0-SNAPSHOT.jar <relative path of the file with respect to the root>
```
In this archive, the sample tests are available in the ```tests``` directory relative to the 
archive. So the example becomes

```
mvn clean install
java -jar target/mark43-1.0-SNAPSHOT.jar tests
```




