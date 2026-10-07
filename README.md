# Motorcycles API

[English](README.md) | [Português](README.pt-BR.md)

A full-stack motorcycle catalog application built with Spring Boot, PostgreSQL, and vanilla JavaScript.

The project provides a REST API for managing motorcycle data, a responsive web interface for browsing the catalog, session-based authentication for administrative operations, and interactive API documentation with Swagger UI.

## Features

- Motorcycle catalog with detailed technical specifications
- Search and sorting by name, release year, and displacement
- Responsive frontend built with HTML, CSS, and JavaScript
- RESTful CRUD API
- PostgreSQL persistence with Spring Data JPA
- Input validation and structured error responses
- Session-based administrator authentication
- CSRF protection
- Interactive API documentation with Swagger UI

## Web Interface

Users can **Log in** to authenticate their session and access **Swagger Docs**, using administrator role privileges to perform protected API operations.

```text
Public user
│
└── Catalog → Search / Sort / Details

Administrator
│
├── Log in → authenticated session
│
└── Swagger Docs → POST / PUT / DELETE
```

## Technologies

**Backend**
- Java 25
- Spring Boot 4.1
- Spring Web
- Spring Data JPA
- Spring Security
- Hibernate
- PostgreSQL
- Maven

**Frontend**
- HTML
- CSS
- JavaScript

**Documentation**
- OpenAPI
- Swagger UI

## API

| Method | Endpoint | Description | Access |
| --- | --- | --- | --- |
| GET | `/api/auth/status` | Check authentication status | Public |
| GET | `/api/motorcycles` | List motorcycles | Public |
| GET | `/api/motorcycles/{id}` | Get motorcycle details | Public |
| POST | `/api/motorcycles` | Create a motorcycle | Admin |
| PUT | `/api/motorcycles/{id}` | Update a motorcycle | Admin |
| DELETE | `/api/motorcycles/{id}` | Delete a motorcycle | Admin |


## Authentication

The application uses Spring Security with session-based authentication.

Reading the catalog is public. Creating, updating, and deleting motorcycles requires an authenticated administrator.

CSRF protection is enabled for authenticated operations.

## Configuration

The application requires PostgreSQL and the following environment variables:

```text
DB_PASSWORD
ADMIN_USERNAME
ADMIN_PASSWORD
```

The database configuration expects:

```text
Database: motorcycles_db
Port: 5432
```

For detailed setup instructions, see the [Environment Configuration](https://github.com/diego-gin/Motorcycles-API/wiki/Environment-Variables) guide.

## Running the Project

Clone the repository:

```bash
git clone https://github.com/diego-gin/Motorcycles-API.git
cd Motorcycles-API
```

Create the PostgreSQL database:

```sql
CREATE DATABASE motorcycles_db;
```

Set the required environment variables: [See the guide here.](https://github.com/diego-gin/Motorcycles-API/wiki/Environment-Variables)

And start the application:

**Windows**

```powershell
.\mvnw spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

Then open:

```text
http://localhost:8080
```

## Sample Data

The project includes an optional dataset of **30 motorcycles** for development and demonstration purposes.

The sample data is stored in `database/seed.sql`, with corresponding motorcycle images located in `src/main/resources/static/images/motorcycles/`.

### Importing the Dataset

Make sure PostgreSQL is running and the `motorcycles_db` database and its tables have been created.

Start the application at least once to allow Hibernate to create the required database tables before importing the dataset.

Run the following command from the project root:

```bash
psql -U postgres -d motorcycles_db -f database/seed.sql
```

The seed script uses UTF-8 encoding and populates the motorcycle catalog with technical specifications and image paths.

> **Note:** The dataset is intended for development and demonstration. Review the SQL script before running it against a database containing existing records.

The application can run without the sample dataset.

## API Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

Public endpoints can be tested directly. After logging in as an administrator, protected operations can also be executed through Swagger UI using the authenticated browser session.

## Architecture

```text
Frontend
HTML / CSS / JavaScript
        │
        ├── Spring Security
        │
        ├── REST Controller
        │
        ├── Service
        │
        ├── Repository
        │
        └── JPA / Hibernate
                    │
                PostgreSQL
```

## License

This project is intended for academic and portfolio purposes.