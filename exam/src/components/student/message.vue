<template>
  <div id="message">
    <div class="title">💬 给我留言</div>
    <div class="wrapper">
      <el-card class="input-card">
        <el-input
          placeholder="留言标题"
          v-model="title"
          clearable
          class="input-title">
        </el-input>

        <el-input
          type="textarea"
          :rows="4"
          placeholder="留言内容"
          v-model="content"
          clearable
          class="input-content">
        </el-input>

        <div class="btn">
          <el-button type="primary" icon="el-icon-edit-outline" @click="submit">提交留言</el-button>
        </div>
      </el-card>

      <div class="all">
        <el-card class="msg-card" shadow="hover" v-for="(data, index) in msg" :key="index" @mouseenter="enter(index)" @mouseleave="leave(index)">
          <div class="list">
            <p class="title">
              <i class="iconfont icon-untitled33"></i>{{ data.title }}
            </p>
            <p class="content">{{ data.content }}</p>
            <p class="date">
              <i class="iconfont icon-date"></i>{{ data.time }}
            </p>
            <div class="replies">
              <p class="comment" v-for="(replayData, index2) in data.replays" :key="index2">
                <i class="iconfont icon-huifuxiaoxi"></i>{{ replayData.replay }}
              </p>
            </div>
            <span class="replay" @click="replay(data.id)" v-if="flag && index === current">回复</span>
          </div>
        </el-card>
      </div>

      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[4, 6, 8, 10]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      flag: false,
      current: 0,
      title: "",
      content: "",
      pagination: {
        current: 1,
        total: null,
        size: 4
      },
      msg: []
    }
  },
  created() {
    this.getMsg()
  },
  methods: {
    getMsg() {
      this.$axios(`/api/messages/${this.pagination.current}/${this.pagination.size}`).then(res => {
        if (res.data.code === 200) {
          this.msg = res.data.data.records
          this.pagination = res.data.data
        }
      })
    },
    handleSizeChange(val) {
      this.pagination.size = val
      this.getMsg()
    },
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getMsg()
    },
    submit() {
      let date = new Date()
      if (this.title.trim() === '' || this.content.trim() === '') {
        this.$message({ type: 'error', message: '留言标题或内容不能为空' })
        return
      }
      this.$axios({
        url: "/api/message",
        method: "post",
        data: {
          title: this.title,
          content: this.content,
          time: date
        }
      }).then(res => {
        if (res.data.code === 200) {
          this.$message({ type: "success", message: "留言成功" })
          this.getMsg()
        }
      })
      this.title = ""
      this.content = ""
    },
    replay(messageId) {
      this.$prompt('回复留言', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[\s\S]*.*[^\s][\s\S]*$/,
        inputErrorMessage: '回复不能为空'
      }).then(({ value }) => {
        let date = new Date()
        this.$axios({
          url: '/api/replay',
          method: 'post',
          data: {
            replay: value,
            replayTime: date,
            messageId: messageId
          }
        }).then(() => this.getMsg())
        this.$message({ type: 'success', message: '回复成功' })
      }).catch(() => {
        this.$message({ type: 'info', message: '取消输入' })
      });
    },
    enter(index) {
      this.flag = true
      this.current = index
    },
    leave(index) {
      this.flag = false
      this.current = index
    }
  }
}
</script>

<style lang="less" scoped>
#message {
  max-width: 980px;
  margin: 0 auto;
  padding: 20px;
}

.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
  text-align: center;
}

.input-card {
  margin-bottom: 30px;
  padding: 20px;
  background-color: #fafafa;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}

.input-title,
.input-content {
  margin-bottom: 20px;
}

.btn {
  text-align: right;
}

.msg-card {
  margin-bottom: 20px;
}

.list {
  position: relative;
  font-size: 14px;

  .title {
    color: #409EFF;
    font-weight: 600;
    margin-bottom: 6px;
  }

  .content {
    margin-bottom: 10px;
    color: #666;
  }

  .date {
    font-size: 12px;
    color: #999;
  }

  .comment {
    font-size: 13px;
    color: #67C23A;
    margin-top: 6px;
    i {
      margin-right: 5px;
    }
  }

  .replay {
    position: absolute;
    bottom: 10px;
    right: 20px;
    font-size: 13px;
    color: tomato;
    cursor: pointer;
    transition: 0.2s;
  }

  .replay:hover {
    color: #ff694d;
  }
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
