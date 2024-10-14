package cn.itcast.session.entity;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public boolean hasStock(String productId, int quantity) {
        Product product = inventory.get(productId);
        return product != null && product.getStock() >= quantity;
    }

    public void reduceStock(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null && product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
        }
    }

    public Map<String, Product> getInventorySnapshot() {
        return new HashMap<>(inventory);
    }
}