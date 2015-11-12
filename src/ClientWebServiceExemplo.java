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
	import br.com.coffeebeans.usuario.Usuario;

	public class ClientWebServiceExemplo {

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
				System.out.println("Server response .... \n");
				System.out.println(list.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
