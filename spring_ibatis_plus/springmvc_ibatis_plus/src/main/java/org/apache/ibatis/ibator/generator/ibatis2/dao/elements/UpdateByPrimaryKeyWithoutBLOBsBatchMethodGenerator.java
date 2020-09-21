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

/**
 * 
 * @author Jeff Butler
 * 
 */
public class UpdateByPrimaryKeyWithoutBLOBsBatchMethodGenerator extends AbstractDAOElementGenerator
{
    private boolean generateForJava5;
    
    public UpdateByPrimaryKeyWithoutBLOBsBatchMethodGenerator(boolean generateForJava5)
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
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        StringBuilder sb = new StringBuilder();
        // begin batch ,add by kalin
        FullyQualifiedJavaType keyType = introspectedTable.getBaseRecordType();
        sb.setLength(0);
        sb.append(" sqlMapClientTemplate.execute(new SqlMapClientCallback(){ ").append(lineSeparator);
        sb.append("          @Override").append(lineSeparator);
        sb.append("          public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException{").append(lineSeparator);
        sb.append("          executor.startBatch();").append(lineSeparator);
        sb.append("          for (").append(keyType.getShortName()).append(" it").append(" : records){").append(lineSeparator);
        sb.append(String.format("             executor.update(\"%s.%s\",it);", table.getSqlMapNamespace(), XmlConstants.UPDATE_BY_PRIMARY_KEY_STATEMENT_ID)).append(lineSeparator);
        sb.append("            }").append(lineSeparator);
        sb.append("          executor.executeBatch(); ").append(lineSeparator);
        sb.append("          return null; ").append(lineSeparator);
        sb.append("          } ").append(lineSeparator);
        sb.append("         });");
        method.addBodyLine(sb.toString());
        // end batch
        if (ibatorContext.getPlugins().daoUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
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
        if (ibatorContext.getPlugins().daoUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
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
        method.setName(getDAOMethodNameCalculator().getUpdateByPrimaryKeyWithoutBLOBsMethodName(introspectedTable) + "List");
        StringBuilder sb = new StringBuilder();
        sb.append("").append("").append(lineSeparator);
        method.addJunitStart();
        method.addBodyLine(sb.toString());
        method.addJunitEnd();
        junitClass.addMethod(method);
    }
    
    private Method getMethodShell(Set<FullyQualifiedJavaType> importedTypes)
    {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        // method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator().getUpdateByPrimaryKeyWithoutBLOBsMethodName(introspectedTable));
        // 入参List
        FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
        // 处理泛型
        if (generateForJava5)
        {
            FullyQualifiedJavaType type = introspectedTable.getBaseRecordType();
            importedTypes.add(type);
            parameterType.addFinalTypeArgument(type);
        }
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "records")); //$NON-NLS-1$
        for (FullyQualifiedJavaType fqjt : daoTemplate.getCheckedExceptions())
        {
            method.addException(fqjt);
            importedTypes.add(fqjt);
        }
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        ibatorContext.getCommentGenerator().addGeneralMethodComment(method, table);
        return method;
    }
}
