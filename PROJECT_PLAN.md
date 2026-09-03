# TruthLens – Misinformation Awareness & Digital Literacy Platform

## Project Status: 100% Complete & Fully Functional (All Tests Passing)

## 1. Project Vision
TruthLens is a production-grade open-source platform and community initiative developed for the **S-09 Online Misinformation Awareness Campaign for Young Adults**. The platform combines automated SIFT heuristic verification, interactive learning modules, pre/post susceptibility assessments, workshop management, and real-time social impact analytics to cultivate critical thinking skills among youth.

---

## 2. Technical Stack & Architecture

- **Backend:** Java 17, Spring Boot 3.4.3
- **Security:** Spring Security 6 with stateless JWT authentication
- **Database:** Spring Data JPA with H2 In-Memory DB (PostgreSQL compatibility mode) & auto-seeding
- **API Documentation:** SpringDoc OpenAPI 3 / Swagger UI
- **Frontend:** Responsive Single-Page Application (HTML5, Tailwind CSS, JavaScript)
- **Testing:** JUnit 5, Mockito, Spring Boot MockMvc (13/13 passing tests)

---

## 3. Implemented Features & Milestones

- [x] **Core Architecture & Data Layer:** Entities, JPA repositories, database schema, and auto-initialization fixtures.
- [x] **Security & Authentication:** JWT token generation/validation, password hashing with BCrypt, public and protected route control.
- [x] **SIFT Claim Verification Engine (`/api/v1/fact-check/verify`):** Automated heuristic claim analysis, verdict classification (TRUE / FALSE / MISLEADING), confidence scoring, SIFT step breakdown, and verified source citations.
- [x] **Awareness Modules Hub (`/api/v1/campaign/modules`):** Categorized digital literacy and health myth curriculum.
- [x] **Susceptibility & Literacy Survey System (`/api/v1/campaign/surveys/submit`):** Dynamic 10-point scoring algorithm measuring baseline vs. endline risk.
- [x] **Interactive Workshops Hub (`/api/v1/workshops` & `/api/v1/workshops/register`):** Session scheduling, capacity tracking, and student registration.
- [x] **Real-Time Analytics Dashboard (`/api/v1/analytics/overview`):** Live KPI aggregation tracking reach, workshop attendance, susceptibility reduction, and verification shifts.
- [x] **Interactive Frontend Application (`/index.html`):** Single-page web dashboard integrating all API capabilities with zero external dependencies.
- [x] **Comprehensive Documentation Package (`docs/`):** All 6 core deliverables + 3 bonus deliverables completed.

---

## 4. Deliverables & Documentation Index

| Deliverable | File Path | Status |
| :--- | :--- | :--- |
| **Baseline & Endline Survey Analysis** | `docs/SURVEY_ANALYSIS.md` | ✅ Complete |
| **Campaign Materials Repository** | `docs/CAMPAIGN_MATERIALS.md` | ✅ Complete |
| **Workshop Facilitation Guide** | `docs/WORKSHOP_FACILITATION_GUIDE.md` | ✅ Complete |
| **Fact-Checking Resource Guide** | `docs/FACT_CHECKING_RESOURCE_GUIDE.md` | ✅ Complete |
| **Social Media Analytics Report** | `docs/SOCIAL_MEDIA_ANALYTICS_REPORT.md` | ✅ Complete |
| **Final Impact & Evaluation Report** | `docs/FINAL_IMPACT_REPORT.md` | ✅ Complete |
| **Policy Brief to University (Bonus)** | `docs/POLICY_BRIEF_DIGITAL_LITERACY.md` | ✅ Complete |
| **WhatsApp Bot Integration Guide (Bonus)** | `docs/WHATSAPP_BOT_GUIDE.md` | ✅ Complete |
| **Fact-Checker Partnership Session Plan (Bonus)** | `docs/PARTNERSHIP_SESSION_PLAN.md` | ✅ Complete |

---

## 5. Author & Contributor
**Ashutosh Agrawal**  
CUSoC 2026 Contributor
