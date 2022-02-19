package com.radu.configuration;

import org.springframework.context.ApplicationContextAware;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	   @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {

	        var ctx = new AnnotationConfigWebApplicationContext();
	        ctx.register(ApplicationContextAware.class);
	        ctx.register(WebApp.class);
	        ctx.setServletContext(servletContext);

	        var servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
	        servlet.setLoadOnStartup(1);
	        servlet.addMapping("/");
	    }
}

