package com.mos.eboot.api.jwt.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mos.eboot.api.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;

/**
 * @author 小尘哥
 */
@Service("userService")
public class UserService implements IUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    private ISysUserService sysUserService;

    public ResultModel<String> updateUser(SysUser user){
        return sysUserService.saveOrUpdate(user);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("------------>根据用户name获取SysUser");
		return sysUserService.getByUsername(username);
	}
}
