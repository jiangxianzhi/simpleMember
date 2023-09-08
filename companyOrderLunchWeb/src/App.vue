<script>
import addMember from './components/addMember.vue'
export default {
  components: {addMember}
}
</script>

<template>
  <div class="background">hello world

<addMember></addMember>


  </div>
</template>

<style scoped>
.background {
  width: 100%;
  height: auto;
  position: absolute;
  background-image: linear-gradient(
    to right top,
    #d16ba5,
    #c777b9,
    #ba83ca,
    #aa8fd8,
    #9a9ae1,
    #8aa7ec,
    #79b3f4,
    #69bff8,
    #52cffe,
    #41dfff,
    #46eefa,
    #5ffbf1
  );
}
</style>
