package com.mos.eboot.service.platform.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mos.eboot.platform.entity.UserInfo;
import com.mos.eboot.service.platform.service.IUserInfoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author zlb
 * @since 2018-09-20
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	private Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	@Resource
    private IUserInfoService userInfoService;
	
	@ApiOperation(value = "获取用户信息",notes = "根据用户名获取用户")
    @ApiImplicitParam(name = "username", value = "用户名")
    @RequestMapping(value = "get-by-name")
    public UserInfo getByUsername(@RequestParam("username")String username){
		logger.info("----------->获取用户信息");
        EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("loginAccount",username);
        return userInfoService.selectOne(entityWrapper);
    }
}
