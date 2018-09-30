package com.mos.eboot.api.jwt.common.jwtfilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mos.eboot.api.jwt.common.constant.Constants;
import com.mos.eboot.api.jwt.common.exception.BaseException;
import com.mos.eboot.api.jwt.common.security.GrantedAuthorityImpl;
import com.mos.eboot.platform.entity.SysUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 *  jwt登录校验
 *  登录controller方法不用自己写，直接访问/login就行
 *  该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 *
 *  验证用户名密码正确后，生成一个token，并将token返回给客户端
 *  attemptAuthentication ：接收并解析用户凭证。
 *  successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @author 周恒
 * @date 20180831 16:17:09
 * @since v1.0
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /***
     * 处理登录请求，校验用户名和密码
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException {
        try {
            SysUser user = new ObjectMapper().readValue(req.getInputStream(), SysUser.class);
            //authorities现在是空的，但是自定义权限验证的时候会设置进去
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    /***
      * 用户成功登录后，已经生成了Authentication令牌，
      * 我们在这个方法里对令牌生成token
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        List authorities = (List)auth.getAuthorities();
        int length = authorities==null?0:authorities.size();
        String subject = auth.getName();
        for(int i=0;i<length;i++){
            if(authorities.get(i) instanceof GrantedAuthorityImpl){
                subject+=","+((GrantedAuthorityImpl) authorities.get(i)).getAuthority();
            }
        }
        //subject中存入用户名和角色权限
        String token = Jwts.builder()
                //设置主题
                .setSubject(subject)
                //设置到期时间
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                //选择 加密算法和私钥
                .signWith(SignatureAlgorithm.HS512, Constants.SIGNING_KEY)
                .compact();
        //token返回到请求头中，前端在请求头中获取
        res.addHeader( Constants.HEADER_AUTH,Constants.AUTH_HEADER_START_WITH + token);
    }

}
