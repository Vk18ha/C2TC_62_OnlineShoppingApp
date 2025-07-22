package model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey().getName())
              .append(" - Quantity: ")
              .append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
