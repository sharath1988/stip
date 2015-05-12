package edu.ucsd.som.stip.app;

import java.io.IOException;

@SuppressWarnings("unused")
public class EnvironmentException extends Exception {

	private static final long serialVersionUID = 1L;

	public EnvironmentException(String message) { 
		 super(message); 
	 } 

	 public EnvironmentException(String localizedMessage, Exception e) { 
		 super(localizedMessage, e); 
	 } 
	
	  public EnvironmentException(Exception e) { 
		  super(e); 
	  } 
}
