/**
 * CCC 2009-2013 All Rights Reserved.
 */
package com.ipeony.ssm;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.testng.annotations.Test;

/**
 * 
 * 功能描述： 测试能否读取到配置文件
 * @author 作者 13071472
 * @version 1.0.0
 * @date 2013年11月7日 上午10:20:56
 */

public class ResourceTest {
    @Test
    public void testResource() throws IOException {
        String resource = "conf/mybatis/mapper/dataManager_Mapper.xml";
        Resources.getResourceAsStream(resource);
        String config = "conf/mybatis/myBatis-config.xml";
        Resources.getResourceAsStream(config);
    }
}
