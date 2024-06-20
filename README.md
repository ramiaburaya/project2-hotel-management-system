# Hotel Management System

## Project Information

This project is a comprehensive Hotel Management System developed using Spring Boot. It provides functionalities for customer management, employee management, room management, reservation management, housekeeping management, billing, and user authentication and authorization. The system is designed to streamline hotel operations and enhance customer satisfaction.

### Resources

- **Customer Management**: Enables users to register, login, and manage their profiles (view, update, change password).
- **Employee Management**: Allows admins to manage hotel employees and staff.
- **Room Management**: Allows admins to manage room types, availability, and status.
- **Reservation Management**: Enables customers to book rooms, modify, and cancel reservations (cancellations require admin approval).
- **Housekeeping Management**: Allows admins to schedule and track housekeeping tasks and employees.
- **Billing**: Manages invoices for customer reservations.
- **User Authentication and Authorization**: Secures the system using JWT for authentication and role-based access control.

### Student Names

- Student 1: Rami Aburayya - 1201782

## ER Diagram

![ER Diagram](path/to/your/er-diagram.png)

## Building, Packaging, and Running the Application

### Building and Packaging

To build and package the application as a JAR file:

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/your-repo.git
    cd your-repo
    ```

2. Build the project using Maven:
    ```bash
    ./mvnw clean package
    ```

3. The generated JAR file will be located in the `target` directory.

### Running the Application

To run the application:

1. Navigate to the `target` directory:
    ```bash
    cd target
    ```

2. Run the JAR file:
    ```bash
    java -jar your-jar-file.jar
    ```

### Building and Running with Docker

To build and run the application as a Docker container:

1. Build the Docker image:
    ```bash
    docker build -t your-dockerhub-username/hotel-management-system .
    ```

2. Run the Docker container:
    ```bash
    docker run -p 8080:8080 your-dockerhub-username/hotel-management-system
    ```

### DockerHub

The Docker image is available on DockerHub: [DockerHub Link]([https://hub.docker.com/r/your-dockerhub-username/hotel-management-system](https://hub.docker.com/r/ramiaburaya/hotel-management-app))

## What We Have Learned

Through this project, we have gained valuable experience and knowledge in the following areas:

1. **Spring Boot Framework**: Understanding the core concepts and components of Spring Boot.
2. **RESTful API Development**: Designing and implementing RESTful web services.
3. **Database Design**: Creating an ER diagram and designing a relational database schema.
4. **JWT Authentication**: Implementing secure authentication and authorization using JWT.
5. **Role-Based Access Control**: Managing user roles and permissions within the system.
6. **Docker**: Containerizing the application and managing Docker images and containers.
7. **API Documentation**: Generating and using OpenAPI/Swagger for API documentation.
8. **Postman**: Creating and using Postman collections for API testing.

We hope this project serves as a solid foundation for further development and improvement in our understanding of web application development and software engineering principles.
