# Free deployment (Railway limit exceeded)

TruthLens runs on **H2 in memory** by default, so you do **not** need a paid database for the CUSoC demo.

## Option A — Render (recommended, free, no card)

1. Push this repo to GitHub (already configured as `origin`).
2. Open https://dashboard.render.com/ and sign in with GitHub.
3. Click **New +** → **Blueprint** → select this repository  
   **or** **New +** → **Web Service** → connect the repo.
4. Settings:
   - **Runtime:** Docker
   - **Plan:** Free
   - **Health Check Path:** `/`
5. Add env var (optional but recommended):
   - `JAVA_TOOL_OPTIONS` = `-XX:MaxRAMPercentage=70.0 -XX:+UseSerialGC -Xss512k`
   - `H2_CONSOLE_ENABLED` = `false`
6. Deploy. First build takes ~5–10 minutes.
7. Open the public URL Render gives you (like `https://truthlens-s09.onrender.com`).

**Demo tip:** Free services sleep after ~15 minutes idle. Open the URL **2–3 minutes before** your evaluation slot so it wakes up.

## Option B — Temporary public URL during evaluation (fastest)

If cloud build is slow, run locally and tunnel:

```powershell
.\mvnw.cmd spring-boot:run
# other terminal:
npx --yes localtunnel --port 8080
```

Share the `https://….loca.lt` URL with evaluators.

## After deploy, verify

- `/` — website
- `/swagger-ui.html` — API docs
- `/presentation/FINAL_EVALUATION_SLIDES.html` — slides
- Sample SIFT claim → FALSE verdict
