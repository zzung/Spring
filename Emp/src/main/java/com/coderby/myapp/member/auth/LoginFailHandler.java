package com.coderby.myapp.member.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		request.getSession().setAttribute("message", exception.getMessage());
		response.sendRedirect("login?error"); //세션에 저장해놓고 리다이렉트로 보냄
		//세션에는 많이 넣어놓으면 위험함
	}
	//에러가 있다는 표시로 ?error라고 해줌
	
	
}
