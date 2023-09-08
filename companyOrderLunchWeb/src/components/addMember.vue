<template>
  <div>
    <div class="input">
      <el-input style="width: 20vw" v-model="input" placeholder="输入候选人"> </el-input>
      <el-button type="primary" class="addButton" @click="addMember()">添加</el-button>
    </div>

    <!-- 候选区域 -->
    <div>
      <div class="titleToMid">候选名单</div>
      <div class="grid-container">
        <li
          v-for="(item, index) in memberArray"
          :key="index"
          class="liCard"
          @click="addToOrderArray(item, index)"
          @contextmenu.prevent.stop="deleteFromMemberArray(item, index)"
        >
          {{ item }}
        </li>
      </div>
    </div>

    <!-- 划分线 -->
    <div class="box line2"></div>
    <!-- 已选区域-->
    <!-- 显示选的人 -->
    <div>
      <div class="titleToMid">已选名单</div>
      <div class="grid-container">
        <li
          v-for="(item, index) in orderArray"
          :key="index"
          class="liCard"
          @click="deleteFromOrderArray(item, index)"
        >
          {{ item }}
        </li>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  beforeMount() {
    // 初始化,加载memberArray orderArray
    console.log('getAllMembersAtBeginning')
    axios
      .get('http://192.168.3.139:8080/Member/getAllMember')
      .then((response) => {
        // Handle the response data
        // console.log("init data is"+response.data)

        // 提取对象数组
        // console.log(response.data[0])
        for (; this.i < response.data.length; this.i++) {
          this.memberArray[this.i] = response.data[this.i].name
          if (response.data[this.i].state == 1) {
            this.orderArray[this.i1++] = response.data[this.i].name
          }
          // console.log(this.memberArray[this.i])
        }
        // console.log(this.orderArray)
      })
      .catch((error) => {
        // console.log('hi')
        // Handle any errors
        console.error('init Array failed' + error)
      })
  },
  data() {
    return {
      i: 0,
      i1: 0,
      input: '',
      memberArray: ['未被', '初始化', '的', '对象'],
      // 候选名单
      orderArray: []
      // 已选名单
    }
  },
  methods: {
    // 在memberArray中删除人
    deleteFromMemberArray(item, index) {
      axios
          .get('http://192.168.3.139:8080/Member/deleteMember?name=' + item)
          .then((response) => {
            // Handle the response data
            console.log(response.status)
            this.memberArray.splice(index, 1)
            // 显示删除成功，到时候得到返回消息时才用
            this.$message({
              message: item + ' 删除成功',
              type: 'success'
            })
          })
          .catch((error) => {
            console.log(error)
            this.open('网络原因')
          })
    
    },
    // 在orderArray中删除人
    deleteFromOrderArray(item, index) {
      if (this.orderArray[index]) {
        axios
          .get('http://192.168.3.139:8080/Member/cancelMember?name=' + item)
          .then((response) => {
            // Handle the response data
            console.log(response.status)
            this.orderArray.splice(index, 1)
            // 显示删除成功，到时候得到返回消息时才用
            this.$message({
              message: item + ' 删除成功',
              type: 'success'
            })
          })
          .catch((error) => {
            console.log(error)
            this.open('网络原因')
          })

        return
      }
    },
    // 添加到已经选择的人
    addToOrderArray(item, index) {
      if (this.hasSameElement(item, this.orderArray)) {
        this.open('已经添加已选名单')
        this.clearInput()
        return
      }

      axios
        .get('http://192.168.3.139:8080/Member/chooseMember?name=' + item)
        .then((response) => {
          // Handle the response data
          console.log(response.status)
          this.orderArray.push(item)
          this.$message({
            message: item + ' 选择成功',
            type: 'success'
          })
        })
        .catch((error) => {
          console.log(error)
          this.open('网络原因')
        })
    },
    open(string) {
      this.$alert(this.input + string, '标题名称', {
        confirmButtonText: '确定'
      })
    },
    // 添加成员到后端
    addMember() {
      if (this.hasSameElement(this.input, this.memberArray)) {
        this.open('已经添加到候选名单')
        return
      }
      if (this.isEnglishAndChinese(this.input)) {
        // axios默认超时时间是 6s
        axios
          .get('http://192.168.3.139:8080/Member/createMember?name=' + this.input)
          .then((response) => {
            // Handle the response data
            // console.log(response.status)
            this.memberArray.push(this.input)
            console.log('memberArray is ' + this.memberArray)
            this.$message({
              message: this.input + ' 添加成功',
              type: 'success'
            })
            this.clearInput()
          })
          .catch((error) => {
            console.log(error)
            this.open('网络原因')
          })
      } else {
        this.open('格式不是中英文')
      }
    },
    // 清空 input
    clearInput() {
      this.input = ''
    },
    // 过滤input

    isEnglishAndChinese(inputString) {
      var pattern = /^[\u4e00-\u9fffA-Za-z]+$/
      return pattern.test(inputString)
    },
    // judge same element
    hasSameElement(stringInput, array) {
      for (var i = 0; i < array.length; i++) {
        if (array[i] == stringInput) {
          return true
        }
      }
      return false
    }
  }
}
</script>

<style>
.input {
  width: 10%;
  position: relative;
}

.addButton {
  left: 202%;
  top: 0;
  position: absolute;
}

.grid-container {
  margin: 60px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 20px;
  /*  声明行的高度  */
  grid-template-rows: 100px 100px 100px 100px;
}

.liCard {
  cursor: pointer;
  caret-color: transparent;
  /* 取消光标 */
  list-style-type: none;
  height: 80px;
  text-align: center;
  line-height: 80px;
  color: white;

  background: rgba(255, 255, 255, 0.2);
  -webkit-backdrop-filter: blur(8px);
  backdrop-filter: blur(8px);
  border-radius: 25px;
  box-shadow: inset 0 0 6px rgba(255, 255, 255, 0.2);
}
.box {
  height: 100px;
  line-height: 100px;
  margin-bottom: 20px;
}
.line2 {
  background: linear-gradient(to left, transparent 0%, transparent 50%, #ccc 50%, #ccc 100%);
  background-size: 10px 1px;
  background-repeat: repeat-x;
}
.titleToMid {
  text-align: center;
  color: white;
  font-size: large;
}
</style>
