package br.com.coffeebeans.atividade;

import java.text.SimpleDateFormat;
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

@Path("/atividade")
public class AtividadeResource {
	private Fachada f;
	public List<Atividade> listaAtividades = null;

	public AtividadeResource() throws Exception {
		f = Fachada.getInstance();
	}

	@GET
	@Path("existe/{descricao}")
	@Produces(MediaType.APPLICATION_JSON)
	public String existe(@PathParam("descricao") String descricao)
			throws ResourceException {
		boolean existe = false;
		try {
			existe = f.existeAtividade(descricao);

			System.out.println("AtividadeResource > existeAtividade "
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
	public void cadastrar(Atividade atividade) throws ResourceException {
		System.out.println("AtividadeResource > saveAtividade>  "
				+ atividade.getDescricao());

		try {
			f.cadastrar(atividade);

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
			listaAtividades = f.atividadeListar();
			System.out.println("AtividadeResource > listAtividades "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaAtividades);
	}

	@GET
	@Path("procurar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("id") int id) throws ResourceException {
		Atividade atividade = null;
		try {
			atividade = f.atividadeProcurar(id);

			System.out.println("AtividadeResource > procuraAtividade "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(atividade);
	}

	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(Atividade atividade) throws ResourceException {
		System.out.println("AtividadeResource > atualizaAtividade>  : "
				+ atividade.getDescricao());

		try {
			f.atualizar(atividade);

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
			f.atividadeRemover(id);

			System.out.println("AtividadeResource > removeAtividade "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
	}
}
