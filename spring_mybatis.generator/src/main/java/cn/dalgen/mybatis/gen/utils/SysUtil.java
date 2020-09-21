package cn.dalgen.mybatis.gen.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import cn.dalgen.mybatis.gen.enums.SystemEnum;
import org.apache.commons.lang.enums.EnumUtils;
import sun.misc.BASE64Encoder;

/**
 * 获取系统信息
 * <p/>
 *
 * @author bangis.wangdf
 * @date 16/4/16.09:44
 */
public class SysUtil {
    public static SystemEnum getSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        final List<SystemEnum> enumList = EnumUtils.getEnumList(SystemEnum.class);
        for (SystemEnum systemEnum : enumList) {
            if (os.contains(systemEnum.getCode())) {
                return systemEnum;
            }
        }
        throw new RuntimeException("不支持的操作系统" + os);
    }

    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     */
    public static String EncoderByMd5(String str) {
        //确定计算方法
        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr= null;
        try {
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }
}
