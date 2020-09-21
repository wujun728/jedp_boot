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

package org.apache.ibatis.ibator.internal;

import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.ibator.api.CommentGenerator;
import org.apache.ibatis.ibator.api.FullyQualifiedTable;
import org.apache.ibatis.ibator.api.dom.java.CompilationUnit;
import org.apache.ibatis.ibator.api.dom.java.Field;
import org.apache.ibatis.ibator.api.dom.java.InnerClass;
import org.apache.ibatis.ibator.api.dom.java.InnerEnum;
import org.apache.ibatis.ibator.api.dom.java.JavaElement;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.Parameter;
import org.apache.ibatis.ibator.api.dom.xml.XmlElement;
import org.apache.ibatis.ibator.config.PropertyRegistry;
import org.apache.ibatis.ibator.internal.util.StringUtility;

/**
 * @author Jeff Butler
 * 
 */
public class DefaultCommentGenerator implements CommentGenerator
{
    
    private Properties properties;
    
    private boolean suppressDate;
    
    public DefaultCommentGenerator()
    {
        super();
        properties = new Properties();
        suppressDate = false;
    }
    
    public void addFieldComment(Field field, FullyQualifiedTable table, String columnName)
    {
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" *  database column "); //$NON-NLS-1$
        sb.append(table);
        sb.append('.');
        sb.append(columnName);
        field.addJavaDocLine(sb.toString());
        addIbatorJavadocTag(field);
        field.addJavaDocLine(" */"); //$NON-NLS-1$
    }
    
    public void addFieldComment(Field field, FullyQualifiedTable table)
    {
        addIbatorJavadocTag(field);
    }
    
    public void addClassComment(InnerClass innerClass, FullyQualifiedTable table)
    {
        addIbatorJavadocTag(innerClass);
    }
    
    public void addEnumComment(InnerEnum innerEnum, FullyQualifiedTable table)
    {
        addIbatorJavadocTag(innerEnum);
    }
    
    public void addGetterComment(Method method, FullyQualifiedTable table, String columnName)
    {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.setLength(0);
        sb.append(" * @return the value of "); //$NON-NLS-1$
        sb.append(table);
        sb.append('.');
        sb.append(columnName);
        method.addJavaDocLine(sb.toString());
        addIbatorJavadocTag(method);
        method.addJavaDocLine(" */"); //$NON-NLS-1$
    }
    
    public void addSetterComment(Method method, FullyQualifiedTable table, String columnName)
    {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param "); //$NON-NLS-1$
        sb.append(parm.getName());
        sb.append(" the value for "); //$NON-NLS-1$
        sb.append(table);
        sb.append('.');
        sb.append(columnName);
        method.addJavaDocLine(sb.toString());
        addIbatorJavadocTag(method);
        method.addJavaDocLine(" */"); //$NON-NLS-1$
    }
    
    public void addGeneralMethodComment(Method method, FullyQualifiedTable table)
    {
        method.addJavaDocLine("");
        addIbatorJavadocTag(method);
    }
    
    public void addJavaFileComment(CompilationUnit compilationUnit)
    {
    }
    
    /**
     * Adds a suitable comment to warn users that the element was generated, and when it was generated.
     */
    public void addComment(XmlElement xmlElement)
    {
    }
    
    public void addRootComment(XmlElement rootElement)
    {
    }
    
    public void addConfigurationProperties(Properties properties)
    {
        this.properties.putAll(properties);
        suppressDate = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
    }
    
    /**
     * This method adds the custom javadoc tag for ibator. You may do nothing if you do not wish to include the Javadoc
     * tag - however, if you do not include the Javadoc tag then the Java merge capability of the eclipse plugin will
     * break.
     * 
     * @param javaElement the java element
     */
    protected void addIbatorJavadocTag(JavaElement javaElement)
    {
    }
    
    /**
     * This method returns a formated date string to include in the Javadoc tag and XML comments. You may return null if
     * you do not want the date in these documentation elements.
     * 
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString()
    {
        if (suppressDate)
        {
            return null;
        }
        else
        {
            return new Date().toString();
        }
    }
}
