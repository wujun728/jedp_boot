//生成菜单
var menuItem = Vue.extend({
   name:'menu-item',
    props:{item:{}},
    template:[
        '<li>',
        '<a v-if="item.type === 0" href="javascript:;">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul v-if="item.type === 0" class="treeview-menu">',
        '<menu-item :item="item" v-for="item in item.list"></menu-item>',
        '</ul>',
        '<a v-if="item.type === 1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
        '</li>'
    ].join('')
});
//注册菜单组件
Vue.component('menuItem',menuItem);

var index=new Vue({
    el:'#yapp',
    data:{
        user:{},
        menuList:{},
        main:"sys/main",
        password:'',
        newPassword:'',
        navTitle:'控制台'
    },
    methods:{
        updatPassword:function () {
            alert('修改密码！！');
        }
    }
})