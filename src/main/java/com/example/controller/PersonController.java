package com.example.controller;

import org.platkmframework.annotation.Api;
import org.platkmframework.annotation.ClassMethod;
import org.platkmframework.annotation.HttpRequestMethod;
import org.platkmframework.annotation.RequestBody;
import org.platkmframework.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(path = "/person", description = "Example APIs")
public class PersonController {
	
	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	public PersonController() {
		super();
	}
	
	@ClassMethod(name="/{id}",   method = HttpRequestMethod.GET)
	public Person getPersonById(@RequestParam(name = "id") Long id) {
		Person person = new Person();
		person.setLastname("Last Name " + id);
		person.setName("Name  " + id);
				
		return person;
	}
	
	@ClassMethod(name = "/change/{name}", method = HttpRequestMethod.PUT)
	public String changePersonName(@RequestParam(name = "name") String name ) {
		return "new Name: " + name;
	}
	
	@ClassMethod(name = "/create", method = HttpRequestMethod.POST)
	public void crearPersona(@RequestBody() Person person ) {
		logger.info("Nombre de la persona : {}", person.getName() + " " + person.getLastname()); 
	}

}
