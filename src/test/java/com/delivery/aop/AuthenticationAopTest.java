package com.delivery.aop;

import com.delivery.exception.InvalidAuthenticationException;
import com.delivery.user.Authentication;
import com.delivery.user.AuthenticationHolder;
import com.delivery.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
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
@AutoConfigureMockMvc
class AuthenticationAopTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TestService service;

	@Test
	@DisplayName("인증 정보가 없으면 InvalidAuthenticationException 발생")
	void throwInvalidAuthenticationExceptionTest() {
		AuthenticationHolder.setAuthentication(null);
		assertThrows(InvalidAuthenticationException.class, () -> service.orderFood());
	}

	@Test
	void authenticateTest() {
		AuthenticationHolder.setAuthentication(new Authentication("a@a", "nick", "01012341234"));
		assertDoesNotThrow(() -> service.orderFood());
	}

	@Component
	static class TestService {

		@AuthenticationRequired
		public void orderFood() {
			System.out.println("order process");
		}
	}
}
