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

@Path("/atividadeRealizada")
public class AtividadeRealizadaResource {
	private Fachada f;
	public List<AtividadeRealizada> listaAtividadesRealizadas = null;
	public Date date;
	public Date date2;

	public AtividadeRealizadaResource() throws Exception {
		f = Fachada.getInstance();
	}

	@GET
	@Path("existe/{data_hora_inicio}/{data_hora_fim}/{id_atividade}/{id_usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public String existe(
			@PathParam("data_hora_inicio") String data_hora_inicio,
			@PathParam("data_hora_fim") String data_hora_fim,
			@PathParam("id_usuario") int id_usuario,
			@PathParam("id_atividade") int id_atividade)
			throws ResourceException {
		boolean existe = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(data_hora_inicio);
			Date date2 = sdf.parse(data_hora_fim);

			existe = f.existeAtividadeRealizada(id_usuario, id_atividade, date,
					date2);

			System.out
					.println("AtividadeRealizadaResource > existeAtividadeRealizada "
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
	public void cadastrar(AtividadeRealizada atividade)
			throws ResourceException {
		System.out
				.println("AtividadeRealidadeResource > saveAtividadeRealizada>"
						+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
								.format(new Date()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			date = sdf.parse(atividade.getDateHoraInicio());
			date2 = sdf.parse(atividade.getDateHoraFim());
			atividade.setDataHoraInicio(date);
			atividade.setDataHoraFim(date2);
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
			System.out.println("AtividadeRealizadaResource > list2 "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaAtividadesRealizadas);
	}

	@GET
	@Path("procurar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("id") int id) throws ResourceException {
		AtividadeRealizada ar = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			ar = f.atividadeRealizadaProcurar(id);
			ar.setDateHoraInicio(sdf.format(ar.getDataHoraInicio()));
			ar.setDateHoraFim(sdf.format(ar.getDataHoraFim()));

			System.out
					.println("AtividadeRealizadaResource > procuraAtividadeRealizada "
							+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
									.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(ar);
	}

	@GET
	@Path("procurar2/{descricao}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("descricao") String descricao)
			throws ResourceException {
		List<AtividadeRealizada> list = null;
		//AtividadeRealizada ar = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			list = f.atividadeRealizadaProcurar(descricao);
			// ar=f.atividadeRealizadaProcurar(descricao);
			for (AtividadeRealizada atividadeRealizada : list) {
				atividadeRealizada.setDateHoraInicio(sdf
						.format(atividadeRealizada.getDataHoraInicio()));
				atividadeRealizada.setDateHoraFim(sdf.format(atividadeRealizada
						.getDataHoraFim()));

			}
			// ar.setDateHoraInicio(sdf.format(ar.getDataHoraInicio()));
			// ar.setDateHoraFim(sdf.format(ar.getDataHoraFim()));

			System.out
					.println("AtividadeRealizadaResource > procuraAtividadeRealizada "
							+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
									.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(list);
	}

	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(AtividadeRealizada atividadeRealizada)
			throws ResourceException {
		System.out
				.println("AtividadeRealizadaResource > atualizaAtividadeRealizada>  : ");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			date = sdf.parse(atividadeRealizada.getDateHoraInicio());
			date2 = sdf.parse(atividadeRealizada.getDateHoraFim());
			atividadeRealizada.setDataHoraInicio(date);
			atividadeRealizada.setDataHoraFim(date2);

			f.atualizar(atividadeRealizada);

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
			f.atividadeRealizadaRemover(id);

			System.out.println("AtividadeRealizadaResource > removeAtividadeRealizada "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
	}
	
	@GET
	@Path("/getUltimasAtividades")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUltimasAtividades() throws ResourceException {
		List<AtividadeRealizada> listUltimasAtividadesRealizadas=null;
		try {
			listUltimasAtividadesRealizadas = f.getUltimasAtividades();
			System.out.println("AtividadeRealizadaResource > list "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listUltimasAtividadesRealizadas);
	}


}
