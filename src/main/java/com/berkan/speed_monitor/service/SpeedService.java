package com.berkan.speed_monitor.service;

import com.berkan.speed_monitor.dto.SpeedAverageDTO;
import com.berkan.speed_monitor.dto.SpeedRecordDTO;
import com.berkan.speed_monitor.model.SpeedRecord;
import com.berkan.speed_monitor.repository.SpeedRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpeedService {

    private final SpeedRecordRepository repository;

    public SpeedService(SpeedRecordRepository repository) {
        this.repository = repository;
    }

    public List<SpeedRecord> getAllRecords() {
        return repository.findAll();
    }

    public SpeedRecord addRecord(SpeedRecordDTO dto) {
        SpeedRecord record = new SpeedRecord(
                null,
                dto.getDownloadSpeedMbps(),
                dto.getUploadSpeedMbps(),
                dto.getPingMs(),
                LocalDateTime.now()
        );
        return repository.save(record);
    }

    public SpeedAverageDTO getAverages() {
        List<SpeedRecord> records = repository.findAll();
        if (records.isEmpty()) {
            return new SpeedAverageDTO(0, 0, 0, 0);
        }
        double avgDownload = records.stream().mapToDouble(SpeedRecord::getDownloadSpeedMbps).average().orElse(0);
        double avgUpload = records.stream().mapToDouble(SpeedRecord::getUploadSpeedMbps).average().orElse(0);
        double avgPing = records.stream().mapToInt(SpeedRecord::getPingMs).average().orElse(0);
        return new SpeedAverageDTO(avgDownload, avgUpload, avgPing, records.size());
    }

    public String exportCsv() {
        List<SpeedRecord> records = repository.findAll();
        StringBuilder sb = new StringBuilder("id,downloadSpeedMbps,uploadSpeedMbps,pingMs,timestamp\n");
        for (SpeedRecord r : records) {
            sb.append(r.getId()).append(',')
              .append(r.getDownloadSpeedMbps()).append(',')
              .append(r.getUploadSpeedMbps()).append(',')
              .append(r.getPingMs()).append(',')
              .append(r.getTimestamp()).append('\n');
        }
        return sb.toString();
    }
}
