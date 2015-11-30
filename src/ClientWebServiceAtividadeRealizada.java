import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.coffeebeans.atividade.AtividadeRealizada;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.coffeebeans.usuario.Usuario;
import br.com.coffeebeans.atividade.Atividade;;

public class ClientWebServiceAtividadeRealizada {
	static WebResource webResource3;

	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			clientConfig.getClasses().add(JacksonJsonProvider.class);

			Client client = Client.create(clientConfig);

			ObjectMapper obj = new ObjectMapper();

			WebResource webResource = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/all");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<AtividadeRealizada> list = obj.readValue(
					response.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

			WebResource webResource2 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/all/1");

			ClientResponse response2 = webResource2.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<AtividadeRealizada> list2 = obj.readValue(
					response2.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

		/*	WebResource webResource3 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/add");

			Usuario usuario = new Usuario("teste", "teste", "teste",
					"a@b.czao", "SIM", "ADMINISTRADOR");
			usuario.setId(1);

			Atividade atividade = new Atividade("TESTE3");
			atividade.setId(1);

			Date date = new Date();
			Date date2 = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String teste1 = sdf.format(date);
			String teste2 = sdf.format(date2);

			AtividadeRealizada ar = new AtividadeRealizada(atividade, date,
					date2, usuario, 0.00, teste1, teste2);

			//webResource3.type(MediaType.APPLICATION_JSON).post(ar); */
		
			//////////////////////////////////////FUNCIONA
			
			System.out.println(list);
			System.out.println(list2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
