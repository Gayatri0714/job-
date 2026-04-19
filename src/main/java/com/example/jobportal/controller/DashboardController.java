package com.example.jobportal.controller;

import com.example.jobportal.entity.Application;
import com.example.jobportal.entity.ApplicationStatus;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.ApplicationService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    public String home() {
        return "index"; // landing page
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow();
        model.addAttribute("user", user);

        if (user.getRole() == Role.STUDENT) {
            model.addAttribute("applications", applicationService.getApplicationsByUser(user.getId()));
            return "student/dashboard";
        } else if (user.getRole() == Role.EMPLOYER) {
            model.addAttribute("jobs", user.getJobs());
            return "employer/dashboard";
        } else {
            model.addAttribute("users", userService.findAll());
            model.addAttribute("totalJobs", jobService.findAll().size());
            return "admin/dashboard";
        }
    }

    @PostMapping("/student/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId, Authentication authentication, RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow();
        Job job = jobService.findById(jobId).orElseThrow();

        try {
            applicationService.applyForJob(user, job);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully applied for " + job.getTitle());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/jobs";
    }

    @GetMapping("/employer/applications/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model, Authentication authentication) {
        Job job = jobService.findById(jobId).orElseThrow();
        // ensure owner
        String email = authentication.getName();
        if (!job.getEmployer().getEmail().equals(email)) {
            return "redirect:/dashboard";
        }
        model.addAttribute("job", job);
        model.addAttribute("applications", applicationService.getApplicationsByJob(jobId));
        return "employer/applicants";
    }

    @PostMapping("/employer/application/{appId}/status")
    public String updateAppStatus(@PathVariable Long appId, @RequestParam ApplicationStatus status, Authentication authentication) {
         applicationService.updateApplicationStatus(appId, status);
         // normally would check ownership here as well
         return "redirect:/dashboard";
    }

    @GetMapping("/profile")
    public String profilePage(Authentication authentication, Model model) {
        String email = authentication.getName();
        model.addAttribute("user", userService.findByEmail(email).orElseThrow());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User userDetails, 
                                @RequestParam("resume") MultipartFile resume, 
                                Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            userService.updateProfile(userDetails, resume);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error uploading resume");
        }
        return "redirect:/profile";
    }
}
