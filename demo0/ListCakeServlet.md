# ListCakeServlet 类

## 概述
`ListCakeServlet` 类是一个用于列出所有蛋糕商品的 Servlet。它扩展了 `HttpServlet` 类，并覆盖了 `doGet` 方法来处理 GET 请求。该 Servlet 的主要功能是从 `InventoryManager` 中获取蛋糕商品的列表，并将它们以 HTML 格式展示给客户端。

## 注解

- `@WebServlet(name="ListCakeServlet", urlPatterns="/ListCakeServlet")`
    - 这个注解用于将 `ListCakeServlet` 类注册为一个 Web Servlet。它指定了 Servlet 的名称为 "ListCakeServlet"，并定义了 URL 模式为 "/ListCakeServlet"。这意味着当客户端发送请求到 "/ListCakeServlet" 时，该 Servlet 将被调用。

## 静态成员变量

- `private static InventoryManager inventoryManager = new InventoryManager();`
    - 这是一个静态的 `InventoryManager` 对象，用于管理蛋糕商品的库存。由于它是静态的，所以它的生命周期与类相同，保证了库存不会在每次请求时重置。

## 静态初始化块

- `static { ... }`
    - 这是一个静态初始化块，它在类加载时执行一次。在这个块中，我们预置了一些蛋糕商品到 `InventoryManager` 中，以便在 Servlet 启动时就有一些初始的库存数据。

## 方法

### doGet(HttpServletRequest req, HttpServletResponse resp)
- **参数**:
    - `HttpServletRequest req`: 表示客户端的请求，包含请求参数（虽然在这个方法中未使用）。
    - `HttpServletResponse resp`: 用于向客户端发送响应。
- **抛出异常**:
    - `ServletException`: 如果 Servlet 遇到错误。
    - `IOException`: 如果发生 I/O 错误。
- **描述**:
    - 该方法首先设置响应的内容类型为 "text/html;charset=utf-8"，以确保输出的是 UTF-8 编码的 HTML 内容。
    - 然后，它获取一个 `PrintWriter` 对象，用于向客户端发送文本数据。
    - 接下来，它遍历 `InventoryManager` 中的所有蛋糕商品，并为每个商品生成一个购买链接。
    - 最后，它将每个商品的名称和价格，以及购买链接，以 HTML 格式输出到客户端。

## 注意事项

- `InventoryManager` 被设计为静态的，以便在 Servlet 的整个生命周期中共享。然而，在实际应用中，更好的做法是使用依赖注入（如 Spring）来管理 `InventoryManager` 的生命周期和依赖关系。
- 在这个示例中，商品数据是在静态初始化块中硬编码的。在实际应用中，商品数据应该来自持久化存储（如数据库），而不是硬编码在代码中。
- 由于这个 Servlet 只是简单地列出了商品，并没有处理用户输入或表单数据，所以它只适用于展示静态内容。如果需要处理用户输入，应该使用表单，并在相应的 Servlet 中处理 POST 请求。
- 输出的 HTML 内容非常简单，没有使用模板引擎或视图层框架（如 JSP、Thymeleaf 等）。在实际应用中，更好的做法是使用这些技术来生成更复杂和动态的 HTML 内容。

以上文档为 `ListCakeServlet` 类提供了全面的说明，包括其功能、注解、静态成员变量、静态初始化块、方法的描述以及注意事项。