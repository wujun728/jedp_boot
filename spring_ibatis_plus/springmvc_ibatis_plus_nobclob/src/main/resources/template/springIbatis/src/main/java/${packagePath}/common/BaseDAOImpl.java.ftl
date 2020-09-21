package ${packageName}.common;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * BaseDAO 实现类
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository
@SuppressWarnings({"deprecation", "rawtypes"})
public abstract class BaseDAOImpl<T, E> implements BaseDAO<T, E>
{
    
    @Autowired
    private SqlMapClientTemplate sqlMapClientTemplate;
    
    String entitySimpleClassName; // 实体类名
    
    protected BaseDAOImpl()
    {
        ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
        entitySimpleClassName = ((Class)pt.getActualTypeArguments()[0]).getSimpleName();
    }
    
    public void insert(T record)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".insert").toString();
        sqlMapClientTemplate.insert(statementName, record);
    }
    
    public void insert(final List<T> list)
    {
        final String statementName = new StringBuilder(entitySimpleClassName).append(".insert").toString();
        sqlMapClientTemplate.execute(new SqlMapClientCallback<Object>()
        {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor)
                throws SQLException
            {
                executor.startBatch();
                for (T t : list)
                {
                    executor.insert(statementName, t);
                }
                executor.executeBatch();
                return null;
            }
            
        });
    }
    
    public void insertSelective(T record)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".insertSelective").toString();
        sqlMapClientTemplate.insert(statementName, record);
    }
    
    public void insertSelective(final List<T> list)
    {
        final String statementName = new StringBuilder(entitySimpleClassName).append(".insertSelective").toString();
        sqlMapClientTemplate.execute(new SqlMapClientCallback<Object>()
        {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor)
                throws SQLException
            {
                executor.startBatch();
                for (T t : list)
                {
                    executor.insert(statementName, t);
                }
                executor.executeBatch();
                return null;
            }
            
        });
    }
    
    public int deleteByPrimaryKey(Long id)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".deleteByPrimaryKey").toString();
        int rows = sqlMapClientTemplate.delete(statementName, id);
        return rows;
    }
    
    public int deleteByExample(E example)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".deleteByExample").toString();
        int rows = sqlMapClientTemplate.delete(statementName, example);
        return rows;
    }
    
    public void deleteByExample(final List<E> list)
    {
        final String statementName = new StringBuilder(entitySimpleClassName).append(".deleteByExample").toString();
        sqlMapClientTemplate.execute(new SqlMapClientCallback<Object>()
        {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor)
                throws SQLException
            {
                executor.startBatch();
                for (E e : list)
                {
                    executor.delete(statementName, e);
                }
                executor.executeBatch();
                return null;
            }
        });
    }
    
    public int updateByPrimaryKey(T record)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".updateByPrimaryKey").toString();
        int rows = sqlMapClientTemplate.update(statementName, record);
        return rows;
    }
    
    public void updateByPrimaryKey(final List<T> list)
    {
        final String statementName = new StringBuilder(entitySimpleClassName).append(".updateByPrimaryKey").toString();
        sqlMapClientTemplate.execute(new SqlMapClientCallback<Object>()
        {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor)
                throws SQLException
            {
                executor.startBatch();
                for (T t : list)
                {
                    executor.update(statementName, t);
                }
                executor.executeBatch();
                return null;
            }
            
        });
    }
    
    public int updateByPrimaryKeySelective(T record)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".updateByPrimaryKeySelective").toString();
        int rows = sqlMapClientTemplate.update(statementName, record);
        return rows;
    }
    
    public void updateByPrimaryKeySelective(final List<T> list)
    {
        final String statementName = new StringBuilder(entitySimpleClassName).append(".updateByPrimaryKeySelective").toString();
        sqlMapClientTemplate.execute(new SqlMapClientCallback<Object>()
        {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor)
                throws SQLException
            {
                executor.startBatch();
                for (T t : list)
                {
                    executor.update(statementName, t);
                }
                executor.executeBatch();
                return null;
            }
            
        });
    }
    
    public int countByExample(E example)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".countByExample").toString();
        Integer totalCount = (Integer)sqlMapClientTemplate.queryForObject(statementName, example);
        return totalCount.intValue();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> selectByExample(E example)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".selectByExample").toString();
        return sqlMapClientTemplate.queryForList(statementName, example);
    }
    
    @SuppressWarnings("unchecked")
    public T selectByPrimaryKey(Long id)
    {
        String statementName = new StringBuilder(entitySimpleClassName).append(".selectByPrimaryKey").toString();
        return (T)sqlMapClientTemplate.queryForObject(statementName, id);
    }
    
    /**
     * 查询全部
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<T> selectAll()
    {
        return selectByExample(null);
    }
    
    @SuppressWarnings("unchecked")
    public PaginationSupport queryPagingEntities(E example, int pageNo, int pageSize)
    {
        pageNo = Math.max(1, pageNo);
        pageSize = Math.max(5, pageSize);
        int totalcount = countByExample(example);
        
        // 构造返回对象
        PaginationSupport entites = new PaginationSupport(totalcount, pageNo, pageSize);
        String statementName = new StringBuilder(entitySimpleClassName).append(".selectByExample").toString();
        
        // 设置返回数据
        List<T> entityList = sqlMapClientTemplate.queryForList(statementName, example, pageSize * (pageNo - 1), pageSize);
        entites.setItems(entityList);
        return entites;
    }
    
    /**
     * 为子类方法 updateByExample 、updateByExampleSelective 封装的底层 update 方法 <br>
     * 方便在此处扩展功能，如：添加缓存支持
     * 
     * @param statementName
     * @param parameterObject
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected int update(String statementName, Object parameterObject)
    {
        // 此处可添加扩展功能
        return sqlMapClientTemplate.update(statementName, parameterObject);
    }
}
