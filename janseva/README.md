# JanSeva – Government Grievance Redressal System

A Java + Spring Boot prototype for registering, tracking and managing civic complaints. It demonstrates a typical e-governance workflow with a citizen-facing portal, department desk, REST APIs and persistent relational storage.

## Features
- Register a grievance with citizen contact details, department, subject, description and location.
- Generate a unique tracking ID (`JS-XXXXXXXX`) for each complaint.
- Track a complaint using its tracking ID.
- Department desk to view recent complaints, assign an officer and update status.
- Dashboard counts for total, submitted, in-progress and resolved complaints.
- Bean Validation for incoming requests and a centralized validation-error response.
- H2 file-backed database by default; optional MySQL profile.

## Requirements
- Java 17+
- Maven 3.6+

## Run locally (default H2 database)
```bash
mvn spring-boot:run
```
Open **http://localhost:8080**. The H2 console is at **http://localhost:8080/h2-console** (JDBC URL: `jdbc:h2:file:./data/janseva`, user: `sa`, blank password).

## Use MySQL instead
1. Install MySQL and create a database (or allow the configured URL to create it).
2. Edit `src/main/resources/application-mysql.properties` and replace `CHANGE_ME` with your local password.
3. Run:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## REST API
| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/complaints` | Register a complaint |
| GET | `/api/complaints` | List complaints |
| GET | `/api/complaints/{id}` | Get complaint by database ID |
| GET | `/api/track/{trackingId}` | Track by public tracking ID |
| PATCH | `/api/complaints/{id}/status` | Update status / officer / resolution note |
| GET | `/api/dashboard` | Dashboard summary and five latest complaints |
| GET | `/api/departments` | List supported departments |

### Example: register a complaint
```json
{
  "citizenName": "Aarav Sharma",
  "email": "aarav@example.com",
  "phone": "9876543210",
  "department": "ROADS",
  "subject": "Pothole near community park",
  "description": "A large pothole has been present near the community park entrance for several days.",
  "location": "Sector 12, Community Park entrance"
}
```

### Example: update status
```json
{
  "status": "IN_PROGRESS",
  "assignedOfficer": "Demo Officer",
  "resolutionNote": "Inspection scheduled"
}
```

## Important scope and security note
This is an educational MVP, not a production government service. The department desk is a demonstration interface and the status-update API is not protected by authentication/authorization. Do not deploy with real citizen data. A production version should add verified identity, role-based access control, audit trails, rate limiting, secure deployment, privacy controls and formal departmental workflows.

## Suggested next improvements
- Spring Security with citizen/officer/admin roles and JWT or session authentication.
- Complaint audit history and notifications.
- Pagination, search and department-level authorization.
- GIS coordinates and PostGIS-backed complaint mapping.
- Automated tests for service and controller workflows.
