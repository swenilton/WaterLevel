package br.com.coffeebeans.atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.coffeebeans.exception.BDException;
import br.com.coffeebeans.exception.DAOException;
import br.com.coffeebeans.util.Conexao;

public class AtividadeDAO implements IAtividadeDAO {

	private Connection connection = null;
	private String sistema = "mysql";

	public AtividadeDAO() throws Exception {
		this.connection = Conexao.conectar(sistema);

	}

	@Override
	public boolean existe(String descricao) throws SQLException, DAOException {
		boolean existe = false;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "SELECT * FROM ATIVIDADE WHERE DESCRICAO = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, descricao);
				rs = stmt.executeQuery();
				if (rs.next()) {
					existe = true;
				}
			} else
				throw new BDException();
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			/*
			 * if (connection != null) if (!connection.isClosed())
			 * this.connection.close();
			 */

		}

		return existe;
	}

	public void cadastrar(Atividade atividade) throws SQLException,
			DAOException {
		PreparedStatement stmt = null;

		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "INSERT INTO ATIVIDADE (DESCRICAO) VALUES (?)";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, atividade.getDescricao());
				stmt.execute();
			} else {
				throw new BDException();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			if (stmt != null)
				stmt.close();
			/*
			 * if (connection != null) if (!connection.isClosed())
			 * this.connection.close();
			 */
		}
	}

	public List<Atividade> listar() throws SQLException, DAOException {
		List<Atividade> atividades = new ArrayList<Atividade>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {

				String sql = "SELECT * FROM ATIVIDADE";
				stmt = this.connection.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Atividade atividade = new Atividade(
							rs.getString("DESCRICAO"));
					atividade.setId(rs.getInt("ID"));
					atividades.add(atividade);
				}
			} else {
				throw new BDException();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			/*
			 * if (connection != null) if (!connection.isClosed())
			 * this.connection.close();
			 */
		}
		return atividades;
	}

	public Atividade procurar(int id) throws SQLException, DAOException {
		Atividade atividade = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "SELECT * FROM ATIVIDADE WHERE ID = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next()) {
					atividade = new Atividade(rs.getString("DESCRICAO"));
					atividade.setId(rs.getInt("ID"));
				}/*
				 * 
				 * else { throw new IllegalArgumentException(
				 * "Atividade nao encontrada"); }
				 */
			} else {
				throw new BDException();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			/*
			 * if (connection != null) if (!connection.isClosed())
			 * this.connection.close();
			 */

		}
		return atividade;
	}

	public void atualizar(Atividade atividade) throws SQLException,
			DAOException {
		PreparedStatement stmt = null;

		if (atividade != null) {
			try {
				// this.connection = Conexao.conectar(sistema);
				if (connection != null) {

					String sql = "UPDATE ATIVIDADE SET DESCRICAO = ? WHERE ID = ?";
					stmt = this.connection.prepareStatement(sql);
					stmt.setString(1, atividade.getDescricao());
					stmt.setInt(2, atividade.getId());

					Integer resultado = stmt.executeUpdate();
					/*
					 * if (resultado == 0) throw new
					 * AtividadeNaoEncontradaException();
					 */
				} else {
					throw new BDException();
				}
			} catch (SQLException e) {
				throw new SQLException(e);
			} catch (Exception e) {
				throw new DAOException(e);
			} finally {
				if (stmt != null)
					stmt.close();
				/*
				 * if (connection != null) if (!connection.isClosed())
				 * this.connection.close();
				 */

			}
		}
	}

	public void excluir(int id) throws SQLException, DAOException {
		PreparedStatement stmt = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "DELETE FROM ATIVIDADE WHERE ID = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.execute();
			} else {
				throw new BDException();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			if (stmt != null)
				stmt.close();
			/*
			 * if (connection != null) if (!connection.isClosed())
			 * this.connection.close();
			 */
		}
	}
}
