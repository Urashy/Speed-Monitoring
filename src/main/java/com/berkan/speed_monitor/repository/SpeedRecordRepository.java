package com.berkan.speed_monitor.repository;

import com.berkan.speed_monitor.model.SpeedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedRecordRepository extends JpaRepository<SpeedRecord, Long> {
    
}