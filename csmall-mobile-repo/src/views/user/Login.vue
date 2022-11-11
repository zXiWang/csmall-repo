<template>
  <div
    class="container"
    :style="{
      'background-image': `url(${require('@/assets/login_background.jpg')})`,
    }"
  >
    <van-image
      class="logo"
      width="180px"
      height="180px"
      fit="contain"
      :src="require('../../assets/logo.png')"
    />
    <van-form @submit="onSubmit">
      <van-field
        v-model="formInfo.username"
        name="用户名"
        label="用户名"
        placeholder="用户名"
        :left-icon="require('../../assets/u8.svg')"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="formInfo.password"
        type="password"
        name="密码"
        label="密码"
        placeholder="密码"
        :left-icon="require('../../assets/u9.svg')"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 16px">
        <van-button round block type="info" native-type="submit"
          >提交</van-button
        >
      </div>
    </van-form>
    <van-row type="flex" justify="space-between">
      <van-col span="12" style="text-align: left">
        <router-link to="#" class="link">短信验证码登录</router-link>
      </van-col>
      <van-col span="12" style="text-align: right">
        <router-link to="/user/registry" class="link">注册</router-link>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import {Toast} from 'vant'
import {mapMutations} from 'vuex'

export default {
  data() {
    return {
      formInfo:{
        username: "",
        password: ""
      }
    };
  },
  methods: {

    ...mapMutations(['saveToken', 'updateUserInfo']),

    onSubmit() {
      console.log("submit", this.formInfo);
      this.$api.userApi.login(this.formInfo).then(res=>{
        console.log('登录请求结果', res)
        if(res.data.state==200){
          // 获取token，并保存
          let tokenHeader = res.data.data.tokenHeader
          let tokenValue = res.data.data.tokenValue
          // 整合tokenInfo字符串 存入vuex
          let tokenInfo = {
            tokenHeader: 'Authorization',
            tokenValue: `${tokenHeader} ${tokenValue}`
          }
          console.log(this)
          this.saveToken(tokenInfo)
          // 发送请求，获取当前用户信息
          this.$api.userApi.getCurrentUser().then(res=>{
            console.log('获取当前用户', res)
            if(res.data.state==200){
              this.updateUserInfo(res.data.data)
              this.$router.push("/home/index");
            }
          })
        }else{
          Toast.fail('账号密码输入错误');
        }
      })
    },
  },
};
</script>

<style scoped>
/** 容器样式 */
.container {
  text-align: center;
  height: 100vh;
  background-size: cover;
}
.container .logo {
  margin-top: 60px;
}
.link {
  margin: 0px 20px;
  color: #333;
}
</style>

<style>
.van-field__left-icon .van-icon {
  margin-top: 4px !important;
}
</style>
