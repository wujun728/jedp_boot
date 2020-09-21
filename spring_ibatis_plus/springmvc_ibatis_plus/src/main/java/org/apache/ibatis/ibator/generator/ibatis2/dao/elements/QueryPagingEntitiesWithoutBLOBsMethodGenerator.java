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
package org.apache.ibatis.ibator.generator.ibatis2.dao.elements;

import java.util.Set;
import java.util.TreeSet;

import org.apache.ibatis.ibator.api.FullyQualifiedTable;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.api.dom.java.Interface;
import org.apache.ibatis.ibator.api.dom.java.JavaVisibility;
import org.apache.ibatis.ibator.api.dom.java.JunitTestClass;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.Parameter;
import org.apache.ibatis.ibator.api.dom.java.TopLevelClass;
import org.apache.ibatis.ibator.generator.ibatis2.XmlConstants;
import org.apache.ibatis.ibator.internal.rules.IbatorRules;
import org.apache.ibatis.ibator.internal.util.messages.Messages;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class QueryPagingEntitiesWithoutBLOBsMethodGenerator extends AbstractDAOElementGenerator
{
    private boolean generateForJava5;
    
    public QueryPagingEntitiesWithoutBLOBsMethodGenerator(boolean generateForJava5)
    {
        super();
        this.generateForJava5 = generateForJava5;
    }
    
    @Override
    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        if (generateForJava5)
        {
            method.addSuppressTypeWarningsAnnotation();
        }
        method.addBodyLine("pageNo = Math.max(1, pageNo);");
        method.addBodyLine("pageSize = Math.max(2, pageSize);");
        method.addBodyLine("int totalcount = countByExample(example);");
        method.addBodyLine("PaginationSupport entites = new PaginationSupport(totalcount, pageNo, pageSize);");
        method.addBodyLine(String.format("String statementName =\"%s.%s\";", table.getSqlMapNamespace(), XmlConstants.SELECT_BY_EXAMPLE_STATEMENT_ID));
        if (generateForJava5)
        {
            FullyQualifiedJavaType fqjt;
            if (introspectedTable.getRules().generateBaseRecordClass())
            {
                fqjt = introspectedTable.getBaseRecordType();
            }
            else if (introspectedTable.getRules().generatePrimaryKeyClass())
            {
                fqjt = introspectedTable.getPrimaryKeyType();
            }
            else
            {
                throw new RuntimeException(Messages.getString("RuntimeError.12")); //$NON-NLS-1$
            }
            method.addBodyLine(String.format("List<%s> entityList = ", fqjt.getShortName()));
        }
        else
        {
            method.addBodyLine("List entityList = ");
        }
        method.addBodyLine("    sqlMapClientTemplate.queryForList(statementName, example, pageSize * (pageNo - 1), pageSize);");
        method.addBodyLine("entites.setItems(entityList);");
        method.addBodyLine("return entites;");
        if (ibatorContext.getPlugins().daoSelectByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
        {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }
    
    @Override
    public void addInterfaceElements(Interface interfaze)
    {
        if (getExampleMethodVisibility() == JavaVisibility.PUBLIC)
        {
            Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
            Method method = getMethodShell(importedTypes);
            if (ibatorContext.getPlugins().daoSelectByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }
    }
    
    @Override
    public void addJunitElements(JunitTestClass junitClass)
    {
        Method method = new Method();
        method.addAnnotation("@Test");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("queryPagingEntitiesWithoutBLOBs");
        StringBuilder sb = new StringBuilder();
        sb.append("").append("").append(lineSeparator);
        method.addJunitStart();
        method.addBodyLine(sb.toString());
        method.addJunitEnd();
        junitClass.addMethod(method);
    }
    
    private Method getMethodShell(Set<FullyQualifiedJavaType> importedTypes)
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        FullyQualifiedJavaType type = introspectedTable.getExampleType();
        importedTypes.add(type);
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        Method method = new Method();
        method.setVisibility(getExampleMethodVisibility());
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("PaginationSupport");
        method.setReturnType(returnType);
        if (ibatorContext.getSuppressTypeWarnings(introspectedTable))
        {
            method.addSuppressTypeWarningsAnnotation();
        }
        IbatorRules rules = introspectedTable.getRules();
        if (rules.generateSelectByExampleWithBLOBs())
        {
            method.setName("queryPagingEntitiesWithoutBLOBs");
        }
        else
        {
            method.setName("queryPagingEntities");
        }
        method.addParameter(new Parameter(type, "example")); //$NON-NLS-1$
        type = new FullyQualifiedJavaType("int");
        method.addParameter(new Parameter(type, "pageNo"));
        method.addParameter(new Parameter(type, "pageSize"));
        for (FullyQualifiedJavaType fqjt : daoTemplate.getCheckedExceptions())
        {
            method.addException(fqjt);
            importedTypes.add(fqjt);
        }
        ibatorContext.getCommentGenerator().addGeneralMethodComment(method, table);
        return method;
    }
}
