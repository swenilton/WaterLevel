package br.com.coffeebeans.repositorio;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorRepositorio {

	private IRepositorioDAO irepositorio;

	public ControladorRepositorio() throws Exception {
		this.irepositorio = new RepositorioDAO();
	}

	public void cadastrar(Repositorio repositorio) throws SQLException,
			RepositorioJaExistenteException, RepositorioNaoEncontradoException {
		if (repositorio == null) {
			throw new NullPointerException();
		}
		if (irepositorio.procurar(repositorio.getId()) != null) {
			throw new RepositorioJaExistenteException();
		}
		irepositorio.cadastrar(repositorio);

	}

	public ArrayList<Repositorio> listar() throws SQLException,
			ListaVaziaException {
		if (irepositorio.listar().isEmpty()) {
			throw new ListaVaziaException();
		}
		return irepositorio.listar();

	}

	public Repositorio procurar(int id) throws SQLException,
			RepositorioNaoEncontradoException {
		if (irepositorio.procurar(id) == null) {
			throw new RepositorioNaoEncontradoException();
		}

		return irepositorio.procurar(id);

	}

	public void atualizar(Repositorio repositorioNovo) throws SQLException,
			RepositorioNaoEncontradoException {
		if (repositorioNovo == null) {
			throw new NullPointerException();
		}

		if (irepositorio.procurar(repositorioNovo.getId()) == null) {
			throw new RepositorioNaoEncontradoException();

		}
		irepositorio.atualizar(repositorioNovo);
	}

	public void remover(int id) throws SQLException,
			RepositorioNaoEncontradoException {
		if (irepositorio.procurar(id) == null) {
			throw new RepositorioNaoEncontradoException();
		} else {
			irepositorio.excluir(id);
		}
	}

	
	//método ainda será codificado
	public Double calcularCapacidade(Double arg1,Double arg2) {
		// esse retorno é só para o método não dar erro
		return 0.00;
	}
}
