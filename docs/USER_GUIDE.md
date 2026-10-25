# User Guide

This guide explains every section of the TruthLens campaign website and how to use it during a demo or self-study session.

## 1. Who this is for

- **Students (18–25)** who want a fast, repeatable way to check viral claims.
- **Peer multipliers and student clubs** running media-literacy micro-sessions.
- **Evaluators and mentors** reviewing the CUSoC S-09 project.
- **Developers** exploring the API and architecture.

No account is needed for the public website features.

## 2. Website tour

### Home / hero
The landing section summarises the problem, the SIFT method, and the campaign's five headline KPIs. The card on the right previews what the analyzer returns.

### Campaign (`#campaign`)
Explains why misinformation spreads, the three harm categories (health, financial, synthetic media), the six-week timeline, and the campaign snapshot.

### SIFT framework (`#sift`)
Four cards explain **Stop**, **Investigate the source**, **Find better coverage**, and **Trace claims**, with practical prompts and tools for each.

### SIFT analyzer (`#fact-checker`)
1. Paste a claim, or choose a sample (health cure, free laptop, 5G virus).
2. Pick a category and source platform.
3. Click **Verify claim with SIFT**.
4. Read the verdict badge, confidence score, warning flags, four SIFT steps, and suggested sources.

The analyzer returns one of: `VERIFIED_FALSE`, `MISLEADING`, `VERIFIED_TRUE`, or `UNVERIFIED`. A client-side fallback keeps the demo working if the backend is offline.

> The engine is a transparent heuristic demo, not a live AI oracle. For breaking claims, follow the suggested sources (PIB Fact Check, BOOM Live, Alt News, WHO, Snopes).

### Learning modules (`#modules`)
Cards are loaded from the API and can be filtered by category: SIFT literacy, health myths, deepfakes, WhatsApp rumours, scams, and fact-checking tools. Each card links to the relevant documentation.

### Susceptibility check (`#quiz`)
Answer four scenario questions. The score runs from 0 to 10:

| Score | Meaning |
| :--- | :--- |
| 0–3 | Low susceptibility — keep using SIFT |
| 4–6 | Moderate — practise reverse image search and source checks |
| 7–10 | High — start with the SIFT module and verify three forwards this week |

Choose **Baseline** before training or **Endline** after training. When the backend is running, the response is stored as a survey record and appears in analytics. This is an educational self-check, not a clinical assessment.

### Workshops (`#workshops`)
Upcoming sessions show the facilitator, venue, date/time, and a live capacity bar. Click **Register seat** and submit name, email, and college. The backend rejects duplicate emails and full sessions with a clear message.

### Campaign materials (`#campaign-assets`)
Three print-ready SVG cards: the SIFT cheat sheet, the WhatsApp rumor radar response card, and the deepfake checklist. Use the **Download** buttons or open the full materials document for scripts and poster copy. Materials are licensed CC BY-SA 4.0.

### Impact & analytics (`#analytics`)
Live KPIs from `/api/v1/analytics/overview`: reach, workshop attendees, susceptibility reduction, verification shift, peer multipliers, and reach by channel. Click **Refresh analytics** after submitting a survey or registration.

### Resource library (`#resources`)
All deliverables grouped into Campaign & Impact, Engineering, and Guides & Repo. Links open the Markdown files served by the app or the GitHub repository.

### FAQ (`#faq`)
Answers about SIFT, the heuristic engine, impact methodology, accounts, data storage, and licensing.

### Contact (`#contact`)
Project background, contributor, programme, contact email, repository link, and evaluator quick links (analyzer, Swagger, slides, technical report, testing report, impact report).

## 3. Demo walkthrough (4 minutes)

1. **Problem (20s):** Viral health myths and phishing harm young adults.
2. **SIFT (40s):** Explain the four steps using the framework cards.
3. **Analyzer (60s):** Choose the health sample → verify → show verdict, confidence, warning flags, and sources.
4. **Quiz (40s):** Submit a baseline response → show the score and stored survey.
5. **Workshops (30s):** Register a demo student → show capacity update.
6. **Impact (40s):** Refresh analytics → point to the five KPIs and channel chart.
7. **Docs/Swagger (30s):** Open the resource library and Swagger UI.
8. **Close (20s):** Open-source repo with tests, documentation, reports, and slides.

## 4. API and admin access

- Public users can read modules and workshops, submit surveys, register for workshops, and call the analyzer.
- Admin-only endpoints (create module, create workshop, list survey responses, list workshop registrations, update metrics) require a JWT with the `ADMIN` role.
- To get a token, call `POST /api/v1/auth/login` with the seeded admin account, then send `Authorization: Bearer <token>`.
- Full request and response details are in [API_DOCUMENTATION.md](API_DOCUMENTATION.md) or the Swagger UI.

## 5. Mobile use

The navigation collapses into a menu below 1280px. Cards stack vertically, tables scroll, and all buttons meet touch-target sizing. The site has been smoke-tested in Chrome and Edge at desktop and phone widths.

## 6. Accessibility notes

- Semantic landmarks (`header`, `main`, `footer`, `nav`) and a skip link.
- Labels for every form field and `aria-live` on result panels.
- Native `<details>` FAQ elements, keyboard-operable by default.
- Status is conveyed with text, not colour alone.
- Images include descriptive `alt` text.

## 7. Data and privacy

- Public interactions do not require a name or account; the quiz accepts an identifier to correlate pre/post responses.
- The demo uses in-memory H2 data that resets on restart.
- Never enter real OTPs, bank details, or sensitive personal information into a demo.
- For a live deployment, replace demo credentials, set `JWT_SECRET`, disable the H2 console, and use PostgreSQL.
