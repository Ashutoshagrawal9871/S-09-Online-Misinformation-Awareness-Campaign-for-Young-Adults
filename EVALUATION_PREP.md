# CUSoC Final Evaluation — Tomorrow Prep (TruthLens S-09)

## Before you join the meeting
1. Open the **Google Sheet** schedule and note your exact slot + the **correct meeting link** (ignore older email links).
2. Join **5 minutes early**, mic/camera ready, screen-share tested.
3. Keep these tabs open:
   - App: `http://localhost:8080`
   - Swagger: `http://localhost:8080/swagger-ui.html`
   - Slides: `http://localhost:8080/presentation/FINAL_EVALUATION_SLIDES.html`
   - GitHub repo README
4. Start the app before your slot:

```powershell
cd C:\S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults
.\mvnw.cmd spring-boot:run
```

Optional: run tests once in the morning — `.\mvnw.cmd test -q`

## 4-minute live demo script (say this)
1. **Problem (20s):** Viral WhatsApp health myths and phishing hurt young adults. TruthLens teaches SIFT and measures behavior change.
2. **SIFT Analyzer (60s):** Click sample “Health Cure” → Verify → show FALSE verdict + 4 SIFT steps.
3. **Quiz (40s):** Submit 4 answers → show susceptibility score saved to backend.
4. **Workshops (30s):** Register a demo student → capacity updates.
5. **Impact (40s):** Analytics section — 1,415 reach, 118 attendees, −62.1% susceptibility, +48.3% verification, 14 multipliers.
6. **Docs/Swagger (30s):** Open Resource Guide + Swagger to prove engineering maturity.
7. **Close (20s):** All KPIs exceeded; open-source repo with tests, docs, report, slides.

## Likely viva questions → short answers
| Question | Answer |
| :--- | :--- |
| Why Spring Boot? | Production-grade Java stack, security, JPA, OpenAPI for maintainable APIs. |
| How does fact-check work? | Heuristic SIFT engine classifies claims with confidence + recommended action (demo-ready; extensible to live IFCN APIs). |
| How did you measure impact? | Baseline vs post-campaign surveys, paired analysis (−62.1%, p&lt;0.001), social analytics report. |
| What is SIFT? | Stop, Investigate source, Find better coverage, Trace claims (Mike Caulfield). |
| Repo practices? | Modular packages, tests, README, LICENSE, docs, changelog, meaningful commits. |

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
