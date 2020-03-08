package com.taetaetae.springrestdocs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/docs/index.html")
public class SpringRestDocsAccessFilter implements Filter {

	@Value("${spring.profiles.active}")
	private String phase;

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException {

		if (StringUtils.equals("release", phase)) {
			HttpServletResponse response = (HttpServletResponse)servletResponse;
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			filterChain.doFilter(servletRequest, response);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}
}
