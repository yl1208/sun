<template>
  <div id="myExam">
    <h2 class="title">我的试卷</h2>

    <div class="wrapper">
      <!-- 顶部搜索栏 -->
      <div class="top">
        <div class="order">试卷列表</div>
        <div class="search-wrapper">
          <input
            type="text"
            v-model="key"
            placeholder="试卷名称"
            @keyup.enter="search"
            class="search-input"
          />
          <i class="el-icon-search search-icon" @click="search"></i>
        </div>
        <el-button type="primary" class="search-btn" @click="search">搜索试卷</el-button>
      </div>

      <!-- 试卷卡片列表 -->
      <div class="paper" v-loading="loading" v-if="pagination.records.length">
        <div
          class="item"
          v-for="item in pagination.records"
          :key="item.examCode"
          @click="toExamMsg(item.examCode)"
        >
          <h3 class="item-title">{{ item.source }}</h3>
          <p class="item-desc">{{ item.source }} - {{ item.description }}</p>
          <div class="item-info">
            <span><i class="el-icon-date"></i> {{ item.examDate.substr(0, 10) }}</span>
            <span v-if="item.totalTime !== null">
              <i class="iconfont icon-icon-time"></i> 限时{{ item.totalTime }}分钟
            </span>
            <span><i class="iconfont icon-fenshu"></i> 满分{{ item.totalScore }}分</span>
          </div>
        </div>
      </div>

      <div v-else class="empty">暂无试卷数据</div>

      <!-- 分页 -->
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-size="pagination.size"
          :page-sizes="[6, 10, 20, 40]"
          :total="pagination.total"
          layout="total, sizes, prev, pager, next, jumper"
          background
          small
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      key: '',
      pagination: {
        current: 1,
        size: 6,
        total: 0,
        records: []
      }
    }
  },
  created() {
    this.loading = true
    this.getExamInfo()
  },
  methods: {
    getExamInfo() {
      this.$axios(`/api/exams/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data
        this.loading = false
      })
    },
    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
    search() {
      this.loading = true
      this.$axios('/api/exams').then(res => {
        this.loading = false
        if (res.data.code === 200) {
          const filtered = res.data.data.filter(item =>
            item.source.toLowerCase().includes(this.key.trim().toLowerCase())
          )
          this.pagination.records = filtered
          this.pagination.total = filtered.length
          this.pagination.current = 1
          this.pagination.size = 6
        }
      })
    },
    toExamMsg(code) {
      this.$router.push({ path: '/examMsg', query: { examCode: code } })
    }
  }
}
</script>

<style lang="less" scoped>
#myExam {
  padding: 32px 48px;
  background: #f0f2f5;
  min-height: 100vh;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.title {
  font-size: 30px;
  font-weight: 700;
  color: #222;
  margin-bottom: 28px;
  user-select: none;
}

.wrapper {
  background: #fff;
  padding: 28px 32px;
  border-radius: 12px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.07);
  display: flex;
  flex-direction: column;
  min-height: 640px;
}

/* 顶部搜索栏 */
.top {
  display: flex;
  align-items: center;
  gap: 16px;
  border-bottom: 1px solid #ddd;
  padding-bottom: 18px;
  margin-bottom: 32px;
  flex-wrap: wrap; /* 允许换行，防止溢出 */
}

.order {
  font-size: 20px;
  font-weight: 600;
  color: #34495e;
  white-space: nowrap;
  flex-shrink: 0; /* 固定宽度，不缩小 */
}

.search-wrapper {
  position: relative;
  width: 300px; /* 固定宽度 */
  flex-shrink: 0; /* 不缩小 */
}

.search-input {
  width: 100%;
  padding: 10px 42px 10px 16px;
  border: 1.8px solid #ccc;
  border-radius: 8px;
  font-size: 15px;
  transition: border-color 0.3s ease;
  outline: none;
  box-sizing: border-box;
}

.search-input:focus {
  border-color: #409eff;
  box-shadow: 0 0 10px #409eff88;
}

.search-icon {
  position: absolute;
  right: 14px;
  top: 50%;
  transform: translateY(-50%);
  font-size: 19px;
  color: #888;
  cursor: pointer;
  user-select: none;
}

.search-btn {
  height: 38px;
  padding: 0 28px;
  font-weight: 600;
  border-radius: 8px;
  white-space: nowrap;
  flex-shrink: 0; /* 不缩小 */
}

/* 试卷列表 grid 布局 */
.paper {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px 20px;
  flex-grow: 1;
  overflow-y: auto;
  min-height: 320px;
}

/* 单张试卷卡片 */
.item {
  background: #fff;
  border-radius: 14px;
  border: 1.5px solid #e1e4e8;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  padding: 22px 26px;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  transition: box-shadow 0.3s ease, transform 0.3s ease;
}

.item:hover {
  box-shadow: 0 8px 28px rgba(1, 149, 255, 0.25);
  transform: translateY(-5px);
}

.item-title {
  font-size: 22px;
  font-weight: 700;
  color: #1a202c;
  margin-bottom: 10px;
  border-bottom: 2px solid transparent;
  padding-bottom: 4px;
  user-select: none;
  transition: border-color 0.3s ease;
}

.item:hover .item-title {
  border-color: #409eff;
}

.item-desc {
  font-size: 14px;
  color: #6c757d;
  margin-bottom: 18px;
  flex-grow: 1;
  line-height: 1.45;
  user-select: text;
}

.item-info {
  font-size: 13px;
  color: #9ca3af;
  display: flex;
  gap: 20px;
  align-items: center;
  user-select: none;
}

.item-info i {
  font-size: 16px;
  color: #3b82f6;
  vertical-align: middle;
}

.item-info span {
  vertical-align: middle;
  user-select: text;
}

/* 分页 */
.pagination {
  margin-top: 32px;
  display: flex;
  justify-content: center;
}

/* 空状态 */
.empty {
  flex-grow: 1;
  font-size: 18px;
  color: #999;
  text-align: center;
  margin-top: 60px;
  user-select: none;
}
</style>
