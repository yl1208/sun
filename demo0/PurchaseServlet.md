# PurchaseServlet 类

## 概述
`PurchaseServlet` 类是一个用于处理购买商品请求的 Servlet。它扩展了 `HttpServlet` 类，并覆盖了 `doGet` 方法来处理 GET 请求。该 Servlet 的主要功能是接收商品 ID，检查库存，将商品添加到购物车，并更新库存数量。

## 注解

- `@WebServlet(name = "PurchaseServlet", urlPatterns = "/PurchaseServlet")`
    - 这个注解用于将 `PurchaseServlet` 类注册为一个 Web Servlet。它指定了 Servlet 的名称为 "PurchaseServlet"，并定义了 URL 模式为 "/PurchaseServlet"。这意味着当客户端发送请求到 "/PurchaseServlet" 时，该 Servlet 将被调用。

## 方法

### doGet(HttpServletRequest req, HttpServletResponse resp)
- **参数**:
    - `HttpServletRequest req`: 表示客户端的请求，包含请求参数。
    - `HttpServletResponse resp`: 用于向客户端发送响应。
- **抛出异常**:
    - `ServletException`: 如果 Servlet 遇到错误。
    - `IOException`: 如果发生 I/O 错误。
- **描述**:
    - 该方法首先尝试从请求参数中获取商品 ID。
    - 如果商品 ID 为空，则重定向到 "ListCakeServlet"。
    - 然后，它创建一个 `InventoryManager` 对象（注意：在实际应用中，`InventoryManager` 应该在更高层初始化和共享，而不是在每次请求时都创建新的实例）。
    - 使用 `InventoryManager` 对象获取对应的 `Product` 对象。
    - 如果商品不存在，则重定向到 "ListCakeServlet"。
    - 接下来，获取当前会话，并尝试从会话中获取名为 "cart" 的购物车对象。
    - 如果购物车对象不存在，则创建一个新的 `ShoppingCart` 对象，并将其存储在会话中。
    - 设置购买数量为 1（在实际应用中，应该根据用户输入决定购买的数量）。
    - 检查库存是否足够。如果足够，则将商品添加到购物车，并减少库存数量。
    - 更新会话中的购物车对象。
    - 重定向到 "CartServlet" 以显示购物车内容。
    - 如果库存不足，则向客户端发送 "库存不足，无法购买。" 的消息。

## 实现逻辑

1. 从请求参数中获取商品 ID。
2. 检查商品 ID 是否为空。如果为空，重定向到 "ListCakeServlet"。
3. 创建 `InventoryManager` 对象（注意：应优化为共享实例）。
4. 获取对应的 `Product` 对象。
5. 检查商品是否存在。如果不存在，重定向到 "ListCakeServlet"。
6. 获取当前会话。
7. 尝试从会话中获取购物车对象。
8. 如果购物车对象不存在，则创建新的购物车对象并存储在会话中。
9. 设置购买数量为 1（或根据用户输入决定）。
10. 检查库存是否足够。
11. 如果库存足够，将商品添加到购物车，并减少库存数量。
12. 更新会话中的购物车对象。
13. 重定向到 "CartServlet"。
14. 如果库存不足，向客户端发送错误消息。

## 注意事项

- 在实际应用中，`InventoryManager` 应该是一个单例或在更高层（如 Spring 容器）中初始化和共享，而不是在每次请求时都创建新的实例。
- 购买数量应该根据用户输入来确定，而不是硬编码为 1。
- 错误处理和异常捕获机制应该更加完善，以处理可能的异常情况，如会话不存在、商品 ID 无效等。
- 重定向到 "ListCakeServlet" 时，可以考虑传递错误信息或提示信息，以便用户了解为什么被重定向。

以上文档为 `PurchaseServlet` 类提供了全面的说明，包括其用途、注解、方法的描述、实现逻辑以及注意事项。