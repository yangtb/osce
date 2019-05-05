package com.osce.server.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: PfLogoutFilter
 * @Description: 退出登录过滤器
 * @Author yangtongbin
 * @Date 2017/10/12 21:40
 */
public class PfLogoutFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(PfLogoutFilter.class);

	private RequestMatcher logoutRequestMatcher = new AntPathRequestMatcher("/logout");

	private final LogoutHandler handler;
	private final LogoutSuccessHandler logoutSuccessHandler;

	public PfLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler... handlers) {
		this.handler = new CompositeLogoutHandler(handlers);
		Assert.notNull(logoutSuccessHandler, "logoutSuccessHandler注入不能为空！");
		this.logoutSuccessHandler = logoutSuccessHandler;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (requiresLogout(request, response)) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				logger.debug("用户{}正在退出登录，并将退出到指定位置", auth);
			//退出操作
			this.handler.logout(request, response, auth);
			//退出成功操作
			logoutSuccessHandler.onLogoutSuccess(request, response, auth);

			return;
		}
		chain.doFilter(request, response);
	}

	protected boolean requiresLogout(HttpServletRequest request, HttpServletResponse response) {
		return logoutRequestMatcher.matches(request);
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.logoutRequestMatcher = new AntPathRequestMatcher(filterProcessesUrl);
	}

}
