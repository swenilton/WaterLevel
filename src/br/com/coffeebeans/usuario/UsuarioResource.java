package br.com.coffeebeans.usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.coffeebeans.exception.ResourceException;
import br.com.coffeebeans.fachada.Fachada;

//TODO // remover fachada dessa classe 
@Path("/user")
public class UsuarioResource {
	private Fachada f;
	public List<Usuario> listaUsers = null;

	public UsuarioResource() throws Exception {
		f = Fachada.getInstance();
	}

	@GET
	@Path("existe/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public String existe(@PathParam("login") String login)
			throws ResourceException {
		boolean existe = false;
		try {
			existe = f.existeUsuario(login);

			System.out.println("UsarioResource > existeUser "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(existe);
	}

	@GET
	@Path("existe2/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String existeEmail(@PathParam("email") String email)
			throws ResourceException {
		boolean existe = false;
		try {
			existe = f.existeEmail(email);

			System.out.println("UsarioResource > existeEmail "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(existe);
	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void cadastrar(Usuario user) throws ResourceException {
		System.out.println("UserResource > saveUser> saveUser: "
				+ user.getNome());

		try {
			f.cadastrar(user);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}

	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() throws ResourceException {
		try {
			listaUsers = f.getUsuarioLista();
			System.out.println("UsarioResource > listUsers "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaUsers);
	}

	@GET
	@Path("procurar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("id") int id) throws ResourceException {
		Usuario usuario = null;
		try {
			usuario = f.usuarioProcurar(id);

			System.out.println("UsarioResource > procuraUser "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(usuario);
	}

	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(Usuario user) throws ResourceException {
		System.out.println("UserResource > atualizaUser>  : " + user.getNome());

		try {
			f.atualizar(user);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}

	}

	@PUT
	@Path("newPwd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void alterarSenha(Senha senha) throws ResourceException {
		System.out.println("UserResource > alteraSenha>  : ");

		try {
			f.alterarSenhaUsuario(senha.getIdUser(), senha.getSenha());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}

	}

	@DELETE
	@Path("excluir/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") int id) throws ResourceException {
		// Usuario usuario = null;
		try {
			f.usuarioRemover(id);

			System.out.println("UsarioResource > removeUser "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
	}

	@GET
	@Path("loginFace/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String loginFacebook(@PathParam("email") String email)
			throws ResourceException {
		boolean continuar = false;
		try {
			continuar = f.loginFacebook(email);

			System.out.println("UsarioResource > loginFace "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(continuar);
	}

	@GET
	@Path("login/{usuario}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("usuario") String usuario,
			@PathParam("senha") String senha) throws ResourceException {
		boolean continuar = false;
		try {
			continuar = f.login(usuario, senha);

			System.out.println("UsarioResource > logar "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(continuar);
	}

	@GET
	@Path("md5/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public String md5(@PathParam("senha") String senha)
			throws ResourceException {
		String senhaCrip = "";
		try {
			senhaCrip = f.md5(senha);

			System.out.println("UsarioResource > md5 "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(senhaCrip);
	}
}
