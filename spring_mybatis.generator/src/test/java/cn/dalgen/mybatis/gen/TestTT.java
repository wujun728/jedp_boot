/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen: TestTT.java, v 0.1 2019-04-25 19:17 bangis.wangdf Exp $ 
 */
public class TestTT {
    public static void main(String[] args) {
        String pp = "order";
        String sql =" a order b order c order ";
        //sql.replaceFirst(pp,"aaa");

        Matcher matcher = Pattern.compile(pp).matcher(sql);
        matcher.reset();
        int cnt = 0;
        while (matcher.find()){
            cnt=cnt+1;
        }
        System.out.println(cnt);
        matcher.reset();
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            if(cnt==1){
                // 将匹配之前的字符串复制到sb,再将匹配结果替换为："favour"，并追加到sb
                matcher.appendReplacement(sb, "favour");
            }
            cnt=cnt-1;
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());

    }
}
