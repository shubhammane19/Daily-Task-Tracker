package com.Tracker.EveryDayTracker.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tracker.EveryDayTracker.Entity.Task;

import jakarta.persistence.Id;

public interface TaskRepository extends JpaRepository<Task,Id>{
	
	Optional<Task> findByDate(LocalDate date);
}
