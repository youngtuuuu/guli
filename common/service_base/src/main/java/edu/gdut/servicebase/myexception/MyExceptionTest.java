package edu.gdut.servicebase.myexception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: xieburou
 * 日期: 2023/4/23 15:43
 * 概括:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExceptionTest extends RuntimeException{
    private Integer code;
    private String msg;
}
