# CompanyOS – Enterprise Management System

**CompanyOS** is a Java-based enterprise management system built with Spring Boot and PostgreSQL. Its goal is to provide a centralised platform for managing organisational information and business operations.

The project is currently under active development.

## Overview

CompanyOS aims to simplify organisational operations through a modular backend architecture, structured database management and RESTful APIs.

The initial version focuses on a single-company architecture.

## Technology Stack

| Technology | Version |
|---|---|
| Java | 25 |
| Spring Boot | 4.1.1 |
| PostgreSQL | 18.6 |
| Build Tool | Maven |
| Database | company_os |
| IDE | IntelliJ IDEA |

## Current Implementation

- Spring Boot application setup.
- PostgreSQL database connectivity.
- Initial Company entity and repository.
- Database migration using SQL migration scripts.
- Application health-check endpoint.
- Basic project and package structure.
- Maven-based build configuration.

## Planned Modules

The following modules are planned for future development:

- **Organisation Management:** Company and organisational information.
- **Employee Management:** Employee profiles and records.
- **Department Management:** Department and designation management.
- **Authentication:** Secure login and role-based access control.
- **HR Management:** Attendance and leave management.
- **Project Management:** Project creation, assignment and tracking.
- **Team Management:** Team organisation and employee allocation.
- **Dashboard & Analytics:** Organisational insights and reports.

## Project Structure

```text
CompanyOS/
│
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/companyos/
│   │   │       ├── CompanyOsApplication.java
│   │   │       └── company/
│   │   │           ├── Company.java
│   │   │           └── CompanyRepository.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── migration/
│   │               └── V1__create_companies_table.sql
│   │
│   └── test/
│       └── java/
│
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 25
- PostgreSQL 18.6
- IntelliJ IDEA (recommended)
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/vibhu222005/Company_OS.git
```

### 2. Navigate to the Project

```bash
cd Company_OS
```

### 3. Configure PostgreSQL

Create a PostgreSQL database:

```sql
CREATE DATABASE company_os;
```

Configure your database connection in `application.properties`.

Use