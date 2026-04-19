package com.example.jobportal.config;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Seed only if no users exist
        if (userRepository.count() == 0) {
            
            // 1. Create a Fake Employer
            User employer = new User();
            employer.setName("CyberDyne Systems");
            employer.setEmail("hr@cyberdyne.com");
            employer.setPassword(passwordEncoder.encode("password123"));
            employer.setRole(Role.EMPLOYER);
            employer.setLocation("Silicon Valley, CA");
            userRepository.save(employer);

            // 2. Create another Fake Employer
            User employer2 = new User();
            employer2.setName("Stark Industries");
            employer2.setEmail("careers@stark.com");
            employer2.setPassword(passwordEncoder.encode("password123"));
            employer2.setRole(Role.EMPLOYER);
            employer2.setLocation("New York, NY");
            userRepository.save(employer2);

            // 3. Create a Fake Student for testing
            User student = new User();
            student.setName("Jane Doe");
            student.setEmail("jane@student.com");
            student.setPassword(passwordEncoder.encode("password123"));
            student.setRole(Role.STUDENT);
            student.setSkills("Java, Spring Boot, React");
            userRepository.save(student);

            // 4. Create an Admin User
            User admin = new User();
            admin.setName("Super Admin");
            admin.setEmail("admin@jobportal.com");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

            // 5. Create Fake Jobs
            Job job1 = new Job();
            job1.setTitle("Senior Quantum Engineer");
            job1.setDescription("Looking for a highly skilled engineer to maintain quantum core systems and neural networks. Must have experience with multi-dimensional routing.");
            job1.setSalary("$150,000 - $180,000");
            job1.setLocation("Remote (Earth Sector)");
            job1.setRequiredSkills("Quantum Computing, Java, AI");
            job1.setEmployer(employer);
            jobRepository.save(job1);

            Job job2 = new Job();
            job2.setTitle("Frontend Neon UI Developer");
            job2.setDescription("Design and implement anti-gravity, glassmorphism interfaces for our core command terminals. High proficiency in vanilla CSS required.");
            job2.setSalary("$110,000 - $130,000");
            job2.setLocation("Silicon Valley, CA");
            job2.setRequiredSkills("CSS, HTML, JavaScript");
            job2.setEmployer(employer);
            jobRepository.save(job2);

            Job job3 = new Job();
            job3.setTitle("Backend Sentinel (Security)");
            job3.setDescription("Maintain firewall perimeters around the Arc Reactor network. You will be responsible for intrusion detection and system hardening.");
            job3.setSalary("$140,000 - $200,000");
            job3.setLocation("New York, NY");
            job3.setRequiredSkills("Spring Security, Networking, Java");
            job3.setEmployer(employer2);
            jobRepository.save(job3);
            
            System.out.println("✅ Fake Employers, Jobs, and Job Seekers have been heavily injected into the Matrix!");
        }
    }
}
