//判断是否ie浏览器
function isIeBrower(){
	var isIE = null;
	if(document.all && window.external){ 
		   //IE代码 
			isIE=true;
		}else{ 
		  //其他 
			isIE=false;
		}
	return isIE;
}

//下拉框验证
$.extend($.fn.validatebox.defaults.rules, {  
	selectValueRequired: {  
		validator: function(value,param){  
			//console.info($(param[0]).find("option:contains('"+value+"')").val()); 
			return $(param[0]).find("option:contains('"+value+"')").val() != '';  
		},  
		message: '该下拉框必选项！'  
	}  
});

//checkbox全选
function checkAll(name)
{
    var el = document.getElementsByTagName('input');
    var len = el.length;
    for(var i=0; i<len; i++)
    {
        if((el[i].type=="checkbox") && (el[i].name==name))
        {
            el[i].checked = true;
        }
    }
}

//checkbox取消
function clearAll(name)
{
    var el = document.getElementsByTagName('input');
    var len = el.length;
    for(var i=0; i<len; i++)
    {
        if((el[i].type=="checkbox") && (el[i].name==name))
        {
            el[i].checked = false;
        }
    }
}

///格式化日期
function formatDatebox(value) {
  if (value == null || value == '') {
      return '';
  }
  var dt;
  if (value instanceof Date) {
      dt = value;
  }
  else {
      dt = new Date(value);
      if (isNaN(dt)) {
          value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
          dt = new Date();
          dt.setTime(value);
      }
  }

  return dt.format("yyyy-MM-dd hh:mm");   //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
}

///格式化日期
function formatDate(value) {
  if (value == null || value == '') {
      return '';
  }
  var dt;
  if (value instanceof Date) {
      dt = value;
  }
  else {
      dt = new Date(value);
      if (isNaN(dt)) {
          value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
          dt = new Date();
          dt.setTime(value);
      }
  }

  return dt.format("yyyy-MM-dd hh:mm:ss");   //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
}

Date.prototype.format = function (format) {
  var o = {
      "M+": this.getMonth() + 1, //month 
      "d+": this.getDate(),    //day 
      "h+": this.getHours(),   //hour 
      "m+": this.getMinutes(), //minute 
      "s+": this.getSeconds(), //second 
      "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
      "S": this.getMilliseconds() //millisecond 
  }
  if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
  (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o) if (new RegExp("(" + k + ")").test(format))
      format = format.replace(RegExp.$1,
    RegExp.$1.length == 1 ? o[k] :
      ("00" + o[k]).substr(("" + o[k]).length));
  return format;
}

///显示进度条
function showProcess(isShow, title, msg) {
  if (!isShow) {
      $.messager.progress('close');
      return;
  }
  var win = $.messager.progress({
      title: title,
      msg: msg
  });
}

//IP-->数值
function ipToNumber(ip) {    
    var numbers = ip.split(".");    
    return parseInt(numbers[0])*256*256*256 +     
    parseInt(numbers[1])*256*256 +     
    parseInt(numbers[2])*256 +     
    parseInt(numbers[3]);    
}  

//数值-->IP
function numberToIp(number) {  
	if(parseInt(number)==number) 
	{ 
		return (Math.floor(number/(256*256*256))) + "." +     
	    (Math.floor(number%(256*256*256)/(256*256))) + "." +     
	    (Math.floor(number%(256*256)/256)) + "." +     
	    (Math.floor(number%256)); 
	}
    return    number;
}

//是否为空
function empty(v){ 
	switch (typeof v){ 
		case 'undefined' : return true; 
		case 'string' : if(trim(v).length == 0) return true; break; 
		case 'boolean' : if(!v) return true; break; 
		case 'number' : if(0 === v) return true; break; 
		case 'object' : 
			if(null === v) return true; 
			if(undefined !== v.length && v.length==0) return true; 
			for(var k in v){return false;} 
				return true; 
				break; 
	} 
	return false; 
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ //删除左边的空格
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
    return str.replace(/(\s*$)/g,"");
}

//提示框--提示
function messagerInfoAlert(content){
	$.messager.alert('提示','<center  style="padding-right:26px">'+content+'</center>','info');
}

//成功--提示
function messagerSuccessAlert(content){
	$.messager.alert('成功','<center  style="padding-right:26px">'+content+'</center>','success');
}

/**成功--提示--刷新父页面@author:cw314@dnion.com*/
function msgSuccessAlertReload(content){
	$.messager.alert('成功','<center  style="padding-right:26px">'+content+'</center>','success',function(r){
    	document.location.reload();
    });
}

//提示框--警告
function messagerWarningAlert(content){
	$.messager.alert('警告','<center  style="padding-right:26px">'+content+'</center>','warning');
}

var messagerErr;
//提示框-错误
function messagerErrorAlert(content){
	///alert(messager.window);
	if(messagerErr){
		messagerErr.window("close");
	}
	messager = $.messager.alert('错误','<center  style="padding-right:26px">'+content+'</center>','error');
}

//提示框-超时或系统关闭
function messagerTimeOutAlert(content){
	if(messagerErr){
		messagerErr.window("close");
	}
	messager = $.messager.alert('错误','<center  style="padding-right:26px">'+content+'</center>','error',function(r){
    	document.location.reload();
    });
}

//提示框--无标识
//未修改点保存标准
function messagerNoeditAlert(content){
	$.messager.alert('提示','<center  style="padding-right:26px">'+content+'</center>');
}

//tab关闭事件的实现
function CloseTab(tabs,menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    //var tabs = $("#tabs");
    
    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }
    
    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];
    
    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });
    
    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}

$.extend($.fn.datagrid.methods, {      
    /**
     * 开打提示功能    
     * @param {} jq    
     * @param {} params 提示消息框的样式    
     * @return {}    
     */     
    doCellTip:function (jq, params) {      
        function showTip(showParams, td, e, dg) {      
            //无文本，不提示。      
            if ($(td).text() == "") return;      
               
            params = params || {};   
            var options = dg.data('datagrid');      
            showParams.content = '<div class="tipcontent">' + showParams.content + '</div>';      
            $(td).tooltip({      
                content:showParams.content,      
                trackMouse:true,      
                position:params.position,      
                onHide:function () {      
                    $(this).tooltip('destroy');      
                },      
                onShow:function () {      
                    var tip = $(this).tooltip('tip');      
                    if(showParams.tipStyler){      
                        tip.css(showParams.tipStyler);      
                    }      
                    if(showParams.contentStyler){      
                        tip.find('div.tipcontent').css(showParams.contentStyler);      
                    }   
                }      
            }).tooltip('show');      
     
        };      
        return jq.each(function () {      
            var grid = $(this);      
            var options = $(this).data('datagrid');      
            if (!options.tooltip) {      
                var panel = grid.datagrid('getPanel').panel('panel');      
                panel.find('.datagrid-body').each(function () {      
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;      
                    $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td[field]', {      
                        'mouseover':function (e) {   
                            //if($(this).attr('field')===undefined) return;      
                            var that = this;   
                            var setField = null;   
                            if(params.specialShowFields && params.specialShowFields.sort){   
                                for(var i=0; i<params.specialShowFields.length; i++){   
                                    if(params.specialShowFields[i].field == $(this).attr('field')){   
                                        setField = params.specialShowFields[i];   
                                    }   
                                }   
                            }   
                            if(setField==null){   
                                options.factContent = $(this).find('>div').clone().css({'margin-left':'-5000px', 'width':'auto', 'display':'inline', 'position':'absolute'}).appendTo('body');      
                                var factContentWidth = options.factContent.width();      
                                params.content = $(this).text();      
                                if (params.onlyShowInterrupt) {      
                                    if (factContentWidth > $(this).width()) {      
                                        showTip(params, this, e, grid);      
                                    }      
                                } else {      
                                    showTip(params, this, e, grid);      
                                }    
                            }else{   
                                panel.find('.datagrid-body').each(function(){   
                                    var trs = $(this).find('tr[datagrid-row-index="' + $(that).parent().attr('datagrid-row-index') + '"]');   
                                    trs.each(function(){   
                                        var td = $(this).find('> td[field="' + setField.showField + '"]');   
                                        if(td.length){   
                                            params.content = td.text();   
                                        }   
                                    });   
                                });   
                                showTip(params, this, e, grid);   
                            }   
                        },      
                        'mouseout':function (e) {      
                            if (options.factContent) {      
                                options.factContent.remove();      
                                options.factContent = null;      
                            }      
                        }      
                    });      
                });      
            }      
        });      
    },      
    /**
     * 关闭消息提示功能    
     * @param {} jq    
     * @return {}    
     */     
    cancelCellTip:function (jq) {      
        return jq.each(function () {      
            var data = $(this).data('datagrid');      
            if (data.factContent) {      
                data.factContent.remove();      
                data.factContent = null;      
            }      
            var panel = $(this).datagrid('getPanel').panel('panel');      
            panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')      
        });      
    }      
});  


function arrive_timer_format(s) {
	var t;
	s=parseInt(s/1000);
	if(s > -1){
	 hour = Math.floor(s/3600);
	 min = Math.floor(s/60) % 60;
     sec = s % 60;
     day = parseInt(hour / 24);
     if (day > 0) {
     hour = hour - 24 * day;
     t = day + "天 " + hour + ":";
	 }
	else t = hour + ":";   
	 if(min < 10){t += "0";}
	  t += min + ":";
	if(sec < 10){t += "0";}
	 t += sec;
	}
	return t;
}

function showEmptyStr(value){
	if(value==null||value==''){
		return '---';
	}
	return value;
}

function formatLongStr(value){
	if(value==null||value==''){
		return '---';
	}
	return value.replace(new RegExp(',',"gm"),'&nbsp;|&nbsp;');
}

function isNumber(value){
	var reg = /^\d+$/;
	return reg.test(value);
}

/***
 * easy ui validatebox 限制输入长度
 * obj 传this
 * limitLength 限制长度
 */
function validateBoxLimitInput(obj,limitLength){
	var curLength = $(obj).val().length;
	if( curLength >= limitLength ){
		$(obj).val($(obj).val().substr(0,limitLength-1));
	}
}

/****
 * 获取屏幕分辨率
 */
function getResolution(){
	var data = {
			height:window.screen.height,
			width:window.screen.width
	};
	return data;
}


function openWin(url){
	window.open(url, 'newWin');
}

