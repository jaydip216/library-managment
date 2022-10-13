## Library Management System

#### [API DOCUMENTATION](https://documenter.getpostman.com/view/10132051/2s83zmKgj2)

#### TechStack: Java, Spring boot, JPA, Spring Security, PostgreSQL(AWS RDS Server), TestNG

##### Spring dependency used in this project:
* spring-boot-starter-security: Used to apply security to the application
* spring-security-config: Provides role based security to the application
* spring-boot-starter-web: Used to implement REST APIs
* spring-boot-starter-data-jpa: Used to query database
* org.postgresql: PostgreSQL driver
* jjwt: JWT library which we use to generate and verify JWT tokens
* spring-boot-starter-validation: used to validate values of a JavaBean’s fields which are JSON values in the request.
* testng, mockito-core: used to write test cases

##### How To Run this service:
* Clone this repo and import it in your IDE
* Run it as a springboot application
* No need to change any database config as this service is using public AWS RDS
* Service will start on port 8080. Use API documentation given above to test the APIs
