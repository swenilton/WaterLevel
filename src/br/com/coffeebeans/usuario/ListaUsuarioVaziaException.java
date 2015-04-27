package br.com.coffeebeans.usuario;

public class ListaUsuarioVaziaException extends Exception {

	public ListaUsuarioVaziaException() {
		super("A lista de usuários está vazia");
	}
}
