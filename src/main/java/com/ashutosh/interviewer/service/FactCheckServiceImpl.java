package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.FactCheckRequest;
import com.ashutosh.interviewer.dto.FactCheckResult;
import com.ashutosh.interviewer.model.FactCheckQuery;
import com.ashutosh.interviewer.repository.FactCheckQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FactCheckServiceImpl implements FactCheckService {

    private final FactCheckQueryRepository queryRepository;

    private static final List<FactKnowledge> KNOWLEDGE_BASE = List.of(
            new FactKnowledge(
                    List.of("5g", "radiation", "covid", "virus", "tower"),
                    "HEALTH",
                    "VERIFIED_FALSE",
                    98,
                    "5G technology and mobile radiation do not cause or transmit viral infections or Covid-19.",
                    "Viruses cannot travel on radio waves or mobile networks. Covid-19 is spread through respiratory droplets. International health bodies (WHO, FCC) have repeatedly confirmed radio frequency transmissions have zero biological mechanism for virus replication.",
                    List.of("Stop: Pause before forwarding panic messages.", "Investigate: Check WHO & FCC electromagnetic safety studies.", "Find trusted coverage: Search Alt News & BOOM Live health archives.", "Trace: The claim originated on unregulated conspiracy forums in early 2020."),
                    List.of("WHO Mythbusters: 5G Mobile Networks Do Not Spread COVID-19", "BOOM Live: Debunking 5G Radiation Hoaxes", "Alt News Science Fact-Checks"),
                    List.of("Sensational emotional framing", "Lacks accredited scientific study citations", "Classic viral forwarded text format"),
                    "Do NOT forward this rumor. Refer to official WHO or health ministry portals."
            ),
            new FactKnowledge(
                    List.of("lemon", "baking soda", "cure cancer", "alkaline", "hot water"),
                    "HEALTH",
                    "VERIFIED_FALSE",
                    95,
                    "Drinking hot water with lemon or baking soda does not cure cancer.",
                    "No scientific evidence supports the claim that lemon juice or alkaline diets cure cancer. While lemons provide vitamin C and hydration, claiming they replace oncology therapy is dangerous medical misinformation.",
                    List.of("Stop: Do not delay medical treatment based on viral home remedy claims.", "Investigate: Verify source with ICMR or American Cancer Society.", "Find peer-reviewed oncology literature.", "Trace: Viral copy-paste chains from Yahoo groups in 2011."),
                    List.of("American Cancer Society Medical Fact Check", "Snopes: Hot Lemon Water Cancer Cure Myth", "BOOM Live Health Check"),
                    List.of("Promises miracle cures without side effects", "Urges readers to 'Share with everyone you love'", "Misattributes quotes to anonymous doctors"),
                    "Consult an oncologist for medical advice. Report health misinformation on social channels."
            ),
            new FactKnowledge(
                    List.of("unesco", "best national anthem", "modi", "jana gana mana", "declared"),
                    "POLITICS",
                    "VERIFIED_FALSE",
                    99,
                    "UNESCO has never declared any country's national anthem as the 'Best Anthem in the World'.",
                    "UNESCO officially refuted this recurring hoax. UNESCO does not rank, rate, or confer awards on national anthems or political leaders through random internet polls or viral messages.",
                    List.of("Stop: Recognize recurring patriotic chain forwards.", "Investigate: Search UNESCO's official website (unesco.org).", "Find trusted coverage: Alt News has debunked this recurring WhatsApp rumor since 2016.", "Trace: Viral email forward from 2008 recycled annually."),
                    List.of("UNESCO Official Statement on National Anthem Rumors", "Alt News: No, UNESCO Did Not Declare Jana Gana Mana Best Anthem", "BOOM Live Fact Check"),
                    List.of("Recycled hoax circulated around national holidays", "Claims UNESCO issued an unverified press release", "'Forward to make everyone proud' chain hook"),
                    "Politely reply in family/student WhatsApp groups with the Alt News / UNESCO debunk link."
            ),
            new FactKnowledge(
                    List.of("free recharge", "government", "yojana", "laptop", "subsidy", "click here", "link"),
                    "FINANCIAL",
                    "VERIFIED_FALSE",
                    97,
                    "Phishing scam: Government or telecom providers are not offering free recharges or laptops via WhatsApp links.",
                    "Scammers create fake domains imitating government portals to harvest personal phone numbers, bank credentials, and OTPs. Official government schemes are only hosted on '.gov.in' or '.nic.in' domains.",
                    List.of("Stop: NEVER click unknown links offering free gifts or money.", "Investigate domain: Look for genuine '.gov.in' extensions, not bit.ly or .xyz.", "Find coverage on PIB Fact Check (Press Information Bureau).", "Trace: Domain registration shows recently created anonymous phishing pages."),
                    List.of("PIB Fact Check Warning on Fake Subsidy Schemes", "CyberPeace Foundation Phishing Alert", "Cert-In Advisory on Social Media Financial Scams"),
                    List.of("Urgency trigger ('Offer valid for next 2 hours only')", "Unofficial domain extension (.club, .free, .tk)", "Requires forwarding to 5 WhatsApp groups to claim"),
                    "Immediately block and report the sender. Never enter OTPs or personal credentials."
            ),
            new FactKnowledge(
                    List.of("deepfake", "ai video", "voice clone", "face swap", "manipulated"),
                    "DEEPFAKES",
                    "MISLEADING",
                    92,
                    "Synthetic media / Deepfake alert: AI-generated videos and audio clips are increasingly cloned to manipulate public opinion.",
                    "Deepfakes use Generative Adversarial Networks (GANs) or diffusion models to replace facial expressions or generate synthetic voices. Look for irregular eye blinking, audio-visual desync, and unnatural boundary artifacts around ears and teeth.",
                    List.of("Stop: Do not react emotionally to shocking celebrity/politician videos.", "Investigate: Use Sieve, InVID-WeVerify video verification plugins.", "Find original unedited press conference or speech video.", "Trace: Search video keyframes on Google Lens and Yandex Reverse Image."),
                    List.of("InVID Verification Plugin Guide", "Alt News Deepfake Debunk Archive", "Deepfake Forensics & Boomerang Video Analysis"),
                    List.of("Unnatural lighting differences between face and background", "Robotic cadence or audio phasing artifacts", "Blurry mouth interior and missing natural micro-expressions"),
                    "Verify original unedited video source using reverse video search before reposting."
            )
    );

    @Override
    @Transactional
    public FactCheckResult verifyClaim(FactCheckRequest request) {
        String claimLower = request.getClaim().toLowerCase();

        // Check if matching known misinformation patterns
        FactKnowledge matched = null;
        int maxMatches = 0;
        for (FactKnowledge knowledge : KNOWLEDGE_BASE) {
            int count = 0;
            for (String kw : knowledge.keywords) {
                if (claimLower.contains(kw)) {
                    count++;
                }
            }
            if (count > maxMatches && count >= 1) {
                maxMatches = count;
                matched = knowledge;
            }
        }

        FactCheckResult result;
        if (matched != null) {
            result = FactCheckResult.builder()
                    .claim(request.getClaim())
                    .verdict(matched.verdict)
                    .confidenceScore(matched.confidenceScore)
                    .summary(matched.summary)
                    .detailedAnalysis(matched.detailedAnalysis)
                    .factCheckFrameworkSteps(matched.siftSteps)
                    .verificationSources(matched.sources)
                    .warningFlags(matched.warningFlags)
                    .recommendedAction(matched.recommendedAction)
                    .build();
        } else {
            // General verification using heuristic SIFT analysis
            boolean hasSensationalism = claimLower.contains("urgent") || claimLower.contains("shocking") || claimLower.contains("share before delete") || claimLower.contains("secret");
            boolean hasMedicalClaims = claimLower.contains("cure") || claimLower.contains("doctor said") || claimLower.contains("miracle");
            boolean hasFinancialHooks = claimLower.contains("free") || claimLower.contains("win") || claimLower.contains("click");

            String verdict = (hasSensationalism || hasMedicalClaims || hasFinancialHooks) ? "MISLEADING" : "UNVERIFIED";
            int confidence = (hasSensationalism || hasMedicalClaims || hasFinancialHooks) ? 78 : 60;

            List<String> flags = new ArrayList<>();
            if (hasSensationalism) flags.add("Sensationalist language detected ('urgent', 'breaking', 'shocking')");
            if (hasMedicalClaims) flags.add("Unverified medical claim without clinical trial backing");
            if (hasFinancialHooks) flags.add("Potential scam hook ('free rewards', 'click to claim')");
            if (flags.isEmpty()) flags.add("Unverified viral assertion requiring lateral verification");

            result = FactCheckResult.builder()
                    .claim(request.getClaim())
                    .verdict(verdict)
                    .confidenceScore(confidence)
                    .summary("Claim requires lateral verification using SIFT methodology and accredited fact-checking databases.")
                    .detailedAnalysis("This claim lacks citation of primary peer-reviewed sources, institutional press releases, or official gazettes. Heuristic analysis suggests applying lateral reading: open new tabs to check if BOOM Live, Alt News, Snopes, or Reuters Fact Check have covered this story.")
                    .factCheckFrameworkSteps(List.of(
                            "Stop: Avoid reacting or forwarding immediately.",
                            "Investigate the Source: Look up the author or domain reputation on Wikipedia or Whois.",
                            "Find Better Coverage: Search the core statement with 'fact check' keyword on Google.",
                            "Trace to Original Context: Find where the quote, image, or video first appeared."
                    ))
                    .verificationSources(List.of(
                            "BOOM Live Fact Check (boomlive.in)",
                            "Alt News Archive (altnews.in)",
                            "PIB Fact Check (pib.gov.in)",
                            "Google Fact Check Explorer (toolbox.google.com/factcheck)"
                    ))
                    .warningFlags(flags)
                    .recommendedAction("Apply lateral reading: Cross-examine with two independent fact-checking organizations before sharing.")
                    .build();
        }

        // Save to query log
        FactCheckQuery savedQuery = queryRepository.save(FactCheckQuery.builder()
                .claimText(request.getClaim())
                .category(request.getCategory() != null ? request.getCategory().toUpperCase() : "GENERAL")
                .verdict(result.getVerdict())
                .confidenceScore(result.getConfidenceScore())
                .debunkSummary(result.getSummary())
                .primarySources(String.join(", ", result.getVerificationSources()))
                .verificationMethod("SIFT_FRAMEWORK")
                .checkedAt(LocalDateTime.now())
                .build());

        result.setQueryId(savedQuery.getId());
        return result;
    }

    @Override
    public List<FactCheckQuery> getRecentQueries() {
        return queryRepository.findTop10ByOrderByCheckedAtDesc();
    }

    @Override
    public List<FactCheckQuery> getQueriesByCategory(String category) {
        return queryRepository.findByCategory(category.toUpperCase());
    }

    private static class FactKnowledge {
        List<String> keywords;
        String category;
        String verdict;
        int confidenceScore;
        String summary;
        String detailedAnalysis;
        List<String> siftSteps;
        List<String> sources;
        List<String> warningFlags;
        String recommendedAction;

        public FactKnowledge(List<String> keywords, String category, String verdict, int confidenceScore,
                             String summary, String detailedAnalysis, List<String> siftSteps,
                             List<String> sources, List<String> warningFlags, String recommendedAction) {
            this.keywords = keywords;
            this.category = category;
            this.verdict = verdict;
            this.confidenceScore = confidenceScore;
            this.summary = summary;
            this.detailedAnalysis = detailedAnalysis;
            this.siftSteps = siftSteps;
            this.sources = sources;
            this.warningFlags = warningFlags;
            this.recommendedAction = recommendedAction;
        }
    }
}
