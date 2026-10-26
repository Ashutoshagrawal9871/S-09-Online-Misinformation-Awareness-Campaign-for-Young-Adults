# Installation & Setup Guide

TruthLens (S-09) is a Spring Boot 3.4.3 application with a static single-page campaign website. This guide covers local setup, configuration, testing, packaging, and deployment.

## 1. Prerequisites

| Requirement | Version | Notes |
| :--- | :--- | :--- |
| JDK | 17 or newer | `java -version` must report 17+ |
| Maven | Not required globally | Use the included Maven Wrapper (`mvnw` / `mvnw.cmd`) |
| Browser | Chrome, Edge, Firefox, or Safari | Modern browser with JavaScript enabled |
| Docker | Optional | Only for container deployment |

No external database, API key, or paid service is required for the default demo.

## 2. Quick start (H2 in-memory)

```bash
git clone https://github.com/Ashutoshagrawal9871/S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults.git
cd S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults

# macOS / Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Open <http://localhost:8080>. The database is created and seeded automatically on startup by `DataInitializer`.

### URLs

| Resource | URL |
| :--- | :--- |
| Campaign website | <http://localhost:8080> |
| Swagger UI | <http://localhost:8080/swagger-ui.html> |
| OpenAPI JSON | <http://localhost:8080/v3/api-docs> |
| H2 console | <http://localhost:8080/h2-console> |
| Evaluation slides | <http://localhost:8080/presentation/FINAL_EVALUATION_SLIDES.html> |
| Health/status API | <http://localhost:8080/api/status> |

H2 console settings: JDBC URL `jdbc:h2:mem:truthlens_db`, user `sa`, blank password.

## 3. Run the automated tests

```bash
# macOS / Linux
./mvnw clean test

# Windows
mvnw.cmd clean test
```

Expected result: **21 tests, 0 failures, 0 errors** (see [TESTING_REPORT.md](TESTING_REPORT.md)).

## 4. Build a runnable JAR

```bash
./mvnw clean package
java -jar target/truthlens-0.0.1-SNAPSHOT.jar
```

During the Maven build, `docs/`, `report/`, `presentation/`, `assets/`, `media/`, and `tests/README.md` are copied into the servable static bundle. This keeps a single source of truth in the repository while making the files available at `/docs/**`, `/report/**`, `/presentation/**`, and `/assets/**`. Automated tests compile from `tests/java` (configured via `testSourceDirectory` in `pom.xml`).

## 5. Configuration

The application reads the following environment variables. Defaults are suitable for local development.

| Variable | Default | Purpose |
| :--- | :--- | :--- |
| `PORT` | `8080` | HTTP port |
| `SPRING_DATASOURCE_URL` (or `DB_URL`) | H2 in-memory URL | JDBC connection string |
| `SPRING_DATASOURCE_USERNAME` (or `DB_USERNAME`) | `sa` | Database user |
| `SPRING_DATASOURCE_PASSWORD` (or `DB_PASSWORD`) | blank | Database password |
| `SPRING_DATASOURCE_DRIVER_CLASS_NAME` | `org.h2.Driver` | JDBC driver |
| `SPRING_JPA_DATABASE_PLATFORM` | `org.hibernate.dialect.H2Dialect` | Hibernate dialect |
| `H2_CONSOLE_ENABLED` | `true` | Set to `false` in production |
| `JWT_SECRET` | demo Base64 secret | **Override in production** with a unique Base64 key of at least 32 bytes |

A template is provided in `.env.example`. Never commit real credentials.

## 6. PostgreSQL (optional, persistent)

```bash
export SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/truthlens_db"
export SPRING_DATASOURCE_USERNAME="truthlens_user"
export SPRING_DATASOURCE_PASSWORD="your_secure_password"
export SPRING_DATASOURCE_DRIVER_CLASS_NAME="org.postgresql.Driver"
export SPRING_JPA_DATABASE_PLATFORM="org.hibernate.dialect.PostgreSQLDialect"
export H2_CONSOLE_ENABLED="false"
export JWT_SECRET="<unique-base64-secret>"
./mvnw spring-boot:run
```

Hibernate runs with `ddl-auto=update`, so the schema is created on first connection. A full table reference is available in [DATABASE_SCHEMA.md](DATABASE_SCHEMA.md).

## 7. Docker and Render

```bash
docker build -t truthlens-s09 .
docker run -p 8080:8080 -e H2_CONSOLE_ENABLED=false truthlens-s09
```

The repository includes `Dockerfile` and `render.yaml` for a free Render web service. Step-by-step instructions are in [DEPLOYMENT_RENDER.md](DEPLOYMENT_RENDER.md) (Railway alternative: [DEPLOYMENT_RAILWAY.md](DEPLOYMENT_RAILWAY.md)). Note that free instances sleep after idle periods; open the URL a few minutes before a demo.

## 8. Seeded demo accounts

| Username | Email | Password | Role |
| :--- | :--- | :--- | :--- |
| `admin` | `admin@truthlens.org` | `admin123` | `ADMIN` |
| `priya_sharma` | `priya.sharma@university.edu` | `password123` | `PEER_MULTIPLIER` |
| `rohit_verma` | `rohit.verma@college.ac.in` | `password123` | `STUDENT` |

These accounts exist only for the demo and are recreated when the H2 database starts empty. Change or remove them before any real deployment.

## 9. Troubleshooting

| Symptom | Likely cause | Fix |
| :--- | :--- | :--- |
| `Port 8080 is already in use` | Another process is listening | Stop it or run with `PORT=8081 ./mvnw spring-boot:run` |
| `Unsupported class file version` | Older JDK | Install JDK 17+ and set `JAVA_HOME` |
| `mvnw: permission denied` | Wrapper not executable | `chmod +x mvnw` |
| Site loads without styling | CDN blocked | Check network access to Tailwind/Font Awesome/Google Fonts CDNs |
| API calls fail, page still works | Backend not running | Start the app; the UI has a client-side SIFT fallback |
| Workshop registration says email already registered | Duplicate guard | Use a different email or restart the in-memory database |
| H2 console blank | H2 console disabled | Set `H2_CONSOLE_ENABLED=true` |

## 10. Repository structure

```
S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults/
├── README.md                  # Project overview and deliverables
├── LICENSE                    # CC BY-SA 4.0
├── docs/                      # Guides, API, architecture, schema, testing report, changelog, project plan
├── src/                       # Spring Boot application
│   ├── main/java/             # Controllers, services, repositories, entities
│   └── main/resources/static/ # Website (index.html)
├── tests/                     # Automated test suite
│   └── java/                  # JUnit 5 / MockMvc tests (Maven testSourceDirectory)
├── assets/                    # Campaign posters and favicon (served at /assets)
├── media/                     # Field evidence placeholder (photos/videos)
├── presentation/              # Final evaluation slides
├── report/                    # Technical, impact, survey, analytics reports and policy brief
├── pom.xml, mvnw, mvnw.cmd    # Maven build and wrapper
├── Dockerfile                 # Container build
├── render.yaml                # Render blueprint
└── railway.json               # Railway configuration
```
