# shecancode-attendance-system
SheCanCODE Attendance System is a web-based application designed to manage and automate the process of attendance tracking and transport stipend calculations for SheCanCODE training cohorts. The system provides a secure and auditable way to register students, capture daily attendance, compute weekly transport allowances, and generate reports for administrative and donor review.

Key Objectives

Accurately track daily attendance

Ensure only eligible students receive transport stipends

Calculate stipend based on attendance rules

Provide transparent reporting for stakeholders

Maintain data integrity and auditability

Core Modules
Student Registration Module

Registers learners into the system

Assigns each learner to a cohort

Stores student details and status (Active/Inactive)

Provides student data to the attendance and stipend modules

 Attendance Module

Captures daily attendance for each cohort

Validates attendance records to prevent duplication

Supports attendance reporting by day or cohort

 Stipend Module

Calculates weekly transport allowance

Applies the rule:
Only students who attend all 5 days (Mon–Fri) are eligible

Uses a daily allowance rate of 2,500 RWF

Records stipend payments with status tracking (Pending/Approved/Paid)

 Reporting Module

Generates weekly and monthly reports

Provides export options for Excel/PDF

Supports donor and internal audits

 Security Module

Role-based access control:

Admin

Trainer

Student

Ensures only authorized users can perform actions

 Key Business Rules

Training runs Monday–Friday

Daily transport allowance is 2,500 RWF

Students must attend all 5 days to qualify

Attendance is recorded once per student per day

Stipend is calculated once per week per student

 Data Flow Overview

Admin registers cohorts and students

Trainers record daily attendance

The system calculates the stipend weekly

Admin generates reports

Payments are processed and status updated

Technology Stack

Backend: Java Spring Boot

Database: PostgreSQL

Frontend: React (optional for later)

Security: JWT / Role-based access

 Why This System is Needed

This system eliminates manual attendance spreadsheets, reduces human errors, and ensures
transparent and fair stipend distribution. It also provides an auditable record for donors, stakeholders, and program management.

If you want, I can also create:
✅ A one-page project proposal
✅ A technical design document
✅ A system architecture diagram
✅ An MVP roadmap
