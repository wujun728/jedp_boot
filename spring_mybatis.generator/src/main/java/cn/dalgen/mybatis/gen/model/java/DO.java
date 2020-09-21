package cn.dalgen.mybatis.gen.model.java;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by bangis.wangdf on 15/12/12. Desc
 */
public class DO extends Base{


    /**
     * The Fieldses.
     */
    private List<Filelds> fieldses    = Lists.newLinkedList();



    /**
     * Gets fieldses.
     *
     * @return the fieldses
     */
    public List<Filelds> getFieldses() {
        //对params进行排序
        //Ordering<Filelds> typeOrdering = new Ordering<Filelds>() {
        //    @Override
        //    public int compare(Filelds left, Filelds right) {
        //        if(StringUtils.equalsIgnoreCase(left.getName(), "id")){
        //            return -1;
        //        }
        //        int cr = compare(left.getJavaType(), right.getJavaType());
        //        return cr == 0 ? compare(left.getName(), right.getName()) : cr;
        //    }
        //
        //    private int compare(String left, String right) {
        //        int cr = Ints.compare(left.length(), right.length());
        //        return cr == 0 ? left.compareTo(right) : cr;
        //    }
        //
        //};
        return fieldses;
    }

    /**
     * Add fields.
     *
     * @param fields the fields
     */
    public void addFields(Filelds fields) {
        this.fieldses.add(fields);
    }


}
