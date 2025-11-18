package app;

public class Student {
    private int id;
    private String rollNo;
    private String name;
    private String fatherName;
    private String dob;
    private String gender;
    private String course;
    private String address;
    private String phone;
    private String email;

    public Student(int id, String rollNo, String name, String fatherName, String dob, String gender, String course, String address, String phone, String email) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.gender = gender;
        this.course = course;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getFatherName() { return fatherName; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getCourse() { return course; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}