package com.example.controller;

import com.example.DTO.AnalysticsResponse;
import com.example.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analystics")
@Tag(name = "Analytics API", description = "Endpoints for collecting and summarizing analytics data")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/summery")
    @Operation(summary = "Get Analytics Summary", description = "Retrieve summarized analytics data for the system")
    public ResponseEntity<AnalysticsResponse> getSummey() {
        return ResponseEntity.ok(analyticsService.collectData());
    }
}
