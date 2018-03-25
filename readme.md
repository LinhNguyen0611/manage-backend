# Project name

Project description

## Things That May Make You Twitch

### Environment

The environment is set up at `src/main/resources`

`application.properties`: Common environment. The variable `spring.profiles.active` is for selecting the environment

**Available environments:**

- `local`: Local development environment
- `ci`: CI (continuous integration) development environment

## Setting Things Up

### Create db

This project is using MySQL for db

Create new MySQL schema with this SQL command:
```sql
CREATE SCHEMA `new_schema` DEFAULT CHARACTER SET utf8 ;
```

### Change connection setting

File: `resources/application-env.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_db
spring.datasource.username=root
spring.datasource.password=root
```

### Running Tasks

To get a list of available tasks for a project, run `./gradlew tasks`. 

Build project: `./gradlew build`

Prepare the war package: `./gradlew assemble`

### Start server

To start a server, run the command:

```bash
$ ./gradlew bootRun
```

### Getting API documentation

Access API-doc with this URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Coding guideline

<b>1. Create entity</b>

Create Entity class in `entities` package, extends `BaseEntity`

<b>2. Create repository</b>

Create Repository interface in `repositories` package, extends `JpaRepository<{entity class}, {entity primary key}>`

This repository include CRUD based method

You can create custom sql query in this interface. Reference: [JPA Repositories - Spring](https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html)

<b>3. Create service</b>

Create Service class in `services` package, extends `BaseService<{repository interface}, {entity class}, {entity primary key}>`

This service included basic CRUD method, included get paging list

All business, transaction & error handle is written in this class

<b>4. Create data class</b>

Input class: XXXModels in `models` package. This class include validation annotations, and can convert to entity class

Output class: XXXResponse in `responses` package. This class included formatted output, and can convert from entity class

<b>5. Create controller class</b>

Create Controller class in `controllers` package.

Input of controller method is Model class, output is `ResponseModel<{response class}>`

Basic operations:
```
POST /XXX/add: Insert new item
PUT (or POST) /XXX/update/{id}: Edit item by id
GET /XXX/get/{id}: Get item by id
DELETE (or POST) /XXX/delete/{id}: Delete item by id
GET /XXX/list/{size}/{page}: Get list paging of all items
```

