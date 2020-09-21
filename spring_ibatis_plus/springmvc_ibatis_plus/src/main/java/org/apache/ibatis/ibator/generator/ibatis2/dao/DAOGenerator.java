/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.ibatis.ibator.generator.ibatis2.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.ibator.api.CommentGenerator;
import org.apache.ibatis.ibator.api.FullyQualifiedTable;
import org.apache.ibatis.ibator.api.dom.java.CompilationUnit;
import org.apache.ibatis.ibator.api.dom.java.Field;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.api.dom.java.Interface;
import org.apache.ibatis.ibator.api.dom.java.JavaVisibility;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.TopLevelClass;
import org.apache.ibatis.ibator.config.PropertyRegistry;
import org.apache.ibatis.ibator.generator.AbstractJavaGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.AbstractDAOElementGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.CountByExampleMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.DeleteAllMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.DeleteByExampleMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.DeleteByPrimaryKeyBatchMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.DeleteByPrimaryKeyMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.InsertBatchMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.InsertMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.InsertSelectiveBatchMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.InsertSelectiveMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.QueryPagingEntitiesWithBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.QueryPagingEntitiesWithoutBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.SelectAllWithBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.SelectAllWithoutBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.SelectByPrimaryKeyMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByExampleParmsInnerclassGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByExampleSelectiveMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByPrimaryKeySelectiveBatchMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByPrimaryKeyWithBLOBsBatchMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByPrimaryKeyWithoutBLOBsBatchMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.apache.ibatis.ibator.generator.ibatis2.dao.templates.AbstractDAOTemplate;
import org.apache.ibatis.ibator.internal.rules.IbatorRules;
import org.apache.ibatis.ibator.internal.util.StringUtility;
import org.apache.ibatis.ibator.internal.util.messages.Messages;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class DAOGenerator extends AbstractJavaGenerator
{
    private AbstractDAOTemplate daoTemplate;
    
    private boolean generateForJava5;
    
    public DAOGenerator(AbstractDAOTemplate daoTemplate, boolean generateForJava5)
    {
        super();
        this.daoTemplate = daoTemplate;
        this.generateForJava5 = generateForJava5;
    }
    
    @Override
    public List<CompilationUnit> getCompilationUnits()
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(Messages.getString("Progress.14", table.toString())); //$NON-NLS-1$
        TopLevelClass topLevelClass = getTopLevelClassShell();
        Interface interfaze = getInterfaceShell();
        addCountByExampleMethod(topLevelClass, interfaze);
        addDeleteAllMethod(topLevelClass, interfaze); // add by kalin
        addDeleteByExampleMethod(topLevelClass, interfaze);
        addDeleteByPrimaryKeyMethod(topLevelClass, interfaze);
        addDeleteByPrimaryKeyBatchMethod(topLevelClass, interfaze);// add by kalin
        addInsertMethod(topLevelClass, interfaze);
        addInsertBatchMethod(topLevelClass, interfaze);// add by kalin
        addInsertSelectiveMethod(topLevelClass, interfaze);
        addInsertSelectiveBatchMethod(topLevelClass, interfaze);// add by kalin
        addSelectAllWithBLOBsMethod(topLevelClass, interfaze); // add by kalin
        addSelectAllWithoutBLOBsMethod(topLevelClass, interfaze); // add by kalin
        addSelectByExampleWithBLOBsMethod(topLevelClass, interfaze);
        addSelectByExampleWithoutBLOBsMethod(topLevelClass, interfaze);
        addSelectByPrimaryKeyMethod(topLevelClass, interfaze);
        addUpdateByExampleParmsInnerclass(topLevelClass, interfaze);
        addUpdateByExampleSelectiveMethod(topLevelClass, interfaze);
        addUpdateByExampleWithBLOBsMethod(topLevelClass, interfaze);
        addUpdateByExampleWithoutBLOBsMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeySelectiveMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeySelectiveBatchMethod(topLevelClass, interfaze);// add by kalin
        addUpdateByPrimaryKeyWithBLOBsMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeyWithBLOBsBatchMethod(topLevelClass, interfaze);// add by kalin
        addUpdateByPrimaryKeyWithoutBLOBsMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeyWithoutBLOBsBatchMethod(topLevelClass, interfaze);// add by kalin
        // 分页方法 add by kalin
        addQueryPagingEntitiesWithBLOBsMethod(topLevelClass, interfaze);
        addQueryPagingEntitiesWithoutBLOBsMethod(topLevelClass, interfaze);
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (ibatorContext.getPlugins().daoImplementationGenerated(topLevelClass, introspectedTable))
        {
            answer.add(topLevelClass);
        }
        if (ibatorContext.getPlugins().daoInterfaceGenerated(interfaze, introspectedTable))
        {
            answer.add(interfaze);
        }
        return answer;
    }
    
    protected TopLevelClass getTopLevelClassShell()
    {
        FullyQualifiedJavaType interfaceType = introspectedTable.getDAOInterfaceType();
        FullyQualifiedJavaType implementationType = introspectedTable.getDAOImplementationType();
        CommentGenerator commentGenerator = ibatorContext.getCommentGenerator();
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        TopLevelClass answer = new TopLevelClass(implementationType);
        // add by 00fly
        answer.addAnnotation("@Repository");// 增加注解
        Field sqlmapclient = new Field();
        sqlmapclient.setType(new FullyQualifiedJavaType("org.springframework.orm.ibatis.SqlMapClientTemplate"));
        sqlmapclient.setName("sqlMapClientTemplate");
        sqlmapclient.addAnnotation("@Autowired");
        sqlmapclient.setVisibility(JavaVisibility.DEFAULT);
        answer.addField(sqlmapclient);
        answer.setVisibility(JavaVisibility.PUBLIC);
        answer.setSuperClass(daoTemplate.getSuperClass());
        answer.addImportedType(daoTemplate.getSuperClass());
        answer.addSuperInterface(interfaceType);
        answer.addImportedType(interfaceType);
        for (FullyQualifiedJavaType fqjt : daoTemplate.getImplementationImports())
        {
            answer.addImportedType(fqjt);
        }
        commentGenerator.addJavaFileComment(answer);
        // add constructor from the template
        answer.addMethod(daoTemplate.getConstructorClone(commentGenerator, implementationType, table));
        // add any fields from the template
        for (Field field : daoTemplate.getFieldClones(commentGenerator, table))
        {
            answer.addField(field);
        }
        // add any methods from the template
        for (Method method : daoTemplate.getMethodClones(commentGenerator, table))
        {
            answer.addMethod(method);
        }
        return answer;
    }
    
    protected Interface getInterfaceShell()
    {
        Interface answer = new Interface(introspectedTable.getDAOInterfaceType());
        answer.setVisibility(JavaVisibility.PUBLIC);
        String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (rootInterface == null)
        {
            rootInterface = ibatorContext.getDaoGeneratorConfiguration().getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }
        if (StringUtility.stringHasValue(rootInterface))
        {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
            answer.addSuperInterface(fqjt);
            answer.addImportedType(fqjt);
        }
        for (FullyQualifiedJavaType fqjt : daoTemplate.getInterfaceImports())
        {
            answer.addImportedType(fqjt);
        }
        ibatorContext.getCommentGenerator().addJavaFileComment(answer);
        return answer;
    }
    
    protected void addCountByExampleMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateCountByExample())
        {
            AbstractDAOElementGenerator methodGenerator = new CountByExampleMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addDeleteAllMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateDeleteByExample())
        {
            AbstractDAOElementGenerator methodGenerator = new DeleteAllMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addDeleteByExampleMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateDeleteByExample())
        {
            AbstractDAOElementGenerator methodGenerator = new DeleteByExampleMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addDeleteByPrimaryKeyMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey())
        {
            AbstractDAOElementGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addDeleteByPrimaryKeyBatchMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey())
        {
            AbstractDAOElementGenerator methodGenerator = new DeleteByPrimaryKeyBatchMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addInsertMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateInsert())
        {
            AbstractDAOElementGenerator methodGenerator = new InsertMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addInsertBatchMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateInsert())
        {
            AbstractDAOElementGenerator methodGenerator = new InsertBatchMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addInsertSelectiveMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateInsertSelective())
        {
            AbstractDAOElementGenerator methodGenerator = new InsertSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addInsertSelectiveBatchMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateInsertSelective())
        {
            AbstractDAOElementGenerator methodGenerator = new InsertSelectiveBatchMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addSelectAllWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByExampleWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectAllWithBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addSelectAllWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectAllWithoutBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addSelectByExampleWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByExampleWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addSelectByExampleWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectByExampleWithoutBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addSelectByPrimaryKeyMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByPrimaryKey())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByExampleParmsInnerclass(TopLevelClass topLevelClass, Interface interfaze)
    {
        IbatorRules ibatorRules = introspectedTable.getRules();
        if (ibatorRules.generateUpdateByExampleSelective() || ibatorRules.generateUpdateByExampleWithBLOBs() || ibatorRules.generateUpdateByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleParmsInnerclassGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByExampleSelectiveMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByExampleSelective())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByExampleWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByExampleWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByExampleWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByPrimaryKeySelectiveBatchMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeySelectiveBatchMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByPrimaryKeyWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByPrimaryKeyWithBLOBsBatchMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsBatchMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addUpdateByPrimaryKeyWithoutBLOBsBatchMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsBatchMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addQueryPagingEntitiesWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByExampleWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new QueryPagingEntitiesWithBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void addQueryPagingEntitiesWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new QueryPagingEntitiesWithoutBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }
    
    protected void initializeAndExecuteGenerator(AbstractDAOElementGenerator methodGenerator, TopLevelClass topLevelClass, Interface interfaze)
    {
        methodGenerator.setDAOTemplate(daoTemplate);
        methodGenerator.setIbatorContext(ibatorContext);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addImplementationElements(topLevelClass);
        methodGenerator.addInterfaceElements(interfaze);
        // 导入类 add by kalin
        FullyQualifiedJavaType sqlexception = new FullyQualifiedJavaType("java.sql.SQLException");
        FullyQualifiedJavaType sqlmapclientcallback = new FullyQualifiedJavaType("org.springframework.orm.ibatis.SqlMapClientCallback");
        String packName = introspectedTable.getJavaModelPackage();
        String topPack = packName.substring(0, packName.lastIndexOf("."));
        FullyQualifiedJavaType paginationsupport = new FullyQualifiedJavaType(topPack + ".common.PaginationSupport");
        FullyQualifiedJavaType sqlmapexecutor = new FullyQualifiedJavaType("com.ibatis.sqlmap.client.SqlMapExecutor");
        FullyQualifiedJavaType autowired = new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired");
        FullyQualifiedJavaType repository = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
        FullyQualifiedJavaType sqlmapclient = new FullyQualifiedJavaType("org.springframework.orm.ibatis.SqlMapClientTemplate");
        topLevelClass.addImportedType(sqlexception);
        topLevelClass.addImportedType(sqlmapclientcallback);
        topLevelClass.addImportedType(sqlmapexecutor);
        topLevelClass.addImportedType(paginationsupport);
        topLevelClass.addImportedType(autowired);
        topLevelClass.addImportedType(repository);
        topLevelClass.addImportedType(sqlmapclient);
        interfaze.addImportedType(paginationsupport);
    }
}
