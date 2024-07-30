# Spring Boot JWT Authentication and Authorization

## Overview

This project is a Spring Boot application that implements JWT (JSON Web Tokens) for secure authentication and authorization. It uses Spring Security 5 to manage user authentication and protect API endpoints. JWTs are used to securely transmit user identity and authorization information between parties.

## Features

- **JWT Authentication**: Secure authentication using JSON Web Tokens.
- **Spring Security Integration**: Utilizes Spring Security 5 for managing authentication and authorization.
- **Custom Security Filters**: Includes custom filters for token validation and authentication.
- **Exception Handling**: Handles authentication and authorization errors gracefully.

## Prerequisites

Before running this project, ensure you have the following installed:

- **JDK 17 or later**
- **Maven** or **Gradle**
- An IDE such as **IntelliJ IDEA** or **Eclipse**

## Getting Started

1. **Clone the Repository:**
   
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
2. **Configure Application Properties:**
 - Open src/main/resources/application.properties or application.yml.
 - Set your JWT secret key and other related properties.

2. **Build the Project:**
 - Use Maven to build the project `mvn clean install`.
   
2. **Run the Application:**
 - Use Maven to run the application `mvn spring-boot:run`.

## Configuration

### JWT Properties

- **`jwt.secret`**: The secret key used to sign JWTs. Ensure this is a strong, secure key.
- **`jwt.expiration`**: The expiration time for the JWT in milliseconds.
- **`jwt.token-prefix`**: The prefix for the JWT in the Authorization header (typically "Bearer").
- **`jwt.header-string`**: The name of the HTTP header where the token is sent (usually "Authorization").

### Security Configuration

- **Security Configuration**: Configures HTTP security to use JWT for authentication. This includes setting up custom filters and specifying which endpoints require authentication.

## Endpoints

- **Authentication Endpoint**: `/auth/login` - Use this endpoint to obtain a JWT by providing valid credentials.
- **Protected Endpoints**: All other endpoints are secured and require a valid JWT to access. Include the token in the Authorization header with the prefix `Bearer`.
