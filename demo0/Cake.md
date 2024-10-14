# Cake 类

## 概述
`Cake` 类表示一个蛋糕的实体。它包含蛋糕的唯一标识符（ID）和名称。这个类主要用于存储和管理蛋糕的基本信息。

## 属性

### `serialVersionUID`
- **类型**: `long`
- **描述**: 用于序列化版本控制的唯一标识符。在对象序列化过程中，这个值用于确保版本的一致性。
- **默认值**: `1L`

### `id`
- **类型**: `String`
- **描述**: 蛋糕的唯一标识符。用于区分不同的蛋糕实例。

### `name`
- **类型**: `String`
- **描述**: 蛋糕的名称。

## 构造方法

### Cake()
- **描述**: 无参构造方法。创建一个默认的 `Cake` 实例，其 `id` 和 `name` 属性为 `null`。

### Cake(String id, String name)
- **参数**:
    - `id`: 蛋糕的唯一标识符。
    - `name`: 蛋糕的名称。
- **描述**: 创建一个带有指定 `id` 和 `name` 的 `Cake` 实例。

## 方法

### getId()
- **返回类型**: `String`
- **描述**: 获取蛋糕的唯一标识符。

### setId(String id)
- **参数**:
    - `id`: 要设置的蛋糕唯一标识符。
- **描述**: 设置蛋糕的唯一标识符。

### getName()
- **返回类型**: `String`
- **描述**: 获取蛋糕的名称。

### setName(String name)
- **参数**:
    - `name`: 要设置的蛋糕名称。
- **描述**: 设置蛋糕的名称。

## 使用示例
```java  
// 创建一个Cake实例  
Cake cake = new Cake("123", "Chocolate Cake");  
  
// 获取并打印Cake的ID和名称  
System.out.println("ID: " + cake.getId());  
System.out.println("Name: " + cake.getName());  
  
// 修改Cake的名称  
cake.setName("Vanilla Cake");  
  
// 打印修改后的名称  
System.out.println("Updated Name: " + cake.getName());