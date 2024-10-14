package cn.itcast.session.entity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderServlet", urlPatterns = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.getWriter().print("会话不存在，无法生成订单。");
            return;
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            resp.getWriter().print("购物车为空，无法生成订单。");
            return;
        }

        // 创建订单
        Order order = new Order(cart);

        // 清空购物车
        session.removeAttribute("cart");

        // 输出订单信息
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().print("订单生成成功! 详情如下:<br>");
        resp.getWriter().print(order.toString());
    }
}