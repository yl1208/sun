<template>
  <div id="worksheet">
    <!--顶部信息栏-->
    <div class="header">
      <ul class="nav">
        <li><i class="iconfont icon-menufold icon20" ref="toggle" @click="slider_flag = !slider_flag"></i></li>
        <li>{{ examData.type }}-{{ examData.source }}</li>
        <li><i class="iconfont icon-arrRight icon20"></i></li>
        <li class="user-info-item" @click="flag = !flag">
          <i class="iconfont icon-user icon20"></i>
          <div class="user-info" v-if="flag" @click="flag = !flag">
            <p>姓名：{{ userInfo.name }}</p>
            <p>准考证号: {{ userInfo.id }}</p>
          </div>
        </li>
      </ul>
    </div>
    <div class="main-container">
      <!--左边题目编号区-->
      <transition name="slider-fade">
        <div class="sidebar" v-if="slider_flag">
          <ul class="sidebar-options">
            <li><a href="javascript:;" class="current"></a><span>当前</span></li>
            <li><a href="javascript:;" class="unanswered"></a><span>未答</span></li>
            <li><a href="javascript:;" class="answered"></a><span>已答</span></li>
            <li><a href="javascript:;" class="marked"></a><span>标记</span></li>
          </ul>
          <div class="sidebar-bottom">
            <div class="section">
              <p>选择题部分</p>
              <ul>
                <li v-for="(list, index1) in topic[1]" :key="index1">
                  <a href="javascript:;" @click="change(index1)"
                     :class="{ active: index === index1 && currentType === 1, answered: bg_flag && topic[1][index1].isClick === true }">
                    <span :class="{ marked: topic[1][index1].isMark === true }"></span>
                    {{ index1 + 1 }}
                  </a>
                </li>
              </ul>
            </div>
            <div class="section">
              <p>填空题部分</p>
              <ul>
                <li v-for="(list, index2) in topic[2]" :key="index2">
                  <a href="javascript:;" @click="fill(index2)"
                     :class="{ active: index === index2 && currentType === 2, answered: fillAnswer[index2] && fillAnswer[index2][3] === true }">
                    <span :class="{ marked: topic[2][index2].isMark === true }"></span>
                    {{ topicCount[0] + index2 + 1 }}
                  </a>
                </li>
              </ul>
            </div>
            <div class="section">
              <p>判断题部分</p>
              <ul>
                <li v-for="(list, index3) in topic[3]" :key="index3">
                  <a href="javascript:;" @click="judge(index3)"
                     :class="{ active: index === index3 && currentType === 3, answered: bg_flag && topic[3][index3].isClick === true }">
                    <span :class="{ marked: topic[3][index3].isMark === true }"></span>
                    {{ topicCount[0] + topicCount[1] + index3 + 1 }}
                  </a>
                </li>
              </ul>
            </div>
            <button class="submit-btn" @click="commit(examData.examCode)">结束考试</button>
          </div>
        </div>
      </transition>
      <!--右边选择答题区-->
      <transition name="slider-fade">
        <div class="content-area">
          <div class="content-header">
            <p>{{ title }}</p>
            <i class="iconfont icon-right auto-right"></i>
            <span>全卷共{{ topicCount[0] + topicCount[1] + topicCount[2] }}题 <i class="iconfont icon-time"></i>倒计时：<b>{{ time }}</b>分钟</span>
          </div>
          <div class="question-content">
            <p class="question"><span class="question-number">{{ number }}</span>{{ showQuestion }}</p>
            <div v-if="currentType === 1">
              <el-radio-group v-model="radio[index]" @change="getChangeLabel">
                <el-radio :label="1">{{ showAnswer.answerA }}</el-radio>
                <el-radio :label="2">{{ showAnswer.answerB }}</el-radio>
                <el-radio :label="3">{{ showAnswer.answerC }}</el-radio>
                <el-radio :label="4">{{ showAnswer.answerD }}</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li><el-tag type="success">正确答案：</el-tag><span class="correct-answer">{{ reduceAnswer.rightAnswer }}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{ reduceAnswer.analysis == null ? '暂无解析' : reduceAnswer.analysis }}</li>
                </ul>
              </div>
            </div>
            <div class="fill" v-if="currentType === 2">
              <div v-for="(item, currentIndex) in part" :key="currentIndex">
                <el-input placeholder="请填写答案"
                          v-model="fillAnswer[index][currentIndex]"
                          clearable
                          @blur="fillBG">
                </el-input>
              </div>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li><el-tag type="success">正确答案：</el-tag><span class="correct-answer">{{ topic[2][index].answer }}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{ topic[2][index].analysis == null ? '暂无解析' : topic[2][index].analysis }}</li>
                </ul>
              </div>
            </div>
            <div class="judge" v-if="currentType === 3">
              <el-radio-group v-model="judgeAnswer[index]" @change="getJudgeLabel">
                <el-radio :label="1">正确</el-radio>
                <el-radio :label="2">错误</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li><el-tag type="success">正确答案：</el-tag><span class="correct-answer">{{ topic[3][index].answer }}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{ topic[3][index].analysis == null ? '暂无解析' : topic[3][index].analysis }}</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="controls">
            <ul class="control-buttons">
              <li @click="previous()"><i class="iconfont icon-previous"></i><span>上一题</span></li>
              <li @click="mark()"><i class="iconfont icon-mark-o"></i><span>标记</span></li>
              <li @click="next()"><span>下一题</span><i class="iconfont icon-next"></i></li>
            </ul>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  data() {
    return {
      startTime: null,
      endTime: null,
      time: null,
      reduceAnswer: [],
      answerScore: 0,
      bg_flag: false,
      isFillClick: false,
      slider_flag: true,
      flag: false,
      currentType: 1,
      radio: [],
      title: "请选择正确的选项",
      index: 0,
      userInfo: {
        name: null,
        id: null
      },
      topicCount: [],
      score: [],
      examData: {},
      topic: {},
      showQuestion: [],
      showAnswer: {},
      number: 1,
      part: null,
      fillAnswer: [[]],
      judgeAnswer: [],
      topic1Answer: [],
      rightAnswer: ''
    }
  },
  created() {
    this.getCookies()
    this.getExamData()
    this.showTime()
  },
  methods: {
    getTime(date) {
      let year = date.getFullYear()
      let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    },
    getCookies() {
      this.userInfo.name = this.$cookies.get("cname")
      this.userInfo.id = this.$cookies.get("cid")
    },
    calcuScore() {},
    getExamData() {
      let date = new Date()
      this.startTime = this.getTime(date)
      let examCode = this.$route.query.examCode
      this.$axios(`/api/exam/${examCode}`).then(res => {
        this.examData = {...res.data.data}
        this.index = 0
        this.time = this.examData.totalScore
        let paperId = this.examData.paperId
        this.$axios(`/api/paper/${paperId}`).then(res => {
          this.topic = {...res.data}
          let reduceAnswer = this.topic[1][this.index]
          this.reduceAnswer = reduceAnswer
          let keys = Object.keys(this.topic)
          keys.forEach(e => {
            let data = this.topic[e]
            this.topicCount.push(data.length)
            let currentScore = 0
            for (let i = 0; i < data.length; i++) {
              currentScore += data[i].score
            }
            this.score.push(currentScore)
          })
          let len = this.topicCount[1]
          let father = []
          for (let i = 0; i < len; i++) {
            let children = [null, null, null, null]
            father.push(children)
          }
          this.fillAnswer = father
          let dataInit = this.topic[1]
          this.number = 1
          this.showQuestion = dataInit[0].question
          this.showAnswer = dataInit[0]
        })
      })
    },
    change(index) {
      this.index = index
      let reduceAnswer = this.topic[1][this.index]
      this.reduceAnswer = reduceAnswer
      this.isFillClick = true
      this.currentType = 1
      let len = this.topic[1].length
      if (this.index < len) {
        if (this.index <= 0) {
          this.index = 0
        }
        this.title = "请选择正确的选项"
        let Data = this.topic[1]
        this.showQuestion = Data[this.index].question
        this.showAnswer = Data[this.index]
        this.number = this.index + 1
      } else if (this.index >= len) {
        this.index = 0
        this.fill(this.index)
      }
    },
    fillBG() {
      if (this.fillAnswer[this.index][0] != null) {
        this.fillAnswer[this.index][3] = true
      }
    },
    fill(index) {
      let len = this.topic[2].length
      this.currentType = 2
      this.index = index
      if (index < len) {
        if (index < 0) {
          index = this.topic[1].length - 1
          this.change(index)
        } else {
          this.title = "请在横线处填写答案"
          let Data = this.topic[2]
          this.showQuestion = Data[index].question
          let part = this.showQuestion.split("()").length - 1
          this.part = part
          this.number = this.topicCount[0] + index + 1
        }
      } else if (index >= len) {
        this.index = 0
        this.judge(this.index)
      }
    },
    judge(index) {
      let len = this.topic[3].length
      this.currentType = 3
      this.index = index
      if (this.index < len) {
        if (this.index < 0) {
          this.index = this.topic[2].length - 1
          this.fill(this.index)
        } else {
          this.title = "请作出正确判断"
          let Data = this.topic[3]
          this.showQuestion = Data[index].question
          this.number = this.topicCount[0] + this.topicCount[1] + index + 1
        }
      } else if (this.index >= len) {
        this.index = 0
        this.change(this.index)
      }
    },
    getChangeLabel(val) {
      this.radio[this.index] = val
      if (val) {
        let data = this.topic[1]
        this.bg_flag = true
        data[this.index]["isClick"] = true
      }
      this.topic1Answer[this.index] = val
    },
    getJudgeLabel(val) {
      this.judgeAnswer[this.index] = val
      if (val) {
        let data = this.topic[3]
        this.bg_flag = true
        data[this.index]["isClick"] = true
      }
    },
    previous() {
      this.index--
      switch (this.currentType) {
        case 1:
          this.change(this.index)
          break
        case 2:
          this.fill(this.index)
          break
        case 3:
          this.judge(this.index)
          break
      }
    },
    next() {
      this.index++
      switch (this.currentType) {
        case 1:
          this.change(this.index)
          break
        case 2:
          this.fill(this.index)
          break
        case 3:
          this.judge(this.index)
          break
      }
    },
    mark() {
      switch (this.currentType) {
        case 1:
          this.topic[1][this.index]["isMark"] = true
          break
        case 2:
          this.topic[2][this.index]["isMark"] = true
          break
        case 3:
          this.topic[3][this.index]["isMark"] = true
      }
    },
    commit(id) {
      let topic1Answer = this.topic1Answer
      let finalScore = 0
      topic1Answer.forEach((element, index) => {
        let right = null
        if (element != null) {
          switch (element) {
            case 1:
              right = "A"
              break
            case 2:
              right = "B"
              break
            case 3:
              right = "C"
              break
            case 4:
              right = "D"
          }
          if (right === this.topic[1][index].rightAnswer) {
            finalScore += this.topic[1][index].score
          }
        }
      })
      let fillAnswer = this.fillAnswer
      fillAnswer.forEach((element, index) => {
        element.forEach((inner) => {
          if (this.topic[2][index].answer.includes(inner)) {
            finalScore += this.topic[2][index].score
          }
        })
      });
      let topic3Answer = this.judgeAnswer
      topic3Answer.forEach((element, index) => {
        let right = null
        switch (element) {
          case 1:
            right = "T"
            break
          case 2:
            right = "F"
        }
        if (right === this.topic[3][index].answer) {
          finalScore += this.topic[3][index].score
        }
      })
      if (this.time !== 0) {
        this.$confirm("考试结束时间未到,是否提前交卷", "友情提示", {
          confirmButtonText: '立即交卷',
          cancelButtonText: '再检查一下',
          type: 'warning'
        }).then(() => {
          let date = new Date()
          this.endTime = this.getTime(date)
          let answerDate = this.endTime.substr(0, 10)
          this.$axios({
            url: '/api/score',
            method: 'post',
            data: {
              examCode: this.examData.examCode,
              studentId: this.userInfo.id,
              subject: this.examData.source,
              etScore: finalScore,
              answerDate: answerDate,
            }
          }).then(res => {
            if (res.data.code == 200) {
              this.$router.push({
                path: '/studentScore', query: {
                  examCode: id,
                  score: finalScore,
                  startTime: this.startTime,
                  endTime: this.endTime
                }
              })
            }
          })
        }).catch(() => {})
      }
    },
    showTime() {
      setInterval(() => {
        this.time -= 1
        if (this.time == 10) {
          this.$message({
            showClose: true,
            type: 'error',
            message: '考生注意,考试时间还剩10分钟！！！'
          })
          if (this.time == 0) {
            console.log("考试时间已到,强制交卷。")
          }
        }
      }, 1000 * 60)
    }
  },
  computed: mapState(["isPractice"])
}
</script>

<style lang="less">
#worksheet {
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  background: #f0f2f5;
  min-height: 100vh;
  padding-bottom: 40px;
}

.header {
  background: linear-gradient(90deg, #1e3a8a, #3b82f6);
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px 24px;
  color: #fff;
  font-size: 16px;
  max-width: 1280px;
  margin: 0 auto;

  li {
    display: flex;
    align-items: center;
    margin-right: 24px;
    cursor: pointer;
    transition: opacity 0.3s;

    &:hover {
      opacity: 0.85;
    }

    &.user-info-item {
      margin-left: auto;
      margin-right: 0;
    }
  }
}

.user-info {
  position: absolute;
  top: 48px;
  right: 16px;
  background: #fff;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 6px 16px rgba(0,0,0,0.12);
  color: #1f2937;
  font-size: 14px;
  min-width: 220px;
  z-index: 1000;

  p {
    margin: 8px 0;
    line-height: 1.6;
    font-weight: 500;
  }
}

.main-container {
  display: flex;
  max-width: 1280px;
  margin: 24px auto;
  gap: 24px;
  padding: 0 16px;
}

.sidebar {
  width: 300px;
  flex-shrink: 0;
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);

  .sidebar-options {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 12px;
    padding: 16px;
    background: #f9fafb;
    border-bottom: 1px solid #e5e7eb;

    li {
      display: flex;
      flex-direction: column;
      align-items: center;
      font-size: 12px;
      color: #4b5563;

      a {
        width: 36px;
        height: 36px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 6px;
        transition: all 0.3s;

        &.current {
          background: #f97316;
          color: #fff;
        }

        &.unanswered {
          background: #d1d5db;
        }

        &.answered {
          background: #3b82f6;
          color: #fff;
        }

        &.marked {
          background: #ef4444;
          color: #fff;
          position: relative;

          &::before {
            content: '';
            width: 8px;
            height: 8px;
            background: #f97316;
            border-radius: 50%;
            position: absolute;
            top: 0;
            right: 0;
          }
        }
      }
    }
  }

  .sidebar-bottom {
    padding: 16px;

    .section {
      margin-bottom: 24px;

      p {
        font-size: 16px;
        font-weight: 600;
        color: #1f2937;
        margin-bottom: 12px;
        padding-left: 12px;
        border-left: 4px solid #3b82f6;
      }

      ul {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(44px, 1fr));
        gap: 10px;

        li {
          a {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 40px;
            height: 40px;
            border-radius: 8px;
            border: 1px solid #e5e7eb;
            background: #fff;
            color: #1f2937;
            font-size: 14px;
            position: relative;
            transition: all 0.3s;

            &.active {
              border: 2px solid #f97316;
              font-weight: 600;
              background: #fff7ed;
            }

            &.answered {
              background: #3b82f6;
              color: #fff;
              border: none;
            }

            .marked {
              position: absolute;
              top: 0;
              right: 0;
              width: 8px;
              height: 8px;
              background: #f97316;
              border-radius: 50%;
            }
          }
        }
      }
    }

    .submit-btn {
      width: 100%;
      padding: 12px;
      background: #f97316;
      color: #fff;
      border: none;
      border-radius: 8px;
      font-size: 16px;
      font-weight: 500;
      cursor: pointer;
      transition: background 0.3s;

      &:hover {
        background: #ea580c;
      }
    }
  }
}

.content-area {
  flex: 1;
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);

  .content-header {
    display: flex;
    align-items: center;
    background: #f9fafb;
    padding: 16px 24px;
    border-bottom: 1px solid #e5e7eb;

    p {
      font-size: 18px;
      font-weight: 600;
      color: #1f2937;
      flex: 1;
    }

    span {
      font-size: 14px;
      color: #4b5563;

      .icon-time {
        color: #3b82f6;
        margin: 0 6px;
      }

      b {
        color: #f97316;
        font-weight: 600;
      }
    }

    .auto-right {
      color: #3b82f6;
      margin-left: 12px;
    }
  }

  .question-content {
    padding: 24px;
    min-height: 400px;

    .question {
      display: flex;
      align-items: flex-start;
      margin-bottom: 24px;
      font-size: 16px;
      color: #1f2937;
      line-height: 1.6;

      .question-number {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        width: 28px;
        height: 28px;
        background: #3b82f6;
        color: #fff;
        border-radius: 6px;
        margin-right: 12px;
        font-size: 14px;
        font-weight: 500;
        flex-shrink: 0;
      }
    }

    .el-radio-group {
      display: flex;
      flex-direction: column;
      gap: 12px;

      .el-radio {
        font-size: 15px;
        color: #1f2937;
        line-height: 1.6;
        padding: 8px;
        border-radius: 6px;
        transition: background 0.3s;

        &:hover {
          background: #f9fafb;
        }
      }
    }

    .fill .el-input {
      width: 240px;
      margin: 12px 0;

      .el-input__inner {
        border: none;
        border-bottom: 2px solid #e5e7eb;
        border-radius: 0;
        padding-left: 12px;
        font-size: 15px;
        transition: border-color 0.3s;

        &:focus {
          border-bottom: 2px solid #3b82f6;
        }
      }
    }

    .analysis {
      margin-top: 24px;
      padding: 16px;
      background: #f9fafb;
      border-radius: 8px;
      border: 1px solid #e5e7eb;

      ul {
        li {
          margin: 12px 0;
          font-size: 14px;
          color: #1f2937;

          .correct-answer {
            color: #3b82f6;
            font-weight: 500;
            margin-left: 12px;
            padding: 4px 10px;
            border: 1px solid #3b82f6;
            border-radius: 6px;
          }

          &:nth-child(2) {
            font-weight: 500;
            margin: 16px 0;
          }

          &:nth-child(3) {
            background: #fff;
            padding: 12px;
            border-radius: 6px;
            line-height: 1.6;
          }
        }
      }
    }
  }

  .controls {
    padding: 16px 24px;
    background: #f9fafb;
    border-top: 1px solid #e5e7eb;

    .control-buttons {
      display: flex;
      justify-content: center;
      gap: 32px;

      li {
        display: flex;
        align-items: center;
        padding: 10px 24px;
        border-radius: 8px;
        cursor: pointer;
        transition: all 0.3s;
        font-size: 14px;
        color: #1f2937;
        background: #fff;
        border: 1px solid #e5e7eb;

        i {
          margin: 0 6px;
          color: #3b82f6;
        }

        &:hover {
          background: #f3f4f6;
          border-color: #3b82f6;
        }

        &:nth-child(2) {
          background: #3b82f6;
          color: #fff;
          border: none;
          width: 64px;
          height: 64px;
          border-radius: 50%;
          flex-direction: column;
          justify-content: center;
          align-items: center;

          i {
            color: #fff;
            font-size: 20px;
          }

          &:hover {
            background: #2563eb;
          }
        }
      }
    }
  }
}

.slider-fade-enter-active,
.slider-fade-leave-active {
  transition: all 0.3s ease;
}

.slider-fade-enter,
.slider-fade-leave-to {
  transform: translateX(-30px);
  opacity: 0;
}

.icon20 {
  font-size: 20px;
  font-weight: 600;
}
</style>
