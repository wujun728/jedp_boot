package com.BBS.service;

import com.BBS.pojo.Forum;
import com.BBS.pojo.ReplyForum;
import com.BBS.utils.PageUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zlq on 2019/1/3.
 */
public interface ForumService {

    //获取帖子数据
    Forum queryByForum(String Forumid);

    //新憎帖子操作
    void AddByForum(Forum forum);

    //删除帖子
    void deleteByForum(int Forumid);

    //更新帖子
    void updateByForum(Forum forum);

    //获取所有帖子信息
    ArrayList<Forum> AllForum(PageUtils webPage);

    //更新帖子点击数
    void updateReadTimes(Forum forum);

    //更新帖子回复数
    void updateReplyTimes(Forum forum);

    //    更新帖子置顶状态
    void updatetop(Forum forum);

    //    更新帖子精贴状态
    void updatedelicate(Forum forum);

    //回帖操作
    void replyForum(Map<String, Object> map);

    //获取对应帖子回复条数以及信息
    ArrayList<ReplyForum> queryreplyForum(String forumid) throws SQLException;

    //点赞操作
    void updateZan(Map<String, Object> map) throws SQLException;

    //删除回帖操作
    void deletereply(String id);

    //采纳回复操作
    void accpetreply(String id);

    //获取周榜人数
    ArrayList<Map<String, Object>> weeks();

    //根据帖子类型筛选帖子数据
    ArrayList<Forum> searchForum(PageUtils bbsType);
 }
