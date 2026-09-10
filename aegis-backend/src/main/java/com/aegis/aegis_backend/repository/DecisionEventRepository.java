package com.aegis.aegis_backend.repository;



import com.aegis.aegis_backend.entity.DecisionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecisionEventRepository extends JpaRepository<DecisionEvent, Long> {
    List<DecisionEvent> findByMissionIdOrderByTimestampAsc(Long missionId);
}