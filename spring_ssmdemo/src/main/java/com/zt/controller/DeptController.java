package com.zt.controller;

import com.zt.pojo.SysDept;
import com.zt.service.IDeptService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by CDHong on 2018/4/3.
 */

@Controller  //声明为控制器，实例创建交给Spring管理
@RequestMapping("/dept")  //根命名空间，用于区分不同模块对应的请求
public class DeptController {

    //自动注入控制器中需要DeptService实例
    @Autowired private IDeptService deptService;

    /**
     * 根据ID查询部门信息
     * @param id  前端路径传递的部门编号
     * @param model 返回的前端的数据，作用域类似于HttpServletRequest
     * @return  返回视图解析器的路径
     */
    @RequestMapping(value = "/getDeptInfoById/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable("id") Integer id, Model model){
        SysDept dept = deptService.findDeptById(id);
        model.addAttribute("dept",dept);
        return "showDept";
    }

}
