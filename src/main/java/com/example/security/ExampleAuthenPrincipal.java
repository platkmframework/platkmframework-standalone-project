package com.example.security;

import java.util.ArrayList;
import java.util.List;

import org.platkmframework.security.content.AuthenPrincipal;

public class ExampleAuthenPrincipal implements AuthenPrincipal {

	private String name;
	private List<String> credentials;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<String> credentials() {
		return credentials;
	}

	public List<String> getCredentials() {
		if(credentials == null) credentials = new ArrayList<>();
		return credentials;
	}

	public void setCredentials(List<String> credentials) {
		this.credentials = credentials;
	}

	public void setName(String name) {
		this.name = name;
	}

}
