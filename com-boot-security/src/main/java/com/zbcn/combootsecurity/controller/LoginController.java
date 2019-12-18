package com.zbcn.combootsecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 如代码所示，获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
 *
 * @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
 */
@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SessionRegistry sessionRegistry;

	/**
	 * 登录后首页
	 * @return
	 */
	@RequestMapping("/")
	public String showHome() {
		//获取登录用户名
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("当前登陆用户：" + name);

		return "home.html";
	}

	/**
	 * 登录url
	 * @return
	 */
	@RequestMapping("/login")
	public String showLogin() {
		return "login.html";
	}

	@RequestMapping("/admin")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String printAdmin() {
		return "如果你看见这句话，说明你有ROLE_ADMIN角色";
	}

	//让我们修改下我们要访问的接口，@PreAuthorize("hasPermission('/admin','r')")是关键，参数1指明了访问该接口需要的url，参数2指明了访问该接口需要的权限。
	@RequestMapping("/admin/r")
	@ResponseBody
	@PreAuthorize("hasPermission('/admin','r')")
	public String printAdminR() {
		return "如果你看见这句话，说明你访问/admin路径具有r权限";
	}

	@RequestMapping("/admin/c")
	@ResponseBody
	@PreAuthorize("hasPermission('/admin','c')")
	public String printAdminC() {
		return "如果你看见这句话，说明你访问/admin路径具有c权限";
	}


	@RequestMapping("/user")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_USER')")
	public String printUser() {
		return "如果你看见这句话，说明你有ROLE_USER角色";
	}

	@RequestMapping("/login/error")
	public void loginError(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		AuthenticationException exception =
				(AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		try {
			response.getWriter().write(exception.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/login/invalid")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public String invalid() {
		return "Session 已过期，请重新登录";
	}

	/**
	 * 剔除用户
	 * sessionRegistry.getAllPrincipals(); 获取所有 principal 信息
	 * 通过 principal.getUsername 是否等于输入值，获取到指定用户的 principal
	 * sessionRegistry.getAllSessions(principal, false)获取该 principal 上的所有 session
	 * 通过 sessionInformation.expireNow() 使得 session 过期
	 * @param username
	 * @return
	 */
	@GetMapping("/kick")
	@ResponseBody
	public String removeUserSessionByUsername(@RequestParam String username) {
		int count = 0;

		// 获取session中所有的用户信息
		List<Object> users = sessionRegistry.getAllPrincipals();
		for (Object principal : users) {
			if (principal instanceof User) {
				String principalName = ((User)principal).getUsername();
				if (principalName.equals(username)) {
					// 参数二：是否包含过期的Session
					List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
					if (null != sessionsInfo && sessionsInfo.size() > 0) {
						for (SessionInformation sessionInformation : sessionsInfo) {
							sessionInformation.expireNow();
							count++;
						}
					}
				}
			}
		}
		return "操作成功，清理session共" + count + "个";
	}

	@RequestMapping("/sms/code")
	@ResponseBody
	public ResponseEntity sms(String mobile, HttpSession session) {
		int code = (int) Math.ceil(Math.random() * 9000 + 1000);

		Map<String, Object> map = new HashMap<>(16);
		map.put("mobile", mobile);
		map.put("code", code);

		session.setAttribute("smsCode", map);

		logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
		return ResponseEntity.ok(map);
	}



}
