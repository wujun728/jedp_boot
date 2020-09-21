package ${packageName}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ${packageName}.common.utils.AnnotationHelper;

/**
 * 
 * IndexController
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class IndexController
{
    @Autowired
    ApplicationContext applicationContext;
    
    /**
     * 首页
     * 
     * @param model
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model)
        throws Exception
    {
        model.addAttribute("urls", AnnotationHelper.getRequestMappingURL(applicationContext));
        return "index";
    }
}
