package com.wujunshen.exception;

import com.wujunshen.vo.BaseResultVo;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-11-3 <br>
 * Time:13:42 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */
@ControllerAdvice//如果返回的为json数据或其它对象，添加该注解
@ResponseBody
public class GlobalExceptionHandler {//添加全局异常处理流程，根据需要设置需要处理的异常

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Object MethodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {  //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }

        BaseResultVo baseResultVo = new BaseResultVo();
        baseResultVo.setCode(ResultStatusCode.PARAMETER_ERROR.getCode());
        baseResultVo.setMessage(ResultStatusCode.PARAMETER_ERROR.getMessage());
        baseResultVo.setData(invalidArguments);
        return baseResultVo;
    }
}