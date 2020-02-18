package com.zbcn.shiroauthentication.service;

import com.zbcn.shiroauthentication.pojo.UserOnline;

import java.util.List;

public interface SessionService {
	List<UserOnline> list();
	boolean forceLogout(String sessionId);
}
