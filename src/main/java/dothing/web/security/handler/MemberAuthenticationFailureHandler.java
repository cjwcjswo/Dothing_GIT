package dothing.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


/**
 * 스프링 시큐리티에서 로그인 실패시 호출되는 EventHandler
 * springBean 설정문서에서
 *  security:form-login 태그에 authentication-failure-handler-ref 추가하면
 * 로그인 실패시 onAuthenticationFailure 메소드 자동 호출
 * */
@Component //id="memberAuthenticationFailureHandler"
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
			throws IOException, ServletException {
		req.setAttribute("errorMessage", auth.getMessage()); // 에러메세지 추가
		req.getRequestDispatcher("/error").forward(req, res); // error페이지로 forward 형태로 이동
		
	}

	
	
}
