package com.Tracker.EveryDayTracker.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Tracker.EveryDayTracker.Entity.Task;
import com.Tracker.EveryDayTracker.Service.TrackerService;

@Controller
@RequestMapping("/tracker")
public class TaskController {

    @Autowired
    private TrackerService service;

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute("record", service.getTodayTask());
        model.addAttribute("history", service.getHistory());
        model.addAttribute("today", LocalDate.now());

        return "dashboard";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("record") Task task) {
        service.saveOrUpdate(task);
        return "redirect:/tracker";
    }
    
    @PostMapping("/delete")
    public String delete(@RequestParam("date") LocalDate date) {
        service.deleteByDate(date);
        return "redirect:/tracker";
    }
    
 // git test change


}
