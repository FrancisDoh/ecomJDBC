package dao;

import entities.Product;
import util.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public int addProduct(Product product) {
        //
        String sql = "INSERT INTO products(productName, productDescription, productPrice) " +
                "     VALUES (?,?,?)";
        int status = 0;
        //
        try(Connection con = SQLConnector.createConnection()){
            //
            //String sql = "INSERT INTO students(name, gpa, address, grade) VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt. setString(1, product.getProductName());
            stmt. setString(2, product.getProductDescription());
            stmt. setDouble(3, product.getProductPrice());
            //
            status = stmt.executeUpdate(); // .executeUpdate() because we're writing in the DB.
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Insertion error: " + e.getMessage());
        }
        //
        return status;
    }

    @Override
    public List<Product> getAllProducts() {
        //return List.of();
        String sql = "SELECT * " +
                "     FROM products";
        //
        List<Product> products = new ArrayList<>();

        try(Connection con = SQLConnector.createConnection()){
            // Prepare the SQL statement
            PreparedStatement stmt = con.prepareStatement(sql);
            // ResultSet executes and grabs the result from the DB
            ResultSet res = stmt.executeQuery(); // Read from SQL DB
            //
            while(res.next()) {
                Product thisProduct = new Product();
                //
                thisProduct.setProductID(res.getInt(1));
                thisProduct.setProductName(res.getString(2));
                thisProduct.setProductDescription(res.getString(3));
                thisProduct.setProductPrice(res.getDouble(4));
                // Add tot the list
                products.add(thisProduct);
            }
        }catch(SQLException e){
            //e.printStackTrace(); //
            System.out.println("Data retrieving error: " + e.getMessage()); // user friendly error display.
        }

        return products;
    }

    @Override
    public int updateProduct(int productID, Product updatedProduct) {
        //
        String sql = "UPDATE products " +
                "     SET productName=?, productDescription=?, productPrice=? " +
                "     WHERE productID=?";
        int status = 0;
        //
        try(Connection con = SQLConnector.createConnection()){
            // Prepare statement
            PreparedStatement stmt = con.prepareStatement(sql);
            // in case we need to update only one or two records from the student object, we'll only need to mention the specific one below.
            /*
                 sql = "UPDATE students SET (name=?, gpa=?) WHERE id=?";
                 stmt.setString(1, updatedStudent.getName());
                 stmt.setDouble(2, updatedStudent.getGpa());

                 stmt.setInt(5, id);
                 stmt.executeUpdate();
             */

            stmt. setString(1, updatedProduct.getProductName());
            stmt. setString(2, updatedProduct.getProductDescription());
            stmt. setDouble(3, updatedProduct.getProductPrice());
            //
            stmt.setInt(4, productID);
            //
            stmt.executeUpdate(); // Write in SQL DB

        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Update error: " + e.getMessage());
        }
        return status;
    }

    @Override
    public int deleteProduct(int productID) {
        //
        String sql = "DELETE " +
                "     FROM products " +
                "     WHERE productID = ?";
        int status = 0;
        //
        try(Connection con = SQLConnector.createConnection()){
            //
            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt.setInt(1, productID);
            //
            status = stmt.executeUpdate();
        }catch (SQLException e){
            //e.printStackTrace();//
            System.out.println("Deletion error: " + e.getMessage());
        }
        return status;
    }

    @Override
    public Product getProduct(int productID) {
        //
        int status = 0;
        String sql = "SELECT *" +
                "     FROM products " +
                "     WHERE productID = ?";
        //
        Product thisProduct = new Product();

        //
        try(Connection con = SQLConnector.createConnection()) {
            // Prepare the SQL statement
            PreparedStatement stmt = con.prepareStatement(sql);
            //
            stmt.setInt(1, productID);
            // ResultSet executes and grabs the result from the DB
            ResultSet res = stmt.executeQuery(); // Read from SQL DB
            //
            if(res.next()) {
                //Product thisProduct = new Product();
                //
                thisProduct.setProductID(res.getInt(1));
                thisProduct.setProductName(res.getString(2));
                thisProduct.setProductDescription(res.getString(3));
                thisProduct.setProductPrice(res.getDouble(4));
                // Add tot the list
                //products.add(thisProduct);

            }
        } catch (SQLException e){
            //e.printStackTrace();//
            System.out.println("Product retrieval error: " + e.getMessage());
        }
        return thisProduct;
        //return status;
    }

    //
    @Override
    public List<Product> searchProductResults(String productName) {
        //
        //String sql = "SELECT * FROM products WHERE productName LIKE 'sho%'"; // This Query is working
        String sql = "SELECT * " +
                "FROM products " +
                "WHERE productName LIKE ?";
        List<Product> products = new ArrayList<>();

        try(Connection con = SQLConnector.createConnection()){
            // Prepare the SQL statement
            PreparedStatement stmt = con.prepareStatement(sql);
            //stmt.setInt(1, productID);

            stmt.setString(1, productName + '%');
            // ResultSet executes and grabs the result from the DB
            ResultSet res = stmt.executeQuery(); // Read from SQL DB
            //
            while(res.next()) {
                Product thisProduct = new Product();
                //
                thisProduct.setProductID(res.getInt(1));
                thisProduct.setProductName(res.getString(2));
                thisProduct.setProductDescription(res.getString(3));
                thisProduct.setProductPrice(res.getDouble(4));
                // Add to the list
                products.add(thisProduct);
            }
        }catch(SQLException e){
            //e.printStackTrace(); //
            System.out.println("Data retrieving error: " + e.getMessage()); // user friendly error display.
        }

        return products;
    }
}
