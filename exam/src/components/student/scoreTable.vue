<template>
  <div class="score-table">
    <h2 class="title">我的成绩</h2>
    <section class="content">
      <el-table
        ref="filterTable"
        :data="score"
        stripe
        border
        v-loading="loading"
        class="custom-table"
        style="width: 100%">

        <el-table-column
          prop="answerDate"
          label="考试日期"
          sortable
          min-width="180"
          column-key="answerDate"
          :filters="filter"
          :filter-method="filterHandler">
        </el-table-column>

        <el-table-column
          prop="subject"
          label="课程名称"
          min-width="180"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag type="info">{{ scope.row.subject }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          prop="etScore"
          label="考试分数"
          min-width="140">
        </el-table-column>

        <el-table-column
          label="是否及格"
          min-width="120">
          <template slot-scope="scope">
            <el-tag :type="scope.row.etScore >= 60 ? 'success' : 'danger'">
              {{ scope.row.etScore >= 60 ? '及格' : '不及格' }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>

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
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: {
        current: 1,
        total: null,
        size: 10
      },
      loading: false,
      score: [],
      filter: null
    }
  },
  created() {
    this.getScore()
    this.loading = true
  },
  methods: {
    getScore() {
      let studentId = this.$cookies.get("cid")
      this.$axios(`/api/score/${this.pagination.current}/${this.pagination.size}/${studentId}`).then(res => {
        if(res.data.code === 200) {
          this.loading = false
          this.score = res.data.data.records
          this.pagination = { ...res.data.data }

          let mapVal = this.score.map(item => ({
            text: item.answerDate,
            value: item.answerDate
          }))

          const seen = new Set()
          this.filter = mapVal.filter(item => {
            if (seen.has(item.text)) return false
            seen.add(item.text)
            return true
          })
        }
      })
    },
    handleSizeChange(val) {
      this.pagination.size = val
      this.getScore()
    },
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getScore()
    },
    filterHandler(value, row, column) {
      const property = column.property
      return row[property] === value
    }
  }
}
</script>

<style scoped>
.score-table {
  max-width: 100%;
  margin: 40px auto;
  padding: 0 20px;
}

.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #2c3e50;
  text-align: center;
}

.content {
  background-color: #fff;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  overflow-x: auto;
}

.custom-table {
  border-radius: 10px;
  overflow: hidden;
}

.pagination {
  display: flex;
  justify-content: center;
  padding: 20px 0 10px;
}
</style>
