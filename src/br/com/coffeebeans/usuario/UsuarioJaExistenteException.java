package br.com.coffeebeans.usuario;

public class UsuarioJaExistenteException extends Exception {
	public UsuarioJaExistenteException() {
		super("Usuário já cadastrado");
	}
}
