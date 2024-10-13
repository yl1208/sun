package cn.itcast.session.entity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应的Content-Type为text/html;charset=utf-8
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        List<Cake> cart = null;
        boolean purFlag = true;
        HttpSession session = req.getSession(false);
        if (session == null){
            purFlag = false;
        }else{
            cart = (List)session.getAttribute("cart");

            if(cart==null)
                purFlag=false;
        }
        /* 如果purFlag为false，表明用户没有购买蛋糕，则重定向到ListServlet页面 */
        if (!purFlag) {
            out.write("<br>");
            out.print("对不起！您还没有购买任何商品!");
        }
        else {
            // 否则显示用户购买蛋糕的信息
            out.println("您购买的蛋糕有:");
            for(Cake cake : cart){
                String name=cake.getName();
                double price=0;
                out.print(name+"<br>");
            }
        }
    }

}
