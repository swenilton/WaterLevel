package br.com.coffeebeans.exception;

public class ListaVaziaException extends Exception {

	public ListaVaziaException() {
		super("A lista de reposit�rio est� vazia");
	}
}
