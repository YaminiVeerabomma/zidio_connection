package com.example.controller;

import com.example.DTO.AnalysticsResponse;
import com.example.DTO.ApplicationDTO;
import com.example.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analystics")
@Tag(name = "Analytics API", description = "Endpoints for collecting and summarizing analytics data")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;


    @GetMapping
    @Operation(summary = "Get all analytics data",
               description = "Retrieve counts of students, recruiters, jobs, applications, payments, etc.")
    public ResponseEntity<AnalysticsResponse> getAllAnalytics() {
        return ResponseEntity.ok(analyticsService.collectData());
    }

    @GetMapping("/applications/weekly")
    @Operation(summary = "Weekly Applications",
               description = "Get application counts grouped by week")
    public ResponseEntity<List<ApplicationDTO>> getWeeklyTrackers() {
        return ResponseEntity.ok(analyticsService.getWeeklyApplicationsTracker());
    }
}
