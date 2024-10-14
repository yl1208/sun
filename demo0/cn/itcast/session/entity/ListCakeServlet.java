package cn.itcast.session.entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ListCakeServlet", urlPatterns="/ListCakeServlet")
public class ListCakeServlet extends HttpServlet {

    // 持久化的 InventoryManager，保证库存不会在每次请求时重置
    private static InventoryManager inventoryManager = new InventoryManager();

    // 静态块用于初始化库存，只在类加载时调用一次
    static {
        // 预置一些产品
        inventoryManager.addProduct(new Product("1", "A类蛋糕", 500, 10));
        inventoryManager.addProduct(new Product("2", "B类蛋糕", 400, 5));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        // 遍历库存中的产品并输出
        for (Product product : inventoryManager.getInventorySnapshot().values()) {
            String url = "PurchaseServlet?id=" + product.getId();
            out.write(product.getName() + " 价格: " + product.getPrice() +
                    " 点击购买<br>");
        }
    }
}