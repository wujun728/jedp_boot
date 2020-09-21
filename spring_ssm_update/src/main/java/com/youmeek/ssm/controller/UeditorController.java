package com.youmeek.ssm.controller;

/*import com.baidu.ueditor.ActionEnter;*/
import com.baidu.ueditor.ActionEnter;
import com.youmeek.ssm.pojo.Spcolumn;
import com.youmeek.ssm.service.EditorService;
import com.youmeek.ssm.util.ConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Ueditor Controller
 * @Author frank.fang
 * @Date 2016/5/4 9:04
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController extends BaseController {

    /**
     * 上传根目录
     */
    @Value("${rootPath}")
    String rootPath;

    @Autowired
    EditorService editorService;

    /*private static final String configJSONPath = "/resources/config.json";*/

    @RequestMapping("/dispatch")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到ueditor
     * @Author frank.fang
     * @Date 2016/5/4 9:04
     */
    @RequestMapping("/index")
    public String toEditor() {
        return "/ueditor/upload";
    }

    /**
     * 获取所有列表
     * @Author frank.fang
     * @Date 2016/5/5 11:04
     */
    @RequestMapping("/getAllSpCol")
    @ResponseBody
    public List<Spcolumn> getSpCol() {
        List<Spcolumn> spcolumns = editorService.getAllSpCol();
        return spcolumns;
    }

    /**
     * 表单提交
     * @Author frank.fang
     * @Date 2016/5/5 11:05
     */
    @RequestMapping(value="/submit")
    @ResponseBody
    public String submitForm(@RequestParam(value = "picture", required = false) MultipartFile uploadPic,
                             @RequestParam(value = "video", required = false) MultipartFile uploadVideo,
                             @RequestParam(value = "tt", required = false) String title,
                             @RequestParam(value = "spCol", required = false) String spCol,
                             @RequestParam(value = "childCol", required = false) String childCol,
                             HttpServletRequest request) {

        Object obj = request.getParameter("content");

        /*String picPath = editorService.saveFile(uploadPic,spCol,childCol, ConfigConstant.FileType.IMAGE);
        String videoPath = editorService.saveFile(uploadVideo,spCol,childCol, ConfigConstant.FileType.VIDEO);*/

        return null;
    }
}
