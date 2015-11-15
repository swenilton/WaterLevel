package br.com.coffeebeans.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.coffeebeans.exception.BDException;
import br.com.coffeebeans.exception.DAOException;
import br.com.coffeebeans.exception.UsuarioInativoException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.util.Conexao;
 
public class UsuarioDAO implements IUsuarioDAO {

	private Connection connection = null;
	private String sistema = "mysql";
	private static Usuario usuarioLogado;

	public UsuarioDAO() throws Exception {
		this.connection = Conexao.conectar(sistema);
	}

	@Override
	public boolean existe(String login) throws SQLException, DAOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, login);
				rs = stmt.executeQuery();
				if (rs.next()) {
					existe = true;
				} /*
				 * else { throw new
				 * IllegalArgumentException("usuario nao encontrado"); }
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
		return existe;
	}

	@Override
	public boolean existeEmail(String email) throws SQLException, DAOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "SELECT * FROM USUARIO WHERE EMAIL" + " = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, email);
				rs = stmt.executeQuery();
				if (rs.next()) {
					existe = true;
				} /*
				 * else { throw new
				 * IllegalArgumentException("usuario nao encontrado"); }
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
		return existe;
	}

	@Override
	public void cadastrar(Usuario usuario) throws SQLException, DAOException {
		PreparedStatement stmt = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "INSERT INTO USUARIO(NOME, LOGIN, SENHA, EMAIL, TELEFONE, ATIVO, FOTO, PERFIL) VALUES"
						+ " (?, ?, ?, ?, ?, ?, ?, ?)";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getLogin());
				stmt.setString(3, md5(usuario.getSenha()));
				stmt.setString(4, usuario.getEmail());
				stmt.setString(5, usuario.getTelefone());
				stmt.setString(6, usuario.getAtivo());
				stmt.setString(7, usuario.getFoto());
				stmt.setString(8, usuario.getPerfil());
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

	@Override
	public List<Usuario> getLista() throws SQLException, DAOException {
		List<Usuario> usuarios = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// this.connection = Conexao.conectar(sistema);
			usuarios = new ArrayList<Usuario>();
			String sql = "SELECT * FROM USUARIO";
			if (connection != null) {
				stmt = this.connection.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Usuario usuario = new Usuario(rs.getString("NOME"),
							rs.getString("LOGIN"), rs.getString("SENHA"),
							rs.getString("EMAIL"), rs.getString("ATIVO"),
							rs.getString("PERFIL"));
					usuario.setId(rs.getInt("ID"));
					usuario.setFoto(rs.getString("FOTO"));
					usuario.setTelefone(rs.getString("TELEFONE"));
					usuarios.add(usuario);
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
		return usuarios;
	}

	@Override
	public Usuario procurar(int id) throws SQLException, DAOException {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "SELECT * FROM USUARIO WHERE ID = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next()) {
					usuario = new Usuario(rs.getString("NOME"),
							rs.getString("LOGIN"), rs.getString("SENHA"),
							rs.getString("EMAIL"), rs.getString("ATIVO"),
							rs.getString("PERFIL"));
					usuario.setId(rs.getInt("ID"));
					usuario.setFoto(rs.getString("FOTO"));
					usuario.setTelefone(rs.getString("TELEFONE"));
				} /*
				 * else { throw new
				 * IllegalArgumentException("usuario nao encontrado"); }
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
		return usuario;
	}

	@Override
	public void atualizar(Usuario usuario)
			throws UsuarioNaoEncontradoException, SQLException, DAOException {
		PreparedStatement stmt = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "UPDATE USUARIO SET LOGIN= ?, NOME = ?, EMAIL = ?, TELEFONE = ?, FOTO = ?, ATIVO = ?, PERFIL = ? WHERE ID = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, usuario.getLogin());
				stmt.setString(2, usuario.getNome());
				stmt.setString(3, usuario.getEmail());
				stmt.setString(4, usuario.getTelefone());
				stmt.setString(5, usuario.getFoto());
				stmt.setString(6, usuario.getAtivo());
				stmt.setString(7, usuario.getPerfil());
				stmt.setInt(8, usuario.getId());
				Integer resultado = stmt.executeUpdate();
				/*
				 * if (resultado == 0) throw new
				 * UsuarioNaoEncontradoException();
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

	public void alterarSenha(int id, String senha)
			throws UsuarioNaoEncontradoException, SQLException, DAOException {
		PreparedStatement stmt = null;
		try {
			// if (id != 0) {

			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {

				String sql = "UPDATE USUARIO SET SENHA= ? WHERE ID = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, md5(senha));
				stmt.setInt(2, id);
				Integer resultado = stmt.executeUpdate();
				/*
				 * if (resultado == 0) throw new
				 * UsuarioNaoEncontradoException();
				 */
			} else {
				throw new BDException();
			}

		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		// }
		finally {
			if (stmt != null)
				stmt.close();
			/*
			 * if (connection != null) if (!connection.isClosed())
			 * this.connection.close();
			 */
		}
	}

	@Override
	public void excluir(int id) throws SQLException,
			UsuarioNaoEncontradoException, DAOException {
		PreparedStatement stmt = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "DELETE FROM USUARIO WHERE ID = ?";
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

	public boolean loginFacebook(String email) throws DAOException,
			SQLException {
		Usuario inativo = null;
		boolean result = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				String sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, email);
				rs = stmt.executeQuery();
				if (rs.next()) {
					if (rs.getString("ATIVO").equals("Nao")) {
						inativo = procurar(rs.getInt("id"));
						throw new UsuarioInativoException(
								procurar(rs.getInt("id")));
					}
					this.usuarioLogado = procurar(rs.getInt("id"));
					result= true;
				} else {
					result= false;

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
		return result;
	}

	public boolean login(String usuario, String senha)
			throws UsuarioInativoException, DAOException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "";
		Usuario inativo = null;

		try {
			if (usuario.contains("@"))
				sql = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ?";
			else
				sql = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";

			// this.connection = Conexao.conectar(sistema);
			if (connection != null) {
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, usuario);
				stmt.setString(2, md5(senha));
				rs = stmt.executeQuery();
				if (rs.next()) {
					if (rs.getString("ATIVO").equals("Nao")) {
						inativo = procurar(rs.getInt("id"));
						throw new UsuarioInativoException(
								procurar(rs.getInt("id")));
					}
					this.usuarioLogado = procurar(rs.getInt("id"));
					return true;
				} else {
					return false;
				}
			} else {
				throw new BDException();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (UsuarioInativoException e) {
			throw new UsuarioInativoException(inativo);
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
	}

	public String md5(String senha) throws DAOException {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		sen = hash.toString(16);
		return sen;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

}
