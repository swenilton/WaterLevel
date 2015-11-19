import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.exception.ResourceException;
import br.com.coffeebeans.repositorio.Repositorio;
import br.com.coffeebeans.repositorio.RepositorioCircular;
import br.com.coffeebeans.repositorio.RepositorioRetangular;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientWebServiceRepositorio {
	static WebResource webResource2;
	static WebResource webResource3;
	static WebResource webResource6;
	static WebResource webResource7;

	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			clientConfig.getClasses().add(JacksonJsonProvider.class);

			Client client = Client.create(clientConfig);

			ObjectMapper obj = new ObjectMapper();
			obj.registerSubtypes(RepositorioCircular.class,
					RepositorioRetangular.class);

			WebResource webResource = client
					.resource("http://localhost:8080/WaterLevel/WS3/repositorio/all");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<Repositorio> list = obj.readValue(
					response.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

			// FUNCIONA

			/*
			 * webResource2 = client.resource(
			 * "http://localhost:8080/WaterLevel/WS3/repositorio/circular/add");
			 * 
			 * Repositorio repositorio = new RepositorioCircular("Circular9",
			 * 200.00, 100.00, 900.00, 0.00); post(repositorio);
			 */

			// FUNCIONA
			/*
			 * webResource3 = client .resource(
			 * "http://localhost:8080/WaterLevel/WS3/repositorio/retangular/add"
			 * );
			 * 
			 * Repositorio repositorio2 = new
			 * RepositorioRetangular("Retangular3", 200.00, 100.00, 900.00,
			 * 0.00); post2(repositorio2);
			 */

			WebResource webResource4 = client
					.resource("http://localhost:8080/WaterLevel/WS3/repositorio/procurar/3");

			ClientResponse response2 = webResource4.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Object repositorio3 = obj.readValue(
					response2.getEntity(String.class),
					new TypeReference<Object>() {
					});

			WebResource webResource5 = client
					.resource("http://localhost:8080/WaterLevel/WS3/repositorio/procurar2/Circular5");

			ClientResponse response3 = webResource5.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Object repositorio4 = obj.readValue(
					response3.getEntity(String.class),
					new TypeReference<Object>() {
					});

			// FUNCIONANDO

			/*
			 * webResource6 = client .resource(
			 * "http://localhost:8080/WaterLevel/WS3/repositorio/update/circular"
			 * ); Repositorio repositorio5 = new
			 * RepositorioCircular("Circular900", 200.00, 100.00, 900.00, 0.00);
			 * repositorio5.setId(14); put(repositorio5);
			 * 
			 * //FUNCIONANDO
			 * 
			 * webResource7 = client .resource(
			 * "http://localhost:8080/WaterLevel/WS3/repositorio/update/retangular"
			 * ); Repositorio repositorio6 = new RepositorioRetangular(
			 * "Retangular900", 200.00, 100.00, 900.00, 0.00);
			 * repositorio6.setId(13); put2(repositorio6);
			 */

			// TA FUNCIONANDO
			WebResource webResource8 = client
					.resource("http://localhost:8080/WaterLevel/WS3/repositorio/excluir/4");

			ClientResponse response4 = webResource8.type(
					MediaType.APPLICATION_JSON).delete(ClientResponse.class);

			System.out.println("lista: " + list);
			System.out.println("procurar: " + repositorio3);
			System.out.println("procurar: " + repositorio4);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void post(Object requestEntity) {
		try {
			webResource2.type(MediaType.APPLICATION_JSON).post(requestEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void post2(Object requestEntity) {
		try {
			webResource3.type(MediaType.APPLICATION_JSON).post(requestEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void put(Object requestEntity) {
		try {
			webResource6.type(MediaType.APPLICATION_JSON).put(requestEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void put2(Object requestEntity) {
		try {
			webResource7.type(MediaType.APPLICATION_JSON).put(requestEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
