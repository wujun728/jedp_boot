package cn.dalgen.mybatis.gen.model.config;

import org.apache.commons.lang.StringUtils;

/**
 * 一对多子查询
 * <p/>
 *
 * @author bangis.wangdf
 * @date 16/5/14.01:28
 */
public class CfCollection {
    /**
     * 属性
     */
    private String property;

    /**
     * 从结果中指定字段作为子查询条件
     */
    private String column;

    /**
     * 调用哪个表的哪个方法<br/>
     * tableName.method
     */
    private String select;

    /**
     * 注释
     */
    private String remark;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTableName() {
        final String[] split = StringUtils.split(this.select, ".");
        if (split != null && split.length == 2) {
            return split[0];
        }
        throw new RuntimeException("Association 配置错误,到tables模板中搜一下吧");
    }

    public String getMethodName() {
        final String[] split = StringUtils.split(this.select, ".");
        if (split != null && split.length == 2) {
            return split[1];
        }
        throw new RuntimeException("Association 配置错误,到tables模板中搜一下吧");
    }
}
