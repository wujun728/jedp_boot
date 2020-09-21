/**
 * 事件详细JS BootstrapTabble版
 * @Author frank.fang
 * @Date 2016/5/3 14:38
 */
$(function () {
    $('#dg').bootstrapTable({
        method: 'post',
        url: CONTEXT_PATH+'/event/list',
        sidePagination: "server",
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 10,
        pageNumber:1,
        queryParamsType:"limit",
        pageList: [10, 20,50],
        search: false,
        showColumns: true,
        showRefresh: true,
        minimumCountColumns: 2,
        clickToSelect: true,
        contentType: "application/x-www-form-urlencoded",
        queryParams:function(params){
            return $.common.formatSearchParam('searchAccountForm',params.limit,params.pageNumber);
        },
        responseHandler:function(res){
            return res;
        },
        columns: [{
            field: 'id',
            align: 'center',
            valign: 'bottom',
            width:"38px",
            title:"<input type='checkbox' name='allItemId' onclick='allCheck(this);'/>",
            formatter: checkboxFormatter
        },{
            field: 'eventId',
            align: 'center',
            title: '事件Id',
            valign: 'bottom',
            width:"38px",
        }, {
            field: 'name',
            title: '姓名',
            align: 'left',
            valign: 'bottom',
            sortable: false,
            width:"200px",
            clickToSelect: false
        }, {
            field: 'detailTime',
            title: '时间',
            align: 'left',
            valign: 'bottom',
            sortable: false,
            width:"200px",
            clickToSelect: false
        }, {
            field: 'leftAir',
            title: '剩余氧气',
            align: 'left',
            valign: 'bottom',
            sortable: false,
            width:"200px",
            clickToSelect: false
        }],
    },'zh-CN');

});

//时间formatter
function detailTimeFormatter(val,row,index) {
    var detailTime = new Date(val);
    var _detailTime = detailTime.format("MM-dd hh:mm:ss");
    return _detailTime;
}

function checkboxFormatter(value,row,index) {
    return "<input type='checkbox' value='"+value+"' name='allItemId' />";
}

function allCheck(o) {
    if($(o).is(":checked")){
        $("input[name='allItemId']").prop("checked",true);
    }else{
        $("input[name='allItemId']").prop("checked",false);
    }
}