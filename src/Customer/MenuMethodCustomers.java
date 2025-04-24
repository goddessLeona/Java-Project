package Customer;

import Validation.Validation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMethodCustomers {

    CustomerService methodCustomers = new CustomerService();
    Validation validations = new Validation();

    Scanner scanner = new Scanner(System.in);

    public void getAllCustomerMethod() throws SQLException {
        ArrayList<Customer> customers = methodCustomers.getAllCustomer();
        for(Customer customer: customers){
            System.out.println("Customer id: " + customer.getCustermerId());
            System.out.println("Customer name: " + customer.getName());
            System.out.println("customer email: " + customer.getEmail());
            System.out.println();
        }

    }

    public void addNewCustomerMethod() throws SQLException {

        Customer newCustomer = new Customer();

        boolean notCorrectInput = true;
        try {
            while (notCorrectInput) {
                System.out.println("Write in your name: ");
                String name = scanner.nextLine();
                newCustomer.setName(name);

                String email;
                while (true) {
                    System.out.println("Write in your email: ");
                    email = scanner.nextLine();

                    if (validations.emailValition(email)) {
                        System.out.println("Email is valid");
                        newCustomer.setEmail(email);
                        break;
                    } else {
                        System.out.println("Invalid email, please try again.");
                    }
                }

                System.out.println("Write in your phone Number: ");
                String phone = scanner.nextLine();
                newCustomer.setPhoneNummber(phone);

                System.out.println("Write in your Address: ");
                String address = scanner.nextLine();
                newCustomer.setAddress(address);

                String password;
                while (true) {
                    System.out.println("Create a password: ");
                    System.out.println("Password have to be 8 characters long & minimum one number");
                    password = scanner.nextLine();
                    if (validations.passwordValidation(password)) {
                        System.out.println("Password is ok");
                        newCustomer.setPassword(password);
                        break;
                    } else {
                        System.out.println("Invalid password, try again");
                    }

                }

                System.out.println("\n This is the information you have added: ");
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Phone nr: " + phone);
                System.out.println("Address: " + address);

                System.out.println("Are the information correct ?");
                System.out.println("yes or no");
                String response = scanner.nextLine();

                notCorrectInput = response.equalsIgnoreCase("no");
            }

            boolean success = methodCustomers.addNewCustomer(newCustomer);

            if (success) {
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("You failed to add customer");

            }
        } catch (Exception e) {
            System.out.println("Database trouble");
            throw new RuntimeException(e);
        }
    }

    public void updateEmailMethod() throws SQLException {

        Customer updateEmail = new Customer();

        // write in id to be able to change the email with that id
        int id;

        while(true){
            System.out.println("\u001B[33mWrite in your customer ID, to change your email: \u001B[0m");
            String input = scanner.nextLine();
            if(validations.numberValidation(input)){
                id = Integer.parseInt(input);
                if(methodCustomers.validateIfCustomerExist(id)){
                    updateEmail.setCustermerId(id);
                    break;
                }else{
                    System.out.println("That customer ID does not exist. Try again");
                }
            }else{
                System.out.println("Invalid input. It have to be a number.");
            }
        }


        // Write in new email and update to database
        String email;
        while(true){
            System.out.print("\u001B[33mWrite in your new email: \u001B[0m");
            email = scanner.nextLine();

            if(validations.emailValition(email)){
                System.out.println("Email is valid");
                updateEmail.setEmail(email);
                break;
            }else{
                System.out.println("Invalid email, please try again.");
            }
        }

        boolean changed = methodCustomers.uppdateCustomerEmail(updateEmail);

        if(changed){
            System.out.println("Customers email have been changed successfully!");
        }else{
            System.out.println("You failed to change the customer's email");
        }
    }

    public void getCustomerByIDMethod(int customer_id) throws SQLException {
        ArrayList<Customer> customers = methodCustomers.getCustomerById(customer_id);
        for(Customer customer: customers){
            System.out.println(customer.getName());
            System.out.println();
        }

    }

    public void getCustomerByEmailMethod(String email) throws SQLException {
        ArrayList<Customer> customers = methodCustomers.getCustomerByEmail(email);
        for(Customer customer: customers){
            System.out.println(customer.getName());
            System.out.println();
        }

    }
}

