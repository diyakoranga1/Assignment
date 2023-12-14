package com.sis.entity;

import java.util.Date;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private Date enrollmentDate;

    // Constructor
    public Enrollment(int enrollmentId, int studentId, int courseId, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getter methods
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    // Setter methods
    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
