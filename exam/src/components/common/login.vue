<!-- 用户登录界面 -->
<template>
  <div id="login">
    <div class="bg"></div>
    <el-row class="main-container">
      <el-col :lg="8" :xs="16" :md="10" :span="10">
        <div class="top">
          <i class="iconfont icon-kaoshi"></i><span class="title">在线考试系统</span>
        </div>
        <div class="bottom">
          <div class="container">
<!--            <p class="title">登录</p>-->
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="用户名">
                <el-input v-model.number="formLabelAlign.username" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="formLabelAlign.password" placeholder="请输入密码" type='password'></el-input>
              </el-form-item>
              <div class="submit">
                <el-button type="primary" class="row-login" @click="login()">登录</el-button>
              </div>
              <div class="options">
                <p class="find"><a href="javascript:;">找回密码</a></p>
                <div class="register">
                  <span>没有账号?</span>
                  <span><a href="javascript:;">注册</a></span>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: "login",
  data() {
    return {
      role: 2,
      labelPosition: 'left',
      formLabelAlign: {
        username: '6666',
        password: '123456'
      }
    }
  },
  methods: {
    //用户登录请求后台处理
    login() {
      console.log("登录操作执行-------");
      this.$axios({
        url: `/api/login`,
        method: 'post',
        data: {
          ...this.formLabelAlign
        }
      }).then(res=>{
        let resData = res.data.data
        if(resData != null) {
          switch(resData.role) {
            case "0":  //管理员
              this.$cookies.set("cname", resData.adminName)
              this.$cookies.set("cid", resData.adminId)
              this.$cookies.set("role", 0)
              this.$router.push({path: '/index' }) //跳转到首页
              break
            case "1": //教师
              this.$cookies.set("cname", resData.teacherName)
              this.$cookies.set("cid", resData.teacherId)
              this.$cookies.set("role", 1)
              this.$router.push({path: '/index' }) //跳转到教师用户
              break
            case "2": //学生
              this.$cookies.set("cname", resData.studentName)
              this.$cookies.set("cid", resData.studentId)
              this.$router.push({path: '/student'})
              break
          }
        }
        if(resData == null) { //错误提示
          this.$message({
            showClose: true,
            type: 'error',
            message: '用户名或者密码错误'
          })
        }
      })
    },
    clickTag(key) {
      this.role = key
    }
  },
  computed: mapState(["userInfo"]),
  mounted() {

  }
}
</script>

<style lang="less" scoped>
#login {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(to right, #e0ecf8, #f5f9ff);
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.bg {
  display: none;
}

.main-container {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-col {
  background-color: #ffffff;
  padding: 40px 30px;
  border-radius: 12px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  max-width: 400px;
}

.top {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 24px;
}

.top .icon-kaoshi {
  font-size: 40px;
  color: #1976d2;
  margin-right: 10px;
}

.top .title {
  font-size: 24px;
  color: #333;
  font-weight: 600;
}


.container {
  width: 100%;
}

.container .title {
  font-size: 20px;
  font-weight: 500;
  margin-bottom: 20px;
  text-align: center;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input {
  width: 100%;
}

.submit {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.row-login {
  width: 100%;
  padding: 14px 0;
  background-color: #1976d2;
  border-color: #1976d2;
  font-weight: 600;
  font-size: 16px;
}

.row-login:hover {
  background-color: #125a9c;
  border-color: #125a9c;
}

.options {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  font-size: 13px;
  color: #555;
}

.options a {
  color: #1976d2;
  text-decoration: none;
}

.options a:hover {
  text-decoration: underline;
}

.register span:nth-child(1) {
  color: #888;
  margin-right: 5px;
}
</style>

