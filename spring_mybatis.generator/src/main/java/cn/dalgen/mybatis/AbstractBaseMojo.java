package cn.dalgen.mybatis;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

/**
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis: AbstractBaseMojo.java, v 0.1 2019-11-22 18:53 bangis.wangdf Exp $
 */
public abstract class AbstractBaseMojo extends AbstractMojo {
    public abstract MavenProject getProject();
    public abstract File getTemplateDirectory();
    public abstract File getTemplateExtDirectory();
    public  abstract File getConfig();
    public abstract boolean isCopyTemplate();
}
