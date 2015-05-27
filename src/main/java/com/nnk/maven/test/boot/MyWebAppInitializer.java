package com.nnk.maven.test.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class MyWebAppInitializer implements WebApplicationInitializer {

	private static Logger log = LoggerFactory.getLogger(MyWebAppInitializer.class);

	@Override
	public void onStartup(ServletContext container) {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/spring-servlet.xml");
		log.debug("******************************************************************************");
		ServletRegistration.Dynamic dispatcher = container.addServlet("spring", new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}