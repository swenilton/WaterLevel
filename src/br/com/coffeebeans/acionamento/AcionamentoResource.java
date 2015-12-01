package br.com.coffeebeans.acionamento;
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

@Path("/acionamento")
public class AcionamentoResource {
	private Fachada f;

	public AcionamentoResource() throws Exception {
		f = Fachada.getInstance();
	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void cadastrar(Acionamento acionamento) throws ResourceException {
		System.out.println("AcionamentoResource > saveAcionamento>"
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format(new Date()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date = sdf.parse(acionamento.getDateHoraInicio());
			Date date2 = sdf.parse(acionamento.getDateHoraFim());
			acionamento.setDataHoraInicio(date);
			acionamento.setDataHoraFim(date2);
			f.cadastrar(acionamento);
		} catch (Exception e) {
			System.out.println("erro no add resource " + e.getMessage());
			throw new ResourceException(e);
		}
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() throws ResourceException {
		List<Acionamento> listaAcionamentos = null;
		try {
			listaAcionamentos = f.acionamentoListar();
			System.out.println("AcionamentoResource > list "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaAcionamentos);
	}

	@GET
	@Path("/getUltimosAcionamentos")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUltimosAcionamentos() throws ResourceException {
		List<Acionamento> listUltimosAcionamentos=null;
		try {
			listUltimosAcionamentos = f.getUltimosAcionamentos();
			System.out.println("AcionamentoResource > getUltimosAcionamentos "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceException(e);
		}
		return new Gson().toJson(listUltimosAcionamentos);
	}
	@GET
	@Path("procurar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurar(@PathParam("id") int id) throws ResourceException {
		Acionamento acionamento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			acionamento = f.acionamentoProcurar(id);
			acionamento.setDateHoraInicio(sdf.format(acionamento.getDataHoraInicio()));
			acionamento.setDateHoraFim(sdf.format(acionamento.getDataHoraFim()));

			System.out
					.println("AcionamentoResource > procuraAcionamento "
							+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
									.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(acionamento);
	}

	@GET
	@Path("procurarIni/{date1}/{date2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurarIni(@PathParam("date1") String date1, @PathParam("date2") String date2) throws ResourceException {
		Acionamento acionamento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date3 = sdf.parse(date1);
			Date date4 = sdf.parse(date2);

			acionamento = f.acionamentoProcurarIni(date3,date4);
			acionamento.setDateHoraInicio(sdf.format(acionamento.getDataHoraInicio()));
			acionamento.setDateHoraFim(sdf.format(acionamento.getDataHoraFim()));
			
			System.out
					.println("AcionamentoResource > procuraAcionamentoIni "
							+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
									.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(acionamento);
	}
	@GET
	@Path("procurarFim/{date1}/{date2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String procurarFim(@PathParam("date1") String date1, @PathParam("date2") String date2) throws ResourceException {
		Acionamento acionamento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date3 = sdf.parse(date1);
			Date date4 = sdf.parse(date2);

			acionamento = f.acionamentoProcurarFim(date3,date4);
			acionamento.setDateHoraInicio(sdf.format(acionamento.getDataHoraInicio()));
			acionamento.setDateHoraFim(sdf.format(acionamento.getDataHoraFim()));
			
			System.out
					.println("AcionamentoResource > procuraAcionamentoFim "
							+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
									.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
		return new Gson().toJson(acionamento);
	}
	
	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void atualizar(Acionamento acionamento)
			throws ResourceException {
		System.out
				.println("AcionamentoResource > atualizaAcionamento>: ");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date = sdf.parse(acionamento.getDateHoraInicio());
			Date date2 = sdf.parse(acionamento.getDateHoraFim());
			acionamento.setDataHoraInicio(date);
			acionamento.setDataHoraFim(date2);

			f.atualizar(acionamento);

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
			f.acionamentoRemover(id);

			System.out.println("AcionamentoResource > removeAcionamento "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
							.format(new Date()));

		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw new ResourceException(e);
		}
	}
}
