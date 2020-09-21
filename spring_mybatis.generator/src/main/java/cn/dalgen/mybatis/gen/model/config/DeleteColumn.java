package cn.dalgen.mybatis.gen.model.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 软删除字段
 * <p/>
 *
 * @author bangis.wangdf
 * @date 16 /3/27.13:31
 */
public class DeleteColumn {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Del val.
     */
    private String delVal;
    /**
     * The Def val.
     */
    private String defVal;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets del val.
     *
     * @return the del val
     */
    public String getDelVal() {
        return delVal;
    }

    /**
     * Sets del val.
     *
     * @param delVal the del val
     */
    public void setDelVal(String delVal) {
        this.delVal = delVal;
    }

    /**
     * Gets def val.
     *
     * @return the def val
     */
    public String getDefVal() {
        return defVal;
    }

    /**
     * Sets def val.
     *
     * @param defVal the def val
     */
    public void setDefVal(String defVal) {
        this.defVal = defVal;
    }

    private static final Pattern pattern = Pattern.compile("\\d+");

    public boolean getIsNumber() {
        Matcher matcher = pattern.matcher(defVal);
        return matcher.find();
    }

}
