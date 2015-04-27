package br.com.coffeebeans.usuario;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.UsuarioJaExistenteException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;

public class ControladorUsuario {
	private IUsuarioDAO iusuario;

	public ControladorUsuario() throws Exception {
		this.iusuario = new UsuarioDAO();
	}

	public void cadastrar(Usuario usuario) throws SQLException,
			UsuarioJaExistenteException, UsuarioNaoEncontradoException {
		if (usuario == null) {
			throw new NullPointerException();
		}
		if (iusuario.procurar(usuario.getId()) != null) {
			throw new UsuarioJaExistenteException();
		}
		iusuario.cadastrar(usuario);

	}

	public ArrayList<Usuario> listar() throws SQLException,
			ListaUsuarioVaziaException {
		if (iusuario.listar().isEmpty()) {
			throw new ListaUsuarioVaziaException();
		}
		return iusuario.listar();

	}

	public Usuario procurar(int id) throws SQLException,
			UsuarioNaoEncontradoException {
		if (iusuario.procurar(id) == null) {
			throw new UsuarioNaoEncontradoException();
		}

		return iusuario.procurar(id);

	}

	public void atualizar(Usuario usuarioNovo) throws SQLException,
			UsuarioNaoEncontradoException {
		if (usuarioNovo == null) {
			throw new NullPointerException();
		}

		if (iusuario.procurar(usuarioNovo.getId()) == null) {
			throw new UsuarioNaoEncontradoException();

		}
		iusuario.atualizar(usuarioNovo);
	}

	public void remover(int id) throws SQLException,
			UsuarioNaoEncontradoException {
		if (iusuario.procurar(id) == null) {
			throw new UsuarioNaoEncontradoException();
		} else {
			iusuario.excluir(id);
		}
	}

}
