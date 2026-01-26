<template>
  <section class="description">
    <div class="card">
      <h2 class="title">
        <i class="iconfont icon-tiku" /> 题库管理功能概述
      </h2>
      <p class="content">
        为了更好地管理不同类型的试题，题库设计采用模块化结构，分为选择题、填空题、判断题三张独立题库表。
        各表分别存储对应题型，通过一张中间表实现题库与试卷的关联，从而灵活生成完整的试卷。
      </p>
    </div>
  </section>
</template>

<style lang="less" scoped>
.description {
  display: flex;
  justify-content: center;
  padding: 40px 20px;
  background: linear-gradient(135deg, #fefefe, #f9f9f9);
}

.card {
  max-width: 720px;
  width: 100%;
  background: #ffffff;
  border-radius: 12px;
  padding: 30px 40px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.06);
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 14px 35px rgba(0, 0, 0, 0.08);
  }

  .title {
    font-size: 24px;
    font-weight: 600;
    color: #34495e;
    display: flex;
    align-items: center;
    margin-bottom: 20px;

    i {
      font-size: 26px;
      color: #f08d8d;
      margin-right: 10px;
    }
  }

  .content {
    font-size: 16px;
    line-height: 1.8;
    color: #555;
    border-left: 5px solid #f08d8d;
    padding-left: 20px;
    background-color: #fff7f7;
    border-radius: 4px;
  }
}
</style>
