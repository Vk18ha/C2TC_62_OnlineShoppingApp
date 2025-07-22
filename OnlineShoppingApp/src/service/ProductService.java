package service;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(int productId) {
        productList.removeIf(p -> p.getProductId() == productId);
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(int id) {
        for (Product p : productList) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }
}
