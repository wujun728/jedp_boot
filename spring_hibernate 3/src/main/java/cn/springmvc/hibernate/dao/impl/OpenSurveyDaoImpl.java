package cn.springmvc.hibernate.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.springmvc.hibernate.dao.DaoService;
import cn.springmvc.hibernate.dao.OpenSurveyDao;
import cn.springmvc.hibernate.model.OpenSurvey;

/**
 * @author Vincent.wang
 *
 */
@Repository
public class OpenSurveyDaoImpl implements OpenSurveyDao {

    @Autowired
    private DaoService daoService;

    @Override
    public OpenSurvey findOpenSurveyById(String id) {
        return daoService.getByPrimaryKey(OpenSurvey.class, id);
    }

    @Override
    public List<OpenSurvey> findOpenSurveyAll() {
        return daoService.query(" from cn.springmvc.hibernate.model.OpenSurvey ");
    }
}
