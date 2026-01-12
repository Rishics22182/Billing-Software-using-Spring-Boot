# Billing Software Backend (Spring Boot)

## Project Overview

This project is a Billing Software Backend developed using Spring Boot,
following a proper Layered Architecture:

Controller → Service → Repository → Database

The system provides REST APIs to manage products, customers, and
generate invoices with GST calculations. APIs are tested using Postman.

------------------------------------------------------------------------

## Tech Stack

-   Java 17\
-   Spring Boot\
-   Spring Web\
-   Spring Data JPA\
-   Maven\
-   Postman (API Testing)

------------------------------------------------------------------------

## Project Structure

billing-software │ ├── controller │ ├── ProductController.java │ ├──
CustomerController.java │ ├── InvoiceController.java │ ├── service │ ├──
ProductService.java │ ├── CustomerService.java │ ├── InvoiceService.java
│ ├── InvoiceServiceImpl.java │ ├── repository │ ├──
ProductRepository.java │ ├── CustomerRepository.java │ ├──
InvoiceRepository.java │ ├── entity │ ├── Product.java │ ├──
Customer.java │ ├── Invoice.java │ ├── InvoiceItem.java │ ├── dto │ ├──
InvoiceRequestDTO.java │ ├── InvoiceResponseDTO.java │ ├── exception │
├── ResourceNotFoundException.java │ └── BillingSoftwareApplication.java

------------------------------------------------------------------------

## Modules Implemented

### Product Management

-   Add, update, delete and fetch products
-   Handles product price, GST and stock

### Customer Management

-   Add and fetch customers
-   Stores customer details

### Invoice / Billing Module

-   Generate invoices
-   Calculate GST dynamically
-   Store invoice and invoice items

------------------------------------------------------------------------

## Database Tables

-   products\
-   customers\
-   invoices\
-   invoice_items

------------------------------------------------------------------------

## API Testing

All APIs are tested using Postman: - Product CRUD operations\
- Customer CRUD operations\
- Invoice generation and retrieval

------------------------------------------------------------------------

## How to Run

1.  Clone the repository
2.  Open project in IntelliJ / Eclipse
3.  Run BillingSoftwareApplication.java
4.  Test APIs using Postman

------------------------------------------------------------------------

## Author

Rishi Gupta B.Tech Computer Science
