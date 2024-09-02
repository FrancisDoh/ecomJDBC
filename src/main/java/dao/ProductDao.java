package dao;

import entities.Product;

import java.util.List;

public interface ProductDao {
    //
    public int addProduct(Product product);
    public List<Product> getAllProducts();
    public int updateProduct(int productID, Product updatedProduct);
    public int deleteProduct(int productID);
    //
    public Product getProduct(int productID); // Returns a single product based on the provided productID.
    public List<Product> searchProductResults(String productName); // Returns a list of products based on name search.



}
