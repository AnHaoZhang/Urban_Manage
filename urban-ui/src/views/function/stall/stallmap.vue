<template>
  <div class="hello">
    <div class="contentone">
      <h1 align="center" style="color:white " >{{ msg }}</h1>
    </div>

    <!--百度地图-->
    <baidu-map
      class="bm-view"
      :center="center"
      :zoom="zoom"
      @ready="handler"
      :auto-resize="true"
      :scroll-wheel-zoom="true"
    >
      <bm-marker
        v-for="(item, index) in points"
        :key="index"
        :position="{ lng: item.lng, lat: item.lat }"
        @click="lookDetail(item)">

      <bm-info-window
        :show="item.show"
        :position="{ lng: item.lng, lat: item.lat }"
        @close="infoWindowClose"
        @open="infoWindowOpen"
      >
        <div class="info-window">
          <div>{{item.address}}</div>
          <p>联系方式：{{item.remark}}</p>

        </div>
      </bm-info-window>
      </bm-marker>
    </baidu-map>
  </div>
</template>

<script>
import { listStall} from "@/api/function/stall";


export default {
  name:'map',
  data () {
    return {
      BMap:{},
      map:{},
      msg: '城市流动商贩摊位图',
      center: { lng: 112.144631, lat:32.030165},
      zoom: 14,
      points: [],
    }
  },
  mounted() {
  },
  methods: {

    //地图实例
    handler({ BMap, map }) {
      //选择一个经纬度作为中心点
      this.BMap = BMap;
      this.map = map;
      // console.log(BMap, map);
      this.center.lng = 112.144631;
      this.center.lat = 32.030165 ;
      this.zoom = 15;

      //主题
      let mapStyle ={style:"midnight"};
      map.setMapStyle(mapStyle);

     // 查询摊位信息列表
      listStall(this.queryParams).then(response => {
        this.points = response.rows;

        this.points.forEach((e)=>{
          this.$set(e,'show',false)
        })
        console.log(this.points);
      });

    },
    //打开信息窗口
    lookDetail(item){
       this.points.lng = item.lng;
       this.points.lat = item.lat;
      item.show = !item.show
      console.log(item.show , 'show')

    },


    infoWindowOpen(e) {
      e.show = true;
      console.log(e.show,"show")
    },
    infoWindowClose(e) {
      e.show = false;
      console.log(e,'infoWindow')
    },
  }
}
</script>

<style scoped>
.bm-view {
  width: 100%;
  height: 800px;
}
.contentone {
  background-color:#08304b;
  width: 1684px;
  vertical-align: middle;
  display: table-cell;
}
</style>
