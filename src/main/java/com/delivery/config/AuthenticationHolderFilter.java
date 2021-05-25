package com.delivery.config;

import com.delivery.exception.InvalidAuthenticationException;
import com.delivery.user.Authentication;
import com.delivery.user.AuthenticationHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationHolderFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		try {
			Authentication auth = (Authentication) session.getAttribute("auth");
			AuthenticationHolder.setAuthentication(auth);
		} catch (ClassCastException ex) {
			throw new InvalidAuthenticationException();
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		AuthenticationHolder.reset();
	}
}
