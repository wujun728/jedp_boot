package cn.dalgen.mybatis.gen.model.java;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by bangis.wangdf on 15/12/13. Desc
 */
public class Base {
    /**
     * The Class name.
     */
    private String className;

    /**
     * The Desc.
     */
    private String desc;

    /**
     * The Package name.
     */
    private String packageName;
    /**
     * The Table name.
     */
    private String tableName;


    /**
     * The Import lists.
     */
    private List<String> importLists = Lists.newArrayList();

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets class name.
     *
     * @param className the class name
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets package name.
     *
     * @param packageName the package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets class path.
     *
     * @return the class path
     */
    public String getClassPath() {
        return StringUtils.replace(packageName, ".", "/");
    }

    /**
     * Gets import lists.
     *
     * @return the import lists
     */
    public List<String> getImportLists() {
        return importLists;
    }

    /**
     * Add import list.
     *
     * @param importClass the import class
     */
    public void addImport(String importClass) {
        String _importClass = importClass;
        String __importClass;
        if (importClass.indexOf("<") > 0 && importClass.indexOf(".") > 0) {
            _importClass = importClass.substring(0, importClass.indexOf("<"));
            add2Import(_importClass);
            //处理<>内部的类
            __importClass = importClass.substring(importClass.indexOf("<") + 1, importClass.indexOf(">"));
            if (StringUtils.isNotBlank(__importClass) && __importClass.contains(".")) {
                add2Import(__importClass);
            }

        }
        if (classMap.containsKey(_importClass.toLowerCase())) {
            final String classZ = classMap.get(_importClass.toLowerCase());
            if (StringUtils.isNotBlank(classZ)) {
                if (!this.importLists.contains(classZ)) {
                    this.importLists.add(classZ);
                }
            }
        } else if (!this.importLists.contains(_importClass) && importClass.indexOf("<") < 0) {
            this.importLists.add(_importClass);
        }

    }

    private void add2Import(String classz) {
        if (StringUtils.isNotBlank(classz) && !this.importLists.contains(classz)) {
            this.importLists.add(classz);
        }
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets table name.
     *
     * @param tableName the table name
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    private static final Map<String, String> classMap = Maps.newHashMap();

    static {
        classMap.put("long", null);
        classMap.put("int", null);
        classMap.put("double", null);
        classMap.put("float", null);
        classMap.put("integer", null);
        classMap.put("boolean", null);
        classMap.put("byte", null);
        classMap.put("string", null);
        classMap.put("date", "java.util.Date");
        classMap.put("list", "java.util.List");
    }
}
