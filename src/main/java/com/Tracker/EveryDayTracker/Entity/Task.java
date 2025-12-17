package com.Tracker.EveryDayTracker.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="daily_tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private LocalDate date;
	private boolean DSA;
	private boolean projectDev;
	private boolean microServicesApi;
	private boolean cpp;
	
	private double productivityScore;
	
	public void calproductivityScore() {
		
		double complete=0;
		
		if(DSA) complete++;
		if(projectDev) complete++;
		if(microServicesApi) complete++;
		if(cpp) complete++;
		
		this.productivityScore= (complete/4.0)*100;
	}
	
	public Task() {
			
	}

	public Task(Long id, LocalDate date, boolean dSA, boolean projectDev, boolean microServicesApi, boolean cpp,
			double productivityScore) {
		super();
		Id = id;
		this.date = date;
		DSA = dSA;
		this.projectDev = projectDev;
		this.microServicesApi = microServicesApi;
		this.cpp = cpp;
		this.productivityScore = productivityScore;
	}

	public Long getId() {
		return Id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setId(Long id) {
		Id = id;
	}

	public boolean isDSA() {
		return DSA;
	}

	public void setDSA(boolean dSA) {
		DSA = dSA;
	}

	public boolean isProjectDev() {
		return projectDev;
	}

	public void setProjectDev(boolean projectDev) {
		this.projectDev = projectDev;
	}

	public boolean isMicroServicesApi() {
		return microServicesApi;
	}

	public void setMicroServicesApi(boolean microServicesApi) {
		this.microServicesApi = microServicesApi;
	}

	public boolean isCpp() {
		return cpp;
	}

	public void setCpp(boolean cpp) {
		this.cpp = cpp;
	}

	public double getProductivityScore() {
		return productivityScore;
	}

	public void setProductivityScore(double productivityScore) {
		this.productivityScore = productivityScore;
	}
	
	
}
