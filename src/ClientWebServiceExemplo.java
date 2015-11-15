import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.coffeebeans.usuario.Senha;
import br.com.coffeebeans.usuario.Usuario;

public class ClientWebServiceExemplo {

	static WebResource webResource4;
	static WebResource webResource6;
	static WebResource webResource7;

	// @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			clientConfig.getClasses().add(JacksonJsonProvider.class);

			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/WaterLevel/user/all");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<Usuario> list = new ObjectMapper().readValue(
					response.getEntity(String.class),
					new TypeReference<ArrayList<Usuario>>() {
					});

			WebResource webResource2 = client
					.resource("http://localhost:8080/WaterLevel/user/existe/admin");

			ClientResponse response2 = webResource2.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Boolean existe = new ObjectMapper().readValue(
					response2.getEntity(String.class),
					new TypeReference<Boolean>() {
					});

			WebResource webResource3 = client
					.resource("http://localhost:8080/WaterLevel/user/existe2/andreilip@hotmail.com");

			ClientResponse response3 = webResource3.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Boolean existe2 = new ObjectMapper().readValue(
					response3.getEntity(String.class),
					new TypeReference<Boolean>() {
					});

			// TA FUNCIONANDO

			/*
			 * webResource4 = client
			 * .resource("http://localhost:8080/WaterLevel/user/add");
			 * 
			 * Usuario usuario = new Usuario("Jorge", "josiasssss", "12345",
			 * "a@b.comsssss", "SIM", "ADMINISTRADOR"); post(usuario);
			 */

			WebResource webResource5 = client
					.resource("http://localhost:8080/WaterLevel/user/procurar/1");

			ClientResponse response5 = webResource5.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Usuario usuario2 = new ObjectMapper().readValue(
					response5.getEntity(String.class),
					new TypeReference<Usuario>() {
					});

			// TA FUNCIONANDO

			/*
			 * webResource6 = client
			 * .resource("http://localhost:8080/WaterLevel/user/update");
			 * 
			 * Usuario usuario3 = new Usuario("Jose", "josiao", "12345",
			 * "a@b.comm", "SIM", "ADMINISTRADOR");
			 * 
			 * usuario3.setId(4); put(usuario3);
			 */

			// TA FUNCIONANDO

			/*
			 * webResource7 = client
			 * .resource("http://localhost:8080/WaterLevel/user/newPwd");
			 * 
			 * Senha senha = new Senha(2, "698dc19d489c4e4db73e28a713eab07b");
			 * 
			 * put2(senha);
			 */

			// TA FUNCIONANDO

			WebResource webResource8 = client
					.resource("http://localhost:8080/WaterLevel/user/excluir/8");

			ClientResponse response6 = webResource8.type(
					MediaType.APPLICATION_JSON).delete(ClientResponse.class);

			WebResource webResource9 = client
					.resource("http://localhost:8080/WaterLevel/user/loginFace/a@b.c");

			ClientResponse response7 = webResource9.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Boolean logou = new ObjectMapper().readValue(
					response7.getEntity(String.class),
					new TypeReference<Boolean>() {
					});
			
			WebResource webResource10 = client
					.resource("http://localhost:8080/WaterLevel/user/login/admin/181290");

			ClientResponse response8 = webResource10.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Boolean logou2 = new ObjectMapper().readValue(
					response8.getEntity(String.class),
					new TypeReference<Boolean>() {
					});
			
			WebResource webResource11 = client
					.resource("http://localhost:8080/WaterLevel/user/md5/181290");

			ClientResponse response9 = webResource11.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			
			String senhaCrip=response9.getEntity(String.class);
			
			System.out.println("Server response .... \n");
			System.out.println(list.toString());
			System.out.println(existe);
			System.out.println(existe2);
			// System.out.println(usuario);
			System.out.println(usuario2);
			// System.out.println(usuario3);
			System.out.println(logou);
			System.out.println(logou2);
			System.out.println(senhaCrip);
				
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void post(Object requestEntity) {
		try {
			webResource4.type(MediaType.APPLICATION_JSON).post(requestEntity);
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
