# JanSeva – Government Grievance Redressal System 🇮🇳

**A Java Spring Boot-based e-Governance application for citizen grievance registration, complaint tracking, and departmental management.**

JanSeva is a web-based prototype designed to simplify the process of reporting and monitoring civic issues. It provides citizens with a platform to submit complaints and track their resolution using a unique tracking ID, while providing a departmental interface to manage complaints and update their status.

---

## 📌 Project Overview

Public grievance management often involves multiple departments, manual communication, and limited complaint visibility.

JanSeva aims to demonstrate a centralized digital workflow through which citizens can register civic complaints, receive unique tracking IDs, and monitor progress.

The application follows a layered backend architecture using Java, Spring Boot, Spring Data JPA, and a relational database.

## ✨ Key Features

### 1. Citizen Grievance Registration
- Register complaints with citizen name and contact details.
- Select the relevant government department.
- Provide complaint subject, description, and location.
- Automatically generate a unique complaint tracking ID.

### 2. Complaint Tracking
- Track submitted complaints using a unique tracking ID.
- View complaint information and current resolution status.
- Monitor the progress of registered grievances.

### 3. Department Management
- View registered complaints through the department interface.
- Assign complaints to officers.
- Update complaint statuses.
- Add resolution notes.

### 4. Administrative Dashboard
- View total registered complaints.
- Monitor submitted, in-progress, and resolved complaints.
- View the latest complaint records.

### 5. Backend and Data Management
- RESTful API architecture.
- Persistent H2 database storage.
- Spring Data JPA and Hibernate for database operations.
- Request validation and centralized exception handling.

---

## 🛠️ Technology Stack

| Component | Technology |
|---|---|
| Programming Language | Java 17+ |
| Backend Framework | Spring Boot 3.3.5 |
| Database | H2 Database |
| ORM | Hibernate / Spring Data JPA |
| API Architecture | REST APIs |
| Frontend | HTML, CSS, JavaScript |
| Build Tool | Apache Maven |
| Optional Database | MySQL |
| Version Control | Git & GitHub |

---

## 🏗️ Project Architecture

The application follows a layered architecture to separate responsibilities.

```text
JanSeva/
│
├── src/
│   └── main/
│       ├── java/in/janseva/
│       │   ├── controller/
│       │   │   ├── ComplaintController.java
│       │   │   └── ApiExceptionHandler.java
│       │   │
│       │   ├── service/
│       │   │   └── ComplaintService.java
│       │   │
│       │   ├── repository/
│       │   │   └── ComplaintRepository.java
│       │   │
│       │   ├── model/
│       │   │   ├── Complaint.java
│       │   │   ├── Department.java
│       │   │   └── ComplaintStatus.java
│       │   │
│       │   ├── dto/
│       │   │   ├── ComplaintRequest.java
│       │   │   └── StatusUpdateRequest.java
│       │   │
│       │   └── JanSevaApplication.java
│       │
│       └── resources/
│           ├── static/
│           │   ├── index.html
│           │   ├── style.css
│           │   └── app.js
│           │
│           ├── application.properties
│           └── application-mysql.properties
│
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

Follow these instructions to run JanSeva locally.

### Prerequisites

Install the following:

1. **Java JDK 17 or later**
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **Apache Maven 3.6 or later**
   - Download: https://maven.apache.org/download.cgi

3. **Git**
   - Download: https://git-scm.com/downloads

Verify your installations:

```bash
java -version
mvn -version
git --version
```

### Step 1: Clone the Repository

```bash
git clone https://github.com/jiya-wadhwa/JanSeva-Government-Grievance-System.git
```

### Step 2: Navigate to the Project Directory

```bash
cd JanSeva-Government-Grievance-System
```

If the repository contains a nested `janseva` directory, enter it:

```bash
cd janseva
```

Run Maven commands from the directory containing `pom.xml`.

### Step 3: Run the Application

```bash
mvn spring-boot:run
```

Maven will download the required dependencies and start the Spring Boot application.

### Step 4: Open the Application

Once the application starts successfully, open:

**http://localhost:8080**

The application will use the default H2 file-backed database. No separate database installation is required.

---

## 🗄️ Database Configuration

### Default: H2 Database

JanSeva uses an H2 file-backed database by default.

Configuration:

```properties
spring.datasource.url=jdbc:h2:file:./data/janseva;AUTO_SERVER=TRUE
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

H2 Database Console:

http://localhost:8080/h2-console

JDBC URL:

```text
jdbc:h2:file:./data/janseva
```

Username: `sa`

Password: Leave blank.

### Optional: MySQL Configuration

The project also includes an optional MySQL configuration.

1. Install MySQL.
2. Create a database.
3. Update `src/main/resources/application-mysql.properties`.
4. Replace the database credentials with your local configuration.

Activate the MySQL profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

---

## 🔌 REST API Documentation

The backend exposes REST endpoints for complaint registration, retrieval, tracking, and status management.

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/complaints` | Register a new complaint |
| GET | `/api/complaints` | Retrieve all complaints |
| GET | `/api/complaints/{id}` | Retrieve complaint by ID |
| GET | `/api/track/{trackingId}` | Track complaint |
| PATCH | `/api/complaints/{id}/status` | Update complaint status |
| GET | `/api/dashboard` | Retrieve dashboard statistics |
| GET | `/api/departments` | Retrieve available departments |

### Example: Register a Complaint

**POST** `/api/complaints`

Request body:

```json
{
  "citizenName": "Aarav Sharma",
  "email": "aarav@example.com",
  "phone": "9876543210",
  "department": "ROADS",
  "subject": "Pothole near community park",
  "description": "A large pothole has been present near the community park entrance.",
  "location": "Sector 12, Community Park"
}
```

A successful registration generates a unique tracking ID.

### Example: Update Complaint Status

**PATCH** `/api/complaints/1/status`

Request body:

```json
{
  "status": "IN_PROGRESS",
  "assignedOfficer": "Demo Officer",
  "resolutionNote": "Inspection scheduled"
}
```

---

## 🔄 Application Workflow

```text
Citizen
   |
   v
Register Complaint
   |
   v
Generate Tracking ID
   |
   v
Store Complaint in Database
   |
   v
Department Dashboard
   |
   v
Assign Officer
   |
   v
Update Complaint Status
   |
   v
Citizen Tracks Progress
```

---

## 🔮 Future Enhancements

Potential improvements include:

- Spring Security-based authentication and authorization.
- Separate citizen, officer, and administrator roles.
- Email/SMS notifications for complaint updates.
- Complaint history and audit logging.
- GIS-based complaint mapping and location visualization.
- PostGIS integration for spatial complaint analysis.
- Advanced filtering, pagination, and reporting.

---

## 🔐 Security and Project Scope

JanSeva is an educational e-Governance prototype and is not an official government service.

The current demonstration interface and status-update APIs do not implement authentication or authorization. It should not be deployed with real citizen information.

Production deployment would require secure authentication, role-based authorization, privacy controls, audit logging, and appropriate infrastructure.

---

## 👩‍💻 Author

**Jiya Wadhwa**

B.Tech – Artificial Intelligence & Data Science

Guru Gobind Singh Indraprastha University

- GitHub: https://github.com/jiya-wadhwa
- LinkedIn: www.linkedin.com/in/jiya-wadhwa-7836ba328

---

## ⭐ Acknowledgements

Built as an independent learning project to explore Java backend development, Spring Boot, RESTful services, relational database management, and e-Governance application workflows.

If you find this project useful, consider giving the repository a ⭐ on GitHub!
