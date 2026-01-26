//查询所有考试
<template>
  <div class="exam">
    <div class="wrapper">
      <ul class="top">
        <li class="order">试卷列表</li>
        <li class="search-li"><div class="icon"><input type="text" placeholder="试卷名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
        <li><el-button type="primary" @click="search()">搜索试卷</el-button></li>
      </ul>
    </div>

    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="source" label="试卷名称" width="180"></el-table-column>
      <el-table-column prop="description" label="介绍" width="200"></el-table-column>
      <el-table-column prop="institute" label="所属学院" width="120"></el-table-column>
      <el-table-column prop="major" label="所属专业" width="200"></el-table-column>
      <el-table-column prop="grade" label="年级" width="100"></el-table-column>
      <el-table-column prop="examDate" label="考试日期" width="120"></el-table-column>
      <el-table-column prop="totalTime" label="持续时间" width="120"></el-table-column>
      <el-table-column prop="totalScore" label="总分" width="120"></el-table-column>
      <el-table-column prop="type" label="试卷类型" width="120"></el-table-column>
      <el-table-column prop="tips" label="考生提示" width="400"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row.examCode)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteRecord(scope.row.examCode)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[4, 8, 10, 20]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total" class="page">
    </el-pagination>
    <!-- 编辑对话框-->
    <el-dialog
      title="编辑试卷信息"
      :visible.sync="dialogVisible"
      width="600px"
      :before-close="handleClose">
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
          <el-col :span="24" class="date-picker-col">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="form.examDate"
              style="width: 100%;">
            </el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="持续时间">
          <el-input v-model="form.totalTime" placeholder="请输入持续时间（分钟）"></el-input>
        </el-form-item>
        <el-form-item label="总分">
          <el-input v-model="form.totalScore" placeholder="请输入总分"></el-input>
        </el-form-item>
        <el-form-item label="试卷类型">
          <el-input v-model="form.type" placeholder="请输入考试类型"></el-input>
        </el-form-item>
        <el-form-item label="考生提示">
          <el-input type="textarea" v-model="form.tips" placeholder="请输入考生提示" :rows="4" autosize></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="submit">保存</el-button>
          <el-button type="text" @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script >
export default {
  data() {
    return {
      form: {}, //保存点击以后当前试卷的信息
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4 //每页条数
      },
      dialogVisible: false
    }
  },
  created() {
    this.getExamInfo()
  },
  methods: {
    //搜索试卷
    search() {
      this.$axios('/api/exams').then(res => {
        if(res.data.code == 200) {
          let allExam = res.data.data
          let newPage = allExam.filter(item => {
            return item.source.includes(this.key)
          })
          this.pagination.records = newPage
        }
      })
    },
    edit(examCode) { //编辑试卷
      this.dialogVisible = true
      this.$axios(`/api/exam/${examCode}`).then(res => { //根据试卷id请求后台
        if(res.data.code == 200) {
          this.form = res.data.data
        }
      })
    },
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
    submit() { //提交修改后的试卷信息
      this.dialogVisible = false
      this.$axios({
        url: '/api/exam',
        method: 'put',
        data: {
          ...this.form
        }
      }).then(res => {
        if(res.data.code == 200) {
          this.$message({ //成功修改提示
            message: '更新成功',
            type: 'success'
          })
        }
        this.getExamInfo()
      })
    },
    deleteRecord(examCode) {
      this.$confirm("确定删除该记录吗","删除提示",{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(()=> { //确认删除
        this.$axios({
          url: `/api/exam/${examCode}`,
          method: 'delete',
        }).then(res => {
          this.getExamInfo()
        })
      }).catch(() => {

      })
    },
    getExamInfo() { //分页查询所有试卷信息
      this.$axios(`/api/exams/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data
      }).catch(error => {
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
  },
};
</script>
<style lang="less">
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
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

</style>
<style lang="less" scoped>
.pagination {
  padding: 20px 0px 30px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.item .info i {
  margin-right: 5px;
  color: #0195ff;
}
.item .info span {
  margin-right: 14px;
}
.paper .item {
  width: 380px;
  border-radius: 4px;
  padding: 20px 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 14px;
  color: #88949b;
}
.paper .item .name {
  font-size: 14px;
  color: #88949b;
}
.paper * {
  margin: 20px 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}
.top .icon {
  position: relative;
}
.wrapper .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}
.search-li {
  margin-left: auto;
  padding-left: 740px;
}
.top .search-li {
  margin-left: auto;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
.wrapper .top {
  display: flex;
}
.wrapper .top li {
  margin: 20px;
}


 .wrapper {
  background-color: #fff;
}
</style>
