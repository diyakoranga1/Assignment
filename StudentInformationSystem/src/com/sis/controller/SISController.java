package com.sis.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.sis.dao.StudentServiceProvider;
import com.sis.entity.Course;
import com.sis.entity.Payment;
import com.sis.entity.Student;
import com.sis.entity.Teacher;

public class SISController {

    private StudentServiceProvider studentServiceProvider;

    public SISController(StudentServiceProvider studentServiceProvider) {
        this.studentServiceProvider = studentServiceProvider;
    }

    public Student getStudentInformation() {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirthString = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        return new Student(0, firstName, lastName, dateOfBirth, email, phoneNumber);
    }

    public void addStudentToDatabase(Student student) {
        try {
            studentServiceProvider.addStudentToDatabase(student);
            System.out.println("Student added successfully.");

        } catch (Exception e) {
            handleException(e);
        }
    }

    public List<Student> getAllStudentsFromDatabase() {
        try {
            return studentServiceProvider.getAllStudentsFromDatabase();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public void displayStudentInfo(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

 
    public void addCourseToDatabase(Course course) {
        try {
            studentServiceProvider.addCourseToDatabase(course);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void enrollInCourse(int studentId, int courseId) {
        try {
            Student student = studentServiceProvider.getStudentById(studentId);
            Course course = studentServiceProvider.getCourseById(courseId);

            if (student != null && course != null) {
                if (!studentServiceProvider.isStudentEnrolledInCourse(studentId, courseId)) {
                    studentServiceProvider.enrollStudentInCourse(student, course);

                    System.out.println("Student with ID " + studentId + " enrolled in course with ID " + courseId);
                } else {
                    System.out.println("Student is already enrolled in the course.");
                }
            } else {
                System.out.println("Student or course not found.");
            }
        } catch (Exception e) {
            handleException(e);
        }
    }


    public List<Course> getEnrolledCourses(int studentId) {
        try {
            Student student = studentServiceProvider.getStudentById(studentId);
            return studentServiceProvider.getEnrolledCourses(student);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public void makePayment(int studentId, BigDecimal amount, LocalDate paymentDate) {
        try {
            Student student = studentServiceProvider.getStudentById(studentId);
            studentServiceProvider.makePayment(student, amount, paymentDate);
            System.out.println("payment added successfully.");

        } catch (Exception e) {
            handleException(e);
        }
    }

    public List<Payment> getPayments(int studentId) {
        try {
            Student student = studentServiceProvider.getStudentById(studentId);
            return studentServiceProvider.getPayments(student);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public List<Student> getEnrolledStudentsForCourse(int courseId) {
        try {
            Course course = studentServiceProvider.getCourseById(courseId);
            return studentServiceProvider.getEnrolledStudentsForCourse(course);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public Student getStudentById(int studentId) {
        try {
            return studentServiceProvider.getStudentById(studentId);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public void displayEnrolledCoursesForStudent(List<Course> enrolledCourses) {
        for (Course course : enrolledCourses) {
            System.out.println(course.toString());
        }
    }

    public void displayPayments(List<Payment> payments) {
        for (Payment payment : payments) {
            System.out.println(payment.toString());
        }
    }

    public void generateEnrollmentReportForCourse(Course course) {
        try {
            List<Student> enrolledStudents = studentServiceProvider.getEnrolledStudentsForCourse(course);
            System.out.println("Enrolled Students for Course " + course.getCourseId() + ":");
            displayStudentInfo(enrolledStudents);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void assignTeacherToCourse(int teacherId, int courseId) {
        try {
            studentServiceProvider.assignTeacherToCourse(teacherId, courseId);
            System.out.println("Teacher assigned successfully.");
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void recordPayment(int studentId, BigDecimal amount, LocalDateTime paymentDate) {
        try {
            Student student = studentServiceProvider.getStudentById(studentId);
            studentServiceProvider.recordPayment(student, amount, paymentDate);
        } catch (Exception e) {
            handleException(e);
        }
    }


    public void updateStudentInformation(int studentId) {
        try {
            Student existingStudent = studentServiceProvider.getStudentById(studentId);

            if (existingStudent != null) {
                Student updatedStudent = getUpdatedStudentInformation(existingStudent);
                studentServiceProvider.updateStudentInDatabase(updatedStudent);
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void displayAllCourses() {
        try {
            List<Course> allCourses = studentServiceProvider.getAllCoursesFromDatabase();
            System.out.println("All Courses:");
            studentServiceProvider.displayCourseInfo(allCourses);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void displayAllTeachers() {
        try {
            List<Teacher> allTeachers = studentServiceProvider.getAllTeachersFromDatabase();
            System.out.println("All Teachers:");
            studentServiceProvider.displayTeacherInfo(allTeachers);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void displayPaymentsForStudent(Student studentId) {
        try {
            List<Payment> payments = studentServiceProvider.getPayments(studentId);
            displayPayments(payments);
        } catch (Exception e) {
            handleException(e);
        }
    }
    public Course getCourseById(int courseId) {
        try {
            return studentServiceProvider.getCourseById(courseId);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }
    public Teacher getTeacherInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        return new Teacher(0, firstName, lastName, email);
    }


    public void updateStudentInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirthString = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Student updatedStudent = new Student(studentId, firstName, lastName, dateOfBirth, email, phoneNumber);
        
        try {
            studentServiceProvider.updateStudentInDatabase(updatedStudent);
        } catch (Exception e) {
            handleException(e);
        }
    }



  
    private Student getUpdatedStudentInformation(Student existingStudent) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter updated First Name: ");
        String updatedFirstName = scanner.nextLine();
        
        System.out.print("Enter updated Last Name: ");
        String updatedLastName = scanner.nextLine();
        
        System.out.print("Enter updated Date of Birth (YYYY-MM-DD): ");
        String updatedDateOfBirthString = scanner.nextLine();
        LocalDate updatedDateOfBirth = LocalDate.parse(updatedDateOfBirthString);
        
        System.out.print("Enter updated Email: ");
        String updatedEmail = scanner.nextLine();
        
        System.out.print("Enter updated Phone Number: ");
        String updatedPhoneNumber = scanner.nextLine();

        return new Student(
                existingStudent.getStudentId(),
                updatedFirstName,
                updatedLastName,
                updatedDateOfBirth,
                updatedEmail,
                updatedPhoneNumber
        );
    }

    public void handleException(Exception e) {
    	System.out.println(e);
    }
}
