package com.example.jobportal.service;

import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "uploads/";

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateProfile(User updatedUser, MultipartFile resumeFile) throws IOException {
        User existing = userRepository.findById(updatedUser.getId()).orElseThrow();
        existing.setName(updatedUser.getName());
        existing.setSkills(updatedUser.getSkills());
        existing.setExperience(updatedUser.getExperience());
        existing.setLocation(updatedUser.getLocation());

        if (resumeFile != null && !resumeFile.isEmpty()) {
            byte[] bytes = resumeFile.getBytes();
            Path path = Paths.get(UPLOAD_DIR + resumeFile.getOriginalFilename());
            Files.write(path, bytes);
            existing.setResumeFileName(resumeFile.getOriginalFilename());
        }

        userRepository.save(existing);
    }
}
