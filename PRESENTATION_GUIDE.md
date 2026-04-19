# 🚀 Nova Jobs: Job Portal Management System
### Presentation & Explanation Guide

Use this document as your "cheat sheet" when presenting the project. It breaks down the system into simple, easy-to-explain concepts.

---

## 📌 1. What is this project?
This is a **Full-Stack Job Portal Web Application**. It acts as a bridge connecting **Job Seekers** (Students) with **Recruiters** (Employers). You can think of it as a simplified, highly modern version of LinkedIn or Indeed. 

## 🏗️ 2. What Technologies Were Used? (The Tech Stack)
If someone asks "What did you use to build this?", here is your exact answer:

* **Spring Boot (Java)**: This is the brain or "Backend" of the application. It handles all the complex logic, security, and data transportation.
* **Spring Security**: We used this to lock down the application. It encrypts user passwords and ensures Students can't access Employer dashboards.
* **Spring Data JPA**: This is the translator between our Java code and the Database. It saves and retrieves records without us needing to write complex SQL code.
* **Database (H2 / MySQL)**: Where all the Users, Jobs, and Application records are stored safely.
* **Thymeleaf**: The "Frontend View Engine". It takes the raw data from the Java backend and effortlessly binds it to our HTML web pages.
* **Vanilla HTML & CSS**: Used to design the actual visual interface. We specifically manually crafted a modern **"Glassmorphism"** theme (glass-like transparent cards) with neon glows to give it an aesthetic, futuristic feel.

---

## 👥 3. How Does the System Work? (The 3 User Roles)
The application dynamically changes its layout depending on *who* logs in. 

1. **The Student (Job Seeker)**
   * **What they do:** They can register an account, fill out their profile (adding their skills and uploading a resume PDF), browse the available job market, search using keywords, and hit "Apply".
   * **Dashboard:** Shows a log of every job they applied to and whether they got rejected or shortlisted.

2. **The Employer (Recruiter)**
   * **What they do:** They can create an account, post new Jobs to the market (setting salary, location, skills), and check who applied to their jobs.
   * **Dashboard:** Shows all their active job postings, and allows them to review applicants and click "Shortlist" or "Reject".

3. **The Administrator**
   * **What they do:** They have "God Mode" view over the network. They can see total user counts, active jobs, and monitor the entire system matrix.

---

## ⚙️ 4. The Engineering Architecture (For Technical Questions)
If they ask about the architecture, tell them we used the **MVC (Model-View-Controller)** pattern.

* **The Models (Entities):** We have three core entities: `User`, `Job`, and `Application`. The relationships are highly strictly mapped: *One* Employer has *Many* Jobs. *One* Student has *Many* Applications.
* **The Controllers:** `AuthController`, `JobController`, and `DashboardController`. These act as traffic cops. When a user clicks a button on the website, it hits a Controller. The Controller asks the system for data, and then hands that data back to the webpage.
* **The Repositories:** These are the database workers. When a keyword is typed into the search bar, the `JobRepository` automatically executes a dynamic SQL query to filter the jobs instantly.
* **Security & Hashing:** We don't save passwords as plain text for security reasons. We use `BCryptPasswordEncoder` to scramble the passwords into unreadable hashes before putting them in the database!

---

## 💡 5. Best Things to Show Off (Live Demo Ideas)
When showing the app on your screen, click through this flow:
1. Show the neon, floating design of the **Homepage**.
2. Log in as `hr@cyberdyne.com` and post a new Job.
3. Log out. Log in as `jane@student.com` and **Search** for that exact job using the filter box.
4. Click Apply. 
5. Log back in as `hr@cyberdyne.com` to show how Jane's application instantly appeared on the Employer's dashboard for review!
