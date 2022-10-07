const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  // devServer: {
  //   proxy:

  //   { // 请求代理服务器 warning  in ./src/main.js
  //       '/Spring-vue-books': { //带上api前缀的
  //           target: 'http://localhost:8848/', //代理目标地址
  //           changeOrigin: true,
  //           pathRewrite: { // 在发出请求后将/api替换为''空值，这样不影响接口请求
  //               '^/Spring-vue-books': ''
  //           }
  //       }
  //   }
  // },
})
