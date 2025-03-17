# **Client Gateway Service**

## **Overview**  
`client-gateway-service` is the **entry point** of the system. It receives **HTTP requests** from external clients, **authenticates users**, and starts the **data collection process** by sending a message to **ActiveMQ Artemis**.  

This service acts as a **producer**, dispatching requests into the system for further processing by other microservices.

---

## ** Key Responsibilities**
Accepts **client ID** from external requests.  
**Authenticates users**.  
**Triggers the data collection process** for a given client ID.  
**Implements global exception handling** for `JMSException` to logs error details for debugging.

---

## ** Technology Stack**
- **Java 17 + Spring Boot 3**  
- **Spring Security** (Basic Authentication)  
- **Spring JMS** (ActiveMQ Artemis Integration)  
- **Spring Web**

---

## **📌 API Endpoints**
### **1️⃣ Login Endpoint**
Handles **user authentication** before allowing requests.

#### **Request:**
```http
POST /login
```
**Request Body:**
```json
{
  "success": "true"
}
```
**Response:**
```Session Cookie:
Set-Cookie: JSESSIONID=68D069AEF12345678ABCD90123; Path=/; HttpOnly
```

---

### **2️⃣ Start Client Data Processing**
Sends a request to collect client data.

#### **Request:**
```http
POST /client-collect-info
```
**Request Body:**
```json
{
  "clientId": 1
}
```
**Response:**
```json
{
  "message": "Client data processing started"
}
```

---

## **Summary**
 **Receives HTTP requests and authenticates users**.  
 **Publishes client data requests to ActiveMQ Artemis**.  
 **Starts an asynchronous data collection process**.  
 **First step in the microservices chain**.  
 **Global exception handling is implemented to manage `JMSException` occurrences**.  

