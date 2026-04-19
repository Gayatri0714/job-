package com.example.jobportal.controller;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewJobs(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "location", required = false) String location,
            Model model) {
        
        List<Job> jobs;
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        boolean hasLocation = location != null && !location.trim().isEmpty();

        if (hasKeyword || hasLocation) {
            jobs = jobService.searchJobs(
                hasKeyword ? keyword.trim() : null, 
                hasLocation ? location.trim() : null
            );
        } else {
            jobs = jobService.findAll();
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("keyword", keyword);
        model.addAttribute("location", location);
        return "jobs";
    }

    @GetMapping("/post")
    public String postJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "employer/post-job";
    }

    @PostMapping("/post")
    public String saveJob(@Valid @ModelAttribute("job") Job job, BindingResult result, Authentication authentication, Model model) {
        if (result.hasErrors()) {
            return "employer/post-job";
        }

        String username = authentication.getName();
        User employer = userService.findByEmail(username).orElseThrow();
        job.setEmployer(employer);
        
        jobService.save(job);
        return "redirect:/dashboard?success=Job posted successfully";
    }
}
