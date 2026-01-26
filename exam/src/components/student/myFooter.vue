<!-- 页脚部分 -->
<template>
  <footer id="footer">
    <ul>
      <li><a href="javascript:;">关于我们</a></li>
      <li>&copy; 2025 在线考试系统</li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: "myFooter"
}
</script>

<style scoped>
#footer {
  background-color: #f9f9f9;
  border-top: 1px solid #ddd;
  padding: 20px 0;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

#footer ul {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin: 0;
  padding: 0;
  list-style: none;
}

#footer ul li {
  color: #666;
  font-size: 14px;
}

#footer a {
  color: #666;
  text-decoration: none;
  transition: color 0.3s;
}

#footer a:hover {
  color: #0195ff;
}
</style>
