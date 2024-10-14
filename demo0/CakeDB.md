# CakeDB 类

## 概述
`CakeDB` 类是一个简单的蛋糕数据库，用于存储和管理蛋糕实体。它使用 `LinkedHashMap` 来维护蛋糕的集合，以确保蛋糕的插入顺序。该类提供了获取所有蛋糕和根据 ID 获取特定蛋糕的方法。

## 属性

### `cake`
- **类型**: `Map<String, Cake>`
- **描述**: 用于存储蛋糕实体的映射。键是蛋糕的 ID（字符串类型），值是 `Cake` 对象。使用 `LinkedHashMap` 来保持插入顺序。

## 静态初始化块

在 `CakeDB` 类加载时，静态初始化块会执行，初始化 `cake` 映射，并添加五个预定义的蛋糕实体。

```java  
static {  
    cake.put("1", new Cake("1", "A类蛋糕"));  
    cake.put("2", new Cake("2", "B类蛋糕"));  
    cake.put("3", new Cake("3", "C类蛋糕"));  
    cake.put("4", new Cake("4", "D类蛋糕"));  
    cake.put("5", new Cake("5", "E类蛋糕"));  
}