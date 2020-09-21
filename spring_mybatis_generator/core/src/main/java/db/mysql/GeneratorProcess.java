package db.mysql;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.model.TableData;
import db.mysql.process.MysqlGenUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * PACKAGE_NAME
 * Created by ASUS on 2017/7/3.
 * 14:27
 */
public class GeneratorProcess {

    public static void main(String[] args) throws IOException, TemplateException, SQLException, ClassNotFoundException {
        System.out.println(RuntimeEnv.pp);
        RuntimeEnv.reader();
        generate();
        RuntimeEnv.storage();
        System.out.println(RuntimeEnv.pp);
    }
    public static void generate() throws IOException, TemplateException, SQLException, ClassNotFoundException {
//        List<MySqlData> mySqlDataList = RuntimeEnv.mc.getTableColumns(RuntimeEnv.pp.getTableName());
        TableData tableData = RuntimeEnv.mc.getTableData(RuntimeEnv.pp.getTableName());
        Map<String,Object> root = new HashMap<>();
        root.put("schema", RuntimeEnv.pp.getSchema());
        root.put("packageModel", RuntimeEnv.pp.getPackageModel());
        root.put("packageMapper", RuntimeEnv.pp.getPackageMapper());
        root.put("tableName", RuntimeEnv.pp.getTableName());
        root.put("className", RuntimeEnv.pp.getClassName());
        root.put("author", RuntimeEnv.pp.getAuthor());
        root.put("attrs",tableData.getColumns());
        root.put("tableAttrs",tableData);
        root.put("mapperName", RuntimeEnv.pp.getMapperName());
        root.put("mapperXmlName", RuntimeEnv.pp.getMapperXmlName());
        root.put("sense", DataBaseTypeEnum.getByName(RuntimeEnv.pp.getDataBaseType()).getSense());
        root.put("databaseType", RuntimeEnv.pp.getDataBaseType());
        root.put("timeStamp",String.valueOf(System.currentTimeMillis()));
        MysqlGenUtils.genrate(root);
    }

}
