package br.com.coffeebeans.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.UsuarioJaExistenteException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.util.Conexao;

/*-----------------------------------------------------------------------
 *				 	MÉTODOS A SEREM IMPLEMENTADOS------------------------
 */
public class UsuarioDAO implements IUsuarioDAO {
	private Connection connection = null;
	private String sistema = "mysql";

	public UsuarioDAO() throws Exception {
		this.connection = Conexao.conectar(sistema);
	}

	@Override
	public void cadastrar(Usuario usuario) throws SQLException,
			UsuarioJaExistenteException {

	}

	@Override
	public ArrayList<Usuario> listar() throws SQLException,
			ListaUsuarioVaziaException {
		return null;
	}

	@Override
	public Usuario procurar(int id) throws SQLException {
		return null;
	}

	@Override
	public void atualizar(Usuario usuario)
			throws UsuarioNaoEncontradoException, SQLException {

	}

	@Override
	public void excluir(int id) throws SQLException,
			UsuarioNaoEncontradoException {

	}

}
