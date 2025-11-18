package app;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final String url;
    private String lastErrorMessage = null;

    public Database(String dbFile) {
        // print absolute path so you know exactly which file is being used
        File f = new File(dbFile);
        System.out.println("Using DB file: " + f.getAbsolutePath());
        this.url = "jdbc:sqlite:" + dbFile;
        createTableIfNotExists();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " roll TEXT,\n"
                + " name TEXT NOT NULL,\n"
                + " father_name TEXT,\n"
                + " dob TEXT,\n"
                + " gender TEXT,\n"
                + " course TEXT,\n"
                + " address TEXT,\n"
                + " phone TEXT,\n"
                + " email TEXT\n"
                + ");";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Inserts a student and returns true on success, false on failure.
     * On failure, getLastErrorMessage() will contain the SQL error message (if any).
     */
    public boolean insertStudent(Student s) {
        String sql = "INSERT INTO students(roll, name, father_name, dob, gender, course, address, phone, email) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.getRollNo());
            pstmt.setString(2, s.getName());
            pstmt.setString(3, s.getFatherName());
            pstmt.setString(4, s.getDob());
            pstmt.setString(5, s.getGender());
            pstmt.setString(6, s.getCourse());
            pstmt.setString(7, s.getAddress());
            pstmt.setString(8, s.getPhone());
            pstmt.setString(9, s.getEmail());
            pstmt.executeUpdate();
            lastErrorMessage = null;
            return true;
        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            e.printStackTrace(); // check console for full stacktrace
            return false;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, roll, name, father_name, dob, gender, course, address, phone, email FROM students ORDER BY id DESC";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("roll"),
                        rs.getString("name"),
                        rs.getString("father_name"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("course"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            e.printStackTrace();
        }
        return list;
    }
}