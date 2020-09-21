package com.springmvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zsq on 16/12/21.
 */
@Controller
public class UploadController {

    /**
     * 文件上传
     * 在配置文件中配置 MultipartResolver 返回一个bean
     * 在控制器中用 MultipartFile 接受上传的文件, 用MultipartFile[] 接受多个文件.
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file) {
        String urlPath = "/Users/zsq/images/" + file.getOriginalFilename();
        try {
            FileUtils.writeByteArrayToFile(new File(urlPath), file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
