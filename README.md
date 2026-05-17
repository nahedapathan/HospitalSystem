# HospitalSystem
# 🏥 MediCare HMS — Hospital Management System


> A full-stack Hospital Management System built with Spring Boot and Angular — enabling seamless management of patients, doctors, and appointments with role-based access control.



## 🌟 Overview

**MediCare HMS** is a full-stack web application that digitizes hospital operations. It provides a secure, role-based platform for three types of users — **Admins**, **Doctors**, and **Patients** — each with a dedicated dashboard and access to relevant features.

The system handles user authentication via **JWT tokens**, manages patient records and insurance data, allows patients to **book appointments** with doctors, and gives doctors a real-time view of their consultation schedule.

---

## ✨ Features

### 🔐 Authentication & Authorization
- JWT-based secure login and signup
- Role-based access control (Admin / Doctor / Patient)
- Protected routes on both frontend and backend

### 👨‍💼 Admin
- View and manage all registered patients
- Paginated patient list with search functionality
- Blood group statistics dashboard

### 👨‍⚕️ Doctor
- View all assigned appointments
- Filter by upcoming / completed status
- Real-time appointment stats (Today, Upcoming, Completed)

### 🧑‍🤝‍🧑 Patient
- View personal health profile
- Book appointments with available doctors
- View appointment history with doctor details

### 🌐 Public
- Browse all doctors without logging in
- Filter doctors by name and specialization
- One-click redirect to book appointment

---

## 🛠️ Tech Stack

### Backend
| Technology | Purpose |
|---|---|
| Java 17 | Core language |
| Spring Boot 3.x | Application framework |
| Spring Security | Authentication & authorization |
| JWT (JJWT) | Token-based auth |
| Spring Data JPA | Database ORM |
| Hibernate | Entity management |
| MySQL | Relational database |
| ModelMapper | DTO mapping |
| Lombok | Boilerplate reduction |
| Maven | Dependency management |

### Frontend
| Technology | Purpose |
|---|---|
| Angular 17 | Frontend framework |
| Angular Material | UI component library |
| TypeScript | Type-safe scripting |
| RxJS | Reactive programming |
| HTTP Interceptors | JWT injection |
| Route Guards | Frontend access control |
| Lazy Loading | Module-level code splitting |

---

## 📁 Folder Structure

```
HospitalManagementSystem/
│
├── HospitalManamentSystem/               # Spring Boot Backend
│   └── src/main/java/com/hms/
│       ├── controller/                   # REST Controllers
│       │   ├── AdminController.java
│       │   ├── AuthController.java
│       │   ├── DoctorController.java
│       │   └── PatientController.java
│       ├── service/                      # Business Logic
│       │   ├── AppointmentService.java
│       │   ├── AuthService.java
│       │   ├── DoctorService.java
│       │   ├── InsuranceService.java
│       │   └── PatientService.java
│       ├── entity/                       # JPA Entities
│       │   ├── Appointment.java
│       │   ├── Department.java
│       │   ├── Doctor.java
│       │   ├── Insurance.java
│       │   ├── Patient.java
│       │   └── User.java
│       ├── dto/                          # Data Transfer Objects
│       ├── repository/                   # Spring Data Repositories
│       ├── security/                     # JWT + Spring Security
│       │   ├── AuthUtil.java
│       │   ├── CustomUserDetailsService.java
│       │   ├── JwtAuthFilter.java
│       │   └── WebSecurityConfig.java
│       └── config/                       # App Configuration
│           └── SecurityBeansConfig.java
│
└── HospitalFrontend/                     # Angular Frontend
    └── src/app/
        ├── core/
        │   ├── guards/                   # Auth Guard
        │   ├── interceptors/             # JWT Interceptor
        │   └── services/                 # Auth, Patient, Doctor Services
        ├── features/
        │   ├── admin/                    # Admin Dashboard
        │   ├── auth/                     # Login & Signup
        │   ├── dashboard/                # Shared Shell Layout
        │   ├── doctor/                   # Doctor Dashboard
        │   ├── patient/                  # Patient Dashboard
        │   └── public/                   # Public Doctor Listing
        └── shared/
            ├── material/                 # Angular Material Module
            └── pipes/                    # Custom Pipes (BloodGroup)
```

---

## 📡 API Overview

### Auth Endpoints — `/auth/**` (Public)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/login` | Login and receive JWT token |
| POST | `/auth/signup` | Register a new patient account |

### Admin Endpoints — `/admin/**` (ADMIN only)
| Method | Endpoint | Description |
|---|---|---|
| GET | `/admin/patients` | Get paginated list of all patients |

### Doctor Endpoints — `/doctors/**` (DOCTOR only)
| Method | Endpoint | Description |
|---|---|---|
| GET | `/doctors/appointments` | Get all appointments for logged-in doctor |

### Patient Endpoints — `/patients/**` (PATIENT only)
| Method | Endpoint | Description |
|---|---|---|
| GET | `/patients/profile` | Get logged-in patient's profile |
| POST | `/patients/appointments` | Book a new appointment |

### Public Endpoints — `/public/**` (No auth required)
| Method | Endpoint | Description |
|---|---|---|
| GET | `/public/doctors` | Get all available doctors |

---

## 🚀 Installation & Setup

### Prerequisites
- Java 17+
- Node.js 18+ and npm
- MySQL 8.0+
- Maven 3.8+

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/medicare-hms.git
cd medicare-hms
```

### 2. Backend Setup
```bash
cd HospitalManamentSystem
```

Create the MySQL database:
```sql
CREATE DATABASE hospital_db;
```

Configure `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
jwt.secretKey=your_secret_key_minimum_32_characters_long
```

Run the backend:
```bash
mvn spring-boot:run
```
Backend runs on **http://localhost:8080**

### 3. Frontend Setup
```bash
cd HospitalFrontend
npm install
ng serve
```
Frontend runs on **http://localhost:4200**

### 4. Default Access
| Role | How to get it |
|---|---|
| PATIENT | Sign up via `/auth/signup` (default role) |
| DOCTOR | Manually set `role = DOCTOR` in DB |
| ADMIN | Manually set `role = ADMIN` in DB |

---
