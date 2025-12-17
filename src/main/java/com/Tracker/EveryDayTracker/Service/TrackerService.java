package com.Tracker.EveryDayTracker.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tracker.EveryDayTracker.Entity.Task;
import com.Tracker.EveryDayTracker.Repository.TaskRepository;

@Service
public class TrackerService {
	
	@Autowired
	private TaskRepository repository;
	
	public Task saveOrUpdateDailyRecord(Task record) {
		
		if(record.getDate() == null) {
			record.setDate(LocalDate.now());
		}
		
		Optional<Task> existingRecord = repository.findByDate(record.getDate());
		if(existingRecord.isPresent()) {
			record.setId(existingRecord.get().getId());
		}
		
		double score = calculateScore(record);
		record.setProductivityScore(score);
		
		return repository.save(record);
	}
	
	private double calculateScore(Task record) {
        double completedTasks = 0;
        double totalTasks = 4.0; // DSA, Project, Microservices, C++

        if (record.isDSA()) completedTasks++;
        if (record.isProjectDev()) completedTasks++;
        if (record.isMicroServicesApi()) completedTasks++;
        if (record.isCpp()) completedTasks++;

        return (completedTasks / totalTasks) * 100;
    }

    /**
     * Retrieves all history sorted by date (descending).
     */
    public List<Task> getAllRecords() {
        return repository.findAll();
    }

    /**
     * Finds a specific day's record.
     */
    public Task getRecordByDate(LocalDate date) {
        return repository.findByDate(date).orElse(new Task());
    }
}
