package br.com.coffeebeans.atividade;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.AtividadeJaExistenteException;
import br.com.coffeebeans.exception.AtividadeNaoEncontradaException;
import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;

public interface IAtividadeRealizadaDAO {

	public void cadastrar(AtividadeRealizada atividadeRealizada)
			throws SQLException, AtividadeJaExistenteException,
			ViolacaoChaveEstrangeiraException;

	public ArrayList<AtividadeRealizada> listar() throws SQLException,
			ListaVaziaException;

	public AtividadeRealizada procurar(int id) throws SQLException,
			AtividadeNaoEncontradaException;

	public AtividadeRealizada procurar(String descricao) throws SQLException,
			AtividadeNaoEncontradaException;

	public void atualizar(AtividadeRealizada atividadeRealizada)
			throws AtividadeNaoEncontradaException, SQLException;

	public void excluir(int id) throws AtividadeNaoEncontradaException,
			SQLException;
}
