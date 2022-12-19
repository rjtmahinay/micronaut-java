# Micronaut Demonstration Project
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a demonstration API project using Micronaut Framework.
## Micronaut Documentations

- [User Guide](https://docs.micronaut.io/3.7.4/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.7.4/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.7.4/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

## How to run
Fork the project and run locally using the command. Optionally, you can the <i>-t</i> option for live code reload.
```
gradlew run -t
```

## Swagger via OpenAPI

- Access the swagger documentation via local environment
```
http://localhost:<port>/swagger-ui
```

## APIs

### Create
```
/v1/employee/add
```
### Read
Get all employee
```
/v1/employee
```
Get a specific employee
```
/v1/employee/{id}
```
### Update
```
/v1/employee/update/{id}
```
### Delete
```
/v1/employee/delete/{id}
```

## Database
H2 database is used for this demonstration project.

## Deployment to cloud
TODO