package cn.dearth.vexmusic.core.exception;



/**
 * @author dearth
 */
public class BizException extends RuntimeException{
    private final Integer code;

    public Integer getCode() {
        return code;
    }

    public BizException(ExceptionType exceptionType) {
        // 调用父类的构造函数,来生成运行时异常
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }
}
