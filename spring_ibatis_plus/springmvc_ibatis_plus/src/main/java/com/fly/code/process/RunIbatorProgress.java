package com.fly.code.process;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ibator.api.Ibator;
import org.apache.ibatis.ibator.config.IbatorConfiguration;
import org.apache.ibatis.ibator.config.xml.IbatorConfigurationParser;
import org.apache.ibatis.ibator.internal.DefaultShellCallback;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.fly.code.CodeMaker;
import com.fly.code.util.StringUtil;
import com.keta.generate.db.BaseDataSource;
import com.keta.generate.db.Mysql;
import com.keta.generate.db.Oracle;
import com.keta.generate.util.ConvertHandler;
import com.keta.generate.util.Resources;
import com.keta.generate.vo.Table;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 
 * ibator代码生成
 * 
 * @author 00fly
 * @version [版本号, 2018年8月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RunIbatorProgress implements IRunnableWithProgress
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RunIbatorProgress.class);
    
    private static Configuration config;
    
    private static Map<String, String> map = new HashMap<>();
    
    static
    {
        config = new Configuration();
        config.setDefaultEncoding("UTF-8");
        config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        config.setDateFormat("yyyy-MM-dd");
        config.setTimeFormat("HH:mm:ss");
        config.setNumberFormat("#0.#");
        config.setObjectWrapper(new DefaultObjectWrapper());
        config.setClassForTemplateLoading(RunIbatorProgress.class, "/template/springIbatis");
        ResourceBundle bundle = ResourceBundle.getBundle("generateType");
        for (String key : bundle.keySet())
        {
            map.put(key, StringUtils.trimToEmpty(bundle.getString(key)));
        }
    }
    
    private String driver;
    
    private String dburl;
    
    private String username;
    
    private String password;
    
    private String packName;
    
    private String projectDir;
    
    private String projectSrcDir;
    
    private String[] tableName;
    
    private String prefix;
    
    /**
     * FreeMarker model
     */
    Map<String, Object> model = new HashMap<>();
    
    /**
     * 默认构造函数
     */
    public RunIbatorProgress(String driver, String dburl, String username, String password, String packName, String projectDir, String[] tabName, String prefix)
    {
        super();
        this.driver = driver;
        this.dburl = dburl;
        this.username = username;
        this.password = password;
        this.packName = packName;
        this.projectDir = projectDir;
        this.projectSrcDir = projectDir + "src\\";
        this.tableName = tabName;
        new File(projectSrcDir + "/main/java/").mkdirs();
        new File(projectSrcDir + "/main/resources/").mkdirs();
        new File(projectSrcDir + "/main/webapp/").mkdirs();
        new File(projectSrcDir + "/test/java/").mkdirs();
        new File(projectSrcDir + "/test/resources/").mkdirs();
        this.prefix = prefix;
    }
    
    /**
     * 在当前目录，创建并运行脚本
     * 
     * @param monitor
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @Override
    public void run(IProgressMonitor monitor)
        throws InvocationTargetException, InterruptedException
    {
        try
        {
            monitor.beginTask("生成代码", IProgressMonitor.UNKNOWN);
            monitor.subTask("自动生成DAO、Map、MODEL 代码中......");
            creatAndRunIbator(driver, dburl, username, password, packName, projectSrcDir, tableName);
            monitor.done();
        }
        catch (Exception e)
        {
            throw new InvocationTargetException(e.getCause(), e.getMessage());
        }
    }
    
    /**
     * 在当前目录，创建ibator 配置文件
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String creatIbatorConig()
    {
        model.put("driver", driver);
        model.put("dburl", dburl);
        model.put("username", username);
        model.put("password", password);
        model.put("packageName", packName);
        model.put("packagePath", packName.replace(".", "\\"));
        model.put("projectSrcDir", projectSrcDir);
        model.put("tables", tableName);
        Map<String, String> domainName = new HashMap<>();
        for (String table : tableName)
        {
            String domain = table;
            if (StringUtils.isNotBlank(prefix))
            {
                domain = table.replaceAll("(?i)^" + prefix, "");
            }
            domainName.put(table, StringUtil.camelCase(domain, true));
        }
        model.put("domains", domainName);
        model.put("date", new Date());
        return FreeMarkers.renderIbatorConfigTemplate(model);
    }
    
    /**
     * 运行 Ibator代码创建程序
     * 
     * @param driver
     * @param dburl
     * @param username
     * @param password
     * @param packName
     * @param projectSrcDir
     * @param tabName
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    private void creatAndRunIbator(String driver, String dburl, String username, String password, String packName, String projectSrcDir, String[] tabName)
        throws Exception
    {
        // 直接调用ibator IbatorRunner main
        java.util.List<String> warnings = new ArrayList<>();
        Set<String> contexts = new HashSet<>();
        Set<String> fullyqualifiedTables = new HashSet<>();
        StringReader configStr = new StringReader(creatIbatorConig());
        InputSource inputSource = new InputSource(configStr);
        IbatorConfigurationParser cp = new IbatorConfigurationParser(warnings);
        IbatorConfiguration ibatorConfiguration = cp.parseIbatorConfiguration(inputSource);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        Ibator ibator = new Ibator(ibatorConfiguration, callback, warnings);
        ibator.generate(null, contexts, fullyqualifiedTables);
        creatTemplateCode(projectDir, packName, tabName);
    }
    
    /**
     * 生成模板代码
     * 
     * @param projectDir
     * @param packName
     * @param tabNames
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    private void creatTemplateCode(String projectDir, String packName, String[] tabNames)
        throws Exception
    {
        for (String tableName : tabNames)
        {
            String className;
            if (StringUtils.isEmpty(prefix))
            {
                className = StringUtil.camelCase(tableName, true);
            }
            else
            {
                className = StringUtil.camelCase(tableName.replaceAll("(?i)^" + prefix, ""), true);
            }
            Resources.init(driver, dburl, username, password, tableName, packName, projectDir, className, null);
            Table table = getTable(tableName);
            model.put("pk", table.getPk());
            model.put("className", className);
            model.put("columns", table.getColumns());
            model.put("instanceName", StringUtils.uncapitalize(className));
            model.put("map", map);
            
            // 遍历map xml文件
            String fatherDir = projectDir + "src\\main\\resources\\" + packName.replace(".", "\\");
            String mapper = fatherDir + "\\map\\";
            Set<String> mappers = new HashSet<>();
            if (new File(mapper).exists())
            {
                for (File file : new File(mapper).listFiles())
                {
                    mappers.add(packName.replace(".", "\\") + "\\map\\" + file.getName());
                }
            }
            model.put("mappers", mappers);
            URL url = CodeMaker.class.getProtectionDomain().getCodeSource().getLocation();
            LOGGER.info("File Path: {}", url.getPath());
            if (url.getPath().endsWith(".jar")) // 可运行jar内文件处理
            {
                JarFile jarFile = new JarFile(url.getFile());
                Enumeration<JarEntry> entrys = jarFile.entries();
                while (entrys.hasMoreElements())
                {
                    JarEntry jar = (JarEntry)entrys.nextElement();
                    String name = jar.getName();
                    if (!jar.isDirectory() && name.startsWith("template/springIbatis"))
                    {
                        String path = FreeMarkers.renderString(name, model);
                        String realPath = projectDir + StringUtils.substringAfter(path, "template/springIbatis");
                        if (name.endsWith(".ftl"))// 模板文件
                        {
                            realPath = realPath.substring(0, realPath.length() - 4);
                            String ftl = StringUtils.substringAfter(name, "template/springIbatis/");
                            Template template = config.getTemplate(ftl);
                            String content = FreeMarkers.renderTemplate(template, model);
                            content = content.replace("$\\{", "${");
                            FileUtils.writeStringToFile(new File(realPath), content, "UTF-8");
                        }
                        else
                        {
                            InputStream inputStream = CodeMaker.class.getResourceAsStream("/" + name);
                            FileUtils.copyInputStreamToFile(inputStream, new File(realPath));
                        }
                    }
                }
                jarFile.close();
            }
            else
            {
                File tFile = new File(url.getFile() + "/template/springIbatis");
                Collection<File> listFiles = FileUtils.listFiles(tFile, new String[] {"jar", "ftl", "jsp", "html", "htm", "js", "css", "map", "eot", "svg", "ttf", "woff", "woff2"}, true);
                for (File file : listFiles)
                {
                    String path = FreeMarkers.renderString(file.getAbsolutePath(), model);
                    String realPath = projectDir + StringUtils.substringAfter(path, "\\template\\springIbatis\\");
                    if (realPath.endsWith(".ftl"))
                    {
                        realPath = realPath.substring(0, realPath.length() - 4);
                        String ftl = StringUtils.substringAfter(file.getAbsolutePath(), "\\template\\springIbatis\\");
                        Template template = config.getTemplate(ftl);
                        String content = FreeMarkers.renderTemplate(template, model);
                        content = content.replace("$\\{", "${");
                        FileUtils.writeStringToFile(new File(realPath), content, "UTF-8");
                    }
                    else
                    {
                        FileUtils.copyFile(file, new File(realPath));
                    }
                }
            }
        }
    }
    
    /**
     * 根据表名查询表信息
     * 
     * @param tableName
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    private Table getTable(String tableName)
        throws Exception
    {
        String dialect = Resources.JDBC_DRIVER;
        BaseDataSource db;
        if ("com.mysql.jdbc.Driver".equals(dialect))
        {
            db = new Mysql();
        }
        else
        {
            db = new Oracle();
        }
        Table table = db.getTable(tableName);
        ConvertHandler.tableHandle(table);
        return table;
    }
}
