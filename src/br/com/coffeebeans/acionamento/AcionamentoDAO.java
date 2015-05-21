package br.com.coffeebeans.acionamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import br.com.coffeebeans.exception.AcionamentoJaExistenteException;
import br.com.coffeebeans.exception.AcionamentoNaoEncontradoException;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;

import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.util.Conexao;

public class AcionamentoDAO implements IAcionamentoDAO {
	private String sistema = "mysql";
	private Connection conexao;

	public AcionamentoDAO() throws Exception {
		this.conexao = Conexao.conectar(sistema);
	}

	@Override
	public void cadastrar(Acionamento acionamento) throws SQLException,
			AcionamentoJaExistenteException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			String sql = "INSERT INTO ACIONAMENTO(INICIO,DATA_HORA_FIM,ID_BOMBA)VALUES(?,?,?)";
			stmt = this.conexao.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setTimestamp(1, acionamento.getDataHoraInicio());
			stmt.setTimestamp(2, acionamento.getDataHoraFim());
			stmt.setInt(3, acionamento.getIdBomba());
			stmt.execute();

			if (sistema.equals("mysql")) {
				rs = stmt.getGeneratedKeys();
			}

			while (rs.next()) {

				id = rs.getInt(1);
			}
			acionamento.setId(id);
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
	public ArrayList<Acionamento> listar() throws SQLException,
			ListaVaziaException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Acionamento acionamento;
		ArrayList<Acionamento> acionamentos = null;
		try {
			String sql = "SELECT * FROM ACIONAMENTO";
			stmt = this.conexao.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				acionamento = new Acionamento(rs.getTimestamp("INICIO"),
						rs.getTimestamp("DATA_HORA_FIM"), rs.getInt("ID_BOMBA"));
				acionamentos.add(acionamento);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}
		return acionamentos;
	}

	@Override
	public Acionamento procurar(int id) throws SQLException,
			AcionamentoNaoEncontradoException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Acionamento acionamento = null;

		try {
			String sql = "SELECT * FROM ACIONAMENTO where ID=?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				acionamento = new Acionamento(rs.getTimestamp("INICIO"),
						rs.getTimestamp("DATA_HORA_FIM"), rs.getInt("ID_BOMBA"));
				acionamento.setId(rs.getInt("ID"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}
		return acionamento;
	}

	@Override
	public Acionamento procurarIni(Timestamp data1, Timestamp data2)
			throws SQLException, AcionamentoNaoEncontradoException {
		Acionamento acionamento = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM ACIONAMENTO where INICIO BETWEEN ? and ?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setTimestamp(1, data1);
			stmt.setTimestamp(2, data2);
			rs = stmt.executeQuery();

			if (rs.next()) {
				acionamento = new Acionamento(rs.getTimestamp("INICIO"),
						rs.getTimestamp("DATA_HORA_FIM"), rs.getInt("ID_BOMBA"));
				acionamento.setId(rs.getInt("ID"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}

		return acionamento;
	}

	@Override
	public Acionamento procurarFim(Timestamp data1, Timestamp data2)
			throws SQLException, AcionamentoNaoEncontradoException {
		Acionamento acionamento = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM ACIONAMENTO where DATA_HORA_FIM BETWEEN ? and ?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setTimestamp(1, data1);
			stmt.setTimestamp(2, data2);
			rs = stmt.executeQuery();

			if (rs.next()) {
				acionamento = new Acionamento(rs.getTimestamp("INICIO"),
						rs.getTimestamp("DATA_HORA_FIM"), rs.getInt("ID_BOMBA"));
				acionamento.setId(rs.getInt("ID"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
			rs.close();
		}

		return acionamento;
	}

	@Override
	public void atualizar(Acionamento acionamento)
			throws AcionamentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE ACIONAMENTO SET INICIO=?,DATA_HORA_FIM=?,ID_BOMBA=? WHERE ID=?";
			stmt = this.conexao.prepareStatement(sql);
			stmt.setTimestamp(1, acionamento.getDataHoraInicio());
			stmt.setTimestamp(2, acionamento.getDataHoraFim());
			stmt.setInt(3, acionamento.getIdBomba());
			stmt.setInt(4, acionamento.getId());

			Integer resultado = stmt.executeUpdate();
			if (resultado == 0) {
				throw new AcionamentoNaoEncontradoException();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stmt.close();
		}
	}

	@Override
	public void excluir(int id) throws SQLException,
			AcionamentoNaoEncontradoException {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM ACIONAMENTO WHERE ID=?";
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
