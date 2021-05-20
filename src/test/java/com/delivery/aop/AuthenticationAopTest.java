package com.delivery.aop;

import com.delivery.exception.InvalidAuthenticationException;
import com.delivery.user.Authentication;
import com.delivery.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

import static com.delivery.aop.AuthenticationAopTest.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
		TestService.class,
		AuthenticationAop.class,
})
@EnableAspectJAutoProxy // aop 프록시 생성 관련 빈을 활성화
class AuthenticationAopTest {

	@Autowired
	TestService service;

	@Test
	@DisplayName("session에 인증 정보가 없으면 InvalidAuthenticationException 발생")
	void throwInvalidAuthenticationExceptionTest() {
		assertThrows(InvalidAuthenticationException.class, () -> service.orderFood());
	}

	@Test
	void authenticateTest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession();
		session.setAttribute("auth", new Authentication("a@a", "nick", "01012341234"));

		assertDoesNotThrow(() -> service.orderFood());
	}

	@Test
	void wrongAuthenticationTypeTest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession();
		session.setAttribute("auth", new User("e@e", "pwd", "nick", "01012341234"));
		assertThrows(InvalidAuthenticationException.class, () -> service.orderFood());

		session.setAttribute("auth", null);
		assertThrows(InvalidAuthenticationException.class, () -> service.orderFood());
	}

	@Component
	static class TestService {

		@AuthenticationRequired
		public void orderFood() {
			System.out.println("order process");
		}
	}
}
