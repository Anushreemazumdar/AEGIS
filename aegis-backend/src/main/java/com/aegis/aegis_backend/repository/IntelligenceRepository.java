package com.aegis.aegis_backend.repository;


import com.aegis.aegis_backend.entity.Intelligence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntelligenceRepository extends JpaRepository<Intelligence, Long> {

    // Spring Data JPA auto-generates the SQL for this from the method name alone:
    // SELECT * FROM intelligence WHERE mission_id = ?
    List<Intelligence> findByMissionId(Long missionId);
}
