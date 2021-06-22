# Cash Backend

**_Made by Federico Markus `federicomarkus@gmail.com`_** 

## Git

```bash
git clone https://github.com/federicomarkus/cash-backend.git
```

## Requirements


1. Java: 11

2. Maven: 3.8.1

3. MySQL: 5.1.6

## Database - MySQL 

**Change username and password on** `src/main/resources/application.properties`

+ Username: `spring.datasource.username`
+ Password: `spring.datasource.password`

## Run

+ ### **From IDE (preferably IntelliJ)**

Execute the main method in the `src/main/java/com/cash/app/CashApplication.java` class from your IDE.

+ ### **From Command Line (Windows)**

```bash
mvn package
java -jar cash-app\target\cash-app-1.0.0.jar
```
The app will start running at http://localhost:8080.