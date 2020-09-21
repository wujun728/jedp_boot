package cn.dalgen.mybatis.gen.utils;

import java.io.*;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import cn.dalgen.mybatis.AbstractBaseMojo;
import cn.dalgen.mybatis.ddl.DalddlMojo;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import cn.dalgen.mybatis.gen.DalgenMojo;
import org.codehaus.plexus.util.IOUtil;

/**
 * Created by bangis.wangdf on 15/12/17. Desc 初始化所需文件<br/>
 * 首次执行自动copy所需文件<br/>
 * 如config.xml已存在则更新templates,table-config-1.1.dtd
 */
public class ConfInit {

    public static final String NEED_COPY_CONFIG = "dalgen/config/";
    public static final String NEED_COPY_TEMPLATES = "dalgen/templates/";
    public static final String NEED_COPY_EXT = "dalgen/ext/";
    public static final String NEED_DDL_COPY_CONFIG = "dalddl/config/";
    public static final String NEED_DDL_COPY_TEMPLATES = "dalddl/templates/";
    public static final String NEED_DDL_COPY_EXT = "dalddl/ext/";

    private static JarFile jarFile;

    static {
        try {
            jarFile = new JarFile(URLDecoder.decode(ConfInit.class.getProtectionDomain().getCodeSource()
                .getLocation().getPath(), "UTF-8"));
        } catch (IOException e) {
        }
    }

    /**
     * copy config.xml文件
     *
     * @param dalgenMojo
     */
    public static void copyConfigFile(AbstractBaseMojo dalgenMojo) {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String fileName = jarEntry.getName();
            if (dalgenMojo instanceof DalgenMojo &&StringUtils.startsWithIgnoreCase(fileName, NEED_COPY_CONFIG)) {
                if (StringUtils.equalsIgnoreCase(fileName, NEED_COPY_CONFIG + "config.xml")) {
                    if (!dalgenMojo.getConfig().exists()) {
                        dalgenMojo.getLog().info("初始化配置信息开始");
                        copyAndOverWriteFile(fileName, dalgenMojo.getConfig());

                        dalgenMojo.getLog().info("初始化完成,下一步到 " + StringUtils
                            .substringAfter(dalgenMojo.getConfig().getAbsolutePath(),
                                dalgenMojo.getProject().getBasedir().getAbsolutePath()) + " 中配置数据源");
                        System.exit(0);
                    }
                } else {
                    copyAndOverWriteFile(fileName, new File(dalgenMojo.getConfig()
                        .getParent() + jarEntry.getName().substring(NEED_COPY_CONFIG.length() - 1)));
                }
            }
            //DDL
            if (dalgenMojo instanceof DalddlMojo &&StringUtils.startsWithIgnoreCase(fileName, NEED_DDL_COPY_CONFIG)) {
                if (StringUtils.equalsIgnoreCase(fileName, NEED_DDL_COPY_CONFIG + "config.xml")) {
                    if (!dalgenMojo.getConfig().exists()) {
                        dalgenMojo.getLog().info("初始化配置信息开始");
                        copyAndOverWriteFile(fileName, dalgenMojo.getConfig());

                        dalgenMojo.getLog().info("初始化完成,下一步到 " + StringUtils
                            .substringAfter(dalgenMojo.getConfig().getAbsolutePath(),
                                dalgenMojo.getProject().getBasedir().getAbsolutePath()) + " 中配置数据源");
                        System.exit(0);
                    }
                } else {
                    copyAndOverWriteFile(fileName, new File(dalgenMojo.getConfig()
                        .getParent() + jarEntry.getName().substring(NEED_DDL_COPY_CONFIG.length() - 1)));
                }
            }
        }
    }

    /**
     * copy template 文件
     *
     * @param dalgenMojo
     */
    public static void copyTemplateFile(AbstractBaseMojo dalgenMojo) {
        Enumeration<JarEntry> entries = jarFile.entries();
        boolean _copyTemplate = dalgenMojo.isCopyTemplate();
        if (!_copyTemplate) {
            if (StringUtils.startsWithIgnoreCase(dalgenMojo.getTemplateDirectory().getAbsolutePath(),
                dalgenMojo.getProject().getBuild().getOutputDirectory())) {
                _copyTemplate = true;
            }
        }
        if (_copyTemplate) {
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String fileName = jarEntry.getName();

                if (dalgenMojo instanceof DalgenMojo && StringUtils.startsWithIgnoreCase(fileName, NEED_COPY_TEMPLATES)) {
                    copyAndOverWriteFile(fileName, new File(
                        dalgenMojo.getTemplateDirectory().getAbsolutePath() + jarEntry.getName()
                            .substring(NEED_COPY_TEMPLATES.length() - 1)));
                }
                //DDL
                if (dalgenMojo instanceof DalddlMojo && StringUtils.startsWithIgnoreCase(fileName, NEED_DDL_COPY_TEMPLATES)) {
                    copyAndOverWriteFile(fileName, new File(
                        dalgenMojo.getTemplateDirectory().getAbsolutePath() + jarEntry.getName()
                            .substring(NEED_DDL_COPY_TEMPLATES.length() - 1)));
                }
            }
        }
    }

    /**
     * copy ext 文件
     *
     * @param dalgenMojo
     */
    public static void copyExtFile(AbstractBaseMojo dalgenMojo) {
        if (!dalgenMojo.getTemplateExtDirectory().exists()) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String fileName = jarEntry.getName();
                if (dalgenMojo instanceof DalgenMojo &&StringUtils.startsWithIgnoreCase(fileName, NEED_COPY_EXT)) {
                    copyAndOverWriteFile(fileName, new File(
                        dalgenMojo.getTemplateExtDirectory().getAbsolutePath() + jarEntry.getName()
                            .substring(NEED_COPY_EXT.length() - 1)));
                }

                if (dalgenMojo instanceof DalddlMojo &&StringUtils.startsWithIgnoreCase(fileName, NEED_DDL_COPY_EXT)) {
                    copyAndOverWriteFile(fileName, new File(
                        dalgenMojo.getTemplateExtDirectory().getAbsolutePath() + jarEntry.getName()
                            .substring(NEED_DDL_COPY_EXT.length() - 1)));
                }
            }
        }
    }

    /**
     * Copy and over write file.
     *
     * @param soureName the soure name
     * @param outFile   the out file
     * @throws IOException the io exception
     */
    private static void copyAndOverWriteFile(String soureName, File outFile) {
        //目录不存在则创建
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }

        //不是文件则不copy 判断标准为文件含 点 号
        if (StringUtils.indexOf(soureName, '.') == -1) {
            return;
        }
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                ConfInit.class.getResourceAsStream("/" + soureName), "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write("\n");
            }
            writer.flush();
        } catch (Exception e) {
            System.out.println("======");
        } finally {
            IOUtil.close(reader);
            IOUtil.close(writer);
        }
    }

}
