package universityManagement.services;

import universityManagement.baseClasses.*;
import universityManagement.repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class Services {

    private Repository repository = null;

    public Services () {

        this.repository = new Repository();

    }


    public List<Faculty> getFaculties () {


        try {

            List<Faculty> facultyList = repository.getFaculties();
            return facultyList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }
    }

    public List<String> getStudyLevels (int facultyID) {

        try {

            List<String> studyLevelList = repository.getStudyLevels(facultyID);
            return studyLevelList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }
    }

    public List<Specialization> getSpecializations (int facultyID, String studyLevel) {

        try {

            List<Specialization> specializationList = repository.getSpecializations(facultyID, studyLevel);
            return specializationList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Integer> getStudyYears (int specializationID) {

        try {

            List<Integer> studyYearsList = repository.getStudyYears(specializationID);
            return studyYearsList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public List<Integer> getStudentGroups (int specializationID, int studyYear) {

        try {

            List<Integer> studentGroupList = repository.getStudentGroups(specializationID, studyYear);
            return studentGroupList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public Integer getFacultyID (String facultyName) {

        try {

            Integer facultyID = repository.getFacultyID(facultyName);
            return facultyID;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public Integer getSpecializationID (int facultyID, String specializationName) {

        try {

            Integer specializationID = repository.getSpecializationID(facultyID, specializationName);
            return specializationID;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public String checkUserPassCombination (String username, String password) {

        try {

            String userType = repository.checkUserPassCombination(username, password);
            return userType;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public String getName (String username) {

        try {

            String name = repository.getName(username);
            return name;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public Integer getUserID (String username) {

        try {

            Integer userID = repository.getUserID(username);
            return userID;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public boolean checkUsernameAvailability (String username) {

        try {

            boolean availability = repository.checkUsernameAvailability(username);
            return availability;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;
        }

    }

    public boolean addAdmin (String username, String password, String userType, String lastName, String firstName) {

        try {

            repository.addAdmin(username, password, userType, lastName, firstName);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean addProfessor (String username, String password, String userType, String lastName, String firstName, String birthDate, int facultyID, String expertise) {

        try {

            repository.addProfessor(username, password, userType, lastName, firstName, birthDate, facultyID, expertise);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean addStudent (String username, String password, String userType, String lastName, String firstName, String birthDate, String email, String phone, int groupID) {

        try {

            repository.addStudent(username, password, userType, lastName, firstName, birthDate, email, phone, groupID);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public List<String> getAccountsDataForDeletion (String username) {

        try {

            List<String> accountDataList = repository.getAccountsDataForDeletion(username);
            return accountDataList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public boolean deleteAccount (int userID, String table) {

        try {

            repository.deleteAccount(userID, table);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public List<Course> getSpecializationCourses (int specializationID) {

        try {

            List<Course> courseList = repository.getSpecializationCourses(specializationID);
            return courseList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public List<Professor> getProfessors (int facultyID) {

        try {

            List<Professor> professorList = repository.getProfessors(facultyID);
            return professorList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public boolean changeCourseProfessor (int courseID, int professorID) {

        try {

            repository.changeCourseProfessor(courseID, professorID);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public Integer getProfessorID (int userID) {

        try {

            Integer professorID = repository.getProfessorID(userID);
            return professorID;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public List<Course> getProfessorCourseList (int professorID)  {

        try {

            List<Course> professorCourseList = repository.getProfessorCourseList(professorID);
            return professorCourseList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<CourseSchedule> getProfessorCourseSchedule (List<Course> courseList) {

        try {

            List<CourseSchedule> courseScheduleList = repository.getProfessorCourseSchedule(courseList);
            return courseScheduleList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Task> getProfessorCourseTasks (Integer professorID, Integer courseID) {

        try {

            List<Task> taskList = repository.getProfessorCourseTasks(professorID, courseID);
            return taskList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Exam> getProfessorExams (List<Course> courseList) {

        try {

            List<Exam> examList = repository.getProfessorExams(courseList);
            return examList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public List<Grade> getExamGrades (List<Course> courseList) {

        try {

            List<Grade> gradeList = repository.getExamGrades(courseList);
            return gradeList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Student> getCourseStudents (int courseID) {

        try {

            List<Student> studentList = repository.getCourseStudents(courseID);
            return studentList;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public String getStudentGrade (int studentID, int courseID) {

        try {

            String grade = repository.getStudentGrade(studentID, courseID);
            return grade;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public String getGradeExamDate (int studentID, int courseID){

        try {

            String examDate = repository.getGradeExamDate(studentID, courseID);
            return examDate;
        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public boolean modifyGrade (Integer studentID, Integer courseID, String examDate, Integer grade) {

        try {

            repository.modifyGrade(studentID, courseID, examDate, grade);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean addTask (Integer professorID, Integer courseID, String taskDescription) {

        try {

            repository.addTask(professorID, courseID, taskDescription);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public String scheduleExam (Integer courseID, String examType, String examDateTime) {

        try {

            if (repository.scheduleExam(courseID, examType, examDateTime)) {

                return "SUCCESS";

            } else {

                return "TAKEN";

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return "ERROR";
        }

    }

    public boolean deleteExam (Integer courseID, String examDateTime) {

        try {

            repository.deleteExam(courseID, examDateTime);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean deleteTask (Integer taskID) {

        try {

            repository.deleteTask(taskID);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public Integer getStudentID (Integer userID) {

        try {

            Integer studentID = repository.getStudentID(userID);
            return studentID;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public Integer getStudentSpecializationID (Integer studentID) {

        try {

            Integer specializationID = repository.getStudentSpecializationID(studentID);
            return specializationID;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Task> getCourseTasks (Integer courseID) {

        try {

            return repository.getCourseTasks(courseID);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Exam> getSpecializationExams (List<Course> courseList) {

        try {

            return repository.getSpecializationExams(courseList);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public List<Grade> getStudentGrades (List<Course> courseList, Integer studentID) {

        try {

            return repository.getStudentGrades(courseList, studentID);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public boolean checkPassword (Integer userID, String password) {

        try {

            return repository.checkPassword(userID, password);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean changePassword (Integer userID, String newPassword) {

        try {

            repository.changePassword(userID, newPassword);
            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean verifyDate (String date) {

        char[] chars = date.toCharArray();

        if (chars.length > 10) return false;

        for (int i = 0; i < chars.length; i++) {

            if (
                    (chars[i] != '0') &&
                            (chars[i] != '1') &&
                            (chars[i] != '2') &&
                            (chars[i] != '3') &&
                            (chars[i] != '4') &&
                            (chars[i] != '5') &&
                            (chars[i] != '6') &&
                            (chars[i] != '7') &&
                            (chars[i] != '8') &&
                            (chars[i] != '9') &&
                            (chars[i] != '-')

            ) {

                return false;

            }
        }

        if (chars[4] != '-') return false;
        if (chars[7] != '-') return false;

        char[] charYear = date.substring(0,4).toCharArray();
        char[] charMonth = date.substring(5, 7).toCharArray();
        char[] charDay = date.substring(8).toCharArray();

        for (int i = 0; i < charYear.length; i++) {

            if (
                    (charYear[i] != '0') &&
                            (charYear[i] != '1') &&
                            (charYear[i] != '2') &&
                            (charYear[i] != '3') &&
                            (charYear[i] != '4') &&
                            (charYear[i] != '5') &&
                            (charYear[i] != '6') &&
                            (charYear[i] != '7') &&
                            (charYear[i] != '8') &&
                            (charYear[i] != '9')

            ) {

                return false;

            }
        }

        for (int i = 0; i < charMonth.length; i++) {

            if (
                    (charMonth[i] != '0') &&
                            (charMonth[i] != '1') &&
                            (charMonth[i] != '2') &&
                            (charMonth[i] != '3') &&
                            (charMonth[i] != '4') &&
                            (charMonth[i] != '5') &&
                            (charMonth[i] != '6') &&
                            (charMonth[i] != '7') &&
                            (charMonth[i] != '8') &&
                            (charMonth[i] != '9')

            ) {

                return false;

            }
        }

        for (int i = 0; i < charDay.length; i++) {

            if (
                    (charDay[i] != '0') &&
                            (charDay[i] != '1') &&
                            (charDay[i] != '2') &&
                            (charDay[i] != '3') &&
                            (charDay[i] != '4') &&
                            (charDay[i] != '5') &&
                            (charDay[i] != '6') &&
                            (charDay[i] != '7') &&
                            (charDay[i] != '8') &&
                            (charDay[i] != '9')

            ) {

                return false;

            }
        }


        int intYear = Integer.parseInt(date.substring(0,4));
        int intMonth = Integer.parseInt(date.substring(5,7));
        int intDay = Integer.parseInt(date.substring(8));

        if (intYear < 1900) return false;
        if ((intMonth == 0) || (intMonth > 12)) return false;
        if (intDay == 0) return false;


        if (intMonth == 2) {

            if ((intYear % 4) == 0) {

                if (intDay > 29) return false;

            } else if (intDay > 28) {

                return false;

            }

        }


        if (
                (intMonth == 1) ||
                        (intMonth == 3) ||
                        (intMonth == 5) ||
                        (intMonth == 7) ||
                        (intMonth == 8) ||
                        (intMonth == 10) ||
                        (intMonth == 12)

        ) if (intDay > 31) return false;


        if (
                (intMonth == 4) ||
                        (intMonth == 6) ||
                        (intMonth == 9) ||
                        (intMonth == 11)
                
        ) if (intDay > 30) return false;


        return true;

    }

    public boolean verifyTime (String time) {

        char[] chars = time.toCharArray();

        if (chars.length > 5) return false;

        for (int i = 0; i < chars.length; i++) {

            if (
                    (chars[i] != '0') &&
                            (chars[i] != '1') &&
                            (chars[i] != '2') &&
                            (chars[i] != '3') &&
                            (chars[i] != '4') &&
                            (chars[i] != '5') &&
                            (chars[i] != '6') &&
                            (chars[i] != '7') &&
                            (chars[i] != '8') &&
                            (chars[i] != '9') &&
                            (chars[i] != ':')

            ) {

                return false;

            }
        }

        if (chars[2] != ':') return false;

        char[] charHour = time.substring(0,2).toCharArray();
        char[] charMin = time.substring(3).toCharArray();

        for (int i = 0; i < charHour.length; i++) {

            if (
                    (charHour[i] != '0') &&
                            (charHour[i] != '1') &&
                            (charHour[i] != '2') &&
                            (charHour[i] != '3') &&
                            (charHour[i] != '4') &&
                            (charHour[i] != '5') &&
                            (charHour[i] != '6') &&
                            (charHour[i] != '7') &&
                            (charHour[i] != '8') &&
                            (charHour[i] != '9')

            ) {

                return false;

            }
        }

        for (int i = 0; i < charMin.length; i++) {

            if (
                    (charMin[i] != '0') &&
                            (charMin[i] != '1') &&
                            (charMin[i] != '2') &&
                            (charMin[i] != '3') &&
                            (charMin[i] != '4') &&
                            (charMin[i] != '5') &&
                            (charMin[i] != '6') &&
                            (charMin[i] != '7') &&
                            (charMin[i] != '8') &&
                            (charMin[i] != '9')

            ) {

                return false;

            }
        }

        int intHour = Integer.parseInt(time.substring(0,2));
        int intMin = Integer.parseInt(time.substring(3));

        if (intHour > 23) return false;
        if (intMin > 59) return false;

        return true;
    }


    public static void main(String[] args) {

/*        new Services().verifyDate("2012-10-101");
        new Services().verifyDate("2012-10-1a");
        new Services().verifyDate("2012-10210");
        new Services().verifyDate("20-2-10-10");
        new Services().verifyDate("2012-10-10");
        System.out.println();
        new Services().verifyDate("0000-10-10");
        System.out.println();
        new Services().verifyDate("0002-10-10");
        System.out.println();
        new Services().verifyDate("0012-10-10");
        System.out.println();
        new Services().verifyDate("0212-10-10");*/


        System.out.println(new Services().verifyDate("1998-02-29"));


    }
}
