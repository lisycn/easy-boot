package com.mos.eboot.api.jwt.common.exception;

/**
 * @ClassName: BaseException
 * @Description:基础异常类
 * @author Mr.zhou
 * @date 2018年9月30日 上午10:18:49
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -8458116729669426482L;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause,
                         boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

}
