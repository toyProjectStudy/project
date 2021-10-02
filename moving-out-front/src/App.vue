<template>
  <button @click="axiosTest">axios-test</button>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { TestData } from "@/api/TestModel";

export default defineComponent({
  name: "App",
  components: {
  },
  methods: {
    axiosTest() {
      this.axios.get<TestData>('http://localhost:3000/data/1').then(response => {
        console.log(response.data)
      })
    }
  }
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
