package com.mos.eboot.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author zhoulibo
 * 进入IM主页面入口类
 */
@Controller
public class ImCountroller {
	@RequestMapping("im")
	public String imIndex() {
		return "index";
	}
}
