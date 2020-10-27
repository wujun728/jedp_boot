package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: pojo-->接口-->接口.xml-->去applicationContext.xml绑定mapper
 * @Author: Mr.Chen
 * @CreateTime: 2020-02-28  15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    private int bookID;
    private String bookName;
    private int bookCounts;
    private String detail;

}
