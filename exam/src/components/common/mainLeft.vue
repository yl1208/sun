<template>
  <div id="left">
    <el-menu
      active-text-color="#dd5862"
      text-color="#000"
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="flag"
      background-color="#020517"
      menu-trigger="click"
      router>

      <el-submenu v-for="(item, index) in filteredMenu" :index="item.index" :key="index">
        <template slot="title">
          <div class="left-width">
            <i class="iconfont" :class="item.icon"></i>
            <span class="title">{{ item.title }}</span>
          </div>
        </template>

        <el-menu-item-group v-for="(list, idx) in item.content" :key="idx">
          <el-menu-item
            v-if="list.item1 != null"
            class="LiColor"
            @click="handleTitle(item.index)"
            :index="list.path">
            {{ list.item1 }}
          </el-menu-item>
          <el-menu-item
            v-if="list.item2 != null"
            class="LiColor"
            @click="handleTitle(item.index)"
            :index="list.path">
            {{ list.item2 }}
          </el-menu-item>
          <el-menu-item
            v-if="list.item3 != null"
            class="LiColor"
            @click="handleTitle(item.index)"
            :index="list.path">
            {{ list.item3 }}
          </el-menu-item>
        </el-menu-item-group>

      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "mainLeft",
  computed: {
    ...mapState(['flag', 'menu']),
    filteredMenu() {
      const role = this.$cookies.get('role')
      // 教师角色（假设1为教师），过滤掉“教师管理”
      if (role === '1' || role === 1) {
        return this.menu.filter(item => item.title !== '教师管理')
      }
      // 其他角色显示全部菜单
      return this.menu
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      // 这里可以扩展
    },
    handleClose(key, keyPath) {
      // 这里可以扩展
    },
    handleTitle(index) {
      this.bus.$emit('sendIndex', index)
    }
  }
}
</script>

<style scoped>
.el-menu-vertical-demo .el-submenu__title {
  overflow: hidden;
}
.left-width .iconfont {
  font-size: 18px;
  color: #fff;
}
.left-width {
  width: 160px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  min-height: 900px;
}
#left {
  height: 900px;
  background-color: #020517;
  z-index: 0;
}
#left .el-menu-vertical-demo .title {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  margin-left: 14px;
}
.el-submenu {
  border-bottom: 1px solid #eeeeee0f !important;
}
.el-submenu__title:hover {
  background-color: #fff;
}
.el-submenu__title i {
  color: #fbfbfc !important;
}
.LiColor {
  color: #ffffff !important;
}
</style>
