package universityManagement.gui;

import universityManagement.baseClasses.*;
import universityManagement.services.Services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ProfessorScreen extends JFrame {

    private Services services = null;
    private String username = null;
    private String professorName = null;
    private int userID = 0;
    private int professorID = 0;

    private JFrame frame = new JFrame();
    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JLabel labelProfessorName = new JLabel("");


    private JButton buttonViewCourses = new JButton("<html><div style=\"text-align:center\">View<br>Courses</div></html>");
    private JButton buttonViewSchedule = new JButton("<html><div style=\"text-align:center\">View<br>Schedule</div></html>");
    private JButton buttonAddTask = new JButton("<html><div style=\"text-align:center\">Add<br>Tasks</div></html>");
    private JButton buttonViewTasks = new JButton("<html><div style=\"text-align:center\">View<br>Given<br>Tasks</div></html>");
    private JButton buttonScheduleExams = new JButton("<html><div style=\"text-align:center\">Schedule<br>Exams</div></html>");
    private JButton buttonViewExams = new JButton("<html><div style=\"text-align:center\">View<br>Scheduled<br>Exams</div></html>");
    private JButton buttonGiveGrades = new JButton("<html><div style=\"text-align:center\">Give<br>Grades</div></html>");
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
    private List<Student> studentList;

    Course selectedCourse = null;
    Student selectedStudent = null;

    // ADD TASK FUNCTION

    private JTextArea textAreaTaskDescription = new JTextArea();
    private JButton buttonAddTask2 = new JButton("Add Task");
    private JLabel labelTaskAddedSuccess = new JLabel("Task added successfully!", SwingConstants.CENTER);
    private JLabel labelTaskAddedFailure = new JLabel("Something went wrong. Please try again.", SwingConstants.CENTER);
    private JLabel labelFillTaskDescription = new JLabel("Please add a task description.", SwingConstants.CENTER);

    // ADD TASK FUNCTION



    // VIEW TASKS FUNCTION

    private void initializeTableTasks (int courseID) {

        taskList = services.getProfessorCourseTasks(professorID, courseID);

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



    // DELETE TASKS FUNCTIONS

    private JButton buttonDeleteTasks = new JButton("Delete Task");
    private JFrame frameDeleteTask = new JFrame();
    private JLabel labelDeleteTask = new JLabel("<html><div style=\"text-align:center\">Are you sure you want<br>to delete the selected task?</div></html>", SwingConstants.CENTER);
    private JButton buttonDeleteTaskYes = new JButton("Yes");
    private JButton buttonDeleteTaskNo = new JButton("No");
    private JLabel labelNoTasks = new JLabel("There are no tasks.", SwingConstants.CENTER);
    private JLabel labelTaskDeletionSuccess = new JLabel("Task deleted successfully.", SwingConstants.CENTER);
    private JLabel labelTaskDeletionFailure = new JLabel("Something went wrong. Please try again.", SwingConstants.CENTER);


    // DELETE TASKS FUNCTIONS



    // SCHEDULE EXAMS FUNCTION

    private JComboBox comboBoxScheduleExamsCourses = new JComboBox();
    private JComboBox comboBoxExamTypes = new JComboBox(new String[]{"WRITTEN", "ORAL", "PROJECT"});
    private JTextField textFieldExamDate = new JTextField();
    private JTextField textFieldExamTime = new JTextField();
    private JButton buttonScheduleExam2 = new JButton("Schedule Exam");

    private JLabel labelScheduleExamSuccess = new JLabel("Exam scheduled successfully", SwingConstants.CENTER);
    private JLabel labelScheduleExamError = new JLabel("<html><div style=\"text-align:center\">Something went wrong.<br>Please try again.</div></html>", SwingConstants.CENTER);
    private JLabel labelScheduleExamTaken = new JLabel("<html><div style=\"text-align:center\">Time slot already taken.<br>Try a different one.</div></html>", SwingConstants.CENTER);
    private JLabel labelFillAllFields = new JLabel("Please fill all fields", SwingConstants.CENTER);
    private JLabel labelWrongDateTimeFormat = new JLabel("<html><div style=\"text-align:center\">Invalid date/time format.<br>Please try again.</div></html>", SwingConstants.CENTER);

    // SCHEDULE EXAMS FUNCTION



    // VIEW EXAMS FUNCTION

    private void initializeTableExams () {

        examList = services.getProfessorExams(courseList);

        table = new JTable(examList.size(), 7);

        model = (DefaultTableModel) table.getModel();

        table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
        table.getColumnModel().getColumn(1).setHeaderValue("Study Level");
        table.getColumnModel().getColumn(2).setHeaderValue("Specialization");
        table.getColumnModel().getColumn(3).setHeaderValue("Course Name");
        table.getColumnModel().getColumn(4).setHeaderValue("Exam Type");
        table.getColumnModel().getColumn(5).setHeaderValue("Date");
        table.getColumnModel().getColumn(6).setHeaderValue("Time");

        table.getColumnModel().getColumn(0).setMaxWidth(25);
        table.getColumnModel().getColumn(1).setMaxWidth(75);
        table.getColumnModel().getColumn(2).setMaxWidth(250);
        table.getColumnModel().getColumn(3).setMaxWidth(250);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        table.getColumnModel().getColumn(5).setMaxWidth(75);
        table.getColumnModel().getColumn(6).setMaxWidth(75);

        table.setRowHeight(50);


        scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane);
        scrollPane.setBounds(100, 100, 800, 500);


        for (int i = 0; i < examList.size(); i++) {

            String[] dateTime = examList.get(i).getDateTime().split(" ");


            model.setValueAt((i + 1), i, 0);
            model.setValueAt(examList.get(i).getCourse().getSpecialization().getStudyLevel(), i, 1);
            model.setValueAt(examList.get(i).getCourse().getSpecialization().getName(), i, 2);
            model.setValueAt(examList.get(i).getCourse().getName(), i, 3);
            model.setValueAt(examList.get(i).getExamType(), i, 4);
            model.setValueAt(dateTime[0], i, 5);
            model.setValueAt(dateTime[1], i, 6);

        }

        table.setDefaultEditor(Object.class, null);

    }

    // VIEW EXAMS FUNCTION



    // DELETE EXAMS FUNCTION

    private JButton buttonDeleteExam = new JButton("Delete Exam");
    private JFrame frameDeleteExam = new JFrame();
    private JLabel labelDeleteExam = new JLabel("<html><div style=\"text-align:center\">Are you sure you want<br>to delete the selected exam?</div></html>", SwingConstants.CENTER);
    private JButton buttonDeleteExamYes = new JButton("Yes");
    private JButton buttonDeleteExamNo = new JButton("No");
    private JLabel labelNoExams = new JLabel("There are no exams scheduled.", SwingConstants.CENTER);
    private JLabel labelExamDeletionSuccess = new JLabel("Exam deleted successfully.", SwingConstants.CENTER);
    private JLabel labelExamDeletionFailure = new JLabel("Something went wrong. Please try again.", SwingConstants.CENTER);

    // DELETE EXAMS FUNCTION



    // MODIFY GRADE FUNCTION

    private JComboBox comboBoxGradeCourses = new JComboBox();
    private JComboBox comboBoxStudents = new JComboBox();

    private JTextField textFieldStudentName = new JTextField();
    private JComboBox comboBoxExamDates = new JComboBox();
    private JTextField textFieldGrade = new JTextField();

    private JButton buttonModifyGrade = new JButton("Modify Grade");
    private JButton buttonPreviousStudent = new JButton("Previous Student");
    private JButton buttonNextStudent = new JButton("Next Student");

    private JLabel labelGradeModSucces = new JLabel("Modification successful!", SwingConstants.CENTER);
    private JLabel labelGradeModFailure = new JLabel("Modification failed! Please try again.", SwingConstants.CENTER);
    private JLabel labelScheduleAnExamm = new JLabel("No exams scheduled. Please schedule an exam first.", SwingConstants.CENTER);
    private JLabel labelGradeNotANumber = new JLabel("For the grade, insert an integer from 0 to 10.", SwingConstants.CENTER);

    private void initializeComboBoxGradeCourses () {

        String[] courseArray = new String[courseList.size()];

        for (int i = 0; i < courseArray.length; i++)
            courseArray[i] = courseList.get(i).getName();

        comboBoxGradeCourses = new JComboBox(courseArray);

    }

    private void getSelectedCourse () {

        String selectedCourseName = (String) comboBoxGradeCourses.getSelectedItem();
        selectedCourse = null;

        for (int i = 0; i < courseList.size(); i++) {

            if (selectedCourseName.equals(courseList.get(i).getName())) {

                selectedCourse = courseList.get(i);
                break;

            }
        }

    }

    private void initializeComboBoxStudents () {

        studentList = services.getCourseStudents(selectedCourse.getId());

        String[] studentArray = new String[studentList.size()];

        for (int i = 0; i < studentList.size(); i++)
            studentArray[i] = "" + (i+1) + ", " + studentList.get(i).getLastName() + " " + studentList.get(i).getFirstName();


        comboBoxStudents = new JComboBox(studentArray);

    }

    private void getSelectedStudent () {

        selectedStudent = null;

        String selectedStudentName = (String) comboBoxStudents.getSelectedItem();
        String[] selectedStudentArray = selectedStudentName.split(", ");
        int studentNR = (Integer.parseInt(selectedStudentArray[0])) - 1;

        selectedStudent = studentList.get(studentNR);

    }

    private void initializeComboBoxExamDates() {

        int courseExamDates1 = 0;

        for (int i = 0; i < examList.size(); i++) {

            if (selectedCourse.getId() == examList.get(i).getCourse().getId()) courseExamDates1++;

        }

        String[] courseExamDatesArray = new String[courseExamDates1];

        int courseExamDates2 = 0;

        for (int i = 0; i < examList.size(); i++) {

            if (selectedCourse.getId() == examList.get(i).getCourse().getId()) {

                courseExamDatesArray[courseExamDates2] = examList.get(i).getDateTime();
                courseExamDates2++;

            }
        }

        comboBoxExamDates = new JComboBox(courseExamDatesArray);


    }

    private void selectExamDate () {

        String examDate = services.getGradeExamDate(selectedStudent.getId(), selectedCourse.getId());

        int elements = comboBoxExamDates.getModel().getSize();

        if (!Objects.isNull(examDate)) {

            for (int i = 0; i < elements; i++) {

                String searchExamDate = (String) comboBoxExamDates.getModel().getElementAt(i);

                if (examDate.equals(searchExamDate)) {

                    comboBoxExamDates.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    // MODIFY GRADE FUNCTION



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

                courseList = services.getProfessorCourseList(professorID);


                if (courseList.size() > 0) {

                    JLabel labelViewCourses = new JLabel("VIEW COURSES", SwingConstants.CENTER);
                    rightPanel.add(labelViewCourses);
                    labelViewCourses.setBounds(0, 0, 1000, 50);
                    labelViewCourses.setBackground(Color.LIGHT_GRAY);
                    labelViewCourses.setOpaque(true);


                    table = new JTable(courseList.size(), 4);

                    model = (DefaultTableModel) table.getModel();

                    table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                    table.getColumnModel().getColumn(1).setHeaderValue("Study Level");
                    table.getColumnModel().getColumn(2).setHeaderValue("Specialization");
                    table.getColumnModel().getColumn(3).setHeaderValue("Course Name");

                    table.getColumnModel().getColumn(0).setMaxWidth(25);
                    table.getColumnModel().getColumn(1).setMaxWidth(75);

                    table.setRowHeight(50);


                    scrollPane = new JScrollPane(table);
                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(100, 100, 800, 500);

                    for (int i = 0; i < courseList.size(); i++) {

                        model.setValueAt((i + 1), i, 0);
                        model.setValueAt(courseList.get(i).getSpecialization().getStudyLevel(), i, 1);
                        model.setValueAt(courseList.get(i).getSpecialization().getName(), i, 2);
                        model.setValueAt(courseList.get(i).getName(), i, 3);


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

                courseList = services.getProfessorCourseList(professorID);

                if (courseList.size() > 0) {

                    JLabel labelViewSchedule = new JLabel("VIEW SCHEDULE", SwingConstants.CENTER);

                    rightPanel.add(labelViewSchedule);
                    labelViewSchedule.setBounds(0, 0, 1000, 50);
                    labelViewSchedule.setBackground(Color.LIGHT_GRAY);
                    labelViewSchedule.setOpaque(true);


                    courseScheduleList = services.getProfessorCourseSchedule(courseList);

                    table = new JTable(courseScheduleList.size(), 7);

                    model = (DefaultTableModel) table.getModel();

                    table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                    table.getColumnModel().getColumn(1).setHeaderValue("Study Level");
                    table.getColumnModel().getColumn(2).setHeaderValue("Specialization");
                    table.getColumnModel().getColumn(3).setHeaderValue("Course Name");
                    table.getColumnModel().getColumn(4).setHeaderValue("Day");
                    table.getColumnModel().getColumn(5).setHeaderValue("Time");
                    table.getColumnModel().getColumn(6).setHeaderValue("Location");

                    table.getColumnModel().getColumn(0).setMaxWidth(25);
                    table.getColumnModel().getColumn(1).setMaxWidth(75);
                    table.getColumnModel().getColumn(2).setMaxWidth(250);
                    table.getColumnModel().getColumn(3).setMaxWidth(250);
                    table.getColumnModel().getColumn(4).setMaxWidth(75);
                    table.getColumnModel().getColumn(5).setMaxWidth(75);
                    table.getColumnModel().getColumn(6).setMaxWidth(75);

                    table.setRowHeight(50);


                    scrollPane = new JScrollPane(table);
                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(100, 100, 800, 500);

                    for (int i = 0; i < courseScheduleList.size(); i++) {

                        model.setValueAt((i+1), i, 0);
                        model.setValueAt(courseScheduleList.get(i).getCourse().getSpecialization().getStudyLevel(), i, 1);
                        model.setValueAt(courseScheduleList.get(i).getCourse().getSpecialization().getName(), i, 2);
                        model.setValueAt(courseScheduleList.get(i).getCourse().getName(), i, 3);
                        model.setValueAt(courseScheduleList.get(i).getDay(), i, 4);
                        model.setValueAt(courseScheduleList.get(i).getTime(), i, 5);
                        model.setValueAt(courseScheduleList.get(i).getLocation(), i, 6);

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


        buttonAddTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getProfessorCourseList(professorID);

                if (courseList.size() > 0) {

                    JLabel labelAddTask = new JLabel("ADD TASK", SwingConstants.CENTER);

                    rightPanel.add(labelAddTask);
                    labelAddTask.setBounds(0, 0, 1000, 50);
                    labelAddTask.setBackground(Color.LIGHT_GRAY);
                    labelAddTask.setOpaque(true);

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

                    JLabel labelNewTask = new JLabel("New task:", SwingConstants.CENTER);
                    rightPanel.add(labelNewTask);
                    labelNewTask.setBounds(400, 200, 200, 50);

                    textAreaTaskDescription = new JTextArea();
                    scrollPane = new JScrollPane(textAreaTaskDescription);

                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(400, 250, 200, 200);

                    rightPanel.add(buttonAddTask2);
                    buttonAddTask2.setBounds(400, 460, 200, 100);

                    comboBoxCourses.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            rightPanel.remove(labelTaskAddedSuccess);
                            rightPanel.remove(labelTaskAddedFailure);
                            rightPanel.remove(labelFillTaskDescription);
                            rightPanel.revalidate();
                            rightPanel.repaint();

                            String selection = (String) comboBoxCourses.getSelectedItem();
                            selectedCourse = null;

                            for (int i = 0; i < courseList.size(); i++) {

                                if (selection.equals(courseList.get(i).getName())) {

                                    selectedCourse = courseList.get(i);
                                    break;

                                }
                            }

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

        buttonAddTask2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelTaskAddedSuccess);
                rightPanel.remove(labelTaskAddedFailure);
                rightPanel.remove(labelFillTaskDescription);
                rightPanel.revalidate();
                rightPanel.repaint();

                int courseID = selectedCourse.getId();
                String taskDescription = textAreaTaskDescription.getText();

                if (!taskDescription.isBlank()) {

                    if (services.addTask(professorID, courseID, taskDescription)) {

                        rightPanel.add(labelTaskAddedSuccess);
                        labelTaskAddedSuccess.setBounds(400, 560, 200, 50);
                        labelTaskAddedSuccess.setForeground(Color.GREEN);

                    } else {

                        rightPanel.add(labelTaskAddedFailure);
                        labelTaskAddedFailure.setBounds(400, 560, 200, 50);
                        labelTaskAddedFailure.setForeground(Color.RED);

                    }

                } else {

                    rightPanel.add(labelFillTaskDescription);
                    labelFillTaskDescription.setBounds(400, 560, 200, 50);
                    labelFillTaskDescription.setForeground(Color.RED);

                }

            }
        });


        buttonViewTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getProfessorCourseList(professorID);

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

                    rightPanel.add(buttonDeleteTasks);
                    buttonDeleteTasks.setBounds(700, 100, 200, 50);


                }  else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }

            }
        });

        buttonDeleteTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelNoTasks);
                rightPanel.remove(labelTaskDeletionSuccess);
                rightPanel.remove(labelTaskDeletionFailure);
                rightPanel.revalidate();
                rightPanel.repaint();


                if (taskList.size() > 0) {

                    frameDeleteTask.setTitle("");
                    frameDeleteTask.setSize(300, 200);
                    frameDeleteTask.setLayout(null);
                    frameDeleteTask.setVisible(true);
                    frameDeleteTask.setResizable(false);
                    frameDeleteTask.setLocationRelativeTo(null);
                    frameDeleteTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    frameDeleteTask.add(labelDeleteTask);
                    frameDeleteTask.add(buttonDeleteTaskYes);
                    frameDeleteTask.add(buttonDeleteTaskNo);

                    labelDeleteTask.setBounds(0, 25, 300, 50);
                    buttonDeleteTaskYes.setBounds(25, 100, 100, 50);
                    buttonDeleteTaskNo.setBounds(175, 100, 100, 50);

                } else {

                    rightPanel.add(labelNoTasks);
                    labelNoTasks.setBounds(700, 75, 200, 25);
                    labelNoTasks.setForeground(Color.RED);

                }

            }
        });

        buttonDeleteTaskYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                Integer taskID = taskList.get(i).getTaskID();

                if (services.deleteTask(taskID)) {

                    rightPanel.remove(scrollPane);
                    rightPanel.revalidate();
                    rightPanel.repaint();

                    initializeTableTasks(selectedCourse.getId());

                    rightPanel.add(labelTaskDeletionSuccess);
                    labelTaskDeletionSuccess.setBounds(700, 75, 200, 25);
                    labelTaskDeletionSuccess.setForeground(Color.GREEN);

                    frameDeleteTask.dispose();

                } else {

                    rightPanel.add(labelTaskDeletionFailure);
                    labelTaskDeletionFailure.setBounds(700, 75, 200, 25);
                    labelTaskDeletionFailure.setForeground(Color.RED);

                    frameDeleteTask.dispose();

                }
            }
        });

        buttonDeleteTaskNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameDeleteTask.dispose();

            }
        });


        buttonScheduleExams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getProfessorCourseList(professorID);

                if (courseList.size() > 0) {

                    JLabel labelScheduleExams = new JLabel("SCHEDULE EXAMS", SwingConstants.CENTER);

                    rightPanel.add(labelScheduleExams);
                    labelScheduleExams.setBounds(0, 0, 1000, 50);
                    labelScheduleExams.setBackground(Color.LIGHT_GRAY);
                    labelScheduleExams.setOpaque(true);

                    String[] courseArray = new String[courseList.size()];

                    for (int i = 0; i < courseArray.length; i++) courseArray[i] = courseList.get(i).getName();

                    JLabel labelSelectCourse = new JLabel("Select a course:", SwingConstants.CENTER);
                    rightPanel.add(labelSelectCourse);
                    labelSelectCourse.setBounds(400, 75, 200, 25);

                    comboBoxScheduleExamsCourses = new JComboBox(courseArray);
                    rightPanel.add(comboBoxScheduleExamsCourses);
                    comboBoxScheduleExamsCourses.setBounds(400, 100, 200, 50);


                    JLabel labelExamType = new JLabel("Exam type:", SwingConstants.CENTER);
                    rightPanel.add(labelExamType);
                    labelExamType.setBounds(400, 200, 200, 50);

                    rightPanel.add(comboBoxExamTypes);
                    comboBoxExamTypes.setBounds(400, 250, 200, 50);


                    JLabel labelExamDate = new JLabel("Exam date:", SwingConstants.CENTER);
                    rightPanel.add(labelExamDate);
                    labelExamDate.setBounds(400, 300, 200, 50);

                    rightPanel.add(textFieldExamDate);
                    textFieldExamDate.setBounds(400, 350, 200, 50);
                    textFieldExamDate.setText("Date format: YYYY-MM-DD");
                    textFieldExamDate.setToolTipText("Date format: YYYY-MM-DD");


                    JLabel labelExamTime = new JLabel("Exam time:", SwingConstants.CENTER);
                    rightPanel.add(labelExamTime);
                    labelExamTime.setBounds(400, 400, 200, 50);

                    rightPanel.add(textFieldExamTime);
                    textFieldExamTime.setBounds(400, 450, 200, 50);
                    textFieldExamTime.setText("Time format: HH:MM");
                    textFieldExamTime.setToolTipText("Time format: HH:MM");

                    rightPanel.add(buttonScheduleExam2);
                    buttonScheduleExam2.setBounds(400, 550, 200, 50);

                    comboBoxScheduleExamsCourses.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            rightPanel.remove(labelScheduleExamSuccess);
                            rightPanel.remove(labelScheduleExamError);
                            rightPanel.remove(labelScheduleExamTaken);
                            rightPanel.remove(labelFillAllFields);
                            rightPanel.remove(labelWrongDateTimeFormat);

                            rightPanel.revalidate();
                            rightPanel.repaint();

                        }
                    });

                    comboBoxExamTypes.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            rightPanel.remove(labelScheduleExamSuccess);
                            rightPanel.remove(labelScheduleExamError);
                            rightPanel.remove(labelScheduleExamTaken);
                            rightPanel.remove(labelFillAllFields);
                            rightPanel.remove(labelWrongDateTimeFormat);

                            rightPanel.revalidate();
                            rightPanel.repaint();

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

        buttonScheduleExam2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelScheduleExamSuccess);
                rightPanel.remove(labelScheduleExamError);
                rightPanel.remove(labelScheduleExamTaken);
                rightPanel.remove(labelFillAllFields);
                rightPanel.remove(labelWrongDateTimeFormat);

                rightPanel.revalidate();
                rightPanel.repaint();

                String selection = (String) comboBoxScheduleExamsCourses.getSelectedItem();
                int courseID = 0;

                for (int i = 0; i < courseList.size(); i++) {

                    if (selection.equals(courseList.get(i).getName())) {

                        courseID = courseList.get(i).getId();
                        break;

                    }
                }

                String examType = (String) comboBoxExamTypes.getSelectedItem();
                String examDate = textFieldExamDate.getText();
                String examTime = textFieldExamTime.getText();


                boolean condition = true;

                if (courseID == 0) condition = false;
                else if (Objects.isNull(examType))  condition = false;
                else if (examDate.isBlank())  condition = false;
                else if (examTime.isBlank())  condition = false;


                if (condition) {

                    if ((services.verifyDate(examDate)) && (services.verifyTime(examTime))) {

                        String examDateTime = examDate + " " + examTime + ":00";

                        String result = services.scheduleExam(courseID, examType, examDateTime);

                        if (result.equals("SUCCESS")) {

                            rightPanel.add(labelScheduleExamSuccess);
                            labelScheduleExamSuccess.setBounds(400, 600, 200, 50);
                            labelScheduleExamSuccess.setForeground(Color.GREEN);

                        } else if (result.equals("TAKEN")) {

                            rightPanel.add(labelScheduleExamTaken);
                            labelScheduleExamTaken.setBounds(400, 600, 200, 50);
                            labelScheduleExamTaken.setForeground(Color.RED);

                        } else if (result.equals("ERROR")) {

                            rightPanel.add(labelScheduleExamError);
                            labelScheduleExamError.setBounds(400, 600, 200, 50);
                            labelScheduleExamError.setForeground(Color.RED);

                        }

                    } else {

                        rightPanel.add(labelWrongDateTimeFormat);
                        labelWrongDateTimeFormat.setBounds(400, 600, 200, 50);
                        labelWrongDateTimeFormat.setForeground(Color.RED);

                    }

                } else {

                    rightPanel.add(labelFillAllFields);
                    labelFillAllFields.setBounds(400, 600, 200, 50);
                    labelFillAllFields.setForeground(Color.RED);

                }
            }
        });


        buttonViewExams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getProfessorCourseList(professorID);

                if (courseList.size() > 0) {

                    JLabel labelViewExams = new JLabel("VIEW EXAMS", SwingConstants.CENTER);

                    rightPanel.add(labelViewExams);
                    labelViewExams.setBounds(0, 0, 1000, 50);
                    labelViewExams.setBackground(Color.LIGHT_GRAY);
                    labelViewExams.setOpaque(true);

                    initializeTableExams();

                    rightPanel.add(buttonDeleteExam);
                    buttonDeleteExam.setBounds(400, 625, 200, 50);

                }   else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }

            }
        });

        buttonDeleteExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelNoExams);
                rightPanel.remove(labelExamDeletionSuccess);
                rightPanel.remove(labelExamDeletionFailure);
                rightPanel.revalidate();
                rightPanel.repaint();


                if (examList.size() > 0) {

                    frameDeleteExam.setTitle("");
                    frameDeleteExam.setSize(300, 200);
                    frameDeleteExam.setLayout(null);
                    frameDeleteExam.setVisible(true);
                    frameDeleteExam.setResizable(false);
                    frameDeleteExam.setLocationRelativeTo(null);
                    frameDeleteExam.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    frameDeleteExam.add(labelDeleteExam);
                    frameDeleteExam.add(buttonDeleteExamYes);
                    frameDeleteExam.add(buttonDeleteExamNo);

                    labelDeleteExam.setBounds(0, 25, 300, 50);
                    buttonDeleteExamYes.setBounds(25, 100, 100, 50);
                    buttonDeleteExamNo.setBounds(175, 100, 100, 50);

                } else {

                    rightPanel.add(labelNoExams);
                    labelNoExams.setBounds(400, 675, 200, 25);
                    labelNoExams.setForeground(Color.RED);

                }

            }
        });

        buttonDeleteExamYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                Integer courseID = examList.get(i).getCourse().getId();
                String examDateTime = examList.get(i).getDateTime();

                if (services.deleteExam(courseID, examDateTime)) {

                    rightPanel.remove(scrollPane);
                    rightPanel.revalidate();
                    rightPanel.repaint();

                    initializeTableExams();

                    rightPanel.add(labelExamDeletionSuccess);
                    labelExamDeletionSuccess.setBounds(400, 675, 200, 25);
                    labelExamDeletionSuccess.setForeground(Color.GREEN);

                    frameDeleteExam.dispose();

                } else {

                    rightPanel.add(labelExamDeletionFailure);
                    labelExamDeletionFailure.setBounds(400, 675, 200, 25);
                    labelExamDeletionFailure.setForeground(Color.RED);

                    frameDeleteExam.dispose();

                }
            }
        });

        buttonDeleteExamNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameDeleteExam.dispose();

            }
        });


        buttonGiveGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                selectedCourse = null;
                selectedStudent = null;

                courseList = services.getProfessorCourseList(professorID);

                if (courseList.size() > 0) {

                    JLabel labelGiveGrades = new JLabel("GIVE/MODIFY GRADES", SwingConstants.CENTER);
                    rightPanel.add(labelGiveGrades);
                    labelGiveGrades.setBounds(0, 0, 1000, 50);
                    labelGiveGrades.setBackground(Color.LIGHT_GRAY);
                    labelGiveGrades.setOpaque(true);

                    examList = services.getProfessorExams(courseList);

                    JLabel labelSelectCourse = new JLabel("Select a course:", SwingConstants.CENTER);
                    rightPanel.add(labelSelectCourse);
                    labelSelectCourse.setBounds(350, 75, 300, 25);

                    initializeComboBoxGradeCourses();
                    rightPanel.add(comboBoxGradeCourses);
                    comboBoxGradeCourses.setBounds(350, 100, 300, 50);
                    comboBoxGradeCourses.setSelectedIndex(0);


                    getSelectedCourse();


                    JLabel labelSelectStudent = new JLabel("Select a student:", SwingConstants.CENTER);
                    rightPanel.add(labelSelectStudent);
                    labelSelectStudent.setBounds(350, 150, 300, 50);

                    initializeComboBoxStudents();
                    rightPanel.add(comboBoxStudents);
                    comboBoxStudents.setBounds(350, 200, 300, 50);
                    comboBoxStudents.setSelectedIndex(0);


                    getSelectedStudent();


                    JLabel labelStudentName = new JLabel("Student:");
                    rightPanel.add(labelStudentName);
                    labelStudentName.setBounds(350, 300, 100, 50);

                    rightPanel.add(textFieldStudentName);
                    textFieldStudentName.setBounds(450, 300, 200, 50);
                    textFieldStudentName.setEditable(false);
                    textFieldStudentName.setBackground(Color.LIGHT_GRAY);
                    textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());


                    JLabel labelExamDate = new JLabel("Exam date:");
                    rightPanel.add(labelExamDate);
                    labelExamDate.setBounds(350, 350, 100, 50);

                    initializeComboBoxExamDates();
                    rightPanel.add(comboBoxExamDates);
                    comboBoxExamDates.setBounds(450, 350, 200, 50);
                    comboBoxExamDates.setSelectedIndex(0);

                    selectExamDate();

                    JLabel labelGradeEquals0 = new JLabel("0 = no grade", SwingConstants.CENTER);
                    rightPanel.add(labelGradeEquals0);
                    labelGradeEquals0.setBounds(510, 425, 140, 25);

                    JLabel labelGrade = new JLabel("Grade:");
                    rightPanel.add(labelGrade);
                    labelGrade.setBounds(350, 450, 100, 50);

                    rightPanel.add(textFieldGrade);
                    textFieldGrade.setBounds(450, 450, 50, 50);

                    String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                    if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                    else textFieldGrade.setText(studentGrade);


                    rightPanel.add(buttonModifyGrade);
                    rightPanel.add(buttonPreviousStudent);
                    rightPanel.add(buttonNextStudent);

                    buttonModifyGrade.setBounds(510, 450, 140, 50);
                    buttonPreviousStudent.setBounds(350, 510, 150, 50);
                    buttonNextStudent.setBounds(500, 510, 150, 50);


                    comboBoxGradeCourses.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            rightPanel.remove(labelGradeModSucces);
                            rightPanel.remove(labelGradeModFailure);
                            rightPanel.remove(labelScheduleAnExamm);
                            rightPanel.remove(labelGradeNotANumber);
                            rightPanel.remove(comboBoxExamDates);
                            rightPanel.remove(comboBoxStudents);
                            rightPanel.revalidate();
                            rightPanel.repaint();

                            getSelectedCourse();

                            initializeComboBoxStudents();
                            rightPanel.add(comboBoxStudents);
                            comboBoxStudents.setBounds(350, 200, 300, 50);
                            comboBoxStudents.setSelectedIndex(0);

                            getSelectedStudent();
                            textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());

                            initializeComboBoxExamDates();
                            rightPanel.add(comboBoxExamDates);
                            comboBoxExamDates.setBounds(450, 350, 200, 50);
                            comboBoxExamDates.setSelectedIndex(0);

                            selectExamDate();

                            String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                            if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                            else textFieldGrade.setText(studentGrade);


                            comboBoxStudents.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    rightPanel.remove(labelGradeModSucces);
                                    rightPanel.remove(labelGradeModFailure);
                                    rightPanel.remove(labelScheduleAnExamm);
                                    rightPanel.remove(labelGradeNotANumber);
                                    rightPanel.remove(comboBoxExamDates);
                                    rightPanel.revalidate();
                                    rightPanel.repaint();

                                    getSelectedStudent();
                                    textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());

                                    initializeComboBoxExamDates();
                                    rightPanel.add(comboBoxExamDates);
                                    comboBoxExamDates.setBounds(450, 350, 200, 50);
                                    comboBoxExamDates.setSelectedIndex(0);

                                    selectExamDate();

                                    String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                                    if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                                    else textFieldGrade.setText(studentGrade);

                                    comboBoxExamDates.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            rightPanel.remove(labelGradeModSucces);
                                            rightPanel.remove(labelGradeModFailure);
                                            rightPanel.remove(labelScheduleAnExamm);
                                            rightPanel.remove(labelGradeNotANumber);
                                            rightPanel.revalidate();
                                            rightPanel.repaint();

                                        }
                                    });

                                }
                            });
                        }
                    });


                } else {

                    JLabel labelNoCourses = new JLabel("<html><div style=\"text-align:center\">You are not assigned to any courses.<br>Please contact your administrator.</div></html>", SwingConstants.CENTER);
                    rightPanel.add(labelNoCourses);
                    labelNoCourses.setBounds(0, 100, 1000, 100);
                    labelNoCourses.setOpaque(true);
                    labelNoCourses.setBackground(Color.LIGHT_GRAY);

                }
            }
        });

        buttonPreviousStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedStudentIndex = comboBoxStudents.getSelectedIndex();
                int newIndex = selectedStudentIndex;

                if (selectedStudentIndex > 0) newIndex--;

                rightPanel.remove(labelGradeModSucces);
                rightPanel.remove(labelGradeModFailure);
                rightPanel.remove(labelScheduleAnExamm);
                rightPanel.remove(labelGradeNotANumber);
                rightPanel.remove(comboBoxExamDates);
                rightPanel.remove(comboBoxStudents);
                rightPanel.revalidate();
                rightPanel.repaint();

                getSelectedCourse();

                initializeComboBoxStudents();
                rightPanel.add(comboBoxStudents);
                comboBoxStudents.setBounds(350, 200, 300, 50);

                comboBoxStudents.setSelectedIndex(newIndex);
                getSelectedStudent();
                textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());

                initializeComboBoxExamDates();
                rightPanel.add(comboBoxExamDates);
                comboBoxExamDates.setBounds(450, 350, 200, 50);
                comboBoxExamDates.setSelectedIndex(0);

                selectExamDate();

                String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                else textFieldGrade.setText(studentGrade);


                comboBoxStudents.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(labelGradeModSucces);
                        rightPanel.remove(labelGradeModFailure);
                        rightPanel.remove(labelScheduleAnExamm);
                        rightPanel.remove(labelGradeNotANumber);
                        rightPanel.remove(comboBoxExamDates);
                        rightPanel.revalidate();
                        rightPanel.repaint();

                        getSelectedStudent();
                        textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());

                        initializeComboBoxExamDates();
                        rightPanel.add(comboBoxExamDates);
                        comboBoxExamDates.setBounds(450, 350, 200, 50);
                        comboBoxExamDates.setSelectedIndex(0);

                        selectExamDate();

                        String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                        if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                        else textFieldGrade.setText(studentGrade);

                        comboBoxExamDates.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.remove(labelGradeModSucces);
                                rightPanel.remove(labelGradeModFailure);
                                rightPanel.remove(labelScheduleAnExamm);
                                rightPanel.remove(labelGradeNotANumber);
                                rightPanel.revalidate();
                                rightPanel.repaint();

                            }
                        });

                    }
                });
            }
        });

        buttonNextStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int boxSize = comboBoxStudents.getModel().getSize();
                int selectedStudentIndex = comboBoxStudents.getSelectedIndex();
                int newIndex = selectedStudentIndex;

                if (selectedStudentIndex < (boxSize - 1)) newIndex++;

                rightPanel.remove(labelGradeModSucces);
                rightPanel.remove(labelGradeModFailure);
                rightPanel.remove(labelScheduleAnExamm);
                rightPanel.remove(labelGradeNotANumber);
                rightPanel.remove(comboBoxExamDates);
                rightPanel.remove(comboBoxStudents);
                rightPanel.revalidate();
                rightPanel.repaint();

                getSelectedCourse();

                initializeComboBoxStudents();
                rightPanel.add(comboBoxStudents);
                comboBoxStudents.setBounds(350, 200, 300, 50);

                comboBoxStudents.setSelectedIndex(newIndex);
                getSelectedStudent();
                textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());

                initializeComboBoxExamDates();
                rightPanel.add(comboBoxExamDates);
                comboBoxExamDates.setBounds(450, 350, 200, 50);
                comboBoxExamDates.setSelectedIndex(0);

                selectExamDate();

                String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                else textFieldGrade.setText(studentGrade);


                comboBoxStudents.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(labelGradeModSucces);
                        rightPanel.remove(labelGradeModFailure);
                        rightPanel.remove(labelScheduleAnExamm);
                        rightPanel.remove(labelGradeNotANumber);
                        rightPanel.remove(comboBoxExamDates);
                        rightPanel.revalidate();
                        rightPanel.repaint();

                        getSelectedStudent();
                        textFieldStudentName.setText(selectedStudent.getLastName() + " " + selectedStudent.getFirstName());

                        initializeComboBoxExamDates();
                        rightPanel.add(comboBoxExamDates);
                        comboBoxExamDates.setBounds(450, 350, 200, 50);
                        comboBoxExamDates.setSelectedIndex(0);

                        selectExamDate();

                        String studentGrade = services.getStudentGrade(selectedStudent.getId(), selectedCourse.getId());

                        if (Objects.isNull(studentGrade)) textFieldGrade.setText("");
                        else textFieldGrade.setText(studentGrade);

                        comboBoxExamDates.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.remove(labelGradeModSucces);
                                rightPanel.remove(labelGradeModFailure);
                                rightPanel.remove(labelScheduleAnExamm);
                                rightPanel.remove(labelGradeNotANumber);
                                rightPanel.revalidate();
                                rightPanel.repaint();

                            }
                        });

                    }
                });
            }
        });


        buttonModifyGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelGradeModSucces);
                rightPanel.remove(labelGradeModFailure);
                rightPanel.remove(labelScheduleAnExamm);
                rightPanel.remove(labelGradeNotANumber);
                rightPanel.revalidate();
                rightPanel.repaint();

                Integer courseID = null;
                Integer studentID = null;
                String examDate = null;
                String gradeString = null;
                Integer grade = null;

                if (!Objects.isNull(selectedCourse)) courseID = selectedCourse.getId();
                if (!Objects.isNull(selectedStudent)) studentID = selectedStudent.getId();
                if (0 != (comboBoxExamDates.getModel().getSize())) examDate = (String) comboBoxExamDates.getSelectedItem();
                if (!textFieldGrade.getText().isBlank()) gradeString = textFieldGrade.getText();

                boolean condition = true;

                if (Objects.isNull(examDate)) condition = false;

                if (condition) {

                    if (!Objects.isNull(gradeString)) {

                        if (!gradeString.isBlank()) {

                            char[] chars = gradeString.toCharArray();
                            boolean intCondition = true;

                            for (int i = 0; i < chars.length; i++) {

                                if (
                                        (chars[i] != '0') &&
                                                (chars[i] != '1') &&
                                                (chars[i] != '2') &&
                                                (chars[i] != '3') &&
                                                (chars[i] != '4') &&
                                                (chars[i] != '5') &&
                                                (chars[i] != '6') &&
                                                (chars[i] != '7') &&
                                                (chars[i] != '8') &&
                                                (chars[i] != '9')
                                ) {

                                    intCondition = false;
                                    break;

                                }

                            }

                            if (intCondition) {

                                grade = Integer.parseInt(gradeString);
                                if (grade > 10) intCondition = false;


                                if (intCondition) {

                                        if (services.modifyGrade(studentID, courseID, examDate, grade)) {

                                            rightPanel.add(labelGradeModSucces);
                                            labelGradeModSucces.setBounds(350, 560, 300, 50);
                                            labelGradeModSucces.setForeground(Color.GREEN);

                                            textFieldGrade.setText(services.getStudentGrade(studentID, courseID));

                                        } else {

                                            rightPanel.add(labelGradeModFailure);
                                            labelGradeModFailure.setBounds(350, 560, 300, 50);
                                            labelGradeModFailure.setForeground(Color.RED);

                                        }

                                } else {

                                    rightPanel.add(labelGradeNotANumber);
                                    labelGradeNotANumber.setBounds(350, 560, 300, 50);
                                    labelGradeNotANumber.setForeground(Color.RED);
                                }

                            } else {

                                rightPanel.add(labelGradeNotANumber);
                                labelGradeNotANumber.setBounds(350, 560, 300, 50);
                                labelGradeNotANumber.setForeground(Color.RED);
                            }

                        } else {

                            rightPanel.add(labelGradeNotANumber);
                            labelGradeNotANumber.setBounds(350, 560, 300, 50);
                            labelGradeNotANumber.setForeground(Color.RED);
                        }

                    } else {

                        rightPanel.add(labelGradeNotANumber);
                        labelGradeNotANumber.setBounds(350, 560, 300, 50);
                        labelGradeNotANumber.setForeground(Color.RED);
                    }

                } else {

                    rightPanel.add(labelScheduleAnExamm);
                    labelScheduleAnExamm.setBounds(350, 560, 300, 50);
                    labelScheduleAnExamm.setForeground(Color.RED);

                }

            }
        });


        buttonViewGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                courseList = services.getProfessorCourseList(professorID);

                if (courseList.size() > 0) {

                    examList = services.getProfessorExams(courseList);
                    gradeList = services.getExamGrades(courseList);

                    JLabel labelViewGrades = new JLabel("VIEW GRADES", SwingConstants.CENTER);

                    rightPanel.add(labelViewGrades);
                    labelViewGrades.setBounds(0, 0, 1000, 50);
                    labelViewGrades.setBackground(Color.LIGHT_GRAY);
                    labelViewGrades.setOpaque(true);


                    String[] courseArray = new String[courseList.size()];

                    for (int i = 0; i < courseArray.length; i++) courseArray[i] = courseList.get(i).getName();


                    JLabel labelSelectCourse2 = new JLabel("Select a course:", SwingConstants.CENTER);
                    rightPanel.add(labelSelectCourse2);
                    labelSelectCourse2.setBounds(400, 75, 200, 25);

                    JComboBox comboBoxCourses = new JComboBox(courseArray);
                    rightPanel.add(comboBoxCourses);
                    comboBoxCourses.setBounds(400, 100, 200, 50);

                    String selection = (String) comboBoxCourses.getSelectedItem();

                    int rowCount = 0;

                    for (int i = 0; i < gradeList.size(); i++) {

                        if (selection.equals(gradeList.get(i).getCourse().getName())) rowCount++;

                    }


                    table = new JTable(rowCount, 5);

                    model = (DefaultTableModel) table.getModel();

                    table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                    table.getColumnModel().getColumn(1).setHeaderValue("Student");
                    table.getColumnModel().getColumn(2).setHeaderValue("Grade");
                    table.getColumnModel().getColumn(3).setHeaderValue("Date");
                    table.getColumnModel().getColumn(4).setHeaderValue("Time");


                    table.getColumnModel().getColumn(0).setPreferredWidth(25);
                    table.getColumnModel().getColumn(1).setPreferredWidth(200);
                    table.getColumnModel().getColumn(2).setPreferredWidth(50);
                    table.getColumnModel().getColumn(3).setPreferredWidth(50);
                    table.getColumnModel().getColumn(4).setPreferredWidth(50);


                    scrollPane = new JScrollPane(table);
                    rightPanel.add(scrollPane);
                    scrollPane.setBounds(100, 200, 800, 500);

                    table.setRowHeight(50);

                    int row = 0;

                    for (int i = 0; i < gradeList.size(); i++) {

                        if (selection.equals(gradeList.get(i).getCourse().getName())) {

                            String studentName = gradeList.get(i).getStudent().getLastName() + " " + gradeList.get(i).getStudent().getFirstName();
                            String[] dateTime = null;

                            if ((gradeList.get(i).getGrade()) == 0) {

                                dateTime = new String[]{"-"};

                            } else dateTime = gradeList.get(i).getDateTime().split(" ");


                            model.setValueAt(row + 1, row, 0);
                            model.setValueAt(studentName, row, 1);
                            if ((gradeList.get(i).getGrade()) == 0) model.setValueAt("-", row, 2);
                            else model.setValueAt(gradeList.get(i).getGrade(), row, 2);


                            if (dateTime[0].equals("-")) {
                                model.setValueAt("-", row, 3);
                                model.setValueAt("-", row, 4);
                            } else {
                                model.setValueAt(dateTime[0], row, 3);
                                model.setValueAt(dateTime[1], row, 4);
                            }

                            row++;

                        }

                        table.setDefaultEditor(Object.class, null);

                    }

                    rightPanel.revalidate();

                    comboBoxCourses.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            rightPanel.remove(scrollPane);
                            rightPanel.revalidate();
                            rightPanel.repaint();

                            String selection2 = (String) comboBoxCourses.getSelectedItem();

                            int rowCount2 = 0;

                            for (int i = 0; i < gradeList.size(); i++) {

                                if (selection2.equals(gradeList.get(i).getCourse().getName())) rowCount2++;

                            }

                            table = new JTable(rowCount2, 5);

                            model = (DefaultTableModel) table.getModel();

                            table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
                            table.getColumnModel().getColumn(1).setHeaderValue("Student");
                            table.getColumnModel().getColumn(2).setHeaderValue("Grade");
                            table.getColumnModel().getColumn(3).setHeaderValue("Date");
                            table.getColumnModel().getColumn(4).setHeaderValue("Time");


                            table.getColumnModel().getColumn(0).setPreferredWidth(25);
                            table.getColumnModel().getColumn(1).setPreferredWidth(200);
                            table.getColumnModel().getColumn(2).setPreferredWidth(50);
                            table.getColumnModel().getColumn(3).setPreferredWidth(50);
                            table.getColumnModel().getColumn(4).setPreferredWidth(50);


                            scrollPane = new JScrollPane(table);
                            rightPanel.add(scrollPane);
                            scrollPane.setBounds(100, 200, 800, 500);

                            table.setRowHeight(50);

                            int row2 = 0;

                            for (int i = 0; i < gradeList.size(); i++) {

                                if (selection2.equals(gradeList.get(i).getCourse().getName())) {

                                    String studentName2 = gradeList.get(i).getStudent().getLastName() + " " + gradeList.get(i).getStudent().getFirstName();
                                    String[] dateTime2 = null;

                                    if ((gradeList.get(i).getGrade()) == 0) {

                                        dateTime2 = new String[]{"-"};

                                    } else dateTime2 = gradeList.get(i).getDateTime().split(" ");


                                    model.setValueAt(row2 + 1, row2, 0);
                                    model.setValueAt(studentName2, row2, 1);
                                    if (gradeList.get(i).getGrade() == 0) model.setValueAt("-", row2, 2);
                                    else model.setValueAt(gradeList.get(i).getGrade(), row2, 2);


                                    if (dateTime2[0].equals("-")) {
                                        model.setValueAt("-", row2, 3);
                                        model.setValueAt("-", row2, 4);
                                    } else {
                                        model.setValueAt(dateTime2[0], row2, 3);
                                        model.setValueAt(dateTime2[1], row2, 4);
                                    }


                                    row2++;

                                }
                            }

                            table.setDefaultEditor(Object.class, null);

                            rightPanel.revalidate();

                        }
                    });

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


    public ProfessorScreen (String username, Services services) {

        this.services = services;
        this.username = username;
        this.professorName = services.getName(username);
        this.userID = services.getUserID(username);
        this.professorID = services.getProfessorID(userID);

        frame.setTitle("Professor Screen");
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

        topPanel.add(labelProfessorName);
        labelProfessorName.setBounds(10, 0, 200, 50);
        labelProfessorName.setText("Professor: " + professorName);

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
        leftPanel.add(buttonAddTask);
        leftPanel.add(buttonViewTasks);
        leftPanel.add(buttonScheduleExams);
        leftPanel.add(buttonViewExams);
        leftPanel.add(buttonGiveGrades);
        leftPanel.add(buttonViewGrades);

        buttonViewCourses.setBounds(5, 10, 90, 80);
        buttonViewSchedule.setBounds(5, 10+80+5, 90, 80);
        buttonAddTask.setBounds(5, 10+160+10, 90, 80);
        buttonViewTasks.setBounds(5, 10+240+15, 90, 80);
        buttonScheduleExams.setBounds(5, 10+320+20, 90, 80);
        buttonViewExams.setBounds(5, 10+400+25, 90, 80);
        buttonGiveGrades.setBounds(5, 10+480+30, 90, 80);
        buttonViewGrades.setBounds(5, 10+560+35, 90, 80);

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
        ProfessorScreen professorScreen = new ProfessorScreen("professor7", services);

//        for (int i = 1; i < 10; i++) new ProfessorScreen("professor" + i, services);
//        ProfessorScreen professorScreen = new ProfessorScreen("professor9", services);


        /*professorScreen = new ProfessorScreen("professor4", services);
        professorScreen = new ProfessorScreen("professor5", services);
        professorScreen = new ProfessorScreen("professor6", services);
        professorScreen = new ProfessorScreen("professor8", services);*/


    }

}
