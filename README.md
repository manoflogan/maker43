To play the poker game from the file, the players need to execute the game as follows. To run the
game, you need to have the following softwares installed on your system

* Java 8
* Maven

The project assumes a directory structure as given below.

+-- target
|   +-- mark43-1.0-SNAPSHOT.jar
+-- tests
|   +-- 01
|   +-- 02
|   +-- 03
|   +-- 04
|   +--  ... <Many more files>

The invocation command is
```
mvn clean install && java -jar target/mark43-1.0-SNAPSHOT.jar <relative path of the file with respect to the root>
```





