package br.com.coffeebeans.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.fachada.Fachada;
import br.com.coffeebeans.usuario.Usuario;
import br.com.coffeebeans.usuario.UsuarioDAO;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Fachada fachada;

	/**
	 * Default constructor.
	 */
	public ServletController() {
		try {
			fachada = Fachada.getInstance();
		} catch (Exception e) {
			System.out.println("Erro ao instanciar Fachada => "
					+ e.getMessage());
		}
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) {

		String acao = request.getParameter("acao");
		try {
			if (acao == null) {
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (acao.equals("login")) {
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (acao.equals("inserirUsuario")) {
				String nome = request.getParameter("nome");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String email = request.getParameter("email");
				String ativo = request.getParameter("ativo");
				String perfil = request.getParameter("perfil");
				String telefone = request.getParameter("telefone");
				String foto = request.getParameter("foto");
				Usuario u = new Usuario(nome, login, senha, email, ativo,
						perfil);
				u.setTelefone(telefone);
				u.setFoto(foto);

				try {
					fachada.cadastrar(u);
				} catch (Exception e) {
					System.out.println("Erro ao inserir usuario => "
							+ e.getMessage());
					e.printStackTrace();
				}

				RequestDispatcher rd = request
						.getRequestDispatcher("/usuario-inserir.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					System.out
							.println("Erro ao direcionar usuario-inserir.jsp => "
									+ e.getMessage());
				} catch (IOException e) {
					System.out
							.println("Erro ao direcionar usuario-inserir.jsp => "
									+ e.getMessage());
				}
			} else {
				System.out.println("porra nenhuma");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("foi executado");

	}

	public List<Usuario> getListaUsuario() throws SQLException,
			ListaUsuarioVaziaException, RepositorioException {
		return fachada.getUsuarioLista();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

	}

}
