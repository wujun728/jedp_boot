/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen.model.repository.db.database;

import java.util.HashMap;
import java.util.Map;

import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen.model.repository.db.database: DataBaseInfoHandler.java, v 0.1 2019-04-23 16:39
 * bangis.wangdf Exp $
 */
public class DataBaseInfoHandler {
    private static Map<String, DataBaseInfoService> dataBaseInfoServiceMap = new HashMap<>();

    static {
        dataBaseInfoServiceMap.put("mysql", new MySqlDataBaseInfoService());
        dataBaseInfoServiceMap.put("oracle", new OracleDataBaseInfoService());
    }

    public static DataBaseInfoService getDataBaseInfoService() {
        return dataBaseInfoServiceMap.get(StringUtils.lowerCase(ConfigUtil.getCurrentDb().getType()));
    }
}
