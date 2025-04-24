package Validation;

import java.util.Scanner;

public class Validation {

    Scanner scanner = new Scanner(System.in);

    public boolean emailValition(String email){
        return email != null && email.contains("@")
                && email.indexOf("@")>0
                && email.indexOf("@")<email.length()-1;
    }

    public boolean passwordValidation(String password){
        return password != null && password.length()>8 && password.matches(".*\\d.*");
    }

    public boolean numberValidation(String input){
        return input != null && input.matches(".*\\d.*");
    }

    public boolean NoNegativeNumberValidation(int input){
         return input >=0;
    }

    public boolean NoNegativeNumberValidation(double input){
        return input >=0;
    }

    public boolean yesNoValidation(String input){
        return input != null && (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no"));
    }
}
