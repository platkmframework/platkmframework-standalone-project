package com.example.controller;

import org.platkmframework.annotation.Api;
import org.platkmframework.annotation.ClassMethod;
import org.platkmframework.annotation.HttpRequestMethod;
import org.platkmframework.annotation.RequestBody;
import org.platkmframework.annotation.RequestParam;

@Api(path = "/persona", description = "Gestión de personas")
public class PersonController {

	public PersonController() {
		super();
	}
	
	@ClassMethod(name="/{id}",   method = HttpRequestMethod.GET)
	public Persona obtenerNombrePersona(@RequestParam(name = "id") Long id) {
		Persona persona = new Persona();
		persona.setApellidos("Appellido 1");
		persona.setNombre("Nombre 1");
				
		return persona;
	}
	
	@ClassMethod(name = "/cambiar/{nombre}", method = HttpRequestMethod.PUT)
	public String cambiarNombrePersona(@RequestParam(name = "nombre") String nombre ) {
		return "Nuevo Nombre: " + nombre;
	}
	
	@ClassMethod(name = "/create", method = HttpRequestMethod.POST)
	public void crearPersona(@RequestBody() Persona persona ) {
		System.out.print(persona.getNombre() + " - " + persona.getApellidos());
	}

}
