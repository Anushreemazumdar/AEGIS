package com.aegis.aegis_backend.controller;


import com.aegis.aegis_backend.dto.CreateMissionRequest;
import com.aegis.aegis_backend.dto.MissionResponse;
import com.aegis.aegis_backend.entity.Mission;
import com.aegis.aegis_backend.service.MissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMission(@Valid @RequestBody CreateMissionRequest request) {
        Mission mission = missionService.createMission(request);

        Map<String, Object> response = new HashMap<>();
        response.put("missionId", mission.getId());
        response.put("status", mission.getStatus().name());
        response.put("message", "Emergency mission created successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionResponse> getMission(@PathVariable Long id) {
        Mission mission = missionService.getMissionById(id);
        return ResponseEntity.ok(MissionResponse.fromEntity(mission));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, String>> getMissionStatus(@PathVariable Long id) {
        Mission.MissionStatus status = missionService.getMissionStatus(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", status.name());
        return ResponseEntity.ok(response);
    }
}
