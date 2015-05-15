package br.com.coffeebeans.fachada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.RepositorioJaExistenteException;
import br.com.coffeebeans.exception.RepositorioNaoEncontradoException;
import br.com.coffeebeans.repositorio.ControladorRepositorio;
import br.com.coffeebeans.repositorio.Repositorio;
import br.com.coffeebeans.repositorio.RepositorioCircular;

public class Fachada {
	private static Fachada instance = null;
	ControladorRepositorio controladorRepositorio;

	private Fachada() throws Exception {
		this.controladorRepositorio = new ControladorRepositorio();
	}

	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();

		}
		return Fachada.instance;
	}

	public <E> void cadastrar(E element) throws SQLException,
			RepositorioJaExistenteException, RepositorioNaoEncontradoException {
		if (element instanceof Repositorio) {
			controladorRepositorio.cadastrar((Repositorio) element);
		}
	}

	public <E> void atualizar(E element) throws SQLException,
			RepositorioNaoEncontradoException {
		if (element instanceof Repositorio) {
			controladorRepositorio.atualizar((Repositorio) element);
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

}
