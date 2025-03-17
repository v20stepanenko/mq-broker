This project is a **microservices-based system** for processing **client data** using **Spring Boot, ActiveMQ, PostgreSQL, Redis caching, and OpenFeign**.

---

## ** Features**
**Spring Boot Microservices Architecture**  
**Asynchronous Communication with ActiveMQ Artemis**  
**Redis Caching for Performance Optimization**  
**PostgreSQL for Persistent Data Storage**  
**WireMock for Mock External API Calls**  
**Service-to-Service Communication with OpenFeign**  
**Authentication via Username & Password**  
**Marshaling and Unmarshaling for ClientData in ActiveMQ**
**Incremental population of ClientData across services**

---

## ** Getting Started**

### **1. Build the Project**
Use **Maven** to build all microservices:
```sh
mvn clean package -DskipTests
```

### **2. Start Services with Docker Compose**
```sh
docker compose up
```
This command will:
- Start all microservices.
- Deploy **ActiveMQ Artemis** (Message Broker).
- Deploy **PostgreSQL** (Data Storage).
- Deploy **Redis** (Caching for processing services).
- Start **WireMock** to mock external API calls.

---

## ** Testing the API with Postman**
- Import the **Postman collection**: [`postman/postman_collection.json`](postman/postman_collection.json)
- Run requests in this order:
    1. **Login** (`POST /login`)
    2. **Send client data** (`POST /client/process`)
    3. **Monitor asynchronous processing in logs**

---
## ** Project Architecture**
```
 ├── client-gateway-service (Handles authentication, sends data to ActiveMQ)
 ├── client-full-name-service (Processes client full name, caches responses)
 ├── client-address-service (Retrieves address data, caches responses)
 ├── client-contacts-service (Retrieves contacts, caches responses)
 ├── service-storage (Final storage in PostgreSQL)
 ├── WireMock (Mocks external API calls)
 ├── ActiveMQ Artemis (Message Broker for asynchronous communication)
 ├── Redis (Caching for faster responses)
 ├── PostgreSQL (Database for persistent storage)
```

---

## ** Technology Stack**
| **Technology**  | **Description**  |
|---|---|
| **Java 17** | Core language for microservices |
| **Spring Boot 3** | Framework for building microservices |
| **ActiveMQ Artemis** | Message Broker for async communication |
| **Spring JMS** | Manages messaging between services |
| **Spring Data JPA** | ORM framework for PostgreSQL |
| **PostgreSQL** | SQL Database for storage |
| **Redis** | In-memory caching system |
| **WireMock** | Mock external API calls |
| **Spring Cloud OpenFeign** | Service-to-service communication |
| **Maven** | Dependency and build management |
| **Docker & Docker Compose** | Containerization and deployment |
| **Postman** | API testing tool |
