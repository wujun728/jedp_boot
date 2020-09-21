package ${packageName}.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 注解处理工具类
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AnnotationHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationHelper.class);
    
    private static List<String> cacheList = new ArrayList<>();
    
    private AnnotationHelper()
    {
        super();
    }
    
    /**
     * 得到类上面的注解信息
     * 
     * @param scannerClass
     * @param allowInjectClass
     * @return
     */
    private static Annotation getClassAnnotation(Class<?> scannerClass, Class<? extends Annotation> allowInjectClass)
    {
        if (!scannerClass.isAnnotationPresent(allowInjectClass))
        {
            return null;
        }
        return scannerClass.getAnnotation(allowInjectClass);
    }
    
    /**
     * 使用Java反射得到注解的信息
     * 
     * @param annotation
     * @param methodName
     * @return Exception
     */
    private static Object getAnnotationInfo(Annotation annotation, String methodName)
        throws Exception
    {
        if (annotation == null)
        {
            return null;
        }
        Method declaredMethod = annotation.getClass().getDeclaredMethod(methodName, null);
        return declaredMethod.invoke(annotation, null);
    }
    
    /**
     * 从上下文获取 Controller注解类的 RequestMapping注解url信息
     * 
     * @param applicationContext
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static List<String> getRequestMappingURL(ApplicationContext applicationContext)
        throws Exception
    {
        if (cacheList.isEmpty())
        {
            synchronized (AnnotationHelper.class)
            {
                List<String> list = new ArrayList<>();
                Map<String, Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
                for (String key : map.keySet())
                {
                    Class clazz = (Class)map.get(key).getClass();
                    Annotation classAnnotation = getClassAnnotation(clazz, RequestMapping.class);
                    Object object = getAnnotationInfo(classAnnotation, "value");
                    if (object != null)
                    {
                        String[] array = (String[])object;
                        LOGGER.info("{} -> {}", clazz.getName(), object);
                        list.addAll(Arrays.asList(array));
                    }
                }
                cacheList = list;
            }
        }
        return cacheList;
    }
}
