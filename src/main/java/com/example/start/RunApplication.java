package com.example.start;

import org.platkmframework.boot.jpa.server.runner.PlatkmFrameworkApplication;
import org.platkmframework.content.project.ProjectContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RunApplication {
	private static Logger logger = LoggerFactory.getLogger(RunApplication.class);

	public static void main(String[] args) {
		logger.info("Starting..");
		ProjectContent.instance().projectName("Example"). //change it with your project name
								  server("localhost"). // change it with your server name or ip
								  servletPath("/example"). // path for end point
		                          port("8080"). // change it with your server port
		                          IvD("com.example"); // change it with the package path, where you want IvD takes care. Example "com.example,com.controllers"
		
		PlatkmFrameworkApplication.start(args);
	}

}
