package universityManagement.gui;

import universityManagement.repository.Repository;
import universityManagement.services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

public class LoginScreen extends JFrame {

    private Services services = null;

    private JFrame frame = new JFrame();

    private JLabel labelUsername = new JLabel("Username:");
    private JLabel labelPassword = new JLabel("Password:");

    private JTextField textFieldUsername = new JTextField();
    private JPasswordField textFieldPassword = new JPasswordField();

    private JButton buttonLogin = new JButton("Log in");

    private JLabel labelWrongCombination = new JLabel("" +
            "<html><div style=\"text-align:center\">" +
            "Incorrect username/password combination." +
            "</div><br>" +
            "<div style=\"text-align:center\">" +
            "Please try again." +
            "</div><html>", SwingConstants.CENTER);


    private String username = null;
    private String password = null;
    private String userType = null;


    private void actions() {

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                username = textFieldUsername.getText();
                password = String.valueOf(textFieldPassword.getPassword());

                userType = services.checkUserPassCombination(username, password);

                if (Objects.isNull(userType)) {

                    frame.add(labelWrongCombination);

                    labelWrongCombination.setBounds(10, 200, 270, 50);
                    labelWrongCombination.setForeground(Color.RED);

                } else if (userType.equals("ADMIN")) {

                    frame.dispose();
                    AdminScreen adminScreen = new AdminScreen(username, services);

                } else if (userType.equals("STUDENT")) {

                    frame.dispose();
                    StudentScreen studentScreen = new StudentScreen(username, services);

                } else if (userType.equals("PROFESSOR")) {

                    frame.dispose();
                    ProfessorScreen professorScreen = new ProfessorScreen(username, services);

                }

            }
        });

    }

    public LoginScreen (Services services) {

        this.services = services;
        frame.setTitle("Login Screen");
        frame.setSize(300, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(255, 215, 215));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(labelUsername);
        frame.add(labelPassword);

        labelUsername.setBounds(10, 10, 80, 50);
        labelPassword.setBounds(10, 60, 80, 50);

        frame.add(textFieldUsername);
        frame.add(textFieldPassword);

        textFieldUsername.setBounds(90, 10, 190, 50);
        textFieldUsername.setToolTipText("Enter your username");
        textFieldPassword.setBounds(90, 60, 190, 50);
        textFieldPassword.setToolTipText("Enter your password");

        frame.add(buttonLogin);
        buttonLogin.setBounds(10, 120, 270, 50);

        actions();

    }



    public static void main(String[] args) {

        Services services = new Services();
        LoginScreen loginScreen = new LoginScreen(services);

    }
}
