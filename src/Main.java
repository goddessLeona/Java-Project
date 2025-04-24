import Customer.CustomerLoggedIn;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Program is now running");

     MainMenu mainMenu = new MainMenu();
     mainMenu.runMainMenu();

    }
}