package com.osce.exception;

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

	/** 部门管理 */
	DEPT_DEL_LIMIT("0101002001", "部门下已维护学员，不允许删除"),

	/** 房间管理 */
	ROOM_IS_EXIST("0102002001", "部门下已维护学员，不允许删除"),

	/** 学员管理 */
	USER_NAME_USED("0101003001", "该手机号已被使用"),

	// ========== end 业务具体错误编码 end ==========



	// ========== start 移动端错误编码 start ==========
	/** 简易登陆 */
	AUTH_CODE_NOT_SET("1000101001", "授权码参数未配置"),
	AUTH_CODE_ERROR("1000101002", "授权码不正确，请重新输入"),

	// ========== end 移动端错误编码 start ==========

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
