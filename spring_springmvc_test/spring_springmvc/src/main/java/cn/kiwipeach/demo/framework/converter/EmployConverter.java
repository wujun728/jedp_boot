/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.framework.converter;

import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.response.Pageable;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * Create Date: 2018/01/18
 * Description: 定义分页参数转换器:目的是将把前台字符串参数转化到Pageable中去
 *
 * @author Wujun
 */
final public class EmployConverter implements Converter<String, Employ> {

    @Override
    public Employ convert(String employString) {
        employString = employString.replace("[", "");
        employString = employString.replace("]", "");
        System.out.println("获取到数据:" + employString);
        String[] empStrings = employString.split(",");
        Employ employ = new Employ();
        employ.setEmpno(new BigDecimal(empStrings[0]));
        employ.setEname(empStrings[1]);
        employ.setJob(empStrings[2]);
        return employ;
    }
}
