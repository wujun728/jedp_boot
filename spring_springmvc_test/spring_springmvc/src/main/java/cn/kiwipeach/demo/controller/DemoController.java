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

import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.annotations.CurrentUser;
import cn.kiwipeach.demo.framework.response.Pageable;
import cn.kiwipeach.demo.framework.response.SimpleResponse;
import cn.kiwipeach.demo.framework.response.util.ResponseUtil;
import cn.kiwipeach.demo.service.EmployService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Create Date: 2018/01/08
 * Description: Demo控制器
 *
 * @author Wujun
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private EmployService employService;

    @RequestMapping(value = "toEmpDetail", method = RequestMethod.GET)
    public String toEmployDetailPage(@RequestParam("empno") BigDecimal empno, Model model) {
        Employ employ = employService.queryEmploy(empno);
        model.addAttribute("employ", employ);
        return "/WEB-INF/views/emp_detail.jsp";
    }

    @RequestMapping(value = "queryEmpDetail", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<Employ> queryDetailData(@RequestParam("empno") BigDecimal empno) {
        Employ employ = employService.queryEmploy(empno);
        return ResponseUtil.simpleSuccess(employ);
    }

    @RequestMapping(value = "mulityds/pagequery", method = RequestMethod.GET)
    @ResponseBody
    public Pageable<Employ> queryMulityDSPageData(
            @RequestParam(value = "jobNameList", required = false) List<String> jobNameList,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "dbtype", required = false) String dbtype
    ) {
        Pageable<Employ> pageable = new Pageable<Employ>(pageNo, pageSize);
        employService.pqgeQueryEmployInfo(jobNameList, pageable, dbtype);
        return pageable;
    }

    @RequestMapping(value = "testMulityDTransactionService", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<Integer> testMulityDTransactionService(@CurrentUser("employ") Employ employ) {
        Employ another = new Employ();
        BeanUtils.copyProperties(employ, another);
        int insertTotalRow = employService.testMulityDTransactionService(employ, another, true);
        return ResponseUtil.simpleSuccess(insertTotalRow);
    }


    /**
     * 测试参数解析器
     *
     * @param employ 员工
     * @return 报文
     */
    @RequestMapping(value = "argumentresolver", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<Employ> testArgumentResolvers(@CurrentUser("employ") Employ employ) {
        return ResponseUtil.simpleSuccess(employ);
    }

    /**
     * 测试参数转换器
     * 没有转换器会抛出:Failed to convert value of type 'java.lang.String' to required type 'cn.kiwipeach.demo.domain.Employ';
     *
     * @param employ 分页对象
     * @return 分页报文
     */
    @RequestMapping(value = "converter", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse<Employ> testConverter(@RequestParam("employ") Employ employ) {
        System.out.println("testConverter is invoking....");
        return ResponseUtil.simpleSuccess(employ);
    }

    /**
     * 测试get方式地址栏乱码解决方案
     *
     * @param urlArgs 地址栏中文参数
     * @return
     */
    @RequestMapping(value = "urlargs", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<String> testUriString(@RequestParam("urlArgs") String urlArgs) {
        System.out.println("testUriString is invoking....");
        return ResponseUtil.simpleSuccess(urlArgs);
    }


    @RequestMapping(value = "pojo", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse<Employ> testPojo(Employ employ) {
        System.out.println("pojo is invoking....");
        return ResponseUtil.simpleSuccess(employ);
    }

    @RequestMapping(value = "initBander", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse<Employ> initBander(Employ employ) {
        System.out.println("initBander is invoking....");
        return ResponseUtil.simpleSuccess(employ);
    }

    @InitBinder
    public void employInitBander(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("job");
    }

    @RequestMapping(value = "i18n", method = RequestMethod.GET)
    public String toI18nJspPage() throws UnsupportedEncodingException {
        return "i18n";
    }


    @RequestMapping(value = "customView", method = RequestMethod.GET)
    public String testCustomView() throws UnsupportedEncodingException {
        return "myCustomView";
    }
    


}
