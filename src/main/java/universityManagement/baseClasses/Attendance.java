package universityManagement.baseClasses;

public class Attendance {

    private Student student;
    private Course course;
    private String date;
    private boolean attendance;

    public Attendance(Student student, Course course, String date, boolean attendance) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.attendance = attendance;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "student=" + student +
                ", course=" + course +
                ", date='" + date + '\'' +
                ", attendance=" + attendance +
                '}';
    }


}
