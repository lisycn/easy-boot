package com.mos.eboot.api.platform.api;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;

/**
 * @author 小尘哥
 */
@FeignClient("boot-service")
public interface ISysUserService {

	/**
	 * 根据用户名获取user
	 * @param username
	 * @return
	 */
    @RequestMapping("api/user/get-by-name")
    SysUser getByUsername(@RequestParam("username") String username);

	/**
	 * 新增/修改
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("api/user/save-or-update")
	ResultModel<String> saveOrUpdate(@RequestBody SysUser sysUser);
	/**
	* @Title: all 
	* @Description: 根据条件获取用户
	* @param @param user
	* @param @return    设定文件 
	* @return ResultModel<List<SysUser>>    返回类型 
	* @throws
	*/
	@RequestMapping(value = "api/user/all")
	ResultModel<List<SysUser>> all(@RequestBody SysUser user);
}
