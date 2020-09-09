<%--
  Created by IntelliJ IDEA.
  User: dyl
  Date: 2018/7/19
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@smallwei/avue@1.3.0/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://cdn.jsdelivr.net/npm/@smallwei/avue@1.3.0/lib/index.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>登录</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <%--<link rel="stylesheet" href="/app/css/app.css">--%>

    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/element-ui/2.3.4/theme-chalk/index.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/@smallwei/avue/lib/theme-chalk/index.css" />

    <script src="https://cdn.bootcss.com/axios/0.18.0/axios.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/element-ui/2.3.4/index.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@smallwei/avue/lib/index.js"></script>
</head>
<body>
<div id="app-3">
    <p v-if="seen">Original message: "{{ message }}"</p>
    <p  >Computed reversed message: "{{ reversedMessage }}"</p>
</div>

<div id="app">
    <avue-crud :data="data" :option="option" v-model="obj"></avue-crud>
    <hr>
    <el-tag>当前弹出框表单中的数据{{obj}}</el-tag>
</div>
</body>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<%--<script src="/app/js/vue-resource.js"></script>--%>


<script>
    import {Component} from "element-ui";
    // import ElementUI from 'element-ui';
    import Vue from "vue";

    Vue.component('avue-crud',Component);
    var app = new Vue({
        el: '#app',

        data() {
            return {
                obj:{},
                message: 'hello',
                reversedMessage: 'hell',
                data: [
                    {
                        name:'张三',
                        sex:'男'
                    }, {
                        name:'李四',
                        sex:'女'
                    }, {
                        name:'王五',
                        sex:'女'
                    }, {
                        name:'赵六',
                        sex:'男'
                    }
                ],
                option:{
                    page:false,
                    align:'center',
                    menuAlign:'center',
                    column:[
                        {
                            label:'姓名',
                            prop:'name'
                        },
                        {
                            label:'性别',
                            prop:'sex'
                        }
                    ]
                }
            }
        },
        methods:{

        },
    });
</script>
</html>
