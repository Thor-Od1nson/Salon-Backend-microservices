### Tech stack 

*Java 17, Springboot 3.5.0, maven 3.9.8

### How to run this project

1. clone the project
2. Run--  mvn clean install
3. First run Eureka server then API Gateway and later you can other services

### Salon Management System (Microservices-based Project)

Built a comprehensive salon appointment system using microservices architecture, enabling users to book appointments, make payments, and provide reviews. 
The system empowers salon owners to manage services, categories, and bookings efficiently. Secure, scalable, and user-friendly, the application includes 
service segregation, role-based access, and real-time availability tracking.

#### Key Contributions:

* Developed scalable microservices for salon, booking, payment, and user management using Spring Boot, improving modularity and service maintainability.
* Engineered an API Gateway to centralize routing and authentication, enhancing request handling and system security.
* Implemented booking logic enabling users to schedule appointments based on real-time salon availability, reducing double bookings.
* Enabled salon owners to manage categories and service offerings dynamically, supporting flexible business needs and service updates.
* Integrated a secure payment service for post-appointment transactions, ensuring reliable and traceable payment processing.
* Built a review system allowing users to rate services post-appointment, increasing customer engagement and feedback visibility.
* Designed a role-based access system for users and salon owners, supporting secure operations and proper data segregation.

### Architecture

Clients (Web + Mobile)
       ↓
[ API Gateway ]  ← Spring Cloud Gateway + JWT Authentication + Rate Limiting
       ↓
[Eureka Server]  ← Service Discovery

       ┌──────────────────────┬──────────────────────┬──────────────────────┐
       │                      │                      │                      │
   User Service         Salon Service         Category Service      Service Offering Service
       │                      │                      │                      │
       └──────────────┬───────┴──────────────┬───────┴──────────────┬───────┘
                      │                      │                      │
                  Booking Service  ────►  Payment Service
                      │
                  Review Service (can be added later)

          ↓ (Async Events)
     [ RabbitMQ / Kafka ]   ← For Saga, Notifications, Loyalty etc.
