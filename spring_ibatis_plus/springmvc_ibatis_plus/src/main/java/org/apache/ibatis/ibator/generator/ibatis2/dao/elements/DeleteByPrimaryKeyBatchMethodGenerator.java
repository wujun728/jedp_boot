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
public class DeleteByPrimaryKeyBatchMethodGenerator extends AbstractDAOElementGenerator
{
    private boolean generateForJava5;
    
    public DeleteByPrimaryKeyBatchMethodGenerator(boolean generateForJava5)
    {
        super();
        this.generateForJava5 = generateForJava5;
    }
    
    @Override
    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);
        // @SuppressWarnings("unchecked") by kalin
        method.addSuppressTypeWarningsAnnotation();
        StringBuilder sb = new StringBuilder();
        // begin batch ,add by kalin
        sb.setLength(0);
        sb.append(" sqlMapClientTemplate.execute(new SqlMapClientCallback(){ ").append(lineSeparator);
        sb.append("          @Override").append(lineSeparator);
        sb.append("          public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException{").append(lineSeparator);
        sb.append("          executor.startBatch();").append(lineSeparator);
        FullyQualifiedJavaType keyType;
        if (introspectedTable.getRules().generatePrimaryKeyClass())
        {
            keyType = introspectedTable.getPrimaryKeyType();
            sb.append("          for (").append(keyType.getShortName()).append(" it: keys){").append(lineSeparator);
        }
        else
        {
            keyType = introspectedTable.getBaseRecordType();
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns())
            {
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                sb.append("          for (").append(type.getShortName()).append(" ").append(introspectedColumn.getJavaProperty()).append(" : " + introspectedColumn.getJavaProperty() + "s){").append(
                    lineSeparator);
                sb.append("             ").append(keyType.getShortName()).append(" it = new ").append(keyType.getShortName()).append("();").append(lineSeparator);
                sb.append("             it.")
                    .append(JavaBeansUtil.getSetterMethodName(introspectedColumn.getJavaProperty()))
                    .append('(')
                    .append(introspectedColumn.getJavaProperty())
                    .append(");")
                    .append(lineSeparator);
            }
        }
        topLevelClass.addImportedType(keyType);
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        sb.append(String.format("             executor.delete(\"%s.%s\",it);", table.getSqlMapNamespace(), XmlConstants.DELETE_BY_PRIMARY_KEY_STATEMENT_ID)).append(lineSeparator);
        sb.append("            }").append(lineSeparator);
        sb.append("          executor.executeBatch(); ").append(lineSeparator);
        sb.append("          return null; ").append(lineSeparator);
        sb.append("          } ").append(lineSeparator);
        sb.append("         });");
        method.addBodyLine(sb.toString());
        // end batch
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
        method.setName("deleteByPrimaryKeys");
        method.addJunitStart();
        String daoName = junitClass.getType().getShortName().replace("Test", "");
        String beanId = daoName.substring(0, 1).toLowerCase() + daoName.substring(1);
        // 处理泛型
        if (introspectedTable.getRules().generatePrimaryKeyClass())
        {
            FullyQualifiedJavaType exampleType = introspectedTable.getExampleType();
            StringBuilder sb = new StringBuilder();
            sb.append(exampleType.getShortName()).append(" example = new ").append(exampleType.getShortName()).append("();");
            method.addBodyLine(sb.toString());
            sb.setLength(0);
            sb.append("count = ").append(beanId).append(".countByExample(example);");
            method.addBodyLine(sb.toString());
            FullyQualifiedJavaType type = introspectedTable.getPrimaryKeyType();
            method.addBodyLine(String.format("List<%s> keys = new ArrayList<%s>();", type.getShortName(), type.getShortName()));
            method.addBodyLine(String.format("%s.deleteByPrimaryKey(keys);", beanId));
            sb.setLength(0);
            sb.append("int count1 = ").append(beanId).append(".countByExample(example);");
            method.addBodyLine(sb.toString());
            method.addBodyLine("Assert.assertEquals(count,  count1);");
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
        // method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator().getDeleteByPrimaryKeyMethodName(introspectedTable));
        if (introspectedTable.getRules().generatePrimaryKeyClass())
        {
            // 入参List
            FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
            // 处理泛型
            if (generateForJava5)
            {
                FullyQualifiedJavaType type = introspectedTable.getPrimaryKeyType();
                importedTypes.add(type);
                parameterType.addFinalTypeArgument(type);
            }
            importedTypes.add(parameterType);
            method.addParameter(new Parameter(parameterType, "keys")); //$NON-NLS-1$
        }
        else
        {
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns())
            {
                // 入参List
                FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
                // 处理泛型
                if (generateForJava5)
                {
                    FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                    importedTypes.add(type);
                    parameterType.addFinalTypeArgument(type);
                }
                importedTypes.add(parameterType);
                method.addParameter(new Parameter(parameterType, introspectedColumn.getJavaProperty() + "s"));
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
