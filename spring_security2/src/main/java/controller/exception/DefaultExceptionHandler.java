package controller.exception;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {
    /**
     * 没有权限 异常
     */
    @ExceptionHandler({AuthenticationException.class})
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, AuthenticationException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("/Spring-security/html/norule2.html");
        return mv;
    }

}
