-- TASK1
CREATE DATABASE SISDB;
USE SISDB;

-- table creation

CREATE TABLE Teacher (
    teacher_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(20)
);

CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);

CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY ,
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Payments (
    payment_id INT PRIMARY KEY,
    student_id INT,
    amount DECIMAL(10, 2),
    payment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

-- TASK2)

-- values insertion

INSERT INTO Students (student_id, first_name, last_name, date_of_birth, email, phone_number)
VALUES
   (301, 'Aarav', 'Kumar', '1995-03-20', 'aarav.kumar@example.com', '9876543210'),
  (302, 'Isha', 'Singh', '1998-07-12', 'isha.singh@example.com', '8765432109'),
  (303, 'Arjun', 'Sharma', '1997-05-15', 'arjun.sharma@example.com', '7654321098'),
  (304, 'Anaya', 'Patel', '1996-12-02', 'anaya.patel@example.com', '6543210987'),
  (305, 'Rohan', 'Verma', '1999-08-25', 'rohan.verma@example.com', '5432109876'),
  (306, 'Neha', 'Gupta', '1994-10-08', 'neha.gupta@example.com', '4321098765'),
  (307, 'Vivan', 'Rajput', '1993-06-30', 'vivan.rajput@example.com', '3210987654'),
  (308, 'Avani', 'Reddy', '1992-04-12', 'avani.reddy@example.com', '2109876543'),
  (309, 'Aryan', 'Malhotra', '1991-02-18', 'aryan.malhotra@example.com', '1098765432'),
  (310, 'Sanya', 'Thakur', '1990-11-05', 'sanya.thakur@example.com', '9876543210'),
  (311, 'Aisha', 'Malik', '1994-09-18', 'aisha.malik@example.com', '9876543210'),
  (312, 'Rahul', 'Sharma', '1996-05-12', 'rahul.sharma@example.com', '8765432109'),
  (313, 'Sara', 'Khan', '1998-03-25', 'sara.khan@example.com', '7654321098'),
  (314, 'Aditya', 'Verma', '1997-11-02', 'aditya.verma@example.com', '6543210987'),
  (315, 'Neha', 'Singh', '1995-08-25', 'neha.singh@example.com', '5432109876');

INSERT INTO Teacher (teacher_id, first_name, last_name, email)
VALUES
  (101, 'Professor', 'Brown', 'prof.brown@example.com'),
  (102, 'Dr.', 'Taylor', 'dr.taylor@example.com'),
  (103, 'Ms.', 'Clark', 'ms.clark@example.com'),
  (104, 'Mr.', 'Lee', 'mr.lee@example.com'),
  (105, 'Mrs.', 'Adams', 'mrs.adams@example.com'),
  (106, 'Dr.', 'Garcia', 'dr.garcia@example.com'),
  (107, 'Mrs.', 'Martin', 'mrs.martin@example.com'),
  (108, 'Mr.', 'Roberts', 'mr.roberts@example.com'),
  (109, 'Professor', 'Wang', 'prof.wang@example.com'),
  (110, 'Dr.', 'Lopez', 'dr.lopez@example.com'),
  (111, 'Prof.', 'Smith', 'prof.smith@example.com'),
  (312, 'Dr.', 'Anderson', 'dr.anderson@example.com'),
  (313, 'Ms.', 'Johnson', 'ms.johnson@example.com');


INSERT INTO Courses (course_id, course_name, credits, teacher_id)
VALUES
  (401, 'Physics', 4, 101),
  (402, 'Art', 3, 102),
  (403, 'Mathematics', 3, 103),
  (404, 'Literature', 4, 104),
  (405, 'History', 3, 105),
  (406, 'Computer Science', 4, 106),
  (407, 'Biology', 4, 107),
  (408, 'Chemistry', 3, 108),
  (409, 'Geography', 3, 109),
  (410, 'Economics', 4, 110),
  (411, 'Psychology', 3, 101),
  (412, 'Sociology', 3, 102),
  (413, 'Philosophy', 4, 103),
  (414, 'Statistics', 3, 104),
  (415, 'Political Science', 4, 105),
  (421, 'Psychology', 3, 101),
  (422, 'Sociology', 3, 102),
  (423, 'Philosophy', 4, 103),
  (424, 'Statistics', 3, 104),
  (425, 'Political Science', 4, 105);

INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date)
VALUES
  (501, 301, 401, '2023-01-05'),
  (502, 301, 402, '2023-02-10'),
  (503, 302, 410, '2023-01-15'),
  (504, 303, 403, '2023-03-01'),
  (505, 304, 404, '2023-02-20'),
  (506, 305, 405, '2023-01-10'),
  (507, 306, 406, '2023-01-05'),
  (508, 307, 407, '2023-02-15'),
  (509, 308, 408, '2023-02-25'),
  (510, 309, 409, '2023-03-05'),
  (521, 311, 411, '2023-03-15'),
  (522, 311, 412, '2023-03-20'),
  (523, 312, 413, '2023-04-01'),
  (524, 312, 414, '2023-04-10'),
  (525, 313, 415, '2023-04-15'),
  (526, 313, 401, '2023-05-01'),
  (527, 314, 402, '2023-05-10'),
  (528, 314, 403, '2023-05-20'),
  (529, 315, 404, '2023-06-01'),
  (530, 315, 405, '2023-06-10');


INSERT INTO Payments (payment_id, student_id, amount, payment_date)
VALUES
  (601, 301, 80.00, '2023-01-10'),
  (602, 302, 60.50, '2023-02-15'),
  (603, 303, 90.00, '2023-03-01'),
  (604, 304, 75.00, '2023-02-20'),
  (605, 305, 100.00, '2023-01-15'),
  (606, 306, 120.00, '2023-03-05'),
  (607, 307, 95.50, '2023-02-10'),
  (608, 308, 85.00, '2023-01-20'),
  (609, 309, 110.00, '2023-02-25'),
  (610, 310, 70.00, '2023-01-05');


select * from courses;
select * from enrollments;
select * from payments;
select * from students;
select * from teacher;

-- TASK3-

/*query to enroll a student in a course.
Choose an existing student and course and insert a record into the "Enrollments" table with the enrollment date*/

select * from enrollments;
INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date)
VALUES (12, 301, 401, '2023-12-01');

-- 3.	Update the email address of a specific teacher in the "Teacher" table. 
UPDATE Teacher SET email = 'mew@gmailcom' WHERE teacher_id = 107;
select * from teacher;

select * from enrollments;

-- query to delete a specific enrollment record from the "Enrollments" table

DELETE FROM Enrollments
WHERE student_id = 301 AND course_id = 401;

-
-- Delete a specific student from the "Students" table and remove all their enrollment records from the "Enrollments" table

DELETE FROM Payments WHERE student_id = 301;
DELETE FROM Enrollments WHERE student_id = 301;
DELETE FROM Students WHERE student_id = 301;

-- update the payment amount for a specific payment record in the "Payments" table
update  Payments SET amount = 8000 WHERE payment_id = 607;
SELECT * FROM PAYMENTS;
-- TASK3-
-- joins queries
-- 1. Write an SQL query to insert a new student into the "Students" table with the following details:
--
INSERT INTO Students (student_id,first_name, last_name, date_of_birth, email, phone_number)
VALUES (20,'HAPPY', 'CAT', '2000-08-15', 'us@gmail.com', '1234567890');

-- 2. Write an SQL query to enroll a student in a course. Choose an existing student and course and
-- insert a record into the "Enrollments" table with the enrollment date.
INSERT INTO Enrollments (student_id, course_id, enrollment_date)
VALUES (301, 401, '2023-07-01');

-- 3. Update the email address of a specific teacher in the "Teacher" table. Choose any teacher and
-- modify their email address.
UPDATE Teacher
SET email = 'new.email@example.com'
WHERE teacher_id = 101;

-- 4. Write an SQL query to delete a specific enrollment record from the "Enrollments" table. Select
-- an enrollment record based on student and course.
DELETE FROM Enrollments
WHERE student_id = 301 AND course_id = 401;

-- 5. Update the "Courses" table to assign a specific teacher to a course. Choose any course and
-- teacher from the respective tables.
select * from courses;
UPDATE Courses
SET teacher_id = 101
WHERE course_id = 402;

-- 6. Delete a specific student from the "Students" table and remove all their enrollment records
-- from the "Enrollments" table. Be sure to maintain referential integrity.
DELETE FROM Students
WHERE student_id = 301;

-- 7. Update the payment amount for a specific payment record in the "Payments" table. Choose any
-- payment record and modify the payment amount.
UPDATE Payments
SET amount = 120.00
WHERE payment_id = 602;
select * from payments;

-- TASK4
-- 1. Write an SQL query to calculate the total payments made by a specific student.
--  You will need to join the "Payments" table with the "Students" table based on the student's ID.
SELECT s.student_id, s.first_name, s.last_name, SUM(p.amount) AS total_payments
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
WHERE s.student_id = 305
GROUP BY s.student_id, s.first_name, s.last_name;
 

-- 2. Write an SQL query to retrieve a list of courses along with the count of students enrolled in each course. 
-- Use a JOIN operation between the "Courses" table and the "Enrollments" table.
SELECT c.course_id, c.course_name, COUNT(e.student_id) AS enrolled_students
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name;
 

-- 3. Write an SQL query to find the names of students who have not enrolled in any course.
--  Use a LEFT JOIN between the "Students" table and the "Enrollments" table to identify students without enrollments.
SELECT s.first_name, s.last_name
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
WHERE e.student_id IS NULL;
 

-- 4. Write an SQL query to retrieve the first name, last name of students, 
-- and the names of the courses they are enrolled in. Use JOIN operations between the "Students" table and the "Enrollments" and "Courses" tables. 

SELECT s.first_name, s.last_name, c.course_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id;
 

-- 5. Create a query to list the names of teachers and the courses they are assigned to.
--  Join the "Teacher" table with the "Courses" table. 
SELECT t.first_name, t.last_name, c.course_name
FROM Teacher t
JOIN Courses c ON t.teacher_id = c.teacher_id;
 

-- 6. Retrieve a list of students and their enrollment dates for a specific course
-- . You'll need to join the "Students" table with the "Enrollments" and "Courses" tables..

SELECT s.first_name, s.last_name, e.enrollment_date
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
WHERE e.course_id = 403;
 

-- 7. Find the names of students who have not made any payments.
--  Use a LEFT JOIN between the "Students" table and the "Payments" table and filter for students with NULL payment records.
SELECT s.first_name, s.last_name
FROM Students s
LEFT JOIN Payments p ON s.student_id = p.student_id
WHERE p.payment_id IS NULL;
-- Insert additional values into Courses table
 
-- 8. Write a query to identify courses that have no enrollments.
--  You'll need to use a LEFT JOIN between the "Courses" table and the "Enrollments" table and filter for courses with NULL enrollment records. 
SELECT c.course_id, c.course_name
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
WHERE e.enrollment_id IS NULL;  
 

-- 9. Identify students who are enrolled in more than one course. 
-- Use a self-join on the "Enrollments" table to find students with multiple enrollment records. 
SELECT e1.student_id, COUNT(DISTINCT e1.course_id) AS courses_enrolled
FROM Enrollments e1
JOIN Enrollments e2 ON e1.student_id = e2.student_id AND e1.enrollment_id <> e2.enrollment_id
GROUP BY e1.student_id
HAVING COUNT(DISTINCT e1.course_id) > 1;
 

-- 10. Find teachers who are not assigned to any courses.
--  Use a LEFT JOIN between the "Teacher" table and the "Courses" table and filter for teachers with NULL course assignments
SELECT t.first_name, t.last_name
FROM Teacher t
LEFT JOIN Courses c ON t.teacher_id = c.teacher_id
WHERE c.course_id IS NULL;
 

-- task5
-- 1 Write an SQL query to calculate the average number of students enrolled in each course. 
-- Use aggregate functions and subqueries to achieve this.


select c.course_id, c.course_name, avg(e.student_id) as average_students_enrolled
from Courses c left join Enrollments e on c.course_id = e.course_id group by c.course_id, c.course_name;

-- 2 Identify the student(s) who made the highest payment. Use a subquery to find the maximum payment amount 
-- and then retrieve the student(s) associated with that amount.

select  amount, payment_date,student_id from Payments where amount = (select max(amount) from Payments);

-- 3 Retrieve a list of courses with the highest number of enrollments. 
-- Use subqueries to find the course(s) with the maximum enrollment count.

select c.course_id, c.course_name, count(e.enrollment_id) as en_count
from Courses c left join Enrollments e on c.course_id = e.course_id  group by c.course_id, c.course_name 
having en_count = (select max(en_count) from (select count(enrollment_id) as en_count from Enrollments 
group by course_id) as maximum_enrolls);


/* 4. Calculate the total payments made to courses taught by each teacher. Use subqueries to sum
payments for each teacher's courses. */


SELECT t.teacher_id, t.first_name AS teacher_first_name,t.last_name AS teacher_last_name,SUM(p.amount) AS total_payments
FROM Teacher t LEFT JOIN Courses c ON t.teacher_id = c.teacher_id
LEFT JOIN Enrollments e ON c.course_id = e.course_id LEFT JOIN Payments p ON e.student_id = p.student_id
GROUP BY t.teacher_id, t.first_name, t.last_name;


/* 5. Identify students who are enrolled in all available courses. Use subqueries to compare a
student's enrollments with the total number of courses. */
-- adding data so that 303 is enrolled in all the courses
INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date)
VALUES
  (536, 303, 401, '2023-06-20'),
  (537, 303, 402, '2023-07-05'),
  (538, 303, 403, '2023-07-15'),
  (539, 303, 404, '2023-08-01'),
  (540, 303, 405, '2023-08-10'),
  (541, 303, 406, '2023-08-20'),
  (542, 303, 407, '2023-09-05'),
  (543, 303, 408, '2023-09-15'),
  (544, 303, 409, '2023-10-01'),
  (545, 303, 410, '2023-10-10'),
  (546, 303, 411, '2023-10-20'),
  (547, 303, 412, '2023-11-05'),
  (548, 303, 413, '2023-11-10'),
  (549, 303, 414, '2023-11-20'),
  (550, 303, 415, '2023-12-05'),
  (551, 303, 421, '2023-12-10'),
  (552, 303, 422, '2023-12-20'),
  (553, 303, 423, '2024-01-05'),
  (554, 303, 424, '2024-01-10'),
  (555, 303, 425, '2024-01-20'),
  (556,303,440,'2024-01-10');

select student_id from Enrollments group by student_id 
having count(distinct course_id) = (select count(distinct course_id) from Courses);

/* 6. Retrieve the names of teachers who have not been assigned to any courses. Use subqueries to
find teachers with no course assignments. */

select teacher_id, first_name, last_name from Teacher where teacher_id not in (select distinct teacher_id from Courses);

/* 7. Calculate the average age of all students. Use subqueries to calculate the age of each student
based on their date of birth. */

select avg(student_age) as average_age 
from (select datediff(curdate(), date_of_birth) / 365 as student_age from Students) as avg_ages;


/* 8. Identify courses with no enrollments. Use subqueries to find courses without enrollment
records. */
-- adding a course which has no enrollments
INSERT INTO Courses (course_id, course_name, credits, teacher_id)
VALUES
  (440, 'Big data', 8, 101);
  -- (450, 'sql',30,102);
  
select course_id, course_name from Courses where course_id not in (select distinct course_id from Enrollments);


-- 9. Calculate the total payments made by each student for each course they are enrolled in. Use
-- subqueries and aggregate functions to sum payments.

select e.student_id,e.course_id,(select sum(amount) from payments p where p.student_id = e.student_id) as total_payments
from enrollments e;

-- 10. Identify students who have made more than one payment. Use subqueries and aggregate
-- functions to count payments per student and filter for those with counts greater than one.

select s.student_id, s.first_name, s.last_name from students s 
join ( select student_id, count(payment_id) as payment_count from payments group by student_id)
as payment_counts on s.student_id = payment_counts.student_id
where payment_counts.payment_count > 1;

-- 11. Write an SQL query to calculate the total payments made by each student. Join the "Students"
-- table with the "Payments" table and use GROUP BY to calculate the sum of payments for each
-- student.

select s.student_id, s.first_name, s.last_name, sum(p.amount) as total_payments from Students s
join Payments p on s.student_id = p.student_id group by s.student_id;

-- 12. Retrieve a list of course names along with the count of students enrolled in each course. Use
-- JOIN operations between the "Courses" table and the "Enrollments" table and GROUP BY to
-- count enrollments.

select c.course_name, count(e.enrollment_id) as students_enrolled from Courses c
left join Enrollments e on c.course_id = e.course_id group by c.course_id;

-- 13. Calculate the average payment amount made by students. Use JOIN operations between the
-- "Students" table and the "Payments" table and GROUP BY to calculate the average.

select s.student_id, s.first_name, s.last_name, avg(p.amount) as average_payment_amount from Students s
join Payments p on s.student_id = p.student_id group by s.student_id;




create database hexadb;
use hexadb;

CREATE TABLE EMP
(EMPNO Integer NOT NULL,
ENAME VARCHAR(10),
JOB VARCHAR(9),
MGR Integer(4),
HIREDATE DATE,
SAL float(7,2),
COMM Integer,
DEPTNO Integer);


INSERT INTO EMP VALUES
(7369, 'SMITH', 'CLERK', 7902,'1980-12-17', 800, NULL, 20);
INSERT INTO EMP VALUES
(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20',1600, 300, 30);

INSERT INTO EMP VALUES
(7521, 'WARD', 'SALESMAN', 7698,'1981-02-22', 1250, 500, 30);

INSERT INTO EMP VALUES
(7566, 'JONES', 'MANAGER', 7839,'1981-04-02', 2975, NULL, 20);

INSERT INTO EMP VALUES
(7654, 'MARTIN', 'SALESMAN', 7698,'1981-09-28', 1250, 1400, 30);

INSERT INTO EMP VALUES
(7698, 'BLAKE', 'MANAGER', 7839,'1981-05-01', 2850, NULL, 30);

INSERT INTO EMP VALUES
(7782, 'CLARK', 'MANAGER', 7839,'1981-06-09',2450, NULL, 10);

INSERT INTO EMP VALUES
(7788, 'SCOTT', 'ANALYST', 7566,'1982-12-09',3000, NULL, 20);

INSERT INTO EMP VALUES
(7839, 'KING', 'PRESIDENT', NULL,'1981-11-17', 5000, NULL, 10);

INSERT INTO EMP VALUES
(7844, 'TURNER', 'SALESMAN', 7698,'1981-09-08', 1500, 0, 30);

INSERT INTO EMP VALUES
(7876, 'ADAMS', 'CLERK', 7788,'1983-01-12', 1100, NULL, 20);

INSERT INTO EMP VALUES
(7900, 'JAMES', 'CLERK', 7698,'1981-12-03', 950, NULL, 30);

INSERT INTO EMP VALUES
(7902, 'FORD', 'ANALYST', 7566,'1981-12-03', 3000, NULL, 20);

INSERT INTO EMP VALUES
(7934, 'MILLER', 'CLERK',7782, '1982-01-23 ', 1300, NULL, 10);




CREATE TABLE DEPT
(DEPTNO INTEGER(2),
DNAME VARCHAR(14),
LOC VARCHAR(13) );

INSERT INTO DEPT VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO DEPT VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO DEPT VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO DEPT VALUES (40, 'OPERATIONS', 'BOSTON');



CREATE TABLE BONUS
(ENAME VARCHAR(10),
JOB VARCHAR(9),
SAL INTEGER,
COMM INTEGER);

CREATE TABLE SALGRADE
(GRADE INTEGER,
LOSAL INTEGER,
HISAL INTEGER);

INSERT INTO SALGRADE VALUES (1, 700, 1200);
INSERT INTO SALGRADE VALUES (2, 1201, 1400);
INSERT INTO SALGRADE VALUES (3, 1401, 2000);
INSERT INTO SALGRADE VALUES (4, 2001, 3000);
INSERT INTO SALGRADE VALUES (5, 3001, 9999);

select * from emp;
select * from dept;
select * from salgrade;
-- 1) List all employees along with their department names

select e.ename,d.dname from emp e left join dept d on e.deptno=d.deptno;

-- 2) Find the annual salary for all the employees

select ename, (sal*12) ann_Sal from emp ;

-- 3) Add a bonus of 500 to all the employees and find the total salary for all the employees

select ename,sum(sal+500) total_sal from emp group by ename;

-- 4) Display the total number of employees in the company

select count(empno) total_emp from emp;

-- 5) Find the total salary paid by the company

select sum(sal) from emp;

-- 6) Show the highest salary for each job title
select * from emp;
select job, max(sal) from emp group by job;

-- 7) List employees whose names start with the letter 'A' and are in the 'SALES' department

select ename from emp where ename like 'A%' and deptno in (select deptno from dept where dname='SALES');
select * from dept;