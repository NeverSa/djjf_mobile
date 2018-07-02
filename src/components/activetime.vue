<template>
	<span class="h-time">{{stime}} - {{etime}}</span>
</template>
<script>
import Moment from "moment";
export default {
  props: ["activeid"],
  data() {
    return {
      stime: "",
      etime: ""
    };
  },
  mounted() {
    var self = this;
    var req = this.Axios.get(this.Interface.GetActiveBaseInfo, {
      params: {
        topicId: self.activeid
      }
    });

    req.then(res => {
      var data = res.data;
      if (data.success) {
        var { startTime, endTime } = data.data;
        var stime = startTime * 1;
        var etime = endTime * 1;

        self.stime = Moment(stime).format("M月DD日");
        self.etime = Moment(etime).format("M月DD日");
      }
    });
  },
  methods: {}
};
</script>
<style lang="less" scoped>

</style>
