#  Product Service - E-Commerce Microservices

A production-ready **Product Service** built using **Spring Boot**, responsible for managing products, categories, and pricing in an e-commerce system.

This service is part of a larger * microservices-based architecture *, designed with scalability and clean architecture principles.

##  Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL (or any relational DB)
- Maven
- REST APIs
- Global Exception Handling (`@ControllerAdvice`)

##  Architecture Overview

This service follows a layered architecture:

Controller Layer → Service Layer → Repository Layer → Database

- **Controller Layer** → Handles incoming HTTP requests
- **Service Layer** → Contains business logic
- **Repository Layer** → Interacts with the database using JPA
- **Exception Layer** → Centralized exception handling

##  Features (Current - Production Ready)

###  Product Management
- Create Product
- Get Product by ID
- Get All Products
- Update Product
- Delete Product

---

###  Category Management
- Create Category
- Fetch Categories
- Associate Products with Categories

---

###  Price Management
- Manage product pricing
- Store and retrieve pricing information

---

###  Exception Handling
- Centralized exception handling using `@ControllerAdvice`
- Custom error responses for better API usability

---

##  Project Structure
    product-service/
    ├── controller/ # REST Controllers
    ├── service/ # Business logic
    ├── repository/ # JPA repositories
    ├── entity/ # Database entities
    ├── exception/ # Custom exceptions & handlers
    ├── dto/ (optional) # Request/Response models
    └── config/ (future) # Configuration classes


---

##  API Endpoints (Sample)

### Product APIs
- `POST /products` → Create product  
- `GET /products/{id}` → Get product by ID  
- `GET /products` → Get all products  
- `PUT /products/{id}` → Update product  
- `DELETE /products/{id}` → Delete product  

---

### Category APIs
- `POST /categories`
- `GET /categories`

---

### Price APIs
- `POST /prices`
- `GET /prices/{productId}`

---

##  How to Run Locally
    git clone https://github.com/<your-username>/product-service.git
    cd product-service
    mvn clean install
    mvn spring-boot:run

Database Configuration

Update your application.properties or application.yml:
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
