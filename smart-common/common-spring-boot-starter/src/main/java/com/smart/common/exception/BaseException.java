package com.smart.common.exception;

import com.smart.common.dto.ResultCodeEnum;
import lombok.Getter;

/**
 * 自定义基础异常
 */
@Getter
public class BaseException extends RuntimeException {
    private final int status;

    public BaseException(ResultCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.status = codeEnum.getStatus();
    }

}
