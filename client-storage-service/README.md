# **Client Storage Service**

## **Overview**
`client-storage-service` is responsible for **storing processed client data** in a **PostgreSQL database**. It acts as a **consumer** in the system, listening for messages from **ActiveMQ Artemis** and persisting the received data into the database using **Spring Data JPA**.

---

## **Key Responsibilities**
- **Consumes messages from ActiveMQ Artemis** containing processed client data.
- **Stores client information (full name, contacts, and cards) in PostgreSQL**.
- **Ensures transactional integrity and efficient storage with JPA**.
- **Handles database exceptions and ensures data consistency**.

---

## **Technology Stack**
- **Java 17 + Spring Boot 3**
- **Spring JMS** (ActiveMQ Artemis Integration)
- **Spring Data JPA** (for database interaction)
- **PostgreSQL** (persistent storage)
- **SLF4J + Logback** (for structured logging)

---

## **Soft Delete Implementation**
- Instead of permanently deleting records, the system **marks them as deleted** by updating a `deleted` flag.
- This allows **data recovery and auditability** while keeping the database clean.
- JPA queries exclude soft-deleted records by default using the `@SQLDelete` annotations.
