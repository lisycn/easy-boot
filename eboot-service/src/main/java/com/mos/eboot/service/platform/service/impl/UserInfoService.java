package com.mos.eboot.service.platform.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mos.eboot.platform.entity.UserInfo;
import com.mos.eboot.service.platform.mapper.UserInfoMapper;
import com.mos.eboot.service.platform.service.IUserInfoService;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author zlb
 * @since 2018-09-20
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
	/**
	 * 获取全部用户
	 */
	@Override
	public List<UserInfo> getAll(UserInfo user) {
		EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
    	entityWrapper.orderBy("creation_time");
		entityWrapper.setEntity(user);
		return this.selectList(entityWrapper);
	}
	
}
