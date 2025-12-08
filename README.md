# Simple WMS API

A REST API for warehouse inventory management.

## Stack
*   Java 21 / Spring Boot 3
*   PostgreSQL
*   Docker Compose
*   OpenAPI (Swagger UI)

## Features
*   Capacity Enforcement
*   Transactional Integrity
*   Custom Exception Handling
*   Data seeding

## Quick Start
1.  Clone the repository.
2.  Run the application:
    ```bash
    docker compose up
    ```
3.  Open the API documentation:
    http://localhost:8080/swagger-ui/index.html

## Endpoints
*   `GET /api/stock`
*   `POST /api/stock`

---
*Note: Credentials are hardcoded for local development simplicity.*