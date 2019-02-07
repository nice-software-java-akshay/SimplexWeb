package com.nss.simplexweb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.nss.simplexweb.configuration.SessionConfig;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SimplexWebApplication.class);
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    super.onStartup(servletContext);
	    servletContext.addListener(new SessionConfig());
	}

}
