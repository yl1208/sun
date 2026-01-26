<template>
  <div id="student">
    <!-- 顶部导航 -->
    <el-row class="padding-50">
      <el-col :span="24">
        <ul class="list">
          <li class="logo"><i class="iconfont icon-kaoshi"></i><span>在线考试系统</span></li>
          <li><a href="javascript:;" @click="exam()">我的试卷</a></li>
          <li><router-link to="/scoreTable">我的成绩</router-link></li>
          <li><router-link to="/message">留言</router-link></li>
          <li class="right" @mouseenter="flag = true" @mouseleave="flag = false">
            <a href="javascript:;"><i class="iconfont icon-Userselect icon"></i>{{ user.userName }}</a>
            <div class="msg" v-if="flag">
              <p @click="manage()">设置中心</p>
              <p class="exit" @click="exit()">退出</p>
            </div>
          </li>
        </ul>
      </el-col>
    </el-row>

    <!-- 主体内容区域：占满页面剩余空间 -->
    <div class="main">
      <router-view></router-view>
    </div>

    <!-- 底部 -->
    <v-footer></v-footer>
  </div>
</template>

<script>
import myFooter from "@/components/student/myFooter"
import { mapState } from 'vuex'

export default {
  components: {
    "v-footer": myFooter
  },
  data() {
    return {
      flag: false,
      user: {}
    }
  },
  created() {
    this.userInfo()
  },
  methods: {
    exit() {
      this.$router.push({ path: "/" })
      this.$cookies.remove("cname")
      this.$cookies.remove("cid")
    },
    manage() {
      this.$router.push({ path: '/manager' })
    },
    userInfo() {
      let studentName = this.$cookies.get("cname")
      let studentId = this.$cookies.get("cid")
      this.user.userName = studentName
      this.user.studentId = studentId
    },
    exam() {
      let isPractice = false
      this.$store.commit("practice", isPractice)
      this.$router.push({ path: '/student' })
    }
  },
  computed: mapState(["isPractice"])
}
</script>

<style scoped>
/* 外层容器：纵向布局，撑满整个页面 */
#student {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #f2f3f5;
}

/* 顶部导航栏样式 */
#student .padding-50 {
  padding: 0 50px;
  background-color: #fff;
  box-shadow: 0 0 10px 4px rgba(1, 149, 255, 0.1);
}

.list a {
  text-decoration: none;
  color: #334046;
}
li {
  list-style: none;
  height: 60px;
  line-height: 60px;
}
#student .list {
  display: flex;
}
#student .list li {
  padding: 0 20px;
  cursor: pointer;
}
#student .list li:hover {
  background-color: #0195ff;
  transition: all 0.3s ease;
}
#student .list li:hover a {
  color: #fff;
}
#student .list .right {
  margin-left: auto;
  position: relative;
}
#student .list .logo {
  display: flex;
  font-weight: bold;
  color: #2f6c9f;
}
#student .list .logo i {
  font-size: 50px;
}
.right .icon {
  margin-right: 6px;
}
.right .msg {
  text-align: center;
  position: absolute;
  top: 60px;
  left: 0;
  display: flex;
  flex-direction: column;
  border-radius: 4px;
  border-bottom: 3px solid #0195ff;
  background-color: #fff;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}
.right .msg p {
  height: 40px;
  line-height: 40px;
  width: 105px;
}
.right .msg p:hover {
  background-color: #0195ff;
  color: white;
}

/* 主体内容区域：自动撑满剩余空间 */
.main {
  flex: 1;
  padding: 30px 50px;
  background-color: #f5f7fa;
}
</style>
