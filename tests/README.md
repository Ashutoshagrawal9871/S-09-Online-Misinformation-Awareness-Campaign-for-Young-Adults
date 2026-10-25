# Tests

The automated test suite follows the standard Maven layout and lives in [`src/test/java`](https://github.com/Ashutoshagrawal9871/S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults/tree/main/src/test/java). This folder exists so the repository matches the CUSoC recommended structure (`src/`, `tests/`, `docs/`, ...).

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
