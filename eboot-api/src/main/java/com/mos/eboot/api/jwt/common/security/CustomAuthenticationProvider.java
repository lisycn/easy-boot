package com.mos.eboot.api.jwt.common.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import com.mos.eboot.api.jwt.common.constant.Constants;
import com.mos.eboot.api.jwt.common.constant.RoleConstants;

/**
 * @ClassName: CustomAuthenticationProvider
 * @Description: AuthenticationProvider：security自定义身份认证验证组件
 * @author Mr.zhou
 * @date 2018年9月30日 上午10:15:56
 *
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /***
     *
     * 用户密码校验
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 认证逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if(null != userDetails){
            String encodePassword = DigestUtils.md5DigestAsHex((password).getBytes());
            if(userDetails.getPassword().equals(encodePassword)){
                // 这里设置权限和角色(这里模拟写死的，可以从userDetails获取getAuthorities())
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                if(Constants.ADMIN_NAME.equals(name)){
                    authorities.add( new GrantedAuthorityImpl(RoleConstants.ADMIN) );
                }else if(Constants.USER_NAME.equals(name)){
                    authorities.add( new GrantedAuthorityImpl(RoleConstants.USER) );
                }
                // 生成jwt令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
                Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
                return auth;
            }else {
            	LOGGER.info("----------->密码错误");
                throw new BadCredentialsException("密码错误");
            }
        }else {
        	LOGGER.info("----------->用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
    }

	/***
	 * 登录attemptAuthentication执行之后，会执行该方法进行认证 再执行authenticate方法，具体认证 是否可以提供输入类型的认证服务
	 * 
	 * @param authentication
	 * @return
	 */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
