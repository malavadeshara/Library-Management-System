package in.ac.adit.pwj.miniproject.library;

public class Student extends User {
    private String enrollmentNo;
    private String department;
    private String userType = "S";

    public Student(String name, String enrollmentNo, String department) {
        super(name);
        this.enrollmentNo = enrollmentNo;
        this.department = department;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public String getDepartment() {
        return department;
    }

    public String getUserType() {
        return userType;
    }
}