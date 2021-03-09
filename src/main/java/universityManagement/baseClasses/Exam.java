package universityManagement.baseClasses;

public class Exam {

    private Course course;
    private String examType;
    private String dateTime;

    public Exam(Course course, String examType, String dateTime) {
        this.course = course;
        this.examType = examType;
        this.dateTime = dateTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "course=" + course +
                ", examType='" + examType + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
