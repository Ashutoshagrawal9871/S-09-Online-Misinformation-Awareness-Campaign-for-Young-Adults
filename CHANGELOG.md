# Changelog

## [1.2.0] — 2026-10-26

### Added
- Complete campaign website: campaign snapshot/timeline, SIFT explainer, analyzer preview, learning-module filters, 10-question quiz, workshops, campaign materials, analytics, resource library, FAQ, contact, registration modal, and back-to-top.
- Downloadable campaign assets: SIFT cheat sheet, WhatsApp rumor card, deepfake checklist, and favicon (`assets/`).
- CUSoC documentation: Installation Guide, User Guide, API Documentation, Architecture, Database Schema, documentation index, test README, and media README.
- `JwtAuthenticationFilter` and integration tests for admin login, JWT enforcement, and bad credentials.
- Workshop service tests for capacity limits, duplicate email, and counter updates.
- Maven resource pipeline that copies `docs/`, `report/`, `presentation/`, and `assets/` into the served static bundle.

### Fixed
- Public registration can no longer self-assign `ADMIN`; new accounts are always students.
- Removed the plaintext password fallback from login; BCrypt is now the only accepted format.
- `CustomUserDetailsService` now loads users from the database instead of two hardcoded accounts.
- JWTs are validated on every request; admin-only endpoints return `403` without a valid token.
- Workshop registration rejects full sessions and duplicate emails with clear `400` responses.
- Invalid credentials, duplicate registration, and validation errors return `400` instead of `500`.
- Survey score is now required and verification frequency is range-validated.
- Upgraded springdoc-openapi `2.6.0` → `2.8.9`; `/v3/api-docs` and Swagger UI now load on Spring Boot 3.4.3.
- Allowed the served root-level documentation and test README paths through Spring Security instead of returning `403`.
- Swagger licence metadata now matches the repository's CC BY-SA 4.0 licence.
- Frontend dynamic content is HTML-escaped; module filters no longer rely on the global `event`; documentation links work inside the packaged JAR.
- Removed stale duplicate documentation and slide copies from `src/main/resources/static`.

### Changed
- README, Project Plan, Architecture, Installation Guide, and Testing Report updated for version 1.2.0 and the 20-test suite.
- Docker image now copies documentation and campaign assets into the build.

## [1.1.0] — 2026-10-25
### Fixed
- Wired frontend payloads to backend DTOs (workshops, surveys, analytics).
- Served campaign documentation under `/docs/**` for live demos.
- Corrected module content URLs to local documentation assets.
- Added mobile navigation for evaluation on phone/tablet screens.

### Added
- `LICENSE` (CC BY-SA 4.0)
- `docs/TESTING_REPORT.md`
- `presentation/FINAL_EVALUATION_SLIDES.html`
- `report/TECHNICAL_REPORT.md`
- `EVALUATION_PREP.md` demo checklist
- Railway deployment scaffolding (Dockerfile, railway.json)

## [1.0.0] — 2026-09
### Added
- Spring Boot 3 TruthLens platform with JWT auth
- SIFT fact-check API, campaign modules, surveys, workshops, analytics
- Interactive static website dashboard
- Campaign documentation suite under `docs/`
