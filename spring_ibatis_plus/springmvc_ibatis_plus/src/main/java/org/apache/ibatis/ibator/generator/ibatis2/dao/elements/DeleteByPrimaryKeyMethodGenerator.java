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
import org.apache.ibatis.ibator.api.IntrospectedColumn;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.api.dom.java.Interface;
import org.apache.ibatis.ibator.api.dom.java.JavaVisibility;
import org.apache.ibatis.ibator.api.dom.java.JunitTestClass;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.Parameter;
import org.apache.ibatis.ibator.api.dom.java.TopLevelClass;
import org.apache.ibatis.ibator.generator.ibatis2.XmlConstants;
import org.apache.ibatis.ibator.internal.util.JavaBeansUtil;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class DeleteByPrimaryKeyMethodGenerator extends AbstractDAOElementGenerator
{
    public DeleteByPrimaryKeyMethodGenerator()
    {
        super();
    }
    
    @Override
    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);
        StringBuilder sb = new StringBuilder();
        if (!introspectedTable.getRules().generatePrimaryKeyClass())
        {
            // no primary key class, but primary key is enabled. Primary
            // key columns must be in the base class.
            FullyQualifiedJavaType keyType = introspectedTable.getBaseRecordType();
            topLevelClass.addImportedType(keyType);
            sb.setLength(0);
            sb.append(keyType.getShortName());
            sb.append(" key = new "); //$NON-NLS-1$
            sb.append(keyType.getShortName());
            sb.append("();"); //$NON-NLS-1$
            method.addBodyLine(sb.toString());
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns())
            {
                sb.setLength(0);
                sb.append("key."); //$NON-NLS-1$
                sb.append(JavaBeansUtil.getSetterMethodName(introspectedColumn.getJavaProperty()));
                sb.append('(');
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(");"); //$NON-NLS-1$
                method.addBodyLine(sb.toString());
            }
        }
        sb.setLength(0);
        sb.append("int rows = "); //$NON-NLS-1$
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        sb.append(daoTemplate.getDeleteMethod(table.getSqlMapNamespace(), XmlConstants.DELETE_BY_PRIMARY_KEY_STATEMENT_ID, "key")); //$NON-NLS-1$
        method.addBodyLine(sb.toString());
        method.addBodyLine("return rows;"); //$NON-NLS-1$
        if (ibatorContext.getPlugins().daoDeleteByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable))
        {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }
    
    @Override
    public void addInterfaceElements(Interface interfaze)
    {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);
        if (ibatorContext.getPlugins().daoDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
    
    @Override
    public void addJunitElements(JunitTestClass junitClass)
    {
        Method method = new Method();
        method.addAnnotation("@Test");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(getDAOMethodNameCalculator().getDeleteByPrimaryKeyMethodName(introspectedTable));
        method.addJunitStart();
        String daoName = junitClass.getType().getShortName().replace("Test", "");
        String beanId = daoName.substring(0, 1).toLowerCase() + daoName.substring(1);
        StringBuilder sb = new StringBuilder();
        // 主键为对象,也即表为unionkey
        if (introspectedTable.getRules().generatePrimaryKeyClass())
        {
            FullyQualifiedJavaType type = introspectedTable.getPrimaryKeyType();
            junitClass.addImportedType(type);
            method.addBodyLine(String.format("%s key = new %s();", type.getShortName(), type.getShortName()));
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns())
            {
                sb.setLength(0);
                sb.append("key."); //$NON-NLS-1$
                sb.append(JavaBeansUtil.getSetterMethodName(introspectedColumn.getJavaProperty()));
                sb.append(String.format("(new %s(\"1\"));", introspectedColumn.getFullyQualifiedJavaType().getShortName()));
                method.addBodyLine(sb.toString());
            }
            // dao 有可能无 SelectByPrimaryKey 方法
            if (introspectedTable.getRules().generateSelectByPrimaryKey())
            {
                method.addBodyLine(String.format("if(%s.selectByPrimaryKey(key)==null){", beanId));
                method.addBodyLine(String.format("Assert.assertEquals(0,  %s.deleteByPrimaryKey(key));", beanId));
                method.addBodyLine("}else {");
                method.addBodyLine(String.format("Assert.assertEquals(1,  %s.deleteByPrimaryKey(key));", beanId));
                method.addBodyLine("}");
            }
            else
            {
                method.addBodyLine(String.format("Assert.assertEquals(0,  %s.deleteByPrimaryKey(key));", beanId));
            }
        }
        else
        {
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns())
            {
                String keyName = introspectedColumn.getJavaProperty();
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                method.addBodyLine(String.format("%s %s = new %s(\"0\");", type.getShortName(), keyName, type.getShortName()));
                method.addBodyLine(String.format("if(%s.selectByPrimaryKey(%s)==null){", beanId, keyName));
                method.addBodyLine(String.format("Assert.assertEquals(0,  %s.deleteByPrimaryKey(%s));", beanId, keyName));
                method.addBodyLine("}else {");
                method.addBodyLine(String.format("Assert.assertEquals(1,  %s.deleteByPrimaryKey(%s));", beanId, keyName));
                method.addBodyLine("}");
            }
        }
        method.addBodyLine("result = true;");
        method.addJunitEnd();
        junitClass.addMethod(method);
    }
    
    private Method getMethodShell(Set<FullyQualifiedJavaType> importedTypes)
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator().getDeleteByPrimaryKeyMethodName(introspectedTable));
        if (introspectedTable.getRules().generatePrimaryKeyClass())
        {
            FullyQualifiedJavaType type = introspectedTable.getPrimaryKeyType();
            importedTypes.add(type);
            method.addParameter(new Parameter(type, "key")); //$NON-NLS-1$
        }
        else
        {
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns())
            {
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(type);
                method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty()));
            }
        }
        for (FullyQualifiedJavaType fqjt : daoTemplate.getCheckedExceptions())
        {
            method.addException(fqjt);
            importedTypes.add(fqjt);
        }
        ibatorContext.getCommentGenerator().addGeneralMethodComment(method, table);
        return method;
    }
}
