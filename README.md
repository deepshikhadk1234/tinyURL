# TinyURL Service

A URL shortening service built with Spring Boot that supports both PostgreSQL and in-memory storage.

## Features

- Shorten long URLs to compact, unique URLs
- Support for both PostgreSQL and in-memory storage
- RESTful API endpoints
- Configurable storage type through properties
- Input validation
- Error handling
- Base62 encoding for short URLs

## Technologies

- Java 17
- Spring Boot 3.x
- PostgreSQL
- Gradle
- JPA/Hibernate
- Lombok

## Prerequisites

- Java 17 or higher
- PostgreSQL 12 or higher
- Gradle 7.x or higher

## Setup

1. **Clone the repository**
```bash
git clone https://github.com/deepshikhadk1234/tinyurl.git
cd tinyurl
```

2. **Configure PostgreSQL**
```sql
CREATE DATABASE tinyurl;
```

3. **Configure application.properties**

The application uses environment variables for sensitive data. Create a `.env` file or set the following environment variables:
```properties
DB_PASSWORD=your_database_password
```

Update `src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tinyurl
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}

# Choose storage type: postgres or in_memory
storage.type=postgres
```

4. **Build the project**
```bash
./gradlew clean build
```

5. **Run the application**
```bash
./gradlew bootRun
```

## API Endpoints

### 1. Shorten URL
```http
POST /api/v1/shorten
Content-Type: application/json

{
    "longURL": "https://www.example.com/very/long/url"
}
```

Response:
```json
{
    "shortUrl": "http://localhost:8080/api/v1/abc123",
    "errorMessage": null
}
```

### 2. Redirect to Original URL
```http
GET /api/v1/{shortURL}
```

Response:
- 302 Found with Location header containing the original URL
- 404 Not Found if short URL doesn't exist

## Storage Types

The application supports two storage types:
1. **PostgreSQL** (`storage.type=postgres`)
   - Persistent storage
   - Suitable for production use
   - Supports high concurrency

2. **In-Memory** (`storage.type=in_memory`)
   - Non-persistent storage
   - Suitable for testing/development
   - Data is lost on application restart

To change storage type, update `storage.type` in `application.properties`.

## Database Schema

```sql
CREATE TABLE url_mappings (
    id BIGSERIAL PRIMARY KEY,
    long_url VARCHAR(2048) NOT NULL UNIQUE,
    short_url VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL
);
```

## Error Handling

The API handles several types of errors:
- Invalid URLs
- Duplicate URLs
- Non-existent short URLs
- Already shortened URLs
- Malformed requests

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Spring Boot team for the amazing framework
- Base62 encoding implementation inspiration from various URL shortening services 
