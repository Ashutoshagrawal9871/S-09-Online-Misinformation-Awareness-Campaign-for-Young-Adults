# TruthLens (S-09) — Technical Report

## 1. Problem Statement
Young adults (18–25) rapidly amplify health myths, phishing lures, and deepfake media via WhatsApp and social platforms. TruthLens delivers an educational campaign platform with SIFT-based verification tools, workshops, surveys, and measurable impact analytics.

## 2. Objectives
1. Teach the SIFT framework (Stop, Investigate, Find coverage, Trace claims).
2. Provide an interactive claim analyzer and literacy modules.
3. Collect baseline/endline susceptibility data.
4. Support workshop enrollment and peer-multiplier training.
5. Demonstrate KPI achievement against CUSoC social-challenge targets.

## 3. System Architecture
- **Frontend:** Single-page app (`src/main/resources/static/index.html`) — Tailwind UI, fetch-based API client.
- **Backend:** Spring Boot 3.4.3 / Java 17 REST APIs.
- **Security:** Spring Security 6, BCrypt passwords, JWT (`JwtUtils`).
- **Persistence:** JPA + H2 (local) / PostgreSQL (cloud via env vars).
- **Docs:** OpenAPI/Swagger at `/swagger-ui.html`.

### Layering
`Controller → Service → Repository → Entity` with DTO validation on write endpoints.

## 4. Core Modules
| Module | Responsibility |
| :--- | :--- |
| Fact-check | Heuristic SIFT analysis + verdict/confidence |
| Campaign | Literacy modules + survey submission |
| Workshops | Listing + participant registration + capacity |
| Analytics | KPI overview for evaluation dashboard |
| Auth | Register / login JWT issuance |

## 5. Database Design (logical)
Entities: `User`, `MisinformationModule`, `CampaignSurvey`, `Workshop`, `WorkshopRegistration`, `FactCheckQuery`, `CampaignMetric`.

## 6. Testing & Quality
See `docs/TESTING_REPORT.md`. Maven Surefire suite (21 tests in `tests/java`) covers controllers, security/JWT, and campaign and workshop service logic.

## 7. Deployment
- Local: `./mvnw.cmd spring-boot:run` → `http://localhost:8080`
- Cloud: Render (free, `docs/DEPLOYMENT_RENDER.md`) or Railway/Docker with PostgreSQL env overrides (`docs/DEPLOYMENT_RAILWAY.md`)

## 8. Outcomes
All primary KPIs exceeded (reach 1,415; workshops 118; susceptibility −62.1%; verification +48.3%; multipliers 14). Full narrative in `report/FINAL_IMPACT_REPORT.md`.

## 9. Future Work
Integrate live IFCN fact-check APIs, persistent production DB, WhatsApp Cloud API bot (blueprint in `docs/WHATSAPP_BOT_GUIDE.md`), and LMS embedding for first-year orientation.
