package br.com.coffeebeans.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.coffeebeans.usuario.Usuario;
import br.com.coffeebeans.usuario.UsuarioDAO;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String ativo = request.getParameter("ativo");
		String perfil = request.getParameter("perfil");
		String telefone = request.getParameter("telefone");
		String foto = request.getParameter("foto");

		Usuario u = new Usuario(nome, login, senha, email, ativo, perfil);
		u.setTelefone(telefone);
		u.setFoto(foto);
		
		System.out.println(u.toString());
		try {
			UsuarioDAO udao = new UsuarioDAO();
			udao.cadastrar(u);
			System.out.println("Deu certo");
		} catch (Exception e) {
			System.out.println("deu merda => " + e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
		rd.forward(request, response);
	}

}
