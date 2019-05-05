package com.osce.service.user.security;

import com.osce.api.security.AuthorityService;
import com.osce.orm.system.authority.AuthorityDao;
import com.sm.open.care.core.enums.YesOrNoNum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Resource
	private AuthorityDao authorityDao;

	@Override
	public List<String> findAuthoritiesByUserId(Long userId, String roleType) {
		List<String> functionCodes;
		if (roleType.equals(YesOrNoNum.YES.getCode())) {
			functionCodes = authorityDao.findFunctionCodesByRoot();
		} else {
			functionCodes = authorityDao.findFunctionCodesByUserId(userId);
		}
		List<String> authorities = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(functionCodes)){
			authorities.addAll(functionCodes);
		}
		return authorities;
	}

}
