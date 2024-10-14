package cn.itcast.session.entity;

import java.util.Date;
import java.util.UUID;

// 订单类
class Order {
    private String id;
    private Date orderDate;
    private ShoppingCart cart;

    public Order(ShoppingCart cart) {
        this.id = UUID.randomUUID().toString();
        this.orderDate = new Date();
        this.cart = cart;
    }

    // Getter 方法
    public String getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + cart.getTotalPrice() +
                '}';
    }
}
