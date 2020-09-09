package com.BBS.controller;


import com.BBS.pojo.Forum;

import com.BBS.pojo.User;
import com.BBS.service.ForumService;

import com.BBS.service.GeneralService;
import com.BBS.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.BBS.utils.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by dyl on 2019/1/8.
 */
@Controller
public class GeneralController {

    private final ForumService forumService;
    private final GeneralService generalService;

    @Autowired
    public GeneralController(ForumService forumService, GeneralService generalService) {
        this.forumService = forumService;
        this.generalService = generalService;
    }


    @RequestMapping("/Forumindex")
    public ModelAndView Forumindex(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/Forum/Forumindex");
        return modelAndView;
    }

    @RequestMapping("/File")
    public ModelAndView FileTest(ModelAndView modelAndView) {
        modelAndView.setViewName("jsp/File");
        return modelAndView;
    }

    @RequestMapping("/FileUpload")
    @ResponseBody
    public ResultVO getFileTest(MultipartHttpServletRequest request) {

        ResultVO result = new ResultVO();
        User user = SessionUtils.getUser(request);
        MultipartFile file = request.getFile("file");  //获取文件对K象

        String name = file.getOriginalFilename();
        String path =  "D:"+File.separator+"IDEAWorks"+File.separator+"BBS_SSm"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"images";
        File filePath = new File(path +File.separator+ file.getOriginalFilename());
        filePath.renameTo(new File(name + new Date().toString()));
        try {
            file.transferTo(filePath);
            user.setPicPath(name);
            this.generalService.update(user);
            String path1 = filePath.getAbsolutePath();
            result.setSuccessMsg(name);
            result.setSuccessCode("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorCode("error");
            result.setErrorMsg("error");
        }
        return result;

    }


    @RequestMapping("page")
    @ResponseBody
    public PageUtils<Forum> pageInfo(int pageNo) {

        //取得总条数
        Map<String, String> map = new HashMap<String, String>();
        map.put("tableName", "b_forum");

        int count = generalService.getCount(map);

        List<Map<String, Object>> list = this.generalService.findForJdbc("select * from b_forum where del_flag = ?", new Object[]{"1"});
        int del_flag = 0;
        List<User> userList = this.generalService.findHql("From User where del_flag =? ", new Object[]{del_flag});

        //获取分页的数据
        PageUtils<Forum> page = new PageUtils<Forum>(count);
        page.setPageNo(pageNo);
        page.getStart(pageNo);
//        pages.setPageList(bookDao.findBook(pages.getStart(pageNo), pages.getPageSize()));
        page.setPagelist(forumService.AllForum(page));

        return page;
    }

    //通用获取图片展示方法
    @RequestMapping("getPhoto")
    public void getPhoto(HttpServletResponse response, String imgUrl, HttpServletRequest request) throws Exception {
        String path =  "D:"+File.separator+"IDEAWorks"+File.separator+"BBS_SSm"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"images";
        User user = SessionUtils.getUser(request);
        if (!"".equals(imgUrl)) {
            File file = new File(path + File.separator + imgUrl);
            FileInputStream fis;
            fis = new FileInputStream(file);

            long size = file.length();
            byte[] temp = new byte[(int) size];
            fis.read(temp, 0, (int) size);
            fis.close();
            byte[] data = temp;
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } else {
            response.setContentType("text/javascript");
            PrintWriter printWriter = response.getWriter();
            printWriter.write("不存在此相片");
            printWriter.flush();
            printWriter.close();
        }

    }

    public ResultVO UploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest Mlt = (MultipartHttpServletRequest) request;

        ResultVO result = new ResultVO();
        InputStream is = null;
        List<List<Object>> list = null;
        MultipartFile file = Mlt.getFile("file");
        if (file.isEmpty()) {
            result.setErrorCode(AppConstant.errorCode);
            result.setErrorMsg(AppConstant.erroeMsg);
            return result;
        }
        is = file.getInputStream();  //获取文件流
        list = new ExcelImportUtil().getBankListByExcel(is, file.getOriginalFilename()); //获取Excel的数据内容
        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (List<Object> lo : list) {
            User family = new User();
            family.setLoginname(String.valueOf(lo.get(0)));
            family.setPassword(String.valueOf(lo.get(1)));
            family.setPhone(String.valueOf(lo.get(2)));
            family.setPlace(String.valueOf(lo.get(3)));

//            this.generalService.executeSql("update b_user set loginname = ? ,password =?,phone = ?,place = ? where id =? ",new Object[]{family.getLoginname()
//                    , family.getPassword(), family.getPhone(), family.getPlace(),family.getId()});
        }
        result.setSuccessCode(AppConstant.successCode);
        result.setSuccessMsg(AppConstant.successMsg);
        return result;
    }


}


