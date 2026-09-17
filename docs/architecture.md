# E-Commerce Platform — Architecture

## 1. Architecture Evolution

The project will not start as a complete microservices
architecture.

The system will be developed incrementally.

The architecture will evolve from a simple service into
a distributed microservices-based system.

## 2. Target Architecture

The final architecture will contain:

- Angular Frontend
- API Gateway
- User Service
- Product Service
- Cart Service
- Order Service
- Inventory Service
- Payment Service
- Notification Service
- Eureka Service Registry
- MySQL
- Redis
- Kafka

Angular
|
v
API Gateway
|
+----------------+
|        |       |
v        v       v
User    Product   Order
Service  Service   Service
|        |       |
MySQL    MySQL   MySQL
|
v
Kafka
/   |   \
/    |    \
v     v     v
Inventory Payment Notification
Service   Service   Service