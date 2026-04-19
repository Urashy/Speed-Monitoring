package com.berkan.speed_monitor.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeedRecordDTO {

    @Positive
    private double downloadSpeedMbps;

    @Positive
    private double uploadSpeedMbps;

    @Positive
    private int pingMs;
}
