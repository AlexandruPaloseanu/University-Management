package universityManagement.baseClasses;

public class Task {

    private int taskID;
    private Professor professor;
    private Course course;
    private String description;

    public Task(int taskID, Professor professor, Course course, String description) {
        this.taskID = taskID;
        this.professor = professor;
        this.course = course;
        this.description = description;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", professor=" + professor +
                ", course=" + course +
                ", description='" + description + '\'' +
                '}';
    }
}
