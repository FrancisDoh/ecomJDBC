package controller;

import dao.ProductDaoImpl;
import entities.Product;
import util.SQLConnector;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MainController {
    //
    // Main
    public static void main(String[] args) {
        //
        Scanner sc = new Scanner(System.in);
        String op;
        String run;

        SQLConnector sqlConnector = new SQLConnector();
        SQLConnector.createConnection();

        System.out.println("Welcome to E-COM APP.");
//        System.out.println("Do you wish to continue? y | n");
//        run = sc.nextLine();

        do{
        System.out.println("Type one of the menus to continue: home | add | update | delete | get | search");
        op = sc.nextLine();

            // One of the CRUD operations
            switch (op) {
                case "add":
                    // Create
                    addNewProduct();
                    break;
                case "home":
                    // Read
                    getAllAddedProducts();
                    break;
                case "update":
                    // Update
                    updateThisProduct();
                    break;
                case "delete":
                    // Delete
                    deleteThisProduct();
                    break;
                case "get":
                    // Get one product
                    getThisProduct();
                    break;
                case "search":
                    // Search
                    searchProducts();
                    break;

                default:
                    System.out.println(":( Wrong Menu! Please type one of these: home | add | update | delete | get | search");
            }

            //
            System.out.println("Do you wish to continue? y | n");
            run = sc.nextLine();

        }while(run.equals("y") || run.equals("Y"));

    }





    // Retrieve list products based on user input productName parameter.
    private static void searchProducts() {
        //
        Scanner sc = new Scanner(System.in);
        String productName;
        //
        getAllAddedProducts();

        System.out.print("Enter the product name to search: ");
        productName = sc.nextLine();

        ProductDaoImpl productPen = new ProductDaoImpl();
        //System.out.println(ProductPen.getAllProducts());
        if(productPen.searchProductResults(productName).isEmpty()){
            System.out.println("No product with name starting with {" + productName +  "} found.");
        }else{
            productPen.searchProductResults(productName).forEach(System.out::println);
        }

        //
    }


    // Retrieve one product based on user provided productID
    private static void getThisProduct() {
        //
        Scanner sc = new Scanner(System.in);
        int prodID;

        //
        getAllAddedProducts();

        System.out.print("Enter the product ID of one product to see details: ");
        prodID = sc.nextInt();
        sc.nextLine();

        ProductDaoImpl productPen = new ProductDaoImpl();

        if(productPen.getProduct(prodID).getProductID() != 0){
            //productPen.getAllProducts().forEach(System.out::println); // .getAllProducts() returns a list.
            System.out.println(productPen.getProduct(prodID));
        }else{
            // Means the product record was not found.
            System.out.println("No product record of ID {" + prodID + "} found.");
        }
    }

    // Add a new product method
    private static void addNewProduct(){
        //        ----------------------------ADD A NEW PRODUCT ------------------------
        Scanner sc = new Scanner(System.in);
        String prodName, prodDescription;
        double prodPrice;

        System.out.print("Add this product name: ");
        prodName = sc.nextLine();

        System.out.print("Add this product description: ");
        prodDescription = sc.nextLine();

        System.out.print("Add this product price: ");
        prodPrice = sc.nextDouble();

        ProductDaoImpl productPen = new ProductDaoImpl();
        //Product myProduct = new Product("Product_1", "Product_1 description", 39.99);
        Product myProduct = new Product(prodName, prodDescription, prodPrice);

        int queryStatus = productPen.addProduct(myProduct);
        if(queryStatus != 0){
            System.out.println(myProduct.getProductName() + " is added successfully :)");
        }else{
            System.out.println("Looks like an error occurred during " + myProduct.getProductName() + " adding :(");
        }
    }

    // Get all added products
    private static void getAllAddedProducts(){
        //        ________________________ READ ALL PRODUCTS _____________________
        ProductDaoImpl productPen = new ProductDaoImpl();
        //System.out.println(ProductPen.getAllProducts());
        if(productPen.getAllProducts().isEmpty()){
            System.out.println("No product record available to display.");
        }else{
            productPen.getAllProducts().forEach(System.out::println); // .getAllProducts() returns a list.
        }

    }

    // Update this product
    private static void updateThisProduct() {
//        ________________________ UPDATE A PRODUCT _____________________
        Scanner sc = new Scanner(System.in);
        int prodID;
        String prodName, prodDescription;
        double prodPrice;

        getAllAddedProducts();

        System.out.print("Enter the product ID to update: ");
        prodID = sc.nextInt();
        sc.nextLine();

        System.out.print("Add this product new name: ");
        prodName = sc.nextLine();

        System.out.print("Add this product new description: ");
        prodDescription = sc.nextLine();

        System.out.print("Add this product new price: ");
        prodPrice = sc.nextDouble();

        ProductDaoImpl productPen = new ProductDaoImpl();
        //Product myProduct = new Product("Product_1", "Product_1 description", 39.99);
        Product myProduct = new Product(prodID, prodName, prodDescription, prodPrice);

        if(productPen.updateProduct(prodID, myProduct) != 0){
            System.out.println(myProduct.getProductName() + " is updated successfully :)");
        }else{
            System.out.println("Looks like an error occurred during " + myProduct.getProductName() + " information update :(");
        }

        getAllAddedProducts();
    }

    // Delete this product
    private static void deleteThisProduct(){
//       ________________________ DELETE A PRODUCT _____________________
        Scanner sc = new Scanner(System.in);
        int prodID;

        getAllAddedProducts();

        System.out.print("Enter the ID of the product to delete: ");
        prodID = sc.nextInt();
        sc.nextLine();

        ProductDaoImpl productPen = new ProductDaoImpl();
        int deleteStatus = productPen.deleteProduct(prodID);

        if(deleteStatus != 0){
            System.out.println("This product is successfully deleted :)");
        }else{
            System.out.println("Unable to delete this product :(");
        }
        //
        getAllAddedProducts();
    }

}
