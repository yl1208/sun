
package cn.itcast.session.entity;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addProduct(new Product("1", "Laptop", 999.99, 10));
        inventoryManager.addProduct(new Product("2", "Smartphone", 699.99, 20));

        ShoppingCart cart = new ShoppingCart(); // 注意：ShoppingCart 类需要您自己定义
        Product laptop = inventoryManager.getProduct("1"); // 使用 getProduct 方法而不是 getProductById
        Product smartphone = inventoryManager.getProduct("2");

        if (laptop != null && smartphone != null) {
            cart.addProduct(laptop, 1); // 添加一台笔记本（假设 ShoppingCart 有这个方法）
            cart.addProduct(smartphone, 2); // 添加两部手机（假设 ShoppingCart 有这个方法）

            // 通常，您不需要在这里再次检查库存，因为您已经从 inventoryManager.getProduct 获取了产品
            // 这意味着这些产品在获取时应该存在于库存中（除非在获取和此检查之间有其他操作改变了库存）
            // 但为了演示和确保安全性，这里保留检查
            if (inventoryManager.hasStock("1", 1) && inventoryManager.hasStock("2", 2)) {
                // 由于已经添加到购物车，这里减少库存
                inventoryManager.reduceStock("1", 1);
                inventoryManager.reduceStock("2", 2);

                // 假设您有一个 Order 类来处理订单创建
                // Order order = new Order(cart); // 这里需要您自己定义 Order 类和它的构造函数
                // System.out.println("Order Created: " + order); // 这里需要您重写 Order 类的 toString 方法或提供适当的输出

                // 由于我们没有 Order 类的实现，这里仅打印一条消息表示订单已处理
                System.out.println("Order processed successfully.");
            } else {
                // 这部分代码实际上可能是多余的，因为前面的 getProduct 调用应该已经保证了产品的存在
                // 但为了完整性，这里保留它并添加一条更合适的错误消息
                System.out.println("An unexpected error occurred: products should be available based on previous checks.");
            }
        } else {
            System.out.println("Products not found in inventory!");
        }
    }
}
