package com.berkan.speed_monitor.controller;

import com.berkan.speed_monitor.model.SpeedRecord;
import com.berkan.speed_monitor.repository.SpeedRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/speedtests")
public class SpeedController {

    private final SpeedRecordRepository repository;

    public SpeedController(SpeedRecordRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SpeedRecord> getAllRecords() {
        return repository.findAll();
    }

    @PostMapping
    public SpeedRecord addRecord(@RequestBody SpeedRecord record) {
        if (record.getTimestamp() == null) {
            record.setTimestamp(LocalDateTime.now());
        }
        return repository.save(record);
    }
}