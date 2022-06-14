package cn.dearth.vexmusic.exception;

import lombok.Data;

/**
 * @author dearth
 */

@Data
public class ErrorResponse {

    private Integer code;
    private String message;
    private Object trace;
}
