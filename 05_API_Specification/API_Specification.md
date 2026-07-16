# API Specification

**Project:** Moroccan Business OS

**Version:** 1.0

**Status:** Draft

---

# 1. Introduction

## 1.1 Purpose

The purpose of this document is to define the REST API specification of Moroccan Business OS.

It serves as the contract between the frontend, backend, mobile applications, and future third-party integrations.

Every API endpoint implemented in the application must comply with this specification.

---

## 1.2 Scope

This document specifies:

- REST endpoints
- HTTP methods
- Request structure
- Response structure
- Authentication
- Authorization
- Validation
- Error handling
- Pagination
- Filtering
- Versioning

---

## 1.3 Objectives

The API aims to provide:

- Consistency
- Simplicity
- Security
- Predictability
- Performance
- Scalability
- Maintainability

---

## 1.4 API Style

Moroccan Business OS adopts a RESTful API architecture.

The API follows resource-oriented design principles.

Resources are accessed through predictable URIs using standard HTTP methods.

---

## 1.5 Supported Methods

The API uses:

- GET
- POST
- PUT
- PATCH
- DELETE

Each method has a specific semantic meaning and must be used consistently.

---

## 1.6 Data Format

The API exchanges data using JSON.

Request:

application/json

Response:

application/json

UTF-8 encoding is mandatory.

---

## 1.7 Base URL

Development

/api/v1

Production

https://api.moroccanbusinessos.com/api/v1

---

## 1.8 References

The API specification is based on:

- Product Requirements Document
- Software Architecture Document
- Database Design Document
- REST API Best Practices
- OpenAPI 3.1 Specification
# 2. API Standards

## 2.1 Overview

This chapter defines the common standards that every API endpoint within Moroccan Business OS must follow.

These standards guarantee consistency, readability, maintainability, and interoperability across all application modules.

---

## 2.2 REST Principles

The API follows REST architectural principles.

Resources are identified by URIs.

Operations are performed using HTTP methods.

The API remains stateless.

---

## 2.3 URI Naming

Rules:

- Use nouns instead of verbs.
- Use lowercase letters.
- Use hyphens only when necessary.
- Use plural resource names.

Examples:

/customers

/products

/invoices

/payments

---

## 2.4 HTTP Methods

GET

Retrieve resources.

POST

Create resources.

PUT

Replace resources.

PATCH

Partially update resources.

DELETE

Logical deletion.

---

## 2.5 Status Codes

Successful Requests

200 OK

201 Created

204 No Content

Client Errors

400 Bad Request

401 Unauthorized

403 Forbidden

404 Not Found

409 Conflict

422 Validation Error

Server Errors

500 Internal Server Error

503 Service Unavailable

---

## 2.6 JSON Format

Every request and response uses JSON.

Example:

{
"id": "...",
"name": "...",
"status": "ACTIVE"
}

---

## 2.7 Pagination

Large collections support pagination.

Parameters:

page

size

sort

Example:

GET /customers?page=0&size=20

---

## 2.8 Filtering

Resources support filtering.

Example:

GET /customers?status=ACTIVE

GET /invoices?customerId=...

---

## 2.9 Sorting

Sorting follows:

sort=field,direction

Example:

sort=createdAt,desc

---

## 2.10 API Consistency

Every endpoint follows identical response structures, naming conventions, and validation rules.

Consistency is mandatory across the platform.
# 3. Authentication API

## 3.1 Overview

Authentication secures access to Moroccan Business OS.

JWT authentication is used together with Refresh Tokens.

---

## 3.2 Endpoints

POST /auth/login

POST /auth/logout

POST /auth/refresh

POST /auth/forgot-password

POST /auth/reset-password

GET /auth/me

---

## 3.3 Login

POST /auth/login

Request

{
"email":"user@email.com",
"password":"********"
}

Response

{
"accessToken":"...",
"refreshToken":"...",
"user":{}
}

---

## 3.4 Logout

POST /auth/logout

Invalidates refresh token.

---

## 3.5 Refresh Token

POST /auth/refresh

Returns a new access token.

---

## 3.6 Current User

GET /auth/me

Returns authenticated user profile.

---

## 3.7 Password Reset

Forgot Password

↓

Email Verification

↓

Reset Password

---

## 3.8 Security Rules

JWT required.

HTTPS only.

Passwords hashed with BCrypt.

Refresh Tokens stored securely.

Inactive users cannot authenticate.

---

## 3.9 Errors

400 Invalid Request

401 Invalid Credentials

403 User Disabled

404 User Not Found

500 Internal Error
# 4. Enterprise API

## 4.1 Overview

The Enterprise API manages company information and workspace configuration.

Each authenticated user belongs to one enterprise.

---

## 4.2 Endpoints

GET /enterprises

GET /enterprises/{id}

POST /enterprises

PUT /enterprises/{id}

PATCH /enterprises/{id}

DELETE /enterprises/{id}

---

## 4.3 Create Enterprise

POST /enterprises

Request

{
"name":"ABC SARL",
"ice":"001234567890123",
"phone":"+212600000000",
"email":"contact@abc.ma"
}

Response

201 Created

---

## 4.4 Update Enterprise

PUT /enterprises/{id}

Updates company information.

---

## 4.5 Retrieve Enterprise

GET /enterprises/{id}

Returns enterprise details.

---

## 4.6 Delete Enterprise

DELETE /enterprises/{id}

Logical deletion only.

---

## 4.7 Validation

Company Name required.

ICE unique.

Email valid.

Phone valid.

---

## 4.8 Permissions

Owner

Admin

Manager

Only authorized roles may update enterprise information.

---

## 4.9 Response Example

{
"id":"...",
"name":"ABC SARL",
"status":"ACTIVE",
"createdAt":"..."
}
# 5. User API

## 5.1 Overview

The User API manages user accounts within an enterprise.

It provides endpoints for creating, updating, retrieving, and managing users and their roles.

---

## 5.2 Endpoints

GET /users

GET /users/{id}

POST /users

PUT /users/{id}

PATCH /users/{id}

DELETE /users/{id}

---

## 5.3 Create User

POST /users

Creates a new user associated with the current enterprise.

---

## 5.4 Update User

PUT /users/{id}

Updates user information.

---

## 5.5 Retrieve Users

GET /users

Returns a paginated list of users.

Supports:

- Pagination
- Sorting
- Filtering

---

## 5.6 Permissions

Only Owners and Administrators can create or delete users.

Managers can view users.

Employees can only view their own profile.

---

## 5.7 Validation

- Email unique within tenant
- Password policy enforced
- Role required
- Enterprise automatically assigned

---

## 5.8 Response Example

{
"id":"...",
"firstName":"Ahmed",
"lastName":"El Amrani",
"email":"ahmed@company.ma",
"role":"ADMIN",
"status":"ACTIVE"
}
# 6. Customer API

## 6.1 Overview

The Customer API manages customer information.

Customers are linked to quotations, invoices, and payments.

---

## 6.2 Endpoints

GET /customers

GET /customers/{id}

POST /customers

PUT /customers/{id}

DELETE /customers/{id}

---

## 6.3 Features

- Customer creation
- Customer update
- Customer search
- Customer archive
- Customer history

---

## 6.4 Filters

- Company Name
- City
- Status
- Created Date

---

## 6.5 Validation

- Company Name mandatory
- Email valid
- Phone optional
- Customer Code unique

---

## 6.6 Relationships

Customer

↓

Quotations

↓

Invoices

↓

Payments

---

## 6.7 Permissions

Owner

Admin

Manager

Sales

---

## 6.8 Response

Returns customer profile including statistics and financial summary.
# 7. Supplier API

## 7.1 Overview

The Supplier API manages suppliers.

Suppliers are associated with expenses and future purchase modules.

---

## 7.2 Endpoints

GET /suppliers

GET /suppliers/{id}

POST /suppliers

PUT /suppliers/{id}

DELETE /suppliers/{id}

---

## 7.3 Features

- Supplier management
- Supplier contacts
- Supplier status
- Supplier history

---

## 7.4 Validation

Supplier Name mandatory.

ICE optional.

Email valid.

Phone valid.

---

## 7.5 Permissions

Owner

Admin

Manager

Accountant

---

## 7.6 Response

Supplier profile with related expenses.
# 8. Product API

## 8.1 Overview

The Product API manages products sold by the enterprise.

Products are used in quotations and invoices.

---

## 8.2 Endpoints

GET /products

GET /products/{id}

POST /products

PUT /products/{id}

DELETE /products/{id}

---

## 8.3 Features

- Product catalog
- Categories
- Pricing
- Product status

Future:

- Inventory
- Barcode
- Stock

---

## 8.4 Validation

Product Code unique.

Name required.

Price positive.

VAT required.

---

## 8.5 Relationships

Product

↓

Quotation Lines

↓

Invoice Lines

---

## 8.6 Permissions

Owner

Admin

Manager

Sales

---

## 8.7 Response

Returns product information including VAT and pricing.
# 9. Service API

## 9.1 Overview

The Service API manages professional services sold by the enterprise.

Services complement products and are primarily used by freelancers, agencies, consultants, and service-based businesses.

---

## 9.2 Endpoints

GET /services

GET /services/{id}

POST /services

PUT /services/{id}

DELETE /services/{id}

---

## 9.3 Features

- Service catalog
- Hourly services
- Fixed-price services
- Categories

---

## 9.4 Validation

Service Name required.

Price required.

VAT required.

Category optional.

---

## 9.5 Relationships

Service

↓

Quotation Lines

↓

Invoice Lines

---

## 9.6 Permissions

Owner

Admin

Manager

Sales

---

## 9.7 Response

Return# 10. Invoice API

# 10.1 Overview

The Invoice API is one of the core modules of Moroccan Business OS.

It manages the complete lifecycle of customer invoices, from draft creation to final payment.

The API enables businesses to create, validate, issue, send, monitor, and archive invoices while ensuring compliance with Moroccan business practices and future regulatory requirements.

Invoices generated through this API are linked to customers, products, services, taxes, payments, reports, and notifications.

The Invoice API is designed to support service companies, freelancers, retailers, agencies, consultants, and small to medium-sized enterprises operating in Morocco.

---

## 10.2 Business Purpose

The purpose of the Invoice API is to provide a secure, reliable, and standardized way to manage commercial invoices.

The API allows businesses to:

- Create professional invoices.
- Edit invoices while they remain in Draft status.
- Automatically calculate totals, discounts, and VAT.
- Associate invoices with customers.
- Include products and services.
- Generate printable PDF invoices.
- Send invoices by email.
- Send invoices through WhatsApp Business.
- Track payment status.
- Record partial or full payments.
- Detect overdue invoices.
- Generate financial reports.
- Feed dashboard statistics.
- Provide financial data to the AI Assistant.

The Invoice API acts as the central financial component of Moroccan Business OS and integrates with almost every major business module.s service information, pricing, VAT, and category.## 10.2 Invoice Lifecycle

### Overview

Every invoice progresses through a predefined lifecycle.

The lifecycle guarantees consistency, traceability, and compliance with business rules.

An invoice can only move to the next state when all validation rules are satisfied.

Direct transitions that bypass required business steps are prohibited.

---

### Invoice Statuses

| Status | Description |
|----------|-------------|
| DRAFT | Invoice is being created and can still be edited. |
| VALIDATED | Invoice has been validated and locked for business consistency. |
| SENT | Invoice has been sent to the customer by Email or WhatsApp. |
| PARTIALLY_PAID | Customer has paid part of the invoice amount. |
| PAID | Invoice has been fully paid. |
| OVERDUE | Invoice due date has passed without full payment. |
| CANCELLED | Invoice has been cancelled following business rules. |
| ARCHIVED | Invoice is archived and no longer appears in daily operations. |

---

### State Transition Diagram

Draft

↓

Validated

↓

Sent

↓

Partially Paid

↓

Paid

↓

Archived

Alternative transitions:

Draft

↓

Cancelled

Validated

↓

Cancelled

Sent

↓

Cancelled (only if no payment exists)

Paid invoices cannot be cancelled.

Archived invoices cannot be modified.

---

### Transition Rules

#### Draft → Validated

Conditions:

- Customer selected
- At least one invoice line
- VAT calculated
- Totals calculated
- Due date defined

---

#### Validated → Sent

Conditions:

- PDF successfully generated
- Customer email or WhatsApp available

---

#### Sent → Partially Paid

Conditions:

- Payment amount > 0
- Payment amount < Remaining Balance

---

#### Partially Paid → Paid

Conditions:

Remaining Balance = 0

---

#### Sent → Overdue

Conditions:

Current Date > Due Date

Remaining Balance > 0

---

#### Overdue → Paid

Conditions:

Remaining Balance = 0

---

#### Draft → Cancelled

Allowed without restrictions.

---

#### Validated → Cancelled

Allowed only if:

- No payment exists
- No accounting export performed

---

#### Sent → Cancelled

Allowed only if:

- Customer agrees
- No payment registered

---

### Forbidden Transitions

The following transitions are prohibited:

Paid → Draft

Paid → Validated

Paid → Cancelled

Archived → Draft

Archived → Paid

Cancelled → Paid

Cancelled → Draft

---

### Lifecycle Benefits

The lifecycle provides:

- Data consistency
- Financial traceability
- Auditability
- Better reporting
- Reliable KPIs
- Legal compliance
## 10.3 Business Workflow

### Overview

The Invoice Business Workflow defines the complete lifecycle of an invoice from its creation until its final archival.

The workflow ensures that every invoice follows the same business process across the platform.

Each step is controlled by validation rules, permissions, and business constraints.

---

## Workflow Overview

New Invoice

↓

Draft

↓

Validation

↓

Invoice Number Generation

↓

PDF Generation

↓

Send to Customer

↓

Payment Tracking

↓

Invoice Paid

↓

Archive

---

## Step 1 — Create Draft Invoice

The user clicks **New Invoice**.

The system creates a Draft invoice.

The invoice is editable.

No accounting impact exists.

No PDF is generated.

No notification is sent.

---

## Step 2 — Add Invoice Information

The user completes:

- Customer
- Invoice Date
- Due Date
- Currency
- Payment Terms
- Notes

The system validates mandatory information.

---

## Step 3 — Add Invoice Lines

Each invoice contains one or more lines.

Each line may contain:

- Product
- Service
- Description
- Quantity
- Unit Price
- Discount
- VAT Rate
- Line Total

The system recalculates totals automatically after each modification.

---

## Step 4 — Automatic Calculations

The platform automatically calculates:

- Subtotal
- Discount Amount
- VAT Amount
- Total Amount
- Remaining Balance

Manual calculations are not allowed.

---

## Step 5 — Validation

Before validation the system verifies:

- Customer exists
- Customer belongs to current tenant
- Invoice contains at least one line
- Prices are valid
- VAT is valid
- Totals are consistent

If validation fails, the invoice remains in Draft status.

---

## Step 6 — Validate Invoice

The user clicks **Validate Invoice**.

The system:

- Locks financial fields
- Generates invoice number
- Changes status to VALIDATED
- Creates audit entry

The invoice becomes immutable except for authorized operations.

---

## Step 7 — Generate PDF

The system generates the official PDF document.

The PDF includes:

- Company Information
- Customer Information
- Invoice Number
- Invoice Lines
- Totals
- VAT Breakdown
- QR Code (future version)
- Legal Footer

The generated file is stored in Object Storage.

---

## Step 8 — Send Invoice

The invoice may be sent using:

- Email
- WhatsApp Business
- Manual Download

The system records:

- Sending Date
- Sending Channel
- Delivery Status

Status changes to SENT.

---

## Step 9 — Payment Registration

Payments may be registered manually or automatically.

Each payment records:

- Amount
- Method
- Reference
- Payment Date
- Notes

Multiple payments are allowed.

---

## Step 10 — Payment Monitoring

The system continuously calculates:

- Amount Paid
- Remaining Balance
- Payment Percentage

Possible statuses:

- SENT
- PARTIALLY_PAID
- PAID
- OVERDUE

---

## Step 11 — Dashboard Update

Every invoice operation updates:

- Revenue KPIs
- Cash Flow
- Outstanding Invoices
- Customer Statistics
- Financial Charts

Dashboard metrics are refreshed automatically.

---

## Step 12 — Notifications

The platform automatically sends notifications when:

- Invoice validated
- Invoice sent
- Payment received
- Due date approaching
- Invoice overdue

Notifications may use:

- Email
- WhatsApp
- In-App Notification

---

## Step 13 — AI Integration

The AI Assistant analyzes invoices to provide:

- Revenue insights
- Late payment predictions
- Customer payment behavior
- Cash flow forecasts
- Business recommendations

---

## Step 14 — Archive

Paid or cancelled invoices may be archived.

Archived invoices:

- remain searchable;
- remain available for reports;
- cannot be modified.

---

## Business Workflow Summary

Create

↓

Draft

↓

Add Information

↓

Add Lines

↓

Automatic Calculations

↓

Validation

↓

Generate Invoice Number

↓

Generate PDF

↓

Send

↓

Receive Payments

↓

Dashboard Update

↓

AI Analysis

↓

Archive
## 10.4 Invoice API Endpoints

### Overview

The Invoice API exposes a complete set of REST endpoints covering the entire invoice lifecycle.

All endpoints require authentication.

All business data is automatically isolated by the authenticated tenant.

Base URL

/api/v1/invoices

---

# 1. Get All Invoices

GET /api/v1/invoices

Description

Returns a paginated list of invoices belonging to the authenticated enterprise.

Authorization

Owner

Admin

Manager

Accountant

Sales

Query Parameters

| Parameter | Type | Required | Description |
|------------|------|----------|-------------|
| page | Integer | No | Page number |
| size | Integer | No | Number of records |
| sort | String | No | Sort field |
| direction | String | No | ASC or DESC |
| status | String | No | Invoice Status |
| customerId | UUID | No | Filter by customer |
| startDate | Date | No | Invoice date |
| endDate | Date | No | Invoice date |

Response

200 OK

Returns paginated invoices.

Possible Errors

401 Unauthorized

403 Forbidden

500 Internal Server Error

---

# 2. Get Invoice By ID

GET /api/v1/invoices/{id}

Description

Returns complete invoice information.

Path Parameters

id (UUID)

Response

200 OK

404 Invoice Not Found

403 Forbidden

---

# 3. Create Invoice

POST /api/v1/invoices

Description

Creates a new Draft invoice.

Authorization

Owner

Admin

Manager

Sales

Request Body

Customer

Invoice Date

Due Date

Currency

Invoice Lines

Payment Terms

Notes

Response

201 Created

Returns newly created invoice.

Possible Errors

400 Validation Error

409 Business Rule Violation

---

# 4. Update Invoice

PUT /api/v1/invoices/{id}

Description

Updates a Draft invoice.

Only Draft invoices may be edited.

Possible Responses

200 OK

400 Validation Error

404 Not Found

409 Invoice Already Validated

---

# 5. Partial Update

PATCH /api/v1/invoices/{id}

Updates one or more editable fields.

Supports partial modifications.

---

# 6. Delete Invoice

DELETE /api/v1/invoices/{id}

Description

Performs logical deletion.

Only Draft invoices may be deleted.

Response

204 No Content

Errors

404 Not Found

409 Invoice Cannot Be Deleted

---

# 7. Validate Invoice

POST /api/v1/invoices/{id}/validate

Description

Validates invoice.

Actions

Checks business rules.

Locks invoice.

Generates invoice number.

Creates audit record.

Returns

200 OK

---

# 8. Generate PDF

GET /api/v1/invoices/{id}/pdf

Description

Returns generated PDF invoice.

Content Type

application/pdf

Possible Responses

200 OK

404 PDF Not Found

---

# 9. Send Invoice

POST /api/v1/invoices/{id}/send

Description

Sends invoice to customer.

Supported Channels

Email

WhatsApp Business

Future:

SMS

Response

200 OK

---

# 10. Duplicate Invoice

POST /api/v1/invoices/{id}/duplicate

Description

Creates a new Draft invoice from an existing invoice.

Response

201 Created

---

# 11. Cancel Invoice

POST /api/v1/invoices/{id}/cancel

Description

Cancels invoice according to business rules.

Restrictions

Paid invoices cannot be cancelled.

Response

200 OK

409 Business Rule Violation

---

# 12. Mark Invoice As Paid

POST /api/v1/invoices/{id}/mark-paid

Description

Marks invoice as fully paid.

Normally performed automatically after payment registration.

Response

200 OK

---

# 13. Get Invoice Payments

GET /api/v1/invoices/{id}/payments

Description

Returns every payment linked to the invoice.

Response

200 OK

---

# 14. Export Invoices

GET /api/v1/invoices/export

Description

Exports invoices.

Supported Formats

PDF

Excel

CSV

Future

XML

UBL

---

# 15. Invoice Statistics

GET /api/v1/invoices/statistics

Returns

Total Revenue

Total Paid

Outstanding Amount

Overdue Amount

Invoice Count

Average Invoice Value

Top Customers

Monthly Revenue

---

# 16. Overdue Invoices

GET /api/v1/invoices/overdue

Returns invoices whose due date has passed.

---

# 17. Draft Invoices

GET /api/v1/invoices/drafts

Returns invoices still in Draft status.

---

## Endpoint Summary

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /invoices | List invoices |
| GET | /invoices/{id} | Invoice details |
| POST | /invoices | Create invoice |
| PUT | /invoices/{id} | Update invoice |
| PATCH | /invoices/{id} | Partial update |
| DELETE | /invoices/{id} | Logical delete |
| POST | /invoices/{id}/validate | Validate invoice |
| GET | /invoices/{id}/pdf | Download PDF |
| POST | /invoices/{id}/send | Send invoice |
| POST | /invoices/{id}/duplicate | Duplicate invoice |
| POST | /invoices/{id}/cancel | Cancel invoice |
| POST | /invoices/{id}/mark-paid | Mark as paid |
| GET | /invoices/{id}/payments | List payments |
| GET | /invoices/export | Export invoices |
| GET | /invoices/statistics | Dashboard statistics |
| GET | /invoices/overdue | Overdue invoices |
| GET | /invoices/drafts | Draft invoices |
## 10.5 Request Models & Response Models

### Overview

This section defines the JSON structures exchanged between clients and the Invoice API.

Every request and response follows a consistent format to ensure interoperability between the frontend, backend, mobile applications, and third-party integrations.

---

# Create Invoice Request

POST /api/v1/invoices

```json
{
  "customerId": "uuid",
  "invoiceDate": "2026-07-15",
  "dueDate": "2026-08-15",
  "currency": "MAD",
  "paymentTerms": "30 Days",
  "notes": "Thank you for your business.",
  "lines": [
    {
      "type": "PRODUCT",
      "productId": "uuid",
      "description": "Laptop Lenovo ThinkPad",
      "quantity": 2,
      "unitPrice": 12000.00,
      "discount": 10,
      "vatRate": 20
    },
    {
      "type": "SERVICE",
      "serviceId": "uuid",
      "description": "Installation",
      "quantity": 1,
      "unitPrice": 500,
      "discount": 0,
      "vatRate": 20
    }
  ]
}
```

---

# Create Invoice Response

HTTP 201 Created

```json
{
  "id": "uuid",
  "invoiceNumber": "FAC-2026-000001",
  "status": "DRAFT",
  "customerId": "uuid",
  "subtotal": 24500.00,
  "discountAmount": 2400.00,
  "vatAmount": 4420.00,
  "totalAmount": 26520.00,
  "currency": "MAD",
  "createdAt": "2026-07-15T10:00:00Z"
}
```

---

# Invoice Details Response

GET /api/v1/invoices/{id}

```json
{
  "id": "uuid",
  "invoiceNumber": "FAC-2026-000001",
  "status": "SENT",
  "customer": {
    "id": "uuid",
    "companyName": "ABC SARL",
    "email": "contact@abc.ma"
  },
  "invoiceDate": "2026-07-15",
  "dueDate": "2026-08-15",
  "currency": "MAD",
  "subtotal": 24500.00,
  "discountAmount": 2400.00,
  "vatAmount": 4420.00,
  "totalAmount": 26520.00,
  "remainingBalance": 12520.00,
  "paymentStatus": "PARTIALLY_PAID",
  "lines": [],
  "payments": [],
  "createdAt": "2026-07-15T10:00:00Z",
  "updatedAt": "2026-07-16T09:15:00Z"
}
```

---

# Update Invoice Request

PUT /api/v1/invoices/{id}

```json
{
  "dueDate": "2026-08-30",
  "paymentTerms": "45 Days",
  "notes": "Updated payment conditions."
}
```

Only invoices in DRAFT status may be updated.

---

# Validate Invoice Response

POST /api/v1/invoices/{id}/validate

```json
{
  "message": "Invoice validated successfully.",
  "invoiceNumber": "FAC-2026-000001",
  "status": "VALIDATED"
}
```

---

# Send Invoice Response

POST /api/v1/invoices/{id}/send

```json
{
  "message": "Invoice sent successfully.",
  "channel": "EMAIL",
  "sentAt": "2026-07-15T14:25:00Z",
  "status": "SENT"
}
```

---

# Invoice Statistics Response

GET /api/v1/invoices/statistics

```json
{
  "totalInvoices": 235,
  "draftInvoices": 12,
  "paidInvoices": 180,
  "overdueInvoices": 8,
  "totalRevenue": 2150000.00,
  "outstandingAmount": 185000.00,
  "averageInvoice": 9148.93,
  "currency": "MAD"
}
```

---

# Standard Success Response

Every successful operation returns the following structure.

```json
{
  "success": true,
  "message": "Operation completed successfully.",
  "data": {}
}
```

---

# Standard Error Response

Every error follows the same format.

```json
{
  "success": false,
  "timestamp": "2026-07-15T10:15:00Z",
  "status": 400,
  "error": "Validation Error",
  "message": "Customer is required.",
  "path": "/api/v1/invoices"
}
```

---

# Response Principles

All responses must:

- Use UTF-8 encoding.
- Return JSON.
- Include meaningful HTTP status codes.
- Never expose internal server information.
- Use camelCase for JSON properties.
- Include ISO 8601 timestamps.
- Return monetary values using two decimal places.

---

# Design Benefits

The standardized request and response models provide:

- Consistent API behavior
- Easier frontend integration
- Strong typing
- Better documentation
- Simplified testing
- Future mobile compatibility
## 10.6 Business Rules

### Overview

Business Rules define the functional behavior of the Invoice module.

Every invoice operation must comply with these rules to guarantee consistency, financial accuracy, tenant isolation, and compliance with Moroccan business practices.

These rules are enforced by the backend regardless of the client application (Web, Mobile, API).

---

# BR-001 Invoice Ownership

Every invoice belongs to exactly one enterprise.

Invoices cannot be transferred between enterprises.

Tenant isolation is mandatory.

---

# BR-002 Customer Ownership

An invoice must reference an existing customer belonging to the same tenant.

Cross-tenant customer references are prohibited.

---

# BR-003 Draft Creation

A newly created invoice always starts with the status:

DRAFT

Invoice numbers are not generated at this stage.

---

# BR-004 Invoice Number Generation

The invoice number is automatically generated during validation.

Users cannot manually modify invoice numbers.

Invoice numbers must be unique within an enterprise.

Example:

FAC-2026-000001

---

# BR-005 Invoice Lines

An invoice must contain at least one invoice line.

Invoice lines may represent:

- Product
- Service

Empty invoices are prohibited.

---

# BR-006 Automatic Calculations

The platform automatically calculates:

- Subtotal
- Discount Amount
- VAT Amount
- Grand Total
- Remaining Balance

Users cannot manually edit calculated values.

---

# BR-007 VAT Calculation

VAT is calculated automatically according to the selected VAT rate.

The calculation must remain consistent across the entire invoice.

Future versions may support multiple VAT rates on the same invoice.

---

# BR-008 Invoice Validation

An invoice may be validated only if:

- Customer exists.
- Customer is active.
- At least one invoice line exists.
- Totals are correct.
- Due date is valid.

Otherwise validation fails.

---

# BR-009 Immutable Financial Data

After validation:

The following fields become read-only:

- Customer
- Invoice Lines
- Unit Prices
- VAT
- Discounts
- Currency

Only administrative notes may remain editable.

---

# BR-010 Invoice Sending

Only validated invoices may be sent.

Sending methods:

- Email
- WhatsApp Business

Every sending action is logged.

---

# BR-011 PDF Generation

Every validated invoice must have an official PDF.

The generated PDF is immutable.

A new PDF version is generated only after creating a new invoice version (future feature).

---

# BR-012 Partial Payments

An invoice may receive multiple payments.

Example:

Invoice

10 000 MAD

Payment 1

3 000 MAD

Payment 2

7 000 MAD

Status

PAID

---

# BR-013 Payment Validation

A payment cannot exceed the remaining balance.

Negative payments are prohibited.

Zero-value payments are prohibited.

---

# BR-014 Invoice Cancellation

Invoices may be cancelled only if:

- No payment exists

OR

Administrative authorization exists.

Paid invoices cannot be cancelled.

---

# BR-015 Overdue Detection

An invoice automatically becomes OVERDUE when:

Current Date > Due Date

AND

Remaining Balance > 0

---

# BR-016 Soft Delete

Invoices are never physically deleted.

Deletion is logical only.

Archived invoices remain available for reporting and auditing.

---

# BR-017 Audit Trail

Every important action generates an audit record.

Tracked actions include:

- Create
- Update
- Validate
- Send
- Payment
- Cancel
- Archive

Audit records are immutable.

---

# BR-018 Dashboard Synchronization

Every invoice operation automatically updates:

- Revenue
- Outstanding Balance
- Customer KPIs
- Monthly Sales
- Dashboard Statistics

No manual synchronization is required.

---

# BR-019 AI Integration

Invoice data is available to the AI Assistant.

The AI may:

- Generate financial summaries
- Detect anomalies
- Predict late payments
- Suggest reminders
- Recommend business actions

The AI cannot modify invoices.

---

# BR-020 Security

Every invoice request must verify:

- Authentication
- Authorization
- Tenant ownership
- Resource existence
- Business permissions

Unauthorized access returns HTTP 403.

---

# Business Rules Summary

The Invoice API guarantees:

- Financial consistency
- Data integrity
- Tenant isolation
- Full traceability
- Automated calculations
- Reliable reporting
- Secure operations
- Enterprise-grade compliance
## 10.7 Validation Rules

### Overview

Validation rules ensure that invoice data is complete, consistent, and compliant before any business operation is executed.

Validation occurs at multiple levels:

- Client-side (React)
- API Request Validation
- Business Validation
- Database Constraints

The backend always performs the final validation.

---

# 10.7.1 Customer Validation

| Field | Rule | Error Message |
|---------|------|---------------|
| customerId | Required | Customer is required. |
| customerId | Must exist | Customer not found. |
| customerId | Must belong to current tenant | Unauthorized customer. |
| customerId | Customer must be ACTIVE | Customer is inactive. |

---

# 10.7.2 Invoice Information Validation

| Field | Rule | Error Message |
|---------|------|---------------|
| invoiceDate | Required | Invoice date is required. |
| dueDate | Required | Due date is required. |
| dueDate | Must be greater than or equal to invoiceDate | Due date must be after invoice date. |
| currency | Required | Currency is required. |
| paymentTerms | Maximum 100 characters | Payment terms too long. |
| notes | Maximum 1000 characters | Notes exceed maximum length. |

---

# 10.7.3 Invoice Line Validation

Each invoice must contain at least one invoice line.

Maximum number of lines:

500

Validation Rules

| Field | Rule | Error Message |
|---------|------|---------------|
| description | Required | Description is required. |
| quantity | > 0 | Quantity must be greater than zero. |
| quantity | Maximum 999999 | Quantity exceeds allowed limit. |
| unitPrice | >= 0 | Invalid unit price. |
| discount | Between 0 and 100 | Invalid discount percentage. |
| vatRate | Valid VAT value | Invalid VAT rate. |

---

# 10.7.4 Product Validation

If Product Type is PRODUCT:

Product must exist.

Product must belong to current tenant.

Product must be ACTIVE.

Archived products cannot be invoiced.

---

# 10.7.5 Service Validation

If Product Type is SERVICE:

Service must exist.

Service must belong to current tenant.

Service must be ACTIVE.

---

# 10.7.6 Financial Validation

Automatic validation includes:

Subtotal ≥ 0

VAT ≥ 0

Total ≥ 0

Discount ≤ Subtotal

Remaining Balance ≥ 0

Calculated values must always match invoice lines.

---

# 10.7.7 Status Validation

Allowed statuses:

- DRAFT
- VALIDATED
- SENT
- PARTIALLY_PAID
- PAID
- OVERDUE
- CANCELLED
- ARCHIVED

Any other value is rejected.

---

# 10.7.8 Payment Validation

Payment Amount:

Greater than zero.

Cannot exceed Remaining Balance.

Payment Date:

Cannot precede Invoice Date.

Payment Method:

Must be one of:

- CASH
- BANK_TRANSFER
- CREDIT_CARD
- CHECK
- MOBILE_PAYMENT

---

# 10.7.9 File Validation

Generated PDF:

Maximum Size:

20 MB

Supported Format:

PDF

Only the system generates official invoice PDFs.

Manual upload is prohibited.

---

# 10.7.10 Security Validation

The API verifies:

Authenticated User

↓

Tenant Ownership

↓

User Permissions

↓

Invoice Existence

↓

Invoice Status

↓

Business Rules

↓

Execute Request

---

# 10.7.11 Validation Priority

Validation is executed in the following order:

1. Authentication

2. Authorization

3. Request Validation

4. Business Validation

5. Database Constraints

6. Business Execution

If any validation fails, the request stops immediately.

---

# 10.7.12 Standard Validation Response

Example

```json
{
  "success": false,
  "status": 400,
  "error": "Validation Error",
  "message": "Due date must be after invoice date.",
  "field": "dueDate",
  "timestamp": "2026-07-15T14:30:00Z"
}
```

---

# Validation Principles

The Invoice API follows these principles:

- Fail Fast
- Clear Error Messages
- Consistent Validation
- Secure Validation
- Predictable Behavior
- Business Rule Enforcement

Validation rules are mandatory for every invoice operation.
## 10.8 Permissions & Authorization (RBAC)

### Overview

The Invoice API implements Role-Based Access Control (RBAC).

Every operation performed on an invoice requires authentication and authorization.

Permissions are evaluated before executing any business operation.

Access decisions are based on:

- Authenticated User
- User Role
- Tenant Ownership
- Invoice Status
- Business Rules

---

# Supported Roles

The platform defines the following business roles:

- OWNER
- ADMIN
- MANAGER
- ACCOUNTANT
- SALES
- EMPLOYEE

Each role has different permissions.

---

# Permission Matrix

| Operation | Owner | Admin | Manager | Accountant | Sales | Employee |
|-----------|:-----:|:-----:|:-------:|:----------:|:-----:|:--------:|
| View Invoice | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ |
| Create Invoice | ✅ | ✅ | ✅ | ❌ | ✅ | ❌ |
| Update Draft Invoice | ✅ | ✅ | ✅ | ❌ | ✅ | ❌ |
| Delete Draft Invoice | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Validate Invoice | ✅ | ✅ | ❌ | ✅ | ❌ | ❌ |
| Generate PDF | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ |
| Send Invoice | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ |
| Register Payment | ✅ | ✅ | ❌ | ✅ | ❌ | ❌ |
| Cancel Invoice | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Archive Invoice | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Export Invoices | ✅ | ✅ | ✅ | ✅ | ❌ | ❌ |
| View Statistics | ✅ | ✅ | ✅ | ✅ | ❌ | ❌ |

---

# Ownership Rules

Users may access only invoices belonging to their own enterprise.

Every request verifies:

Invoice.tenant_id == AuthenticatedUser.tenant_id

Cross-tenant access is prohibited.

---

# Status-Based Authorization

Additional restrictions apply depending on invoice status.

DRAFT

- Editable
- Deletable
- Validatable

VALIDATED

- Read Only
- Send Allowed
- Cancel Restricted

SENT

- Read Only
- Payment Registration Allowed

PARTIALLY_PAID

- Payment Registration Allowed

PAID

- Read Only

CANCELLED

- Read Only

ARCHIVED

- Read Only

---

# Administrative Privileges

Owners and Administrators may:

- View all invoices within their tenant
- Archive invoices
- Restore archived invoices (future version)
- Export invoice data
- Manage invoice settings

They cannot bypass financial integrity rules.

---

# Authentication Requirements

Every request must include:

Authorization: Bearer <JWT>

Missing or invalid tokens result in:

HTTP 401 Unauthorized

---

# Authorization Flow

Authenticate User

↓

Validate JWT

↓

Identify Tenant

↓

Load User Role

↓

Check Required Permission

↓

Verify Invoice Ownership

↓

Verify Invoice Status

↓

Execute Business Logic

---

# Security Principles

The Invoice API follows:

- Least Privilege Principle
- Deny by Default
- Tenant Isolation
- Role-Based Access Control
- Secure by Design

---

# Audit Logging

Every sensitive operation is logged.

Examples:

- Invoice Created
- Invoice Updated
- Invoice Validated
- Invoice Sent
- Invoice Cancelled
- Invoice Deleted
- Invoice Exported

Each audit record includes:

- User ID
- Tenant ID
- Timestamp
- Operation
- Invoice ID
- IP Address
- User Agent

---

# Authorization Error Response

Example

```json
{
  "success": false,
  "status": 403,
  "error": "Forbidden",
  "message": "You do not have permission to validate this invoice.",
  "timestamp": "2026-07-15T15:45:00Z"
}
```

---

# Summary

The RBAC model ensures that every invoice operation is executed only by authorized users within their own enterprise.

This guarantees security, traceability, compliance, and tenant isolation across Moroccan Business OS.
## 10.9 Error Handling & Exception Management

### Overview

The Invoice API implements a standardized error handling strategy to ensure consistent, secure, and predictable responses.

Every exception is translated into a structured API response.

Internal implementation details are never exposed to clients.

---

# Error Handling Principles

The API follows these principles:

- Fail Fast
- Consistent Error Format
- Meaningful Messages
- Secure Responses
- Traceability
- Audit Logging

---

# Standard Error Response

Every error response follows the same JSON structure.

Example

```json
{
  "success": false,
  "timestamp": "2026-07-15T16:30:00Z",
  "status": 400,
  "errorCode": "INV-001",
  "error": "Validation Error",
  "message": "Customer is required.",
  "path": "/api/v1/invoices",
  "requestId": "req_7d91bc18"
}
```

---

# Invoice Error Codes

| Code | HTTP | Description |
|------|------|-------------|
| INV-001 | 400 | Customer is required |
| INV-002 | 400 | Invoice contains no lines |
| INV-003 | 400 | Due date is invalid |
| INV-004 | 400 | Invalid VAT rate |
| INV-005 | 404 | Invoice not found |
| INV-006 | 403 | Access denied |
| INV-007 | 409 | Invoice already validated |
| INV-008 | 409 | Invoice already paid |
| INV-009 | 409 | Invoice cannot be cancelled |
| INV-010 | 422 | Business rule violation |
| INV-011 | 500 | PDF generation failed |
| INV-012 | 500 | Email delivery failed |
| INV-013 | 500 | WhatsApp delivery failed |

---

# HTTP Status Codes

The Invoice API uses the following HTTP status codes.

| Code | Meaning |
|------|----------|
| 200 | Success |
| 201 | Resource Created |
| 204 | No Content |
| 400 | Validation Error |
| 401 | Authentication Required |
| 403 | Forbidden |
| 404 | Resource Not Found |
| 409 | Business Conflict |
| 422 | Business Validation Failed |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

---

# Validation Errors

Validation errors include field-level information.

Example

```json
{
  "success": false,
  "status": 400,
  "errorCode": "INV-001",
  "error": "Validation Error",
  "message": "Validation failed.",
  "fieldErrors": [
    {
      "field": "customerId",
      "message": "Customer is required."
    },
    {
      "field": "dueDate",
      "message": "Due date must be after invoice date."
    }
  ]
}
```

---

# Business Exceptions

Business exceptions occur when a request is technically valid but violates business rules.

Examples:

- Invoice already paid
- Invoice already cancelled
- Invoice already validated
- Payment exceeds remaining balance
- Customer inactive

These errors return HTTP 409 or HTTP 422 depending on the context.

---

# Authentication Errors

Returned when:

- JWT token missing
- JWT expired
- Invalid JWT
- Refresh token invalid

Response

HTTP 401 Unauthorized

---

# Authorization Errors

Returned when:

- User lacks required role
- Cross-tenant access detected
- Operation forbidden

Response

HTTP 403 Forbidden

---

# Resource Not Found

Returned when:

- Invoice does not exist
- Customer does not exist
- Product does not exist
- Service does not exist

Response

HTTP 404 Not Found

---

# Internal Errors

Unexpected server errors return:

HTTP 500

Clients receive a generic message.

Example

```json
{
  "success": false,
  "status": 500,
  "errorCode": "SYS-001",
  "error": "Internal Server Error",
  "message": "An unexpected error occurred. Please try again later."
}
```

Internal stack traces are never exposed.

---

# Logging Strategy

Every exception is logged.

Logged information includes:

- Timestamp
- User ID
- Tenant ID
- Invoice ID
- Endpoint
- HTTP Method
- Error Code
- Exception Type
- Stack Trace
- Request ID

Critical errors generate alerts for administrators.

---

# Global Exception Handler

The backend centralizes exception handling.

Typical exceptions include:

- ValidationException
- BusinessException
- ResourceNotFoundException
- AccessDeniedException
- AuthenticationException
- OptimisticLockException
- DatabaseException

Each exception maps to a standardized API response.

---

# Benefits

This strategy provides:

- Predictable API behavior
- Easier frontend integration
- Better debugging
- Improved observability
- Enhanced security
- Consistent user experience

---

# Summary

The Invoice API uses a centralized and standardized exception management strategy that guarantees reliable communication between clients and the backend while protecting sensitive implementation details.
## 10.10 Sequence Diagrams & End-to-End Business Scenarios

### Overview

This chapter describes how the Invoice API interacts with the different components of Moroccan Business OS during the complete invoice lifecycle.

The objective is to illustrate the runtime behavior of the system from the user's action to the final business outcome.

The following actors participate in the workflow:

- User
- React Frontend
- Spring Boot API
- Business Services
- PostgreSQL Database
- Object Storage
- Email Service
- WhatsApp Business API
- Notification Service
- Dashboard Service
- AI Service
- Audit Service

---

# Scenario 1 — Create Invoice

User

↓

Clicks "New Invoice"

↓

React sends POST /api/v1/invoices

↓

Spring Boot validates request

↓

Business Rules executed

↓

Invoice saved into PostgreSQL

↓

Draft Invoice returned

↓

React updates interface

Result

Invoice Status = DRAFT

---

# Scenario 2 — Validate Invoice

User

↓

Clicks "Validate"

↓

React

↓

POST /api/v1/invoices/{id}/validate

↓

Invoice Service

↓

Business Validation

↓

Generate Invoice Number

↓

Calculate Totals

↓

Create Audit Record

↓

Update Invoice Status

↓

Store Changes

↓

Return VALIDATED Invoice

---

# Scenario 3 — Generate PDF

User

↓

GET /api/v1/invoices/{id}/pdf

↓

Invoice Service

↓

Load Invoice

↓

Load Customer

↓

Load Invoice Lines

↓

Generate PDF

↓

Store PDF in Object Storage

↓

Return PDF Download

---

# Scenario 4 — Send Invoice by Email

User

↓

POST /invoices/{id}/send

↓

Invoice Service

↓

Load PDF

↓

Email Service

↓

SMTP Provider

↓

Customer receives Email

↓

Notification stored

↓

Audit Log created

↓

Invoice Status = SENT

---

# Scenario 5 — Send Invoice via WhatsApp

User

↓

POST /invoices/{id}/send

↓

Invoice Service

↓

Generate Secure Download Link

↓

WhatsApp Business API

↓

Customer receives WhatsApp Message

↓

Delivery Status Updated

↓

Audit Log Created

---

# Scenario 6 — Register Payment

Accountant

↓

POST /payments

↓

Payment Service

↓

Validate Payment

↓

Store Payment

↓

Update Remaining Balance

↓

Update Invoice Status

↓

Dashboard Updated

↓

AI Service Notified

↓

Notification Generated

---

# Scenario 7 — Overdue Invoice Detection

Scheduler

↓

Daily Background Job

↓

Search Due Invoices

↓

Remaining Balance > 0

↓

Current Date > Due Date

↓

Invoice Status

↓

OVERDUE

↓

Reminder Notification

↓

Dashboard Update

↓

AI Risk Analysis

---

# Scenario 8 — AI Financial Analysis

AI Service

↓

Reads Invoice Data

↓

Reads Payment History

↓

Calculates KPIs

↓

Detects Late Payments

↓

Generates Business Insights

↓

Displays Recommendations

---

# Scenario 9 — Dashboard Synchronization

Invoice Created

↓

Invoice Updated

↓

Invoice Paid

↓

Payment Registered

↓

Dashboard Service

↓

Refresh KPIs

↓

Refresh Charts

↓

Refresh Revenue

↓

Refresh Cash Flow

---

# Scenario 10 — Audit Logging

Every critical action triggers:

↓

Audit Service

↓

Store:

- User
- Tenant
- Action
- Resource
- Timestamp
- IP Address
- User Agent

Audit Logs cannot be modified.

---

# Runtime Interaction Summary

User

↓

React Frontend

↓

REST API

↓

Spring Boot

↓

Invoice Service

↓

Business Validation

↓

PostgreSQL

↓

PDF Generator

↓

Email / WhatsApp

↓

Dashboard

↓

AI Analysis

↓

Audit Log

↓

Response

↓

Frontend

↓

User

---

# Design Principles

The Invoice API follows these runtime principles:

- Stateless Communication
- Single Responsibility
- Secure Transactions
- Auditability
- Event-Based Updates
- Loose Coupling
- High Cohesion

Every operation must remain deterministic, traceable, and recoverable.

---

# Summary

The sequence diagrams demonstrate how invoice operations propagate through the Moroccan Business OS platform.

They ensure that every interaction between components remains predictable, secure, and aligned with the overall enterprise architecture.
## 10.11 OpenAPI Specification

### Overview

The Invoice API is fully documented using the OpenAPI 3.1 specification.

This enables automatic generation of interactive documentation, client SDKs, API testing collections, and developer tools.

The OpenAPI definition serves as the single source of truth for API consumers.

---

# OpenAPI Information

```yaml
openapi: 3.1.0

info:
  title: Moroccan Business OS API
  version: 1.0.0
  description: Enterprise REST API for invoice management.
```

---

# Server Configuration

```yaml
servers:
  - url: https://api.moroccanbusinessos.com/api/v1
    description: Production

  - url: https://staging-api.moroccanbusinessos.com/api/v1
    description: Staging

  - url: http://localhost:8080/api/v1
    description: Local Development
```

---

# Authentication

The API uses JWT Bearer Authentication.

```yaml
components:

  securitySchemes:

    bearerAuth:

      type: http

      scheme: bearer

      bearerFormat: JWT
```

Every secured endpoint requires:

Authorization

Bearer <JWT Token>

---

# Invoice Paths

The Invoice module exposes the following endpoints.

```yaml
/invoices

/invoices/{id}

/invoices/{id}/validate

/invoices/{id}/send

/invoices/{id}/pdf

/invoices/{id}/duplicate

/invoices/{id}/cancel

/invoices/{id}/payments

/invoices/statistics

/invoices/overdue

/invoices/export
```

---

# Invoice Schema

Example

```yaml
Invoice:

  type: object

  properties:

    id:

      type: string

      format: uuid

    invoiceNumber:

      type: string

    customerId:

      type: string

      format: uuid

    status:

      type: string

    totalAmount:

      type: number

      format: decimal

    currency:

      type: string

    createdAt:

      type: string

      format: date-time
```

---

# Request Schema

Example

```yaml
CreateInvoiceRequest:

  type: object

  required:

    - customerId

    - invoiceDate

    - dueDate

    - lines

  properties:

    customerId:

      type: string

      format: uuid

    invoiceDate:

      type: string

      format: date

    dueDate:

      type: string

      format: date

    notes:

      type: string

    lines:

      type: array
```

---

# Response Schema

```yaml
InvoiceResponse:

  type: object

  properties:

    success:

      type: boolean

    message:

      type: string

    data:

      $ref: '#/components/schemas/Invoice'
```

---

# Error Schema

```yaml
ErrorResponse:

  type: object

  properties:

    success:

      type: boolean

    status:

      type: integer

    errorCode:

      type: string

    message:

      type: string

    timestamp:

      type: string

      format: date-time
```

---

# Standard Responses

Every endpoint documents:

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 409 Conflict
- 422 Unprocessable Entity
- 500 Internal Server Error

---

# Tags

Endpoints are grouped using tags.

Example

```yaml
tags:

- Authentication

- Users

- Customers

- Suppliers

- Products

- Services

- Invoices

- Payments

- Expenses

- Dashboard

- Notifications

- AI
```

---

# Examples

Every endpoint provides:

- Request Example
- Response Example
- Validation Error Example
- Business Error Example

These examples are displayed automatically in Swagger UI.

---

# Documentation Standards

The OpenAPI specification must:

- Remain synchronized with the backend implementation.
- Use descriptive summaries.
- Include operation identifiers.
- Define request and response schemas.
- Document authentication requirements.
- Provide realistic examples.

The specification is version-controlled alongside the application source code.

---

# Benefits

Using OpenAPI provides:

- Interactive API documentation
- Automatic client generation
- Easier frontend integration
- Simplified API testing
- Consistent developer experience
- Faster onboarding for new developers

---

# Summary

The OpenAPI specification establishes a standardized, machine-readable contract between Moroccan Business OS and all API consumers.

It guarantees that documentation, implementation, and integrations remain aligned throughout the application's lifecycle.
## 10.12 Future Evolution & Versioning

### Overview

The Invoice API has been designed with extensibility in mind.

The initial version focuses on the core invoicing needs of Moroccan businesses while providing a flexible architecture that supports future enhancements without breaking existing integrations.

Future improvements will be introduced through API versioning and backward-compatible changes whenever possible.

---

# API Versioning Strategy

The Invoice API uses URI versioning.

Current Version

/api/v1/invoices

Future versions will follow the same pattern.

Examples

/api/v2/invoices

/api/v3/invoices

Older API versions remain supported during the transition period to avoid disrupting client applications.

---

# Version 2 Roadmap

The following features are planned for Version 2.

## Electronic Invoice Support

Support for Moroccan electronic invoicing regulations.

Automatic generation of compliant electronic invoices.

---

## Credit Notes

New endpoints:

POST /credit-notes

GET /credit-notes

Credit notes linked to original invoices.

---

## Recurring Invoices

Support for recurring billing.

Examples:

- Monthly subscription
- Annual maintenance contract
- Quarterly services

Automatic invoice generation according to a predefined schedule.

---

## Online Payments

Integration with payment gateways.

Examples:

- CMI
- PayPal
- Stripe
- Bank Transfer Confirmation

Customers will be able to pay invoices directly through a secure payment link.

---

## QR Code

Automatic QR Code generation on invoice PDFs.

The QR Code may contain:

- Invoice Number
- Company Information
- Customer Information
- Total Amount
- Verification URL

---

## Digital Signature

Support for digital signatures.

Digitally signed invoices improve authenticity and legal compliance.

---

# Version 3 Roadmap

Future long-term enhancements include:

## Multi-Currency

Support for multiple currencies.

Examples:

- MAD
- EUR
- USD
- GBP

Automatic exchange rate management.

---

## Multi-Language

Invoice generation in multiple languages.

Examples:

- Arabic
- French
- English
- Spanish

Language selection per customer.

---

## Accounting Integration

Synchronization with external accounting software.

Future connectors may include:

- Sage
- Odoo
- QuickBooks

---

## AI Enhancements

The AI Assistant will be able to:

- Detect abnormal invoices.
- Predict payment delays.
- Recommend payment reminders.
- Analyze customer payment behavior.
- Forecast monthly revenue.
- Suggest pricing improvements.

---

## Advanced Analytics

Additional KPIs:

- Customer Lifetime Value
- Average Payment Delay
- Monthly Recurring Revenue
- Outstanding Debt Ratio
- Invoice Conversion Rate

---

## Public API

Secure public API for third-party integrations.

OAuth 2.0 support.

API Keys.

Developer Portal.

Webhook subscriptions.

---

# Backward Compatibility

Breaking changes are avoided whenever possible.

When unavoidable:

- A new API version is created.
- Previous versions remain available during the migration period.
- Deprecation notices are published in advance.

---

# Deprecation Policy

Deprecated endpoints remain supported for a defined period.

Every deprecated endpoint includes:

- Deprecation notice
- Recommended replacement
- Planned removal date

This ensures a smooth migration for API consumers.

---

# Design Principles

Future versions of the Invoice API must preserve:

- Security
- Performance
- Tenant Isolation
- Financial Integrity
- Backward Compatibility
- Developer Experience

---

# Summary

The Invoice API has been designed as a scalable and future-proof service.

Its versioning strategy allows Moroccan Business OS to evolve continuously while maintaining compatibility with existing clients and integrations.
# 11. Payment API

## 11.1 Overview

The Payment API manages all financial transactions associated with customer invoices.

It provides a secure and reliable mechanism to register, validate, monitor, and report payments while maintaining complete financial traceability.

The Payment API integrates with the Invoice module, Dashboard, AI Assistant, Reporting, Notifications, and future Accounting modules.

Every payment belongs to exactly one enterprise and one invoice.

---

## 11.2 Business Purpose

The Payment API enables businesses to:

- Register customer payments.
- Record partial payments.
- Record full payments.
- Track outstanding balances.
- Associate payments with invoices.
- Maintain payment history.
- Generate financial reports.
- Update dashboard KPIs.
- Trigger customer notifications.
- Provide financial information to the AI Assistant.

Payments represent one of the most critical business operations within Moroccan Business OS.

Every payment must be secure, auditable, and traceable.

---

## 11.3 Payment Lifecycle

Every payment follows a predefined lifecycle.

Pending

↓

Validated

↓

Completed

↓

Archived

Alternative lifecycle

Pending

↓

Cancelled

Completed payments cannot be modified.

Archived payments are read-only.

---

## 11.4 Payment Workflow

Customer receives invoice

↓

Customer makes payment

↓

User registers payment

↓

System validates payment

↓

Payment stored

↓

Invoice balance updated

↓

Invoice status updated

↓

Dashboard refreshed

↓

Notification sent

↓

AI updated

↓

Audit log created

---

## 11.5 Supported Payment Methods

The platform supports:

- Cash
- Bank Transfer
- Credit Card
- Debit Card
- Mobile Payment
- Check

Future versions may support:

- Stripe
- PayPal
- CMI
- Apple Pay
- Google Pay

---

## 11.6 Business Principles

Every payment must:

- Belong to one invoice.
- Belong to one tenant.
- Have a positive amount.
- Be fully auditable.
- Respect financial integrity.
- Never exceed the remaining invoice balance.

Payments cannot exist without a valid invoice.
## 11.2 Payment API Endpoints

### Overview

The Payment API exposes a complete set of REST endpoints for managing customer payments.

All endpoints require authentication.

All operations are automatically restricted to the authenticated tenant.

Base URL

/api/v1/payments

---

# 1. Get All Payments

GET /api/v1/payments

Description

Returns a paginated list of payments.

Authorization

Owner

Admin

Accountant

Manager

Query Parameters

| Parameter | Type | Required | Description |
|------------|------|----------|-------------|
| page | Integer | No | Page number |
| size | Integer | No | Records per page |
| sort | String | No | Sort field |
| direction | String | No | ASC or DESC |
| invoiceId | UUID | No | Filter by invoice |
| customerId | UUID | No | Filter by customer |
| paymentMethod | String | No | Payment method |
| status | String | No | Payment status |
| startDate | Date | No | Start payment date |
| endDate | Date | No | End payment date |

Response

200 OK

---

# 2. Get Payment By ID

GET /api/v1/payments/{id}

Returns detailed payment information.

Possible Responses

200 OK

404 Payment Not Found

403 Forbidden

---

# 3. Register Payment

POST /api/v1/payments

Description

Registers a new payment for an invoice.

Authorization

Owner

Admin

Accountant

Request Body

Invoice ID

Payment Date

Payment Method

Amount

Reference

Notes

Response

201 Created

---

# 4. Update Payment

PUT /api/v1/payments/{id}

Only pending payments may be updated.

Response

200 OK

409 Payment Already Validated

---

# 5. Delete Payment

DELETE /api/v1/payments/{id}

Performs logical deletion.

Only pending payments may be deleted.

Response

204 No Content

---

# 6. Validate Payment

POST /api/v1/payments/{id}/validate

Description

Validates a payment.

Actions

Verify amount.

Update invoice balance.

Update invoice status.

Create audit record.

Refresh dashboard.

---

# 7. Cancel Payment

POST /api/v1/payments/{id}/cancel

Allowed only before payment completion.

Completed payments cannot be cancelled.

---

# 8. Refund Payment (Future)

POST /api/v1/payments/{id}/refund

Available in Version 2.

Creates refund transaction.

Keeps original payment unchanged.

---

# 9. Export Payments

GET /api/v1/payments/export

Formats

PDF

Excel

CSV

---

# 10. Payment Statistics

GET /api/v1/payments/statistics

Returns

Total Payments

Total Revenue

Average Payment

Pending Payments

Completed Payments

Cancelled Payments

Payment Methods Distribution

Monthly Revenue

---

# Endpoint Summary

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /payments | List payments |
| GET | /payments/{id} | Payment details |
| POST | /payments | Register payment |
| PUT | /payments/{id} | Update payment |
| DELETE | /payments/{id} | Delete payment |
| POST | /payments/{id}/validate | Validate payment |
| POST | /payments/{id}/cancel | Cancel payment |
| POST | /payments/{id}/refund | Refund payment (V2) |
| GET | /payments/export | Export payments |
| GET | /payments/statistics | Payment statistics |
## 11.3 Request Models & Response Models

### Overview

This section defines the JSON models exchanged between clients and the Payment API.

The models are designed to provide a consistent, secure, and extensible communication format.

All requests and responses use:

- application/json
- UTF-8 encoding
- ISO 8601 timestamps
- UUID identifiers

---

# Register Payment Request

POST /api/v1/payments

```json
{
  "invoiceId": "uuid",
  "paymentDate": "2026-07-20",
  "paymentMethod": "BANK_TRANSFER",
  "amount": 3500.00,
  "reference": "TRX-987654321",
  "notes": "Bank transfer received."
}
```

---

# Register Payment Response

HTTP 201 Created

```json
{
  "id": "uuid",
  "invoiceId": "uuid",
  "status": "PENDING",
  "paymentMethod": "BANK_TRANSFER",
  "amount": 3500.00,
  "currency": "MAD",
  "paymentDate": "2026-07-20",
  "createdAt": "2026-07-20T09:15:00Z"
}
```

---

# Payment Details Response

GET /api/v1/payments/{id}

```json
{
  "id": "uuid",
  "invoiceId": "uuid",
  "invoiceNumber": "FAC-2026-000021",
  "customer": {
    "id": "uuid",
    "companyName": "ABC SARL"
  },
  "paymentMethod": "BANK_TRANSFER",
  "reference": "TRX-987654321",
  "amount": 3500.00,
  "currency": "MAD",
  "status": "COMPLETED",
  "paymentDate": "2026-07-20",
  "validatedBy": "uuid",
  "createdAt": "2026-07-20T09:15:00Z",
  "updatedAt": "2026-07-20T09:20:00Z"
}
```

---

# Update Payment Request

PUT /api/v1/payments/{id}

```json
{
  "paymentMethod": "CASH",
  "reference": "Cash Payment",
  "notes": "Updated payment information."
}
```

Only pending payments may be updated.

---

# Validate Payment Response

POST /payments/{id}/validate

```json
{
  "message": "Payment validated successfully.",
  "status": "COMPLETED",
  "remainingBalance": 1500.00,
  "invoiceStatus": "PARTIALLY_PAID"
}
```

---

# Payment Statistics Response

GET /payments/statistics

```json
{
  "totalPayments": 1250,
  "completedPayments": 1180,
  "pendingPayments": 45,
  "cancelledPayments": 25,
  "totalRevenue": 3850000.00,
  "averagePayment": 3080.00,
  "currency": "MAD"
}
```

---

# Standard Success Response

```json
{
  "success": true,
  "message": "Operation completed successfully.",
  "data": {}
}
```

---

# Standard Error Response

```json
{
  "success": false,
  "status": 400,
  "errorCode": "PAY-001",
  "error": "Validation Error",
  "message": "Payment amount exceeds remaining balance.",
  "timestamp": "2026-07-20T09:25:00Z",
  "path": "/api/v1/payments"
}
```

---

# Response Principles

Every response must:

- Use camelCase properties.
- Return ISO 8601 timestamps.
- Return monetary values with two decimal places.
- Never expose internal server details.
- Use standardized HTTP status codes.

---

# Design Benefits

These request and response models provide:

- Consistent frontend integration
- Strong typing
- Easier API testing
- Better documentation
- Reusable DTOs
- OpenAPI compatibility