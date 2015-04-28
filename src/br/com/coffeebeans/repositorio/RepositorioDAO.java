package br.com.coffeebeans.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.coffeebeans.exception.RepositorioJaExistenteException;
import br.com.coffeebeans.exception.RepositorioNaoEncontradoException;
import br.com.coffeebeans.util.Conexao;

public class RepositorioDAO implements IRepositorioDAO {
	private Connection connection = null;
	private String sistema = "mysql";

	public RepositorioDAO() throws Exception {
		this.connection = Conexao.conectar(sistema);
	}

	@Override
	public void cadastrar(Repositorio repositorio) throws SQLException,
			RepositorioJaExistenteException {
		PreparedStatement stmt = null;

		try {
			if (repositorio instanceof RepositorioCircular) {
				String sql = "INSERT INTO REPOSITORIO VALUES (?,?,?,?,?,?)";

				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, repositorio.getDescricao());
				stmt.setDouble(2, repositorio.getCapacidade());
				stmt.setDouble(3, repositorio.getProfundidade());
				stmt.setDouble(4, repositorio.getLimiteMinimo());
				stmt.setDouble(5, repositorio.getLimiteMaximo());
				stmt.setDouble(6,
						((RepositorioCircular) repositorio).getDiametroMedio());
				stmt.execute();
			}

			else if (repositorio instanceof RepositorioRetangular) {

				String sql = "INSERT INTO REPOSITORIO VALUES (?,?,?,?,?,?)";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, repositorio.getDescricao());
				stmt.setDouble(2, repositorio.getCapacidade());
				stmt.setDouble(3, repositorio.getProfundidade());
				stmt.setDouble(4, repositorio.getLimiteMinimo());
				stmt.setDouble(5, repositorio.getLimiteMaximo());
				stmt.setDouble(6,
						((RepositorioRetangular) repositorio).getAreaBase());
				stmt.execute();
			}

		} catch (Exception e) {

		} finally {
			stmt.close();

		}
	}

	@Override
	public ArrayList<Repositorio> listar() throws SQLException {
		ArrayList<Repositorio> repositorios = new ArrayList<Repositorio>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Repositorio repositorio = null;

		try {

			String sql = "SELECT * FROM REPOSITORIO";
			stmt = this.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("DIAMETRO_MEDIO") == "") {
					repositorio = new RepositorioRetangular(
							rs.getString("DESCRICAO"),
							rs.getDouble("CAPACIDADE"),
							rs.getDouble("PROFUNDIDADE"),
							rs.getDouble("LIMITE_MINIMO"),
							rs.getDouble("LIMITE_MAXIMO"),
							rs.getDouble("AREA_BASE"));
					repositorio.setId(rs.getInt("ID"));
				} else {
					repositorio = new RepositorioCircular(
							rs.getString("DESCRICAO"),
							rs.getDouble("CAPACIDADE"),
							rs.getDouble("PROFUNDIDADE"),
							rs.getDouble("LIMITE_MINIMO"),
							rs.getDouble("LIMITE_MAXIMO"),
							rs.getDouble("DIAMETRO_MEDIO"));
					repositorio.setId(rs.getInt("ID"));
				}

				repositorios.add(repositorio);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

		finally {
			stmt.close();
			rs.close();
		}

		return repositorios;
	}

	@Override
	public Repositorio procurar(int id) throws SQLException {
		Repositorio repositorio = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM REPOSITORIO WHERE ID = ?";
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.getString("DIAMETRO_MEDIO") == "") {
				repositorio = new RepositorioRetangular(
						rs.getString("DESCRICAO"), rs.getDouble("CAPACIDADE"),
						rs.getDouble("PROFUNDIDADE"),
						rs.getDouble("LIMITE_MINIMO"),
						rs.getDouble("LIMITE_MAXIMO"),
						rs.getDouble("AREA_BASE"));
				repositorio.setId(rs.getInt("ID"));
			} else {
				repositorio = new RepositorioCircular(
						rs.getString("DESCRICAO"), rs.getDouble("CAPACIDADE"),
						rs.getDouble("PROFUNDIDADE"),
						rs.getDouble("LIMITE_MINIMO"),
						rs.getDouble("LIMITE_MAXIMO"),
						rs.getDouble("DIAMETRO_MEDIO"));
				repositorio.setId(rs.getInt("ID"));
			}

		} catch (SQLException e) {
		} finally {
			stmt.close();
			rs.close();
		}
		return repositorio;

	}

	@Override
	public void atualizar(Repositorio repositorio)
			throws RepositorioNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		try {

			if (repositorio instanceof RepositorioCircular) {
				String sql = "UPDATE REPOSITORIO SET DESCRICAO = ?, CAPACIDADE = ?, PROFUNDIDADE = ?, LIMITE_MINIMO = ?, LIMITE_MAXIMO = ?, DIAMETRO_MEDIO=? where ID=?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, repositorio.getDescricao());
				stmt.setDouble(2, repositorio.getCapacidade());
				stmt.setDouble(3, repositorio.getProfundidade());
				stmt.setDouble(4, repositorio.getLimiteMinimo());
				stmt.setDouble(5, repositorio.getLimiteMaximo());
				stmt.setDouble(6,
						((RepositorioCircular) repositorio).getDiametroMedio());

			} else if (repositorio instanceof RepositorioRetangular) {
				String sql = "UPDATE REPOSITORIO SET DESCRICAO = ?, CAPACIDADE = ?, MODELO = ?, PROFUNDIDADE = ?, LIMITE_MINIMO = ?, LIMITE_MAXIMO = ?, AREA_BASE=? where ID=?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, repositorio.getDescricao());
				stmt.setDouble(2, repositorio.getCapacidade());
				stmt.setDouble(3, repositorio.getProfundidade());
				stmt.setDouble(4, repositorio.getLimiteMinimo());
				stmt.setDouble(5, repositorio.getLimiteMaximo());
				stmt.setDouble(6,
						((RepositorioRetangular) repositorio).getAreaBase());
			}

			Integer resultado = stmt.executeUpdate();
			if (resultado == 0)
				throw new RepositorioNaoEncontradoException();
		} catch (SQLException e) {
		} finally {
			stmt.close();
		}
	}

	@Override
	public void excluir(int id) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM REPOSITORIO WHERE ID = ?";
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {

		} finally {
			stmt.close();
		}

	}

}
