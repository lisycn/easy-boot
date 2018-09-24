package com.mos.eboot.service.platform.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.platform.entity.UserInfo;
import com.mos.eboot.service.platform.service.IUserInfoService;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	@ResponseBody
    public UserInfo getByUsername(@RequestParam("username")String username){
		logger.info("----------->根据用户名字获取用户信息"+username);
        EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("login_account",username);
        return userInfoService.selectOne(entityWrapper);
    }
	@ApiOperation(value = "条件查询用户",notes = "条件查询用户")
	@RequestMapping(value = "all")
	@ResponseBody
	public List<UserInfo> all(@ApiParam @RequestBody UserInfo user){
		return userInfoService.getAll(user);
	}
}
