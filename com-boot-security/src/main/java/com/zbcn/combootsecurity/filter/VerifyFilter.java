package com.zbcn.combootsecurity.filter;

import org.springframework.security.authentication.DisabledException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  @title VerifyFilter
 *  @Description 验证码过滤器
 *  自定义一个过滤器，实现 OncePerRequestFilter （该 Filter 保证每次请求一定会过滤），在 isProtectedUrl() 方法中拦截了 POST 方式的 /login 请求
 *  @author zbcn8
 *  @Date 2019/12/16 13:58
 */
public class VerifyFilter extends OncePerRequestFilter {

	private static final PathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(isProtectedUrl(request)) {
			String verifyCode = request.getParameter("verifyCode");
			if(!validateVerify(verifyCode)) {
				//手动设置异常
				request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION",new DisabledException("验证码输入错误"));
				// 转发到错误Url
				request.getRequestDispatcher("/login/error").forward(request,response);
			} else {
				filterChain.doFilter(request,response);
			}
		} else {
			filterChain.doFilter(request,response);
		}
	}

	private boolean validateVerify(String inputVerify) {
		//获取当前线程绑定的request对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 不分区大小写
		// 这个validateCode是在servlet中存入session的名字
		String validateCode = ((String) request.getSession().getAttribute("validateCode")).toLowerCase();
		inputVerify = inputVerify.toLowerCase();

		System.out.println("验证码：" + validateCode + "用户输入：" + inputVerify);
		return validateCode.equals(inputVerify);
	}

	// 拦截 /login的POST请求
	private boolean isProtectedUrl(HttpServletRequest request) {
		return "POST".equals(request.getMethod()) && pathMatcher.match("/login", request.getServletPath());
	}

}
