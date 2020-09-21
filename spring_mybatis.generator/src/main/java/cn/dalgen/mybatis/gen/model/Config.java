package cn.dalgen.mybatis.gen.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.dalgen.mybatis.gen.model.config.DeleteColumn;
import cn.dalgen.mybatis.gen.model.db.DataBase;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Created by bangis.wangdf on 15/12/3. 数据源相关配置
 */
public class Config {
    /**
     * 数据源.
     */
    private Map<String, DataBase> dataSourceMap = Maps.newHashMap();

    /**
     * 数据库类型转java类型后二次转换.
     */
    private Map<String, String>   typeMap       = Maps.newHashMap();

    /**
     * 分表后缀.
     */
    private List<String> splitTableSuffixs = Lists.newArrayList();

    /**
     * 索引前缀处理
     */
    private Map<String,String> indexReplaceMap = Maps.newHashMap();

    /**
     * 文件输出路径
     */
    private Map<String, String> outpathMap  = Maps.newHashMap();
    /**
     * 扩展参数
     */
    private Map<String, String> extParamMap = Maps.newHashMap();
    {
        extParamMap.put("Repository","true");
        extParamMap.put("DoMapperName","DO"); // 废弃 by Vr80s 2019-05-23
        // 生成文件命名规则参数 by Vr80s 2019-05-23
        extParamMap.put("EntityNaming","[EntityName]DO");
        extParamMap.put("XMLMapperNaming","[EntityName]DOMapper");
        extParamMap.put("MapperInterfaceNaming","[EntityName]DOMapper");
        extParamMap.put("DaoNaming","[EntityName]DAO");
    }
    /**
     * 软删除字段
     */
    private DeleteColumn deleteColumn;

    /**
     * Add 数据源.
     *
     * @param dataBase the data base
     */
    public void addDataSource(DataBase dataBase) {
        this.dataSourceMap.put(dataBase.getName(), dataBase);
    }

    /**
     * Gets data source map.
     *
     * @return the data source map
     */
    public Map<String, DataBase> getDataSourceMap() {
        return dataSourceMap;
    }

    /**
     * Gets type map.
     *
     * @return the type map
     */
    public Map<String, String> getTypeMap() {
        return typeMap;
    }

    /**
     * Add type map.
     *
     * @param type the type
     * @param to   the to
     */
    public void addTypeMap(String type, String to) {
        this.typeMap.put(type, to);
    }

    /**
     * Gets split table suffixs.
     *
     * @return the split table suffixs
     */
    public List<String> getSplitTableSuffixs() {
        return splitTableSuffixs;
    }

    /**
     * Add split table suffix.
     *
     * @param splitTableSuffix the split table suffix
     */
    public void addSplitTableSuffix(String splitTableSuffix) {
        this.splitTableSuffixs.add(splitTableSuffix);
    }

    /**
     * 获取输出路径
     *
     * @param name the name
     * @return outpath
     */
    public String getOutpath(String name) {
        String path = outpathMap.get(name);
        if (StringUtils.isBlank(path)) {
            path = outpathMap.get("default");
        }
        return path;
    }

    /**
     * 是否自定义命令
     *
     * @param cmdName
     * @return
     */
    public boolean containCmd(String cmdName) {
        return outpathMap.containsKey(cmdName);
    }

    /**
     * 获取所有cmd命令
     *
     * @return
     */
    public List<String> cmdNames() {
        return Lists.newArrayList(outpathMap.keySet());
    }

    /**
     * 添加输出路径
     *
     * @param name the name
     * @param path the path
     */
    public void addOutpath(String name, String path) {
        outpathMap.put(name, path == null ? "" : path);
    }

    public void addExtParam(String key, String value) {
        this.extParamMap.put(key, value);
    }

    public String getExtParam(String key) {
        return extParamMap.get(key);
    }

    /**
     * Gets delete column.
     *
     * @return the delete column
     */
    public DeleteColumn getDeleteColumn() {
        return deleteColumn;
    }

    /**
     * Sets delete column.
     *
     * @param deleteColumn the delete column
     */
    public void setDeleteColumn(DeleteColumn deleteColumn) {
        this.deleteColumn = deleteColumn;
    }

    public String dealIndexName(String indexName) {
        Validate.notEmpty(indexName,"索引名不能为空");
        indexName = StringUtils.upperCase(indexName);
        for(Entry<String, String> entry : indexReplaceMap.entrySet()) {
            if(StringUtils.startsWith(indexName,entry.getKey())){
               return StringUtils.replace(indexName,entry.getKey(),entry.getValue(),1);
            }
        }
        return indexName;
    }

    public void addIndexReplaceMap(String indexPre,String replaceStr) {
        if(StringUtils.isBlank(replaceStr)){
            replaceStr = "";
        }else if(!StringUtils.endsWith(replaceStr,"_")){
            replaceStr+="_";
        }
        if(!StringUtils.endsWith(indexPre,"_")){
            indexPre+="_";
        }
        this.indexReplaceMap.put(StringUtils.upperCase(indexPre),StringUtils.upperCase(replaceStr));
    }
}
