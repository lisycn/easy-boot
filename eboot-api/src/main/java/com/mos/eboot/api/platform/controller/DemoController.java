package com.mos.eboot.api.platform.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mos.eboot.api.platform.api.IUserInfoService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;

/**
 * @author 小尘哥
 * @date 2018/5/7 11:07
 * @description
 */
@RestController
public class DemoController extends BaseController {

	@Resource
	private IUserInfoService userInfoService;
	@RequestMapping("/demo/test1")
	@ResponseBody
	public ResultModel<String> test1() {
		ResultModel<String> result = new ResultModel<String>();
//		IUser user = PrincipalUtils.getCurrentUser();
		String string = "this is test111111111";
		System.out.println(string);
		return result.defaultSuccess("string");
	}
}
