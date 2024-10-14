# CartServlet 类

## 概述
`CartServlet` 类是一个用于处理购物车相关请求的 Servlet。它扩展了 `HttpServlet` 类，并覆盖了 `doGet` 方法来处理 GET 请求。该 Servlet 主要用于显示购物车中的商品信息，包括商品名称、数量和总价，并显示购物车的总价。

## 注解

- `@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")`
    - 这个注解用于将 `CartServlet` 类注册为一个 Web Servlet。它指定了 Servlet 的名称为 "CartServlet"，并定义了 URL 模式为 "/CartServlet"。这意味着当客户端发送请求到 "/CartServlet" 时，该 Servlet 将被调用。

## 方法

### doGet(HttpServletRequest req, HttpServletResponse resp)
- **参数**:
    - `HttpServletRequest req`: 表示客户端的请求。
    - `HttpServletResponse resp`: 用于向客户端发送响应。
- **抛出异常**:
    - `ServletException`: 如果 Servlet 遇到错误。
    - `IOException`: 如果发生 I/O 错误。
- **描述**:
    - 该方法首先设置响应的内容类型为 "text/html; charset=utf-8"，以确保返回的 HTML 内容使用 UTF-8 编码。
    - 然后，它获取当前会话（如果存在的话），并尝试从会话中获取名为 "cart" 的属性，该属性应该是一个 `ShoppingCart` 对象。
    - 如果购物车为空或不存在，则向客户端发送 "购物车为空！" 的消息。
    - 如果购物车存在且包含商品，则遍历购物车中的每个 `CartItem`，并打印出商品名称、数量、总价。
    - 最后，打印出购物车的总价，并显示 "生成订单" 的提示。

## 实现逻辑

1. 设置响应的内容类型。
2. 获取当前会话（如果会话不存在，则 `req.getSession(false)` 将返回 `null`）。
3. 尝试从会话中获取购物车对象。
4. 检查购物车是否为空或不存在商品。
5. 如果购物车不为空，遍历购物车中的每个商品项，并打印相关信息。
6. 打印购物车的总价。
7. 显示 "生成订单" 的提示。

## 注意事项

- 在实际使用中，应该添加适当的错误处理和异常捕获机制，以处理可能的异常情况，如会话不存在、购物车对象不是预期的 `ShoppingCart` 类型等。
- 此外，生成订单的逻辑应该是一个更复杂的过程，可能涉及订单确认、支付处理等多个步骤，这里只是简单地显示了提示信息。

以上文档为 `CartServlet` 类提供了全面的说明，包括其用途、注解、方法的描述以及实现逻辑。