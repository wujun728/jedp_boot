package db.mysql.process;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mymx.jlh
 * @version 1.0.0 2018/3/2 11:00
 * 下划转驼峰
 */
public class StringUtils {

    public static String underLineToCamel(String content,boolean firstUpperCase){
        if (content == null || content.length()==0){
            return "";
        }

        if (!isNeedChange(content)){
            return content;
        }

        String result= Stream.of(content.split("_")).map(m -> {
            String text = m;
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
            return text;
        }).collect(Collectors.joining());
        if (firstUpperCase) {
            return result.substring(0, 1).toLowerCase() + result.substring(1);
        }
        else {
            return result;
        }
    }

    private static boolean isNeedChange(String content){
        return content.contains("_");
    }

}
