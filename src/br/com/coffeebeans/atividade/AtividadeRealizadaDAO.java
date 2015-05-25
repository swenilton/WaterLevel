package br.com.coffeebeans.atividade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import br.com.coffeebeans.exception.AcionamentoNaoEncontradoException;
import br.com.coffeebeans.exception.AtividadeJaExistenteException;
import br.com.coffeebeans.exception.AtividadeNaoEncontradaException;
import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;
import br.com.coffeebeans.util.Conexao;

public class AtividadeRealizadaDAO implements IAtividadeRealizadaDAO {
	private String sistema = "mysql";
	private Connection conexao;

	public AtividadeRealizadaDAO() throws Exception {
		this.conexao = Conexao.conectar(sistema);

	}

	@Override
	public void cadastrar(AtividadeRealizada atividadeRealizada)
			throws SQLException, AtividadeJaExistenteException,
			ViolacaoChaveEstrangeiraException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			String sql = "INSERT INTO atividade_realizada(INICIO,FIM,ID_ATIVIDADE,ID_USUARIO)VALUES(?,?,?,?)";
			stmt = this.conexao.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setTimestamp(1, new Timestamp(atividadeRealizada
					.getDataHoraInicio().getTime()));
			stmt.setTimestamp(2, new Timestamp(atividadeRealizada
					.getDataHoraFim().getTime()));
			stmt.setInt(3, atividadeRealizada.getIdAtividade());
			stmt.setInt(4, atividadeRealizada.getIdUsuario());
			stmt.execute();

			if (sistema.equals("mysql")) {
				rs = stmt.getGeneratedKeys();
			}

			while (rs.next()) {

				id = rs.getInt(1);
			}
			atividadeRealizada.setId(id);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			ViolacaoChaveEstrangeiraException exc = new ViolacaoChaveEstrangeiraException();
			System.out.println(exc.getMessage());

			// throw new ViolacaoChaveEstrangeiraException();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}

	}

	@Override
	public ArrayList<AtividadeRealizada> listar() throws SQLException,
			ListaVaziaException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AtividadeRealizada atividadeRealizada = null;
		ArrayList<AtividadeRealizada> atividades = new ArrayList<AtividadeRealizada>();
		try {
			String sql = "SELECT * FROM ATIVIDADE_REALIZADA";
			stmt = this.conexao.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				atividadeRealizada = new AtividadeRealizada(
						rs.getTimestamp("INICIO"), rs.getTimestamp("FIM"),
						rs.getInt("ID_ATIVIDADE"), rs.getInt("ID_USUARIO"));

				atividadeRealizada.setId(rs.getInt("ID"));
				atividades.add(atividadeRealizada);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}
		return atividades;
	}

	@Override
	public AtividadeRealizada procurar(int id) throws SQLException,
			AtividadeNaoEncontradaException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AtividadeRealizada atividadeRealizada = null;

		try {
			String sql = "SELECT * FROM ATIVIDADE_REALIZADA where ID=?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				atividadeRealizada = new AtividadeRealizada(
						rs.getTimestamp("INICIO"), rs.getTimestamp("FIM"),
						rs.getInt("ID_ATIVIDADE"), rs.getInt("ID_USUARIO"));
				atividadeRealizada.setId(rs.getInt("ID"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}
		return atividadeRealizada;
	}

	@Override
	public AtividadeRealizada procurar(String descricao) throws SQLException,
			AtividadeNaoEncontradaException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AtividadeRealizada atividadeRealizada = null;

		try {
			String sql = "SELECT ar.INICIO,ar.FIM,ar.ID_ATIVIDADE,ar.ID_USUARIO FROM ATIVIDADE_REALIZADA as ar,atividade as r where a.ID=ar.id_atividade and a.descricao=?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, descricao);

			rs = stmt.executeQuery();

			if (rs.next()) {
				atividadeRealizada = new AtividadeRealizada(
						rs.getTimestamp("ar.INICIO"),
						rs.getTimestamp("ar.FIM"),
						rs.getInt("ar.ID_ATIVIDADE"),
						rs.getInt("ar.ID_USUARIO"));
				atividadeRealizada.setId(rs.getInt("ar.ID"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}
		return atividadeRealizada;

	}

	@Override
	public void atualizar(AtividadeRealizada atividadeRealizada)
			throws AtividadeNaoEncontradaException, SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE ATIVIDADE_REALIZADA SET INICIO=?,FIM=?,ID_ATIVIDADE=?,ID_USUARIO=? WHERE ID=?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setTimestamp(1, new Timestamp(atividadeRealizada
					.getDataHoraInicio().getTime()));
			stmt.setTimestamp(2, new Timestamp(atividadeRealizada
					.getDataHoraFim().getTime()));
			stmt.setInt(3, atividadeRealizada.getIdAtividade());
			stmt.setInt(4, atividadeRealizada.getIdUsuario());
			stmt.setInt(5, atividadeRealizada.getId());

			Integer resultado = stmt.executeUpdate();
			if (resultado == 0) {
				throw new AtividadeNaoEncontradaException();
			}

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			ViolacaoChaveEstrangeiraException exc = new ViolacaoChaveEstrangeiraException();
			System.out.println(exc.getMessage());

			// throw new ViolacaoChaveEstrangeiraException();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
		}

	}

	@Override
	public void excluir(int id) throws AtividadeNaoEncontradaException,
			SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM ATIVIDADE_REALIZADA WHERE ID=?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
		}

	}

}
