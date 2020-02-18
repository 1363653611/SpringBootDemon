package com.zbcn.shiroauthentication.service.impl;

import com.zbcn.shiroauthentication.pojo.User;
import com.zbcn.shiroauthentication.pojo.UserOnline;
import com.zbcn.shiroauthentication.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionDAO sessionDAO;
	@Override
	public List<UserOnline> list() {
		List<UserOnline> list = new ArrayList<>();
		Collection<Session> activeSessions = sessionDAO.getActiveSessions();
		for (Session session : activeSessions) {
			UserOnline userOnline = new UserOnline();
			User user = new User();
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			}else {
				principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				user = (User) principalCollection.getPrimaryPrincipal();
				userOnline.setUsername(user.getUserName());
				userOnline.setUserId(user.getId().toString());
			}
			userOnline.setId((String) session.getId());
			userOnline.setHost(session.getHost());
			userOnline.setStartTimestamp(session.getStartTimestamp());
			userOnline.setLastAccessTime(session.getLastAccessTime());
			Long timeout = session.getTimeout();
			if (timeout == 0l) {
				userOnline.setStatus("离线");
			} else {
				userOnline.setStatus("在线");
			}
			userOnline.setTimeout(timeout);
			list.add(userOnline);

		}
		return list;
	}

	@Override
	public boolean forceLogout(String sessionId) {
		Session session = sessionDAO.readSession(sessionId);
		//redis 的删除session
		sessionDAO.delete(session);
		//ehcache 删除session
		//session.setTimeout(0);
		return true;
	}
}
