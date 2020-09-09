package com.BBS.service;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface GeneralService {


    String getUploadBasePath() throws Exception;

    Integer getCount(Map<String, String> map);
    /* < -------------  JDBC 封装常用方法 -----------------------   >*/
    List<Map<String, Object>> findForJdbc(String sql, Object... arg1);

    Map<String, Object> findOneForJdbc(String sql, Object... arg1);


    public void executeSql(String sql, Object... arg1);

    /* < -------------  JDBC 封装常用方法 -----------------------   >*/



    /* < -------------  hibernate 封装常用方法 -----------------------   >*/


        /*《--------  下面是hibernate的封装方法 ------------》  */
    //hibernate 查询封装方法
    <T> List<T> findHql(String sql, Object... args);

    //根据实体保存数据
    <T> String save(T t);
    //单表用于保存或者更新
    <T> String saveOrUpdate(Class<T> tClass, T t);

    //根据主键获取单个实体的信息
    <T> T get(Class<T> tClass, String id);

    <T> void update(T entity);
    /* < -------------  hibernate 封装常用方法 -----------------------   >*/



    /* < -------------  redis 封装常用方法 -----------------------   >*/

    //设置有过期时间的String数据
    void opsForStringSet(String key,String  value,Long time);

    //设置没过期时间的String数据
    void opsForStringSet(String key,String  value);

    //根据Key获取对象
    String opsForStringGet(String key);

    //设置有过期时间的Map数据
    void opsForMapSet(String Key, Map<? extends String , ?> map, Long time);

    Map<? extends String, ?> opsForMapGet(String Key);

    //设置有过期时间的List数据
    void opsForListSet(String Key, List<?> list,Long time);

    List<?> opsForListGet(String Key);

    //根据Key 删除redis对象
    String opsForDelKey(String Key);

//    String opsForExpire(String Key, long time);

    /* < -------------  redis 封装常用方法 -----------------------   >*/

}
