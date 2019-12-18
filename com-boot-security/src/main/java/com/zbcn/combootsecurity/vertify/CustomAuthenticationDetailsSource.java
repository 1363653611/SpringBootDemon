package com.zbcn.combootsecurity.vertify;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *  @title CustomAuthenticationDetailsSource
 *  @Description 该接口用于在Spring Security登录过程中对用户的登录信息的详细信息进行填充
 *  @author zbcn8
 *  @Date 2019/12/16 14:07
 */
@Component("authenticationDetailsSource")
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
		return new CustomWebAuthenticationDetails(request);
	}
}
