package br.com.coffeebeans.exception;

public class ResourceException extends Exception {

	public ResourceException(Exception e) {
	super("Erro no Resource "+e.getMessage());
	}


}
