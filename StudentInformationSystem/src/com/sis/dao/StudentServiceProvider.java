package com.sis.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.sis.entity.Course;
import com.sis.entity.Payment;
import com.sis.entity.Student;
import com.sis.exception.*;

public interface StudentServiceProvider {
    void enrollInCourse(Student student, Course course)
            throws DuplicateEnrollmentException, CourseNotFoundException, StudentNotFoundException, InsufficientFundsException;

 
    void makePayment(Student student, BigDecimal amount, LocalDate paymentDate)
            throws StudentNotFoundException, PaymentValidationException;

    List<Course> getEnrolledCourses(Student student) throws StudentNotFoundException;

    void recordPayment(Student student, BigDecimal amount, LocalDateTime paymentDate)
            throws StudentNotFoundException, PaymentValidationException;

    List<Student> getEnrolledStudentsForCourse(Course course) throws SQLException;
    void assignTeacherToCourse(int teacherId, int courseId) throws CourseNotFoundException, TeacherNotFoundException;
    List<Payment> getPayments(Student student) throws SQLException;

    boolean isStudentEnrolledInCourse(int studentId, int courseId);

    void enrollStudentInCourse(Student student, Course course);
    
    Course getCourseById(int courseId) throws CourseNotFoundException;
    void addStudentToDatabase(Student student);
    
    List<Student> getAllStudentsFromDatabase() throws SQLException;
    
    Student getStudentById(int studentId) throws SQLException, StudentNotFoundException;
    
}
