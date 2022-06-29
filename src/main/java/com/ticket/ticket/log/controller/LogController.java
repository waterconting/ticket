package com.ticket.ticket.log.controller;

import com.ticket.ticket.common.ApiResult;
import com.ticket.ticket.log.entity.UserEntity;
import com.ticket.ticket.log.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 14:25
 */

@Api(value = "登录")
@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @GetMapping("/addUser")
    public ApiResult<Object> addUser(@RequestParam @ApiParam(required = true, value = "电话" )  String phone,
                                     @RequestParam(required = false) @ApiParam(value = "卡密" )  String stuckPassword){
        UserEntity entity = new UserEntity();
        entity.setPhone(phone);
        entity.setStuckPassword(stuckPassword);
        UserEntity save = userService.save(entity);
        return ApiResult.SUCCESS(save);
    }

    @ApiOperation("获取卡密")
    @GetMapping("/getStuckPassword")
    public ApiResult<Object> getStuckPassword(@RequestParam @ApiParam(required = true, value = "使用时间类型" )  String type,
                                              @RequestParam @ApiParam(required = true, value = "条数" )  Integer num) {
        List<String> list = userService.getStuckPassword(type, num);
        return ApiResult.SUCCESS(list);
    }

    @ApiOperation("绑定微信uid")
    @GetMapping("/bindingUid")
    public ApiResult<Object> bindingUid(@RequestParam @ApiParam(required = true, value = "微信uuid" )  String uid,
                                        @RequestParam @ApiParam(required = true, value = "手机号" )  String phone) {
        userService.bindingUid(uid, phone);
        return ApiResult.SUCCESS();
    }
}
