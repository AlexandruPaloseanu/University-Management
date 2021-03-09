package universityManagement.baseClasses;

public class Specialization {

    private int id;
    private String name;
    private String studyLevel;
    private Faculty faculty;

    public Specialization(int id, String name, String studyLevel, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.studyLevel = studyLevel;
        this.faculty = faculty;
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

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Specialization {" +
                "id = " + id +
                ", name ='" + name + '\'' +
                ", studyLevel = '" + studyLevel + '\'' +
                ", faculty = " + faculty +
                '}';
    }
}
