package com.youmeek.ssm.service;

import com.github.abel533.echarts.Option;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.youmeek.ssm.model.Message;
import com.youmeek.ssm.model.page.Pagination;
import com.youmeek.ssm.pojo.Event;
import com.youmeek.ssm.pojo.EventDetail;
import com.youmeek.ssm.pojo.Fireman;

import java.util.List;

/**
 * 事件service
 * @Author frank.fang
 * @Date 2016/4/25 18:01
 */
public interface EventService {


    Event getEventById(Integer id);

    List<Event> getAllEvent();

    /**
     *
     * @Author frank.fang
     * @Date 2016/4/26 14:36
     * @return option
     */
    Option getFireOption();

    /**
     * 根据消防员id和事件id获取所有事件详情
     * @Author frank.fang
     * @Date 2016/4/25 18:07
     */
    List<EventDetail> getEventDetailByManAndEventId(Integer firemanId,Integer eventId);

    /**
     * 根据事件id获取所有事件详情
     * @Author frank.fang
     * @Date 2016/4/25 18:07
     */
    List<EventDetail> getEventDetailByEventId(Integer eventId);

    /**
     *
     * @Author frank.fang
     * @Date 2016/4/28 16:59
     */
    Pagination listEventDetail(int pageNo, int pageSize);

    /**
     * 修改时间详细
     * @Author frank.fang
     * @Date 2016/4/29 14:27
     */
    Message updateEventDetail(EventDetail eventDetail);

    /**
     * 新增事件详细
     * @Author frank.fang
     * @Date 2016/4/29 14:35
     */
    Message addEventDetail(EventDetail eventDetail);

    /**
     * 查处所有消防员
     * @Author frank.fang
     * @Date 2016/4/29 17:08
     */
    List<Fireman> listFireman();

    /**
     * 删除事件详细
     * @Author frank.fang
     * @Date 2016/5/3 9:43
     */
    Message delEventDetailByIds(List<String> idList);
}
