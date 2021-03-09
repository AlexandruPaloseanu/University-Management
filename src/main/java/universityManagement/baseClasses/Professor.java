package universityManagement.baseClasses;

public class Professor {

    private int id;
    private User user;
    private String lastName;
    private String firstName;
    private String birthDate;
    private Faculty faculty;
    private String expertise;

    public Professor(int id, User user, String lastName, String firstName, String birthDate, Faculty faculty, String expertise) {
        this.id = id;
        this.user = user;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.expertise = expertise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "Professors{" +
                "id=" + id +
                ", user=" + user +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", faculty=" + faculty +
                ", expertise='" + expertise + '\'' +
                '}';
    }
}
