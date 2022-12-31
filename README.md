# Micronaut Demonstration Project
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a demonstration API project using Micronaut Framework.

## About
Micronaut is a full-stack framework for building microservice and serverless applications. It leverages the use of
compile-time inversion of control that results to quick start-up and low memory footprint.

This project demonstrates the CRUD operation using the framework and some Unit testing.

### Micronaut Main Entry Class
```java
public class Application {

    public static void main(String[] args) {
        Micronaut.build(args)
                .mainClass(Application.class)
                .start();
    }
}
```
## How to run
### Fork the project and run locally using the commands.

Build the jar
```
gradlew shadowJar
```
Run the jar from the libs folder
```
java -jar micronaut-java-0.1-all.jar 
```

Run using Gradle. Optionally, you can put <i>-t</i> option for live code reload.
```
gradlew run -t
```

### Docker Desktop
##### Run the application using docker

Pull the image from Docker Hub
```
docker pull rjtmahinay/micronaut-java
```

Run the container using the image
```
docker run -d -p <port>:8080 --name <your container name> rjtmahinay/micronaut-java
```

## Swagger via OpenAPI

- Access the swagger documentation via local environment
```
http://localhost:<port>/swagger-ui
```

## APIs

### Synchronous
#### Create
```
/v1/synchronous/employee/add
```
#### Read
Get all employee
```
/v1/synchronous/employee
```
Get a specific employee
```
/v1/synchronous/employee/{id}
```
#### Update
```
/v1/synchronous/employee/update/{id}
```
#### Delete
```
/v1/synchronous/employee/delete/{id}
```
#### Exception
Demonstrates the returned response for an exception
```
/v1/synchronous/employee/exception
```

### Reactive
#### Create
```
/v1/reactive/employee/add
```
#### Read
Get all employee
```
/v1/reactive/employee
```
Get a specific employee
```
/v1/reactive/employee/{id}
```
#### Update
```
/v1/reactive/employee/update/{id}
```
#### Delete
```
/v1/reactive/employee/delete/{id}
```
#### Exception
Demonstrates the returned response for an exception
```
/v1/reactive/employee/exception
```

## Database
H2 database is used for this demonstration project.

## License
The project is licensed using MIT License

## Micronaut Documentations

- [User Guide](https://docs.micronaut.io/3.7.5/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.7.5/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.7.5/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)