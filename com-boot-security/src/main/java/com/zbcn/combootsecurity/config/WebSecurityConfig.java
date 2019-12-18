package com.zbcn.combootsecurity.config;

import com.zbcn.combootsecurity.handle.CustomAuthenticationFailureHandler;
import com.zbcn.combootsecurity.handle.CustomAuthenticationSuccessHandler;
import com.zbcn.combootsecurity.handle.CustomLogoutSuccessHandler;
import com.zbcn.combootsecurity.permission.*;
import com.zbcn.combootsecurity.service.CustomUserDetailsService;
import com.zbcn.combootsecurity.sms.SmsAuthenticationProvider;
import com.zbcn.combootsecurity.vertify.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 *  @title WebSecurityConfig
 *  @Description 三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Securtiy 注解。
 *  在 configure() 方法中使用 auth.userDetailsService() 方法替换掉默认的 userDetailsService
 *  @author zbcn8
 *  @Date 2019/12/15 16:57
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;


	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	private CustomLogoutSuccessHandler logoutSuccessHandler;

	/**
	 * 在customWebconfig 中初始化的
	 */
	@Autowired
	private PersistentTokenRepository persistentTokenRepository;

	/**
	 * 在customWebconfig 中初始化的
	 */
	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//这里我们还指定了密码的加密方式（5.0 版本强制要求设置），因为我们数据库是明文存储的，所以明文返回即可
//		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence charSequence, String s) {
//				return s.equals(charSequence.toString());
//			}
//		});
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 如果有允许匿名的url，填在下面
//                .antMatchers().permitAll()
				// 如果有允许匿名的url，填在下面
				.antMatchers("/getVerifyCode").permitAll() //验证码
				.antMatchers("/login/invalid").permitAll()//登录超时
				.anyRequest().authenticated()
				.and()
				// 设置登陆页
				.formLogin().loginPage("/login")
				// 设置登陆成功页
				//.defaultSuccessUrl("/").permitAll()
				// 登录失败Url
				//.failureUrl("/login/error")
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler)
				// 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
				// 指定authenticationDetailsSource
				.authenticationDetailsSource(authenticationDetailsSource)
				.permitAll()
				.and()
				//验证码过滤器:具有两个参数，作用是在参数二之前执行参数一设置的过滤器
				//.addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class)
				.logout()//默认是/logout
				.logoutUrl("/signOut") //指定为signOut
				.deleteCookies("JSESSIONID")//删除浏览器中的cookie
				.logoutSuccessHandler(logoutSuccessHandler)//以配置退出后处理的逻辑
				.permitAll()
				//自动登录（Cookie 存储）
				.and().rememberMe()
				.tokenRepository(persistentTokenRepository)// token 实例化
				.userDetailsService(userDetailsService)
				//有效时间：单位s
				//.tokenValiditySeconds(60)
				.and()//session 管理
				.sessionManagement()
				.invalidSessionUrl("/login/invalid")
				.maximumSessions(1) //最大session 数量
				// 当达到最大值时，是否保留已经登录的用户
				.maxSessionsPreventsLogin(false)
				// 当达到最大值时，旧用户被踢出后的操作
				.expiredSessionStrategy(new CustomExpiredSessionStrategy())
		.sessionRegistry(sessionRegistry)
		;

		// 关闭CSRF跨域
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置拦截忽略文件夹，可以对静态资源放行
		web.ignoring().antMatchers("/css/**", "/js/**");
	}



}
