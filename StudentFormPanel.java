package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class StudentFormPanel extends JPanel {
    private JTextField tfRoll = new JTextField(12);
    private JTextField tfName = new JTextField(20);
    private JTextField tfFather = new JTextField(20);
    private JTextField tfDob = new JTextField(10); // format: YYYY-MM-DD or DD-MM-YYYY
    private JRadioButton rbMale = new JRadioButton("Male");
    private JRadioButton rbFemale = new JRadioButton("Female");
    private JComboBox<String> cbCourse = new JComboBox<>(new String[]{"Select", "B.Sc", "B.Com", "B.A", "B.E", "M.Sc", "MCA"});
    private JTextArea taAddress = new JTextArea(4, 20);
    private JTextField tfPhone = new JTextField(12);
    private JTextField tfEmail = new JTextField(20);

    private Consumer<Student> submitHandler;

    public StudentFormPanel(Consumer<Student> submitHandler) {
        this.submitHandler = submitHandler;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buildForm(), BorderLayout.NORTH);
    }

    private JPanel buildForm() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;

        int row = 0;

        c.gridx = 0; c.gridy = row; p.add(new JLabel("Roll No:"), c);
        c.gridx = 1; p.add(tfRoll, c);

        row++;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("Name:"), c);
        c.gridx = 1; p.add(tfName, c);

        row++;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("Father's Name:"), c);
        c.gridx = 1; p.add(tfFather, c);

        row++;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("DOB:"), c);
        c.gridx = 1; p.add(tfDob, c);

        row++;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("Gender:"), c);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale); bg.add(rbFemale);
        genderPanel.add(rbMale); genderPanel.add(Box.createHorizontalStrut(8)); genderPanel.add(rbFemale);
        c.gridx = 1; p.add(genderPanel, c);

        row++;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("Course:"), c);
        c.gridx = 1; p.add(cbCourse, c);

        row++;
        c.gridx = 0; c.gridy = row; c.anchor = GridBagConstraints.NORTHWEST; p.add(new JLabel("Address:"), c);
        c.gridx = 1; JScrollPane addrScroll = new JScrollPane(taAddress); p.add(addrScroll, c);

        row++;
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("Phone:"), c);
        c.gridx = 1; p.add(tfPhone, c);

        row++;
        c.gridx = 0; c.gridy = row; p.add(new JLabel("Email:"), c);
        c.gridx = 1; p.add(tfEmail, c);

        row++;
        c.gridx = 0; c.gridy = row; c.gridwidth = 2;
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(this::onSave);
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearForm());
        btnRow.add(btnSave);
        btnRow.add(btnClear);
        p.add(btnRow, c);

        return p;
    }

    private void onSave(ActionEvent e) {
        String roll = tfRoll.getText().trim();
        String name = tfName.getText().trim();
        String father = tfFather.getText().trim();
        String dob = tfDob.getText().trim();
        String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";
        String course = (String) cbCourse.getSelectedItem();
        String address = taAddress.getText().trim();
        String phone = tfPhone.getText().trim();
        String email = tfEmail.getText().trim();

        if (roll.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll No and Name are required.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("Select".equals(course)) {
            course = "";
        }

        Student s = new Student(0, roll, name, father, dob, gender, course, address, phone, email);
        submitHandler.accept(s);
        clearForm();
    }

    private void clearForm() {
        tfRoll.setText("");
        tfName.setText("");
        tfFather.setText("");
        tfDob.setText("");
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
        cbCourse.setSelectedIndex(0);
        taAddress.setText("");
        tfPhone.setText("");
        tfEmail.setText("");
    }
}