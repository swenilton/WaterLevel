package br.com.coffeebeans.repositorio;

public class ListaVaziaException extends Exception {

	public ListaVaziaException() {
		super("A lista de repositório está vazia");
	}
}
