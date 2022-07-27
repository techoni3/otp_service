# OTP Service

## Table of Contents:

* [About The Project](#about-the-project)
    * [Entities](#entities)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Steps To Run](#steps-to-run)
        * [Manual Setup](#manual-setup)
        * [Docker Setup](#docker-setup-recommended)
* [Configuration](#configuration)
* [Contact](#contact)

## About The Project:

### Entities:

## Getting Started:

Instructions on how to setup the project, its requirements and things to note during development:

### Prerequisites:

- Java 18
- Maven 3.8+
- Postgresql 14+
- Redis 6+

### Steps to run:

- #### Manual Setup:
    - Install Java and Maven locally
    - Install Postgres and Redis locally on default ports
    - DB Setup: Create database and DB user(The default configuration can be changed from `application.properties`)
        - DATABASE: resync
        - USER:postgres
        - PASS:
    - Open terminal
    - `cd` into the project directory
    - Run the following commands:
      ```
        #Builds and packages the app into a jar 
        mvn clean package
      
        #Runs the jar application
        java -jar target/auth-0.0.1-SNAPSHOT.jar
      ```

- #### Docker Setup (*Recommended*):
    - Setup Pending

### API Spec:

- Import Postman Collection (file: `OTPService.postman_collection.json`)

### Configuration:

- Configuration paramters can be changed from `application.properties`

## Pending Improvements:

- Implement test cases for all the testing scenarios defined
  in `src/test/java/com/resync/auth/services/LoginServiceImplTest.java`
- Implement Global Exception Handler for better error messaging
- Add logging
- Setup Swagger configuration beans (Dependency already added)
- Improve docker caching to avoid downloading of dependencies on each run
- Extract the error message strings out from the methods into a message constants file.
- Add rate limiting mechanism to avoid brute force attacks
- Dockerize the application
- Add Flyway support for migration(DDL) history and tracking
- Allow dynamic switching of otp generation strategies

### Contact

[@Nitin Sachdev](https://www.linkedin.com/in/nitin-sachdev) - nitin_sachdev@outlook.com
