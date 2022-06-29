package com.ticket.ticket.log.controller;

import com.ticket.ticket.common.ApiResult;
import com.ticket.ticket.log.entity.UserTwoEntity;
import com.ticket.ticket.log.service.UserTwoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 14:25
 */

@Api(value = "登录")
@RestController
@RequestMapping(value = "/logTwo")
public class LogTwoController {

    @Resource
    private UserTwoService userTwoService;

    @ApiOperation("用户注册")
    @GetMapping("/addUser")
    public ApiResult<Object> addUser(@RequestParam @ApiParam(required = true, value = "电话" )  String phone,
                                     @RequestParam(required = false) @ApiParam(value = "卡密" )  String stuckPassword){
        UserTwoEntity entity = new UserTwoEntity();
        entity.setPhone(phone);
        entity.setStuckPassword(stuckPassword);
        UserTwoEntity save = userTwoService.save(entity);
        return ApiResult.SUCCESS(save);
    }

    @ApiOperation("获取卡密")
    @GetMapping("/getStuckPassword")
    public ApiResult<Object> getStuckPassword(@RequestParam @ApiParam(required = true, value = "使用时间类型" )  String type,
                                              @RequestParam @ApiParam(required = true, value = "条数" )  Integer num) {
        List<String> list = userTwoService.getStuckPassword(type, num);
        return ApiResult.SUCCESS(list);
    }

    @ApiOperation("绑定微信uid")
    @GetMapping("/bindingUid")
    public ApiResult<Object> bindingUid(@RequestParam @ApiParam(required = true, value = "微信uid" )  String uid,
                                        @RequestParam @ApiParam(required = true, value = "手机号" )  String phone) {
        userTwoService.bindingUid(uid, phone);
        return ApiResult.SUCCESS();
    }
}
