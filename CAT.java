package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CAT {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;
    private Database db;

    public CAT() {
        db = new Database("students.db");
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        frame = new JFrame("Student Entry Form - CAT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 520);
        frame.setLocationRelativeTo(null);

        // Left: form
        StudentFormPanel formPanel = new StudentFormPanel(this::onStudentSubmitted);

        // Right: table
        String[] columns = {"ID", "Roll No", "Name", "Father's Name", "DOB", "Gender", "Course", "Phone", "Email"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tableScroll);
        split.setDividerLocation(420);

        frame.getContentPane().add(split, BorderLayout.CENTER);
        refreshTable();
        frame.setVisible(true);
    }

    /**
     * Called when the form submits a student. Will show a success dialog only if DB insert succeeds,
     * otherwise show an error dialog with the DB message.
     */
    private void onStudentSubmitted(Student s) {
        boolean ok = db.insertStudent(s);
        if (ok) {
            refreshTable();
            JOptionPane.showMessageDialog(frame, "Saved successfully.");
        } else {
            String msg = db.getLastErrorMessage();
            if (msg == null) msg = "Unknown error. See console for details.";
            JOptionPane.showMessageDialog(frame, "Save failed: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        List<Student> list = db.getAllStudents();
        tableModel.setRowCount(0);
        for (Student s : list) {
            Object[] row = {
                    s.getId(),
                    s.getRollNo(),
                    s.getName(),
                    s.getFatherName(),
                    s.getDob(),
                    s.getGender(),
                    s.getCourse(),
                    s.getPhone(),
                    s.getEmail()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        new CAT();
    }
}