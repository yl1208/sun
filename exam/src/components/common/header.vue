<!-- 顶部信息栏 -->
<template>
  <header id="topbar">
    <el-row>
      <el-col :span="4" class="topbar-left">
        <i class="iconfont icon-kaoshi"></i>
        <span class="title" @click="index()">在线考试系统</span>
      </el-col>
      <el-col :span="20" class="topbar-right">
        <i class="el-icon-menu" @click="toggle()"></i>
        <el-dropdown trigger="click" @command="handleCommand">
          <span class="user-dropdown">
            <span class="user-name">{{ user.userName }}</span>
            <img src="@/assets/img/userimg.png" class="user-img" />
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="info">用户信息</el-dropdown-item>
            <el-dropdown-item command="setting">设置</el-dropdown-item>
            <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </header>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
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
  computed: mapState(["flag", "menu"]),
  methods: {
    ...mapMutations(["toggle"]),
    getUserInfo() {
      this.user.userName = this.$cookies.get("cname")
      this.user.userId = this.$cookies.get("cid")
    },
    index() {
      this.$router.push({ path: '/index' })
    },
    handleCommand(command) {
      switch (command) {
        case 'info':
          this.$message('用户信息功能未实现')
          break
        case 'setting':
          this.$message('设置功能未实现')
          break
        case 'logout':
          this.exit()
          break
      }
    },
    exit() {
      let role = this.$cookies.get("role")
      this.$router.push({ path: "/" })
      this.$cookies.remove("cname")
      this.$cookies.remove("cid")
      this.$cookies.remove("role")
      if (role == 0) {
        this.menu.pop()
      }
    }
  }
}
</script>

<style scoped>
#topbar {
  position: relative;
  z-index: 10;
  background-color: rgb(2, 5, 23);
  height: 80px;
  line-height: 80px;
  color: rgba(32, 175, 207, 0.62);
}

.topbar-left {
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(0, 0, 0, 0.05);
}

.topbar-left .icon-kaoshi {
  font-size: 60px;
}

.topbar-left .title {
  font-size: 20px;
  cursor: pointer;
  margin-left: 10px;
}

.topbar-right {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-right: 20px;
}

.topbar-right .el-icon-menu {
  font-size: 30px;
  margin-right: 20px;
  color: #fff;
  cursor: pointer;
}

.user-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
  gap: 10px;
}

.user-name {
  color: #fff;
  font-size: 14px;
}

.user-img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
</style>
