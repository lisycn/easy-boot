package com.mos.eboot.api.jwt.common.security;

import static java.util.Collections.emptyList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mos.eboot.api.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;


/**
* @ClassName: UserDetailsServiceImpl 
* @Description: 实现自定义用户认证接口
* @author Mr.zhou
* @date 2018年9月30日 上午10:15:07 
*
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Resource
    private ISysUserService sysUserService;

    /***
       * 获取数据库用户信息，并封装成userdetails.User返回
      *  还可以获取用户菜单权限角色等信息封装并返回
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	LOGGER.info("获取数据库用户信息");
        SysUser user = sysUserService.getByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
    }

}
