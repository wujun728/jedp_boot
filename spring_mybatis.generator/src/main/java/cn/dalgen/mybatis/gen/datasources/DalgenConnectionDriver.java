package cn.dalgen.mybatis.gen.datasources;

import java.sql.Connection;

import cn.dalgen.mybatis.gen.model.db.DataBase;

/**
 *
 * @author bangis.wangdf
 * @date 2017/9/13
 */
public interface DalgenConnectionDriver {
    /**
     * @param dataBase
     * @return
     */
    Connection getConnection(DataBase dataBase);
}
