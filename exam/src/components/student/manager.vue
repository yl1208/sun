<template>
  <div id='manager'>
    <div class="form-card">
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
        <h3 class="alter">修改你的密码</h3>
        <el-form-item label="密码" prop="pass" class="pass">
          <el-input type="password" v-model="ruleForm2.pass" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm2.checkPass" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item class="btn-group">
          <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm2.checkPass !== '') {
          this.$refs.ruleForm2.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm2.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm2: {
        pass: '',
        checkPass: ''
      },
      rules2: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let studentId = this.$cookies.get("cid")
          this.$axios({
            url: '/api/studentPWD',
            method: 'put',
            data: {
              pwd: this.ruleForm2.pass,
              studentId
            }
          }).then(res => {
            if(res.data != null ) {
              this.$message({
                message: '密码修改成功...',
                type: 'success'
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
#manager {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f4f6f8;
  padding: 40px 0;
}

.form-card {
  background-color: #fff;
  width: 480px;
  padding: 40px 40px 30px;
  border-radius: 12px;
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.05);
  box-sizing: border-box;
}

.alter {
  margin-bottom: 24px;
  font-size: 22px;
  font-weight: 600;
  color: #2c3e50;
  text-align: center;
}

.pass label {
  color: #f56c6c;
  font-weight: bold;
}

.el-form-item {
  margin-bottom: 24px;
}

.el-input {
  font-size: 15px;
}

.btn-group {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.el-button {
  min-width: 100px;
  font-weight: 500;
}
</style>
