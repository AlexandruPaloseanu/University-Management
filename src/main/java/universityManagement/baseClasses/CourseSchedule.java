package universityManagement.baseClasses;

public class CourseSchedule {

    private Course course;
    private String day;
    private String time;
    private String location;

    public CourseSchedule(Course course, String day, String time, String location) {
        this.course = course;
        this.day = day;
        this.time = time;
        this.location = location;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CourseSchedule {" +
                "course = " + course +
                ", day = '" + day + '\'' +
                ", time = '" + time + '\'' +
                ", location = '" + location + '\'' +
                '}';
    }
}
