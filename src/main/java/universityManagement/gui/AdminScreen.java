package universityManagement.gui;

import universityManagement.baseClasses.*;
import universityManagement.repository.Repository;
import universityManagement.services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class AdminScreen extends JFrame {

    private Services services = null;
    private String username = null;
    private String adminName = null;
    private int userID = 0;

    private JFrame frame = new JFrame();
    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JLabel labelAdminName = new JLabel("");
    private JButton buttonCreateNewAccounts = new JButton("<html><div style=\"text-align:center\">Create<br>Accounts</div></html>");
    private JButton buttonDeleteAccounts = new JButton("<html><div style=\"text-align:center\">Delete<br>Accounts</div></html>");
    //private JButton buttonModifyAccounts = new JButton("<html><div style=\"text-align:center\">Modify<br>Accounts</div></html>");
    private JButton buttonChangeProfessorCourse = new JButton("<html><div style=\"text-align:center\">Change a professor's<br>course</div></html>");

    // LOG OUT FUNCTION

    private JButton buttonLogOut = new JButton("Log out");
    private JFrame frameLogOut = new JFrame();
    private JLabel labelLogOut = new JLabel("Do you wish to log out?", SwingConstants.CENTER);
    private JButton buttonLogOutYes = new JButton("Yes");
    private JButton buttonLogOutNo = new JButton("No");

    // LOG OUT FUNCTION



    // CHANGE PASSWORD FUNCTION

    private JButton buttonChangePassword = new JButton("Change password");
    private JTextField textFieldCurrentPassword = new JTextField();
    private JTextField textFieldNewPassword1 = new JTextField();
    private JTextField textFieldNewPassword2 = new JTextField();
    private JButton buttonChangePassword2 = new JButton("Change password");
    private JLabel labelWrongCurrentPassword = new JLabel("<html><div style=\"text-align:center\">You have not introduced the<br>correct current password.</div></html>", SwingConstants.CENTER);
    private JLabel labelWrongNewPassword = new JLabel("<html><div style=\"text-align:center\">You have not introduced the same new password.<br>Please try again.</div></html>", SwingConstants.CENTER);
    private JLabel labelPasswordChangedSuccessfully = new JLabel("Password changed successfully.", SwingConstants.CENTER);
    private JLabel labelPasswordChangeError = new JLabel("<html><div style=\"text-align:center\">Something went wrong. Please try again.</div></html>", SwingConstants.CENTER);
    private JLabel labelFillNewPasswordFields = new JLabel("<html><div style=\"text-align:center\">Please introduce the new password<br>and fill all fields.</div></html>", SwingConstants.CENTER);

    // CHANGE PASSWORD FUNCTION



    // CREATE ACCOUNT FUNCTION

    private JLabel labelCreateAccount = new JLabel("CREATE AN ACCOUNT", SwingConstants.CENTER);

    private JLabel labelAccountType = new JLabel("Account type:");
    private JRadioButton radioButtonAdmin = new JRadioButton("Admin");
    private JRadioButton radioButtonProfessor = new JRadioButton("Professor");
    private JRadioButton radioButtonStudent = new JRadioButton("Student");
    private ButtonGroup buttonGroup1 = new ButtonGroup();

    private JLabel labelUsername = new JLabel("Username:");
    private JTextField textFieldUsername = new JTextField();

    private JLabel labelPassword = new JLabel("Password:");
    private JTextField textFieldPassword = new JTextField();

    private JLabel labelLastName = new JLabel("Last Name:");
    private JTextField textFieldLastName = new JTextField();

    private JLabel labelFirstName = new JLabel("First Name:");
    private JTextField textFieldFirstName = new JTextField();

    private JLabel labelExpertise = new JLabel("Expertise:");
    private JTextField textFieldExpertise = new JTextField();

    private JLabel labelBirthDate = new JLabel("Birth date:");
    private JTextField textFieldBirthDate = new JTextField();

    private JLabel labelEmail = new JLabel("Email:");
    private JTextField textFieldEmail = new JTextField();

    private JLabel labelPhone = new JLabel("Phone:");
    private JTextField textFieldPhone = new JTextField();

    private JLabel labelFaculty = new JLabel("Faculty:");
    private JLabel labelStudyLevel = new JLabel("Study Level:");
    private JLabel labelSpecialization = new JLabel("Specialization:");
    private JLabel labelStudyYear = new JLabel("Study Year:");
    private JLabel labelStudentGroup = new JLabel("Student Group:");

    private JComboBox comboBoxFaculties = new JComboBox();
    private JComboBox comboBoxStudyLevel = new JComboBox();
    private JComboBox comboBoxSpecializations = new JComboBox();
    private JComboBox comboBoxStudyYear = new JComboBox();
    private JComboBox comboBoxStudentGroups = new JComboBox();

    private JButton buttonCreateAccount = new JButton("Create Account");

    private JLabel labelAccountCreated = new JLabel("Account created successfully!", SwingConstants.CENTER);
    private JLabel labelFillAllFields = new JLabel("Please fill all fields!", SwingConstants.CENTER);
    private JLabel labelAccountNotCreated = new JLabel("Something went wrong. Please try again!", SwingConstants.CENTER);
    private JLabel labelUnavailableUsername = new JLabel("Username already taken. Please choose another one", SwingConstants.CENTER);
    private JLabel labelWrongDateFormat = new JLabel("<html><div style=\"text-align:center\">You have introduced an invalid date.<br>Please try again.</div></html>", SwingConstants.CENTER);

    // student - studentID, userID, lastName, firstName, birthDate, email, phone, GroupID
    // admin - adminID, user ID, lastName, firstName
    // professor - professorId, userId, lastName, firsTname, birthDate, facultyID, Expertise

    // CREATE ACCOUNT FUNCTION


    // DELETE ACCOUNT FUNCTION

    private JLabel labelChooseAccountToDelete = new JLabel("DELETE AN ACCOUNT", SwingConstants.CENTER);

    private JButton buttonDeleteAnAccount = new JButton("Delete the account");
    private JComboBox comboBoxAccounts = new JComboBox();

    private JLabel labelUserType = new JLabel("User type:");
    private JTextField textFieldUserType = new JTextField();

    private JLabel labelUserID = new JLabel("User ID:");
    private JTextField textFieldUserID = new JTextField();

    private JFrame frameConfirmDeletion = new JFrame();
    private JButton buttonConfirmDeletion = new JButton("Yes");
    private JButton buttonDeclineDeletion = new JButton("No");
    private JLabel labelDeletionMessage = new JLabel("Do you wish to delete this account?", SwingConstants.CENTER);

    private JLabel labelDeletionSuccessful = new JLabel("Account deleted succesfully!", SwingConstants.CENTER);
    private JLabel labelDeletionFailed = new JLabel("Account deletion failed. Please try again", SwingConstants.CENTER);

    // nr, userType, lastName, firstName, username, userID

    // DELETE ACCOUNT FUNCTION


    // CHANGE PROFESSOR COURSE

    private JLabel labelChangeCourseProfessor = new JLabel("CHANGE A COURSE'S PROFESSOR", SwingConstants.CENTER);

    private JComboBox comboBoxCourses = new JComboBox();
    private JComboBox comboBoxProfessors = new JComboBox();

    private JLabel labelSelectACourse = new JLabel("Select a course:", SwingConstants.CENTER);
    private JLabel labelCourse = new JLabel("Course:");
    private JLabel labelSelectAProfessor = new JLabel("Select a professor:", SwingConstants.CENTER);

    private JLabel labelSelectedCourse = new JLabel("Selected course:");
    private JLabel labelCurrentProfessor = new JLabel("Current professor:");
    private JLabel labelNewProfessor = new JLabel("New professor:");

    private JTextField textFieldSelectedCourse = new JTextField();
    private JTextField textFieldCurrentProfessor = new JTextField();
    private JTextField textFieldNewProfessor = new JTextField();

    private JButton buttonChangeProfessor = new JButton("<html><div style=\"text-align:center\">Change the selected<br>course's professor</div></html>");

    //private JFrame frameChangeProfessor = new JFrame();
    //private JButton buttonConfirmChangeProfessor = new JButton("Yes");
    //private JButton buttonDeclineChangeProfessor = new JButton("No");
    private JLabel labelChangeSuccesful = new JLabel("Professor changed successfully!", SwingConstants.CENTER);
    private JLabel labelChangeFailed = new JLabel("Something went wront. Please try again.", SwingConstants.CENTER);

    // add a local label see what happens

    // CHANGE PROFESSOR COURSE

    private List<Faculty> facultyList;
    private List<String> studyLevelList;
    private List<Specialization> specializationList;
    private List<Integer> studyYearList;
    private List<Integer> studentGroupList;
    private List<Course> courseList;
    private Course selectedCourse;
    private List<Professor> professorList;
    private Professor selectedProfessor;
    private int selectedFacultyID;


    private void initializeBoxFaculties () {

        facultyList = services.getFaculties();

        String[] faculties = new String[facultyList.size()];

        for (int i = 0; i < faculties.length; i++)
            faculties[i] = facultyList.get(i).getName();

        comboBoxFaculties = new JComboBox(faculties);

    }

    private void initializeBoxStudyLevels (int facultyID) {

        studyLevelList = services.getStudyLevels(facultyID);

        String[] studyLevelArray = new String[studyLevelList.size()];

        for (int i = 0; i < studyLevelArray.length; i++) studyLevelArray[i] = studyLevelList.get(i);


        comboBoxStudyLevel = new JComboBox(studyLevelArray);

    }

    private void initializeBoxSpecializations (int facultyID, String studyLevel) {

        specializationList = services.getSpecializations(facultyID, studyLevel);

        String[] specializationArray = new String[specializationList.size()];

        for (int i = 0; i< specializationArray.length; i++) specializationArray[i] = specializationList.get(i).getName();

        comboBoxSpecializations = new JComboBox(specializationArray);

    }

    private void initializeBoxStudyYears (int specializationID) {

        studyYearList = services.getStudyYears(specializationID);

        String[] studyYearsArray = new String[studyYearList.size()];

        for (int i = 0; i < studyYearsArray.length; i++) studyYearsArray[i] = String.valueOf(studyYearList.get(i));

        comboBoxStudyYear = new JComboBox(studyYearsArray);

    }

    private void initializeStudentGroups (int specializationID, int studyYear) {

        studentGroupList = services.getStudentGroups(specializationID, studyYear);

        String[] studentGroupsArray = new String[studentGroupList.size()];

        for (int i = 0; i < studentGroupsArray.length; i++) studentGroupsArray[i] = String.valueOf(studentGroupList.get(i));

        comboBoxStudentGroups = new JComboBox(studentGroupsArray);

    }

    private void initializeAccountDataForDeletions () {

        List<String> accountDataList = services.getAccountsDataForDeletion(username);
        String[] accountDataArray = new String[accountDataList.size()];

        for (int i = 0; i < accountDataArray.length; i++) {

            accountDataArray[i] = "" + (i+1) + ", " + accountDataList.get(i);

        }

        comboBoxAccounts = new JComboBox(accountDataArray);

    }

    private void getSelectionforDeletion () {

        String selection = (String) comboBoxAccounts.getSelectedItem();
        String[] selectionArray = selection.split(", ");
        String[] fullNameArray = selectionArray[1].split(" ");
        String lastName = fullNameArray[0];
        String firstName = fullNameArray[1];
        String selectionUsername = selectionArray[2];
        String userType = selectionArray[3];
        String[] userIdArray = selectionArray[4].split(" = ");
        int selectionUserID = Integer.parseInt(userIdArray[1]);

        textFieldUserType.setText(userType);
        textFieldLastName.setText(lastName);
        textFieldFirstName.setText(firstName);
        textFieldUsername.setText(selectionUsername);
        textFieldUserID.setText(String.valueOf(selectionUserID));

    }

    private void initializeBoxCourses (int specializationID) {

        courseList = services.getSpecializationCourses(specializationID);

        String[] courseArray = new String[courseList.size()];

        for (int i = 0; i < courseArray.length; i++) courseArray[i] = courseList.get(i).getName();

        comboBoxCourses = new JComboBox(courseArray);

    }

    private void initializeBoxProfessors (int facultyID) {

        professorList = services.getProfessors(facultyID);

        String[] professorArray = new String[professorList.size()];

        for (int i = 0; i < professorArray.length; i++)
            professorArray[i] = "" + (i+1) + ", " + professorList.get(i).getLastName() + " " + professorList.get(i).getFirstName() + ", Expertise: " +
                    professorList.get(i).getExpertise() + ", ProfID = " + professorList.get(i).getId();

        comboBoxProfessors = new JComboBox(professorArray);

    }

    private void comboBoxCoursesAddActionListener () {

        rightPanel.remove(comboBoxCourses);
        rightPanel.remove(comboBoxProfessors);
        rightPanel.remove(labelChangeSuccesful);
        rightPanel.remove(labelChangeFailed);
        rightPanel.repaint();

        String specializationName = (String) comboBoxSpecializations.getSelectedItem();
        int specializationID = services.getSpecializationID(selectedFacultyID, specializationName);

        initializeBoxCourses(specializationID);
        rightPanel.add(comboBoxCourses);
        comboBoxCourses.setBounds(150, 350, 200, 50);

        comboBoxProfessors = new JComboBox();
        rightPanel.add(comboBoxProfessors);
        comboBoxProfessors.setBounds(400, 200, 500, 50);

        textFieldSelectedCourse.setText("");
        textFieldCurrentProfessor.setText("");
        textFieldNewProfessor.setText("");

        rightPanel.revalidate();

        comboBoxCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelChangeSuccesful);
                rightPanel.remove(labelChangeFailed);
                rightPanel.remove(comboBoxProfessors);
                rightPanel.repaint();

                String selectedCourseString = (String)comboBoxCourses.getSelectedItem();

                for (int i = 0; i < courseList.size(); i++) {

                    if (selectedCourseString.equals(courseList.get(i).getName())) {

                        selectedCourse = courseList.get(i);
                        break;

                    }
                }

                textFieldSelectedCourse.setText(selectedCourse.getName());
                textFieldCurrentProfessor.setText(selectedCourse.getProfessor().getLastName() + " " + selectedCourse.getProfessor().getFirstName());
                textFieldNewProfessor.setText("");

                initializeBoxProfessors(selectedFacultyID);
                rightPanel.add(comboBoxProfessors);
                comboBoxProfessors.setBounds(400, 200, 500, 50);

                String selectedProfessorString = (String) comboBoxProfessors.getSelectedItem();
                String[] selectedProfessorArray = selectedProfessorString.split(", ");
                int selectedProfessorNr = Integer.parseInt(selectedProfessorArray[0]) - 1;

                selectedProfessor = professorList.get(selectedProfessorNr);

                textFieldNewProfessor.setText(selectedProfessor.getLastName() + " " + selectedProfessor.getFirstName());


                rightPanel.revalidate();

                comboBoxProfessors.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(labelChangeSuccesful);
                        rightPanel.remove(labelChangeFailed);
                        rightPanel.repaint();

                        String selectedProfessorString2 = (String) comboBoxProfessors.getSelectedItem();
                        String[] selectedProfessorArray2 = selectedProfessorString2.split(", ");
                        int selectedProfessorNr2 = Integer.parseInt(selectedProfessorArray2[0]) - 1;

                        selectedProfessor = professorList.get(selectedProfessorNr2);

                        textFieldNewProfessor.setText(selectedProfessor.getLastName() + " " + selectedProfessor.getFirstName());


                    }
                });
            }
        });

    }

    private void actions() {

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
                rightPanel.revalidate();
                rightPanel.repaint();

                String newPass1 = textFieldNewPassword1.getText();
                String newPass2 = textFieldNewPassword2.getText();

                boolean currentPassword = services.checkPassword(userID, textFieldCurrentPassword.getText());
                boolean newPasswordFields = (!newPass1.isBlank()) && (!newPass2.isBlank());
                boolean newPasswordMatch = newPass1.equals(newPass2);


                if (currentPassword) {

                    if (newPasswordFields) {

                        if (newPasswordMatch) {

                            if (services.changePassword(userID, textFieldNewPassword1.getText())) {

                                rightPanel.add(labelPasswordChangedSuccessfully);
                                labelPasswordChangedSuccessfully.setBounds(300, 450, 300, 50);
                                labelPasswordChangedSuccessfully.setForeground(Color.GREEN);

                            } else {

                                rightPanel.add(labelPasswordChangeError);
                                labelPasswordChangeError.setBounds(300, 450, 300, 50);
                                labelPasswordChangeError.setForeground(Color.RED);

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



        buttonCreateNewAccounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                rightPanel.add(labelCreateAccount);
                labelCreateAccount.setBounds(0, 0, 1000, 50);
                labelCreateAccount.setBackground(new Color(187, 187, 187));
                labelCreateAccount.setOpaque(true);

                rightPanel.add(labelAccountType);
                rightPanel.add(radioButtonAdmin);
                rightPanel.add(radioButtonProfessor);
                rightPanel.add(radioButtonStudent);

                labelAccountType.setBounds(300, 60, 100, 50);
                radioButtonAdmin.setBounds(400, 60, 100, 50);
                radioButtonProfessor.setBounds(500, 60, 100, 50);
                radioButtonStudent.setBounds(600, 60, 100, 50);

                buttonGroup1.clearSelection();
                radioButtonAdmin.setSelected(true);

                rightPanel.add(labelUsername);
                rightPanel.add(labelPassword);
                rightPanel.add(labelLastName);
                rightPanel.add(labelFirstName);

                rightPanel.add(textFieldUsername);
                rightPanel.add(textFieldPassword);
                rightPanel.add(textFieldLastName);
                rightPanel.add(textFieldFirstName);


                labelUsername.setBounds(10, 150, 70, 50);
                labelPassword.setBounds(10, 200, 70, 50);
                labelLastName.setBounds(10, 250, 70, 50);
                labelFirstName.setBounds(10, 300, 70, 50);

                textFieldUsername.setBounds(80, 150, 170, 50);
                textFieldPassword.setBounds(80, 200, 170, 50);
                textFieldLastName.setBounds(80, 250, 170, 50);
                textFieldFirstName.setBounds(80, 300, 170, 50);


                textFieldUsername.setEditable(true);
                textFieldLastName.setEditable(true);
                textFieldFirstName.setEditable(true);

                textFieldUsername.setBackground(Color.WHITE);
                textFieldLastName.setBackground(Color.WHITE);
                textFieldFirstName.setBackground(Color.WHITE);

                textFieldUsername.setText("");
                textFieldPassword.setText("");
                textFieldLastName.setText("");
                textFieldFirstName.setText("");
                textFieldExpertise.setText("");
                textFieldBirthDate.setText("");
                textFieldEmail.setText("");
                textFieldPhone.setText("");


                rightPanel.add(buttonCreateAccount);
                buttonCreateAccount.setBounds(300, 500, 400, 100);

            }
        });

        radioButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // remove account creation labels
                rightPanel.remove(labelAccountCreated);
                rightPanel.remove(labelFillAllFields);
                rightPanel.remove(labelAccountNotCreated);
                rightPanel.remove(labelUnavailableUsername);
                rightPanel.remove(labelWrongDateFormat);

                // remove professor items
                rightPanel.remove(labelExpertise);
                rightPanel.remove(textFieldExpertise);

                // remove student items
                rightPanel.remove(labelBirthDate);
                rightPanel.remove(textFieldBirthDate);
                rightPanel.remove(labelEmail);
                rightPanel.remove(textFieldEmail);
                rightPanel.remove(labelPhone);
                rightPanel.remove(textFieldPhone);
                rightPanel.remove(labelFaculty);
                rightPanel.remove(labelStudyLevel);
                rightPanel.remove(labelSpecialization);
                rightPanel.remove(labelStudyYear);
                rightPanel.remove(labelStudentGroup);
                rightPanel.remove(comboBoxFaculties);
                rightPanel.remove(comboBoxStudyLevel);
                rightPanel.remove(comboBoxSpecializations);
                rightPanel.remove(comboBoxStudyYear);
                rightPanel.remove(comboBoxStudentGroups);

                rightPanel.repaint();
                rightPanel.revalidate();

            }
        });

        radioButtonProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // remove account creation labels
                rightPanel.remove(labelAccountCreated);
                rightPanel.remove(labelFillAllFields);
                rightPanel.remove(labelAccountNotCreated);
                rightPanel.remove(labelUnavailableUsername);
                rightPanel.remove(labelWrongDateFormat);

                // remove professor items
                rightPanel.remove(labelExpertise);
                rightPanel.remove(textFieldExpertise);

                // remove student items
                rightPanel.remove(labelBirthDate);
                rightPanel.remove(textFieldBirthDate);
                rightPanel.remove(labelEmail);
                rightPanel.remove(textFieldEmail);
                rightPanel.remove(labelPhone);
                rightPanel.remove(textFieldPhone);
                rightPanel.remove(labelFaculty);
                rightPanel.remove(labelStudyLevel);
                rightPanel.remove(labelSpecialization);
                rightPanel.remove(labelStudyYear);
                rightPanel.remove(labelStudentGroup);
                rightPanel.remove(comboBoxFaculties);
                rightPanel.remove(comboBoxStudyLevel);
                rightPanel.remove(comboBoxSpecializations);
                rightPanel.remove(comboBoxStudyYear);
                rightPanel.remove(comboBoxStudentGroups);

                rightPanel.repaint();


                rightPanel.add(labelBirthDate);
                rightPanel.add(textFieldBirthDate);

                labelBirthDate.setBounds(300, 150, 70, 50);
                textFieldBirthDate.setBounds(370, 150, 170, 50);
                textFieldBirthDate.setToolTipText("Format: YYYY-MM-DD");

                rightPanel.add(labelExpertise);
                rightPanel.add(textFieldExpertise);

                labelExpertise.setBounds(300, 200, 70, 50);
                textFieldExpertise.setBounds(370, 200, 170, 50);

                initializeBoxFaculties();

                rightPanel.add(labelFaculty);
                rightPanel.add(comboBoxFaculties);
                labelFaculty.setBounds(600, 150, 100, 50);
                comboBoxFaculties.setBounds(700, 150, 200, 50);

                rightPanel.revalidate();
            }
        });

        radioButtonStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // remove account creation labels
                rightPanel.remove(labelAccountCreated);
                rightPanel.remove(labelFillAllFields);
                rightPanel.remove(labelAccountNotCreated);
                rightPanel.remove(labelUnavailableUsername);
                rightPanel.remove(labelWrongDateFormat);

                // remove professor items
                rightPanel.remove(labelExpertise);
                rightPanel.remove(textFieldExpertise);

                // remove student items
                rightPanel.remove(labelBirthDate);
                rightPanel.remove(textFieldBirthDate);
                rightPanel.remove(labelEmail);
                rightPanel.remove(textFieldEmail);
                rightPanel.remove(labelPhone);
                rightPanel.remove(textFieldPhone);
                rightPanel.remove(labelFaculty);
                rightPanel.remove(labelStudyLevel);
                rightPanel.remove(labelSpecialization);
                rightPanel.remove(labelStudyYear);
                rightPanel.remove(labelStudentGroup);
                rightPanel.remove(comboBoxFaculties);
                rightPanel.remove(comboBoxStudyLevel);
                rightPanel.remove(comboBoxSpecializations);
                rightPanel.remove(comboBoxStudyYear);
                rightPanel.remove(comboBoxStudentGroups);

                rightPanel.repaint();

                rightPanel.add(labelBirthDate);
                rightPanel.add(textFieldBirthDate);
                rightPanel.add(labelEmail);
                rightPanel.add(textFieldEmail);
                rightPanel.add(labelPhone);
                rightPanel.add(textFieldPhone);

                labelBirthDate.setBounds(300, 150, 70, 50);
                textFieldBirthDate.setBounds(370, 150, 170, 50);
                textFieldBirthDate.setToolTipText("Ex: 1997-05-28");

                labelEmail.setBounds(300, 200, 70, 50);
                textFieldEmail.setBounds(370, 200, 170, 50);

                labelPhone.setBounds(300, 250, 70, 50);
                textFieldPhone.setBounds(370, 250, 170, 50);

                rightPanel.add(labelFaculty);
                rightPanel.add(labelStudyLevel);
                rightPanel.add(labelSpecialization);
                rightPanel.add(labelStudyYear);
                rightPanel.add(labelStudentGroup);

                labelFaculty.setBounds(600, 150, 100, 50);
                labelStudyLevel.setBounds(600, 200, 100, 50);
                labelSpecialization.setBounds(600, 250, 100, 50);
                labelStudyYear.setBounds(600, 300, 100, 50);
                labelStudentGroup.setBounds(600, 350, 100, 50);

                initializeBoxFaculties();
                comboBoxStudyLevel = new JComboBox();
                comboBoxSpecializations = new JComboBox();
                comboBoxStudyYear = new JComboBox();
                comboBoxStudentGroups = new JComboBox();

                rightPanel.add(comboBoxFaculties);
                rightPanel.add(comboBoxStudyLevel);
                rightPanel.add(comboBoxSpecializations);
                rightPanel.add(comboBoxStudyYear);
                rightPanel.add(comboBoxStudentGroups);

                comboBoxFaculties.setBounds(700, 150, 200, 50);
                comboBoxStudyLevel.setBounds(700, 200, 200, 50);
                comboBoxSpecializations.setBounds(700,250,200,50);
                comboBoxStudyYear.setBounds(700,300,200,50);
                comboBoxStudentGroups.setBounds(700, 350, 200, 50);

                rightPanel.revalidate();



                comboBoxFaculties.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(comboBoxStudentGroups);
                        rightPanel.remove(comboBoxStudyYear);
                        rightPanel.remove(comboBoxSpecializations);
                        rightPanel.remove(comboBoxStudyLevel);

                        rightPanel.repaint();

                        comboBoxStudentGroups = new JComboBox();
                        comboBoxStudyYear = new JComboBox();
                        comboBoxSpecializations = new JComboBox();

                        rightPanel.add(comboBoxStudentGroups);
                        rightPanel.add(comboBoxStudyYear);
                        rightPanel.add(comboBoxSpecializations);

                        comboBoxSpecializations.setBounds(700,250,200,50);
                        comboBoxStudyYear.setBounds(700,300,200,50);
                        comboBoxStudentGroups.setBounds(700, 350, 200, 50);


                        String facultyName = (String)comboBoxFaculties.getSelectedItem();

                        int facultyID = services.getFacultyID(facultyName);

                        initializeBoxStudyLevels(facultyID);
                        rightPanel.add(comboBoxStudyLevel);
                        comboBoxStudyLevel.setBounds(700, 200, 200, 50);

                        rightPanel.revalidate();


                        comboBoxStudyLevel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.remove(comboBoxStudentGroups);
                                rightPanel.remove(comboBoxStudyYear);
                                rightPanel.remove(comboBoxSpecializations);

                                rightPanel.repaint();

                                comboBoxStudentGroups = new JComboBox();
                                comboBoxStudyYear = new JComboBox();

                                rightPanel.add(comboBoxStudentGroups);
                                rightPanel.add(comboBoxStudyYear);

                                comboBoxStudyYear.setBounds(700,300,200,50);
                                comboBoxStudentGroups.setBounds(700, 350, 200, 50);

                                String studyLevel = (String)comboBoxStudyLevel.getSelectedItem();

                                initializeBoxSpecializations(facultyID, studyLevel);
                                rightPanel.add(comboBoxSpecializations);
                                comboBoxSpecializations.setBounds(700,250,200,50);

                                rightPanel.revalidate();

                                comboBoxSpecializations.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        rightPanel.remove(comboBoxStudentGroups);
                                        rightPanel.remove(comboBoxStudyYear);

                                        rightPanel.repaint();

                                        comboBoxStudentGroups = new JComboBox();

                                        rightPanel.add(comboBoxStudentGroups);

                                        comboBoxStudentGroups.setBounds(700, 350, 200, 50);

                                        String specializationName = (String)comboBoxSpecializations.getSelectedItem();

                                        int specializationID = services.getSpecializationID(facultyID, specializationName);

                                        initializeBoxStudyYears(specializationID);
                                        rightPanel.add(comboBoxStudyYear);
                                        comboBoxStudyYear.setBounds(700,300,200,50);

                                        rightPanel.revalidate();

                                        comboBoxStudyYear.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                rightPanel.remove(comboBoxStudentGroups);

                                                rightPanel.repaint();

                                                String selection = (String)comboBoxStudyYear.getSelectedItem();
                                                int studyYear = Integer.parseInt(selection);

                                                initializeStudentGroups(specializationID, studyYear);
                                                rightPanel.add(comboBoxStudentGroups);
                                                comboBoxStudentGroups.setBounds(700, 350, 200, 50);

                                                rightPanel.revalidate();

                                            }
                                        });

                                    }
                                });
                            }
                        });

                    }
                });

            }
        });

        buttonCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // CHECK THAT ALL FIELDS ARE FILLED

                rightPanel.remove(labelAccountCreated);
                rightPanel.remove(labelFillAllFields);
                rightPanel.remove(labelAccountNotCreated);
                rightPanel.remove(labelUnavailableUsername);
                rightPanel.remove(labelWrongDateFormat);


                rightPanel.revalidate();
                rightPanel.repaint();


                String username = textFieldUsername.getText();
                String password = textFieldPassword.getText();
                String lastName = textFieldLastName.getText();
                String firstName = textFieldFirstName.getText();


                if (radioButtonAdmin.isSelected()) {

                    String userType = "ADMIN";

                    boolean condition = true;

                    if (username.isBlank()) condition = false;
                    else if (password.isBlank()) condition = false;
                    else if (lastName.isBlank()) condition = false;
                    else if (firstName.isBlank()) condition = false;

                    if (condition) {

                        if (services.checkUsernameAvailability(username)) {

                            if (services.addAdmin(username, password, userType, lastName, firstName)) {

                                rightPanel.add(labelAccountCreated);
                                labelAccountCreated.setBounds(300, 600, 400, 100);
                                labelAccountCreated.setForeground(Color.GREEN);

                            } else {

                                rightPanel.add(labelAccountNotCreated);
                                labelAccountNotCreated.setBounds(300, 600, 400, 100);
                                labelAccountNotCreated.setForeground(Color.RED);

                            }

                        } else {

                            rightPanel.add(labelUnavailableUsername);
                            labelUnavailableUsername.setBounds(300, 600, 400, 100);
                            labelUnavailableUsername.setForeground(Color.RED);

                        }

                    } else {

                        rightPanel.add(labelFillAllFields);
                        labelFillAllFields.setBounds(300, 600, 400, 100);
                        labelFillAllFields.setForeground(Color.RED);

                    }



                } else if (radioButtonProfessor.isSelected()) {

                    String userType = "PROFESSOR";
                    String birthDate = textFieldBirthDate.getText();
                    String expertise = textFieldExpertise.getText();
                    String facultyName = (String) comboBoxFaculties.getSelectedItem();
                    Integer facultyID = services.getFacultyID(facultyName);


                    boolean condition = true;

                    if (username.isBlank()) condition = false;
                    else if (password.isBlank()) condition = false;
                    else if (lastName.isBlank()) condition = false;
                    else if (firstName.isBlank()) condition = false;
                    else if (birthDate.isBlank()) condition = false;
                    else if (expertise.isBlank()) condition = false;
                    else if (Objects.isNull(facultyID)) condition = false;


                    if (condition) {

                        if (services.verifyDate(birthDate)) {

                            if (services.checkUsernameAvailability(username)) {

                                if (services.addProfessor(username, password, userType, lastName, firstName, birthDate, facultyID, expertise)) {

                                    rightPanel.add(labelAccountCreated);
                                    labelAccountCreated.setBounds(300, 600, 400, 100);
                                    labelAccountCreated.setForeground(Color.GREEN);

                                } else {

                                    rightPanel.add(labelAccountNotCreated);
                                    labelAccountNotCreated.setBounds(300, 600, 400, 100);
                                    labelAccountNotCreated.setForeground(Color.RED);

                                }

                            } else {

                                rightPanel.add(labelUnavailableUsername);
                                labelUnavailableUsername.setBounds(300, 600, 400, 100);
                                labelUnavailableUsername.setForeground(Color.RED);

                            }

                        } else {

                            rightPanel.add(labelWrongDateFormat);
                            labelWrongDateFormat.setBounds(300, 600, 400, 100);
                            labelWrongDateFormat.setForeground(Color.RED);

                        }

                    } else {

                        rightPanel.add(labelFillAllFields);
                        labelFillAllFields.setBounds(300, 600, 400, 100);
                        labelFillAllFields.setForeground(Color.RED);

                    }


                } else if (radioButtonStudent.isSelected()) {

                    String userType = "STUDENT";
                    String birthDate = textFieldBirthDate.getText();
                    String email = textFieldEmail.getText();
                    String phone = textFieldPhone.getText();
                    String selectedGroupID = null;
                    Integer groupID = null;

                    if (Objects.isNull(comboBoxStudentGroups.getSelectedItem())) {

                        groupID = null;


                    } else {

                        selectedGroupID = (String) comboBoxStudentGroups.getSelectedItem();
                        groupID = Integer.parseInt(selectedGroupID);

                    }


                    boolean condition = true;

                    if (username.isBlank()) condition = false;
                    else if (password.isBlank()) condition = false;
                    else if (lastName.isBlank()) condition = false;
                    else if (firstName.isBlank()) condition = false;
                    else if (birthDate.isBlank()) condition = false;
                    else if (email.isBlank()) condition = false;
                    else if (phone.isBlank()) condition = false;
                    else if (Objects.isNull(groupID)) condition = false;



                    if (condition) {

                        if (services.verifyDate(birthDate)) {

                            if (services.checkUsernameAvailability(username)) {

                                if (services.addStudent(username, password, userType, lastName, firstName, birthDate, email, phone, groupID)) {

                                    rightPanel.add(labelAccountCreated);
                                    labelAccountCreated.setBounds(300, 600, 400, 100);
                                    labelAccountCreated.setForeground(Color.GREEN);

                                } else {

                                    rightPanel.add(labelAccountNotCreated);
                                    labelAccountNotCreated.setBounds(300, 600, 400, 100);
                                    labelAccountNotCreated.setForeground(Color.RED);

                                }

                            } else {

                                rightPanel.add(labelUnavailableUsername);
                                labelUnavailableUsername.setBounds(300, 600, 400, 100);
                                labelUnavailableUsername.setForeground(Color.RED);

                            }

                        } else {

                            rightPanel.add(labelWrongDateFormat);
                            labelWrongDateFormat.setBounds(300, 600, 400, 100);
                            labelWrongDateFormat.setForeground(Color.RED);

                        }

                    } else {

                        rightPanel.add(labelFillAllFields);
                        labelFillAllFields.setBounds(300, 600, 400, 100);
                        labelFillAllFields.setForeground(Color.RED);

                    }

                }

            }
        });



        buttonDeleteAccounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                rightPanel.add(labelChooseAccountToDelete);
                labelChooseAccountToDelete.setBounds(0, 0, 1000, 50);
                labelChooseAccountToDelete.setBackground(Color.lightGray);
                labelChooseAccountToDelete.setOpaque(true);

                initializeAccountDataForDeletions();
                rightPanel.add(comboBoxAccounts);
                comboBoxAccounts.setBounds(400, 150, 550, 50);

                rightPanel.add(labelUserType);
                rightPanel.add(labelLastName);
                rightPanel.add(labelFirstName);
                rightPanel.add(labelUsername);
                rightPanel.add(labelUserID);

                labelUserType.setBounds(50, 150, 100, 50);
                labelLastName.setBounds(50, 210, 100, 50);
                labelFirstName.setBounds(50, 270, 100, 50);
                labelUsername.setBounds(50, 330, 100, 50);
                labelUserID.setBounds(50, 390, 100, 50);

                rightPanel.add(textFieldUserType);
                rightPanel.add(textFieldLastName);
                rightPanel.add(textFieldFirstName);
                rightPanel.add(textFieldUsername);
                rightPanel.add(textFieldUserID);

                textFieldUserType.setBounds(150, 150, 200, 50);
                textFieldLastName.setBounds(150, 210, 200, 50);
                textFieldFirstName.setBounds(150, 270, 200, 50);
                textFieldUsername.setBounds(150, 330, 200, 50);
                textFieldUserID.setBounds(150, 390, 200, 50);

                textFieldUserType.setEditable(false);
                textFieldLastName.setEditable(false);
                textFieldFirstName.setEditable(false);
                textFieldUsername.setEditable(false);
                textFieldUserID.setEditable(false);

                textFieldUserType.setBackground(Color.LIGHT_GRAY);
                textFieldLastName.setBackground(Color.LIGHT_GRAY);
                textFieldFirstName.setBackground(Color.LIGHT_GRAY);
                textFieldUsername.setBackground(Color.LIGHT_GRAY);
                textFieldUserID.setBackground(Color.LIGHT_GRAY);

                getSelectionforDeletion();

                rightPanel.add(buttonDeleteAnAccount);
                buttonDeleteAnAccount.setBounds(50, 500, 300, 150);

                comboBoxAccounts.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        getSelectionforDeletion();

                    }
                });


            }
        });

        buttonDeleteAnAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelDeletionSuccessful);
                rightPanel.remove(labelDeletionFailed);
                rightPanel.revalidate();
                rightPanel.repaint();

                frameConfirmDeletion.setTitle("");
                frameConfirmDeletion.setSize(300,200);
                frameConfirmDeletion.setLayout(null);
                frameConfirmDeletion.setVisible(true);
                frameConfirmDeletion.getContentPane().setBackground(new Color(255, 255, 255));
                frameConfirmDeletion.setResizable(false);
                frameConfirmDeletion.setLocationRelativeTo(null);
                frameConfirmDeletion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameConfirmDeletion.add(labelDeletionMessage);
                frameConfirmDeletion.add(buttonConfirmDeletion);
                frameConfirmDeletion.add(buttonDeclineDeletion);

                labelDeletionMessage.setBounds(0, 25, 300, 50);
                buttonConfirmDeletion.setBounds(25, 100, 100, 50);
                buttonDeclineDeletion.setBounds(175, 100, 100, 50);

            }
        });

        buttonConfirmDeletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String selection = (String) comboBoxAccounts.getSelectedItem();
                String[] selectionArray = selection.split(", ");
                String table = selectionArray[3] + "S";
                String[] userIdArray = selectionArray[4].split(" = ");
                int selectionUserID = Integer.parseInt(userIdArray[1]);

                if (services.deleteAccount(selectionUserID, table)) {

                    frameConfirmDeletion.dispose();

                    rightPanel.add(labelDeletionSuccessful);
                    labelDeletionSuccessful.setBounds(50, 650, 300, 50);
                    labelDeletionSuccessful.setForeground(Color.GREEN);
                    rightPanel.repaint();


                } else {

                    frameConfirmDeletion.dispose();

                    rightPanel.add(labelDeletionFailed);
                    labelDeletionFailed.setBounds(50, 650, 300, 50);
                    labelDeletionFailed.setForeground(Color.RED);
                    rightPanel.repaint();

                }

                rightPanel.remove(comboBoxAccounts);
                initializeAccountDataForDeletions();
                rightPanel.add(comboBoxAccounts);
                comboBoxAccounts.setBounds(400, 150, 550, 50);
                getSelectionforDeletion();


                comboBoxAccounts.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(labelDeletionSuccessful);
                        rightPanel.remove(labelDeletionFailed);
                        rightPanel.revalidate();
                        rightPanel.repaint();

                        getSelectionforDeletion();

                    }
                });

                rightPanel.revalidate();
            }
        });

        buttonDeclineDeletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameConfirmDeletion.dispose();

            }
        });



        buttonChangeProfessorCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                rightPanel.add(labelChangeCourseProfessor);
                labelChangeCourseProfessor.setBounds(0, 0, 1000, 50);
                labelChangeCourseProfessor.setBackground(Color.lightGray);
                labelChangeCourseProfessor.setOpaque(true);

                rightPanel.add(labelSelectACourse);
                rightPanel.add(labelFaculty);
                rightPanel.add(labelStudyLevel);
                rightPanel.add(labelSpecialization);
                rightPanel.add(labelCourse);

                labelSelectACourse.setBounds(150, 150, 200, 50);
                labelFaculty.setBounds(50, 200, 100, 50);
                labelStudyLevel.setBounds(50, 250, 100, 50);
                labelSpecialization.setBounds(50, 300, 100, 50);
                labelCourse.setBounds(50, 350, 100, 50);


                initializeBoxFaculties();
                comboBoxStudyLevel = new JComboBox();
                comboBoxSpecializations = new JComboBox();
                comboBoxCourses = new JComboBox();

                rightPanel.add(comboBoxFaculties);
                rightPanel.add(comboBoxStudyLevel);
                rightPanel.add(comboBoxSpecializations);
                rightPanel.add(comboBoxCourses);

                comboBoxFaculties.setBounds(150, 200, 200, 50);
                comboBoxStudyLevel.setBounds(150, 250, 200, 50);
                comboBoxSpecializations.setBounds(150,300,200,50);
                comboBoxCourses.setBounds(150, 350, 200, 50);

                rightPanel.add(labelSelectAProfessor);
                labelSelectAProfessor.setBounds(400, 150, 500, 50);

                comboBoxProfessors = new JComboBox();
                rightPanel.add(comboBoxProfessors);
                comboBoxProfessors.setBounds(400, 200, 500, 50);

                rightPanel.add(labelSelectedCourse);
                rightPanel.add(labelCurrentProfessor);
                rightPanel.add(labelNewProfessor);
                rightPanel.add(textFieldSelectedCourse);
                rightPanel.add(textFieldCurrentProfessor);
                rightPanel.add(textFieldNewProfessor);

                labelSelectedCourse.setBounds(225, 475, 110, 50);
                labelCurrentProfessor.setBounds(225, 525, 110, 50);
                labelNewProfessor.setBounds(225, 575, 110, 50);

                textFieldSelectedCourse.setBounds(335, 475, 200, 50);
                textFieldCurrentProfessor.setBounds(335, 525, 200, 50);
                textFieldNewProfessor.setBounds(335, 575, 200, 50);

                textFieldSelectedCourse.setText("");
                textFieldCurrentProfessor.setText("");
                textFieldNewProfessor.setText("");

                textFieldSelectedCourse.setEditable(false);
                textFieldCurrentProfessor.setEditable(false);
                textFieldNewProfessor.setEditable(false);

                textFieldSelectedCourse.setBackground(Color.lightGray);
                textFieldCurrentProfessor.setBackground(Color.lightGray);
                textFieldNewProfessor.setBackground(Color.lightGray);


                rightPanel.add(buttonChangeProfessor);
                buttonChangeProfessor.setBounds(575, 475, 200, 150);

                rightPanel.revalidate();


                comboBoxFaculties.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(comboBoxStudyLevel);
                        rightPanel.remove(comboBoxSpecializations);
                        rightPanel.remove(comboBoxCourses);
                        rightPanel.remove(comboBoxProfessors);
                        rightPanel.remove(labelChangeSuccesful);
                        rightPanel.remove(labelChangeFailed);

                        rightPanel.repaint();

                        comboBoxSpecializations = new JComboBox();
                        comboBoxCourses = new JComboBox();
                        comboBoxProfessors = new JComboBox();

                        rightPanel.add(comboBoxSpecializations);
                        rightPanel.add(comboBoxCourses);
                        rightPanel.add(comboBoxProfessors);

                        comboBoxSpecializations.setBounds(150,300,200,50);
                        comboBoxCourses.setBounds(150,350,200,50);
                        comboBoxProfessors.setBounds(400, 200, 500, 50);

                        String facultyName = (String)comboBoxFaculties.getSelectedItem();

                        selectedFacultyID = services.getFacultyID(facultyName);

                        initializeBoxStudyLevels(selectedFacultyID);
                        rightPanel.add(comboBoxStudyLevel);
                        comboBoxStudyLevel.setBounds(150, 250, 200, 50);

                        textFieldSelectedCourse.setText("");
                        textFieldCurrentProfessor.setText("");
                        textFieldNewProfessor.setText("");

                        rightPanel.revalidate();

                        comboBoxStudyLevel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.remove(comboBoxSpecializations);
                                rightPanel.remove(comboBoxCourses);
                                rightPanel.remove(comboBoxProfessors);
                                rightPanel.remove(labelChangeSuccesful);
                                rightPanel.remove(labelChangeFailed);

                                rightPanel.repaint();

                                comboBoxCourses = new JComboBox();
                                comboBoxProfessors = new JComboBox();

                                rightPanel.add(comboBoxCourses);
                                rightPanel.add(comboBoxProfessors);

                                comboBoxCourses.setBounds(150,350,200,50);
                                comboBoxProfessors.setBounds(400, 200, 500, 50);

                                String studyLevel = (String)comboBoxStudyLevel.getSelectedItem();

                                initializeBoxSpecializations(selectedFacultyID, studyLevel);
                                rightPanel.add(comboBoxSpecializations);
                                comboBoxSpecializations.setBounds(150,300,200,50);

                                textFieldSelectedCourse.setText("");
                                textFieldCurrentProfessor.setText("");
                                textFieldNewProfessor.setText("");

                                rightPanel.revalidate();

                                comboBoxSpecializations.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        comboBoxCoursesAddActionListener();

                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        buttonChangeProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(labelChangeSuccesful);
                rightPanel.remove(labelChangeFailed);
                rightPanel.repaint();

                if (services.changeCourseProfessor(selectedCourse.getId(), selectedProfessor.getId())) {

                    int comboBoxCourseIndex = comboBoxCourses.getSelectedIndex();

                    comboBoxCoursesAddActionListener();

                    comboBoxCourses.setSelectedIndex(comboBoxCourseIndex);

                    textFieldNewProfessor.setText("");

                    rightPanel.add(labelChangeSuccesful);
                    labelChangeSuccesful.setBounds(225, 625, 550, 50);
                    labelChangeSuccesful.setForeground(Color.GREEN);



                } else {

                    rightPanel.add(labelChangeFailed);
                    labelChangeFailed.setBounds(225, 625, 550, 50);
                    labelChangeFailed.setForeground(Color.RED);

                }

            }
        });


    }


    public AdminScreen (String username, Services services) {

        this.services = services;
        this.username = username;
        this.adminName = services.getName(username);
        this.userID = services.getUserID(username);

        frame.setTitle("Admin Screen");
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

        topPanel.add(labelAdminName);
        labelAdminName.setBounds(10, 0, 200, 50);
        labelAdminName.setText("Admin: " + adminName);

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

        leftPanel.add(buttonCreateNewAccounts);
        leftPanel.add(buttonDeleteAccounts);
        leftPanel.add(buttonChangeProfessorCourse);

        buttonCreateNewAccounts.setBounds(5, 0, 90, 200);
        buttonDeleteAccounts.setBounds(5, 250, 90, 200);
        buttonChangeProfessorCourse.setBounds(5, 500, 90, 200);
        // LEFT PANEL


        // RIGHT PANEL
        frame.add(rightPanel);
        rightPanel.setBounds(100, 50, 1000, 750);
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(255, 238, 214, 255));
        // RIGHT PANEL

        buttonGroup1.add(radioButtonAdmin);
        buttonGroup1.add(radioButtonProfessor);
        buttonGroup1.add(radioButtonStudent);

        actions();

    }



    public static void main(String[] args) throws SQLException {

        Services services = new Services();
        AdminScreen adminScreen = new AdminScreen("admin", services);

    }
}
