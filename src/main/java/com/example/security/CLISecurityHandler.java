package com.example.security;


import org.platkmframework.annotation.Service;
import org.platkmframework.security.content.SecurityContent;
import org.platkmframework.security.content.XSSRequestWrapper;
import org.platkmframework.security.content.handler.AuthenticationSecurityHandler;
import org.platkmframework.security.exception.AuthSecurityException;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CLISecurityHandler implements AuthenticationSecurityHandler {
	
	@Override
	public void authentication(XSSRequestWrapper req, HttpServletResponse resp) throws AuthSecurityException {
			
		ExampleAuthenPrincipal exampleAuthenPrincipal = new ExampleAuthenPrincipal();
		exampleAuthenPrincipal.setName("Example");
		exampleAuthenPrincipal.getCredentials().add("ROLE_");
			
		SecurityContent.instance().setPrincipal(exampleAuthenPrincipal);
		
	}
 

}
