<template>
  <div id="msg">
    <div class="title">
      <span>试卷列表</span>
      <span>/ {{ examData.source }}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{ examData.source }}</li>
        <li><i class="iconfont icon-pen-"></i></li>
        <li><i class="iconfont icon-share"></i></li>
        <li class="right">
          <div>
            <span class="count">总分</span>
            <span class="score">{{ score[0] + score[1] + score[2] }}</span>
          </div>
        </li>
      </ul>
      <ul class="bottom">
        <li>更新于{{ examData.examDate }}</li>
        <li>来自 {{ examData.institute }}</li>
        <li class="btn">{{ examData.type }}</li>
        <li class="right">
          <el-button @click="toAnswer(examData.examCode)">开始答题</el-button>
        </li>
      </ul>
      <ul class="info">
        <li @click="dialogVisible = true">
          <a href="javascript:;"><i class="iconfont icon-info"></i>考生须知</a>
        </li>
      </ul>
    </div>

    <!-- 卡片式题目展示 -->
    <div class="content">
      <div class="card" v-if="topic[1] && topic[1].length">
        <div class="card-header">
          <span>选择题</span>
          <span class="detail">共 {{ topicCount[0] }} 题 / {{ score[0] }} 分</span>
        </div>
        <ul class="card-body">
          <li v-for="(item, index) in topic[1]" :key="index">
            <span class="q-num">{{ index + 1 }}.</span>
            <span class="q-text">{{ item.question }}</span>
            <span class="q-score">（{{ item.score }} 分）</span>
          </li>
        </ul>
      </div>

      <div class="card" v-if="topic[2] && topic[2].length">
        <div class="card-header">
          <span>填空题</span>
          <span class="detail">共 {{ topicCount[1] }} 题 / {{ score[1] }} 分</span>
        </div>
        <ul class="card-body">
          <li v-for="(item, index) in topic[2]" :key="index">
            <span class="q-num">{{ topicCount[0] + index + 1 }}.</span>
            <span class="q-text">{{ item.question }}</span>
            <span class="q-score">（{{ item.score }} 分）</span>
          </li>
        </ul>
      </div>

      <div class="card" v-if="topic[3] && topic[3].length">
        <div class="card-header">
          <span>判断题</span>
          <span class="detail">共 {{ topicCount[2] }} 题 / {{ score[2] }} 分</span>
        </div>
        <ul class="card-body">
          <li v-for="(item, index) in topic[3]" :key="index">
            <span class="q-num">{{ topicCount[0] + topicCount[1] + index + 1 }}.</span>
            <span class="q-text">{{ item.question }}</span>
            <span class="q-score">（{{ item.score }} 分）</span>
          </li>
        </ul>
      </div>
    </div>

    <!-- 考生须知对话框 -->
    <el-dialog title="考生须知" :visible.sync="dialogVisible" width="30%">
      <span>{{ examData.tips }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">知道了</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      activeName: '0',
      topicCount: [],
      score: [],
      examData: {},
      topic: {}
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      let examCode = this.$route.query.examCode;
      this.$axios(`/api/exam/${examCode}`).then(res => {
        res.data.data.examDate = res.data.data.examDate.substr(0, 10);
        this.examData = { ...res.data.data };
        let paperId = this.examData.paperId;
        this.$axios(`/api/paper/${paperId}`).then(res => {
          this.topic = { ...res.data };
          let keys = Object.keys(this.topic);
          keys.forEach(e => {
            let data = this.topic[e];
            this.topicCount.push(data.length);
            let currentScore = 0;
            for (let i = 0; i < data.length; i++) {
              currentScore += data[i].score;
            }
            this.score.push(currentScore);
          });
        });
      });
    },
    toAnswer(id) {
      this.$router.push({ path: '/answer', query: { examCode: id } });
    }
  }
};
</script>

<style lang="less" scoped>
#msg {
  background-color: #f5f7fa;
  width: 1000px;
  margin: 0 auto;
  padding-bottom: 40px;
  font-family: 'Helvetica Neue', Arial, sans-serif;
}

#msg .title {
  margin: 24px 0;
  font-size: 22px;
  font-weight: 600;
  color: #333;
  padding-left: 10px;
}

.wrapper {
  background-color: #fff;
  border-radius: 10px;
  padding: 20px 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.wrapper .top,
.wrapper .bottom {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
}

.wrapper .top .example {
  font-size: 24px;
  font-weight: bold;
  color: #2c3e50;
}

.wrapper .top li i {
  font-size: 20px;
  color: #888;
  margin-left: 16px;
  cursor: pointer;
  transition: color 0.3s;
}

.wrapper .top li i:hover {
  color: #409EFF;
}

.wrapper .right div {
  display: flex;
  align-items: center;
}

.wrapper .count {
  padding: 5px 12px;
  background: #409EFF;
  color: #fff;
  border-radius: 6px 0 0 6px;
  font-size: 14px;
}

.wrapper .score {
  padding: 4px 16px;
  background: #e8f3ff;
  border: 1px dashed #409EFF;
  border-left: none;
  font-size: 18px;
  color: #409EFF;
  font-weight: bold;
  border-radius: 0 6px 6px 0;
}

.wrapper .bottom {
  margin: 20px 0;
  font-size: 14px;
  color: #666;
  gap: 12px;
}

.wrapper .bottom .btn {
  padding: 4px 10px;
  border-radius: 6px;
  background-color: #f0f2f5;
  color: #409EFF;
  border: 1px solid #c6e2ff;
  font-size: 13px;
}

.wrapper .bottom .right .el-button {
  color: #fff;
  background-color: #409EFF;
  border-color: #409EFF;
  padding: 6px 16px;
  border-radius: 6px;
}

.wrapper .info {
  border-top: 1px solid #eee;
  margin-top: 20px;
  padding-top: 14px;
}

.wrapper .info a {
  color: #666;
  font-size: 14px;
  text-decoration: none;
  transition: color 0.3s;
}

.wrapper .info a:hover {
  color: #409EFF;
}

/* 卡片式题目区域 */
.content {
  margin-top: 24px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card {
  background: #ffffff;
  border-radius: 10px;
  padding: 20px 24px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.04);
  border: 1px solid #eaeef2;
}

.card-header {
  display: flex;
  justify-content: space-between;
  font-size: 16px;
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 16px;
  border-bottom: 1px solid #eee;
  padding-bottom: 8px;
}

.card-header .detail {
  font-size: 14px;
  font-weight: 400;
  color: #888;
}

.card-body {
  list-style: none;
  padding-left: 0;
}

.card-body li {
  display: flex;
  align-items: flex-start;
  font-size: 15px;
  color: #444;
  margin-bottom: 12px;
  line-height: 1.6;
}

.q-num {
  font-weight: 600;
  margin-right: 6px;
  color: #409EFF;
}

.q-text {
  flex: 1;
}

.q-score {
  margin-left: 8px;
  color: #999;
  font-size: 13px;
}
</style>
