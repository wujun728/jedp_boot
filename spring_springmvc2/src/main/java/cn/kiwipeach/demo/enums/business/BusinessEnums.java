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
package cn.kiwipeach.demo.enums.business;

import org.springframework.util.StringUtils;

/**
 * Create Date: 2018/01/16
 * Description: 统一返回业务状态返回枚举类
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public enum BusinessEnums {

    EMPNO_NOT_FOUND("BUS_10001", "员工信息不存在"),
    EMP_NUMBER_FORMAT("BUS_10002", "员工编号转换异常"),
    EMP_NULL_POINTER("BUS_10003", "员工信息空指针异常"),
    EMP_BUS_TEST("BUS_5555", "传枚举BUS_ENUM异常"),
    EMP_EMPTY("BUS_5555", "员工信息不能为空"),
    EMPNO_EMPTY("BUS_5555", "员工编号不能为空");
    private String code;
    private String message;

    BusinessEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 由状态码获取枚举信息
     *
     * @param code 状态码
     * @return 枚举信息
     */
    public static BusinessEnums stateOf(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("responseCodeEnum method stateOf's args can not be empty");
        }
        for (BusinessEnums rce : values()) {
            if (rce.getCode().equals(code)) {
                return rce;
            }
        }
        return null;
    }
}
