<template>
    <div id="withdraw_explain">
           <div class="top">提现说明</div>
            <div class="content">
                  1.工作日20点前提现当日即可到账，20点之后次日12点之前到账；<br/>
                2.周末及法定节假日提现最晚于下一个工作日12点之前到账;<br/>
                3.平台每月可免费提现次数为3次’<br/>
                4.若当月提现次数超过3次,则之后每笔提现将收取2元每笔的手续费；<br/>
                5.不可提现金额转可提现金额将收取0.5%的手续费；<br/>
                </div>
    </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {
      msg: "Welcome to Your 提现说明"
    };
  }
};
</script>

<style scoped lang="less">
#withdraw_explain {
  font-size: 30px;
  line-height: 1.7;
  color: #333;

  text-align: justify;
  .content {
   padding: 20px;
  }
  p {
    padding-left: 10px;
  }
}
</style>
