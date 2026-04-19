package com.example.jobportal.service;

import com.example.jobportal.entity.Job;
import com.example.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> searchJobs(String keyword, String location) {
        return jobRepository.searchJobs(keyword, location);
    }
}
