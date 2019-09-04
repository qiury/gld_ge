package com.gld.jee.controllers;

import com.gld.domain.User;
import com.gld.jee.utils.ValidatorUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import response.BaseResponse;
import response.StatusCode;
import com.gld.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * Created by qiuzx on 2019-09-03.
 * @Version 1.0
 * @Author qiuzx@gld.com
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    //新增
    @ApiOperation("保存用户接口")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public BaseResponse save(@RequestBody @Validated @ApiParam("用户参数") User user, BindingResult result){
        String error= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(error)){
            return new BaseResponse(StatusCode.Fail.getCode(),error);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            int id = userService.insert(user);
            response.setData(id);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "查询所有用户（无分页）", notes = "获取所有用户对象，以Json方式返回")
    @GetMapping("/list")
    @ApiResponses({
            @ApiResponse(code = 200,message = "success"),
            @ApiResponse(code = 305,message = "回话异常"),
            @ApiResponse(code = 500,message = "查询异常")
    })
    public BaseResponse listAllUser(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<User> users = userService.selectList();
            response.setData(users);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
