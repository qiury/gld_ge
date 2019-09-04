package com.gld.jee.utils;

import org.springframework.validation.BindingResult;

/**
 * @Description: 请求参数统一校验工具
 * Created by qiuzx on 2019-09-03.
 * @Version 1.0
 * @Author qiuzx@gld.com
 */
public class ValidatorUtil {
    public static String checkResult(BindingResult result){
        StringBuilder sb=new StringBuilder("");
        if (result!=null && result.hasErrors()){
            //java8 stream写法
            result.getAllErrors().stream().forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        }
        return sb.toString();
    }
}
