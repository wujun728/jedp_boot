package cn.dalgen.mybatis.gen.utils;

import java.io.File;
import java.io.IOException;

import cn.dalgen.mybatis.gen.model.Config;
import cn.dalgen.mybatis.gen.model.config.DeleteColumn;
import cn.dalgen.mybatis.gen.model.db.DataBase;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

/**
 * Created by bangis.wangdf on 15/12/3. Desc
 */
public class ConfigUtil {
    /**
     * The constant config.
     */
    private static Config config    = null;

    /**
     * The constant currentDb.
     */
    private static String currentDb = null;
    /**
     * The constant cmd.
     */
    private static String cmd       = null;

    public static String  dalgenPath;

    /**
     * Read config config.
     *
     * @param configFile the config file
     * @return the config
     * @throws IOException the io exception
     */
    public static Config readConfig(File configFile) throws IOException {
        if (config == null) {
            Document document = Jsoup.parse(configFile, "UTF-8");
            config = new Config();

            //typeMap
            for (Element element : document.getElementsByTag("typemap")) {
                config.addTypeMap(element.attr("from"), element.attr("to"));
            }

            //package
            Elements packagesByTag = document.getElementsByTag("package");
            Element aPackage = packagesByTag.first();
            String defaultPackage =aPackage.val();

            //索引处理
            Elements indexPrefixs = document.getElementsByTag("indexPrefix");
            if(CollectionUtils.isNotEmpty(indexPrefixs)){
                for (Element indexPrefix : indexPrefixs) {
                    config.addIndexReplaceMap(indexPrefix.val(),indexPrefix.attr("replace"));
                }
            }

            //tablePrefixs
            Elements tablePrefixsByTag = document.getElementsByTag("tablePrefixs");
            Element tablePrefix = packagesByTag.first();

            //database
            for (Element element : document.getElementsByTag("database")) {
                DataBase dataBase = new DataBase();
                dataBase.setName(element.attr("name"));
                dataBase.setDriverClass(element.attr("class"));
                dataBase.setType(element.attr("type"));
                for (Element property : element.getElementsByTag("property")) {
                    dataBase.addProperty(property.attr("name"), property.attr("value"));
                }
                //
                for (Element packageElement : packagesByTag) {
                    if(StringUtils.equals(packageElement.attr("database"),dataBase.getName())){
                        aPackage = packageElement;
                        defaultPackage =aPackage.val();
                    }
                }


                String genPackage = StringUtils.replace(defaultPackage, "${database.name}",
                        dataBase.getName());
                dataBase.setGenPackage(genPackage);
                dataBase.setGenPackagePath(StringUtils.replace(genPackage, ".", "/"));
                String genCommonPackage = StringUtils.replace(defaultPackage, "${database.name}",
                        "common");
                dataBase.setGenDalCommonPackage(genCommonPackage);
                dataBase.setGenDalCommonPackagePath(StringUtils.replace(genCommonPackage, ".", "/"));
                dataBase.setTablePath(dataBase.getName()+"Tables");

                Elements subClass = aPackage.getElementsByTag("subClass");
                if(subClass!=null && subClass.size()>0){
                    for(Element sc:subClass){
                        dataBase.addSubPackage(sc.attr("name"),
                            StringUtils.replace(sc.val(), "${database.name}",dataBase.getName()));
                    }
                }
                //
                for (Element tablePrefixElement : tablePrefixsByTag) {
                    if(StringUtils.equals(tablePrefixElement.attr("database"),dataBase.getName())){
                        tablePrefix = tablePrefixElement;
                    }
                }

                //获取需要删除或者替换的前缀
                for (Element _tablePrefix : tablePrefix.getElementsByTag("tablePrefix")) {
                    dataBase.addTablePrefixs(_tablePrefix.val(), _tablePrefix.attr("replace"));
                }

                config.addDataSource(dataBase);
            }


            //获取分表规则
            for (Element element : document.getElementsByTag("splitTableSuffix")) {
                config.addSplitTableSuffix(element.val());
            }
            //获取文件输出路径配置
            //config.addOutpath("test", "");
            for (Element element : document.getElementsByTag("outpath")) {
                config.addOutpath(element.attr("cmdName"), element.attr("path"));
            }

            //软删除字段
            Elements softDeletes = document.getElementsByTag("softDelete");
            if(softDeletes!=null && softDeletes.size()>0){
                Element element = softDeletes.get(0);
                DeleteColumn deleteColumn = new DeleteColumn();
                deleteColumn.setName(StringUtils.upperCase(element.attr("cloumn")));
                deleteColumn.setDelVal(element.attr("delVal"));
                deleteColumn.setDefVal(element.attr("defVal"));
                config.setDeleteColumn(deleteColumn);
            }

            //自定义参数
            Elements extParams = document.getElementsByTag("extParam");
            if(CollectionUtils.isNotEmpty(extParams)){
                for(Element extParam : extParams){
                    config.addExtParam(extParam.attr("name"),extParam.val());
                }
            }
        }
        return config;
    }

    /**
     * Sets current db.
     *
     * @param currentDb the current db
     */
    public static void setCurrentDb(String currentDb) {
        ConfigUtil.currentDb = currentDb;
    }

    /**
     * Sets cmd.
     *
     * @param cmd the cmd
     */
    public static void setCmd(String cmd) {
        ConfigUtil.cmd = cmd;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public static Config getConfig() {
        return config;
    }

    /**
     * Gets current db.
     *
     * @return the current db
     */
    public static DataBase getCurrentDb() {
        if (config.getDataSourceMap().size() == 1) {
            for (DataBase dataBase : config.getDataSourceMap().values()) {
                return dataBase;
            }
        }
        return config.getDataSourceMap().get(currentDb);
    }

    /**
     * Gets cmd.
     *
     * @return the cmd
     */
    public static String getCmd() {
        return cmd;
    }

}
