# **Client Contacts Service**

## **Overview**
`client-contacts-service` is responsible for **retrieving and processing client contact information**. It acts as a **consumer** in the system, listening for messages from **ActiveMQ Artemis**, fetching the client's contacts from a mocked external service, caching responses in **Redis**, and sending the enriched data back to the message queue.

---

## **Key Responsibilities**
- **Consumes messages from ActiveMQ Artemis** containing a `clientId`.
- **Fetches the client's contact information** from a mocked external API.
- **Caches contact data in Redis** for faster access.
- **Processes and enriches the client data** with the retrieved contact details.
- **Publishes the enriched client data** back to ActiveMQ for further processing.

---

## **Technology Stack**
- **Java 17 + Spring Boot 3**
- **Spring JMS** (ActiveMQ Artemis Integration)
- **Spring WebClient** (for calling mocked external services)
- **Spring Data Redis** (for caching client contacts)

---

## **Summary**
- **Consumes client data requests from ActiveMQ**.
- **Checks Redis cache before making external API calls**.
- **Fetches contact details from a mocked API if cache miss occurs**.
- **Caches contact data in Redis for faster future retrievals**.
- **Adds contact information to the client data object**.
- **Publishes enriched data back to ActiveMQ**.
- **Implements error handling, logging, and retries for robustness**.

