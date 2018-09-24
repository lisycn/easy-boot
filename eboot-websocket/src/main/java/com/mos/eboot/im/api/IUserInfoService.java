package com.mos.eboot.im.api;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mos.eboot.platform.entity.UserInfo;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.vo.ChartVO;

@FeignClient("boot-service")
public interface IUserInfoService {

	/**
	 * 根据用户名获取user
	 * @param username
	 * @return
	 */
    @RequestMapping(value = "/userInfo/get-by-name")
    UserInfo getByUsername(@RequestParam("username") String username);

	/**
	 * 新增/修改
	 * @param sysUser
	 * @return
	 */
    @RequestMapping(value = "api/user/save-or-update",method = RequestMethod.POST)
    ResultModel<String> saveOrUpdate(@RequestBody UserInfo sysUser);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "api/user/del",method = RequestMethod.POST)
    ResultModel<String> deleteById(@RequestParam("id") String id);
	/**
	 * getById
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "api/user/get-by-id",method = RequestMethod.GET)
    ResultModel<UserInfo> getById(@RequestParam("id")String id);

	/**
	 * 登录统计分析
	 * @return
	 */
	@RequestMapping(value = "api/user/login-count",method = RequestMethod.GET)
	ResultModel<List<ChartVO>> loginCount();

	/**
	 * 全部用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userInfo/all",method = RequestMethod.POST)
	List<UserInfo> all(UserInfo user);

}
