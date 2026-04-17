package com.berkan.speed_monitor.model; 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class SpeedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double downloadSpeedMbps;
    private double uploadSpeedMbps;
    private int pingMs;
    private LocalDateTime timestamp;

    public SpeedRecord() {
    }

    // --- GETTERS ET SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDownloadSpeedMbps() {
        return downloadSpeedMbps;
    }

    public void setDownloadSpeedMbps(double downloadSpeedMbps) {
        this.downloadSpeedMbps = downloadSpeedMbps;
    }

    public double getUploadSpeedMbps() {
        return uploadSpeedMbps;
    }

    public void setUploadSpeedMbps(double uploadSpeedMbps) {
        this.uploadSpeedMbps = uploadSpeedMbps;
    }

    public int getPingMs() {
        return pingMs;
    }

    public void setPingMs(int pingMs) {
        this.pingMs = pingMs;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}