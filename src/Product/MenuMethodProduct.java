package Product;

import Validation.Validation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMethodProduct {

    ProductService methodsProducts = new ProductService();
    Validation validation = new Validation();

    Scanner scanner = new Scanner(System.in);

    public void getAllProducts() throws SQLException {
        ArrayList<Product> products = methodsProducts.getAllP();
        for(Product product: products){
            System.out.println("Product id: " + product.getPruduct_id());
            System.out.println("Product name: " + product.getName());
            System.out.println("Product price: " + product.getPrice());
            System.out.println();
        }
    }

    public void searchByName() throws SQLException{

        while (true){
            System.out.println("Search a product using Name: ");
            String searchName = scanner.nextLine();
            ArrayList<Product>searchResults = methodsProducts.searchProduct(searchName);

            if(searchResults.isEmpty()){
                System.out.println("No product with that name");
            }else {
                for(Product product : searchResults){
                    System.out.println("Product id: " + product.getPruduct_id());
                    System.out.println("Product name: " + product.getName());
                    System.out.println("Product price: " + product.getPrice());
                    System.out.println("Stock quantity: " + product.getStock_Quantety());
                    System.out.println();
                }
                break;
            }
        }
    }

    public void searchByCategory() throws SQLException {

        String searchCatName;

        while (true){
            System.out.println("Search a product by category (smartphones): ");
            searchCatName = scanner.nextLine().trim();

            if(!methodsProducts.categoryExistCheck(searchCatName)){
                System.out.println("The category does not exist in the database.");
            }else{
                break;
            }
        }

        ArrayList<Product>searchResultsCategory = methodsProducts.productsSearchByCatogoryName(searchCatName);

        if(searchResultsCategory.isEmpty()){
            System.out.println("The category exist, but currently has no products.");
        }else {
            for(Product product : searchResultsCategory){
                System.out.println("Product id: " + product.getPruduct_id());
                System.out.println("Product name: " + product.getName());
                System.out.println("Product price: " + product.getPrice());
                System.out.println("Stock quantity: " + product.getStock_Quantety());
                System.out.println();
            }
        }
    }

    public void updateProductPrice() throws SQLException{

        Product uppdateProduct = new Product();
        int productID = 0 ;

        while(true) {
            System.out.println("Write in the product ID of the product that you want to change the price on:");
            String input = scanner.nextLine();

            if (validation.numberValidation(input)) {
                productID = Integer.parseInt(input);
                if(methodsProducts.CheckProductIdExist(productID)){
                    uppdateProduct.setPruduct_id(productID);
                    break;
                }else{
                    System.out.println("Sorry but that product id does not exist, try again");
                }
            } else {
                System.out.println("you have to add a id number");
            }
        }

        getProductsUsingId(productID);

        double newPrice = 0;
        while (true){
            System.out.println("Write in the new price that you want to add: ");
            String priceInput= scanner.nextLine().replace(",",".");

            try{
                newPrice = Double.parseDouble(priceInput);
                if(validation.NoNegativeNumberValidation(newPrice)){
                    break;
                }else{
                    System.out.println("The price have to be 0 or more");
                }
            } catch (NumberFormatException e) {
                System.out.println("invalid price input. Please enter a valid number");
            }
        }

            uppdateProduct.setPrice(newPrice);

            boolean changed = methodsProducts.updatePriceProduct(uppdateProduct);

            if(changed){
                System.out.println("\nThe price have now been changed to:\n");
                getProductsUsingId(productID);
            }else{
            System.out.println("some thing went wrong, the price have not been updated");
            }

    }

    public void updateStock_quantity() throws SQLException {

        Product updateProductStock = new Product();
        int productID = 0;
        int stock = 0;

        // Check so that it is a nr and that the product id exist in the databse
        while(true) {
            System.out.println("Write in the product ID of the product that you want to change the stock quantity on:");
            String input = scanner.nextLine();

            if (validation.numberValidation(input)) {
                productID = Integer.parseInt(input);
                if(methodsProducts.CheckProductIdExist(productID)){
                    updateProductStock.setPruduct_id(productID);
                    break;
                }else{
                    System.out.println("Sorry but that product id does not exist, try again");
                }
            } else {
                System.out.println("you have to add a id number");
            }
        }

        getProductsinfo(productID);

        // check so that it is a nr before you can change the stock quantity
        while(true){
            System.out.println("Write in the new stock quantity that you want to add: ");
            String nrInput = scanner.nextLine();

            if(validation.numberValidation(nrInput)){
                stock =Integer.parseInt(nrInput);
                if(validation.NoNegativeNumberValidation(stock)){
                    break;
                }else{
                    System.out.println("The stock quantity have to be 0 or more");
                }
            }else{
                System.out.println("that is not a number");
            }
        }

        updateProductStock.setStock_Quantety(stock);

        boolean change = methodsProducts.updateStocksProduct(updateProductStock);

        if(change){
            System.out.println("\nThe stock quantity have now been changed to: ");
            getProductsinfo(productID);
        }else{
            System.out.println("some thing went wrong, the stock quantity have not been updated");
        }
    }

    public void addNewProduct() throws SQLException{

        Product newProduct = new Product();
        boolean notCorrectInput = true;
        int manufacturer_id = 0;
        int stock = 0;

        while(notCorrectInput) {

            System.out.println("Add the new products name: ");
            String name = scanner.nextLine();
            newProduct.setName(name);

            while(true) {
                System.out.println("add manufacture-ID: ");
                String input = scanner.nextLine();
                if (validation.numberValidation(input)) {
                    manufacturer_id = Integer.parseInt(input);
                    if (methodsProducts.checkManufacturyIdExist(manufacturer_id)) {
                        newProduct.setManufacturer_id(manufacturer_id);
                        break;
                    } else {
                        System.out.println("There are no manufacturer id with that number");
                    }
                } else {
                    System.out.println("you have to add a number");
                }
            }

            System.out.println("Add a description about the product:");
            String description = scanner.nextLine();
            newProduct.setDescription(description);

            double newPrice = 0;
            while (true){
                System.out.println("Write in the new price that you want to add: ");
                String priceInput= scanner.nextLine().replace(",",".");

                try{
                    newPrice = Double.parseDouble(priceInput);
                    if(validation.NoNegativeNumberValidation(newPrice)){
                        break;
                    }else{
                        System.out.println("the price have to be 0 or more");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("invalid price input. Please enter a valid number");
                }
            }

            newProduct.setPrice(newPrice);

            while(true) {
                System.out.println("Add how many products there are on stock:");
                String nrInput = scanner.nextLine();

                if (validation.numberValidation(nrInput)) {
                    stock = Integer.parseInt(nrInput);
                    if (validation.NoNegativeNumberValidation(stock)) {
                        break;
                    } else {
                        System.out.println("The stock quantity have to be 0 or more");
                    }
                } else {
                    System.out.println("that is not a number");
                }
            }

            newProduct.setStock_Quantety(stock);

            System.out.println("\nThis is the information you have added: ");
            System.out.println("Name: " + name);
            System.out.println("Manufacture_id: " + manufacturer_id);
            System.out.println("Description: " + description);
            System.out.println("Price: " + newPrice);
            System.out.println("Quantity of products in stock: " + stock);

            boolean validResponse = false;
            while(!validResponse){
                System.out.println("Are the information correct ?");
                System.out.println("yes or no");
                String response = scanner.nextLine();

                if (validation.yesNoValidation(response)){
                    notCorrectInput = response.equalsIgnoreCase("no");
                    validResponse = true;
                }else{
                    System.out.println("Please answer YES or NO ");
                }
            }

        }

        boolean success = methodsProducts.newProduct(newProduct);

        if(success){
            System.out.println("Product added successfully!");
        }else{
            System.out.println("You failed to add product");
        }
    }

    //get product info using id
    public void getProductsUsingId(int id) throws SQLException {
        ArrayList<Product> products = methodsProducts.productByid(id);
        for(Product product: products){
            System.out.println("Product id: " + product.getPruduct_id());
            System.out.println("Product name: " + product.getName());
            System.out.println("Product price: " + product.getPrice());
            System.out.println();
        }
    }

    //get product info containing stock using id
    public void getProductsinfo(int id) throws SQLException {
        ArrayList<Product> products = methodsProducts.productByid(id);
        for(Product product: products){
            System.out.println("Product id: " + product.getPruduct_id());
            System.out.println("Product name: " + product.getName());
            System.out.println("Product price: " + product.getPrice());
            System.out.println("Product stock: " + product.getStock_Quantety());
            System.out.println();
        }
    }

}
