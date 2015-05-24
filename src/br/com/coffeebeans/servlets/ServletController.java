package br.com.coffeebeans.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sun.java2d.pipe.BufferedContext;
import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.fachada.Fachada;
import br.com.coffeebeans.usuario.Usuario;
import br.com.coffeebeans.usuario.UsuarioDAO;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ctrl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
maxFileSize = 1024 * 1024 * 4, // 4MB
maxRequestSize = 1024 * 1024 * 4 // 4MB
)
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
			HttpServletResponse response) throws IOException {

		String acao = request.getParameter("acao");

		if (acao == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
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
			RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
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
			Usuario u = new Usuario(nome, login, senha, email, ativo, perfil);
			u.setTelefone(telefone);
			try {
				File dir = new File(System.getProperty("user.dir")
						+ "/WaterLevel/img/");
				Part filePart = request.getPart("foto");
				String fileName = filePart.getSubmittedFileName();
				File f = null;
				InputStream fileContent = null;
				if (fileName.equals("")) {
					f = new File(dir.getCanonicalPath() + "/default.jpg");
					fileContent = new FileInputStream(f);
					f = new File(dir.getCanonicalPath() + "/" + email + ".jpg");
				} else {
					fileContent = filePart.getInputStream();
					String ext[] = fileName.split("\\.");
					int i = ext.length;
					f = new File(dir.getCanonicalPath() + "/" + email + "."
							+ ext[i - 1]);
				}
				OutputStream os = new FileOutputStream(f);
				while (fileContent.available() > 0) {
					os.write(fileContent.read());
				}
				u.setFoto(f.getCanonicalPath());
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
				System.out.println("Erro ao direcionar usuario-inserir.jsp => "
						+ e.getMessage());
			} catch (IOException e) {
				System.out.println("Erro ao direcionar usuario-inserir.jsp => "
						+ e.getMessage());
			}
		} else if (acao.equals("nivel")) {
			System.out.println(request.getParameter("nivel"));
		} else if (acao.equals("lista")) {
			try {
				request.setAttribute("usuarios", fachada.getUsuarioLista());
				request.getRequestDispatcher("/usuario.jsp").forward(request,
						response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				System.out.println("Erro ao direcionar usuario-inserir.jsp => "
						+ e.getMessage());
			} catch (IOException e) {
				System.out.println("Erro ao direcionar usuario-inserir.jsp => "
						+ e.getMessage());

			}
		} else if (acao.equals("pegarUsuario")) {
			try {
				String id = request.getParameter("id");
				Usuario u = fachada.usuarioProcurar(Integer.parseInt(id));
				request.setAttribute("usuarioProcurar", u);
				response.getOutputStream().print(u.getNome() + ",");
				response.getOutputStream().print(u.getEmail() + ",");
				response.getOutputStream().print(u.getTelefone() + ",");
				response.getOutputStream().print(u.getLogin() + ",");
				response.getOutputStream().print(u.getPerfil() + ",");
				response.getOutputStream().print(u.getAtivo() + ",");
				response.getOutputStream().print(u.getFoto() + ",");
				response.getOutputStream().print(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNaoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (acao.equals("alterarUsuario")) {
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String email = request.getParameter("email");
			String ativo = request.getParameter("ativo");
			String perfil = request.getParameter("perfil");
			String telefone = request.getParameter("telefone");
			int id = Integer.parseInt(request.getParameter("id"));
			Usuario u = new Usuario(nome, login, senha, email, ativo, perfil);
			u.setTelefone(telefone);
			System.out.println("id= " + id);
			try {
				File dir = new File(System.getProperty("user.dir")
						+ "/WaterLevel/img/");
				Part filePart = request.getPart("foto");
				String fileName = filePart.getSubmittedFileName();
				File f = null;
				InputStream fileContent = null;
				if (fileName.equals("")) {
					f = new File(dir.getCanonicalPath() + "/default.jpg");
					fileContent = new FileInputStream(f);
					f = new File(dir.getCanonicalPath() + "/" + email + ".jpg");
				} else {
					fileContent = filePart.getInputStream();
					String ext[] = fileName.split("\\.");
					int i = ext.length;
					f = new File(dir.getCanonicalPath() + "/" + email + "."
							+ ext[i - 1]);
				}
				OutputStream os = new FileOutputStream(f);
				while (fileContent.available() > 0) {
					os.write(fileContent.read());
				}
				u.setFoto(f.getCanonicalPath());
				fachada.atualizar(u);
			} catch (Exception e) {
				System.out.println("Erro ao inserir usuario => "
						+ e.getMessage());
				e.printStackTrace();
			}
			RequestDispatcher rd = request
					.getRequestDispatcher("/usuario.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				System.out.println("Erro ao direcionar usuario-inserir.jsp => "
						+ e.getMessage());
			} catch (IOException e) {
				System.out.println("Erro ao direcionar usuario-inserir.jsp => "
						+ e.getMessage());
			}
		} else if (acao.equals("removerUsuario")) {
			try {
				String id = request.getParameter("id");
				fachada.usuarioRemover(Integer.parseInt(id));
				response.setStatus(200);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNaoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (acao.equals("loginFacebook")) {
			try {
				Usuario u = fachada
						.loginFacebook(request.getParameter("email"));
				if (u != null) {
					request.getSession().setAttribute("usuarioLogado", u);
					response.setStatus(200);
					request.getRequestDispatcher("/home.jsp").forward(request,
							response);
				} else {

				}
			} catch (Exception e) {
				System.out.println("Erro ao logar com facebook => "
						+ e.getMessage());
			}
		} else {
			System.out.println("porra nenhuma");
		}
		System.out.println("foi executado");

	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

	}

	public String getFileName(Part part) {
		String header = part.getHeader("content-disposition");
		for (String tmp : header.split(";")) {
			if (tmp.trim().startsWith("filename")) {
				return tmp.substring(tmp.indexOf("=") + 2, tmp.length() - 1);
			}
		}
		return null;
	}

}
