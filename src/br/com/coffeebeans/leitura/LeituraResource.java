package br.com.coffeebeans.leitura;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.coffeebeans.exception.ResourceException;
import br.com.coffeebeans.fachada.Fachada;

@Path("/leitura")
public class LeituraResource {
	private Fachada f;


public LeituraResource() throws Exception {
	f = Fachada.getInstance();
}

@GET
@Path("getUltimaLeitura/{idRepositorio}")
@Produces(MediaType.APPLICATION_JSON)
public String existe(@PathParam("idRepositorio") int idRepositorio)
		throws ResourceException {
	Double ultimaLeitura = 0.0;
	try {
		ultimaLeitura = f.getUltimaLeitura(idRepositorio);

		System.out.println("LeituraResource > getUltimaLeitura "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format(new Date()));

	} catch (Exception e) {
		System.out.println(e.getMessage());

		throw new ResourceException(e);
	}
	return new Gson().toJson(ultimaLeitura);
}

}
