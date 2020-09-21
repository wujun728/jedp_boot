package com.fly.code.util;

/**
 * 
 * 常用 String 工具类
 * 
 * @author 00fly
 * @version [版本号, 2017-7-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StringUtil
{
    
    private StringUtil()
    {
        super();
    }
    
    /**
     * 驼峰命名
     * 
     * @param input
     * @param firstCharacterUppercase
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String camelCase(String input, boolean firstCharacterUppercase)
    {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            switch (c)
            {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                    if (sb.length() > 0)
                    {
                        nextUpperCase = true;
                    }
                    break;
                default:
                    if (nextUpperCase)
                    {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    }
                    else
                    {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }
        if (firstCharacterUppercase)
        {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        return sb.toString();
    }
}
