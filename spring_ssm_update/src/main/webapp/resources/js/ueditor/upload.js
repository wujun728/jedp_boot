
$(function() {

    $("#spCol").combobox({
        url : CONTEXT_PATH + "/ueditor/getAllSpCol",
        valueField : "spcolumnEn",
        textField : "spcolumnCn",
        method : "get",
        onSelect : function(record) {
            $("#childCol").combobox('disable');
            console.log(record);
            if(record.childcolList) {
                $("#childCol").combobox('enable');
                $("#childCol").combobox({
                    data : record.childcolList,
                    valueField : "childcolEn",
                    textField : "childcolCn"
                });
            }
        }
    });

    $('#f_picture').filebox({
        width : "198px",
        buttonText:'选择图片',
        buttonAlign:'left',
        required: false,
        validType: "verifyFileType[[/.jpeg$/, /.jpg$/, /.png$/ , /.gif$/], '图片格式不对']",
        missingMessage: "请上传图片"
    });

    $('#f_video').filebox({
        width : "198px",
        buttonText:'选择视频',
        buttonAlign:'left',
        required: false,
        validType: "verifyFileType[[/.jpeg$/, /.jpg$/, /.png$/ , /.gif$/], '图片格式不对']",
        missingMessage: "请上传图片"
    });

});

function submitForm(){

    $("#uploadForm").form('submit', {
        url: CONTEXT_PATH + '/ueditor/submit',
        onSubmit: function(){
            if(!$(this).form('validate')){
                return false;
            }
            $.messager.progress({
                title:'操作中',
                msg:'正在操作。。。'
            });
            return true;
        },
        success: function(data){
            $.messager.progress('close');
            if(data==0){
                $.messager.alert("结果", "推送失败");
            } else {
                $.messager.alert("结果", "推送成功");
            }
        }
    });
}

