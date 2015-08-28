package br.com.coffeebeans.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.bomba.Bomba;
import br.com.coffeebeans.exception.AtividadeNaoEncontradaException;
import br.com.coffeebeans.exception.BombaNaoEncontradaException;
import br.com.coffeebeans.exception.ListaUsuarioVaziaException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.exception.RepositorioNaoEncontradoException;
import br.com.coffeebeans.exception.UsuarioInativoException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.fachada.Fachada;
import br.com.coffeebeans.repositorio.Repositorio;
import br.com.coffeebeans.repositorio.RepositorioCircular;
import br.com.coffeebeans.repositorio.RepositorioRetangular;
import br.com.coffeebeans.usuario.Usuario;
import br.com.coffeebeans.usuario.UsuarioDAO;
import br.com.coffeebeans.util.Erro;
import br.com.coffeebeans.util.SerialTest;
import br.com.coffeebeans.util.Sucesso;

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
	private SerialTest main;

	/**
	 * Default constructor
	 */
	public ServletController() {
		try {
			fachada = Fachada.getInstance();
			/*
			main = new SerialTest();
			main.initialize();
			Thread t = new Thread() {
				public void run() {
					// the following line will keep this app alive for 1000
					// seconds,
					// waiting for events to occur and responding to them
					// (printing incoming messages to console).
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ie) {
					}
				}
			};
			t.start();
			System.out.println("Started");
			*/
		} catch (Exception e) {
			System.out.println("Erro ao instanciar Fachada => "
					+ e.getMessage());
		}
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String acao = request.getParameter("acao");
		Erro erros = new Erro();
		Sucesso sucessos = new Sucesso();
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
					url = "/index.jsp";
				}
			} catch (UsuarioInativoException e) {
				erros.add("Erro ao logar => " + e.getMessage());
				url = "/index.jsp";
			} catch (RepositorioException e) {
				erros.add("Erro ao logar => " + e.getMessage());
				url = "/index.jsp";
			} catch (SQLException e) {
				erros.add("Erro ao logar => " + e.getMessage());
				url = "/index.jsp";
			} catch (Exception e) {
				erros.add("Erro ao logar => " + e.getMessage());
				url = "/index.jsp";
			}

		} else if (acao.equals("loginFacebook")) {
			String email = request.getParameter("usuario");
			Usuario u;
			try {
				u = fachada.loginFacebook(email);
				request.getSession().setAttribute("usuarioLogado", u);
				response.setStatus(200);
			} catch (RepositorioException e) {
				erros.add("Login Inválido => " + e.getMessage());
				request.getSession().invalidate();
				response.setStatus(4);
			} catch (SQLException e) {
				erros.add("Login Inválido => " + e.getMessage());
				request.getSession().invalidate();
				response.setStatus(4);
			}
		} else if (acao.equals("sair")) {
			request.getSession().invalidate();
			for (Enumeration<String> e = request.getSession()
					.getAttributeNames(); e.hasMoreElements();) {
				String param = e.nextElement();
				param = "";
			}
			sucessos.add("Logoff com sucesso");
			url = "/index.jsp";
		} else if (acao.equals("inserirUsuario")) {
			try {
				String nome = request.getParameter("nome");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String confirmaSenha = request.getParameter("confirma-senha");
				String email = request.getParameter("email");
				String ativo = request.getParameter("ativo");
				String perfil = request.getParameter("perfil");
				String telefone = request.getParameter("telefone");
				if (!senha.equals(confirmaSenha))
					throw new IllegalArgumentException(
							"Confirmação de senha inválida");
				Usuario u = new Usuario(nome, login, senha, email, ativo,
						perfil);
				u.setTelefone(telefone);
				String arquivo = "";
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
					arquivo = "/" + email + ".jpg";
				} else {
					fileContent = filePart.getInputStream();
					String ext[] = fileName.split("\\.");
					int i = ext.length;
					f = new File(dir.getCanonicalPath() + "/" + email + "."
							+ ext[i - 1]);
					arquivo = "/" + email + "." + ext[i - 1];
				}
				OutputStream os = new FileOutputStream(f);
				while (fileContent.available() > 0) {
					os.write(fileContent.read());
				}
				u.setFoto("fotos" + arquivo);
				fachada.cadastrar(u);
				sucessos.add("Usuário inserido");
			} catch (Exception e) {
				erros.add("Erro ao inserir usuario => " + e.getMessage());
			}
			url = "/usuario-inserir.jsp";
		} else if (acao.equals("nivel")) {
			//request.setAttribute("nivel", main.getNivel());
//			ServletOutputStream os = response.getOutputStream();
//			os.print(main.getNivel());
//			os.close();
			url = "";
		} else if (acao.equals("ligaBomba")) {
			byte[] b = new byte[1];
			b[0] = '2';
			main.sendMesssage(b);
		} else if (acao.equals("desligaBomba")) {
			byte[] b = new byte[1];
			b[0] = '1';
			main.sendMesssage(b);
		} else if (acao.equals("acionamento")) {
			byte[] b = new byte[1];
			if(request.getParameter("ac").equals("auto")) {
				b[0] = '3';
			} else {
				b[0] = '4';
			}			
			main.sendMesssage(b);
		} else if (acao.equals("defLimiteMax")) {
			float limMax = Float.parseFloat(request.getParameter("limiteMax"));
			byte[] b = new byte[1];
			b[0] = '5';
			main.sendMesssage(b);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			b = Float.toString(limMax).getBytes();
			main.sendMesssage(b);
		} else if (acao.equals("pegarUsuario")) {
			try {
				String id = request.getParameter("id");
				Usuario u = fachada.usuarioProcurar(Integer.parseInt(id));
				ServletOutputStream os = response.getOutputStream();
				os.print(u.getNome() + ",");
				os.print(u.getEmail() + ",");
				os.print(u.getTelefone() + ",");
				os.print(u.getLogin() + ",");
				os.print(u.getPerfil() + ",");
				os.print(u.getAtivo() + ",");
				os.print(u.getFoto());
				os.close();
				url = "";
			} catch (SQLException e) {
				erros.add("Erro ao pegar usuario => " + e.getMessage());
			} catch (RepositorioException e) {
				erros.add("Erro ao pegar usuario => " + e.getMessage());
			} catch (NumberFormatException e) {
				erros.add("Erro ao pegar usuario => " + e.getMessage());
			} catch (UsuarioNaoEncontradoException e) {
				erros.add("Erro ao pegar usuario => " + e.getMessage());
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
			int id = Integer.parseInt(request.getParameter("id"));
			u.setId(id);
			String arquivo = "";
			try {
				File f2 = new File(
						System.getProperty("user.dir")
								+ "/WaterLevel/img/"
								+ new File(fachada.usuarioProcurar(id)
										.getFoto()).getName());
				f2.delete();
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
					arquivo = "/" + email + ".jpg";
				} else {
					fileContent = filePart.getInputStream();
					String ext[] = fileName.split("\\.");
					int i = ext.length;
					f = new File(dir.getCanonicalPath() + "/" + email + "."
							+ ext[i - 1]);
					arquivo = "/" + email + "." + ext[i - 1];
				}
				OutputStream os = new FileOutputStream(f);
				while (fileContent.available() > 0) {
					os.write(fileContent.read());
				}
				u.setFoto("fotos" + arquivo);
				fachada.atualizar(u);
				sucessos.add("Usuário alterado");
			} catch (Exception e) {
				erros.add("Erro ao alterar usuario => " + e.getMessage());
			}
			url = "/usuario.jsp";
		} else if (acao.equals("alterarSenha")) {
			String senhaAtual = request.getParameter("senhaAtual");
			String novaSenha = request.getParameter("novaSenha");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				if (fachada.usuarioProcurar(id).getSenha()
						.equals(md5(senhaAtual))) {
					fachada.alterarSenhaUsuario(id, novaSenha);
					sucessos.add("Senha alterada");
				} else {
					erros.add("Senha atual inválida");
				}
				url = "/usuario.jsp";
			} catch (Exception e) {
				erros.add(e.getMessage());
			}
		} else if (acao.equals("removerUsuario")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				File f = new File(
						System.getProperty("user.dir")
								+ "/WaterLevel/img/"
								+ new File(fachada.usuarioProcurar(id)
										.getFoto()).getName());
				f.delete();
				fachada.usuarioRemover(id);
				response.setStatus(200);
			} catch (NumberFormatException e) {
				erros.add("erro ao remover usuario = > " + e.getMessage());
			} catch (SQLException e) {
				erros.add("erro ao remover usuario = > " + e.getMessage());
			} catch (UsuarioNaoEncontradoException e) {
				erros.add("erro ao remover usuario = > " + e.getMessage());
			} catch (RepositorioException e) {
				erros.add("erro ao remover usuario = > " + e.getMessage());
			}
		} else if (acao.equals("inserirRepositorio")) {
			String nome = request.getParameter("nome");
			String formato = request.getParameter("formato");
			double area = 0;
			if (request.getParameter("area") != null)
				area = Double.parseDouble(request.getParameter("area").replace(
						",", "."));
			double diametro = 0;
			if (request.getParameter("diametro") != null)
				diametro = Double.parseDouble(request.getParameter("diametro")
						.replace(",", "."));
			double profundidade = Double.parseDouble(request.getParameter(
					"profundidade").replace(",", "."));
			double limiteMin = Double.parseDouble(request.getParameter(
					"limiteMin").replace(",", "."));
			double limiteMax = Double.parseDouble(request.getParameter(
					"limiteMax").replace(",", "."));
			try {
				Repositorio r;
				if (formato.equals("circular"))
					r = new RepositorioCircular(nome, profundidade, limiteMin,
							limiteMax, diametro);
				else
					r = new RepositorioRetangular(nome, profundidade,
							limiteMin, limiteMax, area);
				fachada.cadastrar(r);
				sucessos.add("Repósitorio inserido");
			} catch (Exception e) {
				erros.add("Erro ao inserir repositorio => " + e.getMessage());
			}
			url = "/repositorio-inserir.jsp";
		} else if (acao.equals("pegarRepositorio")) {
			try {
				String id = request.getParameter("id");
				Repositorio r = fachada.repositorioProcurar(Integer
						.parseInt(id));
				ServletOutputStream os = response.getOutputStream();
				os.print(r.getDescricao() + ",");
				os.print(r.getLimiteMaximo() + ",");
				os.print(r.getLimiteMinimo() + ",");
				os.print(r.getProfundidade() + ",");
				if (r instanceof RepositorioCircular) {
					os.print("circular,");
					os.print(((RepositorioCircular) r).getDiametroMedio());
				} else {
					os.print("retangular,");
					os.print(((RepositorioRetangular) r).getAreaBase());
				}
				os.close();
				url = "";
			} catch (SQLException e) {
				erros.add("Erro ao pegar repositorio => " + e.getMessage());
			} catch (NumberFormatException e) {
				erros.add("Erro ao pegar repositorio => " + e.getMessage());
			} catch (RepositorioNaoEncontradoException e) {
				erros.add("Erro ao pegar repositorio => " + e.getMessage());
			}
		} else if (acao.equals("alterarRepositorio")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			String formato = request.getParameter("formato");
			double area = 0;
			if (request.getParameter("area") != null)
				area = Double.parseDouble(request.getParameter("area").replace(
						",", "."));
			double diametro = 0;
			if (request.getParameter("diametro") != null)
				diametro = Double.parseDouble(request.getParameter("diametro")
						.replace(",", "."));
			double profundidade = Double.parseDouble(request.getParameter(
					"profundidade").replace(",", "."));
			double limiteMin = Double.parseDouble(request.getParameter(
					"limiteMin").replace(",", "."));
			double limiteMax = Double.parseDouble(request.getParameter(
					"limiteMax").replace(",", "."));
			try {
				Repositorio r;
				if (formato.equals("circular"))
					r = new RepositorioCircular(nome, profundidade, limiteMin,
							limiteMax, diametro);
				else
					r = new RepositorioRetangular(nome, profundidade,
							limiteMin, limiteMax, area);
				r.setId(id);
				fachada.atualizar(r);
				sucessos.add("Repósitorio Alterado");
			} catch (Exception e) {
				erros.add("Erro ao alterar repositorio => " + e.getMessage());
			}
			url = "/repositorio.jsp";
		} else if (acao.equals("removerRepositorio")) {
			try {
				String id = request.getParameter("id");
				fachada.repositorioRemover(Integer.parseInt(id));
				response.setStatus(200);
				url = "/repositorio.jsp";
			} catch (NumberFormatException e) {
				erros.add("erro ao remover repositorio = > " + e.getMessage());
			} catch (SQLException e) {
				erros.add("erro ao remover repositorio = > " + e.getMessage());
			} catch (RepositorioNaoEncontradoException e) {
				erros.add("erro ao remover repositorio = > " + e.getMessage());
			}
		} else if (acao.equals("inserirAtividade")) {
			String nome = request.getParameter("nome");
			Atividade a = new Atividade(nome);
			try {
				fachada.cadastrar(a);
				sucessos.add("Atividade inserida");
			} catch (Exception e) {
				erros.add("Erro ao inserir atividade => " + e.getMessage());
			}
			url = "/atividade-inserir.jsp";
		} else if (acao.equals("pegarAtividade")) {
			try {
				String id = request.getParameter("id");
				Atividade a = fachada.atividadeProcurar(Integer.parseInt(id));
				request.setAttribute("atividadeProcurar", a);
				ServletOutputStream os = response.getOutputStream();
				os.print(a.getDescricao());
				os.flush();
				os.close();
				url = "";
			} catch (SQLException e) {
				erros.add("Erro ao pegar atividade => " + e.getMessage());
			} catch (NumberFormatException e) {
				erros.add("Erro ao pegar atividade => " + e.getMessage());
			} catch (AtividadeNaoEncontradaException e) {
				erros.add("Erro ao pegar atividade => " + e.getMessage());
			} catch (RepositorioException e) {
				erros.add("Erro ao pegar atividade => " + e.getMessage());
			}
		} else if (acao.equals("alterarAtividade")) {
			String nome = request.getParameter("nome");
			Atividade a = new Atividade(nome);
			a.setId(Integer.parseInt(request.getParameter("id")));
			try {
				fachada.atualizar(a);
				sucessos.add("Atividade alterada");
			} catch (Exception e) {
				erros.add("Erro ao alterar atividade => " + e.getMessage());
			}
			url = "/atividade.jsp";
		} else if (acao.equals("removerAtividade")) {
			try {
				String id = request.getParameter("id");
				fachada.atividadeRemover(Integer.parseInt(id));
				response.setStatus(200);
				url = "/atividade.jsp";
			} catch (NumberFormatException e) {
				erros.add("erro ao remover atividade = > " + e.getMessage());
			} catch (SQLException e) {
				erros.add("erro ao remover atividade = > " + e.getMessage());
			} catch (AtividadeNaoEncontradaException e) {
				erros.add("erro ao remover atividade = > " + e.getMessage());
			} catch (RepositorioException e) {
				erros.add("erro ao remover atividade = > " + e.getMessage());
			}
		} else if (acao.equals("removerBomba")) {
			try {
				String id = request.getParameter("id");
				fachada.bombaRemover(Integer.parseInt(id));
				response.setStatus(200);
				url = "/bomba.jsp";
			} catch (NumberFormatException e) {
				erros.add("erro ao remover bomba = > " + e.getMessage());
			} catch (SQLException e) {
				erros.add("erro ao remover bomba = > " + e.getMessage());
			} catch (BombaNaoEncontradaException e) {
				erros.add("erro ao remover bomba = > " + e.getMessage());
			}
		} else if (acao.equals("inserirBomba")) {
			String descricao = request.getParameter("descricao");
			double potencia = Double.parseDouble(request.getParameter(
					"potencia").replace(",", "."));
			double vazao = Double.parseDouble(request.getParameter("vazao")
					.replace(",", "."));
			String acionamento = request.getParameter("acionamento");
			int idEnche = Integer.parseInt(request.getParameter("enche"));
			int idSeca = Integer.parseInt(request.getParameter("seca"));
			try {
				Bomba b = new Bomba(descricao, potencia, vazao, acionamento);
				if (idEnche != 0) {
					Repositorio enche = fachada.repositorioProcurar(idEnche);
					b.setRepositorioEnche(enche);
				}
				if (idSeca != 0) {
					Repositorio seca = fachada.repositorioProcurar(idSeca);
					b.setRepositorioSeca(seca);
				}
				b.setStatus(Bomba.OFF);
				if (idSeca == 0 && idEnche == 0)
					throw new IllegalArgumentException(
							"Algum repositório tem que ser selecionado");
				fachada.cadastrar(b);
				sucessos.add("Bomba inserida");
			} catch (Exception e) {
				erros.add("Erro ao inserir bomba => " + e.getMessage());
			}
			url = "/bomba-inserir.jsp";
		} else if (acao.equals("pegarBomba")) {
			try {
				String id = request.getParameter("id");
				Bomba b = fachada.bombaProcurar(Integer.parseInt(id));
				request.setAttribute("bombaProcurar", b);
				ServletOutputStream os = response.getOutputStream();
				os.print(b.getCodigo() + ",");
				os.print(b.getDescricao() + ",");
				os.print(b.getPotencia() + ",");
				os.print(b.getVazao() + ",");
				os.print(b.getAcionamento() + ",");
				os.print(b.getIdRepositorioEnche() + ",");
				os.print(b.getIdRepositorioSeca() + ",");
				os.print(b.getStatus());
				os.flush();
				os.close();
				url = "";
			} catch (SQLException e) {
				erros.add("Erro ao pegar bomba => " + e.getMessage());
			} catch (NumberFormatException e) {
				erros.add("Erro ao pegar bomba => " + e.getMessage());
			} catch (BombaNaoEncontradaException e) {
				erros.add("Erro ao pegar bomba => " + e.getMessage());
			}
		} else if (acao.equals("alterarBomba")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String descricao = request.getParameter("descricao");
			String status = request.getParameter("status");
			double potencia = Double.parseDouble(request.getParameter(
					"potencia").replace(",", "."));
			double vazao = Double.parseDouble(request.getParameter("vazao")
					.replace(",", "."));
			String acionamento = request.getParameter("acionamento");
			int idEnche = Integer.parseInt(request.getParameter("enche"));
			int idSeca = Integer.parseInt(request.getParameter("seca"));
			try {
				Bomba b = new Bomba(descricao, potencia, vazao, acionamento);
				if (idEnche != 0) {
					Repositorio enche = fachada.repositorioProcurar(idEnche);
					b.setRepositorioEnche(enche);
				}
				if (idSeca != 0) {
					Repositorio seca = fachada.repositorioProcurar(idSeca);
					b.setRepositorioSeca(seca);
				}
				if (idSeca == 0 && idEnche == 0)
					throw new IllegalArgumentException(
							"Algum repositório tem que ser selecionado");
				b.setStatus(status);
				b.setCodigo(id);
				fachada.atualizar(b);
				sucessos.add("Bomba Alterada");
			} catch (Exception e) {
				erros.add("Erro ao alterar bomba => " + e.getMessage());
			}
			url = "/bomba.jsp";
		} else if (acao.equals("consumoPorHora")) {
			
			try {
				
			} catch (Exception e) {
				erros.add("Erro ao alterar bomba => " + e.getMessage());
			}
			url = "/consumo-periodico.jsp";
		} else {
			System.out.println("Erro");
			url = "/home.jsp";
		}
		request.setAttribute("sucessos", sucessos);
		request.setAttribute("erros", erros);
		if (!url.equals("")) {
			try {
				request.getRequestDispatcher(url).forward(request, response);
			} catch (ServletException e) {
				System.out.println("erro ao direcionar página => "
						+ e.getMessage());
			}
		}
		System.out.println("foi executado");

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

	public String md5(String senha) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		sen = hash.toString(16);
		return sen;
	}

}
