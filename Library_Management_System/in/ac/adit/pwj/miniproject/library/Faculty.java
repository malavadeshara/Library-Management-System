package in.ac.adit.pwj.miniproject.library;

public class Faculty extends User {
    private String facultyID;
    private String department;
    private String userType = "F";

    public Faculty(String name, String facultyID, String department) {
        super(name);
        this.facultyID = facultyID;
        this.department = department;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public String getDepartment() {
        return department;
    }

    public String getUserType() {
        return userType;
    }
}