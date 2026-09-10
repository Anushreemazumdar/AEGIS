package com.aegis.aegis_backend.repository;
import com.aegis.aegis_backend.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    // No methods needed yet — JpaRepository already gives us:
    // save(), findById(), findAll(), deleteById(), etc.
}