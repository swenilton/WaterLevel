package br.com.coffeebeans.usuario;

public class ListaUsuarioVaziaException extends Exception {

	public ListaUsuarioVaziaException() {
		super("A lista de usu�rios est� vazia");
	}
}
