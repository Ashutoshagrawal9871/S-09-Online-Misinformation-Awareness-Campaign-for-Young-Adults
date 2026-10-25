# TruthLens (S-09) — Testing Report

## 1. Scope
Functional, integration, and smoke testing for the TruthLens Spring Boot backend and interactive campaign website before CUSoC final evaluation.

## 2. Environment
| Item | Value |
| :--- | :--- |
| JDK | Java 17+ |
| Framework | Spring Boot 3.4.3 |
| Database (local) | H2 in-memory (`jdbc:h2:mem:truthlens_db`) |
| Build | Maven Wrapper (`./mvnw`) |
| Browser smoke | Chrome / Edge |

## 3. Automated Test Suite
Command:

```bash
./mvnw.cmd clean test
```

| Test Class | Focus | Tests | Result |
| :--- | :--- | :---: | :--- |
| `InterviewerBackendApplicationTests` | Spring context loads | 1 | PASS |
| `SecurityConfigTest` | Public API & Swagger access rules | 3 | PASS |
| `AuthAndJwtIntegrationTest` | Seeded admin login, JWT enforcement, bad credentials, role-escalation guard | 5 | PASS |
| `FactCheckControllerTest` | SIFT verify & recent-queries endpoints | 2 | PASS |
| `CampaignControllerTest` | Modules & survey APIs | 2 | PASS |
| `WorkshopControllerTest` | Workshop list & registration | 2 | PASS |
| `AnalyticsControllerTest` | Impact overview API | 1 | PASS |
| `CampaignServiceTest` | Survey scoring & module service logic | 2 | PASS |
| `WorkshopServiceTest` | Capacity limit, duplicate email, counter update | 3 | PASS |

**Suite status:** **21 tests run, 0 failures, 0 errors** (`BUILD SUCCESS`, 2026-10-26).

## 4. Manual Functional Cases

| ID | Feature | Steps | Expected | Status |
| :--- | :--- | :--- | :--- | :--- |
| TC-01 | Landing page | Open `http://localhost:8080` | Hero, KPI strip, sections render | PASS |
| TC-02 | SIFT Analyzer | Paste sample claim → Verify | Verdict, confidence, 4 SIFT steps | PASS |
| TC-03 | Learning modules | Load modules / filter tabs | Cards from API with working doc links | PASS |
| TC-04 | Susceptibility quiz | Submit baseline answers | Score shown; survey stored | PASS |
| TC-05 | Workshop register | Register with name/email/college | `200` + enrollment increments | PASS |
| TC-06 | Analytics refresh | Click Refresh Analytics | Live KPI values from `/api/v1/analytics/overview` | PASS |
| TC-07 | Docs links | Open `/docs/*.md` from UI | Markdown files served (no 404) | PASS |
| TC-08 | Swagger UI | Open `/swagger-ui.html` and `/v3/api-docs` | UI loads 21 operations; spec returns `200` | PASS |
| TC-09 | Auth | `POST /api/v1/auth/register` + login | JWT issued | PASS |
| TC-10 | Mobile nav | Resize / phone viewport | Hamburger menu opens sections | PASS |
| TC-11 | Workshop full state | Register for a workshop at `maxCapacity` | `400` with “full capacity” message | PASS |
| TC-12 | Duplicate workshop email | Register the same email twice | `400` with “already registered” message | PASS |
| TC-13 | JWT enforcement | `GET /api/v1/campaign/surveys` without token / with admin token | `403` without token, `200` with token | PASS |
| TC-14 | Campaign materials | Open `#campaign-assets` download links | Four SVG assets served from `/assets/**` | PASS |
| TC-15 | Resources & FAQ | Open `#resources` links and FAQ accordions | All 17 docs resolve; FAQ expands/collapses | PASS |
| TC-16 | Analyzer samples | Load all three sample claims | Each renders verdict, confidence, SIFT steps | PASS |
| TC-17 | Role escalation guard | Register with `"role":"ADMIN"` | Account is created as `STUDENT` | PASS |

## 5. Defects Fixed Before Final Evaluation
1. Frontend workshop fields mismatched API (`sessionDate` vs `scheduledAt`).
2. Analytics UI looked for wrong JSON keys (`totalReach` vs `totalImpressions`).
3. Workshop registration payload used wrong DTO property names.
4. Survey submit used wrong field names / non-integer verification frequency.
5. Documentation links pointed to repo-root paths that were not served by Spring static resources.
6. `CustomUserDetailsService` only recognised two hardcoded users instead of database accounts.
7. JWTs were issued but never validated; added `JwtAuthenticationFilter` and admin-only route rules.
8. Workshop registration accepted over-capacity and duplicate-email requests.
9. Invalid credentials and duplicate registrations returned `500` instead of `400`.
10. Duplicate documentation copies under `static/docs` could drift; repository folders are now copied into the build by Maven.
11. Public registration accepted a caller-supplied `role`, allowing self-registration as `ADMIN`; it now always creates a `STUDENT`.
12. Login had a plaintext password fallback; authentication now uses BCrypt only.
13. Swagger UI returned `500` because springdoc 2.6.0 was incompatible with Spring Boot 3.4.3; upgraded to 2.8.9.
14. Root-level docs and test links returned `403`; the security rules now permit the served static paths.

## 6. Known Limitations
- Fact-check engine uses heuristic matching (demo-grade), not a live third-party fact-check API.
- Local default DB is H2 (data resets on restart). PostgreSQL is supported via environment variables for cloud deploy.
- Markdown docs open as plain text in the browser (intentional for lightweight delivery).

## 7. Sign-off
Project is verified ready for CUSoC final demonstration with automated tests green and critical UI↔API integrations corrected.
