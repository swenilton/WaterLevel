package br.com.coffeebeans.fachada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.exception.RepositorioJaExistenteException;
import br.com.coffeebeans.exception.RepositorioNaoEncontradoException;
import br.com.coffeebeans.exception.UsuarioJaExistenteException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.repositorio.ControladorRepositorio;
import br.com.coffeebeans.repositorio.Repositorio;
import br.com.coffeebeans.repositorio.RepositorioCircular;
import br.com.coffeebeans.usuario.ControladorUsuario;
import br.com.coffeebeans.usuario.Usuario;

public class Fachada {
	private static Fachada instance = null;
	ControladorRepositorio controladorRepositorio;
	ControladorUsuario controladorUsuario;

	private Fachada() throws Exception {
		this.controladorRepositorio = new ControladorRepositorio();
		this.controladorUsuario = new ControladorUsuario();
	}

	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

	public <E> void cadastrar(E element) throws SQLException,
			RepositorioJaExistenteException, RepositorioNaoEncontradoException,
			UsuarioJaExistenteException, UsuarioNaoEncontradoException,
			RepositorioException {
		if (element instanceof Repositorio) {
			controladorRepositorio.cadastrar((Repositorio) element);
		} else if (element instanceof Usuario) {
			controladorUsuario.cadastrar((Usuario) element);
		}
	}

	public <E> void atualizar(E element) throws SQLException,
			RepositorioNaoEncontradoException, UsuarioNaoEncontradoException,
			RepositorioException {
		if (element instanceof Repositorio) {
			controladorRepositorio.atualizar((Repositorio) element);
		} else if (element instanceof Usuario) {
			controladorUsuario.atualizar((Usuario) element);
		}
	}

	public void repositorioRemover(int id) throws SQLException,
			RepositorioNaoEncontradoException {
		controladorRepositorio.remover(id);
	}

	public ArrayList<Repositorio> repositorioListar() throws SQLException,
			ListaVaziaException {
		return controladorRepositorio.listar();

	}

	public Repositorio repositorioProcurar(int id) throws SQLException,
			RepositorioNaoEncontradoException {
		return controladorRepositorio.procurar(id);
	}

	public void usuarioRemover(int id) throws SQLException,
			UsuarioNaoEncontradoException, RepositorioException {
		controladorUsuario.remover(id);
	}

	public ArrayList<Usuario> usuarioListar() throws SQLException,
			ListaUsuarioVaziaException, RepositorioException {
		return controladorUsuario.listar();

	}

	public Usuario usuariorioProcurar(int id) throws SQLException,
			UsuarioNaoEncontradoException, RepositorioException {
		return controladorUsuario.procurar(id);
	}

}
