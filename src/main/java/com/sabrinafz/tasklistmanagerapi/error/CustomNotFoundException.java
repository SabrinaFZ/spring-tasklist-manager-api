package com.sabrinafz.tasklistmanagerapi.error;

public class CustomNotFoundException extends RuntimeException {

	public CustomNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CustomNotFoundException(String arg0) {
		super(arg0);
	}

	public CustomNotFoundException(Throwable arg0) {
		super(arg0);
	}
}
