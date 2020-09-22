package com.smart.common.exception;


import com.smart.common.dto.ResultCodeEnum;
import lombok.*;

/**
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ServiceException extends RuntimeException {
    private int status;

    public ServiceException(ResultCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.status = codeEnum.getStatus();
    }
}
