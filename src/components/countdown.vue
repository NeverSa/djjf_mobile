<template>
  <div v-if="content" class="a-state a-state-comming">
      {{content}}
  </div>
</template>
<script>
export default {
  data() {
    return {
      countDownTimeOut: null,
      countDownTime: "",
      content: ""
    };
  },
  props: ["item"],
  mounted() {
    var self = this,
      item = this.item;
    if (item.curTime <= item.startTimeMillis) {
      self.countDownTime = (item.startTimeMillis - item.curTime) / 1000;
      self.countDowns(self.countDownTime);
    }
  },
  methods: {
    countDowns(time) {
      var self = this;
      var theTime = parseInt(time); // 秒
      var theTime1 = "00"; // 分
      var theTime2 = "00"; // 小时
      if (theTime > 60) {
        theTime1 = parseInt(theTime / 60);
        theTime = parseInt(theTime % 60);
        if (theTime1 > 60) {
          theTime2 = parseInt(theTime1 / 60);
          theTime1 = parseInt(theTime1 % 60);
        }
      }

      var result =
        parseInt(theTime) > 9 ? parseInt(theTime) : "0" + parseInt(theTime);
      if (theTime1 > 0) {
        result =
          (parseInt(theTime1) > 9
            ? parseInt(theTime1)
            : "0" + parseInt(theTime1)) +
          ":" +
          result;
      } else {
        result = "00:" + result;
      }
      if (theTime2 > 0) {
        result =
          (parseInt(theTime2) > 9
            ? parseInt(theTime2)
            : "0" + parseInt(theTime2)) +
          ":" +
          result;
      } else {
        result = "00:" + result;
      }
      if (time > 0) {
        self.content = result;
      } else {
        self.content = "00:00:00";
        return false;
      }
      time--;

      if (self.countDownTimeOut) clearTimeout(self.countDownTimeOut);
      self.countDownTimeOut = setTimeout(function() {
        self.countDowns(time);
      }, 1000);
    }
  }
};
</script>
<style lang="less" scoped>

</style>
