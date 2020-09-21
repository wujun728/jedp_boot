package ${packageName}.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.common.PaginationSupport;
import ${packageName}.dao.${className}DAO;
import ${packageName}.model.${className};
import ${packageName}.model.${className}Example;
import ${packageName}.service.${className}Service;

/**
 * 
 * ${className}Service接口实现类
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@Transactional
public class ${className}ServiceImpl implements ${className}Service
{
    @Autowired
    ${className}DAO ${instanceName}DAO;
    
    @Override
    public void insert(${className} ${instanceName})
    {
        ${instanceName}DAO.insert(${instanceName});
    }
    
    @Override
    public void deleteById(${pk.javaType} id)
    {
        ${instanceName}DAO.deleteByPrimaryKey(id);
    }
    
    @Override
    public void updateById(${className} ${instanceName})
    {
        ${instanceName}DAO.updateByPrimaryKey(${instanceName});
    }
    
    @Override
    public void saveOrUpdate(${className} ${instanceName})
    {
        <#if pk.javaType!='String'>if (${instanceName}.${pk.getMethod}() == null)
        {
            insert(${instanceName});
        }</#if><#if pk.javaType='String'>if (StringUtils.isBlank(${instanceName}.${pk.getMethod}()))
        {
        	${instanceName}.${pk.setMethod}(UUID.randomUUID().toString());
            insert(${instanceName});
        }</#if>
        else
        {
            updateById(${instanceName});
        }
    }
    
    @Override
    public ${className} queryById(${pk.javaType} id)
    {
        return ${instanceName}DAO.selectByPrimaryKey(id);
    }
    
    @Override
    public List<${className}> queryAll()
    {
        return ${instanceName}DAO.selectAll();
    }
    
    @Override
    public PaginationSupport<${className}> queryForPagination(${className} criteria, int pageNo, int pageSize)
    {
        return ${instanceName}DAO.queryPagingEntities(new ${className}Example(), pageNo, pageSize);
    }
}
