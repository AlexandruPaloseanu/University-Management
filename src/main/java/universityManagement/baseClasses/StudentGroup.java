package universityManagement.baseClasses;

public class StudentGroup {

    private int id;
    private int groupNr;
    private int studyYear;
    private Specialization specialization;

    public StudentGroup(int id, int groupNr, int studyYear, Specialization specialization) {
        this.id = id;
        this.groupNr = groupNr;
        this.studyYear = studyYear;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupNr() {
        return groupNr;
    }

    public void setGroupNr(int groupNr) {
        this.groupNr = groupNr;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "StudentGroup {" +
                "id =" + id +
                ", groupNr =" + groupNr +
                ", studyYear =" + studyYear +
                ", specialization =" + specialization +
                '}';
    }
}
