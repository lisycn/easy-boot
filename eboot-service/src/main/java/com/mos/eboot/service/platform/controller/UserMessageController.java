package com.mos.eboot.service.platform.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mos.eboot.platform.entity.UserMessage;
import com.mos.eboot.service.platform.service.IUserMessageService;

import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户发送消息记录表 前端控制器
 * </p>
 *
 * @author zlb
 * @since 2018-09-22
 */
@Controller
@RequestMapping("/userMessage")
public class UserMessageController {
	private Logger logger = LoggerFactory.getLogger(UserMessageController.class);
	@Resource
	private IUserMessageService userMessageService;
	@ApiOperation(value = "添加用户发送的消息",notes = "添加用户发送的消息")
	@RequestMapping("/addUserMessage")
	@ResponseBody
	public void addUserMessage(@RequestBody UserMessage userMessage) {
		logger.info("进入添加用户发送消息持久化");
		userMessageService.insert(userMessage);
	}
}
