package cn.dalgen.mybatis.gen.model.java;

import cn.dalgen.mybatis.gen.model.java.domapper.DOMapperMethod;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by bangis.wangdf on 15/12/13. Desc
 */
public class DOMapper extends Base {

    private String xmlFileName;
    private String xmlPackageName;

    public String getXmlFileName() {
        return StringUtils.isBlank(xmlFileName) ? getClassName() : xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public String getXmlPackageName() {
        return StringUtils.isBlank(xmlPackageName) ? getPackageName() : xmlPackageName;
    }

    public void setXmlPackageName(String xmlPackageName) {
        this.xmlPackageName = xmlPackageName;
    }

    /**
     * Gets class path.
     *
     * @return the class path
     */
    public String getXmlPath() {
        return StringUtils.replace(getXmlPackageName(), ".", "/");
    }

    /**
     * The Motheds.
     */
    private List<DOMapperMethod> motheds = Lists.newArrayList();

    /**
     * Gets motheds.
     *
     * @return the motheds
     */
    public List<DOMapperMethod> getMotheds() {
        return motheds;
    }

    /**
     * Add mothed.
     *
     * @param mothed the mothed
     */
    public void addMothed(DOMapperMethod mothed) {
        mothed.setDoMapper(this);
        this.motheds.add(mothed);
    }
}
