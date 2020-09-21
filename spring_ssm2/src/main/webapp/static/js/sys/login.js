/** login 相关js */
$(function(){
    login.init();
})

var login={
    test:false,
    /** 页面初始化 */
    init:function(){
        //判断提示是否显示
        if($(".error-info").is(":visible")){
            $(".error-info").hide();
        }

    },
    /** 页面绑定事件 */
    bind:function(){
        var _this = this;

        //登陆 绑定事件
        $("#loginBtn").one("click", login.loginHandler);

    },
    /** 登陆 */
    loginHandler:function(){
        var account =$("#account").val();
        var password=$("#password").val();
    }


}
/** common 通用组件 */
var common={
    /** 显示错误信息 */
    showError:function(msg){
        $(".error-info").text(msg).show();
    }
}