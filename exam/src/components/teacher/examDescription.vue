<!-- 考试管理功能介绍 -->
<template>
  <section class="description">
    <div class="card">
      <h2 class="title">
        <i class="iconfont icon-kaoshi" /> 考试管理功能概述
      </h2>
      <p class="content">
        学生仅在教师发布考试后，才能在主页中查看相应的考试信息。
        每场考试需在考试安排表中进行记录（对应数据表为 <strong>exammanage</strong>），
        才能为其添加试题。该表包含考试名称、所属课程、考试时间、专业、学院等基础信息，
        为后续试卷管理提供数据支撑。
      </p>
    </div>
  </section>
</template>

<style lang="less" scoped>
.description {
  display: flex;
  justify-content: center;
  padding: 40px 20px;
  background: linear-gradient(135deg, #fefefe, #f4faff);
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
    color: #2c3e50;
    display: flex;
    align-items: center;
    margin-bottom: 20px;

    i {
      font-size: 26px;
      color: #50bfff;
      margin-right: 10px;
    }
  }

  .content {
    font-size: 16px;
    line-height: 1.8;
    color: #4a4a4a;
    border-left: 5px solid #50bfff;
    padding-left: 20px;
    background-color: #f0faff;
    border-radius: 4px;

    strong {
      color: #2980b9;
    }
  }
}
</style>
