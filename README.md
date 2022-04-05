# How to run Unit tests
```
mvn tests
```
# How to run Integration tests
```
mvn failsafe:integration-test
```
# How to run both Unit and Integration tests (does not work currently, works in intellij)
```
mvn verify
```
# How build and run the maven program (does not work currently, works in intellij)
```
mvn package
java -cp target/Prison_Escape-1.0-SNAPSHOT.jar com.group10.app.App
```
