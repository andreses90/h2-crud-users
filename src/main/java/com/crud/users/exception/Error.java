package com.crud.users.exception;

public class Error {
	private String mensaje;

	public Error(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

}
