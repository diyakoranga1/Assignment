package com.sis.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.sis.entity.Course;
import com.sis.entity.Payment;
import com.sis.entity.Student;
import com.sis.entity.Teacher;
import com.sis.exception.*;

public interface StudentServiceProvider {
	
    void addStudentToDatabase(Student student);

    List<Student> getAllStudentsFromDatabase() throws SQLException;

    void enrollInCourse(Student student, Course course)
            throws DuplicateEnrollmentException, CourseNotFoundException, StudentNotFoundException, InsufficientFundsException;

    boolean isStudentEnrolledInCourse(int studentId, int courseId);

    List<Course> getEnrolledCourses(Student student) throws StudentNotFoundException;
   
    List<Student> getEnrolledStudentsForCourse(Course course) throws SQLException;
   
    void enrollStudentInCourse(Student student, Course course);
    
    void updateStudentInDatabase(Student student);
    
    Teacher getTeacherById(int teacherId) throws SQLException;

    Student getStudentById(int studentId) throws SQLException, StudentNotFoundException;

    Course getCourseById(int courseId) throws CourseNotFoundException;
    
    void addCourseToDatabase(Course course);

    void displayCourseInfo(List<Course> courses);

    List<Course> getAllCoursesFromDatabase() throws SQLException;

    List<Teacher> getAllTeachersFromDatabase() throws SQLException;

    void displayTeacherInfo(List<Teacher> teachers);
    
    void assignTeacherToCourse(int teacherId, int courseId) throws CourseNotFoundException, TeacherNotFoundException;

    void makePayment(Student student, BigDecimal amount, LocalDate paymentDate)
            throws StudentNotFoundException, PaymentValidationException;
    
    List<Payment> getPayments(Student student) throws SQLException;
    
    void recordPayment(Student student, BigDecimal amount, LocalDateTime paymentDate)
            throws StudentNotFoundException, PaymentValidationException;

}
