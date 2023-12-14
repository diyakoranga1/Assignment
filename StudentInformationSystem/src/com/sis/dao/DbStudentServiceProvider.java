package com.sis.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sis.entity.Course;
import com.sis.entity.Payment;
import com.sis.entity.Student;
import com.sis.exception.*;

public class DbStudentServiceProvider implements StudentServiceProvider {

    private Connection connection;

    public DbStudentServiceProvider(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void enrollInCourse(Student student, Course course)
            throws DuplicateEnrollmentException, CourseNotFoundException, StudentNotFoundException,
            InsufficientFundsException {
        try {
            String enrollQuery = "INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, NOW())";
            try (PreparedStatement enrollStatement = connection.prepareStatement(enrollQuery)) {
                enrollStatement.setInt(1, student.getStudentId());
                enrollStatement.setInt(2, course.getCourseId());
                enrollStatement.executeUpdate();
            }
        } catch (SQLException e) {
            handleSQLException(e, "enrollInCourse");
        }
    }

  
    @Override
    public boolean isStudentEnrolledInCourse(int studentId, int courseId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM enrollments WHERE student_id = ? AND course_id = ?")) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if there is a result, indicating enrollment
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking enrollment status", e);
        }
    }

    @Override
    public void enrollStudentInCourse(Student student, Course course) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)")) {
            statement.setInt(1, student.getStudentId());
            statement.setInt(2, course.getCourseId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error enrolling student in course", e);
        }
    }
    @Override
    public void makePayment(Student student, BigDecimal amount, LocalDate paymentDate)
            throws StudentNotFoundException, PaymentValidationException {
        try {
            String paymentQuery = "INSERT INTO Payments (student_id, amount, payment_date) VALUES (?, ?, ?)";
            try (PreparedStatement paymentStatement = connection.prepareStatement(paymentQuery)) {
                paymentStatement.setInt(1, student.getStudentId());
                paymentStatement.setBigDecimal(2, amount);
                paymentStatement.setDate(3, java.sql.Date.valueOf(paymentDate));
                paymentStatement.executeUpdate();
            }
        } catch (SQLException e) {
            handleSQLException(e, "makePayment");
        }
    }

    @Override
    public List<Course> getEnrolledCourses(Student student) throws StudentNotFoundException {
        try {
            String enrolledCoursesQuery = "SELECT c.* FROM Courses c JOIN Enrollments e ON c.course_id = e.course_id WHERE e.student_id = ?";
            List<Course> enrolledCourses = new ArrayList<>();
            try (PreparedStatement enrolledCoursesStatement = connection.prepareStatement(enrolledCoursesQuery)) {
                enrolledCoursesStatement.setInt(1, student.getStudentId());
                ResultSet resultSet = enrolledCoursesStatement.executeQuery();
                while (resultSet.next()) {
                    Course course = constructCourseFromResultSet(resultSet);
                    enrolledCourses.add(course);
                }
            }
            return enrolledCourses;
        } catch (SQLException e) {
            handleSQLException(e, "getEnrolledCourses");
            return new ArrayList<>();
        }
    }

    @Override
    public List<Payment> getPayments(Student student) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String getPaymentsQuery = "SELECT * FROM Payments WHERE student_id = ?";

        try (PreparedStatement getPaymentsStatement = connection.prepareStatement(getPaymentsQuery)) {
            getPaymentsStatement.setInt(1, student.getStudentId());

            try (ResultSet resultSet = getPaymentsStatement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment(
                            resultSet.getInt("payment_id"),
                            student,
                            resultSet.getBigDecimal("amount"),
                            resultSet.getDate("payment_date").toLocalDate()
                    );
                    payments.add(payment);
                }
            }
        }

        return payments;
    }

    @Override
    public List<Student> getEnrolledStudentsForCourse(Course course) throws SQLException {
        List<Student> enrolledStudents = new ArrayList<>();
        String getEnrolledStudentsQuery = "SELECT s.* FROM Students s " +
                "JOIN Enrollments e ON s.student_id = e.student_id " +
                "WHERE e.course_id = ?";

        try (PreparedStatement getEnrolledStudentsStatement = connection.prepareStatement(getEnrolledStudentsQuery)) {
            getEnrolledStudentsStatement.setInt(1, course.getCourseId());

            try (ResultSet resultSet = getEnrolledStudentsStatement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = constructStudentFromResultSet(resultSet);
                    enrolledStudents.add(student);
                }
            }
        }

        return enrolledStudents;
    }

    @Override
    public Student getStudentById(int studentId) throws SQLException, StudentNotFoundException {
        String getStudentQuery = "SELECT * FROM Students WHERE student_id = ?";
        try (PreparedStatement getStudentStatement = connection.prepareStatement(getStudentQuery)) {
            getStudentStatement.setInt(1, studentId);
            ResultSet resultSet = getStudentStatement.executeQuery();

            if (resultSet.next()) {
                return constructStudentFromResultSet(resultSet);
            } else {
                throw new StudentNotFoundException();
            }
        }
    }

    private void handleSQLException(SQLException e, String operation) {
        e.printStackTrace();
    }

    private Course constructCourseFromResultSet(ResultSet resultSet) throws SQLException {
        int courseId = resultSet.getInt("course_id");
        String courseName = resultSet.getString("course_name");
        int credits = resultSet.getInt("credits");
        int teacherId = resultSet.getInt("teacher_id");
        return new Course(courseId, courseName, credits, teacherId);
    }

    private Student constructStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("student_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getDate("date_of_birth").toLocalDate(),
                resultSet.getString("email"),
                resultSet.getString("phone_number")
        );
    }
    @Override
    public void recordPayment(Student student, BigDecimal amount, LocalDateTime paymentDateTime)
            throws StudentNotFoundException, PaymentValidationException {
        try {
            String paymentQuery = "INSERT INTO Payments (student_id, amount, payment_date) VALUES (?, ?, ?)";
            try (PreparedStatement paymentStatement = connection.prepareStatement(paymentQuery)) {
                paymentStatement.setInt(1, student.getStudentId());
                paymentStatement.setBigDecimal(2, amount);
                paymentStatement.setTimestamp(3, Timestamp.valueOf(paymentDateTime));
                paymentStatement.executeUpdate();
            }
        } catch (SQLException e) {
            handleSQLException(e, "recordPayment");
        }
    }
    @Override
    public void assignTeacherToCourse(int teacherId, int courseId) throws CourseNotFoundException, TeacherNotFoundException {
        try {
            String assignTeacherQuery = "UPDATE Courses SET teacher_id = ? WHERE course_id = ?";
            try (PreparedStatement assignTeacherStatement = connection.prepareStatement(assignTeacherQuery)) {
                assignTeacherStatement.setInt(1, teacherId);
                assignTeacherStatement.setInt(2, courseId);
                int rowsAffected = assignTeacherStatement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new CourseNotFoundException();
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "assignTeacherToCourse");
        }
    }
    public Course getCourseById(int courseId) throws CourseNotFoundException {
        try {
            String getCourseQuery = "SELECT * FROM Courses WHERE course_id = ?";
            try (PreparedStatement getCourseStatement = connection.prepareStatement(getCourseQuery)) {
                getCourseStatement.setInt(1, courseId);
                ResultSet resultSet = getCourseStatement.executeQuery();

                if (resultSet.next()) {
                    return constructCourseFromResultSet(resultSet);
                } else {
                    throw new CourseNotFoundException();
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "getCourseById");
            return null; // Handle the exception according to your application's requirements
        }
    }
    public void addStudentToDatabase(Student student)  {
        try {
            String addStudentQuery = "INSERT INTO Students (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement addStudentStatement = connection.prepareStatement(addStudentQuery, Statement.RETURN_GENERATED_KEYS)) {
                addStudentStatement.setString(1, student.getFirstName());
                addStudentStatement.setString(2, student.getLastName());
                addStudentStatement.setDate(3, java.sql.Date.valueOf(student.getDateOfBirth()));
                addStudentStatement.setString(4, student.getEmail());
                addStudentStatement.setString(5, student.getPhoneNumber());

                int affectedRows = addStudentStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Adding student failed, no rows affected.");
                }

                try (ResultSet generatedKeys = addStudentStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int studentId = generatedKeys.getInt(1);
                        student.setStudentId(studentId);
                    } else {
                        throw new SQLException("Adding student failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "addStudentToDatabase");
        }
    }
    public List<Student> getAllStudentsFromDatabase() throws SQLException {
        List<Student> students = new ArrayList<>();
        String getAllStudentsQuery = "SELECT * FROM Students";

        try (PreparedStatement getAllStudentsStatement = connection.prepareStatement(getAllStudentsQuery)) {
            try (ResultSet resultSet = getAllStudentsStatement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = constructStudentFromResultSet(resultSet);
                    students.add(student);
                }
            }
        }

        return students;
    }

}


