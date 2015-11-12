package br.com.coffeebeans.usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import br.com.coffeebeans.exception.ResourceException;
import br.com.coffeebeans.fachada.Fachada;

@Path("/user")
public class UsuarioResource {
	private Fachada f;
	public List<Usuario> listaUsers = null;
	
	public UsuarioResource() throws Exception {
		f = Fachada.getInstance();
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() throws ResourceException {
		try {
			listaUsers = f.getUsuarioLista();
			System.out.println("UsarioResource > listUsers "
					+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
			
		} catch (Exception e) {
			throw new ResourceException(e);
		}
		return new Gson().toJson(listaUsers);
	}
}
