# 📚 Spring Boot Library Management System

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Hibernate](https://img.shields.io/badge/Hibernate-5.6.9-yellow.svg)](https://hibernate.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-red.svg)](https://maven.apache.org/)

A comprehensive, production-ready Library Management System showcasing modern Java backend development practices, secure authentication, and enterprise-grade architecture patterns.

## 🚀 **Why This Project Stands Out**

This application demonstrates **real-world enterprise development skills** that directly translate to production environments:

- **Scalable Architecture**: Clean separation of concerns with layered architecture (Controller → Service → Repository → Model)
- **Security-First Design**: Role-based access control (RBAC) with Spring Security
- **Production Standards**: Comprehensive logging, exception handling, and DTO patterns
- **Modern Development Practices**: Test-driven development, dependency injection, and RESTful API design

---

## 🎯 **Core Technical Competencies Demonstrated**

### 1. **Backend Systems & Microservices Architecture**
```java
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final BookService bookService;
    private final MemberService memberService;
    
    // Dependency injection and clean architecture
}
```
- **Modular Design**: Service-oriented architecture with clear boundaries
- **Spring Boot Ecosystem**: Leveraging auto-configuration and embedded server
- **Data Access Optimization**: Spring Data JPA with custom query methods
- **Scalable Patterns**: Ready for microservices decomposition

### 2. **Enterprise Security Implementation**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    // Role-based access control (RBAC)
    // BCrypt password encoding
    // Session management
}
```
- **Authentication & Authorization**: Form-based login with role-based access
- **Password Security**: BCrypt hashing with salt
- **Session Management**: Secure cookie handling and logout
- **Access Control**: Fine-grained permissions for different user roles

### 3. **RESTful API Design & Development**
```java
@GetMapping("/books")
public ResponseEntity<List<BookDTO>> getAllBooks() {
    // Clean API contracts with DTOs
}
```
- **DTOs for API Contracts**: Secure data transfer without exposing internal models
- **HTTP Standards**: Proper status codes and REST conventions
- **Error Handling**: Global exception handling with consistent responses
- **API Documentation**: Self-documenting endpoints

### 4. **Modern Development Practices**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    // Centralized exception handling
    // Structured logging
    // User-friendly error responses
}
```
- **Test-Driven Development**: JUnit testing with H2 in-memory database
- **Observability**: Structured logging with Logback and file rotation
- **Exception Handling**: Global error handling with proper logging
- **Code Quality**: Clean code principles and SOLID design patterns

---

## 🏗️ **Technical Architecture**

### **Technology Stack**
| Layer | Technology | Purpose |
|-------|------------|---------|
| **Frontend** | JSP, Bootstrap 5, JSTL | Modern, responsive UI |
| **Backend** | Spring Boot 2.7, Spring MVC | RESTful web services |
| **Security** | Spring Security 5.7 | Authentication & Authorization |
| **Data Access** | Hibernate 5.6, Spring Data JPA | ORM and data persistence |
| **Database** | MySQL 8.0, H2 (testing) | Production & test databases |
| **Build Tool** | Maven 3.8 | Dependency management |
| **Testing** | JUnit 5, Spring Boot Test | Unit and integration testing |
| **Logging** | SLF4J, Logback | Structured application logging |

### **Project Structure**
```
src/
├── main/java/com/springproject/SpringProject/
│   ├── controller/          # REST endpoints and web controllers
│   ├── service/            # Business logic layer
│   ├── repository/         # Data access layer (Spring Data JPA)
│   ├── models/            # JPA entities and domain models
│   ├── dto/               # Data Transfer Objects for API contracts
│   ├── exception/         # Global exception handling
│   ├── util/              # Utility classes and converters
│   └── configuration/     # Security and application configuration
├── main/resources/
│   ├── logback-spring.xml # Logging configuration
│   └── application.properties
└── test/                  # Comprehensive test suite
```

---

## 🔐 **Security Features**

- **Role-Based Access Control (RBAC)**
  - `ADMIN`: Full system access, user management, book management
  - `USER`: Browse books, manage personal profile, borrow books

- **Authentication Flow**
  - Secure login with BCrypt password hashing
  - Session-based authentication with CSRF protection
  - Automatic logout with secure session cleanup

- **Data Protection**
  - DTOs prevent sensitive data exposure
  - Input validation and sanitization
  - SQL injection prevention through JPA

---

## 🚀 **Quick Start Guide**

### **Prerequisites**
- Java 11 or higher
- MySQL 8.0
- Maven 3.6+

### **1. Clone & Setup**
```bash
git clone https://github.com/Gudalasaipraneeth/springboot-hibernate-library-app.git
cd springboot-hibernate-library-app
```

### **2. Database Configuration**
```bash
# Set environment variables for security
export DB_URL="jdbc:mysql://localhost:3306/librarydb?createDatabaseIfNotExist=true"
export DB_USERNAME="your_mysql_username"
export DB_PASSWORD="your_mysql_password"
```

### **3. Run Application**
```bash
./mvnw clean spring-boot:run
```

### **4. Access Application**
- **Application**: http://localhost:8080
- **Admin Login**: Use credentials from your database setup
- **User Registration**: Available at `/register`

### **5. Run Tests**
```bash
./mvnw test
```

---

## 📊 **Key Features**

### **For Library Administrators**
- 📖 **Book Management**: Add, update, delete, and categorize books
- 👥 **Member Management**: View and manage library members
- 📋 **Borrow Tracking**: Monitor borrowed books and due dates
- 🏷️ **Genre Management**: Organize books by categories

### **For Library Members**
- 🔍 **Book Catalog**: Browse available books with search functionality
- 👤 **Profile Management**: Update personal information securely
- 📚 **Borrowing System**: Request and track borrowed books
- 🔐 **Secure Access**: Personal dashboard with authentication

---

## 🧪 **Testing Strategy**

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

- **Unit Tests**: Service layer business logic validation
- **Integration Tests**: End-to-end API testing
- **Repository Tests**: Data access layer validation with H2
- **Security Tests**: Authentication and authorization workflows

---

## 📈 **Production Readiness**

### **Monitoring & Observability**
- Structured logging with Logback
- File rotation and log management
- Error tracking and debugging support

### **Performance**
- Connection pooling with HikariCP
- JPA optimization and lazy loading
- Efficient query design with Spring Data JPA

### **Scalability**
- Stateless service design
- Clean separation of concerns
- Ready for horizontal scaling

---

## 🛠️ **Development Highlights**

This project showcases several **advanced development practices**:

1. **Clean Architecture**: Proper layering and dependency injection
2. **Security Best Practices**: RBAC, password hashing, session management
3. **Modern Java**: Java 11+ features and best practices
4. **Spring Ecosystem**: Full utilization of Spring Boot capabilities
5. **Database Design**: Normalized schema with proper relationships
6. **Error Handling**: Comprehensive exception management
7. **Testing**: TDD approach with multiple testing layers

---

## 💼 **Professional Impact**

This project directly demonstrates skills relevant to:
- **Enterprise Java Development**
- **Microservices Architecture**
- **Security Implementation**
- **RESTful API Design**
- **Database Design & Optimization**
- **Test-Driven Development**
- **Production System Design**

---

## 📞 **Contact**

**Gudala Sai Praneeth**
- 📧 Email: saigudala188@gmail.com
- 💼 LinkedIn: https://www.linkedin.com/in/gudalasaipraneeth/
- 🐙 GitHub: [@Gudalasaipraneeth](https://github.com/Gudalasaipraneeth)

---

## 📄 **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

*Built with ❤️ using Spring Boot and modern Java development practices*
