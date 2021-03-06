## A simple blog for learning purpose.

## Technologies used here.
- Spring Boot
- Jpa
- Thymeleaf
- Markdown
- Maven
- AWS MySql
- Junit / Spring Test / Mockito
- Restful
- Git
- Java 8
- Swagger
- Spring Boot AOP

## Features
- CRUD Post, Roles, Comments & Users
- Swagger


## Installation

Requirements:

- Java 1.8.
- Maven.
- MySQL (only for local use)
 > The database is already configured in **AWS**
- Local server Apache Tomcat 8.0.



#

1. Clone the project (git clone https://github.com/azaelespinosa/blog.git).
2. Import maven dependencies. 
3. Configure project SDK.
2. Run as Maven project (mvn spring-boot:run).
4. Spring security |**user**=user, **password**=password|

> If you require the DB in local mode, set this property as create **spring.jpa.hibernate.ddl-auto=none**



```mermaid
Swagger -> http://localhost:8081/swagger-ui.html#/
```

```mermaid
A[Create a Role]
B[Create a New User]
C[Create a Post]
D[Create a Comment]
```