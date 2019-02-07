package com.nss.simplexweb;

import javax.servlet.http.HttpServletRequest;

import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;

public class SessionUtility {

	public static User getUserFromSession(HttpServletRequest request) {
		return (User) request.getSession(false).getAttribute(USER.USER.name());
	}
}
