<template>
  <div>
    <!-- 搜索框 -->
    <van-sticky>
      <van-search
        v-model="keyword"
        shape="round"
        placeholder="请输入搜索关键词"
        style="background-color: white"
        @search="loadSpuListBySearchKeyword()"
      >
        <van-icon
          name="arrow-left"
          slot="left"
          style="color: #fff; font-size: 1.2em; margin-right: 6px"
          @click="$router.go(-1)"
        />
      </van-search>
      <!-- 顶部滚动导航  -->

      <van-tabs v-if="spuList && spuList.length != 0">
        <van-tab title="综合"></van-tab>
        <van-tab title="销量"></van-tab>
        <van-tab title="价格"></van-tab>
        <van-tab title="筛选"></van-tab>
      </van-tabs>
    </van-sticky>
    <!-- 商品列表 -->
    <van-empty
      description="该类别下没有商品"
      v-if="spuList && spuList.length == 0"
    />
    <van-card
      v-for="(item, index) in spuList"
      :key="index"
      :price="item.listPrice"
      :desc="item.description"
      :title="item.title"
      :thumb="item.pictures ? item.pictures.split(',')[0] : 'nullpicture'"
      @click="$router.push(`/product/seckill-detail/${item.id}`)"
    >
      <template #tags>
        <van-count-down :time="item.time" style="display: inline-block">
          <template #default="timeData">
            <span class="block">{{ timeData.days }}</span>
            <span class="colon">天</span>
            &nbsp;
            <span class="block">{{ timeData.hours }}</span>
            <span class="colon">:</span>
            <span class="block">{{ timeData.minutes }}</span>
            <span class="colon">:</span>
            <span class="block">{{ timeData.seconds }}</span>
          </template>
        </van-count-down>
      </template>
      <template #footer> </template>
    </van-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      keyword: "", // 绑定当前关键字
      spuList: [], // 保存当前spu列表
    };
  },

  mounted() {
    this.loadSeckillSpuList();
  },

  methods: {
    /**
     * 加载秒杀商品列表
     */
    loadSeckillSpuList() {
      this.$api.seckillApi
        .querySpuList({ page: 1, pageSize: 100 })
        .then((res) => {
          console.log("加载秒杀列表结果", res);
          res.data.data.list.forEach((item) => {
            let t = this.moment(item.endTime, "YYYY-MM-DD HH:mm:ss").format(
              "x"
            );
            item.time = t - new Date().getTime();
          });
          this.spuList = res.data.data.list;
        });
    },
  },
};
</script>

<style scoped>
.colon {
  display: inline-block;
  margin: 0 4px;
  color: #ee0a24;
}
.block {
  display: inline-block;
  width: 22px;
  color: #fff;
  text-align: center;
  background-color: #ee0a24;
  border-radius: 5px;
}
.van-count-down {
  font-size: 0.8px;
}
</style>
