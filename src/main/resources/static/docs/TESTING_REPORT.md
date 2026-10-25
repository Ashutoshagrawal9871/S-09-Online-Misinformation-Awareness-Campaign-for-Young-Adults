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

| Test Class | Focus | Result |
| :--- | :--- | :--- |
| `InterviewerBackendApplicationTests` | Context loads | PASS |
| `SecurityConfigTest` | Public API & Swagger access rules | PASS |
| `FactCheckControllerTest` | SIFT verify endpoint | PASS |
| `CampaignControllerTest` | Modules & survey APIs | PASS |
| `WorkshopControllerTest` | Workshop list & registration | PASS |
| `AnalyticsControllerTest` | Impact overview API | PASS |
| `CampaignServiceTest` | Survey scoring & module service logic | PASS |

**Suite status:** All tests passing.

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
| TC-08 | Swagger UI | Open `/swagger-ui.html` | Interactive OpenAPI docs | PASS |
| TC-09 | Auth | `POST /api/v1/auth/register` + login | JWT issued | PASS |
| TC-10 | Mobile nav | Resize / phone viewport | Hamburger menu opens sections | PASS |

## 5. Defects Fixed Before Final Evaluation
1. Frontend workshop fields mismatched API (`sessionDate` vs `scheduledAt`).
2. Analytics UI looked for wrong JSON keys (`totalReach` vs `totalImpressions`).
3. Workshop registration payload used wrong DTO property names.
4. Survey submit used wrong field names / non-integer verification frequency.
5. Documentation links pointed to repo-root paths that were not served by Spring static resources.

## 6. Known Limitations
- Fact-check engine uses heuristic matching (demo-grade), not a live third-party fact-check API.
- Local default DB is H2 (data resets on restart). PostgreSQL is supported via environment variables for cloud deploy.
- Markdown docs open as plain text in the browser (intentional for lightweight delivery).

## 7. Sign-off
Project is verified ready for CUSoC final demonstration with automated tests green and critical UI↔API integrations corrected.
