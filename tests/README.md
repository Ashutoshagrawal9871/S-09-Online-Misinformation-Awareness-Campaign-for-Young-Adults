# Tests

The automated test suite lives in this folder, in [`tests/java`](https://github.com/Ashutoshagrawal9871/S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults/tree/main/tests/java), following the CUSoC recommended structure (`src/`, `tests/`, `docs/`, ...). Maven is configured with `<testSourceDirectory>tests/java</testSourceDirectory>`, so the standard commands below compile and run it.

```
tests/
├── README.md
└── java/com/ashutosh/interviewer/
    ├── InterviewerBackendApplicationTests.java
    ├── config/SecurityConfigTest.java
    ├── controller/   (Analytics, Campaign, FactCheck, Workshop)
    ├── security/AuthAndJwtIntegrationTest.java
    └── service/      (CampaignServiceTest, WorkshopServiceTest)
```

## Run the suite

```bash
# macOS / Linux
./mvnw clean test

# Windows
mvnw.cmd clean test
```

Expected: **21 tests, 0 failures, 0 errors**.

## Test inventory

| Test class | Purpose |
| :--- | :--- |
| `InterviewerBackendApplicationTests` | Spring context loads |
| `SecurityConfigTest` | Public routes and protected-route behaviour |
| `AuthAndJwtIntegrationTest` | Seeded admin login, JWT enforcement, bad credentials, role-escalation guard |
| `FactCheckControllerTest` | SIFT verify and recent-query endpoints |
| `CampaignControllerTest` | Module listing and survey submission |
| `WorkshopControllerTest` | Workshop listing and registration |
| `AnalyticsControllerTest` | KPI overview endpoint |
| `CampaignServiceTest` | Survey and module service logic |
| `WorkshopServiceTest` | Capacity, duplicate email, and registration counter rules |

## Where results live

- Raw Surefire output: `target/surefire-reports/`
- Human-readable summary: [docs/TESTING_REPORT.md](../docs/TESTING_REPORT.md)
- Manual UI cases and known limitations: same report

## Notes

- Tests run against H2 and do not require external services.
- Controller tests use `@MockitoBean` for service layers, so they verify routing, validation, and serialization without touching the database.
- If you add a new endpoint, add at least one MockMvc test and update `docs/TESTING_REPORT.md`.
