# API Documentation

TruthLens exposes a REST API under `/api/v1`. The interactive OpenAPI console is available at `/swagger-ui.html`; the raw specification is at `/v3/api-docs`.

## 1. Conventions

| Item | Value |
| :--- | :--- |
| Base URL (local) | `http://localhost:8080` |
| Content type | `application/json` |
| Timestamps | ISO-8601 local date-time |
| Auth | `Authorization: Bearer <JWT>` for protected endpoints |
| Public endpoints | No token required |

JWT tokens are issued by `/api/v1/auth/register` and `/api/v1/auth/login`, are signed with HS256, and expire after 24 hours. The token subject is the user's email. A request filter loads the user and populates Spring Security's context; role checks use the `ROLE_<ROLE>` authority.

## 2. Endpoint overview

| Method | Path | Access | Purpose |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/status` | Public | Application status and version |
| `POST` | `/api/v1/auth/register` | Public | Create an account and receive a JWT |
| `POST` | `/api/v1/auth/login` | Public | Authenticate and receive a JWT |
| `POST` | `/api/v1/fact-check/verify` | Public | Analyze a claim with the SIFT heuristic engine |
| `GET` | `/api/v1/fact-check/recent` | Public | Recent analyzed queries |
| `GET` | `/api/v1/fact-check/category/{category}` | Public | Queries filtered by category |
| `GET` | `/api/v1/campaign/modules` | Public | List awareness modules (optional `?category=`) |
| `GET` | `/api/v1/campaign/modules/{id}` | Public | Module details |
| `POST` | `/api/v1/campaign/modules` | Admin | Create a module |
| `POST` | `/api/v1/campaign/surveys/submit` | Public | Submit a baseline or endline survey |
| `GET` | `/api/v1/campaign/surveys` | Admin | List survey responses (optional `?type=`) |
| `GET` | `/api/v1/campaign/surveys/history/{userId}` | Admin | Survey history for a user |
| `GET` | `/api/v1/workshops` | Public | List workshops and capacity |
| `GET` | `/api/v1/workshops/{id}` | Public | Workshop details |
| `POST` | `/api/v1/workshops` | Admin | Create a workshop |
| `POST` | `/api/v1/workshops/register` | Public | Register for a workshop |
| `GET` | `/api/v1/workshops/{id}/registrations` | Admin | Attendee list |
| `GET` | `/api/v1/analytics` or `/overview` | Public | Full KPI response |
| `GET` | `/api/v1/analytics/metrics` | Public | Raw campaign metrics |
| `PUT` | `/api/v1/analytics/metrics/{key}` | Admin | Upsert a metric value |

Admin endpoints return `403` without a valid admin JWT.

## 3. Error format

Validation errors (`400`):

```json
{
  "timestamp": "2026-10-26T10:15:30",
  "status": 400,
  "error": "Validation Error",
  "details": {
    "claim": "Claim text or query is required"
  }
}
```

Business-rule errors such as a full workshop or duplicate registration (`400`):

```json
{
  "timestamp": "2026-10-26T10:16:02",
  "status": 400,
  "error": "Bad Request",
  "message": "This email is already registered for this workshop."
}
```

Missing resources (`404`) use the same shape with `"error": "Not Found"`.

## 4. Authentication

### Register

`POST /api/v1/auth/register`

```json
{
  "username": "new_student",
  "email": "new.student@university.edu",
  "password": "securepass123"
}
```

Response `201 Created`:

```json
{
  "token": "<jwt>",
  "tokenType": "Bearer",
  "userId": 4,
  "username": "new_student",
  "email": "new.student@university.edu",
  "role": "STUDENT",
  "message": "User registered successfully"
}
```

Public registration always creates a `STUDENT`; any supplied `role` is ignored to prevent privilege escalation. Duplicate usernames or emails return `400`.

### Login

`POST /api/v1/auth/login`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

`username` accepts either the username or the email.

## 5. Fact-check engine

`POST /api/v1/fact-check/verify`

```json
{
  "claim": "Drinking boiled garlic water cures pneumonia within 24 hours.",
  "category": "HEALTH",
  "sourcePlatform": "WHATSAPP",
  "sourceUrl": "https://example.com/forward"
}
```

`claim` is required. Other fields are optional hints.

Response `200 OK`:

```json
{
  "queryId": 12,
  "claim": "Drinking boiled garlic water cures pneumonia within 24 hours.",
  "verdict": "VERIFIED_FALSE",
  "confidenceScore": 96,
  "summary": "Medical myth: Boiled garlic water does not cure pneumonia or viral fever within 24 hours.",
  "detailedAnalysis": "Garlic has mild antimicrobial compounds in lab settings, but it is not a substitute for antibiotics, antivirals, or clinical care...",
  "factCheckFrameworkSteps": [
    "Stop: Avoid delaying medical care for unverified remedies.",
    "Investigate Source: Check WHO and ICMR peer-reviewed medical guidance.",
    "Find Better Coverage: Read BOOM Live and Alt News health checks.",
    "Trace Claims: Traced to recurring copy-paste social media chains."
  ],
  "verificationSources": ["WHO Mythbusters", "BOOM Live Health Check", "Alt News Science"],
  "warningFlags": [
    "Promises instant cure for serious illness",
    "Forwarded chain-message format",
    "No hospital or clinical trial citation"
  ],
  "recommendedAction": "Do NOT forward. Seek licensed medical care for pneumonia symptoms."
}
```

Verdict values: `VERIFIED_FALSE`, `MISLEADING`, `VERIFIED_TRUE`, `UNVERIFIED`. Confidence is 0–100.

> This is a heuristic demo engine. It does not call an external AI model or live fact-check database.

## 6. Modules

`GET /api/v1/campaign/modules?category=HEALTH_MYTHS`

```json
[
  {
    "id": 4,
    "title": "Health Myths & Pseudoscience Debunking",
    "description": "Evaluate unverified medical claims, miracle cures, anti-vaccine disinformation...",
    "category": "HEALTH_MYTHS",
    "contentUrl": "/docs/CAMPAIGN_MATERIALS.md",
    "difficulty": "INTERMEDIATE",
    "estimatedMinutes": 18,
    "createdAt": "2026-10-26T09:00:00"
  }
]
```

## 7. Surveys

`POST /api/v1/campaign/surveys/submit`

```json
{
  "participantEmail": "student@university.edu",
  "surveyType": "BASELINE",
  "score": 8,
  "verificationFrequency": 2,
  "primaryNewsSource": "WHATSAPP",
  "feedback": "Self-check q2=HIGH, q3=CORRECT, q4=CORRECT"
}
```

| Field | Rules |
| :--- | :--- |
| `surveyType` | Required; `BASELINE` or `POST_CAMPAIGN` |
| `score` | Required; 0–10 (higher = more susceptible) |
| `verificationFrequency` | Optional; 1–5 |
| `participantEmail` | Optional correlation identifier |
| `userId` | Optional existing user ID |

Response `200 OK` returns the stored `CampaignSurvey` with `id`, `surveyType`, `score`, `completedAt`, and the linked participant, if any.

## 8. Workshops

`GET /api/v1/workshops`

```json
[
  {
    "id": 1,
    "title": "Workshop 1: Hands-on Fact-Checking & SIFT Method",
    "description": "Interactive session covering reverse image search and fact-checking workflows.",
    "instructor": "Prof. A. Sharma (Media Dept) & Guest Fact-Checker",
    "scheduledAt": "2026-10-29T10:00:00",
    "location": "Media Auditorium & Live Stream",
    "meetingLink": "https://meet.truthlens.org/workshop-1",
    "maxCapacity": 120,
    "currentRegistrations": 68,
    "status": "UPCOMING",
    "category": "FACT_CHECKING_TOOLS"
  }
]
```

`POST /api/v1/workshops/register`

```json
{
  "workshopId": 1,
  "participantName": "Priya Sharma",
  "participantEmail": "priya@example.com",
  "organizationOrCollege": "Department of Journalism"
}
```

Rules: workshop must exist; capacity must not be full; the email must not already be registered for that workshop. Successful registration increments `currentRegistrations` and returns the `WorkshopRegistration`.

## 9. Analytics

`GET /api/v1/analytics/overview`

```json
{
  "totalImpressions": 1415,
  "totalEngagements": 820,
  "totalWorkshopRegistrations": 135,
  "totalWorkshopAttendees": 118,
  "peerMultipliersTrained": 14,
  "totalSurveysCompleted": 4,
  "baselineAvgSusceptibilityScore": 7.4,
  "postCampaignAvgSusceptibilityScore": 2.8,
  "susceptibilityReductionPercentage": 62.1,
  "factCheckingBehaviorShiftPercentage": 48.3,
  "keyMetrics": [ ... ],
  "surveyChannelDistribution": {
    "WhatsApp Groups": 540,
    "Instagram Reels & Stories": 480,
    "Campus Posters & QR Codes": 260,
    "Workshops & Seminars": 135
  },
  "weeklyMilestoneProgress": { "Week 1": "..." }
}
```

Headline reach, multiplier and susceptibility values are campaign-level aggregates from the N=142 survey analysis. `totalSurveysCompleted` reflects the rows in the demo database (4 seeded responses).

## 10. Example cURL requests

```bash
# Status
curl http://localhost:8080/api/status

# Verify a claim
curl -X POST http://localhost:8080/api/v1/fact-check/verify \
  -H "Content-Type: application/json" \
  -d '{"claim":"5G radiation causes viral infections","category":"HEALTH"}'

# Admin login and protected call
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' | jq -r .token)

curl http://localhost:8080/api/v1/campaign/surveys \
  -H "Authorization: Bearer $TOKEN"
```

## 11. HTTP status summary

| Status | Meaning |
| :--- | :--- |
| `200` | Success |
| `201` | Created (register, create module/workshop) |
| `400` | Validation or business-rule failure |
| `403` | Missing/invalid token or insufficient role |
| `404` | Resource not found |
| `500` | Unexpected server error |
