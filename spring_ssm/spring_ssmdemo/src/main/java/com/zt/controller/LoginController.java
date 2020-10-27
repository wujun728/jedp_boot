package com.zt.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/4.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired private DefaultKaptcha kaptcha;

    @RequestMapping({"index",""})
    public String index(){
        return "login";
    }

    @RequestMapping("kaptcha.jpg")
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = kaptcha.createText();
        BufferedImage bi = kaptcha.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
    }

    @RequestMapping("getKaptcha")
    public ResponseEntity getKaptcha(HttpSession session){
        return new ResponseEntity(session.getAttribute(Constants.KAPTCHA_SESSION_KEY), HttpStatus.OK);
    }

}
