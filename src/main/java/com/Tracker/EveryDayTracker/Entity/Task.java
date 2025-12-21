package com.Tracker.EveryDayTracker.Entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(
    name = "daily_tasks",
    uniqueConstraints = @UniqueConstraint(columnNames = "date")
)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private boolean dsa;
    private boolean projectDev;
    private boolean microServicesApi;
    private boolean cpp;

    private double productivityScore;

    public Task() {}

    @PrePersist
    @PreUpdate
    public void calculateProductivityScore() {
        int completed = 0;
        if (dsa) completed++;
        if (projectDev) completed++;
        if (microServicesApi) completed++;
        if (cpp) completed++;

        this.productivityScore = (completed / 4.0) * 100;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isDsa() { return dsa; }
    public void setDsa(boolean dsa) { this.dsa = dsa; }

    public boolean isProjectDev() { return projectDev; }
    public void setProjectDev(boolean projectDev) { this.projectDev = projectDev; }

    public boolean isMicroServicesApi() { return microServicesApi; }
    public void setMicroServicesApi(boolean microServicesApi) {
        this.microServicesApi = microServicesApi;
    }

    public boolean isCpp() { return cpp; }
    public void setCpp(boolean cpp) { this.cpp = cpp; }

    public double getProductivityScore() { return productivityScore; }
    public void setProductivityScore(double productivityScore) {
        this.productivityScore = productivityScore;
    }
}
