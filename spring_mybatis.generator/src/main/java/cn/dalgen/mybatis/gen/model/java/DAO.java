package cn.dalgen.mybatis.gen.model.java;

/**
 * Created by bangis.wangdf on 15/12/13. Desc
 */
public class DAO extends DOMapper {
    private DO aDo;
    /**
     * The Do mapper.
     */
    private DOMapper doMapper;

    /**
     * Gets do mapper.
     *
     * @return the do mapper
     */
    public DOMapper getDoMapper() {
        return doMapper;
    }

    /**
     * Sets do mapper.
     *
     * @param doMapper the do mapper
     */
    public void setDoMapper(DOMapper doMapper) {
        this.doMapper = doMapper;
    }

    public DO getaDo() {
        return aDo;
    }

    public void setaDo(DO aDo) {
        this.aDo = aDo;
    }
}
