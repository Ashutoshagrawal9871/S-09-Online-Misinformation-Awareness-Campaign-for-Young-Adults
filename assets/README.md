# Assets

Design assets used by the campaign website and print/digital distribution. At build time this folder is copied to `static/assets/`, so files are served at `/assets/<filename>`.

| File | Purpose |
| :--- | :--- |
| `sift-cheat-sheet.svg` | Four-step SIFT poster (print/digital infographic) |
| `whatsapp-rumor-card.svg` | Calm reply template and red-flag checklist for WhatsApp groups |
| `deepfake-checklist.svg` | Five visual clues for spotting AI-generated video |
| `favicon.svg` | Site icon |

## Usage

- The website links to these files in the **Campaign materials** section with a download attribute.
- SVGs are vector files; they can be exported to PNG/PDF for print without quality loss.
- Keep the attribution line visible when redistributing.

## License

Campaign assets are part of TruthLens S-09 and are licensed under **CC BY-SA 4.0**. Credit the project and share adaptations under the same license.

## Adding new assets

1. Add the source file here.
2. Reference it from `src/main/resources/static/index.html` as `assets/<filename>` or document it in `docs/CAMPAIGN_MATERIALS.md`.
3. Rebuild or restart the app so Maven copies it into the served static bundle.
