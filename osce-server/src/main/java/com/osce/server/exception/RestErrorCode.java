package com.osce.server.exception;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RestErrorCode
 * @Description: 错误编码定义
 * @Author yangtongbin
 * @Date 2019-05-01
 */
public enum RestErrorCode {

	// ========== start 业务具体错误编码 start ==========

	/** 接口入参不为空校验 */
	INTERFACE_ENTRY_NOT_NULL("4000", "接口入参不能为空"),


	// ========== end 业务具体错误编码 end ==========

	/** 未知异常、错误 */
	UNKNOWN_ERROR("1000", "未知错误"),
	ACCESS_RESTRICTED_ERROR("1001", "没有访问权限"),


	/** rpc接口integration层发生异常 */
	RPC_INTEGRATION_ERROR("3000", "rpc接口integration层发生异常");


	private String code;
	private String message;

	RestErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String toJSONString() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", this.code);
		result.put("message", this.message);
		return JSON.toJSONString(result);
	}

}
