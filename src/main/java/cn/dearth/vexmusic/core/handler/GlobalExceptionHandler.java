package cn.dearth.vexmusic.core.handler;

import cn.dearth.vexmusic.core.exception.BizException;
import cn.dearth.vexmusic.core.exception.ErrorResponse;
import cn.dearth.vexmusic.core.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


/**
 * @author dearth
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自己认为哪里出异常的地方所加的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ErrorResponse bizExceptionHandler(BizException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(e.getCode());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTrace(e.getStackTrace());
        return errorResponse;
    }

    /**
     * 兜底的异常处理,如果系统出现了,我们没有再BizException里定义的异常
     * 就需要对一个异常做兜底处理,直接返回500
     */
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse ExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.INNER_ERROR.getCode());
        errorResponse.setMessage(ExceptionType.INNER_ERROR.getMessage());
        return errorResponse;
    }

    /**
     * spring security 的 AccessDeniedException 异常处理
     * 拒绝访问处理 角色不对就会抛出该异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.FORBIDDEN.getCode());
        errorResponse.setMessage(ExceptionType.FORBIDDEN.getMessage());
        return errorResponse;
    }

    /**
     * 也是框架层面上的注解
     * 对于@Validated表单验证注解所抛出的异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> bizExceptionHandler(MethodArgumentNotValidException e) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(objectError -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(objectError.getDefaultMessage());
            errorResponses.add(errorResponse);
        });
        return errorResponses;
    }

}
