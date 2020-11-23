/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.controller;

import cn.kiwipeach.demo.domain.Department;
import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.domain.dto.EmployDeptDTO;
import cn.kiwipeach.demo.framework.response.MulityResponse;
import cn.kiwipeach.demo.framework.response.SimpleResponse;
import cn.kiwipeach.demo.mapper.DepartmentMapper;
import cn.kiwipeach.demo.service.DepartmentService;
import cn.kiwipeach.demo.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

/**
 * Create Date: 2018/01/26
 * Description: 员工管理相关控制器
 *
 * @author Wujun
 */
@Controller
@RequestMapping("employ")
public class EmployController {

    @Autowired
    private EmployService employService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 表单回显ModelAttribute
     *
     * @param empno 员工编号
     * @param model 框架model
     */
    @ModelAttribute
    public void initEmploy(@RequestParam(value = "empno", required = false) String empno, Model model) {
        Employ employ;
        if (!StringUtils.isEmpty(empno)) {
            employ = employService.queryEmploy(new BigDecimal(empno));
            if (employ == null) {
                employ = new Employ();
            }
        } else {
            employ = new Employ();
        }
        //员工信息、部门信息、经理信息回显
        model.addAttribute("employ", employ);
        model.addAttribute("managers", employService.queryAll());
        model.addAttribute("depts", departmentService.queryAll());
    }

    @RequestMapping(value = "home", method = {RequestMethod.GET})
    public String toEmployIndexPage(Model model) {
        List<Employ> employList = employService.queryAll();
        MulityResponse<Employ> mulityResponse = new MulityResponse<Employ>(employList);
        model.addAttribute("employList", mulityResponse);
        return "employ/index";
    }

    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    @ResponseBody
    public MulityResponse<Employ> queryAllEmployData() {
        List<Employ> employList = employService.queryAll();
        return new MulityResponse<Employ>(employList);
    }

    @RequestMapping(value = "queryEmpDeptMsg/{empno}", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<EmployDeptDTO> queryEmpDeptMsg(@PathVariable("empno") String empno) {
        EmployDeptDTO employDeptDTO = employService.queryEmployDeptMsg(empno);
        return new SimpleResponse<EmployDeptDTO>(employDeptDTO);
    }


    @RequestMapping(value = {"add", "update"}, method = RequestMethod.GET)
    public String toAddEmployPage() {
        return "employ/form";
    }

    @RequestMapping(value = "saveOrUpdate", method = {RequestMethod.POST, RequestMethod.PUT})
    public String saveOrUpdateEmploy(Employ employ) throws UnsupportedEncodingException {
        int row = employService.saveOrUpdate(employ);
        StringBuffer resultMsg = new StringBuffer(employ.getEname());
        String empno = String.valueOf(employ.getEmpno());
        if (row < 1) {
            boolean insertOperation = StringUtils.isEmpty(empno) ? true : false;
            resultMsg.append(insertOperation ? ":插入失败" : ":更新失败");
        } else {
            resultMsg.append(":操作成功");
        }
        return "redirect:/employ/add?empno=" + empno + "&info=" + URLEncoder.encode(resultMsg.toString(), "utf-8");
    }

    @RequestMapping(value = "delete/{empno}", method = RequestMethod.DELETE)
    @ResponseBody
    public SimpleResponse<Integer> deleteEmploy(@PathVariable(name = "empno", required = true) String empno) {
        int removeRow = employService.remove(empno);
        return new SimpleResponse<Integer>(removeRow);
    }

}
