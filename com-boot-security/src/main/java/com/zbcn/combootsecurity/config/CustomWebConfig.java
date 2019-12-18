package com.zbcn.combootsecurity.config;

import com.zbcn.combootsecurity.permission.CustomPermissionEvaluator;
import com.zbcn.combootsecurity.vertify.VerifyServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class CustomWebConfig {


	@Autowired
	private CustomPermissionEvaluator customPermissionEvaluator;


	@Autowired
	private DataSource dataSource;

	/**
	 * 注入验证码servlet
	 */
	@Bean
	public ServletRegistrationBean indexServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyServlet());
		registration.addUrlMappings("/getVerifyCode");
		return registration;
	}

	/**
	 * 注入自定义PermissionEvaluator
	 */
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setPermissionEvaluator(customPermissionEvaluator);
		return handler;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}

	/**
	 * 在容器中注入名为 SessionRegistry 的 Bean
	 * @return
	 */
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	/**
	 * 角色继承
	 * @return
	 */
	@Bean
	public RoleHierarchy roleHierarchy() {
		String separator = System.lineSeparator();

		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "ROLE_ADMIN > ROLE_USER " + separator + " ROLE_USER > ROLE_TOURISTS";
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}

}
