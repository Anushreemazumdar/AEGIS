package com.aegis.aegis_backend.service;


import com.aegis.aegis_backend.dto.CreateMissionRequest;
import com.aegis.aegis_backend.entity.Mission;
import com.aegis.aegis_backend.exception.ResourceNotFoundException;
import com.aegis.aegis_backend.repository.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    // Constructor injection: Spring automatically supplies the MissionRepository
    // bean here at startup. No @Autowired needed on constructors since Spring 4.3+
    // when there's only one constructor.
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Transactional
    public Mission createMission(CreateMissionRequest req) {
        Mission mission = new Mission();
        mission.setEmergencyType(req.getEmergencyType());
        mission.setLocation(req.getLocation());
        mission.setRequest(req.getRequest());
        mission.setBudget(req.getBudget());
        mission.setSpent(0.0);
        mission.setStatus(Mission.MissionStatus.CREATED);

        return missionRepository.save(mission);
    }

    public Mission getMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mission not found with id: " + id));
    }

    public Mission.MissionStatus getMissionStatus(Long id) {
        return getMissionById(id).getStatus();
    }
    @Transactional
    public Mission updateStatus(Long id, Mission.MissionStatus status) {
        Mission mission = getMissionById(id);
        mission.setStatus(status);
        return missionRepository.save(mission);
    }
}
