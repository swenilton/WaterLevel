import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.usuario.Usuario;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientWebServiceAtividade {
	static WebResource webResource3;
	static WebResource webResource5;

	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			clientConfig.getClasses().add(JacksonJsonProvider.class);

			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividade/all");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<Atividade> list = new ObjectMapper().readValue(
					response.getEntity(String.class),
					new TypeReference<ArrayList<Atividade>>() {
					});

			WebResource webResource2 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividade/existe/ESCOVAR%20OS%20DENTES");

			ClientResponse response2 = webResource2.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Boolean existe = new ObjectMapper().readValue(
					response2.getEntity(String.class),
					new TypeReference<Boolean>() {
					});

			// TA FUNCIONANDO
			/*
			 * webResource3 = client
			 * .resource("http://localhost:8080/WaterLevel/WS2/atividade/add");
			 * 
			 * Atividade atividade = new Atividade("TESTE"); post(atividade);
			 */

			WebResource webResource4 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividade/procurar/1");

			ClientResponse response3 = webResource4.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Atividade atividade2 = new ObjectMapper().readValue(
					response3.getEntity(String.class),
					new TypeReference<Atividade>() {
					});

			// TA FUNCIONANDO
			/*
			 * webResource5 = client
			 * .resource("http://localhost:8080/WaterLevel/WS2/atividade/update"
			 * );
			 * 
			 * 
			 * Atividade atividade3 = new Atividade("TESTE ATUALIZADO");
			 * atividade3.setId(2); put(atividade3);
			 */

			// TA FUNCIONANDO
			/*WebResource webResource6 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividade/excluir/3");

			ClientResponse response4 = webResource6.type(
					MediaType.APPLICATION_JSON).delete(ClientResponse.class);
			*/
			System.out.println(existe);
			System.out.println(list);
			System.out.println(atividade2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void post(Object requestEntity) {
		try {
			webResource3.type(MediaType.APPLICATION_JSON).post(requestEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void put(Object requestEntity) {
		try {
			webResource5.type(MediaType.APPLICATION_JSON).put(requestEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
