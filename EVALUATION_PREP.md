# CUSoC Final Evaluation Prep — Oct 26, 2026 (TruthLens S-09)

## Before you join the meeting
1. Open the **Google Sheet** schedule and note your exact slot + the **correct meeting link** (ignore older email links).
2. Join **5 minutes early**, mic/camera ready, screen-share tested.
3. Keep these tabs open:
   - App: `http://localhost:8080` (sections: `#campaign`, `#sift`, `#fact-checker`, `#modules`, `#quiz`, `#workshops`, `#campaign-assets`, `#analytics`, `#resources`, `#faq`, `#contact`)
   - Swagger: `http://localhost:8080/swagger-ui.html`
   - Slides: `http://localhost:8080/presentation/FINAL_EVALUATION_SLIDES.html`
   - Resource guide: `http://localhost:8080/docs/FACT_CHECKING_RESOURCE_GUIDE.md`
   - GitHub repo README
4. Start the app before your slot:

```powershell
cd C:\S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults
.\mvnw.cmd spring-boot:run
```

Optional: run tests once before the slot — `.\mvnw.cmd test -q` (expect **21 tests, 0 failures**).

## 4-minute live demo script (say this)
1. **Problem (20s):** Viral WhatsApp health myths and phishing hurt young adults. TruthLens teaches SIFT and measures behavior change. Scroll the campaign snapshot/timeline.
2. **SIFT Analyzer (60s):** Click sample “Health Cure” → Verify → show FALSE verdict, confidence, 4 SIFT steps, and verified sources.
3. **Modules + Quiz (30s):** Filter the learning modules, then submit the susceptibility quiz → score saved to the backend.
4. **Workshops (25s):** Register a demo student → capacity bar updates; mention duplicate-email and full-capacity guards.
5. **Impact (35s):** Analytics section — 1,415 reach, 118 attendees, −62.1% susceptibility, +48.3% verification, 14 multipliers, channel breakdown.
6. **Campaign assets + docs (30s):** Open a downloadable SIFT cheat sheet/WhatsApp card, then the Resource Guide and Swagger.
7. **Close (20s):** All KPIs exceeded; open-source repo with 20 passing tests, full documentation, technical report, and slides.

## Likely viva questions → short answers
| Question | Answer |
| :--- | :--- |
| Why Spring Boot? | Production-grade Java stack, security, JPA, OpenAPI for maintainable APIs. |
| How does fact-check work? | Heuristic SIFT engine classifies claims with confidence + recommended action (demo-ready; extensible to live IFCN APIs). |
| How did you measure impact? | Baseline vs post-campaign surveys, paired analysis (−62.1%, p&lt;0.001), social analytics report. |
| What is SIFT? | Stop, Investigate source, Find better coverage, Trace claims (Mike Caulfield). |
| How is auth secured? | BCrypt passwords, DB-backed `UserDetailsService`, stateless JWT filter, admin-only survey/module/analytics routes. |
| What is tested? | 21 JUnit 5/MockMvc tests: context, public/admin route rules, login + JWT, role-escalation guard, analyzer, modules, surveys, workshops, analytics, capacity and duplicate-email rules. |
| Repo practices? | Modular packages, 20 passing tests, README, LICENSE, docs, changelog, technical report, slides, meaningful commits. |

## Final submission checklist (bring / have ready)
- [x] GitHub source code
- [x] Updated README + docs
- [x] Technical report (`report/TECHNICAL_REPORT.md`)
- [x] Testing report (`docs/TESTING_REPORT.md`)
- [x] Presentation slides (`presentation/FINAL_EVALUATION_SLIDES.html`)
- [x] Final impact report (`docs/FINAL_IMPACT_REPORT.md`)
- [ ] Live deployment URL (optional; local demo is enough if network is stable)
- [ ] Field-work evidence folder (photos/videos/surveys) **if your subject is 25UCI-203**
- [ ] Mentor approval / prior review notes if asked

## If something breaks during demo
1. Refresh the page.
2. Use sample claim buttons (client fallback still shows SIFT output).
3. Switch to Swagger and call `/api/v1/fact-check/verify` + `/api/v1/analytics/overview`.
4. Show GitHub docs/`FINAL_IMPACT_REPORT.md` as backup evidence.
