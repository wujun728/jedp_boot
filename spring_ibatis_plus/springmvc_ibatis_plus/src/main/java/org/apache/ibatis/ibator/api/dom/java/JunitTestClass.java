/*
 *  Copyright 2006 The Apache Software Foundation
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
package org.apache.ibatis.ibator.api.dom.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.ibatis.ibator.api.dom.OutputUtilities;
import org.apache.ibatis.ibator.internal.util.StringUtility;

/**
 * @author Jeff Butler <BR>
 *         生成junit 测试代码 add by kalin
 */
public class JunitTestClass extends InnerClass implements CompilationUnit
{
    private Set<FullyQualifiedJavaType> importedTypes;
    
    private List<String> fileCommentLines;
    
    /**
     *  
     */
    public JunitTestClass(FullyQualifiedJavaType type)
    {
        super(type);
        importedTypes = new TreeSet<FullyQualifiedJavaType>();
        fileCommentLines = new ArrayList<String>();
    }
    
    /**
     * @return Returns the importedTypes.
     */
    @Override
    public Set<FullyQualifiedJavaType> getImportedTypes()
    {
        return Collections.unmodifiableSet(importedTypes);
    }
    
    @Override
    public void addImportedType(FullyQualifiedJavaType importedType)
    {
        if (importedType != null && importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName()))
        {
            importedTypes.add(importedType);
        }
    }
    
    @Override
    public String getFormattedContent()
    {
        StringBuilder sb = new StringBuilder();
        for (String fileCommentLine : fileCommentLines)
        {
            sb.append(fileCommentLine);
            OutputUtilities.newLine(sb);
        }
        if (StringUtility.stringHasValue(getType().getPackageName()))
        {
            sb.append("package "); //$NON-NLS-1$
            sb.append(getType().getPackageName());
            sb.append(';');
            OutputUtilities.newLine(sb);
            OutputUtilities.newLine(sb);
        }
        for (FullyQualifiedJavaType fqjt : importedTypes)
        {
            if (fqjt.isExplicitlyImported())
            {
                sb.append("import "); //$NON-NLS-1$
                sb.append(fqjt.getFullyQualifiedName());
                sb.append(';');
                OutputUtilities.newLine(sb);
            }
        }
        if (importedTypes.size() > 0)
        {
            OutputUtilities.newLine(sb);
        }
        sb.append(super.getFormattedContent(0));
        return sb.toString();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.ibator.internal.java.dom.CompilationUnit#isJavaInterface()
     */
    @Override
    public boolean isJavaInterface()
    {
        return false;
    }
    
    @Override
    public boolean isJavaEnumeration()
    {
        return false;
    }
    
    @Override
    public void addFileCommentLine(String commentLine)
    {
        fileCommentLines.add(commentLine);
    }
    
    @Override
    public List<String> getFileCommentLines()
    {
        return fileCommentLines;
    }
    
    @Override
    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes)
    {
        this.importedTypes.addAll(importedTypes);
    }
}
