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

import br.com.coffeebeans.fachada.Fachada;

public class ClientWebServiceLeitura {
	public static Fachada fachada;

	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			clientConfig.getClasses().add(JacksonJsonProvider.class);

			Client client = Client.create(clientConfig);

			fachada = Fachada.getInstance();

			ObjectMapper obj = new ObjectMapper();

			WebResource webResource = client
					.resource("http://localhost:8080/WaterLevel/WS6/leitura/getUltimaLeitura/3");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			double leitura = obj.readValue(
					response.getEntity(String.class),
					new TypeReference<Double>() {
					});

			System.out.println(leitura);
		System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
	}
}
