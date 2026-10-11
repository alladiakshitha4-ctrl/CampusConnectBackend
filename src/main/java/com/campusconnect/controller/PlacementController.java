package com.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.dto.PlacementReadinessDTO;
import com.campusconnect.service.PlacementService;

@RestController
@RequestMapping("/placement")
@CrossOrigin(origins = "http://localhost:5173")
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    @GetMapping("/readiness/{studentId}")
    public ResponseEntity<PlacementReadinessDTO> getReadiness(
            @PathVariable Long studentId,
            @RequestParam String targetRole) {

        return ResponseEntity.ok(
                placementService.calculateReadiness(
                        studentId, targetRole));
    }
}