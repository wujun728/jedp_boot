package cn.dalgen.mybatis.gen;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.dalgen.mybatis.gen.utils.CmdUtil;
import junit.framework.TestCase;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.project.MavenProject;
import org.mockito.Mockito;

import cn.dalgen.mybatis.gen.utils.ConfigUtil;

/**
 * Created by bangis.wangdf on 15/12/5.
 * Desc
 */
public class DalgenMojoTest extends TestCase{
    /**
     * The constant LOG.
     */
    private static final Log LOG               = new SystemStreamLog();

    /**
     * The constant BASE_PATH.
     */
    public static String BASE_PATH         = CmdUtil.class
        .getResource("")
        .getPath()
        .replace(
            CmdUtil.class.getPackage()
                .getName()
                .replace(".", "/")
                + "/", "");

    static {
        try {
            BASE_PATH = URLDecoder.decode(BASE_PATH, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * The constant outputDirectory.
     */
    public static File outputDirectory   = new File(BASE_PATH + "out/");

    /**
     * The constant templateDirectory.
     */
    public static File         templateDirectory = new File(BASE_PATH + "dalgen/templates/");
    /**
     * The constant templateDirectory.
     */
    public static File         templateExtDirectory = new File(BASE_PATH + "dalgen/ext/");

    /**
     * The constant config.
     */
    public static File         config            = new File(BASE_PATH + "dalgen/config/config.xml");

    static {

        try {
            ConfigUtil.readConfig(config);
            ConfigUtil.setCmd("ABS_PLAN_CAPITAL_ORDER");
        } catch (IOException e) {
            LOG.error(e);
        }
    }
    public void testExecute() throws Exception {
        MavenProject project = new MavenProject();
        project.setName("");
        DalgenMojo dalgenMojo = new DalgenMojo(outputDirectory,templateDirectory,templateExtDirectory,config,project,true);
        CmdUtil cmdUtil = Mockito.mock(CmdUtil.class);
        Mockito.when(cmdUtil.consoleInput()).thenReturn(ConfigUtil.getCmd());
        dalgenMojo.setCmdUtil(cmdUtil);
        dalgenMojo.execute();
    }
}
