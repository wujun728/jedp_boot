package zw.cc.cn.controller;

import common.util.PropertiesUtil;
import common.variables.exception.ExceVariables;

/**
 * Created by zwz
 * 2016/8/20.
 * common.controller
 */
public class BaseResult<T>{

    public Integer stautsCode;

    public String stautsMessage;

    public T data;

    private String COMMON_PROPERTIES_FILE="common.properties";


    //成功信息
    public BaseResult() {
        this.stautsCode = ExceVariables.SUCCESS;
        this.stautsMessage = PropertiesUtil.GetValueByKey(COMMON_PROPERTIES_FILE,ExceVariables.SUCCESS + "");
    }

    public BaseResult(Integer stautsCode) {
        this.stautsCode = stautsCode;
        this.stautsMessage = PropertiesUtil.GetValueByKey(COMMON_PROPERTIES_FILE,String.valueOf(stautsCode));
    }

    public BaseResult(Integer stautsCode, String stautsMessage, T data) {
        this.stautsCode = stautsCode;
        this.stautsMessage = stautsMessage;
        this.data = data;
    }

    public void addResult(Integer stautsCode) {
        this.stautsCode = stautsCode;
        this.stautsMessage = PropertiesUtil.GetValueByKey(COMMON_PROPERTIES_FILE,String.valueOf(stautsCode));
    }

    public Integer getStautsCode() {
        return stautsCode;
    }

    public void setStautsCode(Integer stautsCode) {
        this.stautsCode = stautsCode;
    }

    public String getStautsMessage() {
        return stautsMessage;
    }

    public void setStautsMessage(String stautsMessage) {
        this.stautsMessage = stautsMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
