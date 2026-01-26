<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="100px" label-position="left" class="custom-form">
      <el-form-item label="姓名">
        <el-input v-model="form.studentName" placeholder="请输入学生姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
      </el-form-item>
      <el-form-item label="学院">
        <el-input v-model="form.institute" placeholder="请输入所属学院"></el-input>
      </el-form-item>
      <el-form-item label="所属专业">
        <el-input v-model="form.major" placeholder="请输入所属专业"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="form.grade" placeholder="请输入年级"></el-input>
      </el-form-item>
      <el-form-item label="班级">
        <el-input v-model="form.clazz" placeholder="请输入班级"></el-input>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="form.tel" placeholder="请输入电话号码"></el-input>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="form.cardId" placeholder="请输入身份证号"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" type="email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.pwd" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button type="text" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: {
        studentName: null,
        sex: null,
        institute: null,
        major: null,
        grade: null,
        clazz: null,
        tel: null,
        cardId: null,
        email: null,
        pwd: null,
        role: 2
      }
    }
  },
  methods: {
    onSubmit() {
      this.$axios({
        url: '/api/student',
        method: 'post',
        data: { ...this.form }
      }).then(res => {
        if (res.data.code === 200) {
          this.$message({
            message: '数据添加成功',
            type: 'success'
          });
          this.$router.push({ path: '/studentManage' });
        }
      });
    },
    cancel() {
      this.form = {
        studentName: null,
        sex: null,
        institute: null,
        major: null,
        grade: null,
        clazz: null,
        tel: null,
        cardId: null,
        email: null,
        pwd: null,
        role: 2
      };
    }
  }
}
</script>

<style lang="less" scoped>
.add {
  max-width: 460px;
  margin: 40px auto;
  padding: 30px 40px;
  background: #f5f8ff;
  border-radius: 12px;
  box-shadow: 0 6px 18px rgba(64,158,255,0.15);

  .custom-form {
    .el-form-item {
      margin-bottom: 20px;

      label {
        color: #34495e;
        font-weight: 600;
        font-size: 15px;
        width: 100px !important;
      }

      .el-input__inner {
        border-radius: 6px;
        border-color: #409EFF;
        font-size: 14px;
        padding: 8px 12px;
        transition: border-color 0.3s ease;

        &:focus, &:hover {
          border-color: #66b1ff;
          box-shadow: 0 0 6px rgba(102,177,255,0.5);
        }
      }
    }

    .btns {
      display: flex;
      gap: 18px;

      .el-button--primary {
        background-color: #409EFF;
        border-color: #409EFF;
        font-weight: 600;
        box-shadow: 0 3px 8px rgba(64,158,255,0.4);
        transition: background-color 0.3s ease;

        &:hover {
          background-color: #66b1ff;
          border-color: #66b1ff;
          box-shadow: 0 5px 12px rgba(102,177,255,0.6);
        }
      }

      .el-button--text {
        color: #409EFF;
        font-weight: 600;

        &:hover {
          color: #66b1ff;
          background-color: transparent;
        }
      }
    }
  }
}
</style>
