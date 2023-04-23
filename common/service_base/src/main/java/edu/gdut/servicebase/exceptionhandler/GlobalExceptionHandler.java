package edu.gdut.servicebase.exceptionhandler;

import edu.gdut.commonutils.R;
import edu.gdut.servicebase.myexception.MyExceptionTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作者: xieburou
 * 日期: 2023/4/23 14:52
 * 概括:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    统一异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("系统繁忙...");
    }

//    统一特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R arithmeticError(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("算术异常");
    }

//    统一自定义异常
    @ExceptionHandler(MyExceptionTest.class)
    @ResponseBody
    public R myExceptionError(MyExceptionTest e){
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
