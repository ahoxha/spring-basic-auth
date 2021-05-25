# Basic Authentication using SpringSecurity in a SpringBoot app

This is a simple example that shows how to secure a SpringBoot app via 
SpringSecurity using basic authentication.

*Note*: **The username and password are hardcoded for the sake of simplicity. Don't do this in a production app.**

### Building the app
`CD` into the project directory and type:
```
mvn clean install
```

### Running the app
#### On an IDE
Run the `SpringBootBasicAuthApplication` class.

#### Via maven plugin
`CD` into the project directory and type:
```
mvn spring-boot:run
```

#### As a `jar` file
First, build the app, then type:
```
java -jar target/spring-basic-auth-0.0.1-SNAPSHOT.jar
```
