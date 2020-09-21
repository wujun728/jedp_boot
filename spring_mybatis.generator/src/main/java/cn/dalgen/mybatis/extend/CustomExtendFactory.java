/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.extend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.extend: CustomExtendFactory.java, v 0.1 2019-11-14 16:39 bangis.wangdf Exp $
 */
public class CustomExtendFactory {
    static Map<String, CustomExtendService> serviceMap = new HashMap<>();

    public static CustomExtendService getService(String cmd) {
        return serviceMap.get(cmd);
    }

    public static void register(CustomExtendService customExtendService){
        serviceMap.put(customExtendService.extendCmd(),customExtendService);
    }

    public static List<CustomExtendService> allService(){
        return Lists.newArrayList(serviceMap.values());
    }
}
