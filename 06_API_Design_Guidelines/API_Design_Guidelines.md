# API Design Guidelines

## 1. Overview

The API Design Guidelines define the common principles that every REST API within Moroccan Business OS must follow.

The objective is to ensure consistency, readability, security, maintainability, and a predictable developer experience.

These guidelines apply to all current and future APIs.

---

## 2. REST Principles

The platform follows REST architectural principles.

Every resource is represented by a URI.

The API remains stateless.

HTTP methods are used according to their semantic meaning.

---

## 3. URI Naming Convention

Rules

- Use lowercase letters.
- Use plural resource names.
- Use nouns instead of verbs.
- Use hyphens only when necessary.

Examples

/api/v1/customers

/api/v1/invoices

/api/v1/payments

/api/v1/products

---

## 4. HTTP Methods

GET

Retrieve resources.

POST

Create resources.

PUT

Replace existing resources.

PATCH

Partially update resources.

DELETE

Logical deletion.

---

## 5. HTTP Status Codes

200 OK

201 Created

204 No Content

400 Bad Request

401 Unauthorized

403 Forbidden

404 Not Found

409 Conflict

422 Unprocessable Entity

500 Internal Server Error

503 Service Unavailable

---

## 6. Standard Success Response

Every successful response follows the same structure.

```json
{
  "success": true,
  "message": "Operation completed successfully.",
  "data": {}
}
```

---

## 7. Standard Error Response

```json
{
  "success": false,
  "status": 400,
  "errorCode": "GEN-001",
  "error": "Validation Error",
  "message": "Invalid request.",
  "timestamp": "2026-07-20T10:30:00Z",
  "path": "/api/v1/resource"
}
```

---

## 8. Pagination

Every collection endpoint supports:

page

size

sort

direction

Example

GET /customers?page=0&size=20

---

## 9. Filtering

Filtering is implemented using query parameters.

Example

GET /invoices?status=PAID

GET /payments?paymentMethod=BANK_TRANSFER

---

## 10. Sorting

Sorting uses:

sort=field,direction

Example

sort=createdAt,desc

---

## 11. Authentication

Every protected endpoint requires:

Authorization

Bearer <JWT>

JWT tokens must be validated before processing any request.

---

## 12. Authorization

Authorization follows Role-Based Access Control (RBAC).

Permissions depend on:

- User Role
- Tenant Ownership
- Business Rules

---

## 13. Multi-Tenant Isolation

Every request automatically filters data using the authenticated tenant.

Cross-tenant access is strictly prohibited.

---

## 14. Validation

Validation occurs at four levels:

- Frontend
- Request Validation
- Business Validation
- Database Constraints

Backend validation is always authoritative.

---

## 15. Error Handling

Every exception returns a standardized JSON response.

Internal stack traces are never exposed.

All errors are logged.

---

## 16. Versioning

The API uses URI versioning.

Examples

/api/v1

/api/v2

Breaking changes require a new API version.

---

## 17. Idempotency

GET operations are read-only.

PUT operations are idempotent.

DELETE operations are idempotent.

POST operations creating financial transactions may support idempotency keys in future versions.

---

## 18. Security

The API follows Security by Design principles.

Sensitive data is encrypted.

Passwords are hashed.

HTTPS is mandatory.

JWT authentication is required.

Least Privilege Principle is applied.

---

## 19. Audit Logging

Every critical operation generates an immutable audit record.

Examples

- Login
- Invoice Validation
- Payment Registration
- User Creation
- Export Operations

---

## 20. Documentation

Every endpoint must define:

- Description
- Authentication
- Authorization
- Parameters
- Request Body
- Response Body
- Error Responses
- Examples

The OpenAPI specification must remain synchronized with the implementation.

---

## 21. Design Principles

Every API developed for Moroccan Business OS must respect:

- Consistency
- Simplicity
- Predictability
- Security
- Performance
- Scalability
- Maintainability
- Backward Compatibility

These guidelines are mandatory for all backend services.