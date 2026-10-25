# TruthLens (S-09): Online Misinformation Awareness Campaign for Young Adults

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/Ashutoshagrawal9871/S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults)
[![Tests](https://img.shields.io/badge/tests-21%20passing-brightgreen.svg)](docs/TESTING_REPORT.md)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-green.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-blue.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Swagger](https://img.shields.io/badge/OpenAPI-Swagger%203-yellow.svg)](http://localhost:8080/swagger-ui.html)

---

## 1. Social Challenge & Context

Misinformation — health myths, fake news, phishing scams, and AI-manipulated deepfakes — spreads rapidly through WhatsApp and social media among young adults, causing severe real-world harm (vaccine hesitancy, communal tension, and financial fraud).

**TruthLens (S-09)** is a campaign platform and empirical educational initiative designed to empower young adults (ages 18–25) with critical thinking skills, forensic fact-checking tools, and the **SIFT Framework** (*Stop, Investigate the Source, Find Better Coverage, Trace Claims*).

---

## 2. Impact Metrics & Target Achievement

| Metric | Target KPI | Achieved Metric | Verification Evidence | Status |
| :--- | :--- | :--- | :--- | :--- |
| **Campaign Reach** | $\ge \mathbf{1,000}$ impressions | **1,415 impressions** | [Social Analytics Report](report/SOCIAL_MEDIA_ANALYTICS_REPORT.md) | **EXCEEDED (141.5%)** |
| **Workshop Participants** | $\ge \mathbf{100}$ students | **118 certified attendees** | [Workshop Facilitation Guide](docs/WORKSHOP_FACILITATION_GUIDE.md) | **EXCEEDED (118.0%)** |
| **Susceptibility Score Reduction** | Measurable decrease | **-62.1% reduction** ($7.38 \to 2.80/10$) | [Survey Analysis Report](report/SURVEY_ANALYSIS.md) | **EXCEEDED ($p < 0.001$)** |
| **Fact-Checking Adoption** | $\ge \mathbf{40\%}$ positive shift | **+48.3% increase** ($18.3\% \to 66.6\%$) | Pre/Post Matched Survey DB | **EXCEEDED** |
| **Peer Multipliers Trained** | $\ge \mathbf{10}$ students | **14 certified ambassadors** | Masterclass 2 Certification Logs | **EXCEEDED (140.0%)** |

---

## 3. Website & Platform Features

The public single-page application is served at `/` and works against the live API.

| Section | What it does |
| :--- | :--- |
| **Hero & campaign snapshot** | KPI strip, campaign timeline, and problem statement for young adults. |
| **SIFT explainer** | The four verification moves explained with practical WhatsApp examples. |
| **SIFT Analyzer** | Submit a claim, pick a category and source platform, and receive a verdict, confidence score, 4-step breakdown, verified sources, and recommended action. Three sample claims are built in. |
| **Learning modules** | Filterable curriculum cards (SIFT literacy, health myths, deepfakes, WhatsApp rumors, scams, fact-checking tools) loaded from the API with an offline fallback. |
| **Susceptibility quiz** | 10-question baseline/endline assessment with live scoring and server submission. |
| **Workshops** | Session schedule, seats remaining, capacity bar, and student registration with duplicate/full guards. |
| **Campaign materials** | Downloadable SIFT cheat sheet, WhatsApp rumor card, and deepfake checklist (SVG). |
| **Impact analytics** | Live reach, attendance, susceptibility reduction, fact-checking adoption, and multiplier metrics with channel breakdowns. |
| **Resource library** | Every CUSoC deliverable linked from one place, plus FAQ, contact, and evaluator links. |

Frontend hardening: all API-rendered text is HTML-escaped, documentation links are relative so they work in the packaged JAR, demo data appears if the API is unavailable, and the layout is responsive from phone to desktop.

---

## 4. Architecture & Technical Stack

```
+-----------------------------------------------------------------------------------+
|                     TRUTHLENS FULL-STACK ARCHITECTURE                             |
+-----------------------------------------------------------------------------------+
                                        |
  +-------------------------------------+-------------------------------------+
  |                                                                           |
[Frontend Layer]                                                    [Backend API Layer]
* Single Page App (src/main/resources/static/index.html)            * Spring Boot 3.4.3 & Java 17
* SIFT Interactive Claim Analyzer                                   * Spring Security 6 & Stateless JWT
* Dynamic Awareness Modules Viewer                                  * Automated SIFT Heuristic Engine
* Responsive Quiz & Diagnostic Tool                                 * Spring Data JPA & Repository Pattern
* Workshop Enrollment Hub & Live Metrics Dashboard                  * OpenAPI 3 / Swagger Documentation
                                        |
                                [Database Layer]
                      * H2 In-Memory DB (PostgreSQL Compatibility)
                      * Zero-Configuration Auto-Seeded Fixtures
```

Build-time pipeline: Maven copies `docs/`, `report/`, `presentation/`, `assets/`, `media/`, and `tests/README.md` into the served static bundle, so the repository folders are the single source of truth. Full details: [Architecture](docs/ARCHITECTURE.md) and [Database Schema](docs/DATABASE_SCHEMA.md).

### Core API Endpoints

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/fact-check/verify` | Interactive SIFT heuristic claim verification | Public |
| `GET` | `/api/v1/fact-check/recent` | Recent verification queries | Public |
| `GET` | `/api/v1/campaign/modules` | Fetch educational misinformation modules | Public |
| `POST` | `/api/v1/campaign/surveys/submit` | Submit literacy survey & calculate susceptibility | Public |
| `GET` | `/api/v1/campaign/surveys` | List submitted surveys | Admin (JWT) |
| `GET` | `/api/v1/workshops` | List workshops & enrollment status | Public |
| `POST` | `/api/v1/workshops/register` | Register student for training session | Public |
| `GET` | `/api/v1/analytics/overview` | Fetch campaign KPI summary & metrics | Public |
| `POST` | `/api/v1/auth/register` | Register user account | Public |
| `POST` | `/api/v1/auth/login` | Authenticate user & return JWT | Public |

Full request/response examples: [API Documentation](docs/API_DOCUMENTATION.md).

---

## 5. Quick Start & Execution

### Prerequisites
- **Java 17+ JDK** installed
- Modern web browser (Chrome, Edge, Firefox, Safari)

### Run the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Ashutoshagrawal9871/S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults.git
   cd S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults
   ```
2. **Execute the automated test suite (21/21 passing):**
   ```bash
   ./mvnw clean test
   # On Windows:
   ./mvnw.cmd clean test
   ```
3. **Launch the Spring Boot Application:**
   ```bash
   ./mvnw spring-boot:run
   # On Windows:
   ./mvnw.cmd spring-boot:run
   ```
4. **Access the Web Interfaces:**
   - **Interactive Web App & Dashboard:** [http://localhost:8080](http://localhost:8080)
   - **Interactive Swagger API Documentation:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - **H2 Database Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:truthlens_db`, User: `sa`, Password: *(blank)*)

Step-by-step setup, environment variables, Docker, and troubleshooting: [Installation Guide](docs/INSTALLATION_GUIDE.md). End-user walkthrough: [User Guide](docs/USER_GUIDE.md).

### CUSoC final evaluation (Oct 26, 2026)
Follow the live demo checklist and viva notes in **[Evaluation Prep](docs/EVALUATION_PREP.md)**. Presentation slides: [presentation/FINAL_EVALUATION_SLIDES.html](presentation/FINAL_EVALUATION_SLIDES.html) (also at `/presentation/FINAL_EVALUATION_SLIDES.html` when the app is running).

### Free public demo (Render)
Railway credits may be exhausted. Use **Render Free** instead — see **[Render Deployment Guide](docs/DEPLOYMENT_RENDER.md)** (`render.yaml` included). Local H2 works; no paid database required for evaluation.

---

## 6. Documentation Index

| Document | Purpose |
| :--- | :--- |
| [Documentation Home](docs/README.md) | Index of every document in the repository. |
| [Installation Guide](docs/INSTALLATION_GUIDE.md) | Prerequisites, run commands, configuration, Docker/Render, troubleshooting. |
| [User Guide](docs/USER_GUIDE.md) | Website tour, analyzer, quiz scoring, workshops, analytics, FAQ. |
| [API Documentation](docs/API_DOCUMENTATION.md) | Conventions, auth, endpoint examples, error formats, cURL. |
| [Architecture](docs/ARCHITECTURE.md) | C4-style flow, package layout, security, static pipeline, quality attributes. |
| [Database Schema](docs/DATABASE_SCHEMA.md) | ER diagram, tables, constraints, seed data, data lifecycle. |
| [Testing Report](docs/TESTING_REPORT.md) | Automated suite (21 tests), manual cases, defects fixed, limitations. |
| [Change Log](docs/CHANGELOG.md) | Version history and release notes. |
| [Technical Report](report/TECHNICAL_REPORT.md) | Formal engineering report for CUSoC evaluation. |
| [Presentation Slides](presentation/FINAL_EVALUATION_SLIDES.html) | Final evaluation deck (HTML, no build step). |
| [Evaluation Prep](docs/EVALUATION_PREP.md) | Demo script, viva answers, submission checklist. |
| [Project Plan](docs/PROJECT_PLAN.md) | Scope, milestones, and deliverable status. |
| [Deployment (Render / Railway)](docs/DEPLOYMENT_RENDER.md) | Free cloud deployment; Railway alternative in [DEPLOYMENT_RAILWAY.md](docs/DEPLOYMENT_RAILWAY.md). |

### Repository Structure

Arranged per the CUSoC recommended layout (`README.md LICENSE docs/ src/ tests/ assets/ presentation/ report/ media/`):

```
S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults/
├── README.md                  # Project overview, quick start, documentation index
├── LICENSE                    # CC BY-SA 4.0
├── docs/                      # Installation, user, API, architecture, schema, testing, changelog, plan, guides
├── src/                       # Spring Boot application (API, security, services) + website (main/resources/static)
├── tests/                     # JUnit 5 / MockMvc automated test suite (tests/java) + test docs
├── assets/                    # SIFT cheat sheet, rumor card, deepfake checklist, favicon
├── presentation/              # Final evaluation slides
├── report/                    # Technical, impact, survey, social analytics reports + policy brief
├── media/                     # Field-work evidence guidance (photos/videos)
└── pom.xml, mvnw*, Dockerfile, render.yaml, railway.json   # Build & deploy config (must stay at root)
```

---

## 7. Complete Deliverables Index

### Core Deliverables (Evaluation Criteria: 100/100 Marks)
1. **[Baseline + Endline Survey Statistical Analysis](report/SURVEY_ANALYSIS.md)** — pre/post evaluation across $N=142$ students, paired t-tests, susceptibility reduction analysis.
2. **[Campaign Materials Repository](docs/CAMPAIGN_MATERIALS.md)** — SIFT pocket infographics, WhatsApp rumor radar cards, 30–45s Reel/TikTok scripts.
3. **[Workshop Facilitation Guide](docs/WORKSHOP_FACILITATION_GUIDE.md)** — Masterclass 1 & 2 agendas, breakout exercises, slide outline, Q&A playbook.
4. **[Open-Access Fact-Checking Resource Guide](docs/FACT_CHECKING_RESOURCE_GUIDE.md)** — Google Lens, TinEye, InVID, Wayback Machine, and IFCN-certified portals.
5. **[Social Media Analytics & Reach Report](report/SOCIAL_MEDIA_ANALYTICS_REPORT.md)** — 1,415 impressions, engagement rates, click-through metrics.
6. **[Final Impact & Evaluation Report](report/FINAL_IMPACT_REPORT.md)** — formal evaluation confirming all 5 KPI targets.

### Bonus Impact Deliverables
- **[Policy Brief on Digital Literacy in Higher Education](report/POLICY_BRIEF_DIGITAL_LITERACY.md)** — recommendations submitted to the University Academic Council.
- **[WhatsApp Rumor Radar Bot Architecture Guide](docs/WHATSAPP_BOT_GUIDE.md)** — Cloud API webhook design, state machine, auto-debunking blueprint.
- **[Fact-Checker Partnership Session Plan](docs/PARTNERSHIP_SESSION_PLAN.md)** — collaborative masterclass plan with BOOM Live & Alt News.

---

## 8. Evaluation Criteria Mapping

| Evaluation Criterion | Target Marks | Evidence & Deliverable |
| :--- | :--- | :--- |
| **Campaign Reach & Engagement** | 25 Marks | 1,415 impressions (>1,000 KPI), multi-channel campaign in [Social Analytics Report](report/SOCIAL_MEDIA_ANALYTICS_REPORT.md) & [Campaign Materials](docs/CAMPAIGN_MATERIALS.md). |
| **Survey Quality & Behavior Change** | 25 Marks | Empirical paired pre/post survey analysis in [Survey Analysis Report](report/SURVEY_ANALYSIS.md), demonstrating -62.1% susceptibility drop and +48.3% verification increase ($p < 0.001$). |
| **Workshop Content Quality** | 20 Marks | 2 structured masterclasses with 118 attendees and 14 multipliers in [Workshop Facilitation Guide](docs/WORKSHOP_FACILITATION_GUIDE.md). |
| **Resource Guide Usability** | 15 Marks | Open-access digital handbook with SIFT workflow and OSINT tools in [Fact-Checking Guide](docs/FACT_CHECKING_RESOURCE_GUIDE.md). |
| **Documentation & Presentation** | 15 Marks | Full documentation suite, interactive website, Swagger docs, [Testing Report](docs/TESTING_REPORT.md), and [Final Impact Report](report/FINAL_IMPACT_REPORT.md). |
| **Bonus Opportunities** | Bonus | BOOM/Alt News session plan, WhatsApp bot guide, University policy brief. |

---

## Author

**Ashutosh Agrawal** — CUSoC 2026 Contributor
