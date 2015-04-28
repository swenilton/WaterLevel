package br.com.coffeebeans.repositorio;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.RepositorioJaExistenteException;
import br.com.coffeebeans.exception.RepositorioNaoEncontradoException;

public interface IRepositorioDAO {

	public void cadastrar(Repositorio repositorio) throws SQLException, RepositorioJaExistenteException;

	public ArrayList<Repositorio> listar() throws SQLException,ListaVaziaException;

	public Repositorio procurar(int id) throws SQLException,RepositorioNaoEncontradoException;

	public void atualizar(Repositorio repositorio) throws RepositorioNaoEncontradoException, SQLException;

	public void excluir(int id) throws SQLException,RepositorioNaoEncontradoException;
}