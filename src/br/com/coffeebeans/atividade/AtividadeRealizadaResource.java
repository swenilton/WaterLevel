package br.com.coffeebeans.atividade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.coffeebeans.exception.ResourceException;
import br.com.coffeebeans.fachada.Fachada;

@Path("/atividadeRealizada")
public class AtividadeRealizadaResource {
	private Fachada f;
	public List<AtividadeRealizada> listaAtividadesRealizadas = null;

	public AtividadeRealizadaResource() throws Exception {
		f = Fachada.getInstance();
	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void cadastrar(AtividadeRealizada atividade)
			throws ResourceException {
//		System.out
//				.println("AtividadeRealidadeResource > saveAtividadeRealizada>"
//						+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
//								.format(new Date()));
		System.out.println("Data -> entrou");
		try {
			f.cadastrar(atividade);

		} catch (Exception e) {
			System.out.println("erro no add resource " + e.getMessage());
			throw new ResourceException(e);
		}

	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() throws ResourceException {
		try {
			listaAtividadesRealizadas = f.atividadeRealizadaListar();
			System.out.println("AtividadeRealizadaResource > list "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaAtividadesRealizadas);
	}

	@GET
	@Path("all/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList(@PathParam("id") int id) throws ResourceException {
		try {
			listaAtividadesRealizadas = f.atividadeRealizadaListar(id);
			System.out.println("AtividadeRealizadaResource > list "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaAtividadesRealizadas);
	}
}
