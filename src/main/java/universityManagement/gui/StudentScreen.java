package universityManagement.gui;

import universityManagement.baseClasses.*;
import universityManagement.services.Services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentScreen extends JFrame {

    private Services services = null;
    private String username = null;
    private String studentName = null;
    private Integer userID = 0;
    private Integer studentID = 0;
    private Integer specializationID = 0;

    private JFrame frame = new JFrame();
    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JLabel labelStudentName = new JLabel("");


    private JButton buttonViewCourses = new JButton("<html><div style=\"text-align:center\">View<br>Courses</div></html>");
    private JButton buttonViewSchedule = new JButton("<html><div style=\"text-align:center\">View<br>Schedule</div></html>");
    private JButton buttonViewTasks = new JButton("<html><div style=\"text-align:center\">View<br>Given<br>Tasks</div></html>");
    private JButton buttonViewExams = new JButton("<html><div style=\"text-align:center\">View<br>Scheduled<br>Exams</div></html>");
    private JButton buttonViewGrades = new JButton("<html><div style=\"text-align:center\">View<br>Grades</div></html>");


    // LOG OUT FUNCTION

    private JButton buttonLogOut = new JButton("Log out");
    private JFrame frameLogOut = new JFrame();
    private JLabel labelLogOut = new JLabel("Do you wish to log out?", SwingConstants.CENTER);
    private JButton buttonLogOutYes = new JButton("Yes");
    private JButton buttonLogOutNo = new JButton("No");

    // LOG OUT FUNCTION



    // CHANGE PASSWORD FUNCTION

    private JButton buttonChangePassword = new JButton("Change password");
    private JPasswordField textFieldCurrentPassword = new JPasswordField();
    private JPasswordField textFieldNewPassword1 = new JPasswordField();
    private JPasswordField textFieldNewPassword2 = new JPasswordField();
    private JButton buttonChangePassword2 = new JButton("Change password");
    private JLabel labelWrongCurrentPassword = new JLabel("<html><div style=\"text-align:center\">You have not introduced the<br>correct current password.</div></html>", SwingConstants.CENTER);
    private JLabel labelWrongNewPassword = new JLabel("<html><div style=\"text-align:center\">You have not introduced the same new password.<br>Please try again.</div></html>", SwingConstants.CENTER);
    private JLabel labelPasswordChangedSuccessfully = new JLabel("Password changed successfully.", SwingConstants.CENTER);
    private JLabel labelPasswordChangeError = new JLabel("<html><div style=\"text-align:center\">Something went wrong. Please try again.</div></html>", SwingConstants.CENTER);
    private JLabel labelFillNewPasswordFields = new JLabel("<html><div style=\"text-align:center\">Please introduce the new password<br>and fill all fields.</div></html>", SwingConstants.CENTER);
    private JLabel labelSamePassword = new JLabel("<html><div style=\"text-align:center\">The new password must be different from the old one. Please introduce a different password.</div></html>", SwingConstants.CENTER);

    // CHANGE PASSWORD FUNCTION


    // FUNCTIONS

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    private List<Course> courseList;
    private List<CourseSchedule> courseScheduleList;
    private List<Task> taskList;
    private List<Exam> examList;
    private List<Grade> gradeList;

    private Course selectedCourse = null;


    // FUNCTIONS


    // VIEW TASKS FUNCTION

    private void initializeTableTasks (int courseID) {

        taskList = services.getCourseTasks(courseID);

        table = new JTable(taskList.size(), 2);

        model = (DefaultTableModel) table.getModel();

        table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
        table.getColumnModel().getColumn(1).setHeaderValue("Description");

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(750);

        scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane);
        scrollPane.setBounds(100, 200, 800, 500);

        table.setRowHeight(100);

        for (int i = 0; i < taskList.size(); i++) {

            model.setValueAt((i + 1), i, 0);
            model.setValueAt(taskList.get(i).getDescription(), i, 1);

        }

        rightPanel.revalidate();

        table.setDefaultEditor(Object.class, null);

    }

    // VIEW TASKS FUNCTION



    // VIEW EXAMS FUNCTION

    private void initializeTableExams () {

        examList = services.getSpecializationExams(courseList);

        table = new JTable(examList.size(), 6);

        model = (DefaultTableModel) table.getModel();

        table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
        table.getColumnModel().getColumn(1).setHeaderValue("Course Name");
        table.getColumnModel().getColumn(2).setHeaderValue("Professor");
        table.getColumnModel().getColumn(3).setHeaderValue("Exam Type");
        table.getColumnModel().getColumn(4).setHeaderValue("Date");
        table.getColumnModel().getColumn(5).setHeaderValue("Time");

        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(75);

        table.setRowHeight(50);


        scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane);
        scrollPane.setBounds(100, 100, 800, 500);


        for (int i = 0; i < examList.size(); i++) {

            String[] dateTime = examList.get(i).getDateTime().split(" ");
            String professorName = examList.get(i).getCourse().getProfessor().getLastName() + " " +
                    examList.get(i).getCourse().getProfessor().getFirstName();


            model.setValueAt((i + 1), i, 0);
            model.setValueAt(examList.get(i).getCourse().getName(), i, 1);
            model.setValueAt(professorName, i, 2);
            model.setValueAt(examList.get(i).getExamType(), i, 3);
            model.setValueAt(dateTime[0], i, 4);
            model.setValueAt(dateTime[1], i, 5);

        }

        table.setDefaultEditor(Object.class, null);

    }

    // VIEW EXAMS FUNCTION



    private void actions () {

        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameLogOut.setTitle("");
                frameLogOut.setSize(300, 200);
                frameLogOut.setLayout(null);
                frameLogOut.setVisible(true);
                frameLogOut.setResizable(false);
                frameLogOut.setLocationRelativeTo(null);
                frameLogOut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameLogOut.add(labelLogOut);
                frameLogOut.add(buttonLogOutYes);
                frameLogOut.add(buttonLogOutNo);

                labelLogOut.setBounds(0, 25, 300, 50);
                buttonLogOutYes.setBounds(25, 100, 100, 50);
                buttonLogOutNo.setBounds(175, 100, 100, 50);


            }
        });

        buttonLogOutYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameLogOut.dispose();
                frame.dispose();

                LoginScreen loginScreen = new LoginScreen(services);

            }
        });

        buttonLogOutNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameLogOut.dispose();

            }
        });


        buttonChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                JLabel labelChangePassword = new JLabel("CHANGE YOUR PASSWORD", SwingConstants.CENTER);
                rightPanel.add(labelChangePassword);
                labelChangePassword.setBounds(0, 0, 1000, 50);
                labelChangePassword.setBackground(Color.LIGHT_GRAY);
                labelChangePassword.setOpaque(true);


                JLabel labelCurrentPassword = new JLabel("Current password:");
                JLabel labelNewPassword1 = new JLabel("New password:");
                JLabel labelNewPassword2 = new JLabel("Repeat new password:");

                rightPanel.add(labelCurrentPassword);
                rightPanel.add(labelNewPassword1);
                rightPanel.add(labelNewPassword2);

                labelCurrentPassword.setBounds(300, 150, 150, 50);
                labelNewPassword1.setBounds(300, 200+10, 150, 50);
                labelNewPassword2.setBounds(300, 250+20, 150, 50);


                rightPanel.add(textFieldCurrentPassword);
                rightPanel.add(textFieldNewPassword1);
                rightPanel.add(textFieldNewPassword2);

                textFieldCurrentPassword.setBounds(450, 150, 150, 50);
                textFieldNewPassword1.setBounds(450, 200+10, 150, 50);
                textFieldNewPassword2.setBounds(450, 250+20, 150, 50);

                textFieldCurrentPassword.setText("");
                textFieldNewPassword1.setText("");
                textFieldNewPassword2.setText("");


                rightPanel.add(buttonChangePassword2);
                buttonChangePassword2.setBounds(300, 350, 300, 100);


            }
        });

        buttonChangePassword2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelWrongCurrentPassword);
                rightPanel.remove(labelWrongNewPassword);
                rightPanel.remove(labelPasswordChangedSuccessfully);
                rightPanel.remove(labelPasswordChangeError);
                rightPanel.remove(labelFillNewPasswordFields);
                rightPanel.remove(labelSamePassword);
                rightPanel.revalidate();
                rightPanel.repaint();

                String currentPass = String.valueOf(textFieldCurrentPassword.getPassword());
                String newPass1 = String.valueOf(textFieldNewPassword1.getPassword());
                String newPass2 = String.valueOf(textFieldNewPassword2.getPassword());

                boolean currentPassword = services.checkPassword(userID, currentPass);
                boolean newPasswordFields = (!newPass1.isBlank()) && (!newPass2.isBlank());
                boolean newPasswordMatch = newPass1.equals(newPass2);
                boolean samePassword = !(currentPass.equals(newPass1));


                if (currentPassword) {

                    if (newPasswordFields) {

                        if (newPasswordMatch) {

                            if (samePassword) {

                                if (services.changePassword(userID, newPass1)) {

                                    rightPanel.add(labelPasswordChangedSuccessfully);
                                    labelPasswordChangedSuccessfully.setBounds(300, 450, 300, 50);
                                    labelPasswordChangedSuccessfully.setForeground(Color.GREEN);

                                } else {

                                    rightPanel.add(labelPasswordChangeError);
                                    labelPasswordChangeError.setBounds(300, 450, 300, 50);
                                    labelPasswordChangeError.setForeground(Color.RED);

                                }

                            } else {

                                rightPanel.add(labelSamePassword);
                                labelSamePassword.setBounds(300, 450, 300, 50);
                                labelSamePassword.setForeground(Color.RED);

                            }


                        } else {

                            rightPanel.add(labelWrongNewPassword);
                            labelWrongNewPassword.setBounds(300, 450, 300, 50);
                            labelWrongNewPassword.setForeground(Color.RED);

                        }

                    } else {

                        rightPanel.add(labelFillNewPasswordFields);
                        labelFillNewPasswordFields.setBounds(300, 450, 300, 50);
                        labelFillNewPasswordFields.setForeground(Color.RED);

                    }

                } else {

                    rightPanel.add(labelWrongCurrentPassword);
                    labelWrongCurrentPassword.setBounds(300, 450, 300, 50);
                    labelWrongCurrentPassword.setForeground(Color.RED);

                }


            }
        });


        buttonViewCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getSpecializationCourses(specializationID);


                if (courseList.size() > 0) {

                    JLabel labelViewCourses = new JLabel("VIEW COURSES", SwingConstants.CENTER);
                    rightPanel.add(labelViewCourses);
                    labelViewCourses.setBounds(0, 0, 1000, 50);
                    labelViewCourses.setBackground(Color.LIGHT_GRAY);
                    labelViewCourses.setOpaque(true);


                    table = new JTable(courseList.size(), 5);

                    model = (DefaultTableModel) table.getModel();

                    table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                    table.getColumnModel().getColumn(1).setHeaderValue("Study Level");
                    table.getColumnModel().getColumn(2).setHeaderValue("Specialization");
                    table.getColumnModel().getColumn(3).setHeaderValue("Course Name");
                    table.getColumnModel().getColumn(4).setHeaderValue("Professor");

                    table.getColumnModel().getColumn(0).setMaxWidth(25);
                    table.getColumnModel().getColumn(1).setMaxWidth(75);

                    table.setRowHeight(50);


                    scrollPane = new JScrollPane(table);
                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(100, 100, 800, 500);

                    for (int i = 0; i < courseList.size(); i++) {

                        String professorName = courseList.get(i).getProfessor().getLastName() + " " + courseList.get(i).getProfessor().getFirstName();

                        model.setValueAt((i + 1), i, 0);
                        model.setValueAt(courseList.get(i).getSpecialization().getStudyLevel(), i, 1);
                        model.setValueAt(courseList.get(i).getSpecialization().getName(), i, 2);
                        model.setValueAt(courseList.get(i).getName(), i, 3);
                        model.setValueAt(professorName, i, 4);



                    }

                    table.setDefaultEditor(Object.class, null);


                } else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }
            }
        });


        buttonViewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getSpecializationCourses(specializationID);

                if (courseList.size() > 0) {

                    JLabel labelViewSchedule = new JLabel("VIEW SCHEDULE", SwingConstants.CENTER);

                    rightPanel.add(labelViewSchedule);
                    labelViewSchedule.setBounds(0, 0, 1000, 50);
                    labelViewSchedule.setBackground(Color.LIGHT_GRAY);
                    labelViewSchedule.setOpaque(true);


                    courseScheduleList = services.getProfessorCourseSchedule(courseList);

                    table = new JTable(courseScheduleList.size(), 6);

                    model = (DefaultTableModel) table.getModel();

                    table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                    table.getColumnModel().getColumn(1).setHeaderValue("Course Name");
                    table.getColumnModel().getColumn(2).setHeaderValue("Professor");
                    table.getColumnModel().getColumn(3).setHeaderValue("Day");
                    table.getColumnModel().getColumn(4).setHeaderValue("Time");
                    table.getColumnModel().getColumn(5).setHeaderValue("Location");

                    table.getColumnModel().getColumn(0).setPreferredWidth(25);
                    table.getColumnModel().getColumn(1).setPreferredWidth(200);
                    table.getColumnModel().getColumn(2).setPreferredWidth(200);
                    table.getColumnModel().getColumn(3).setPreferredWidth(50);
                    table.getColumnModel().getColumn(4).setPreferredWidth(50);
                    table.getColumnModel().getColumn(5).setPreferredWidth(100);



                    table.setRowHeight(50);


                    scrollPane = new JScrollPane(table);
                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(100, 100, 800, 500);

                    for (int i = 0; i < courseScheduleList.size(); i++) {

                        String professorName = courseScheduleList.get(i).getCourse().getProfessor().getLastName() + " " +
                                courseScheduleList.get(i).getCourse().getProfessor().getFirstName();

                        model.setValueAt((i+1), i, 0);
                        model.setValueAt(courseScheduleList.get(i).getCourse().getName(), i, 1);
                        model.setValueAt(professorName, i, 2);
                        model.setValueAt(courseScheduleList.get(i).getDay(), i, 3);
                        model.setValueAt(courseScheduleList.get(i).getTime(), i, 4);
                        model.setValueAt(courseScheduleList.get(i).getLocation(), i, 5);

                    }

                    table.setDefaultEditor(Object.class, null);


                } else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }
            }
        });


        buttonViewTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getSpecializationCourses(specializationID);

                if (courseList.size() > 0) {

                    JLabel labelViewTasks = new JLabel("VIEW TASKS", SwingConstants.CENTER);

                    rightPanel.add(labelViewTasks);
                    labelViewTasks.setBounds(0, 0, 1000, 50);
                    labelViewTasks.setBackground(Color.LIGHT_GRAY);
                    labelViewTasks.setOpaque(true);

                    String[] courseArray = new String[courseList.size()];

                    for (int i = 0; i < courseArray.length; i++) courseArray[i] = courseList.get(i).getName();

                    JLabel labelSelectCourse = new JLabel("Select a course:", SwingConstants.CENTER);
                    rightPanel.add(labelSelectCourse);
                    labelSelectCourse.setBounds(400, 75, 200, 25);

                    JComboBox comboBoxCourses = new JComboBox(courseArray);
                    rightPanel.add(comboBoxCourses);
                    comboBoxCourses.setBounds(400, 100, 200, 50);


                    String selection = (String) comboBoxCourses.getSelectedItem();
                    selectedCourse = null;

                    for (int i = 0; i < courseList.size(); i++) {

                        if (selection.equals(courseList.get(i).getName())) {

                            selectedCourse = courseList.get(i);
                            break;

                        }
                    }

                    initializeTableTasks(selectedCourse.getId());


                    comboBoxCourses.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String selection = (String) comboBoxCourses.getSelectedItem();
                            selectedCourse = null;

                            for (int i = 0; i < courseList.size(); i++) {

                                if (selection.equals(courseList.get(i).getName())) {

                                    selectedCourse = courseList.get(i);
                                    break;

                                }
                            }

                            rightPanel.remove(scrollPane);
                            rightPanel.revalidate();
                            rightPanel.repaint();

                            initializeTableTasks(selectedCourse.getId());

                        }
                    });


                }  else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }

            }
        });


        buttonViewExams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getSpecializationCourses(specializationID);

                if (courseList.size() > 0) {

                    JLabel labelViewExams = new JLabel("VIEW EXAMS", SwingConstants.CENTER);

                    rightPanel.add(labelViewExams);
                    labelViewExams.setBounds(0, 0, 1000, 50);
                    labelViewExams.setBackground(Color.LIGHT_GRAY);
                    labelViewExams.setOpaque(true);

                    initializeTableExams();


                }   else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }

            }
        });


        buttonViewGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getSpecializationCourses(specializationID);

                if (courseList.size() > 0) {

                    gradeList = services.getStudentGrades(courseList, studentID);

                    JLabel labelViewGrades = new JLabel("VIEW GRADES", SwingConstants.CENTER);

                    rightPanel.add(labelViewGrades);
                    labelViewGrades.setBounds(0, 0, 1000, 50);
                    labelViewGrades.setBackground(Color.LIGHT_GRAY);
                    labelViewGrades.setOpaque(true);


                    table = new JTable(gradeList.size(), 6);

                    model = (DefaultTableModel) table.getModel();

                    table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                    table.getColumnModel().getColumn(1).setHeaderValue("Course");
                    table.getColumnModel().getColumn(2).setHeaderValue("Professor");
                    table.getColumnModel().getColumn(3).setHeaderValue("Grade");
                    table.getColumnModel().getColumn(4).setHeaderValue("Date");
                    table.getColumnModel().getColumn(5).setHeaderValue("Time");


                    table.getColumnModel().getColumn(0).setPreferredWidth(25);
                    table.getColumnModel().getColumn(1).setPreferredWidth(200);
                    table.getColumnModel().getColumn(2).setPreferredWidth(100);
                    table.getColumnModel().getColumn(3).setPreferredWidth(50);
                    table.getColumnModel().getColumn(4).setPreferredWidth(50);
                    table.getColumnModel().getColumn(5).setPreferredWidth(50);


                    scrollPane = new JScrollPane(table);
                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(100, 200, 800, 500);

                    table.setRowHeight(50);


                    for (int i = 0; i < gradeList.size(); i++) {

                        String[] dateTime = null;

                        if (gradeList.get(i).getGrade() == 0) dateTime = new String[]{"-"};
                        else dateTime = gradeList.get(i).getDateTime().split(" ");

                        String professorName = gradeList.get(i).getCourse().getProfessor().getLastName() + " " +
                                gradeList.get(i).getCourse().getProfessor().getFirstName();


                        model.setValueAt((i+1), i, 0);
                        model.setValueAt(gradeList.get(i).getCourse().getName(), i, 1);
                        model.setValueAt(professorName, i, 2);

                        if (gradeList.get(i).getGrade() == 0) model.setValueAt("-", i, 3);
                        else model.setValueAt(gradeList.get(i).getGrade(), i, 3);


                        if (dateTime[0].equals("-")) {
                            model.setValueAt("-", i, 4);
                            model.setValueAt("-", i, 5);
                        } else {
                            model.setValueAt(dateTime[0], i, 4);
                            model.setValueAt(dateTime[1], i, 5);
                        }
                    }

                    table.setDefaultEditor(Object.class, null);


                } else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }

            }
        });


    }

    public StudentScreen (String username, Services services) {

        this.services = services;
        this.username = username;
        this.studentName = services.getName(username);
        this.userID = services.getUserID(username);
        this.studentID = services.getStudentID(userID);
        this.specializationID = services.getStudentSpecializationID(studentID);

        frame.setTitle("Student Screen");
        frame.setSize(1100,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(255, 238, 214, 255));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // TOP PANEL

        frame.add(topPanel);
        topPanel.setBounds(0, 0, 1100, 50);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(224, 206, 183, 255));

        topPanel.add(labelStudentName);
        labelStudentName.setBounds(10, 0, 200, 50);
        labelStudentName.setText("Student: " + studentName);

        topPanel.add(buttonLogOut);
        topPanel.add(buttonChangePassword);

        buttonChangePassword.setBounds(800, 5, 180, 40);
        buttonLogOut.setBounds(990, 5, 90, 40);

        // TOP PANEL



        // LEFT PANEL

        frame.add(leftPanel);
        leftPanel.setBounds(0, 50, 100, 750);
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(224, 206, 183, 255));

        leftPanel.add(buttonViewCourses);
        leftPanel.add(buttonViewSchedule);
        leftPanel.add(buttonViewTasks);
        leftPanel.add(buttonViewExams);
        leftPanel.add(buttonViewGrades);

        buttonViewCourses.setBounds(5, 0, 90, 100);
        buttonViewSchedule.setBounds(5, 150, 90, 100);
        buttonViewTasks.setBounds(5, 300, 90, 100);
        buttonViewExams.setBounds(5, 450, 90, 100);
        buttonViewGrades.setBounds(5, 600, 90, 100);

        // LEFT PANEL



        // RIGHT PANEL

        frame.add(rightPanel);
        rightPanel.setBounds(100, 50, 1000, 750);
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(255, 238, 214, 255));

        // RIGHT PANEL


        actions();

    }

    public static void main(String[] args) {

        Services services = new Services();

        StudentScreen studentScreen = new StudentScreen("student1", services);

//        for (int i = 1; i < 13; i++) new StudentScreen("student" + i, services);


    }
}
