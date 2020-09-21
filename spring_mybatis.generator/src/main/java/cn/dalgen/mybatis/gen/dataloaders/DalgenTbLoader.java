package cn.dalgen.mybatis.gen.dataloaders;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.dalgen.mybatis.gen.model.Gen;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import cn.dalgen.mybatis.gen.common.FileNameSelector;
import cn.dalgen.mybatis.gen.model.repository.TableRepository;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import com.google.common.collect.Lists;

/**
 * Created by bangis.wangdf on 15/12/3. Desc
 */
public class DalgenTbLoader extends AbstractDalgenLoader {
    /**
     * The constant LOG.
     */
    private static final Log LOG             = new SystemStreamLog();

    /**
     * The Table repository.
     */
    private TableRepository  tableRepository = new TableRepository();

    /**
     * Load.
     *
     * @param gen the gen
     * @param connection the connection
     */
    @Override
    public void load(Gen gen, Connection connection, File tablesFile) throws SQLException {

        String cmd = ConfigUtil.getCmd();
        if (StringUtils.equals(StringUtils.trim(cmd), "*")) {
            return;
        }
        List<String> cmdTables = Lists.newArrayList(StringUtils.split(StringUtils.upperCase(cmd),";"));

        List<String> neadInitTables = Lists.newArrayList();

        List<String> existsTables = Lists.newArrayList();
        for (File file : tablesFile.listFiles(new FileNameSelector("xml"))) {
            existsTables.add(file2DbName(file));
        }

        for (String cmdTable : cmdTables) {
            if (!existsTables.contains(cmdTable)&& StringUtils.isNotBlank(cmdTable)) {
                neadInitTables.add(cmdTable);
            }
        }

        if (neadInitTables.isEmpty()) {
            return;
        }else{
            ConfigUtil.setCmd("*");
        }

        LOG.info("开始初始化表"+neadInitTables);
        for (String neadInitTable : neadInitTables) {
            LOG.info("初始化表:" + neadInitTable);
            gen.addTable(tableRepository.gainTable(connection, neadInitTable, null));
        }
    }
}
