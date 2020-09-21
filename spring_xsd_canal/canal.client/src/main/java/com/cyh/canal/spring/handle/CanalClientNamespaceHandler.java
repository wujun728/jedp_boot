package com.cyh.canal.spring.handle;

import com.cyh.canal.spring.parser.CanalConfigBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author yonghui.cui
 * @version 1.0  2015/1/25
 */
public class CanalClientNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("canal-config", new CanalConfigBeanDefinitionParser());
    }
}
