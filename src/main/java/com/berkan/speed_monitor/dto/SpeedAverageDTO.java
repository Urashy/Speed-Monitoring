package com.berkan.speed_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeedAverageDTO {
    private double avgDownloadMbps;
    private double avgUploadMbps;
    private double avgPingMs;
    private int count;
}
