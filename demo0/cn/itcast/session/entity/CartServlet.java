package cn.itcast.session.entity;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            out.print("购物车为空！");
            return;
        }

        out.println("您购买的商品有:<br>");
        for (CartItem item : cart.getItems()) {
            String name = item.getProduct().getName();
            int quantity = item.getQuantity();
            double price = item.getTotalPrice();
            out.print(name + " x " + quantity + " 总价: " + price + "<br>");
        }

        out.println("购物车总价: " + cart.getTotalPrice() + "<br>");
        out.println("生成订单");
    }
}