package com.BBS.controller;

import com.BBS.pojo.Forum;
import com.BBS.pojo.User;
import com.BBS.service.ForumService;
import com.BBS.service.GeneralService;
import com.BBS.service.UserService;
import com.BBS.utils.*;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dyl on 2018/7/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(StringUtils.class);

    private static final String SESSION_USER = "session_user";

    //设置日期格式
//    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    private final UserService userService;

    private final ForumService forumService;
    private final GeneralService generalService;

    @Autowired
    public UserController(GeneralService generalService, ForumService forumService, UserService userService) {
        this.generalService = generalService;
        this.forumService = forumService;
        this.userService = userService;
    }


    @RequestMapping("/register")
    public ModelAndView register(ModelAndView mv, HttpServletRequest request, User user) {
        //获取前端控件用户输入值
        String Registeloginname = request.getParameter("Registeloginname");
        String Registeusername = request.getParameter("Registeusername");
        String Registerpassword = request.getParameter("Registerpassword");

        String ExistLoginName = userService.findByusername(Registeloginname);
        String ExistUserName = userService.findByusername(Registeusername);
        Thread thread = new Thread();
        //判断注册账号是否已经被注册
        if (Registeloginname.equals(ExistLoginName)) {

            request.setAttribute("error_massage", "该账号已被占用！");
            mv.setViewName("jsp/register1");
            return mv;
        }
        //判断用户名是否已经被注册
        if (Registeusername.equals(ExistUserName)) {
            request.setAttribute("error_massage", "该用户名已存在！");
            mv.setViewName("jsp/register1");
            return mv;
        }
        //成功后将数据保存，插入数据库
        user.setLoginname(Registeloginname);
        user.setUsername(Registeusername);
        user.setPassword(Registerpassword);
        userService.insertUser(user);
        request.setAttribute("massage", "恭喜！注册成功！");
        mv.setViewName("jsp/test");
        return mv;


    }

    @RequestMapping("/forgetPW")
    public ModelAndView forgerPw(ModelAndView mv, HttpServletRequest request) {


        mv.setViewName("jsp/test");
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(ModelAndView mv, RedirectAttributes redirectAttributes, HttpServletRequest request,User user) {
        String verifyCode = request.getSession().getAttribute("verifyCode").toString().toLowerCase();//获取session中的验证码  不区分大小写

        String vercode = request.getParameter("vercode").toLowerCase();//获取用户提交的验证码
        Subject currentUser = SecurityUtils.getSubject();

        //1. 接受提交的当事人的证书，以及host地址，第一参数用户名，第二个参数密码，第三个参数host，我这里传了ID：
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginname(), user.getPassword(), user.getId());
        try {
            //用户认证
            currentUser.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            mv.setViewName("bbs_jsp/user/Login");
            return mv;
        }
        if (currentUser.isAuthenticated()) {
            //登录成功，保存用户相关信息
            User session_user = this.userService.findByLoginName(user.getLoginname());
            SessionUtils.setAttr(request,AppConstant.SESSION_USER,session_user);
            //取得总条数
            Map<String,String> map = new HashMap<String, String>();
            map.put("tableName","b_forum");
            int count = generalService.getCount(map);

            //获取分页的数据
            PageUtils<Forum> page = new  PageUtils<Forum>(count);
            page.setPageNo(1);
            page.getStart(1);

            page.setPagelist(forumService.AllForum(page));

            List<Map<String, Object>> weeks = forumService.weeks();
            //跳转成功页面
            mv.setViewName("bbs_jsp/index");
            mv.addObject("Forum", page);
            mv.addObject("Weeks", weeks);
            return mv;
        } else {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            mv.setViewName("bbs_jsp/user/Login");
            return mv;
        }
    }

    //退出登录
    @RequestMapping("/seeout")
    public ModelAndView seeout(HttpServletRequest request, ModelAndView modelAndView)
            throws ServletException, IOException {
        SessionUtils.setUser(request, null);
        modelAndView.setViewName("jsp/login");
        return modelAndView;
    }

    /*
    角色列表
     */
    @RequestMapping("/getuser")
    public List<User> FinUserList() {
        List<User> userList = userService.FinUserList();
        return userList;
    }

    /*
      用户修改密码接口
     */
    @RequestMapping("/rePass")
    public void RePassWord(HttpServletRequest request,String params) {
        String userid = request.getParameter("userid");
//        Map<String, Object> queryParams = StringUtils.paramsToMap(params);
        this.generalService.opsForStringSet("success","成功了吗");
        System.out.print(this.generalService.opsForStringGet("success"));
    }

    @RequestMapping("findByuser")
    public Map<String, Object> findByuser(String params) {
        JSONObject jso = new JSONObject();
        Map<String, Object> pageDate = new HashMap<>();
        try {
            Map<String, Object> queryParams = StringUtils.paramsToMap(params);
            Page<User> page = userService.findByParams(queryParams);
            pageDate.put("total", page.getTotalElements());
            pageDate.put("row", page.getContent());
            JSONMsg.getJSONOkMsg(jso, "查询成功").put("date",page);
            return pageDate;
        } catch (Exception e) {
            LOGGER.error("用户信息查询异常！",e);
            return JSONMsg.getJSONErrMsg(jso,"查询用户信息异常！"+e.getMessage().toString());
        }

    }

    //个人用户主页
    @RequestMapping("/userhome")
    public ModelAndView userhome(ModelAndView mv,String userid,String replyauthor,HttpSession session) {
        User personal;
        //如果userID=空表示，访问自己的主页
        if (userid == null||userid.equals("")) {
            personal = (User) session.getAttribute("session_User");
            userid = personal.getId();
        }

        personal = userService.findByLoginName(userid);
        List<Map<String,Object>> Recently_answered = userService.Recently_answered(replyauthor);
        List<Map<String, Object>> Forum = userService.personal(userid);

        mv.addObject("answered", Recently_answered);
        mv.addObject("Forum", Forum);
        mv.addObject("personal", personal);
        mv.setViewName("bbs_jsp/user/userhome");
        return mv;
    }

    //用户中心数据
    @RequestMapping("/index")
    public ModelAndView UserIndex(ModelAndView mv, String userid) {

        List<Map<String,Object>> userForun = userService.personal(userid); //获取个人用户所发的帖子
        mv.addObject("userForum", userForun);
        mv.setViewName("bbs_jsp/user/index");
        return mv;
    }



    //用户设置界面
    @RequestMapping("/Set")
    public ModelAndView UserSet(ModelAndView mv, String params) {

        mv.setViewName("bbs_jsp/user/set");
        return mv;
    }

    //我的消息界面
    @RequestMapping("/message")
    public ModelAndView UserMessage(ModelAndView mv, String params) {

        mv.setViewName("bbs_jsp/user/UserMessage");
        return mv;
    }

    //获取验证码接口
    @RequestMapping("VerifyImg")
    public void VerifyImg(HttpServletResponse response, HttpSession session) {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragrma","no-cache");
        response.setDateHeader("Expires",0);
        int width = 120;
        int height = 36;
        int red;
        int green;
        int blue;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics g = img.getGraphics();
        g.setColor(new java.awt.Color(0, 0, 127));
        g.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 26));
        g.fillRect(0,0,width,height);
        String verifyCode = "";
        for(int i=0; i<4; i++){
            java.util.Random random = new java.util.Random();
            int ic = random.nextInt(58)+65;
            if(ic>90 && ic<97){
                i--;
                continue;
            }
            char c = (char)(ic);
            verifyCode += c;
            red = (int)(Math.random()*256);
            green = (int)(Math.random()*256);
            blue = (int)(Math.random()*256);
            g.setColor(new java.awt.Color(red, green, blue));
            g.drawString(String.valueOf(c), width/6*i+20, height/2+8);
        }
        session.setAttribute("verifyCode", verifyCode);
        g.dispose();
        try {
            javax.imageio.ImageIO.write(img, "JPEG", response.getOutputStream());
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

    }

    @RequestMapping("userCheckIn")
    @ResponseBody
    public ResultVO userCheckIn(HttpServletRequest request) {
        ResultVO result = new ResultVO();

        User user = (User) request.getSession().getAttribute(SESSION_USER); //获取请求中的session信息
        try {
            if (user != null && user.getId() != null) {  //判断是否存在这用户
                user.setIsCheckIn(1); //存在表示用户已登录，则可以签到
                request.getSession().setAttribute(SESSION_USER,user);
                this.generalService.executeSql("update b_user  set is_check_in = 1 where id = ?" ,new Object[]{user.getId()}); //设置用户的签到信息
            }
            result.setSuccessMsg(AppConstant.successMsg);
            result.setSuccessCode(AppConstant.successCode);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg("签到失败");
            result.setErrorCode("false");
        }

        return result;
    }


}

