package cn.dalgen.mybatis.gen.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by bangis.wangdf on 15/12/5. Desc
 */
public enum PagingCntTypeEnum {

    /**
     * 默认分页器，.
     */
    paging("paging"),
    /**
     * 解决Sql查询字段时需要回表引起的查询速度慢，主要是先获取分页数据的ID，然后在分页外面关联原表查询的场景.
     */
    optimize("optimize"),
    /**
     * 特殊分页场景，有用的distinct 结果 且还要分页的特殊场景
     */
    pagingExtCnt("pagingExtCnt"),
    /**
     * 实在没办法 上面支持 则使用自己指定分页Count 操作的分页方式  最后的底线
     */
    pagingCustom("pagingCustom");

    /**
     * The Code.
     */
    private String code;

    /**
     * Instantiates a new Multiplicity enum.
     *
     * @param code the code
     */
    private PagingCntTypeEnum(String code) {
        this.code = code;
    }

    /**
     * Gets by code.
     *
     * @param code the code
     * @return the by code
     */
    public static PagingCntTypeEnum getByCode(String code) {
        for (PagingCntTypeEnum multiplicityEnum : PagingCntTypeEnum.values()) {
            if (StringUtils.equals(code, multiplicityEnum.code)) {
                return multiplicityEnum;
            }
        }
        return PagingCntTypeEnum.paging;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
