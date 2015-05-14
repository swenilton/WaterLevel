package br.com.coffeebeans.usuario;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.exception.UsuarioJaExistenteException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;

public interface IUsuarioDAO {
	public void cadastrar(Usuario usuario) throws SQLException,
			UsuarioJaExistenteException;

	public ArrayList<Usuario> listar() throws SQLException,
			ListaUsuarioVaziaException;

	public Usuario procurar(int id) throws SQLException;

	public void atualizar(Usuario usuario)
			throws UsuarioNaoEncontradoException, SQLException, RepositorioException;

	public void excluir(int id) throws SQLException,
			UsuarioNaoEncontradoException;

}
