# InsuranceAPI

A simple CRUD application.

## Requirements

- Need Java 21
- Need Maven < 3.9

## Swagger:

You can access the API documentation locally on the following link:
http://localhost:8080/swagger-ui/index.html

## Build the project

Build the project with the following command:

```sh
$ mvn clean install
```

## Docker

Build the Docker Image:

```sh
$ docker build -t insurance-api-application .
```

Run the Docker Image:

```sh
$ docker run -p 8080:8080 insurance-api-application
```
