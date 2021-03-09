package universityManagement.repository;

import universityManagement.baseClasses.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Repository {

    private Connection con = null;


    public List<Faculty> getFaculties () throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM FACULTIES");

        List<Faculty> facultyList = new ArrayList<>();

        while (rs1.next()) {

            facultyList.add(new Faculty(
                    rs1.getInt(1),
                    rs1.getString(2)
            ));

        }

        con.close();
        return  facultyList;

    }

    public List<String> getStudyLevels (int facultyID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT STUDY_LEVEL FROM SPECIALIZATIONS WHERE FACULTY_ID = " + facultyID + " GROUP BY STUDY_LEVEL;");

        List<String> studyLevelList = new ArrayList<>();

        while (rs1.next()) studyLevelList.add(rs1.getString(1));

        con.close();
        return studyLevelList;

    }

    public List<Specialization> getSpecializations (int facultyID, String studyLevel) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM SPECIALIZATIONS WHERE FACULTY_ID = " + facultyID + " AND STUDY_LEVEL = \"" + studyLevel + "\"");

        List<Specialization> specializationList = new ArrayList<>();

        while (rs1.next())
            specializationList.add(new Specialization(
                    rs1.getInt(1),
                    rs1.getString(2),
                    studyLevel,
                    null));


        con.close();
        return specializationList;

    }

    public List<Integer> getStudyYears (int specializationID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT STUDY_YEAR FROM STUDENT_GROUPS WHERE SPECIALIZATION_ID = " + specializationID + " GROUP BY STUDY_YEAR");

        List<Integer> studyYearsList = new ArrayList<>();

        while (rs1.next()) studyYearsList.add(rs1.getInt(1));

        con.close();
        return studyYearsList;

    }

    public List<Integer> getStudentGroups (int specializationID, int studyYear) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT GROUP_NR FROM STUDENT_GROUPS WHERE SPECIALIZATION_ID = " + specializationID + " AND STUDY_YEAR = " + studyYear);

        List<Integer> studentGroupList = new ArrayList<>();

        while (rs1.next()) studentGroupList.add(rs1.getInt(1));

        con.close();
        return studentGroupList;

    }

    public Integer getFacultyID (String facultyName) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT FACULTY_ID FROM FACULTIES WHERE FACULTY_NAME = \"" + facultyName + "\"");

        Integer facultyID = null;

        if (rs1.next()) facultyID = rs1.getInt(1);

        con.close();
        return facultyID;
    }

    public Integer getSpecializationID (int facultyID, String specializationName) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT SPECIALIZATION_ID FROM SPECIALIZATIONS WHERE SPECIALIZATION_NAME = \"" + specializationName + "\" AND FACULTY_ID = " + facultyID);

        Integer specializationID = null;

        if (rs1.next()) specializationID = rs1.getInt(1);

        con.close();
        return specializationID;

    }

    public String checkUserPassCombination (String username, String password) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();

        ResultSet rs1 = statement.executeQuery("SELECT USER_TYPE FROM USERS WHERE USER_USERNAME = \"" + username +"\" AND USER_PASSWORD = \"" + password + "\";");

        String userType = null;

        if (rs1.next()) userType = rs1.getString(1);

        con.close();
        return userType;

    }

    public String getName (String username) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID, USER_TYPE FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        int userID = 0;
        String table = null;


        if (rs1.next()) {

            userID = rs1.getInt(1);
            table = rs1.getString(2) + "S";

        }


        String name = null;


        if (Objects.isNull(table)) {

            con.close();
            return null;

        } else {

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT LAST_NAME, FIRST_NAME FROM " + table + " WHERE USER_ID = " + userID);

            if (rs2.next()) name = rs2.getString(1) + " " + rs2.getString(2);

            con.close();
            return name;

        }
    }

    public Integer getUserID (String username) throws SQLException {


        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        Integer userID = null;


        if (rs1.next()) {

            userID = rs1.getInt(1);

        }

        con.close();
        return userID;

    }

    public boolean checkUsernameAvailability (String username) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_USERNAME FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        if (rs1.next()) {

            con.close();
            return false;

        } else {

            con.close();
            return true;

        }

    }

    private Integer addUser (String username, String password, String userType) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MAX(USER_ID) FROM USERS");

        Integer userID = null;

        if (rs1.next()) userID = rs1.getInt(1) + 1;

        Statement statement2 = con.createStatement();
        statement2.execute("INSERT INTO USERS VALUES (" + userID + ", \"" + username + "\", \"" + password + "\", \"" + userType + "\")");

        con.close();
        return userID;

    }

    public void addAdmin (String username, String password, String userType, String lastName, String firstName) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MAX(ADMIN_ID) FROM ADMINS");

        Integer adminID = null;

        if (rs1.next()) adminID = rs1.getInt(1) + 1;

        Statement statement2 = con.createStatement();

        Integer userID = addUser(username, password, userType);

        statement2.execute("INSERT INTO ADMINS VALUES (" + adminID + ", " + userID + ", \"" + lastName + "\", \"" + firstName +"\")");

        con.close();
    }

    public void addProfessor (String username, String password, String userType, String lastName, String firstName, String birthDate, int facultyID, String expertise) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MAX(PROFESSOR_ID) FROM PROFESSORS");

        Integer professorID = null;

        if (rs1.next()) professorID = rs1.getInt(1) + 1;

        Statement statement2 = con.createStatement();

        Integer userID = addUser(username, password, userType);

        statement2.execute("INSERT INTO PROFESSORS VALUES (" + professorID + ", " + userID + ", \"" + lastName + "\", \"" + firstName + "\", \""  + birthDate + "\", " + facultyID + ", \"" + expertise +"\")");

        con.close();
    }

    public void addStudent (String username, String password, String userType, String lastName, String firstName, String birthDate, String email, String phone, int groupID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MAX(STUDENT_ID) FROM STUDENTS");

        Integer studentID = null;

        if (rs1.next()) studentID = rs1.getInt(1) + 1;

        Statement statement2 = con.createStatement();

        Integer userID = addUser(username, password, userType);

        statement2.execute("INSERT INTO STUDENTS VALUES (" + studentID + ", " + userID + ", \"" + lastName + "\", \"" + firstName + "\", \""  + birthDate + "\", \"" + email + "\", \"" + phone +"\", " + groupID + ")");

        con.close();
    }

    public List<String> getAccountsDataForDeletion (String username) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery(
                "SELECT ADMINS.LAST_NAME, ADMINS.FIRST_NAME, USERS.USER_USERNAME, USERS.USER_TYPE, USERS.USER_ID\n" +
                "FROM ADMINS\n" +
                "INNER JOIN USERS ON USERS.USER_ID = ADMINS.USER_ID\n" +
                "WHERE USERS.USER_USERNAME NOT IN (\"" + username + "\")\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT PROFESSORS.LAST_NAME, PROFESSORS.FIRST_NAME, USERS.USER_USERNAME, USERS.USER_TYPE, USERS.USER_ID\n" +
                "FROM PROFESSORS\n" +
                "INNER JOIN USERS ON USERS.USER_ID = PROFESSORS.USER_ID\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT STUDENTS.LAST_NAME, STUDENTS.FIRST_NAME, USERS.USER_USERNAME, USERS.USER_TYPE, USERS.USER_ID\n" +
                "FROM STUDENTS\n" +
                "INNER JOIN USERS ON USERS.USER_ID = STUDENTS.USER_ID\n" +
                "ORDER BY 1 ASC, 2 ASC;");

        List<String> accountDataList = new ArrayList<>();
        int count = 0;

        while (rs1.next()) {

            count++;
            accountDataList.add("" + rs1.getString(1) + " " + rs1.getString(2) + ", " + rs1.getString(3) + ", " + rs1.getString(4) + ", ID = " + rs1.getInt(5));

        }

        con.close();
        return accountDataList;

    }

    public void deleteAccount (int userID, String table) throws SQLException {

        con = DatabaseUtils.getConnection();

        Statement statement1 = con.createStatement();

        ResultSet rs1 = statement1.executeQuery("SELECT USER_TYPE FROM USERS WHERE USER_ID = " + userID);

        String userType = "";

        if (rs1.next()) userType = rs1.getString(1);

        if (userType.equals("PROFESSOR")) {

            Statement statement2 = con.createStatement();

            ResultSet rs2 = statement2.executeQuery("SELECT PROFESSOR_ID FROM PROFESSORS WHERE USER_ID = " + userID);

            int professorID = 0;

            if (rs2.next()) professorID = rs2.getInt(10);

            Statement statement3 = con.createStatement();
            statement3.execute("DELETE FROM TASKS WHERE PROFESSOR_ID = " + professorID);

            Statement statement4 = con.createStatement();
            statement4.execute("UPDATE COURSES SET PROFESSOR_ID = 0 WHERE PROFESSOR_ID = " + professorID);

        }

        Statement statement5 = con.createStatement();

        statement5.execute("DELETE FROM " + table + " WHERE USER_ID = " + userID);

        Statement statement6 = con.createStatement();

        statement6.execute("DELETE FROM USERS WHERE USER_ID = " + userID);

        con.close();
    }

    public List<Course> getSpecializationCourses (int specializationID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM COURSES WHERE SPECIALIZATION_ID = " + specializationID);

        Specialization specialization = null;
        Faculty faculty = null;

        Statement statement2 = con.createStatement();
        ResultSet rs2 = statement2.executeQuery("SELECT * FROM SPECIALIZATIONS WHERE SPECIALIZATION_ID = " + specializationID);

        if (rs2.next()) {

            Integer facultyID = rs2.getInt(4);

            Statement statement3 = con.createStatement();
            ResultSet rs3 = statement3.executeQuery("SELECT FACULTY_NAME FROM FACULTIES WHERE FACULTY_ID = " + facultyID);

            if (rs3.next()) faculty = new Faculty(facultyID, rs3.getString(1));

            specialization = new Specialization(rs2.getInt(1), rs2.getString(2), rs2.getString(3), faculty);

        }


        Professor professor = null;

        List<Course> courseList = new ArrayList<>();


        while (rs1.next()) {

            int professorID = rs1.getInt(4);

            Statement statement3 = con.createStatement();
            ResultSet rs3 = statement3.executeQuery("SELECT * FROM PROFESSORS WHERE PROFESSOR_ID = " + professorID);

            if (rs3.next())
                professor = new Professor(
                    professorID, null,
                    rs3.getString(3),
                    rs3.getString(4),
                    rs3.getString(5),
                    faculty,
                    rs3.getString(7)
                );


            courseList.add(new Course(
                    rs1.getInt(1),
                    rs1.getString(2),
                    specialization,
                    professor
            ));

        }

        con.close();
        return courseList;

    }

    public List<Professor> getProfessors (int facultyID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM PROFESSORS WHERE FACULTY_ID = " + facultyID);


        Faculty faculty = null;

        Statement statement2 = con.createStatement();
        ResultSet rs2 = statement2.executeQuery("SELECT FACULTY_NAME FROM FACULTIES WHERE FACULTY_ID = " + facultyID);

        if (rs2.next()) faculty = new Faculty(facultyID, rs2.getString(1));


        List<Professor> professorList = new ArrayList<>();

        while (rs1.next()) professorList.add(
                new Professor(
                    rs1.getInt(1),
                    null,
                    rs1.getString(3),
                    rs1.getString(4),
                    rs1.getString(5),
                    faculty,
                    rs1.getString(7)
                    ));

        con.close();
        return professorList;

    }

    public void changeCourseProfessor (int courseID, int professorID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();

        statement.execute("UPDATE COURSES SET PROFESSOR_ID = " + professorID + " WHERE COURSE_ID = " + courseID);

        con.close();

    }

    public Integer getProfessorID (int userID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT PROFESSOR_ID FROM PROFESSORS WHERE USER_ID = " + userID);

        Integer professorID = null;

        if (rs1.next()) professorID = rs1.getInt(1);

        con.close();
        return professorID;

    }

    public List<Course> getProfessorCourseList (int professorID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM COURSES WHERE PROFESSOR_ID = " + professorID);

        Specialization specialization = null;
        Faculty faculty = null;

        List<Course> courseList = new ArrayList<>();

        while (rs1.next()) {

            int specializationID = rs1.getInt(3);

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT * FROM SPECIALIZATIONS WHERE SPECIALIZATION_ID = " + specializationID);

            if (rs2.next()) {

                int facultyID = rs2.getInt(4);

                Statement statement3 = con.createStatement();
                ResultSet rs3 = statement3.executeQuery("SELECT * FROM FACULTIES WHERE FACULTY_ID = " + facultyID);


                if (rs3.next()) faculty = new Faculty(
                        rs3.getInt(1),
                        rs3.getString(2));


                specialization = new Specialization(
                        rs2.getInt(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        faculty);


                courseList.add(new Course(
                        rs1.getInt(1),
                        rs1.getString(2),
                        specialization,
                        null
                ));

            }

        }

        con.close();
        return courseList;

    }

    public List<CourseSchedule> getProfessorCourseSchedule (List<Course> courseList) throws SQLException {

        con = DatabaseUtils.getConnection();
        List<CourseSchedule> courseScheduleList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {


            int courseID = courseList.get(i).getId();

            Statement statement1 = con.createStatement();
            ResultSet rs1 = statement1.executeQuery("SELECT * FROM COURSES_TIMETABLE WHERE COURSE_ID = " + courseID);

            if (rs1.next()) courseScheduleList.add(
                    new CourseSchedule(
                            courseList.get(i),
                            rs1.getString(2),
                            rs1.getString(3),
                            rs1.getString(4)
                    ));

        }

        con.close();
        return courseScheduleList;

    }

    public List<Task> getProfessorCourseTasks(Integer professorID, Integer courseID) throws SQLException {

        con = DatabaseUtils.getConnection();
        List<Task> taskList = new ArrayList<>();

        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM TASKS WHERE COURSE_ID = " + courseID + " AND PROFESSOR_ID = " + professorID);

        while (rs1.next()) taskList.add(
                new Task(
                        rs1.getInt(1),
                        null,
                        null,
                        rs1.getString(4)
                ));


        con.close();
        return taskList;

    }

    public List<Task> getCourseTasks (Integer courseID) throws SQLException {

        con = DatabaseUtils.getConnection();
        List<Task> taskList = new ArrayList<>();

        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM TASKS WHERE COURSE_ID = " + courseID);

        while (rs1.next()) taskList.add(
                new Task(
                        rs1.getInt(1),
                        null,
                        null,
                        rs1.getString(4)
                ));


        con.close();
        return taskList;

    }

    public List<Exam> getProfessorExams (List<Course> courseList) throws SQLException {

        con = DatabaseUtils.getConnection();
        List<Exam> examList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {

            int courseID = courseList.get(i).getId();

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM EXAMS WHERE COURSE_ID = " + courseID + " ORDER BY EXAM_DATETIME ASC");

            while (rs.next())
                examList.add(new Exam(
                    courseList.get(i),
                    rs.getString(2),
                    rs.getString(3)
            ));

        }

        con.close();
        return examList;

    }

    public List<Grade> getExamGrades (List<Course> courseList) throws SQLException {

        con = DatabaseUtils.getConnection();

        List<Grade> gradeList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {

            Course course = courseList.get(i);
            int courseID = course.getId();

            List <Student> studentList = new Repository().getCourseStudents(courseID);

            for (int j = 0; j < studentList.size(); j++) {

                Student student = studentList.get(j);
                int studentID = student.getId();

                Statement statement1 = con.createStatement();
                ResultSet rs1 = statement1.executeQuery("SELECT * FROM GRADES WHERE STUDENT_ID = " + studentID + " AND COURSE_ID = " + courseID);

                if (rs1.next()) {

                    gradeList.add(new Grade(
                            student,
                            course,
                            rs1.getString(3),
                            rs1.getInt(4)
                            ));

                } else {

                    gradeList.add(new Grade(
                            student,
                            course,
                            "-",
                            0
                    ));

                }
            }
        }

        con.close();
        return gradeList;

    }

    public List<Student> getCourseStudents (int courseID) throws SQLException {

        con = DatabaseUtils.getConnection();

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT s.STUDENT_ID, s.LAST_NAME, s.FIRST_NAME\n" +
                        "FROM STUDENTS s \n" +
                        "INNER JOIN STUDENT_GROUPS sg ON s.GROUP_ID = sg.GROUP_ID\n" +
                        "INNER JOIN SPECIALIZATIONS sp ON sg.SPECIALIZATION_ID = sp.SPECIALIZATION_ID\n" +
                        "INNER JOIN COURSES c ON sp.SPECIALIZATION_ID = c.SPECIALIZATION_ID\n" +
                        "WHERE COURSE_ID = " + courseID + " \n" +
                        "ORDER BY s.LAST_NAME ASC, s.FIRST_NAME ASC"
        );

        List<Student> studentList = new ArrayList<>();

        while (rs.next())

            studentList.add(new Student(
                rs.getInt(1),
                null,
                rs.getString(2),
                rs.getString(3),
                null,
                null,
                null,
                null
            ));

        con.close();
        return studentList;

    }

    public String getStudentGrade (Integer studentID, Integer courseID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT GRADE FROM GRADES WHERE STUDENT_ID = " + studentID + " AND COURSE_ID = " + courseID);

        Integer gradeInt = null;
        String grade = null;

        if (rs.next())  {

            gradeInt = rs.getInt(1);
            grade = String.valueOf(gradeInt);

        }

        con.close();
        return grade;

    }

    public String getGradeExamDate (Integer studentID, Integer courseID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT EXAM_DATETIME FROM GRADES WHERE STUDENT_ID = " + studentID + " AND COURSE_ID = " + courseID);

        String examDate = null;

        if (rs.next()) examDate = rs.getString(1);

        con.close();
        return examDate;

    }

    public void modifyGrade (Integer studentID, Integer courseID, String examDate, Integer grade) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM GRADES WHERE STUDENT_ID = " + studentID + " AND COURSE_ID = " + courseID);

        if (rs1.next()) {

            Statement statement2 = con.createStatement();

            statement2.execute("UPDATE GRADES SET GRADE = " + grade + ", EXAM_DATETIME = \"" + examDate + "\" WHERE STUDENT_ID = " + studentID + " AND COURSE_ID = " + courseID);

        } else {

            Statement statement2 = con.createStatement();

            statement2.execute("INSERT INTO GRADES VALUES (" + studentID + ", " + courseID + ", \"" + examDate + "\", + " + grade + ")");

        }

        con.close();

    }

    public void addTask (Integer professorID, Integer courseID, String taskDescription) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MAX(TASK_ID) FROM TASKS");

        int taskID = 0;

        if (rs1.next()) taskID = rs1.getInt(1) + 1;

        Statement statement2 = con.createStatement();
        statement2.execute("INSERT INTO TASKS VALUES (" + taskID + ", " + professorID + ", " + courseID + ", \"" + taskDescription + "\")");

        con.close();

    }

    public boolean scheduleExam (Integer courseID, String examType, String examDateTime) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM EXAMS WHERE COURSE_ID = " + courseID + " AND EXAM_DATETIME = \"" + examDateTime + "\"");

        if (rs1.next()) {

            con.close();
            return false;

        } else {

            Statement statement2 = con.createStatement();
            statement2.execute("INSERT INTO EXAMS VALUES (" + courseID + ", \"" + examType + "\", \"" + examDateTime + "\")");
            con.close();
            return true;

        }

    }

    public void deleteExam (Integer courseID, String examDateTime) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM EXAMS WHERE COURSE_ID = " + courseID + " AND EXAM_DATETIME = \"" + examDateTime + "\"");

        if (rs1.next()) {

            Statement statement2 = con.createStatement();
            statement2.execute("DELETE FROM EXAMS WHERE COURSE_ID = " + courseID + " AND EXAM_DATETIME = \"" + examDateTime + "\"");

        }

        con.close();

    }

    public void deleteTask (Integer taskID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT * FROM TASKS WHERE TASK_ID = " + taskID);

        if (rs1.next()) {

            Statement statement2 = con.createStatement();
            statement2.execute("DELETE FROM TASKS WHERE TASK_ID = " + taskID);

        }

        con.close();

    }

    public Integer getStudentID (Integer userID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT STUDENT_ID FROM STUDENTS WHERE USER_ID = " + userID);

        Integer studentID = null;

        if (rs1.next()) studentID = rs1.getInt(1);

        con.close();
        return studentID;

    }

    public Integer getStudentSpecializationID (Integer studentID) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("" +
                "SELECT sg.SPECIALIZATION_ID\n" +
                "FROM STUDENT_GROUPS sg\n" +
                "INNER JOIN STUDENTS s ON sg.GROUP_ID = s.GROUP_ID\n" +
                "WHERE s.STUDENT_ID = " + studentID);

        Integer specializationID = 0;

        if (rs1.next()) specializationID = rs1.getInt(1);

        con.close();
        return specializationID;

    }

    public List<Exam> getSpecializationExams (List<Course> courseList) throws SQLException {

        con = DatabaseUtils.getConnection();
        List<Exam> examList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {

            int courseID = courseList.get(i).getId();

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM EXAMS WHERE COURSE_ID = " + courseID + " ORDER BY EXAM_DATETIME ASC");

            while (rs.next())
                examList.add(new Exam(
                        courseList.get(i),
                        rs.getString(2),
                        rs.getString(3)
                ));

        }

        con.close();
        return examList;


    }

    public List<Grade> getStudentGrades (List<Course> courseList, Integer studentID) throws SQLException {

        con = DatabaseUtils.getConnection();

        List<Grade> gradeList = new ArrayList<>();

        for (Course course : courseList) {

            Statement statement1 = con.createStatement();
            ResultSet rs1 = statement1.executeQuery("SELECT * FROM GRADES WHERE COURSE_ID = " + course.getId() + " AND STUDENT_ID = " + studentID);

            if (rs1.next())

                gradeList.add(new Grade(
                   null,
                   course,
                   rs1.getString(3),
                   rs1.getInt(4)
                ));

            else gradeList.add(new Grade(
                    null,
                    course,
                    "-",
                    0
            ));

        }

        con.close();
        return gradeList;


    }

    public boolean checkPassword (Integer userID, String password) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_ID = " + userID + " AND USER_PASSWORD = \"" + password + "\"");

        if (rs1.next()) {

            con.close();
            return true;

        } else {

            con.close();
            return false;

        }

    }

    public void changePassword (Integer userID, String newPassword) throws SQLException {

        con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        statement1.execute("UPDATE USERS SET USER_PASSWORD = \"" + newPassword + "\" WHERE USER_ID = " + userID);
        con.close();


    }



    public static void main(String[] args) throws SQLException {

        //System.out.println(getStudyLevels(3));
        //System.out.println(getSpecializationID(1, "International Relations"));
        //addAdmin("admin2", "admin", "ADMIN", "admin", "admin");
        //addProfessor("professor10", "professor", "PROFESSOR", "professor", "professor", "2021-02-22", 1, "expertise");
        //addStudent("student13", "student", "STUDENT", "student", "student", "2021-02-22", "email", "phone", 5);
        //System.out.println(new Repository().getProfessorID(2));
        //System.out.println(new Repository().getProfessorCourseList(3));
        //System.out.println(new Repository().getProfessorCourseSchedule(new Repository().getProfessorCourseList(3)));
        //System.out.println(new Repository().getExamGrades(new Repository().getProfessorExams(new Repository().getProfessorCourseList(3))));
        //System.out.println(new Repository().getCourseStudents(1));
        //new Repository().deleteExam(7, "2021-02-10 10:00:00");
//        System.out.println(new Repository().getSpecializationCourses(3));;




    }
}
