package universityManagement.baseClasses;

public class Grade {

    private Student student;
    private Course course;
    private String dateTime;
    private int grade;

    public Grade(Student student, Course course, String examDate, int grade) {
        this.student = student;
        this.course = course;
        this.dateTime = examDate;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student=" + student +
                ", course=" + course +
                ", examDate='" + dateTime + '\'' +
                ", grade=" + grade +
                '}';
    }


}
