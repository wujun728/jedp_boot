package cn.dalgen.mybatis.ddl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.dalgen.mybatis.AbstractBaseMojo;
import cn.dalgen.mybatis.gen.dataloaders.DalgenLoader;
import cn.dalgen.mybatis.gen.dataloaders.DalgenTbLoader;
import cn.dalgen.mybatis.gen.enums.SystemEnum;
import cn.dalgen.mybatis.gen.utils.CmdUtil;
import cn.dalgen.mybatis.gen.utils.ConfInit;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import cn.dalgen.mybatis.gen.utils.SysUtil;
import com.google.common.collect.Lists;
import fmpp.ProcessingException;
import fmpp.progresslisteners.ConsoleProgressListener;
import fmpp.setting.SettingException;
import fmpp.setting.Settings;
import fmpp.tdd.EvalException;
import fmpp.tdd.Interpreter;
import fmpp.util.MiscUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @author bangis.wangdf
 * @date 15/12/3
 */
@Mojo(name = "ddl")
public class DalddlMojo extends AbstractBaseMojo {

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
    public DalddlMojo() {
        super();
    }

    /**
     * Instantiates a new Dalgen mojo. for Test
     *
     * @param outputDirectory   the output directory
     * @param templateDirectory the template directory
     * @param config            the config
     * @param project           the project
     */
    public DalddlMojo(File outputDirectory, File templateDirectory, File config,
                      MavenProject project, boolean testF) {
        this.outputDirectory = outputDirectory;
        this.templateDirectory = templateDirectory;
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

        // ConfInit.copyExtFile(this);
    }

    @Override
    public MavenProject getProject() {
        return project;
    }

    @Override
    public File getTemplateDirectory() {
        return templateDirectory;
    }

    @Override
    public File getTemplateExtDirectory() {
        return null;
    }

    @Override
    public File getConfig() {
        return config;
    }

    @Override
    public boolean isCopyTemplate() {
        return copyTemplate;
    }
}
