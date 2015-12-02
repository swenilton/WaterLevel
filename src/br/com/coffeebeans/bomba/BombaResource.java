package br.com.coffeebeans.bomba;

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
import br.com.coffeebeans.repositorio.Repositorio;

@Path("/bomba")
public class BombaResource {
	private Fachada f;

	public BombaResource() throws Exception {
		f = Fachada.getInstance();
	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void cadastrar(Bomba bomba) throws ResourceException {
		System.out.println("BombaResource > saveBomba>"
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format(new Date()));

		try {
			// SE TENTAR FAZER ISSO NO CLIENT , ANTES DE SERIALIZAR, DÁ ERRO
			Repositorio rep = f.repositorioProcurar(bomba
					.getIdRepositorioEnche());
			Repositorio rep2 = f.repositorioProcurar(bomba
					.getIdRepositorioSeca());
			bomba.setRepositorioEnche(rep);
			bomba.setRepositorioSeca(rep2);
			f.cadastrar(bomba);
		} catch (Exception e) {
			System.out.println("erro no add resource " + e.getMessage());
			throw new ResourceException(e);
		}
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() throws ResourceException {
		List<Bomba> listaBombas = null;
		try {
			listaBombas = f.bombaListar();
			System.out.println("BombaResource > list "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaBombas);
	}

	@GET
	@Path("procurar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("id") int id) throws ResourceException {
		Bomba bomba = null;
		try {
			bomba = f.bombaProcurar(id);

			System.out.println("BombaResource > procuraBomba "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(bomba);
	}

	@GET
	@Path("procurar2/{descricao}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("descricao") String descricao)
			throws ResourceException {
		Bomba bomba = null;
		try {
			bomba = f.bombaProcurar(descricao);

			System.out.println("BombaResource > procuraBomba "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(bomba);
	}

	@GET
	@Path("procurarPorRep/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurarPorRep(@PathParam("id") int id)
			throws ResourceException {
		Bomba bomba = null;
		try {
			bomba = f.bombaProcurarPorRepositorio(id);

			System.out.println("BombaResource > bombaProcuraPorRep "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(bomba);
	}

	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(Bomba bomba) throws ResourceException {
		System.out.println("BombaResource > updateBomba>"
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format(new Date()));

		try {
			// SE TENTAR FAZER ISSO NO CLIENT , ANTES DE SERIALIZAR, DÁ ERRO
			Repositorio rep = f.repositorioProcurar(bomba
					.getIdRepositorioEnche());
			Repositorio rep2 = f.repositorioProcurar(bomba
					.getIdRepositorioSeca());
			bomba.setRepositorioEnche(rep);
			bomba.setRepositorioSeca(rep2);
			f.atualizar(bomba);
		} catch (Exception e) {
			System.out.println("erro no update resource " + e.getMessage());
			throw new ResourceException(e);
		}
	}

	@DELETE
	@Path("excluir/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") int id) throws ResourceException {

		try {
			f.bombaRemover(id);

			System.out.println("BombaResource > removeBomba "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
	}

}
