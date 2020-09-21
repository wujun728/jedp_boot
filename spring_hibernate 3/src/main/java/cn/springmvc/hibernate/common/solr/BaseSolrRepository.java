package cn.springmvc.hibernate.common.solr;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vincent.wang
 *
 */
public interface BaseSolrRepository<T, PK extends Serializable> extends BaseRepository<T, PK> {

    public void batchSave(List<T> modelList);

    public void batchDelete(List<String> pkList);

    public T findOneByProperty(String columnName, String value);
}
