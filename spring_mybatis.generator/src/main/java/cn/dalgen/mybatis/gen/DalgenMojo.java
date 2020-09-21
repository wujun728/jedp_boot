package cn.dalgen.mybatis.gen;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.dalgen.mybatis.AbstractBaseMojo;
import cn.dalgen.mybatis.gen.dataloaders.DalgenTbLoader;
import cn.dalgen.mybatis.gen.enums.SystemEnum;
import cn.dalgen.mybatis.gen.utils.CmdUtil;
import cn.dalgen.mybatis.gen.utils.ConfInit;
import cn.dalgen.mybatis.gen.utils.SysUtil;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import cn.dalgen.mybatis.gen.dataloaders.DalgenLoader;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import com.google.common.collect.Lists;
import fmpp.ProcessingException;
import fmpp.progresslisteners.ConsoleProgressListener;
import fmpp.setting.SettingException;
import fmpp.setting.Settings;
import fmpp.tdd.EvalException;
import fmpp.tdd.Interpreter;
import fmpp.util.MiscUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @author bangis.wangdf
 * @date 15/12/3
 */
@Mojo(name = "gen")
public class DalgenMojo extends AbstractBaseMojo {

    /**
     * The constant DEFAULT_ERROR_MSG.
     */
    private static final String DEFAULT_ERROR_MSG = "\"%s\" is a required parameter. ";

    /**
     * The constant cmdUtil.
     */
    private CmdUtil cmdUtil = new CmdUtil();

    /**
     * Project instance, needed for attaching the build info file. Used to add
     * new source directory to the build.
     *
     * @since 1.0
     */
    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    /**
     * Location of the output files.
     */
    @Parameter(defaultValue = "${project.basedir}/src/")
    private File outputDirectory;

    /**
     * Location of the FreeMarker template files.
     * ${project.build.directory}/generated-sources/fmpp/
     *
     * @since 1.0
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}/templates/")
    private File templateDirectory;

    /**
     * Location of the FreeMarker template files.
     * ${project.build.directory}/generated-sources/fmpp/
     *
     * @since 1.0
     */
    @Parameter(defaultValue = "dalgen/ext/")
    private File templateExtDirectory;

    /**
     * 配置文件
     */
    @Parameter(defaultValue = "dalgen/config/config.xml")
    private File config;

    /**
     * copyTemplate
     */
    @Parameter(defaultValue = "true")
    private boolean copyTemplate;

    private boolean testF = false;

    /**
     * Instantiates a new Dalgen mojo.
     */
    public DalgenMojo() {
        super();
    }

    /**
     * Instantiates a new Dalgen mojo. for Test
     *
     * @param outputDirectory      the output directory
     * @param templateDirectory    the template directory
     * @param templateExtDirectory the template directory
     * @param config               the config
     * @param project              the project
     */
    public DalgenMojo(File outputDirectory, File templateDirectory, File templateExtDirectory, File config,
                      MavenProject project, boolean testF) {
        this.outputDirectory = outputDirectory;
        this.templateDirectory = templateDirectory;
        this.templateExtDirectory = templateExtDirectory;
        this.config = config;
        this.project = project;
        this.testF = testF;
    }

    /**
     * Execute.
     *
     * @throws MojoExecutionException the mojo execution exception
     * @throws MojoFailureException the mojo failure exception
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        configInit(testF);

        List<Plugin> buildPlugins = project.getBuildPlugins();
        for (Plugin plugin : buildPlugins) {
            if ("cn.dalgen.plugins".equals(plugin.getGroupId()) && "mybatis-maven-plugin".equals(
                plugin.getArtifactId())) {
                CmdUtil.DALGEN_VERSION = plugin.getVersion();
                break;
            }
        }

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        try {
            ConfigUtil.readConfig(config);
            String _cmd = cmdUtil.consoleInput();

            if (StringUtils.equalsIgnoreCase("Q", _cmd)) {
                getLog().info("dalgen success");
                return;
            }

            if (StringUtils.equalsIgnoreCase("q", _cmd)) {
                getLog().info("dalgen 放弃生成");
                return;
            }

            if (ConfigUtil.getConfig().containCmd(_cmd)) {
                ConfigUtil.setCmd("*");
            }

            ConfigUtil.dalgenPath = config.getParentFile().getParent();
            if (!StringUtils.endsWith("*", ConfigUtil.getCmd())) {
                executeInit();
            }
            executeGen(_cmd);
            executeExtGen(_cmd);

            getLog().info("dalgen 执行生成");
        } catch (Exception e) {
            getLog().error(MiscUtil.causeMessages(e));
            throw new MojoFailureException(MiscUtil.causeMessages(e), e);
        } finally {
        }
    }

    /**
     * Config init.
     *
     * @param testF 测试标记,用于单元测试用
     * @throws MojoExecutionException the mojo execution exception
     * @throws MojoFailureException the mojo failure exception
     */
    private void configInit(boolean testF) throws MojoExecutionException, MojoFailureException {
        if (testF) {
            return;
        }
        if (project == null) {
            throw new MojoExecutionException(String.format(DEFAULT_ERROR_MSG, "project")
                + "This plugin can be used only inside a project.");
        }

        //copy 初始化文件
        ConfInit.copyConfigFile(this);

        //复制模板
        ConfInit.copyTemplateFile(this);

        ConfInit.copyExtFile(this);
    }

    /**
     * Execute init.
     *
     * @throws SettingException the setting exception
     * @throws IOException the io exception
     * @throws EvalException the eval exception
     * @throws ProcessingException the processing exception
     */
    private void executeInit() throws SettingException, IOException, EvalException,
        ProcessingException {
        Settings settings = new Settings(new File("."));
        settings.set(Settings.NAME_SOURCE_ROOT, templateDirectory.getAbsolutePath());
        settings.set(Settings.NAME_OUTPUT_ROOT, config.getParentFile().getParent());
        settings.set(Settings.NAME_OUTPUT_ENCODING, "UTF-8");
        settings.set(Settings.NAME_SOURCE_ENCODING, "UTF-8");

        //输入项目
        List<String> ignoreList = Lists.newArrayList("ignore(.DS_Store)", "ignore(*/.DS_Store)",
            "ignore(config/*.*)", "ignore(lib/*.*)", "ignore(css/*.*)", "ignore(dalgen/*.*)");
        for (String cmd : ConfigUtil.getConfig().cmdNames()) {
            ignoreList.add("ignore(" + cmd + "/*.*)");
        }
        settings.set(Settings.NAME_DATA, "dalgen: " + DalgenTbLoader.class.getName()
            + "(),project:1");
        settings.set(Settings.NAME_MODES,
            Interpreter.evalAsSequence(StringUtils.join(ignoreList, ",")));

        settings.addProgressListener(new ConsoleProgressListener());
        settings.execute();

        getLog().info("初始化表完成");
    }

    /**
     * Execute gen.
     *
     * @throws SettingException the setting exception
     * @throws IOException the io exception
     * @throws EvalException the eval exception
     * @throws ProcessingException the processing exception
     */
    private void executeGen(String cmdName) throws SettingException, IOException, EvalException,
        ProcessingException {
        Settings settings = new Settings(new File("."));
        settings.set(Settings.NAME_SOURCE_ROOT, templateDirectory.getAbsolutePath());
        final String outPath = getOutPath(cmdName, outputDirectory.getAbsolutePath());
        File outPathFile = new File(outPath);
        if (!outPathFile.exists()) {
            outPathFile.mkdirs();
        }
        settings.set(Settings.NAME_OUTPUT_ROOT, outPath);
        settings.set(Settings.NAME_OUTPUT_ENCODING, "UTF-8");
        settings.set(Settings.NAME_SOURCE_ENCODING, "UTF-8");

        List<String> ignoreList = Lists.newArrayList("ignore(.DS_Store)", "ignore(*/.DS_Store)",
            "ignore(config/*.*)", "ignore(lib/*.*)", "ignore(css/*.*)", "ignore(init/*.*)");
        //忽略不需要生成的模板
        for (String cmd : ConfigUtil.getConfig().cmdNames()) {
            if (StringUtils.equals(cmd, cmdName)) {
                ignoreList.add("ignore(dalgen/*.*)");
            } else {
                ignoreList.add("ignore(" + cmd + "/*.*)");
            }
        }

        //输入项目
        settings.set(Settings.NAME_DATA, "dalgen: " + DalgenLoader.class.getName() + "()");
        settings.set(Settings.NAME_MODES,
            Interpreter.evalAsSequence(StringUtils.join(ignoreList, ",")));

        settings.addProgressListener(new ConsoleProgressListener());
        settings.execute();
    }

    /**
     * Execute gen.
     *
     * @throws SettingException the setting exception
     * @throws IOException the io exception
     * @throws EvalException the eval exception
     * @throws ProcessingException the processing exception
     */
    private void executeExtGen(String cmdName) throws SettingException, IOException, EvalException,
        ProcessingException {
        if (!templateExtDirectory.exists()) {
            return;
        }
        Settings settings = new Settings(new File("."));
        settings.set(Settings.NAME_SOURCE_ROOT, templateExtDirectory.getAbsolutePath());
        final String outPath = getOutPath(cmdName, outputDirectory.getAbsolutePath());
        File outPathFile = new File(outPath);
        if (!outPathFile.exists()) {
            outPathFile.mkdirs();
        }
        settings.set(Settings.NAME_OUTPUT_ROOT, outPath);
        settings.set(Settings.NAME_OUTPUT_ENCODING, "UTF-8");
        settings.set(Settings.NAME_SOURCE_ENCODING, "UTF-8");

        List<String> ignoreList = Lists.newArrayList("ignore(.DS_Store)", "ignore(*/.DS_Store)",
            "ignore(config/*.*)", "ignore(lib/*.*)", "ignore(css/*.*)", "ignore(init/*.*)");
        //忽略不需要生成的模板
        for (String cmd : ConfigUtil.getConfig().cmdNames()) {
            if (StringUtils.equals(cmd, cmdName)) {
                ignoreList.add("ignore(dalgen/*.*)");
            } else {
                ignoreList.add("ignore(" + cmd + "/*.*)");
            }
        }

        //输入项目
        settings.set(Settings.NAME_DATA, "dalgen: " + DalgenLoader.class.getName() + "()");
        settings.set(Settings.NAME_MODES,
            Interpreter.evalAsSequence(StringUtils.join(ignoreList, ",")));

        settings.addProgressListener(new ConsoleProgressListener());
        settings.execute();

    }

    /**
     * 设置输出路径
     *
     * @param cmdName
     * @param outputPath
     * @throws SettingException
     */
    private static String getOutPath(String cmdName, String outputPath) throws SettingException {
        String outpath = ConfigUtil.getConfig().getOutpath(cmdName);
        if (StringUtils.isNotBlank(outpath)) {
            if (outpath.contains(":") || outpath.startsWith("/")) {
                //后续会特殊处理
                if (SystemEnum.Window == SysUtil.getSystem()) {
                    return outpath;
                } else {
                    return outpath;
                }
            } else {
                String absolutePath = StringUtils.replace(outputPath, "\\", "/");
                if (outpath.startsWith("../")) {
                    final String[] paths = StringUtils.split(absolutePath, "/");
                    final String[] splits = StringUtils.split(outpath, "/");
                    int cnt = 0;
                    for (String split : splits) {
                        if (StringUtils.equals("..", split)) {
                            cnt++;
                        }
                    }
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < paths.length - cnt; i++) {
                        sb.append("/").append(paths[i]);
                    }
                    for (int i = cnt; i < splits.length; i++) {
                        sb.append("/").append(splits[i]);
                    }
                    return sb.toString();
                } else {
                    return absolutePath + "/" + outpath;
                }

            }

        }
        return outputPath;
    }

    /**
     * Sets cmd util. forTest
     *
     * @param cmdUtil the cmd util
     */
    public void setCmdUtil(CmdUtil cmdUtil) {
        this.cmdUtil = cmdUtil;
    }

    /**
     * Gets project.
     *
     * @return the project
     */
    @Override
    public MavenProject getProject() {
        return project;
    }

    /**
     * Gets output directory.
     *
     * @return the output directory
     */
    public File getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Gets template directory.
     *
     * @return the template directory
     */
    @Override
    public File getTemplateDirectory() {
        return templateDirectory;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    @Override
    public File getConfig() {
        return config;
    }

    /**
     * Is copy template boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isCopyTemplate() {
        return copyTemplate;
    }

    @Override
    public File getTemplateExtDirectory() {
        return templateExtDirectory;
    }
}
