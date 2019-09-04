package com.gld.jee.controllers;

import response.BaseResponse;
import response.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

/**
 * @author qiuzx@gld.com
 * @Description: 响应测试
 * Created by qiuzx on 2019-09-03.
 * @since 1.0
 */

@RestController
@Slf4j
public class BaseController {

    /**
     * 标准请求-响应数据格式
     */
    @GetMapping(value = "/response")
    @ResponseBody
    @ApiOperation("响应测试请求")
    public BaseResponse response(@ApiParam("请求参数") String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        if (name == null) {
         name="这是welcome!";
        }

        response.setData(name + " ^ ^ ");
        return response;
    }
}
