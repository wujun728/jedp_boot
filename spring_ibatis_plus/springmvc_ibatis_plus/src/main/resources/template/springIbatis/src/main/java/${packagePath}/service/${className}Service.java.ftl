package ${packageName}.service;

import java.util.List;

import ${packageName}.common.PaginationSupport;
import ${packageName}.model.${className};

/**
 * 
 * ${className}Service接口
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ${className}Service
{
    /**
     * 增加
     * 
     * @param ${instanceName}
     * @see [类、类#方法、类#成员]
     */
    void insert(${className} ${instanceName});
    
    /**
     * 根据id删除
     * 
     * @param id
     * @see [类、类#方法、类#成员]
     */
    void deleteById(${pk.javaType} id);
    
    /**
     * 根据id更新
     * 
     * @param ${instanceName}
     * @see [类、类#方法、类#成员]
     */
    void updateById(${className} ${instanceName});
    
    /**
     * 新增或更新
     * 
     * @param ${instanceName}
     * @see [类、类#方法、类#成员]
     */
    void saveOrUpdate(${className} ${instanceName});
    
    /**
     * 查询全部
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<${className}> queryAll();
    
    /**
     * 根据id查询
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    ${className} queryById(${pk.javaType} id);
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     */
    PaginationSupport<${className}> queryForPagination(${className} criteria, int pageNo, int pageSize);
    
}