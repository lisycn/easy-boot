package com.mos.eboot.im.api;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mos.eboot.platform.entity.UserMessage;

@FeignClient("boot-service")
public interface IUserMessageService {
	/**
	* @Title: addUserMessage 
	* @Description: 用户消息持久化到数据库
	* @param @param userMessage    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/userMessage/addUserMessage")
	void addUserMessage(@RequestBody UserMessage userMessage);

}
