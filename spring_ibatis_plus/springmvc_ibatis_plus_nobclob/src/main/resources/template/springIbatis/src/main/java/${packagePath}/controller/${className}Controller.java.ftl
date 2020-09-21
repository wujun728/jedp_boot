package ${packageName}.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ${packageName}.common.PaginationSupport;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;

/**
 * ${className}Controller
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/${instanceName}")
public class ${className}Controller
{
    private static final Logger LOGGER = LoggerFactory.getLogger(${className}Controller.class);
    
    @Autowired
    ${className}Service ${instanceName}Service;
    
    /**
     * String 日期转换为 Date
     * 
     * @param dataBinder
     * @see [类、类#方法、类#成员]
     */
    @InitBinder
    public void dataBinder(WebDataBinder dataBinder)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }
    
    /**
     * 新增/更新数据
     * 
     * @param ${instanceName}
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("item") ${className} ${instanceName}, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            StringBuilder errorMsg = new StringBuilder();
            for (ObjectError error : errors.getAllErrors())
            {
                errorMsg.append(error.getDefaultMessage()).append(" ");
            }
            if (errorMsg.length() > 0)
            {
                LOGGER.info("errors message={}", errorMsg);
            }
            model.addAttribute("page", ${instanceName}Service.queryForPagination(null, 1, 10));
            return "/${instanceName}/show";
        }
        ${instanceName}Service.saveOrUpdate(${instanceName});
        return "redirect:/${instanceName}/list";
    }
    
    /**
     * 删除数据
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable ${pk.javaType} id)
    {
        ${instanceName}Service.deleteById(id);
        return "redirect:/${instanceName}/list";
    }
    
    /**
     * 列表展示
     * 
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") int pageNo, Model model)
    {
        ${className} ${instanceName} = new ${className}();
        if (RandomUtils.nextInt(1, 10) > 1)
        {
        	<#list columns as column>
        	 ${instanceName}.${column.setMethod}(${map[column.javaType]});
        	</#list>
        }
        PaginationSupport<${className}> page = ${instanceName}Service.queryForPagination(null, pageNo, 10);
        model.addAttribute("item", ${instanceName});
        model.addAttribute("page", page);
        return "/${instanceName}/show";
    }
    
    /**
     * 编辑数据
     * 
     * @param id
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable ${pk.javaType} id, Model model)
    {
        model.addAttribute("item", ${instanceName}Service.queryById(id));
        model.addAttribute("page", ${instanceName}Service.queryForPagination(null, 1, 10));
        return "/${instanceName}/show";
    }
}
