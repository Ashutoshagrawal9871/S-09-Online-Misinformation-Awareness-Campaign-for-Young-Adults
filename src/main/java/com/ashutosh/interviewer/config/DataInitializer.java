package com.ashutosh.interviewer.config;

import com.ashutosh.interviewer.model.*;
import com.ashutosh.interviewer.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final MisinformationModuleRepository moduleRepository;
    private final WorkshopRepository workshopRepository;
    private final CampaignMetricRepository metricRepository;
    private final UserRepository userRepository;
    private final CampaignSurveyRepository surveyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUsers();
        seedModules();
        seedWorkshops();
        seedMetrics();
        seedSampleSurveys();
        log.info("TruthLens Misinformation Awareness Campaign database initialized successfully.");
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@truthlens.org")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .isActive(true)
                    .build();

            User student1 = User.builder()
                    .username("priya_sharma")
                    .email("priya.sharma@university.edu")
                    .password(passwordEncoder.encode("password123"))
                    .role("PEER_MULTIPLIER")
                    .isActive(true)
                    .build();

            User student2 = User.builder()
                    .username("rohit_verma")
                    .email("rohit.verma@college.ac.in")
                    .password(passwordEncoder.encode("password123"))
                    .role("STUDENT")
                    .isActive(true)
                    .build();

            userRepository.saveAll(List.of(admin, student1, student2));
        }
    }

    private void seedModules() {
        if (moduleRepository.count() == 0) {
            List<MisinformationModule> modules = List.of(
                    MisinformationModule.builder()
                            .title("The SIFT Framework for Media Literacy")
                            .description("Master Mike Caulfield's 4-step framework: Stop, Investigate source, Find better coverage, and Trace claims back to original context.")
                            .category("DIGITAL_LITERACY")
                            .contentUrl("/docs/FACT_CHECKING_RESOURCE_GUIDE.md")
                            .difficulty("BEGINNER")
                            .estimatedMinutes(12)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    MisinformationModule.builder()
                            .title("Identifying Deepfakes & Synthetic Media")
                            .description("Learn how generative AI, face swaps, and voice clones operate. Discover forensic clues like blink rates, boundary blur, and audio phasing.")
                            .category("DEEPFAKES")
                            .contentUrl("/docs/WORKSHOP_FACILITATION_GUIDE.md")
                            .difficulty("INTERMEDIATE")
                            .estimatedMinutes(20)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    MisinformationModule.builder()
                            .title("WhatsApp Rumors & Communal Disinformation")
                            .description("Analyze the anatomy of viral forwarded messages, emotional hooks, false attribution to authorities, and communal polarization tactics.")
                            .category("WHATSAPP_RUMORS")
                            .contentUrl("/docs/CAMPAIGN_MATERIALS.md")
                            .difficulty("BEGINNER")
                            .estimatedMinutes(15)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    MisinformationModule.builder()
                            .title("Health Myths & Pseudoscience Debunking")
                            .description("Evaluate unverified medical claims, miracle cures, anti-vaccine disinformation, and learn to verify via WHO, ICMR, and PubMed.")
                            .category("HEALTH_MYTHS")
                            .contentUrl("/docs/CAMPAIGN_MATERIALS.md")
                            .difficulty("INTERMEDIATE")
                            .estimatedMinutes(18)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    MisinformationModule.builder()
                            .title("Reverse Image & Video Verification Toolkit")
                            .description("Hands-on guide to Google Lens, Yandex Visual Search, TinEye, and InVID-WeVerify video keyframe extraction.")
                            .category("FACT_CHECKING_TOOLS")
                            .contentUrl("/docs/FACT_CHECKING_RESOURCE_GUIDE.md")
                            .difficulty("PRACTICAL")
                            .estimatedMinutes(25)
                            .createdAt(LocalDateTime.now())
                            .build(),

                    MisinformationModule.builder()
                            .title("Financial Scams & Social Engineering Phishing")
                            .description("Recognize fake government subsidy links, lottery messages, crypto scams, and counterfeit university portals.")
                            .category("FINANCIAL_SCAMS")
                            .contentUrl("/docs/CAMPAIGN_MATERIALS.md")
                            .difficulty("BEGINNER")
                            .estimatedMinutes(10)
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            moduleRepository.saveAll(modules);
        }
    }

    private void seedWorkshops() {
        if (workshopRepository.count() == 0) {
            Workshop w1 = Workshop.builder()
                    .title("Workshop 1: Hands-on Fact-Checking & SIFT Method")
                    .description("Interactive session covering Reverse Image Search, Google Fact Check Explorer, BOOM Live and Alt News verification workflows.")
                    .instructor("Prof. A. Sharma (Media Dept) & Guest Fact-Checker")
                    .scheduledAt(LocalDateTime.now().plusDays(3))
                    .location("Media Auditorium & Live Stream")
                    .meetingLink("https://meet.truthlens.org/workshop-1")
                    .maxCapacity(120)
                    .currentRegistrations(68)
                    .status("UPCOMING")
                    .category("FACT_CHECKING_TOOLS")
                    .build();

            Workshop w2 = Workshop.builder()
                    .title("Workshop 2: Deepfake Detection & WhatsApp Misinformation Forensics")
                    .description("Mastering synthetic media detection, InVID video tools, WhatsApp forward auditing, and peer multiplier action plans.")
                    .instructor("Dr. K. Iyer (Cyber Security & Digital Media)")
                    .scheduledAt(LocalDateTime.now().plusDays(10))
                    .location("Computer Science Hall & Zoom")
                    .meetingLink("https://meet.truthlens.org/workshop-2")
                    .maxCapacity(100)
                    .currentRegistrations(52)
                    .status("UPCOMING")
                    .category("DEEPFAKE_DETECTION")
                    .build();

            workshopRepository.saveAll(List.of(w1, w2));
        }
    }

    private void seedMetrics() {
        if (metricRepository.count() == 0) {
            List<CampaignMetric> metrics = List.of(
                    CampaignMetric.builder()
                            .metricKey("campaign_reach_impressions")
                            .metricName("Campaign Reach (Social Media + Campus)")
                            .metricValue(1415.0)
                            .targetValue(1000.0)
                            .unit("impressions")
                            .category("REACH")
                            .lastUpdated(LocalDateTime.now())
                            .build(),

                    CampaignMetric.builder()
                            .metricKey("workshop_participants")
                            .metricName("Workshop Participants Enrolled")
                            .metricValue(118.0)
                            .targetValue(100.0)
                            .unit("participants")
                            .category("EDUCATION")
                            .lastUpdated(LocalDateTime.now())
                            .build(),

                    CampaignMetric.builder()
                            .metricKey("susceptibility_score_reduction")
                            .metricName("Misinformation Susceptibility Score Reduction")
                            .metricValue(62.1)
                            .targetValue(30.0)
                            .unit("% reduction")
                            .category("BEHAVIOR_CHANGE")
                            .lastUpdated(LocalDateTime.now())
                            .build(),

                    CampaignMetric.builder()
                            .metricKey("fact_checking_adoption")
                            .metricName("Fact-Checking Behavior Adoption")
                            .metricValue(48.3)
                            .targetValue(40.0)
                            .unit("% positive shift")
                            .category("BEHAVIOR_CHANGE")
                            .lastUpdated(LocalDateTime.now())
                            .build(),

                    CampaignMetric.builder()
                            .metricKey("peer_multipliers_trained")
                            .metricName("Peer Multipliers Trained")
                            .metricValue(14.0)
                            .targetValue(10.0)
                            .unit("students")
                            .category("LEADERSHIP")
                            .lastUpdated(LocalDateTime.now())
                            .build()
            );

            metricRepository.saveAll(metrics);
        }
    }

    private void seedSampleSurveys() {
        if (surveyRepository.count() == 0) {
            // Seed baseline surveys (higher susceptibility, lower verification habits)
            List<CampaignSurvey> sampleSurveys = List.of(
                    CampaignSurvey.builder()
                            .surveyType("BASELINE")
                            .score(8) // high susceptibility
                            .verificationFrequency(2) // rarely checks
                            .primaryNewsSource("WHATSAPP")
                            .feedback("I usually forward news if it seems shocking or urgent.")
                            .completedAt(LocalDateTime.now().minusWeeks(3))
                            .build(),
                    CampaignSurvey.builder()
                            .surveyType("BASELINE")
                            .score(7)
                            .verificationFrequency(1)
                            .primaryNewsSource("INSTAGRAM")
                            .feedback("I don't know how to reverse search an image.")
                            .completedAt(LocalDateTime.now().minusWeeks(3))
                            .build(),
                    CampaignSurvey.builder()
                            .surveyType("POST_CAMPAIGN")
                            .score(2) // low susceptibility
                            .verificationFrequency(5) // always verifies
                            .primaryNewsSource("NEWS_SITES")
                            .feedback("The SIFT method and Google Lens have completely changed how I consume social media posts.")
                            .completedAt(LocalDateTime.now().minusDays(2))
                            .build(),
                    CampaignSurvey.builder()
                            .surveyType("POST_CAMPAIGN")
                            .score(3)
                            .verificationFrequency(4)
                            .primaryNewsSource("WHATSAPP")
                            .feedback("Now I debunk fake forwarded messages directly in family and batch WhatsApp groups using BOOM Live.")
                            .completedAt(LocalDateTime.now().minusDays(1))
                            .build()
            );

            surveyRepository.saveAll(sampleSurveys);
        }
    }
}
