package br.com.coffeebeans.usuario;

public class UsuarioNaoEncontradoException extends Exception {

	public UsuarioNaoEncontradoException() {
		super("O usuário não foi encontrado");
	}

}
