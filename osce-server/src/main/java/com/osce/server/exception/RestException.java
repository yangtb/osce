package com.osce.server.exception;

import java.text.MessageFormat;

/**
 * @ClassName: RestException
 * @Description: 自定义rest接口业务异常
 * @Author yangtongbin
 * @Date 2019-05-01
 */
public class RestException extends RuntimeException {
	private static final long serialVersionUID = 8961593697275521755L;

	/** 错误编码 */
	String code;
	
	public RestException(String message) {
		super(message);
	}

	public RestException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public RestException(RestErrorCode exceptionEnum) {
		super(exceptionEnum.getMessage());
		this.code = exceptionEnum.getCode();
	}
	
	public RestException(String errorCode, String message, Throwable throwable) {
		super(MessageFormat.format("【errorCode：{0} message：{1}】", errorCode, message), throwable);
		this.code = errorCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
