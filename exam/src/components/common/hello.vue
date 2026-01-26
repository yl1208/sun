<template>
  <section class="index">
    <div class="hello">
      <i class="iconfont icon-xihuan"></i><span>你好,{{user.userName}}老师</span>
    </div>
    <div class="msg">
      <p class="title">教务公告：</p>
      <ul>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>劳动节放假通知</li>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>周例行工作报告会</li>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>毕业生答辩具体流程</li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      user: {
        userName: null,
        userId: null
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
    openMsg() {
      this.$alert('根据《国务院办公厅关于2026年部分节假日安排的通知》精神，越城区行政服务中心将于5月1日至5月5日进行劳动节放假调休，共5天，放假期间不受理业务。5月6日开始正常上班受理业务。望市民朋友相互转告，给您带来不便，敬请谅解。','劳动节放假通知',{
        confirmButtonText: '确定'
      })
    }
  }
}
</script>

<style lang="less" scoped>
.index {
  margin-left: 70px;
  padding: 20px 30px;
  background: #f5f8ff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);

  .hello {
    font-size: 22px;
    color: #2c3e50;
    font-weight: 600;
    display: flex;
    align-items: center;
    gap: 10px;

    .icon-xihuan {
      font-size: 32px;
      color: #409EFF;
      transition: transform 0.3s ease;
    }
    &:hover .icon-xihuan {
      transform: scale(1.2);
      color: #66b1ff;
    }
  }

  .msg {
    margin-top: 30px;

    .title {
      font-size: 18px;
      color: #34495e;
      margin-bottom: 15px;
      font-weight: 600;
      border-left: 4px solid #409EFF;
      padding-left: 12px;
    }

    ul {
      display: flex;
      flex-direction: column;
      width: 250px;
      gap: 14px;
      padding-left: 0;
      list-style: none;
    }

    li {
      font-size: 15px;
      color: #409EFF;
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 8px;
      padding: 8px 12px;
      border-radius: 6px;
      background: white;
      box-shadow: 0 2px 6px rgba(64,158,255,0.2);
      transition: background-color 0.3s ease, box-shadow 0.3s ease;

      i {
        font-size: 18px;
        color: #67aefc;
      }

      &:hover {
        background-color: #e6f0ff;
        box-shadow: 0 4px 12px rgba(64,158,255,0.35);
      }
    }
  }
}
</style>
