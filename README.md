# URL-shortener

## Spring Boot web-application for generating, storing and managin

### New link generation:

Request to `/generate` providing original url to field `original` in request body returns short url. Example

```http request
POST /generate
```

Request body example:
```json
  {
    "original": "https://some-server.com/some/url?some_param=1"
  }
```

Answer example:
```json
  {
    "link": "/l/aKL4n"
  }
```

### Redirecting

Request to short url redirects to original url. Example:

```http request
GET /l/aKL4n
```

### Design notes

Application made with Spring Boot, Spring MVC, Spring Data JPA.

Data layer made with Hibernate, DBMS is PostgresSQL, that runs in Docker container.

DB schema creates and manages with Liquibase.

Short link generation implemented with Base64 conversion from Long id to String.
