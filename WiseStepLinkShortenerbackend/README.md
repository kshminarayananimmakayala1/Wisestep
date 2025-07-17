# Wisestep link shortener backend

A simple and efficient URL shortener service built using **Spring Boot** and **PostgreSQL**. It generates short URLs that redirect to original links and expire after a defined time period.

---

### Tech Stack

- **Backend:** Spring Boot
- **Database:** PostgreSQL
- **Frontend (optional):** ReactJS
- **Build Tool:** Maven
- **ORM:** Spring Data JPA (Hibernate)

---
### application-local.properties

# PostgreSQL Config
-spring.datasource.url=jdbc:postgresql://localhost:5432/shortenerdb
-spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Port
server.port=8081
-### Clone the repository
git clone https://github.com/kshminarayananimmakayala1/Wisestep/tree/main/WiseStepLinkShortenerbackend
cd wisestep-link-shortener
---

-### Set up PostgreSQL Database
-- CREATE DATABASE shortenerdb;


-### Build and Run the Application
# Using Maven
-- ./mvnw clean package
-- java -jar target/shortener-0.0.1-SNAPSHOT.jar


