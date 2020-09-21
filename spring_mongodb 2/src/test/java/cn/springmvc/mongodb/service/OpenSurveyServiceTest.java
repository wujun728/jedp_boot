package cn.springmvc.mongodb.service;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.springmvc.mongo.common.utils.fmt.FormatFactory;
import cn.springmvc.mongo.entity.OpenSurvey;
import cn.springmvc.mongo.service.OpenSurveyService;

/**
 * @author Vincent.wang
 *
 *         production为生产环境，development为测试环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/applicationContext-dao.xml" })
@ActiveProfiles("development")
public class OpenSurveyServiceTest {

    private static final Logger log = LoggerFactory.getLogger(OpenSurveyServiceTest.class);

    @Autowired
    private OpenSurveyService openSurveyService;

    @Test
    public void findOpenSurveyAll() {
        try {
            List<OpenSurvey> surveys = openSurveyService.findOpenSurveyAll();
            if (CollectionUtils.isNotEmpty(surveys)) {
                for (OpenSurvey s : surveys) {
                    log.warn("## {}", FormatFactory.objectToJson(s));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addOpenSurver() {
        try {
            OpenSurvey s = new OpenSurvey();
            // s.setId("00c3d1a9-ea7d-41cd-a07f-f5768de4");
            s.setId("00da34a2-5057-4c5b-b273-e5ee9256");
            s.setAllowreport(0);
            s.setPagecount(1);
            s.setQuestioncount(1);
            s.setSamplecount(1);
            s.setSamplesum(1);
            s.setShow(1);
            s.setSurveyurl("www.baidu.com");
            s.setTag("问卷");
            s.setType(1);
            s.setTypename("问卷");
            s.setUid("sss");
            s.setUsersurveycount(1);
            s.setViewersum(1);
            s.setCreatetime(Calendar.getInstance().getTime());
            s.setName("这是我的问卷，希望得到您的回复！");
            s.setDescription("婚礼活动策划yesno");
            openSurveyService.addOpenSurver(s);
            System.out.print("# 新增数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
