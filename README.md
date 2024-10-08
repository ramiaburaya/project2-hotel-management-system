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

- Student : Rami Aburayya - 1201782

## ER Diagram

![ER Diagram](ERD.png)

## Building, Packaging, and Running the Application

### Building and Packaging

To build and package the application as a JAR file:

1. Clone the repository:
    ```bash
    git clone https://github.com/ramiaburaya/project2-hotel-management-system.git
    cd project2-hotel-management-system
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
    java -jar Hotel_1-0.0.1-SNAPSHOT.jar
    ```

### Building and Running with Docker

To build and run the application as a Docker container:

**Note**: The application depends on a MySQL database, so it must be run using Docker Compose to ensure the necessary dependencies are available.

1. Build the Docker image:
    ```bash
    docker build -t ramiaburaya/hotel-management-app .
    ```

2. Run the Docker container:
    ```bash
    docker run -p 8080:8080 ramiaburaya/hotel-management-app
    ```

### Using Docker Compose

To build and run the application along with MySQL using Docker Compose:

1. Create a `docker-compose.yml` file with the following content:

    ```yaml
    services:
      mysql:
        image: 'mysql:8'
        container_name: 'hotelManG-mysql'
        environment:
          - 'MYSQL_ROOT_PASSWORD=root'
          - 'MYSQL_DATABASE=hotel_management'
          - 'MYSQL_PASSWORD=root'
        ports:
          - '127.0.0.1:3307:3306'
        volumes:
          - 'D:\Mysql\HotelManegment\docker\Mysql:/var/lib/mysql'
        healthcheck:
          test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
          interval: '30s'
          timeout: '10s'
          retries: 5
      app:
        image: ramiaburaya/hotel-management-app:latest
        container_name: hotel_management_app
        depends_on:
          - mysql
        environment:
          SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/hotel_management
          SPRING_DATASOURCE_USERNAME: root
          SPRING_DATASOURCE_PASSWORD: root
        ports:
          - '8080:8080'
    ```

2. Run Docker Compose:
    ```bash
    docker-compose up
    ```


### DockerHub

The Docker image is available on DockerHub: [DockerHub Link](https://hub.docker.com/r/ramiaburaya/hotel-management-app)

### Swagger API Documentation

he API documentation is available on SwaggerHub: [SwaggerHub Link](https://app.swaggerhub.com/apis-docs/RAMIABURAYA16/Hotel_Management_API/1.0.0)

## What We Have Learned

Through this project, we have gained valuable experience and knowledge in the following areas:

1. **JWT Authentication**: Implementing secure authentication and authorization using JWT.
2. **Role-Based Access Control**: Managing user roles and permissions within the system.
3. **Docker**: Containerizing the application and managing Docker images and containers.
4. **API Documentation**: Generating and using OpenAPI/Swagger for API documentation.
5. **Postman**: Creating and using Postman collections for API testing.


I hope this project serves as a solid foundation for further development and improvement in our understanding of web application development and software engineering principles.
