package com.example.controller;

import org.platkmframework.annotation.Api;
import org.platkmframework.annotation.ClassMethod;
import org.platkmframework.annotation.HttpRequestMethod;
import org.platkmframework.annotation.RequestBody;
import org.platkmframework.annotation.RequestParam;

@Api(path = "/person", description = "Example APIs")
public class PersonController {

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
		System.out.print(person.getName() + " - " + person.getLastname());
	}

}
