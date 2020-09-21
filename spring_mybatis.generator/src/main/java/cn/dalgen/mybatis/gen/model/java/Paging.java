package cn.dalgen.mybatis.gen.model.java;

import org.apache.commons.lang.StringUtils;

/**
 * Created by bangis.wangdf on 15/12/18. Desc
 */
public class Paging extends DO {
    /**
     * The Package name.
     */
    private String basePackageName;


    /**
     * The Result type.
     */
    private String resultType;

    /**
     * Gets base package name.
     *
     * @return the base package name
     */
    public String getBasePackageName() {
        return basePackageName;
    }

    /**
     * Sets base package name.
     *
     * @param basePackageName the base package name
     */
    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    /**
     * Gets base class path.
     *
     * @return the base class path
     */
    public String getBaseClassPath() {
        return StringUtils.replace(basePackageName,".","/");
    }


    /**
     * Gets result type.
     *
     * @return the result type
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * Sets result type.
     *
     * @param resultType the result type
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
