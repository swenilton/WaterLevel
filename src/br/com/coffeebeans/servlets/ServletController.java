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
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sun.java2d.pipe.BufferedContext;
import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.exception.UsuarioInativoException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.fachada.Fachada;
import br.com.coffeebeans.usuario.Usuario;
import br.com.coffeebeans.usuario.UsuarioDAO;
import br.com.coffeebeans.util.Erro;

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
	private int idUsuarioAlterar = 0;

	/**
	 * Default constructor
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
		Erro erros = new Erro();
		String url = "";

		if (acao == null) {
			url = "/index.jsp";
		} else if (acao.equals("login")) {
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			try {
				if (fachada.login(usuario, senha)) {
					request.getSession().setAttribute("usuarioLogado",
							UsuarioDAO.getUsuarioLogado());
					url = "/home.jsp";
				} else {
					erros.add("Usuário ou senha inválidos");
					request.getSession().invalidate();
					url = "index.jsp";
				}
			} catch (UsuarioInativoException | RepositorioException
					| SQLException e) {
				erros.add(e.getMessage());
			}

		} else if (acao.equals("sair")) {
			request.getSession().invalidate();
			url = "/index.jsp";
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
				erros.add("Erro ao inserir usuario => " + e.getMessage());
			}
			url = "/usuario-inserir.jsp";
		} else if (acao.equals("nivel")) {
			System.out.println(request.getParameter("nivel"));
		} else if (acao.equals("pegarUsuario")) {
			try {
				String id = request.getParameter("id");
				Usuario u = fachada.usuarioProcurar(Integer.parseInt(id));
				request.setAttribute("usuarioProcurar", u);
				ServletOutputStream os = response.getOutputStream();
				os.print(u.getNome() + ",");
				os.print(u.getEmail() + ",");
				os.print(u.getTelefone() + ",");
				os.print(u.getLogin() + ",");
				os.print(u.getPerfil() + ",");
				os.print(u.getAtivo() + ",");
				os.print(u.getFoto() + ",");
				os.print(u.getId());
				os.flush();
				os.close();
				idUsuarioAlterar = u.getId();
				url = "/usuario.jsp";
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
			Usuario u = new Usuario(nome, login, senha, email, ativo, perfil);
			u.setTelefone(telefone);
			u.setId(idUsuarioAlterar);
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
				erros.add("Erro ao alterar usuario => " + e.getMessage());
			}
			url = "/usuario.jsp";
		} else if (acao.equals("alterarSenha")) {
			String senhaAtual = request.getParameter("senhaAtual");
			String novaSenha = request.getParameter("novaSenha");
			try {
				if (fachada.usuarioProcurar(idUsuarioAlterar).getSenha() == senhaAtual) {
					fachada.alterarSenhaUsuario(idUsuarioAlterar, novaSenha);
				} else {
					erros.add("Senha atual inválida");
				}
				url = "/usuario.jsp";
			} catch (Exception e) {
				erros.add(e.getMessage());
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
		request.setAttribute("erros", erros);
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			System.out
					.println("erro ao direcionar página => " + e.getMessage());
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
