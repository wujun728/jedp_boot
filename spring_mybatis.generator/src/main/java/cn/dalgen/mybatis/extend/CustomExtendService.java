package cn.dalgen.mybatis.extend; /**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */

import java.util.Scanner;

/**
 *
 * @author bangis.wangdf
 * @version PACKAGE_NAME: cn.dalgen.mybatis.extend.CustomExtendService.java, v 0.1 2019-11-14 16:15 bangis.wangdf Exp $
 */
public interface CustomExtendService {
    /**
     * 扩展命令
     * @return
     */
    String extendCmd();

    /**
     * 扩展描述
     * @return
     */
    String extendCmdMemo();

    /**
     * 执行扩展任务
     * @param cmdIn
     * @return
     */
    String execute(Scanner cmdIn);
}
