<template>
  <div>
    <div>
      <label for="inputName">列表添加</label>
      
      <input  placeholder="请输入中文、英文"
        id="inputName"
        type="text"
        v-model.trim="message"
        @keyup.enter="
          addArray();
          emptyInput()
        "
      />

      <!-- .lazy为什么没有作用？ -->
      <button
        @click="
          addArray();
          emptyInput()
        "
      >
        确定
      </button>
    </div>
    <div></div>
    <div v-if="array" class="wrapper">
      <!-- 直接写 传入当前 v-for item的名子 -->
      <li class="item" v-for="(item, index) in array" :key="index"  @contextmenu.prevent.stop="deleteName(item)" @click="addArray1(item, index)" tilte="hello world">
        {{ item }}
      </li>
    </div>
    <div>
      <button @click="clearAllLocalStorage">清除所有人</button>
    </div>
    <div>已经添加的人：</div>

    <!-- 已经添加的人数 -->
    <div class="wrapper addedModule">
      <li v-for="(item, index) in array1" :key="index" @click="subArray1(item, index)">
        {{ item }}
      </li>
    </div>
    <div>人数是{{ this.array1.length }}</div>
  </div>
</template>

<script>
export default {
  // 请求已有的人员表

  created() {
    // 初始化赋值
    if (!localStorage.getItem('countID')) {
      localStorage.setItem('countID', 0)
    }
    this.countID = localStorage.getItem('countID')

    for (var i = 0; localStorage.getItem(i); i++) {
      this.array[i] = localStorage.getItem(i)
    }
  },

  data() {
    return {
      message: '',
      countID: 0,

      array: [],
      // 待添加的列表
      array1: []
      // 已添加的列表
    }
  },
  methods: {
    // 添加本地储存
    // 如果有同名的，那么添加失败
    addArray() {
      // 限制input输入框只能输入中文、数字、英文 

      if (this.isEnglishAndChinese(this.message)&&this.message!=null) {
        console.log("addArray",this.array)
        for (var i = 0; this.array[i]; i++) {
          if (this.array[i] == this.message) {
            alert(this.message + '  is already existed')
            return
          }
        }
        // 利用array即时渲染
        this.array[this.countID] = this.message
        console.log("addArray",this.array)
        // 设置计数数,名字到local

        localStorage.setItem(this.countID, this.message)
        this.countID++
        localStorage.setItem('countID', this.countID)
      }else {
          alert(this.message + ' 输入的不是中英文')
          return
        }
    },

    // 点击确定后，把input的placehold变成空白的

    emptyInput() {
      this.message = null
    },
    // 清除人，所有local Storage，array,array1
    clearAllLocalStorage() {
      this.array = []
      localStorage.clear()
      this.array1 = []
      this.countID = 0
    },

    // 增加到订饭组

    addArray1(item) {
      // 相同的不添加
      for (var i = 0; this.array1[i]; i++) {
        if (this.array1[i] == item) {
          alert('不要添加相同名字')
          return
        }
      }
      this.array1.push(item)
      console.log('add to array1 (already add array)',this.array1)
    },

    // 订饭组删除一个

    subArray1(item, index) {
      if (this.array1[index]) {
        this.array1.splice(index, 1)
        console.log("subArray1",this.array1)
      }
    },
    // judge is English Chinese or not
     isEnglishAndChinese(inputString) {
  var pattern = /^[\u4e00-\u9fffA-Za-z]+$/;
  return pattern.test(inputString);
},
deleteName(item){
  // 删除此元素，从localStoreage中和array中
  console.log(item)
//   var k;
// for(var i = this.countID;this.array[i]!=item;i--){
//   k = i;
// }
  localStorage.removeItem (10)
  if(this.countID>0){
    this.countID--
  }
 
  localStorage.setItem('countID', this.countID)
  console.log('sub  array (wait to add array)')

}
  }
}
</script>

<style>
/* 横着放的布局 */
.wrapper {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 50px;
}
.wrapper li {
  background-color: #3e3f4c;
  border: none;
  border-radius: 5px;
  font-size: 14px;
  cursor: pointer;
  height: 100px;
  list-style-type: none;
  text-align: center;
  color: #be98aa;
  line-height: 100px;
}
/* .addedModule{
  background-color: #C8C7C5;
}
.addedModule li {
  background-color:#002fa7
} */
</style>
