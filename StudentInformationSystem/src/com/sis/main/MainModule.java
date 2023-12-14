package com.sis.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.sis.controller.SISController;
import com.sis.dao.DbStudentServiceProvider;
import com.sis.entity.*;

import com.sis.util.DBConnUtil;
public class MainModule {

    public static void main(String[] args) {
        try (Connection connection = DBConnUtil.getConnection()) {
            DbStudentServiceProvider dbStudentServiceProvider = new DbStudentServiceProvider(connection);
            SISController sisController = new SISController(dbStudentServiceProvider);

            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);

            int choice = 0;
            do {
                try {
                    System.out.println("Student Information System :");
                    System.out.println("1. Add Student");
                    System.out.println("2. Display All Students");
                    System.out.println("3. Add Course and Enroll Student");
                    System.out.println("4. Display Enrolled Courses for a Student");
                    System.out.println("5. Display Payments for a Student");
                    System.out.println("6. Display Course Statistics");
                    System.out.println("7. Assign Teacher to Course");
                    System.out.println("8. Record Payment");
                    System.out.println("9. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            Student newStudent = sisController.getStudentInformation();
                            sisController.addStudentToDatabase(newStudent);
                            break;
                        case 2:
                            List<Student> allStudents = sisController.getAllStudentsFromDatabase();
                            sisController.displayStudentInfo(allStudents);
                            break;
                        case 3:
                        	  System.out.print("Enter Course ID: ");
                        	    int courseIdForEnrollment = scanner.nextInt();

                        	    // Ask for Student ID
                        	    System.out.print("Enter Student ID: ");
                        	    int studentIdForEnrollment = scanner.nextInt();

                        	    sisController.enrollInCourse(studentIdForEnrollment, courseIdForEnrollment);
                        	    break;
                        case 4:
                            System.out.print("Enter Student ID: ");
                            int studentId = scanner.nextInt();
                            List<Course> enrolledCourses = sisController.getEnrolledCourses(studentId);
                            sisController.displayEnrolledCoursesForStudent(enrolledCourses);
                            break;
                        case 5:
                            System.out.print("Enter Student ID: ");
                            int studentIdForPayments = scanner.nextInt();
                            List<Payment> payments = sisController.getPayments(studentIdForPayments);
                            sisController.displayPayments(payments);
                            break;
                        case 6:
                            System.out.print("Enter Course ID: ");
                            int courseIdForReport = scanner.nextInt();
                            Course courseForReport = new Course(courseIdForReport, "", 0, 0);
                            sisController.generateEnrollmentReportForCourse(courseForReport);
                            break;
                        case 7:
                            System.out.print("Enter Teacher ID: ");
                            int teacherIdForAssignment = scanner.nextInt();
                            System.out.print("Enter Course ID: ");
                            int courseIdForAssignment = scanner.nextInt();
                            sisController.assignTeacherToCourse(teacherIdForAssignment, courseIdForAssignment);
                            break;
                        case 8:
                        	System.out.print("Enter Student ID: ");
                        	int studentIdForPayment = scanner.nextInt();
                        	System.out.print("Enter payment amount: ");
                        	BigDecimal paymentAmount = new BigDecimal(scanner.nextDouble()).setScale(2, RoundingMode.HALF_UP);

                        	scanner.nextLine();
                        	System.out.print("Enter payment date and time (YYYY-MM-DD HH:mm): ");
                        	String paymentDateTimeString = scanner.nextLine();
                        	DateTimeFormatter paymentDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        	LocalDateTime paymentDateTime = LocalDateTime.parse(paymentDateTimeString, paymentDateTimeFormatter);

                        	sisController.recordPayment(studentIdForPayment, paymentAmount, paymentDateTime);
                            break;
                        case 9:
                            System.out.println("Bye");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                    }
                } catch (Exception e) {
                    sisController.handleException(e);
                }
            } while (choice != 9);

        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }
}
