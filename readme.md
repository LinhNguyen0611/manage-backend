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

### Select your favorite IDE

- [IntelliJ Community version ](https://www.jetbrains.com/idea/download/#section=windows)
- [Visual Code](https://code.visualstudio.com/Download)

### Gear up the database

In this project, we will be using MySQL for database queries. Here are steps to get it up working

- Download __MySQL Community Server__ from [here](https://dev.mysql.com/downloads/windows/installer/5.7.html)
- Then, download __MySQL Workbench__ [here](https://dev.mysql.com/downloads/workbench/)

After downloading and installing above programs, you will go through some simple set-ups.
1. Open __MySQL Community Installer__ and add __MySQL Server__ (if not installed yet)
2. Go thru some configuration process (remember the password)
3. Open Workbench and create a new connection
4. Put  `root` in the user name and enter password that you created earlier.
5. Press `Test connection`. You will get the dialog confirming your success.  

 Make sure your database connect is working properly, or else, try the following "rememdies"

- Go the `Services Tab` (found in Task Manager or simply search for it), find MySQL service and hit `start`
- Try the [following](https://stackoverflow.com/questions/25777943/failed-to-connect-to-mysql-at-127-0-0-13306-with-user-root-access-denied-for-us) link if the problem is not resovled

#### Create a new database

In this step, we will create a new database that we will use later. In MySQL, the word `database` and `scheme` are interchangeable. 

Create new MySQL schema with this SQL command:
```sql
CREATE SCHEMA `[name-of-the-scheme]` DEFAULT CHARACTER SET utf8 ;
```

For our project, we will create a `mobile-store` database. so
```sql
CREATE SCHEMA `mobile-store` DEFAULT CHARACTER SET utf8 ;
```

#### Change connection setting

1. Go back to your project folder and open up the **application-env** file, found in: `your-project-dir/resources/` folder. (or create one if you haven't found any)
2. Then add the following properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mobile-store
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

