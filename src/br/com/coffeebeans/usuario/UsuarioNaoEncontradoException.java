package br.com.coffeebeans.usuario;

public class UsuarioNaoEncontradoException extends Exception {

	public UsuarioNaoEncontradoException() {
		super("O usu�rio n�o foi encontrado");
	}

}
