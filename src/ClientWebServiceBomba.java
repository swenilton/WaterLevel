import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import br.com.coffeebeans.bomba.Bomba;
import br.com.coffeebeans.fachada.Fachada;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientWebServiceBomba {
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
					.resource("http://localhost:8080/WaterLevel/WS5/bomba/all");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<Bomba> list = obj.readValue(
					response.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

			// FUNCIONANDO

			/*
			 * WebResource webResource2 = client
			 * .resource("http://localhost:8080/WaterLevel/WS5/bomba/add");
			 * 
			 * Bomba bomba = new
			 * Bomba("atualizada","ligada",0.00,0.00,"MANUAL",3);
			 * bomba.setIdRepositorioSeca(3);
			 * 
			 * webResource2.type(MediaType.APPLICATION_JSON).post(bomba);
			 */

			WebResource webResource3 = client
					.resource("http://localhost:8080/WaterLevel/WS5/bomba/procurar/3");

			ClientResponse response2 = webResource3.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Bomba bomba2 = obj.readValue(response2.getEntity(String.class),
					new TypeReference<Bomba>() {
					});

			WebResource webResource4 = client
					.resource("http://localhost:8080/WaterLevel/WS5/bomba/procurar2/nova%20descricao");

			ClientResponse response3 = webResource4.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Bomba bomba3 = obj.readValue(response3.getEntity(String.class),
					new TypeReference<Bomba>() {
					});

			WebResource webResource5 = client
					.resource("http://localhost:8080/WaterLevel/WS5/bomba/procurarPorRep/3");

			ClientResponse response4 = webResource5.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Bomba bomba4 = obj.readValue(response4.getEntity(String.class),
					new TypeReference<Bomba>() {
					});

			// FUNCIONANDO

			/*
			 * WebResource webResource6 = client
			 * .resource("http://localhost:8080/WaterLevel/WS5/bomba/update");
			 * 
			 * Bomba bomba5 = fachada.bombaProcurar(3);
			 * bomba5.setDescricao("nova descricao");
			 * 
			 * webResource6.type(MediaType.APPLICATION_JSON).put(bomba5);
			 */

			// TA FUNCIONANDO
			/*WebResource webResource7 = client
					.resource("http://localhost:8080/WaterLevel/WS5/bomba/excluir/2");

			webResource7.type(MediaType.APPLICATION_JSON).delete(
					ClientResponse.class);*/

			System.out.println(list);
			System.out.println("Procurar: " + bomba2);
			System.out.println("Procurar2: " + bomba3);
			System.out.println("ProcurarPorRep: " + bomba4);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}