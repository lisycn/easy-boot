package com.mos.eboot.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author zhoulibo
 * 进入IM主页面入口类
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mos.eboot.tools.result.ResultModel;
@Controller
@RequestMapping("/im")
public class ImCountroller {
	@RequestMapping("index")
	public String imIndex() {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("loginUser")
	public ResultModel<String> loginUser(@RequestParam String userName,@RequestParam String password){
		ResultModel<String> result=new ResultModel<>();
		
		
		
		
		return result;
		
		
	}
}
