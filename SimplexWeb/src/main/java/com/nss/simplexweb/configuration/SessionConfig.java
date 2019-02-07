package com.nss.simplexweb.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionConfig implements HttpSessionListener {

	@Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("session created");
        event.getSession().setMaxInactiveInterval(60*30);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
       System.out.println("session destroyed");
    }
	
}
