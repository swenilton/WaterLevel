package br.com.coffeebeans.repositorio;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

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

import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.exception.ResourceException;
import br.com.coffeebeans.fachada.Fachada;

@Path("/repositorio")
public class RepositorioResource {
	private Fachada f;
	public List<Repositorio> listaRepositorios = null;

	public RepositorioResource() throws Exception {
		f = Fachada.getInstance();
	}

	@POST
	@Path("circular/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void cadastrar(RepositorioCircular repositorio)
			throws ResourceException {
		System.out.println("RepositorioResource > saveRepositorioCirc>  "
				+ repositorio.getDescricao());

		try {
			f.cadastrar(repositorio);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}

	}

	@POST
	@Path("retangular/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void cadastrar(RepositorioRetangular repositorio)
			throws ResourceException {
		System.out.println("RepositorioResource > saveRepositorioRet>  "
				+ repositorio.getDescricao());

		try {
			f.cadastrar(repositorio);

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
			listaRepositorios = f.repositorioListar();
			System.out.println("RepositorioResource > listRepositorios "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaRepositorios);
	}

	@GET
	@Path("procurar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("id") int id) throws ResourceException {
		Repositorio repositorio = null;
		try {
			repositorio = f.repositorioProcurar(id);

			System.out.println("RepositorioResource > procuraRep "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(repositorio);
	}

	@GET
	@Path("procurar2/{descricao}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("descricao") String descricao)
			throws ResourceException {
		Repositorio repositorio = null;
		try {
			repositorio = f.repositorioProcurar(descricao);

			System.out.println("RepositorioResource > procuraRep "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(repositorio);
	}

	@PUT
	@Path("update/circular")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(RepositorioCircular repositorio)
			throws ResourceException {
		System.out.println("RepositorioResource > atualizaRep>  : "
				+ repositorio.getDescricao());

		try {
			f.atualizar(repositorio);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}

	}

	@PUT
	@Path("update/retangular")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(RepositorioRetangular repositorio)
			throws ResourceException {
		System.out.println("RepositorioResource > atualizaRep>  : "
				+ repositorio.getDescricao());

		try {
			f.atualizar(repositorio);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}

	}

	@DELETE
	@Path("excluir/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") int id) throws ResourceException {

		try {
			f.repositorioRemover(id);

			System.out.println("RepositorioResource > removeRep "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
	}

}
