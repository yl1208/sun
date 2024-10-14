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

    public void updateStock(String productId, int newStock) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setStock(newStock);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
    }

    public boolean hasStock(String productId, int quantity) {
        Product product = inventory.get(productId);
        return product != null && product.getStock() >= quantity;
    }

    public Map<String, Product> getInventorySnapshot() {
        return new HashMap<>(inventory);
    }

    public void removeProduct(String productId) {
        inventory.remove(productId);
    }

    // 新增的 reduceStock 方法
    public void reduceStock(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null && product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
        } else {
            throw new IllegalArgumentException("Insufficient stock or product not found for ID: " + productId);
        }
    }
}