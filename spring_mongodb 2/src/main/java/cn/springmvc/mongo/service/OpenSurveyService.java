package cn.springmvc.mongo.service;

import java.util.List;

import cn.springmvc.mongo.entity.OpenSurvey;

/**
 * @author Vincent.wang
 *
 */
public interface OpenSurveyService {

    /**
     * 根据ID查询公开问卷
     * 
     * @param id
     *            公开问卷ID
     * @return
     */
    public OpenSurvey findOpenSurveyById(String id);

    /**
     * 查询所有的公开问卷
     * 
     * @return
     */
    public List<OpenSurvey> findOpenSurveyAll();

    /**
     * 新增一条公开问卷数据
     * 
     * @param survey
     *            问卷对象
     */
    public void addOpenSurver(OpenSurvey survey);

}
