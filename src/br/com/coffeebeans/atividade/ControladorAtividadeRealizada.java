package br.com.coffeebeans.atividade;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.AtividadeJaExistenteException;
import br.com.coffeebeans.exception.AtividadeNaoEncontradaException;
import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;

public class ControladorAtividadeRealizada {
	private IAtividadeRealizadaDAO iAtividadeRealizada;

	public ControladorAtividadeRealizada() throws Exception {
		this.iAtividadeRealizada = new AtividadeRealizadaDAO();
	}

	public void cadastrar(AtividadeRealizada atividadeRealizada)
			throws SQLException, AtividadeJaExistenteException,
			ViolacaoChaveEstrangeiraException, AtividadeNaoEncontradaException {

		if (atividadeRealizada == null) {
			throw new NullPointerException();
		}
		if (iAtividadeRealizada.procurar(atividadeRealizada.getId()) != null) {
			throw new AtividadeJaExistenteException();
		}
		if (atividadeRealizada.getDataHoraInicio().after(
				atividadeRealizada.getDataHoraFim())
				|| atividadeRealizada.getDataHoraInicio().equals(
						atividadeRealizada.getDataHoraFim())) {
			throw new IllegalArgumentException(
					"Impossível a data hora de inicio ser maior ou igual a data hora fim");

		}
		iAtividadeRealizada.cadastrar(atividadeRealizada);
	}

	public ArrayList<AtividadeRealizada> listar() throws SQLException,
			ListaVaziaException {
		if (iAtividadeRealizada.listar().isEmpty()) {
			throw new ListaVaziaException();
		}
		return iAtividadeRealizada.listar();

	}

	public AtividadeRealizada procurar(int id) throws SQLException,
			AtividadeNaoEncontradaException {
		if (iAtividadeRealizada.procurar(id) == null) {
			throw new AtividadeNaoEncontradaException();
		}
		return iAtividadeRealizada.procurar(id);

	}

	public AtividadeRealizada procurar(String descricao) throws SQLException,
			AtividadeNaoEncontradaException {
		if (iAtividadeRealizada.procurar(descricao) == null) {
			throw new AtividadeNaoEncontradaException();
		}
		return iAtividadeRealizada.procurar(descricao);
	}

	public void atualizar(AtividadeRealizada atividadeRealizada)
			throws AtividadeNaoEncontradaException, SQLException {

		if (atividadeRealizada == null) {
			throw new NullPointerException();
		}

		if (atividadeRealizada.getDataHoraInicio().after(
				atividadeRealizada.getDataHoraFim())
				|| atividadeRealizada.getDataHoraInicio().equals(
						atividadeRealizada.getDataHoraFim())) {
			throw new IllegalArgumentException(
					"Impossível a data hora de inicio ser maior ou igual a data hora fim");

		}
		iAtividadeRealizada.atualizar(atividadeRealizada);
	}

	public void excluir(int id) throws AtividadeNaoEncontradaException,
			SQLException {
		if (iAtividadeRealizada.procurar(id) == null) {
			throw new AtividadeNaoEncontradaException();
		} else {
			iAtividadeRealizada.excluir(id);
		}
	}
}
