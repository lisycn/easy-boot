package com.mos.eboot.api.jwt.common.constant;


/**
 * 常量类
 *
 * @date 20180831 16:19:19
 * @since v1.0
 */
public class Constants {

	/**
	 * 私有构造
	 *
	 * @date 20180831 16:19:25
	 * @since v1.0
	 */
    private Constants() {
    }

    /**
     * AUTH_HEADER_START_WITH 请求中token前缀
     */
    public static final String AUTH_HEADER_START_WITH = "Bearer ";

    /**
     * SIGNING_KEY token生成密钥
     */
    public static final String SIGNING_KEY = "Boss@Jwt!&Secret^#";

    /**
     * HEADER_AUTH 请求header中token的key
     */
    public static final String HEADER_AUTH = "Authorization";

    /**
     * ADMIN_NAME 模拟有ROLE_ADMIN权限的用户姓名
     */
    public static final String ADMIN_NAME = "admin";


    /**
     * USER_NAME 模拟有ROLE_USER权限的用户姓名
     */
    public static final String USER_NAME = "user";

}
