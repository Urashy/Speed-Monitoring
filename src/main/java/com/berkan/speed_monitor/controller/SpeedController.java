package com.berkan.speed_monitor.controller;

import com.berkan.speed_monitor.dto.SpeedAverageDTO;
import com.berkan.speed_monitor.dto.SpeedRecordDTO;
import com.berkan.speed_monitor.model.SpeedRecord;
import com.berkan.speed_monitor.service.SpeedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speedtests")
public class SpeedController {

    private final SpeedService speedService;

    public SpeedController(SpeedService speedService) {
        this.speedService = speedService;
    }

    @GetMapping
    public List<SpeedRecord> getAllRecords() {
        return speedService.getAllRecords();
    }

    @PostMapping
    public SpeedRecord addRecord(@RequestBody @Valid SpeedRecordDTO dto) {
        return speedService.addRecord(dto);
    }

    @GetMapping("/average")
    public SpeedAverageDTO getAverages() {
        return speedService.getAverages();
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportCsv() {
        String csv = speedService.exportCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"speedtests.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}