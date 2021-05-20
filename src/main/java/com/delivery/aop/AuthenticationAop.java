package com.delivery.aop;

import com.delivery.exception.InvalidAuthenticationException;
import com.delivery.user.Authentication;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AuthenticationAop {

	@Pointcut("@annotation(com.delivery.aop.AuthenticationRequired)")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void authenticate() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = attributes.getRequest().getSession();

		try {
			Authentication auth = (Authentication) session.getAttribute("auth");
			if (auth == null) {
				throw new InvalidAuthenticationException();
			}
		} catch (ClassCastException ex) {
			throw new InvalidAuthenticationException();
		}
	}
}
