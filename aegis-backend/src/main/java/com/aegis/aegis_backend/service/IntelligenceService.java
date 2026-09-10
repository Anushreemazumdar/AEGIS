package com.aegis.aegis_backend.service;


import com.aegis.aegis_backend.dto.CreateIntelligenceRequest;
import com.aegis.aegis_backend.entity.Intelligence;
import com.aegis.aegis_backend.entity.Mission;
import com.aegis.aegis_backend.repository.IntelligenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IntelligenceService {

    private final IntelligenceRepository intelligenceRepository;
    private final MissionService missionService;

    public IntelligenceService(IntelligenceRepository intelligenceRepository, MissionService missionService) {
        this.intelligenceRepository = intelligenceRepository;
        this.missionService = missionService;
    }

    @Transactional
    public Intelligence addIntelligence(Long missionId, CreateIntelligenceRequest req) {
        // Throws ResourceNotFoundException (404) if the mission doesn't exist —
        // reusing the service method we already built in Phase 1.
        Mission mission = missionService.getMissionById(missionId);

        Intelligence intel = new Intelligence();
        intel.setMission(mission);
        intel.setType(req.getType());
        intel.setSource(req.getSource());
        intel.setData(req.getData());
        intel.setConfidence(req.getConfidence());
        intel.setVerificationStatus(Intelligence.VerificationStatus.PENDING);

        return intelligenceRepository.save(intel);
    }

    public List<Intelligence> getIntelligenceForMission(Long missionId) {
        // Ensure the mission exists first, so a bad missionId returns a clean 404
        // instead of silently returning an empty list.
        missionService.getMissionById(missionId);
        return intelligenceRepository.findByMissionId(missionId);
    }
}