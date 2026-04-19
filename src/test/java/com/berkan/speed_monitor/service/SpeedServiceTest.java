package com.berkan.speed_monitor.service;

import com.berkan.speed_monitor.dto.SpeedAverageDTO;
import com.berkan.speed_monitor.dto.SpeedRecordDTO;
import com.berkan.speed_monitor.model.SpeedRecord;
import com.berkan.speed_monitor.repository.SpeedRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpeedServiceTest {

    @Mock
    private SpeedRecordRepository repository;

    @InjectMocks
    private SpeedService speedService;

    @Test
    void getAverages_shouldReturnCorrectAverages() {
        List<SpeedRecord> records = List.of(
                new SpeedRecord(1L, 100.0, 50.0, 10, LocalDateTime.now()),
                new SpeedRecord(2L, 200.0, 100.0, 20, LocalDateTime.now()),
                new SpeedRecord(3L, 300.0, 150.0, 30, LocalDateTime.now())
        );
        when(repository.findAll()).thenReturn(records);

        SpeedAverageDTO result = speedService.getAverages();

        assertEquals(200.0, result.getAvgDownloadMbps());
        assertEquals(100.0, result.getAvgUploadMbps());
        assertEquals(20.0, result.getAvgPingMs());
        assertEquals(3, result.getCount());
    }

    @Test
    void getAverages_shouldReturnZerosWhenNoRecords() {
        when(repository.findAll()).thenReturn(List.of());

        SpeedAverageDTO result = speedService.getAverages();

        assertEquals(0.0, result.getAvgDownloadMbps());
        assertEquals(0.0, result.getAvgUploadMbps());
        assertEquals(0.0, result.getAvgPingMs());
        assertEquals(0, result.getCount());
    }

    @Test
    void addRecord_shouldPersistWithTimestamp() {
        SpeedRecordDTO dto = new SpeedRecordDTO(150.0, 75.0, 12);
        SpeedRecord saved = new SpeedRecord(1L, 150.0, 75.0, 12, LocalDateTime.now());
        when(repository.save(any(SpeedRecord.class))).thenReturn(saved);

        SpeedRecord result = speedService.addRecord(dto);

        assertNotNull(result.getTimestamp());
        assertEquals(150.0, result.getDownloadSpeedMbps());
        verify(repository, times(1)).save(any(SpeedRecord.class));
    }

    @Test
    void exportCsv_shouldContainHeaderAndAllRows() {
        List<SpeedRecord> records = List.of(
                new SpeedRecord(1L, 100.0, 50.0, 10, LocalDateTime.of(2026, 4, 19, 10, 0)),
                new SpeedRecord(2L, 200.0, 100.0, 20, LocalDateTime.of(2026, 4, 19, 11, 0))
        );
        when(repository.findAll()).thenReturn(records);

        String csv = speedService.exportCsv();

        assertTrue(csv.startsWith("id,downloadSpeedMbps,uploadSpeedMbps,pingMs,timestamp\n"));
        assertTrue(csv.contains("1,100.0,50.0,10,"));
        assertTrue(csv.contains("2,200.0,100.0,20,"));
    }
}
