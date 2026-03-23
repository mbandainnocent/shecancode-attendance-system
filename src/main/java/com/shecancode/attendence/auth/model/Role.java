package com.shecancode.attendence.auth.model;

/**
 * RBAC roles:
 *  - ADMIN    : full access (manage users, cohorts, programs, students, attendance)
 *  - TRAINER  : can record / update attendance; read-only on students
 *  - STUDENT  : can view their own attendance records only
 */
public enum Role {
    ADMIN,
    TRAINER,
    STUDENT
}
