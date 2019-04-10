package com.kykj.tesla.member;

import com.kykj.tesla.platform.service.exception.BaseException;
import com.kykj.tesla.platform.service.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 基于@ExceptionHandler异常处理.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultResponse exceptionHandle(Throwable throwable) {
        logger.error("", throwable);
        ResultResponse resultResponse = new ResultResponse();
        if (throwable instanceof BaseException) {
            BaseException ex = (BaseException) throwable;
            return resultResponse.resetError(ex.getErrorMessage(), ex.getErrorCode());
        } else if (throwable instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException mane = (MethodArgumentNotValidException) throwable;
            BindingResult bindingResult = mane.getBindingResult();
            return resultResponse.resetError(bindingResult.getAllErrors().get(0).getDefaultMessage(),"-1");
        } else if (throwable instanceof BindException) {

            BindException bindException = (BindException) throwable;
            BindingResult bindingResult = bindException.getBindingResult();
            return resultResponse.resetError(bindingResult.getAllErrors().get(0).getDefaultMessage(),"-1");
        } else {
            return resultResponse.resetError( throwable.getMessage(),"-1");
        }
    }
}
