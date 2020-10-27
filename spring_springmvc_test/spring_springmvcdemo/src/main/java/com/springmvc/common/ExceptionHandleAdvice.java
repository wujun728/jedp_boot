package com.springmvc.common;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zsq on 16/12/20.
 * 定制ControllerAdvice
 */
@ControllerAdvice
public class ExceptionHandleAdvice {


    /**
     * 定义全局的异常处理
     * @param exception
     * @param webRequest
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest webRequest) {
        ModelAndView modelAndView = new ModelAndView("error"); //erro 页面
        //用户在jsp页面中获取errorMessage的异常信息.
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    /**
     * 将键值对添加到全局,所有使用了@RequestMapping的方法可获得此键值对
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    /**
     * 对前台传递的Date进行格式化规定
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
