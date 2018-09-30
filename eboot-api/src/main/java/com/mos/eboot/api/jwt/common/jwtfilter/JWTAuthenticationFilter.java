package com.mos.eboot.api.jwt.common.jwtfilter;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mos.eboot.api.jwt.common.constant.Constants;
import com.mos.eboot.api.jwt.common.security.GrantedAuthorityImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: JWTAuthenticationFilter
 * @Description: token的校验 该类继承自BasicAuthenticationFilter 在doFilterInternal方法中：
 * 				  从http头的Authorization 项读取token数据，
 * 				 然后用Jwts包提供的方法校验token的合法性。
 * 				 如果校验通过，就认为这是一个取得授权的合法请求
 * 				 注意：注册等排除了的url也会进入doFilterInternal方法中！！
 * @author Mr.zhou
 * @date 2018年9月30日 上午10:17:20
 *
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /***
      * 所有的请求都会进入
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(Constants.HEADER_AUTH);
        //没有token
        if (header == null || !header.startsWith(Constants.AUTH_HEADER_START_WITH)) {
            //FilterChain
            //注册等配置排出请求，进入对应controller
            //其余请求，返回403
            chain.doFilter(request, response);
            return;
        }
        //有token，校验token
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    /**
       * 解析token
     *
     * @param request 描述此参数
     * @return 返回 authentication
     * @date 20180831 16:14:13
     * @since v1.0
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Constants.HEADER_AUTH);
        if (token != null) {
            //token格式乱传的会直接报错500
            String userAndRole = Jwts.parser()
                    .setSigningKey(Constants.SIGNING_KEY)
                    .parseClaimsJws(token.replace(Constants.AUTH_HEADER_START_WITH, ""))
                    .getBody()
                    .getSubject();

            //token校验成功，返回用户名和权限信息，并跳转到controller请求路径
            String user = "";
            List<GrantedAuthorityImpl> role = new ArrayList<>();
            if (userAndRole != null) {
                String[] userAndRoles = userAndRole.split(",");
                int length = userAndRoles==null?0:userAndRoles.length;
                if(length > 0){
                    user = userAndRoles[0];
                }
                for(int i=1;i<length;i++){
                    role.add(new GrantedAuthorityImpl(userAndRoles[i]));
                }
                //最后一个参数是权限，一定要带入，不然方法验证权限不得行！！！
                return new UsernamePasswordAuthenticationToken(user, null, role);
            }
            return null;
        }
        return null;
    }

}
