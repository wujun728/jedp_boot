package cn.springmvc.hibernate.dao;

import java.util.List;

import cn.springmvc.hibernate.model.OpenSurvey;

/**
 * 
 * @author Vincent.wang
 *
 */
public interface OpenSurveyDao {

    /**
     * 全量查询公开问卷
     * 
     */
    public OpenSurvey findOpenSurveyById(String id);

    /**
     * 全量查询公开问卷
     * 
     */
    public List<OpenSurvey> findOpenSurveyAll();

}
