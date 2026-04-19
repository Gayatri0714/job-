# Job Portal Management System 🚀

A full-stack Spring Boot web application connecting job seekers and employers, styled with an anti-gravity futuristic UI.

## Features
- **Student Matrix**: Browse jobs, update neural data (resume upload), transmit applications.
- **Corporate Core (Employer)**: Create active datastreams (post jobs), review and shortlist entities (applicants).
- **Master Control (Admin)**: System override terminal with full visibility.
- **Anti-gravity Interface**: Glassmorphism UI, adaptive neon glow mechanics.

## Tech Stack
- Spring Boot (Web, Spring Data JPA)
- Spring Security (BCrypt hashing, Role-based auth)
- MySQL / Hibernate
- Thymeleaf
- Vanilla Custom CSS (No frameworks)

## Installation & Setup

1. **Clone the repository** (or download files):
   ```bash
   git clone https://github.com/your-username/job-portal.git
   cd job-portal
   ```

2. **Configure Database**:
   - Ensure an instance of MySQL is running on port `3306`.
   - Open MySQL CLI or Workbench and run: `CREATE DATABASE job_portal;`.
   - Make sure your properties at `src/main/resources/application.properties` align:
     - `username=root`
     - `password=yourpassword`

3. **Run the application**:
   If Maven is installed locally:
   ```bash
   mvn spring-boot:run
   ```
   Or open the project inside an IDE like Eclipse, VS Code, or IntelliJ and click "Run".

4. **Initialize System Flow**:
   Go to `http://localhost:8080/` to test the application logic.

## File Structure Highlights
- `/uploads` - Generated dynamically for storing PDF/DOCX resumes.
- `/src/main/resources/static/css/index.css` - Where the anti-gravity visual engine lives.
