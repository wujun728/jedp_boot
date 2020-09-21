package cn.springmvc.mongo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.springmvc.mongo.entity.OpenSurvey;
import cn.springmvc.mongo.service.OpenSurveyService;

@Controller("survey")
public class SurveyController {

    // private static final Logger log = LoggerFactory.getLogger(SurveyController.class);

    @Autowired
    private OpenSurveyService openSurveyService;

    @RequestMapping(name = "/list", method = RequestMethod.GET)
    public String survey(Model model) {
        List<OpenSurvey> surveys = openSurveyService.findOpenSurveyAll();
        model.addAttribute("surveys", surveys);
        return "survey_list";
    }

}
