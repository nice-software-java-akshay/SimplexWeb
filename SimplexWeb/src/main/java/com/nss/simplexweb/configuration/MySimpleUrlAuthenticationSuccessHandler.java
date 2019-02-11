package com.nss.simplexweb.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nss.simplexweb.enums.COMPANY;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.MainCompany;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.MainComapanyService;
import com.nss.simplexweb.user.service.UserService;

@Component
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
    private UserService userService;
	
	@Autowired
    private MainComapanyService mainComapnyService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("onAuthenticationSuccess");
		User user = userService.findUserByEmailIdAndIsActive(authentication.getName());
		MainCompany company = mainComapnyService.getMainComapnyInfo();
		
		System.out.println("company : " + company);
		request.getSession(true).setAttribute(USER.USER.name(), user);
		request.getSession(true).setAttribute(COMPANY.COMPANY.name(), company);
        
        //clearAuthenticationAttributes(request);
        String requestedURL = request.getRequestURI();
        
        if(!requestedURL.endsWith("login")) {
        	response.sendRedirect(requestedURL);
        }else {
        	response.sendRedirect("user/home");
        }
	}
	
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
