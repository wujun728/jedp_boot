package cn.springmvc.hibernate.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.hibernate.common.solr.SolrException;
import cn.springmvc.hibernate.common.solr.opensurvey.OpenSurveyDocument;
import cn.springmvc.hibernate.common.solr.opensurvey.OpenSurveySolrRepository;
import cn.springmvc.hibernate.common.solr.opensurvey.OpenSurveySort;
import cn.springmvc.hibernate.common.utils.Pagination;
import cn.springmvc.hibernate.dao.OpenSurveyDao;
import cn.springmvc.hibernate.model.OpenSurvey;
import cn.springmvc.hibernate.service.OpenSurveyService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class OpenSurveyServiceImpl implements OpenSurveyService {

    private static final Logger log = LoggerFactory.getLogger(OpenSurveyServiceImpl.class);

    @Autowired
    private OpenSurveySolrRepository openSurveySolrRepository;

    @Autowired
    private OpenSurveyDao openSurveyDao;

    @Override
    public void openSurveySynchroAllToSolr() {
        List<OpenSurvey> surveys = openSurveyDao.findOpenSurveyAll();
        List<OpenSurveyDocument> docs = new ArrayList<OpenSurveyDocument>();

        Iterator<OpenSurvey> iterator = surveys.iterator();
        while (iterator.hasNext()) {
            docs.add(openSurveySolrRepository.getOpenSurveyDocument(iterator.next()));
        }

        // log.warn("## size={}", surveys.size());
        try {
            openSurveySolrRepository.batchSave(docs);
        } catch (SolrException e) {
            log.error("## OpenSurvey Synchro All error , error message={}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void saveOrUpdateOpenSurveyDocument(OpenSurvey openSurvey) {
        OpenSurveyDocument doc = openSurveySolrRepository.getOpenSurveyDocument(openSurvey);
        openSurveySolrRepository.saveOrUpdate(doc);
    }

    @Override
    public void clearOpenSurveyDocumentAll() {
        try {
            openSurveySolrRepository.clearAll();
        } catch (SolrException e) {
            log.error("## clear all opensurvey error , error message={}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public OpenSurvey findOpenSurveyById(String id) {
        return openSurveyDao.findOpenSurveyById(id);
    }

    @Override
    public Pagination<OpenSurveyDocument> pagedFindOpenSurveyDocumentListByProperty(int start, int pageSize, String keywords) {
        String filterStr = getQueryString(keywords);
        List<SortClause> sortClauses = new ArrayList<SortClause>();
        sortClauses.add(OpenSurveySort.createtime.desc.getSortClause());
        sortClauses.add(OpenSurveySort.samplesum.desc.getSortClause());
        return openSurveySolrRepository.pagedFindOpenSurveyDocumentListByProperty(start, pageSize, filterStr, sortClauses);
    }

    private String getQueryString(String searchText) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isBlank(searchText)) {
            stringBuffer.append("keyWords:*");
        } else {
            stringBuffer.append("keyWords:" + ClientUtils.escapeQueryChars(searchText));
        }
        return stringBuffer.toString();
    }
}
