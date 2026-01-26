<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="100px" label-position="left" class="custom-form">
      <el-form-item label="试卷名称">
        <el-input v-model="form.source" placeholder="请输入试卷名称"></el-input>
      </el-form-item>
      <el-form-item label="介绍">
        <el-input v-model="form.description" placeholder="请输入介绍"></el-input>
      </el-form-item>
      <el-form-item label="所属学院">
        <el-input v-model="form.institute" placeholder="请输入所属学院"></el-input>
      </el-form-item>
      <el-form-item label="所属专业">
        <el-input v-model="form.major" placeholder="请输入所属专业"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="form.grade" placeholder="请输入年级"></el-input>
      </el-form-item>
      <el-form-item label="考试日期">
        <el-col :span="11" class="date-picker-col">
          <el-date-picker
            v-model="form.examDate"
            type="date"
            placeholder="选择日期"
            style="width: 100%;"
          ></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="持续时间">
        <el-input v-model="form.totalTime" placeholder="请输入持续时间（分钟）"></el-input>
      </el-form-item>
      <el-form-item label="总分">
        <el-input v-model="form.totalScore" placeholder="请输入总分"></el-input>
      </el-form-item>
      <el-form-item label="考试类型">
        <el-input v-model="form.type" placeholder="请输入考试类型"></el-input>
      </el-form-item>
      <el-form-item label="考生提示">
        <el-input
          type="textarea"
          v-model="form.tips"
          placeholder="请输入考生提示"
          :rows="4"
          autosize
        ></el-input>
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
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      }
    };
  },
  methods: {
    formatTime(date) {
      let year = date.getFullYear();
      let month = (date.getMonth() + 1).toString().padStart(2, '0');
      let day = date.getDate().toString().padStart(2, '0');
      return `${year}-${month}-${day}`;
    },
    onSubmit() {
      if (this.form.examDate) {
        this.form.examDate = this.formatTime(this.form.examDate);
      }
      this.$axios('/api/examManagePaperId').then(res => {
        this.form.paperId = res.data.data.paperId + 1;
        this.$axios({
          url: '/api/exam',
          method: 'post',
          data: {...this.form}
        }).then(res => {
          if (res.data.code === 200) {
            this.$message({
              message: '数据添加成功',
              type: 'success'
            });
            this.$router.push({ path: '/selectExam' });
          }
        });
      });
    },
    cancel() {
      this.form = {
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      };
    }
  }
};
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

      .el-input__inner,
      .el-textarea__inner {
        border-radius: 6px;
        border-color: #409EFF;
        font-size: 14px;
        padding: 8px 12px;
        transition: border-color 0.3s ease;

        &:focus,
        &:hover {
          border-color: #66b1ff;
          box-shadow: 0 0 6px rgba(102,177,255,0.5);
        }
      }
    }

    .date-picker-col {
      .el-date-editor {
        width: 100%;
        border-radius: 6px;
        border-color: #409EFF;
        transition: border-color 0.3s ease;
      }
      .el-date-editor:hover,
      .el-date-editor:focus-within {
        border-color: #66b1ff;
        box-shadow: 0 0 6px rgba(102,177,255,0.5);
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
