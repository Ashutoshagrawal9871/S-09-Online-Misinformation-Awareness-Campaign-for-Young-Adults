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
- **Testing:** JUnit 5, Mockito, Spring Boot MockMvc (21/21 passing tests)

---

## 3. Implemented Features & Milestones

- [x] **Core Architecture & Data Layer:** Entities, JPA repositories, database schema, and auto-initialization fixtures.
- [x] **Security & Authentication:** JWT token generation/validation, password hashing with BCrypt, public and protected route control.
- [x] **SIFT Claim Verification Engine (`/api/v1/fact-check/verify`):** Automated heuristic claim analysis, verdict classification (TRUE / FALSE / MISLEADING), confidence scoring, SIFT step breakdown, and verified source citations.
- [x] **Awareness Modules Hub (`/api/v1/campaign/modules`):** Categorized digital literacy and health myth curriculum.
- [x] **Susceptibility & Literacy Survey System (`/api/v1/campaign/surveys/submit`):** Dynamic 10-point scoring algorithm measuring baseline vs. endline risk.
- [x] **Interactive Workshops Hub (`/api/v1/workshops` & `/api/v1/workshops/register`):** Session scheduling, capacity tracking, and student registration.
- [x] **Real-Time Analytics Dashboard (`/api/v1/analytics/overview`):** Live KPI aggregation tracking reach, workshop attendance, susceptibility reduction, and verification shifts.
- [x] **Interactive Frontend Application (`/index.html`):** Complete campaign site with hero, campaign timeline, SIFT explainer, analyzer, filterable modules, quiz, workshops, downloadable materials, analytics, resource library, FAQ, contact, and registration modal.
- [x] **Security Hardening:** Database-backed `UserDetailsService`, JWT validation filter, admin-only routes, BCrypt passwords, and `400` responses for invalid credentials.
- [x] **Business-Rule Validation:** Workshop capacity and duplicate-email guards; required survey score and bounded verification frequency.
- [x] **Comprehensive Documentation Package (`docs/`):** All 6 core deliverables + 3 bonus deliverables, plus Installation Guide, User Guide, API Documentation, Architecture, Database Schema, Testing Report, and Change Log.
- [x] **Campaign Assets (`assets/`):** SIFT cheat sheet, WhatsApp rumor card, deepfake checklist, and favicon.
- [x] **Verified Automated Suite:** 21 JUnit 5/MockMvc tests passing, covering context, security, controllers, and service rules.

---

## 4. Deliverables & Documentation Index

| Deliverable | File Path | Status |
| :--- | :--- | :--- |
| **Baseline & Endline Survey Analysis** | `report/SURVEY_ANALYSIS.md` | ✅ Complete |
| **Campaign Materials Repository** | `docs/CAMPAIGN_MATERIALS.md` | ✅ Complete |
| **Workshop Facilitation Guide** | `docs/WORKSHOP_FACILITATION_GUIDE.md` | ✅ Complete |
| **Fact-Checking Resource Guide** | `docs/FACT_CHECKING_RESOURCE_GUIDE.md` | ✅ Complete |
| **Social Media Analytics Report** | `report/SOCIAL_MEDIA_ANALYTICS_REPORT.md` | ✅ Complete |
| **Final Impact & Evaluation Report** | `report/FINAL_IMPACT_REPORT.md` | ✅ Complete |
| **Technical Report** | `report/TECHNICAL_REPORT.md` | ✅ Complete |
| **Presentation Slides** | `presentation/FINAL_EVALUATION_SLIDES.html` | ✅ Complete |
| **Installation Guide** | `docs/INSTALLATION_GUIDE.md` | ✅ Complete |
| **User Guide** | `docs/USER_GUIDE.md` | ✅ Complete |
| **API Documentation** | `docs/API_DOCUMENTATION.md` | ✅ Complete |
| **Architecture Diagram & Description** | `docs/ARCHITECTURE.md` | ✅ Complete |
| **Database Schema** | `docs/DATABASE_SCHEMA.md` | ✅ Complete |
| **Testing Report** | `docs/TESTING_REPORT.md` | ✅ Complete (21/21) |
| **Automated Test Suite** | `tests/java/` | ✅ Complete (21/21) |
| **Change Log** | `docs/CHANGELOG.md` | ✅ Complete |
| **Policy Brief to University (Bonus)** | `report/POLICY_BRIEF_DIGITAL_LITERACY.md` | ✅ Complete |
| **WhatsApp Bot Integration Guide (Bonus)** | `docs/WHATSAPP_BOT_GUIDE.md` | ✅ Complete |
| **Fact-Checker Partnership Session Plan (Bonus)** | `docs/PARTNERSHIP_SESSION_PLAN.md` | ✅ Complete |

---

## 5. Author & Contributor
**Ashutosh Agrawal**  
CUSoC 2026 Contributor
