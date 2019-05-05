##A simple blog for learning purpose.
##Installation

Requirements:

- Java 1.8.
- Maven.
- MySQL (only for local use)
- Git
 > The database is already configured in **AWS**
- Local server Apache Tomcat 8.0.



#

1. Clone the project (git clone https://github.com/azaelespinosa/blog.git).
2. Import maven dependencies. 
3. Configure project SDK.
2. Run as Maven project (mvn spring-boot:run).

> If you require the DB in local mode, set this property as create **spring.jpa.hibernate.ddl-auto=none**



```mermaid
The project have Swagger at port 8081
```