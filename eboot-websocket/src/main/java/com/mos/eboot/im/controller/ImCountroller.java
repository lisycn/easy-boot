package com.mos.eboot.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImCountroller {
	@RequestMapping("im")
	public String imIndex() {
		return "index";
	}
}
