package cn.springmvc.mongo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.mongo.entity.OpenSurvey;
import cn.springmvc.mongo.repository.BaseRepository;
import cn.springmvc.mongo.service.OpenSurveyService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class OpenSurveyServiceImpl implements OpenSurveyService {

    private static final Logger log = LoggerFactory.getLogger(OpenSurveyServiceImpl.class);

    @Autowired
    private BaseRepository<OpenSurvey> baseRepository;

    @Override
    public OpenSurvey findOpenSurveyById(String id) {
        log.warn("## find OpenSurvey By Id , id={}", id);
        return baseRepository.findOne(id, OpenSurvey.class);
    }

    @Override
    public List<OpenSurvey> findOpenSurveyAll() {
        return baseRepository.findAll(OpenSurvey.class);
    }

    @Override
    public void addOpenSurver(OpenSurvey survey) {
        baseRepository.insert(survey);
    }

}
