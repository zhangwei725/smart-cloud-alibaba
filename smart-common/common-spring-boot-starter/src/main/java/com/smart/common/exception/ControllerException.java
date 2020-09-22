package com.smart.common.exception;


import com.smart.common.dto.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ControllerException extends BaseException {
    public ControllerException(ResultCodeEnum codeEnum) {
        super(codeEnum);
    }
}
