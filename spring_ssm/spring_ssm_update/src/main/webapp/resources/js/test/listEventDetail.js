/**
 * 事件详细JS
 * @Author frank.fang
 * @Date 2016/4/28 16:38
 */
$(function () {
    var datagrid; //定义全局变量datagrid
    var editRow = undefined; //定义全局变量：当前编辑的行
    datagrid = $("#dg").datagrid({
        url : CONTEXT_PATH + "/event/list",
        method : 'post',
        rownumbers : true,
        pagination : true,
        width : "1200px",
        pageList : [ 10, 30 ],
        pageSize : 10,
        frozenColumns : [ [ {
            field : 'eid',
            checkbox : true
        }] ],
        columns:[[
            {field:'eventId',title:'事件Id',width:"20%",align:'center'},
            {field:'name',title:'姓名',width:"25%",align:'center',editor:{
                type:'combobox',
                options:{
                    valueField:'firemanId',
                    textField:'name',
                    url:CONTEXT_PATH +"/event/listFireman"
                }
            }},
            {field:'detailTime',title:'时间',width:"25%",align:'center',formatter:detailTimeFormatter,editor:{type : 'datetimebox'}},
            {field:'leftAir',title:'剩余氧气',width:"25%",align:'center',editor: { type: 'textbox'}}
        ]],
        toolbar : [{ text: '添加', iconCls: 'icon-add', handler: function () {//添加列表的操作按钮添加，修改，删除等
            //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
            if (editRow != undefined) {
                datagrid.datagrid("endEdit", editRow);
            }
            //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
            if (editRow == undefined) {
                datagrid.datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {

                    }
                });
                //将新插入的那一行开户编辑状态
                datagrid.datagrid("beginEdit", 0);
                //给当前编辑的行赋值
                editRow = 0;
            }

        }
        }, '-',
            { text: '删除', iconCls: 'icon-remove', handler: function () {
                //删除时先获取选择行
                var rows = datagrid.datagrid("getChecked");
                //选择要删除的行
                if (rows.length > 0) {
                    $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                        if (r) {
                            var ids = [];
                            for (var i = 0; i < rows.length; i++) {
                                ids.push(rows[i].detailId);
                            }
                            //将选择到的行存入数组并用,分隔转换成字符串，
                            //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                            $.post(CONTEXT_PATH + "/event/delEventDetail",{detailId : ids},function(res) {
                                if(res.type == 'success') {
                                    datagrid.datagrid('reload');
                                    $.messager.alert("成功", "删除成功", "info");

                                }
                            });
                        }
                    });
                }
                else {
                    $.messager.alert("提示", "请选择要删除的行", "error");
                }
            }
            }, '-',
            { text: '修改', iconCls: 'icon-edit', handler: function () {
                //修改时要获取选择到的行
                var rows = datagrid.datagrid("getChecked");
                //如果只选择了一行则可以进行修改，否则不操作
                if (rows.length == 1) {
                    //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                    if (editRow != undefined) {
                        datagrid.datagrid("endEdit", editRow);
                    }
                    //当无编辑行时
                    if (editRow == undefined) {
                        //获取到当前选择行的下标
                        var index = datagrid.datagrid("getRowIndex", rows[0]);
                        console.log(index);
                        //开启编辑
                        datagrid.datagrid('beginEdit', index);
                        /*datagrid.datagrid('beginEdit', index);*/
                        //把当前开启编辑的行赋值给全局变量editRow
                        editRow = index;

                        //当开启了当前选择行的编辑状态之后，
                        //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                        datagrid.datagrid("unselectAll");
                    }
                }
            }
            }, '-',
            { text: '保存', iconCls: 'icon-save', handler: function () {
                //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                datagrid.datagrid("endEdit", editRow);
            }
            }, '-',
            { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
                //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                editRow = undefined;
                datagrid.datagrid("rejectChanges");
                datagrid.datagrid("unselectAll");
            }
            }, '-'],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            rowData.detailTime = formatDate(rowData.detailTime);
            rowData.firemanId = rowData.name;//消防员id
            console.log(rowData);
            var dataStr = JSON.stringify(rowData);
            $.post(CONTEXT_PATH+"/event/editRow",{rowData:dataStr},function(msg) {
                console.log(msg);
                if (msg.type == 'success') {
                    $.messager.alert("成功","操作成功",'info');
                    datagrid.datagrid("reload")
                }else {
                    $.messager.alert("成功","操作失败",'error');
                }

            });
            editRow = undefined;
        },
        onDblClickRow: function (index, field, value) {
            console.log(index);
            //双击开启编辑行
            if (editRow != undefined) {
                datagrid.datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                datagrid.datagrid('beginEdit', index);
                /*datagrid.datagrid("beginEdit", rowIndex);*/
                editRow = index;
            }
        },
        onLoadSuccess : function(data) {
            console.log(data);
        }
    })

});

//时间formatter
function detailTimeFormatter(val,row,index) {
    var detailTime = new Date(val);
    var _detailTime = detailTime.format("MM-dd hh:mm:ss");
    return _detailTime;
}