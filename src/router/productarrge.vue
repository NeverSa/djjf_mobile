<template>
   <div>
        <div v-html="context" class="content">

    </div>
   </div>

</template>

<script>
export default {
  props: [],
  data() {
    return {
      context: "",
      name:"",
    };
  },
  methods: {},
  created() {
      var obj = {
        agreeId: this.$route.query.agreeId,
        productId: this.$route.query.productId,
      };
    this.$http.get(this.Interface.getAgreeTpl, { params: obj }).then(res => {
      this.context = res.data.data.content;
      this.name=res.data.data.name;
    });
  }
};
</script>
<style lang="less">
* {
  margin: 0;
  padding: 0;
}
ul,
li {
  list-style: none;
}
.content {
  font-size: 32px;
  padding: 20px;
  line-height: 40px;
}
table {
  /* -moz-border-radius: 5px;
      -webkit-border-radius:5px;
      border-radius:5px; */
  width: 100%;
  border: solid #333;
  border-width: 1px 0px 0px 1px;
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
}
table tbody tr {
  height: 20px;
  line-height: 20px;
}

table tbody tr:hover {
  background-color: #eee;
}
table tbody tr th,
table tbody tr td {
  padding: 3px 5px;
  text-align: left;
  /* border: 1px solid #ddd; */
  border: solid #333;
  border-width: 0px 1px 1px 0px;
}
table tbody tr th {
  font-weight: bold;
  text-align: center;
}

table tbody tr td.tac {
  text-align: center;
}
table tbody tr td a:hover {
  color: #0080c0;
}
.cont {
  margin: 30px 0;
}

.fwxyWrap {
  background: #fff;
  line-height: 2;
  padding: 20px 0;
}

.fwxyWrap strong {
  font-size: 14px;
  margin-top: 10px;
}

.fwxyWrap h4 {
  text-align: center;
  height: 50px;
  line-height: 50px;
  font-size: 22px;
}
p {
  line-height: 36px;
  word-wrap: break-word;
  margin-bottom: 10px;
  text-align: justify;
  word-break: break-all;
  text-indent: 4px;
}
</style>
