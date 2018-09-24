package com.mos.eboot.service.platform.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.platform.entity.UserInfo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author zlb
 * @since 2018-09-20
 */
public interface IUserInfoService extends IService<UserInfo> {
	/**
	 * 获取全部用户
	 * @param user
	 * @return
	 */
    List<UserInfo> getAll(UserInfo user);
}
