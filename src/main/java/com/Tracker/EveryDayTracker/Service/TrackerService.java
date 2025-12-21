package com.Tracker.EveryDayTracker.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Tracker.EveryDayTracker.Entity.Task;
import com.Tracker.EveryDayTracker.Repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TrackerService {

    @Autowired
    private TaskRepository repository;

    public Task getTodayTask() {
        return repository.findByDate(LocalDate.now())
                .orElseGet(() -> {
                    Task t = new Task();
                    t.setDate(LocalDate.now());
                    return t;
                });
    }

    public void saveOrUpdate(Task incoming) {

        Task task = repository.findByDate(incoming.getDate())
                .orElseGet(() -> {
                    Task t = new Task();
                    t.setDate(incoming.getDate());
                    return t;
                });

        task.setDsa(incoming.isDsa());
        task.setProjectDev(incoming.isProjectDev());
        task.setMicroServicesApi(incoming.isMicroServicesApi());
        task.setCpp(incoming.isCpp());

        repository.save(task);
    }

    public List<Task> getHistory() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
    
    @Transactional
    public void deleteByDate(LocalDate date) {
    	
    	repository.deleteByDate(date);
    }
}
