package com.ashutosh.interviewer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/api/status")
    public ResponseEntity<Map<String, Object>> status() {
        return ResponseEntity.ok(Map.of(
                "application", "TruthLens - Misinformation Awareness Campaign Platform",
                "version", "1.0.0",
                "status", "UP",
                "campaign", "S-09: Online Misinformation Awareness Campaign for Young Adults",
                "swaggerDocumentation", "/swagger-ui/index.html"
        ));
    }
}
