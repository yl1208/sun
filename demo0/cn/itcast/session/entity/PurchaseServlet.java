package cn.itcast.session.entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PurchaseServlet", urlPatterns = "/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        if (productId == null) {
            resp.sendRedirect("ListCakeServlet");
            return;
        }

        InventoryManager inventoryManager = new InventoryManager();  // 应在更高层初始化和共享
        Product product = inventoryManager.getProduct(productId);

        if (product == null) {
            resp.sendRedirect("ListCakeServlet");
            return;
        }

        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        int quantity = 1; // 你可以根据用户输入决定购买的数量
        if (inventoryManager.hasStock(productId, quantity)) {
            cart.addProduct(product, quantity);
            inventoryManager.reduceStock(productId, quantity);

            // 更新 session
            session.setAttribute("cart", cart);

            resp.sendRedirect("CartServlet");
        } else {
            resp.getWriter().print("库存不足，无法购买。");
        }
    }
}