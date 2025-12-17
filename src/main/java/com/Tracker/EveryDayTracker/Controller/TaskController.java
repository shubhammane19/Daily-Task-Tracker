package com.Tracker.EveryDayTracker.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Tracker.EveryDayTracker.Entity.Task;
import com.Tracker.EveryDayTracker.Service.TrackerService;

@Controller
@RequestMapping("/tracker")
public class TaskController {
	
	@Autowired
	private TrackerService service;
	
	@GetMapping
	public String showDashboard(Model model) {
	    
	    LocalDate today = LocalDate.now();
	    
	    // Attempt to fetch the record for today
	    Task todayRecord = service.getRecordByDate(today);
	    
	    // === FIX START ===
	    // If the service returns null (no record found for today):
	    if (todayRecord == null) {
	        // 1. Create a brand new Task instance
	        todayRecord = new Task(); 
	        // 2. Set the date on the new task (this is important)
	        todayRecord.setDate(today); 
	    }
	    // === FIX END ===
	    
	    // Note: If you want to use the existing logic for the first time, 
	    // you don't need the `if (todayRecord.getId() == null)` check 
	    // because you already handled the null case above, and a new Task() 
	    // will naturally have a null ID.
	    
	    model.addAttribute("record", todayRecord);
	    model.addAttribute("history", service.getAllRecords()); // Assuming this doesn't crash
	    model.addAttribute("today", today);
	    
	    return "dashboard"; // matches dashboard.html
	}
    	@PostMapping("/save")
	    public String saveProgress(@ModelAttribute("record") Task record) {
	        service.saveOrUpdateDailyRecord(record);
	        return "redirect:/tracker";
	    }
}
