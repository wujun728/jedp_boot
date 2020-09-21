package ${packageName}.common;

import java.util.List;

/**
 * BaseDAO 接口
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface BaseDAO<T, E>
{
    /**
     * 插入记录,全部字段
     * 
     * @param record
     * @see [类、类#方法、类#成员]
     */
    void insert(T record);
    
    /**
     * 批量插入记录,全部字段
     * 
     * @param list
     * @see [类、类#方法、类#成员]
     */
    
    void insert(List<T> list);
    
    /**
     * 插入记录，非全部字段
     * 
     * @param record
     * @see [类、类#方法、类#成员]
     */
    void insertSelective(T record);
    
    /**
     * 批量插入记录，非全部字段
     * 
     * @param list
     * @see [类、类#方法、类#成员]
     */
    void insertSelective(List<T> list);
    
    /**
     * 根据条件删除
     * 
     * @param example
     * @return
     * @see [类、类#方法、类#成员]
     */
    int deleteByExample(E example);
    
    /**
     * 根据条件批量删除
     * 
     * @param example
     * @return
     * @see [类、类#方法、类#成员]
     */
    void deleteByExample(List<E> list);
    
    /**
     * 根据主键删除
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    int deleteByPrimaryKey(Long id);
    
    /**
     * 根据主键更新,全部字段
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateByPrimaryKey(T record);
    
    /**
     * 根据主键批量更新,全部字段
     * 
     * @param list
     * @return
     * @see [类、类#方法、类#成员]
     */
    void updateByPrimaryKey(List<T> list);
    
    /**
     * 根据主键更新，非全部字段
     * 
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateByPrimaryKeySelective(T record);
    
    /**
     * 根据主键批量更新，非全部字段
     * 
     * @param list
     * @return
     * @see [类、类#方法、类#成员]
     */
    void updateByPrimaryKeySelective(List<T> list);
    
    /**
     * 根据条件更新，非全部字段
     * 
     * @param record
     * @param example
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateByExampleSelective(T record, E example);
    
    /**
     * 根据条件更新,全部字段
     * 
     * @param record
     * @param example
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateByExample(T record, E example);
    
    /**
     * 根据条件获取记录总数
     * 
     * @param example
     * @return
     * @see [类、类#方法、类#成员]
     */
    int countByExample(E example);
    
    /**
     * 根据条件查询
     * 
     * @param example
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<T> selectByExample(E example);
    
    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    T selectByPrimaryKey(Long id);
    
     /**
     * 查询全部
     *  
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<T> selectAll();
    
    /**
     * 根据条件分页查询
     * 
     * @param example
     * @param pageNo 默认最小值1
     * @param pageSize 默认最小值5
     * @return
     * @see [类、类#方法、类#成员]
     */
    PaginationSupport queryPagingEntities(E example, int pageNo, int pageSize);
    
}
