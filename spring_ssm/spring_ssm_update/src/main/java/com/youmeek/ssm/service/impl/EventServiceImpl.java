package com.youmeek.ssm.service.impl;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youmeek.ssm.mapper.EventDetailMapper;
import com.youmeek.ssm.mapper.EventMapper;
import com.youmeek.ssm.mapper.FiremanMapper;
import com.youmeek.ssm.model.Message;
import com.youmeek.ssm.model.page.Pagination;
import com.youmeek.ssm.pojo.Event;
import com.youmeek.ssm.pojo.EventDetail;
import com.youmeek.ssm.pojo.Fireman;
import com.youmeek.ssm.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 事件service
 * @author Wujun
 * date 2016/4/15.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Resource
    EventMapper eventMapper;

    @Autowired
    EventDetailMapper eventDetailMapper;

    @Autowired
    FiremanMapper firemanMapper;


    @Override
    public Event getEventById(Integer id) {
        return eventMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Event> getAllEvent() {
        return eventMapper.selectAllEvent();
    }

    //TODO 数据为测试
    @Override
    public Option getFireOption() {
        //创建Option
        Option option = new Option();
        option.title().text("空呼器压力曲线").subtext("测试数据");
        option.tooltip().trigger(Trigger.axis);
        option.legend("张三", "李四");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar)
                                                                                    .show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        option.xAxis(new CategoryAxis().data("09:25:00", "09:29:00", "09:31:00", "09:34:00", "09:35:00", "09:39:00", "09:41:00", "09:45:00", "09:50:00","09:54:00","09:60:00","09:64:00"));
        option.yAxis(new ValueAxis());

        Bar bar = new Bar("张三");
        bar.data(400, 380, 370, 330, 320, 290, 280, 250, 220, 190, 160, 130);
        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        Bar bar2 = new Bar("李四");
        List<Integer> list = Arrays.asList(400, 378, 360, 320, 310, 280, 270, 245, 214, 180, 150, 120);
        bar2.setData(list);
        bar2.markPoint().data(new PointData("最高值", 400).xAxis(0).yAxis(400).symbolSize(28), new PointData("年最低", 100).xAxis(11).yAxis(100));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        option.series(bar, bar2);
        return option;
    }

    @Override
    public List<EventDetail> getEventDetailByManAndEventId(Integer firemanId, Integer eventId) {
        return eventDetailMapper.getEventDetailByManAndEventId(firemanId,eventId);
    }

    @Override
    public List<EventDetail> getEventDetailByEventId(Integer eventId) {
        return eventDetailMapper.getEventDetailByEventId(eventId);
    }

    @Override
    public Pagination listEventDetail(int pageNo, int pageSize) {
        //分页查询
        PageHelper.startPage(pageNo, pageSize,true);
        Page<EventDetail> eventDetails = eventDetailMapper.listEventDetail();
        int total = (int) eventDetails.getTotal();
        Pagination page = new Pagination(pageNo,pageSize,total,eventDetails);
        return page;
    }

    @Override
    public Message updateEventDetail(EventDetail eventDetail) {
        int res = eventDetailMapper.updateByPrimaryKey(eventDetail);
        if (res == 1) {
            return Message.success("修改成功");
        } else {
            return Message.error("修改失败");
        }
    }

    @Override
    public Message addEventDetail(EventDetail eventDetail) {
        int res = eventDetailMapper.insertSelective(eventDetail);
        if (res == 1) {
            return Message.success("新增成功");
        } else {
            return Message.error("新增失败");
        }
    }

    @Override
    public List<Fireman> listFireman() {
        return firemanMapper.getAllFireman();
    }

    @Override
    public Message delEventDetailByIds(List<String> idList) {
        eventDetailMapper.delByIdList(idList);
        return Message.success("删除成功");
    }
}
