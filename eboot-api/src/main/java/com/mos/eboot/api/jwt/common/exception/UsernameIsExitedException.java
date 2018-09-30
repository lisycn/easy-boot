package com.mos.eboot.api.jwt.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName: UsernameIsExitedException
 * @Description: 自定义用户认证权限方面的异常
 * @author Mr.zhou
 * @date 2018年9月30日 上午10:19:05
 *
 */
public class UsernameIsExitedException extends AuthenticationException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}