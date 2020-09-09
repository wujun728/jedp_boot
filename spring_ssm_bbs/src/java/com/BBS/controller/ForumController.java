package com.BBS.controller;

import com.BBS.pojo.Forum;
import com.BBS.pojo.ReplyForum;
import com.BBS.pojo.User;
import com.BBS.service.ForumService;
import com.BBS.service.GeneralService;
import com.BBS.utils.PageUtils;
import com.BBS.utils.ResultVO;
import com.BBS.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dyl on 2019/1/2.
 * 帖子控制器，发帖，回帖等
 */


@Controller
@RequestMapping("/Forum")
public class ForumController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForumController.class);

    private static final String Message = "message";

    //设置日期格式
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    private final ForumService forumService;
    private final GeneralService generalService;

    @Autowired
    public ForumController(ForumService forumService, GeneralService generalService) {
        this.forumService = forumService;
        this.generalService = generalService;
    }


    //发帖主页
    @RequestMapping("add")
    public ModelAndView ForumMain(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/Forum/AddForm");
        return modelAndView;
    }


    //    发帖操作
    @RequestMapping("AddForum")
    public ModelAndView addForum(ModelAndView modelAndView, Forum forum, HttpServletRequest request) {
        Forum setForum = new Forum();


        //获取session对象中的用户对象
        User user = (User) request.getSession().getAttribute("session_User");

        //获取当前系统时间
        long datetime = System.currentTimeMillis();


        setForum.setAuthor(user.getLoginname());
        setForum.setAuthorPath(user.getPicPath());
        setForum.setBbsType(forum.getBbsType());
        setForum.setContent(forum.getContent());
        setForum.setMainid(user.getId());
        setForum.setTitle(forum.getTitle());
        setForum.setReward(forum.getReward());
        //新发帖子默认未结
        setForum.setStatus(0);
        setForum.setDel_flag(0);
//        setForum.setReadTimes(1);
        setForum.setMakeDate(df.format(new Date(datetime)));
        setForum.setMakeTime(dt.format(new Date(datetime)));
//        setForum.setForumid(StringUtils.getUUID());
//        forumService.AddByForum(setForum);
        this.generalService.save(setForum);
        modelAndView.addObject(Message, "发帖成功！快去看看吧！");
        modelAndView.setViewName("bbs_jsp/other/Congratulation");
        return modelAndView;
    }

    //    获取所有帖子内容
    @RequestMapping("getForum")
    @ResponseBody
    public PageUtils getAllForum(HttpSession session, HttpServletRequest request, int pageNo) {
        //取得总条数
        Map<String, String> map = new HashMap<String, String>();
        map.put("tableName", "b_forum");
//        map.equals("1");
//        Collections.synchronizedMap(map);
//        Hashtable hashtable;

        int count = generalService.getCount(map);

        //获取分页的数据
        PageUtils<Forum> page = new PageUtils<Forum>(count);
        pageNo = pageNo == 0 ? 1 : pageNo;
        page.setPageNo(pageNo);
        page.getStart(pageNo);

        page.setPagelist(forumService.AllForum(page));

        List<Map<String, Object>> weeks = forumService.weeks();


        session.setAttribute("Forum", page);
        session.setAttribute("Weeks", weeks);
        return page;
    }

    //    获取帖子详细数据
    @RequestMapping("/ForumDetail")
    public ModelAndView DetilForum(ModelAndView modelAndView, String Forumid) {
        Forum userForum = forumService.queryByForum(Forumid);
        Integer readTimes = userForum.getReadTimes() + 1;
        userForum.setReadTimes(readTimes);

        //更新帖子阅读数
        Forum Forum = new Forum();
        Forum.setReadTimes(readTimes);
        Forum.setForumid(Forumid);
        forumService.updateReadTimes(Forum);

        //获取帖子相关的回复与评论数据
        List<ReplyForum> replyForum = new ArrayList<>();
        try {
            replyForum = forumService.queryreplyForum(Forumid);

        } catch (Exception e) {
            LOGGER.error("获取失败", e.getMessage());

        }

        modelAndView.addObject("userForum", userForum);
        modelAndView.addObject("replyForum", replyForum);
        modelAndView.setViewName("bbs_jsp/Forum/ForumDetail");
        return modelAndView;
    }


    //    删除帖子数据
    @RequestMapping(value = "deleteForum", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO deleteForum(Integer ForumId) {
        ResultVO result = new ResultVO();
        try {
            forumService.deleteByForum(ForumId);
            result.setSuccessCode(ResultVO.SUCCESS);
            result.setSuccessMsg("删除成功！");
        } catch (Exception e) {
            LOGGER.error("删除失败", e);
            result.setErrorCode(ResultVO.FAIL);
            result.setErrorMsg("删除失败");
        }
        return result;
    }

    //    置顶帖子方法
    @RequestMapping(value = "topForum", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO top_Forum(String ForumId, Integer status) {
        ResultVO result = new ResultVO();
        Forum setForum = new Forum();
        setForum.setForumid(ForumId);
        setForum.setTop_status(status);
        try {
            forumService.updatetop(setForum);
            result.setSuccessCode(ResultVO.SUCCESS);
            result.setSuccessMsg("置顶成功！");
        } catch (Exception e) {
            LOGGER.error("置顶失败", e);
            result.setErrorCode(ResultVO.FAIL);
            result.setErrorMsg("置顶失败");
        }
        return result;
    }

    //加精帖子方法
    @RequestMapping(value = "delicateForum", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO delicate_forum(String ForumId, Integer status) {

        ResultVO result = new ResultVO();
        Forum setForum = new Forum();
        setForum.setForumid(ForumId);
        setForum.setDelicate_status(status);
        try {
            forumService.updatedelicate(setForum);
            result.setSuccessCode(ResultVO.SUCCESS);
            result.setSuccessMsg("加精成功！");
        } catch (Exception e) {
            LOGGER.error("加精失败", e);
            result.setErrorCode(ResultVO.FAIL);
            result.setErrorMsg("加精失败");
        }
        return result;
    }

    //    回帖操作
    @RequestMapping(value = "replyForum", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO replyForum(HttpServletRequest request, @RequestBody String params) {
//        String context = request.getParameter("content");
        ResultVO resultVO = new ResultVO();

        long datetime = System.currentTimeMillis();

        Map<String, Object> replyForum = StringUtils.paramsToMap(params);
        replyForum.put("makeTime", df.format(new Date(datetime)));
        replyForum.put("makeDate", dt.format(new Date(datetime)));
        replyForum.put("status", 0);
        replyForum.put("del_flag", 0);
        try {
            forumService.replyForum(replyForum);


            Forum forum = forumService.queryByForum(replyForum.get("forumid").toString());
            Integer replyTimes = 0;
            replyTimes = forum.getReplyTimes() + 1;


            Forum replyforum = new Forum();
            replyforum.setReplyTimes(replyTimes);
            replyforum.setForumid(replyForum.get("forumid").toString());
            forumService.updateReplyTimes(replyforum);

            resultVO.setSuccessMsg("成功请求");
            resultVO.setSuccessCode(ResultVO.SUCCESS);
        } catch (Exception e) {
            LOGGER.error("回帖失败", e);
            resultVO.setErrorMsg("回帖失败");
            resultVO.setErrorCode(ResultVO.FAIL);
        }
        return resultVO;
    }

    // 点赞操作
    @RequestMapping(value = "ForumZan", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO ForumZan(@RequestBody String params) {
        ResultVO resultVO = new ResultVO();

//        Map<String, Object> queryParams = StringUtils.paramsToMap(params);
        return resultVO;
    }

    //删除回复操作
    @RequestMapping(value = "DeleteReply", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO DeleteReply(@RequestBody String params) {
        ResultVO resultVO = new ResultVO();
        Map<String, Object> replyparams = StringUtils.paramsToMap(params);
        try {
            forumService.deletereply(replyparams.get("id").toString());
            resultVO.setSuccessCode(ResultVO.SUCCESS);
            resultVO.setSuccessMsg("删除成功！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultVO.setErrorCode(ResultVO.FAIL);
            resultVO.setErrorMsg("删除失败！");
        }
        return resultVO;
    }

    //采纳回复操作
    @RequestMapping(value = "AcceptReply", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO AcceptReply(@RequestBody String params) {
        ResultVO resultVO = new ResultVO();
        Map<String, Object> replyparams = StringUtils.paramsToMap(params);
        Forum forum = new Forum();
        forum.setStatus(1);
        forum.setForumid(replyparams.get("Forumid").toString());
        try {
            forumService.accpetreply(replyparams.get("id").toString());
            forumService.updateByForum(forum);
            resultVO.setSuccessCode(ResultVO.SUCCESS);
            resultVO.setSuccessMsg("采纳成功！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultVO.setErrorCode(ResultVO.FAIL);
            resultVO.setErrorMsg("采纳失败！");
        }
        return resultVO;
    }

    //    筛选帖子类型
    @RequestMapping("/searchForum")
    @ResponseBody
    public List searchForum(HttpSession session, String bbsType, int pageNo) {

        if (bbsType != null) {

            Map<String, String> map = new HashMap<String, String>();
            map.put("tableName", "b_forum");

            int count = generalService.getCount(map);

            //获取分页的数据
            PageUtils<Forum> page = new PageUtils<Forum>(count);
            pageNo = pageNo == 0 ? 1 : pageNo;
            page.setPageNo(pageNo);
            page.getStart(pageNo);
            page.setParameter(bbsType);
            List<Forum> Forum = forumService.searchForum(page);
            page.setPagelist(Forum);

            session.setAttribute("Forum", page);
            return Forum;
        } else {
            return new ArrayList();
        }

    }

}
