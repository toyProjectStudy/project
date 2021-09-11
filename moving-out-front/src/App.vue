<template>
  <div id="app">
    <CommonNavBar/>
    <Main/>
<!--    <div id="nav">-->
<!--      <router-link to="/">Home</router-link> |-->
<!--      <router-link to="/about">About</router-link>-->
<!--    </div>-->
<!--    <router-view/>-->
  </div>
</template>


<script lang="ts">
import Vue from "vue";
import Main from "./components/Main.vue";
import CommonNavBar from "./components/CommonNavBar.vue";


Vue.config.productionTip = false

export default Vue.extend({
  components:{
    CommonNavBar,
    Main
  }
});
</script>

<style>
#app {
  margin: 10px;
}


</style>
