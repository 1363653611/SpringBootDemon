package com.zbcn.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/","/login") //对login 和/ 不拦截
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login") //设置登录页面
				.defaultSuccessUrl("/chat")//登录成功后跳转页面
				.permitAll()
				.and()
				.logout()
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("zbcn").password("123").roles("USER")
				.and()
				.withUser("xy").password("123").roles("USER");

	}

	@Override
	public void configure(WebSecurity web){
		//静态资源不拦截
		web.ignoring().antMatchers("/resources/static/**");
	}

	//解决：There is no PasswordEncoder mapped for the id "null"问题；只为测试，不推荐
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
