package universityManagement;

import universityManagement.gui.LoginScreen;
import universityManagement.services.Services;


public class Main {

    public static void main(String[] args) {

        Services services = new Services();

        LoginScreen loginScreen = new LoginScreen(services);



    }
}
