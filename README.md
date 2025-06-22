# VetCare – Veterinary Clinic Management System

![App Logo](src/main/resources/static/assets/images/vetcare.png)


---

**VetCare** is a full-stack application developed using **Spring Boot** and **MySQL**, designed to assist veterinary clinics in managing appointments, pet medical records, and prescriptions. The system includes distinct user roles **pet owners** and **clinic admins** to support seamless and secure access to essential clinic services.
> Still working on improving the UI for the rest of the features...
> Initially developed as part of an academic assignment, I was part of backend team. The frontend was completely redesigned by me afterward for improved user experience and functionality.


## Features
### Pet Owner Portal
- **Book Appointments** with available vets.
- **Access Medical Records** including past diagnoses and treatments.
- **View Prescription History** with prescription documents.

### Admin Dashboard
- **Manage Appointments**: View, confirm, update, or cancel bookings.
- **Manage Pet & Owner Profiles**: Full CRUD operations for client records.
- **Medical Logs & Prescriptions**: Add and update health records per visit.
- **Dashboard Analytics**: Overview of bookings and prescriptions issued.
---



##  Tech Stack

| Layer       | Technology Used                                                   |
|-------------|-------------------------------------------------------------------|
| Backend     | Java 17, Spring Boot, Spring MVC                                  |
| Frontend    | (Custom) HTML/CSS/JS, Bootstrap *(updated after backend completion)* |
| Database    | MySQL                                                             |
| Build Tool  | Maven                                                             |
| API Style   | REST APIs                                                         |
| Dev Tools   | IntelliJ IDEA, MySQL Workbench, docker                            |

---

## Installation & Setup

### Prerequisites
- Java 17+
- MySQL Server
- Maven 3.8+

---

### Set Up the Database
* **Login to MySQL and run:**
  * CREATE DATABASE vetcare_db;
* **Update src/main/resources/application.properties with your DB credentials:**
mvn clean spring-boot:run
  * spring.datasource.url=jdbc:mysql://localhost:3306/vetcare_db
  * spring.datasource.username=your_mysql_username
  * spring.datasource.password=your_mysql_password
  * spring.jpa.hibernate.ddl-auto=update
  * spring.jpa.show-sql=true

### Run the Application
   * mvn clean spring-boot:run
   * http://localhost:8080/

### Testing
* Functional testing done via Postman collections
* Add JUnit & Mockito test coverage for controller & service layers

### Developer Notes
* Backend logic and core features were completed during assignment development.
* The frontend interface was completely redesigned post-submission for better UX, responsive design, and more modern aesthetics.