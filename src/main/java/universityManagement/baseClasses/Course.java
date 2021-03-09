package universityManagement.baseClasses;

public class Course {

    private int id;
    private String name;
    private Specialization specialization;
    private Professor professor;

    public Course(int id, String name, Specialization specialization, Professor professor) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization=" + specialization +
                ", professor=" + professor +
                '}';
    }
}
