# How to run Unit tests
```
mvn tests
```
# How to run Integration tests
```
mvn failsafe:integration-test
```
# How to run both Unit and Integration tests
```
mvn verify
```
# How build and run the maven program
```
mvn package
mvn exec:java -Dexec.mainClass="com.group10.app.App"
```
# How to create Javadocs HMTL files
```
mvn javadoc:javadoc

The Javadocs are found in `${basedir}/target/site/apidocs/` if the above command is executed
```
# link to video

<https://youtu.be/Dx4wshp9L6U>

