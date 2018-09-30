package com.mos.eboot.api.jwt.common.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName: GrantedAuthorityImpl
 * @Description: 授予权限 负责存储权限和角色
 * @author Mr.zhou
 * @date 2018年9月30日 上午10:15:20
 *
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
