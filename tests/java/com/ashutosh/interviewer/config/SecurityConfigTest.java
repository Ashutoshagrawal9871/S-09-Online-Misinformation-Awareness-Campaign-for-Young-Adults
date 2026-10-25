package com.ashutosh.interviewer.config;

import com.ashutosh.interviewer.service.CampaignService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CampaignService campaignService;

    @Test
    @DisplayName("GET /swagger-ui/index.html should be accessible without authentication")
    void swaggerUi_ShouldBePubliclyAccessible() throws Exception {
        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/campaign/modules should be accessible without authentication")
    void campaignModules_ShouldBePubliclyAccessible() throws Exception {
        mockMvc.perform(get("/api/v1/campaign/modules"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/protected-resource should return 403 Forbidden when unauthenticated")
    void protectedEndpoint_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/protected-resource"))
                .andExpect(status().isForbidden());
    }
}
