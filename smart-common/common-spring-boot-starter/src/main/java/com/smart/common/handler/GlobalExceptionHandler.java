package com.smart.common.handler;


import com.smart.common.dto.BaseResult;
import com.smart.common.dto.ResultCodeEnum;
import com.smart.common.exception.ControllerException;
import com.smart.common.exception.DaoException;
import com.smart.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.jws.WebResult;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResult<Object> handler(Exception e) {
        BaseResult<Object> baseResult = new BaseResult<>();
        baseResult.setMsg(e.getMessage());
        if (e instanceof ServiceException) {
            log.error("service:{}", e.getMessage());
            baseResult.setStatus(((ServiceException) e).getStatus());
        } else if (e instanceof ControllerException) {
            log.error("controller:{}", e.getMessage());
            baseResult.setStatus(((ControllerException) e).getStatus());
        } else if (e instanceof DaoException) {
            log.error("Dao:{}", e.getMessage());
            baseResult.setStatus(((DaoException) e).getStatus());
        }
        return baseResult;
    }

    /**
     * 处理验证bean validation的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult<Object> parameterExceptionHandler(MethodArgumentNotValidException e) {
        BaseResult<Object> baseResult = BaseResult.error();
        log.error("params: {}", e.getMessage());
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError error = (FieldError) errors.get(0);
                baseResult.setTips(error.getDefaultMessage());
                baseResult.setStatus(Integer.parseInt(Objects.requireNonNull(error.getCode())));
            }
        }
        return baseResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult<Object> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // 参数信息错误
        BaseResult<Object> baseResult = BaseResult.error(ResultCodeEnum.PARAMS_IS_INVALID);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            baseResult.setInfo(errorMessage);
            return baseResult;
        }
        baseResult.setInfo(ex.getMessage());
        return baseResult;
    }
}
