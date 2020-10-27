$(function () {

    //获取图表
    var myChart0 = echarts.init(document.getElementById("child1"));
    var myChart1 = echarts.init(document.getElementById("child2"));
    myChart0.clear();
    myChart1.clear();
    myChart0.showLoading({text: '正在努力的读取数据中...'});
    myChart1.showLoading({text: '正在努力的读取数据中...'});

    /*$.getJSON(CONTEXT_PATH + "/event/getFireOption",function(data) {
        console.log(data);
        myChart.setOption(data);
        /!*myChart.setOption(data, true);*!/
        myChart.hideLoading();
    });*/

    $.getJSON(CONTEXT_PATH + "/event/getOptionByEventId/"+1,function(data) {
        console.log(data);
        myChart0.setOption(data[0]);
        myChart1.setOption(data[1]);
        /*myChart.setOption(data, true);*/
        myChart0.hideLoading();
        myChart1.hideLoading();
    });

});
