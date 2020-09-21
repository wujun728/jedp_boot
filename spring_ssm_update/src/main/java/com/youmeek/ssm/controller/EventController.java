package com.youmeek.ssm.controller;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.youmeek.ssm.model.DataGridModel;
import com.youmeek.ssm.model.Message;
import com.youmeek.ssm.model.page.Pagination;
import com.youmeek.ssm.pojo.Event;
import com.youmeek.ssm.pojo.EventDetail;
import com.youmeek.ssm.pojo.Fireman;
import com.youmeek.ssm.pojo.SysUser;
import com.youmeek.ssm.service.EventService;
import com.youmeek.ssm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 事件 Controller
 * @Author frank.fang
 * @Date 2016/4/26 9:19
 */
@Controller
@RequestMapping("/event")
public class EventController extends BaseController {
    @Resource
    private SysUserService sysUserService;

    @Autowired
    private EventService eventService;

    /**
     * 测试
     * @param model
     */
    @RequestMapping("/test")
    public String showUser(Model model){
        return "test/test";
    }

    /**
     * 获取单个事件
     * @author frank.fang
     * @date 2016/4/20 09:25
     */
    @RequestMapping("/getEventById/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable("id") Integer id){
        return eventService.getEventById(id);
    }


    /**
     * 获取所有事件
     * @author frank.fang
     * @date 2016/4/19 15:25
     */
    @RequestMapping("/getAllEvent")
    @ResponseBody
    public List<Event> getAllEvent(){
        return eventService.getAllEvent();
    }


    @RequestMapping("/showUserToJSONById/{userId}")
    @ResponseBody
    public SysUser showUser(@PathVariable("userId") Long userId){
        return sysUserService.getById(userId);
    }

    /**
     * 获取图表显示option
     * @author frank.fang
     * @date 2016/4/19 15:25
     */
    @RequestMapping("/getFireOption")
    @ResponseBody
    public String getFireOption() {
        Option option = eventService.getFireOption();
        return GsonUtil.format(option);
    }

    /**
     * 根据事件id获取所有事件详情的option
     * @Author frank.fang
     * @Date 2016/4/26 9:07
     */
    @RequestMapping("/getOptionByEventId/{eventId}")
    @ResponseBody
    public String getOptionByEventId(@PathVariable Integer eventId) {

        List<Option> optionList = Lists.newArrayList();
        List<EventDetail> eventDetails = eventService.getEventDetailByEventId(eventId);

        //每個消防員單獨一個option
        Map<String,List<EventDetail>> maps = Maps.newHashMap();
        for (EventDetail eventDetail : eventDetails) {
            if (maps.get(eventDetail.getName()) == null) {//還不存在
                List<EventDetail> eventDetailList = Lists.newArrayList();
                eventDetailList.add(eventDetail);
                maps.put(eventDetail.getName(), eventDetailList);
            } else {//存在了就add
                List<EventDetail> eventDetailList = maps.get(eventDetail.getName());
                eventDetailList.add(eventDetail);
            }
        }

        for (Map.Entry<String,List<EventDetail>> entry : maps.entrySet()) {
            String firemanName = entry.getKey();
            //创建Option
            Option option = new Option();
            option.title().text("空呼器压力曲线").subtext("测试数据").x(X.center);
            option.tooltip().trigger(Trigger.axis);
            option.legend(firemanName);//消防員
            option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar)
                    .show(true), Tool.restore, Tool.saveAsImage);

            //设置y轴为值轴，并且不显示y轴，最大值设置400，最小值-0
            option.yAxis(new ValueAxis().name("剩余氧气")
                    .axisLine(new AxisLine().show(true).lineStyle(new LineStyle().width(0)))
                    .max(400).min(0));


            //创建类目轴，并且不显示竖着的分割线，onZero=false
            CategoryAxis categoryAxis = new CategoryAxis()
                    .splitLine(new SplitLine().show(false))
                    .axisLine(new AxisLine().onZero(false));

            //不显示表格边框，就是围着图标的方框
            option.grid().borderWidth(0);

            //创建Line数据
            Line line = new Line("剩余氧气").smooth(true);


            for (EventDetail eventDetail : entry.getValue()) {
                SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
                String timeStr = sf.format(eventDetail.getDetailTime());
                //增加类目，值为時間
                categoryAxis.data(timeStr);
                //時間对应的剩餘氧氣
                line.data(eventDetail.getLeftAir());
            }

            //设置x轴为类目轴
            option.xAxis(categoryAxis);
            option.series(line);
            optionList.add(option);
        }

        return GsonUtil.format(optionList);
    }

    /**
     * 跳转到事件详情页面
     * @Author frank.fang
     * @Date 2016/4/28 17:19
     */
    @RequestMapping("/toList")
    public String  listEventDetail() {
        return "/test/listEventDetail";
    }

    /**
     * 事件详情页面
     * @Author frank.fang
     * @Date 2016/4/28 17:15
     */
    @RequestMapping("/list")
    @ResponseBody
    public Pagination listEventDetail(DataGridModel model) {
        int pageNo = model.getPage();
        int pageSize = model.getRows();
        return eventService.listEventDetail(pageNo,pageSize);
    }

    /**
     * 新增|修改事件
     * @Author frank.fang
     * @Date 2016/4/29 14:18
     */
    @RequestMapping("/editRow")
    @ResponseBody
    public Message listEventDetail(@RequestParam("rowData") String rowData) {
        EventDetail eventDetail = com.youmeek.ssm.util.GsonUtil.toObject(rowData,EventDetail.class);
        Preconditions.checkNotNull(eventDetail,"无效事件");
        if (eventDetail.getDetailId() != null) {//修改
            return eventService.updateEventDetail(eventDetail);
        } else {//新增
            return eventService.addEventDetail(eventDetail);
        }
    }

    /**
     * 获取所有消防员
     * @Author frank.fang
     * @Date 2016/4/29 17:11
     */
    @RequestMapping("/listFireman")
    @ResponseBody
    public List<Fireman> listFireman() {
        List<Fireman> firemans = eventService.listFireman();
        return firemans;
    }

    /**
     * 批量删除事件详细
     * @Author frank.fang
     * @Date 2016/5/3 10:02
     */
    @RequestMapping("/delEventDetail")
    @ResponseBody
    public Message delEventDetail(@RequestParam("detailId[]") String[] ids) {
        List<String> idList = Arrays.asList(ids);
        return eventService.delEventDetailByIds(idList);
    }

}
