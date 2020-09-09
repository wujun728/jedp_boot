package com.BBS.service.impl;

import com.BBS.dao.ForumDao;
import com.BBS.pojo.Forum;
import com.BBS.pojo.ReplyForum;
import com.BBS.service.ForumService;
import com.BBS.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zlq on 2019/1/3.
 */
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    ForumDao forumDao;

    @Override
    public Forum queryByForum(String Forumid) {
        return forumDao.queryByForum(Forumid);
    }

    @Override
    public void AddByForum(Forum forum) {
        forumDao.AddByForum(forum);
    }

    @Override
    public void deleteByForum(int Forumid) {
        forumDao.deleteByForum(Forumid);
    }

    @Override
    public void updateByForum(Forum forum) {
        forumDao.updateByForum(forum);
    }

    @Override
    public ArrayList<Forum> AllForum(PageUtils webPage) {
        return forumDao.AllForum(webPage);
    }

    @Override
    public void updateReadTimes(Forum forum) {
        forumDao.updateReadTimes(forum);
    }

    @Override
    public void updateReplyTimes(Forum forum) {
        forumDao.updateReplyTimes(forum);
    }

    @Override
    public void updatetop(Forum forum) {
        forumDao.updatetop(forum);
    }

    @Override
    public void updatedelicate(Forum forum) {
        forumDao.updatedelicate(forum);
    }

    @Override
    public void replyForum(Map<String, Object> map) {
        forumDao.replyForum(map);
    }

    @Override
    public ArrayList<ReplyForum> queryreplyForum(String forumid) throws SQLException {
        return forumDao.queryreplyForum(forumid);
    }

    @Override
    public void updateZan(Map<String, Object> map) throws SQLException {
            forumDao.updateZan(map);
    }

    @Override
    public void deletereply(String id) {
        forumDao.deletereply(id);
    }

    @Override
    public void accpetreply(String id) {
        forumDao.accpetreply(id);
    }

    @Override
    public ArrayList<Map<String, Object>> weeks() {
        return forumDao.weeks();
    }

    @Override
    public ArrayList<Forum> searchForum(PageUtils bbsType) {
        return  forumDao.searchForum(bbsType);
    }

}
