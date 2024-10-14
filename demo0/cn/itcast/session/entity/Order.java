package cn.itcast.session.entity;

import java.util.Date;
import java.util.UUID;

public class Order {
    private String id;
    private Date orderDate;
    private ShoppingCart cart;

    public Order(ShoppingCart cart) {
        this.id = UUID.randomUUID().toString();
        this.orderDate = new Date();
        this.cart = cart;
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append("订单编号: ").append(id).append("\n");
        sb.append("订单日期: ").append(orderDate).append("\n");
        sb.append("购买商品:\n");

        for (CartItem item : cart.getItems()) {
            sb.append(item.getProduct().getName()).append(" x ")
                    .append(item.getQuantity()).append(" - 总价: ")
                    .append(item.getTotalPrice()).append("\n");
        }
        sb.append("订单总价: ").append(cart.getTotalPrice()).append("\n");

        return sb.toString();
    }
}