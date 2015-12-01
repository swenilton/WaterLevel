import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import br.com.coffeebeans.acionamento.Acionamento;
import br.com.coffeebeans.bomba.Bomba;
import br.com.coffeebeans.fachada.Fachada;

public class ClientWebServiceAcionamento {
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
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/all");

			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<Acionamento> list = obj.readValue(
					response.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

			/*
			 * WebResource webResource2 = client
			 * .resource("http://localhost:8080/WaterLevel/WS4/acionamento/add"
			 * );
			 * 
			 * Bomba bomba =fachada.bombaProcurar(2);
			 * 
			 * Date date = new Date(); Date date2 = new Date();
			 * 
			 * SimpleDateFormat sdf = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 * 
			 * String teste1 = sdf.format(date); String teste2 =
			 * sdf.format(date2);
			 * 
			 * Acionamento acionamento = new Acionamento(bomba, date, date2,
			 * teste1, teste2);
			 * webResource2.type(MediaType.APPLICATION_JSON).post(acionamento);
			 */

			WebResource webResource3 = client
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/getUltimosAcionamentos");

			ClientResponse response2 = webResource3.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<Acionamento> list2 = obj.readValue(
					response2.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

			WebResource webResource4 = client
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/procurar/2");

			ClientResponse response3 = webResource4.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Acionamento acionamento2 = obj.readValue(
					response3.getEntity(String.class),
					new TypeReference<Acionamento>() {
					});

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date3 = sdf.parse(acionamento2.getDateHoraInicio());
			Date date4 = sdf.parse(acionamento2.getDateHoraFim());

			acionamento2.setDataHoraInicio(date3);
			acionamento2.setDataHoraFim(date4);

			WebResource webResource5 = client
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/procurarIni/2015-05-25%2015:30:00/2015-05-25%2015:30:00");

			ClientResponse response4 = webResource5.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Acionamento acionamento3 = obj.readValue(
					response4.getEntity(String.class),
					new TypeReference<Acionamento>() {
					});
			Date date5 = sdf.parse(acionamento3.getDateHoraInicio());
			Date date6 = sdf.parse(acionamento3.getDateHoraFim());

			acionamento3.setDataHoraInicio(date5);
			acionamento3.setDataHoraFim(date6);

			WebResource webResource6 = client
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/procurarFim/2015-05-25%2015:35:00/2015-05-25%2015:35:00");

			ClientResponse response5 = webResource6.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Acionamento acionamento4 = obj.readValue(
					response5.getEntity(String.class),
					new TypeReference<Acionamento>() {
					});
			Date date7 = sdf.parse(acionamento4.getDateHoraInicio());
			Date date8 = sdf.parse(acionamento4.getDateHoraFim());

			acionamento4.setDataHoraInicio(date7);
			acionamento4.setDataHoraFim(date8);

			WebResource webResource7 = client
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/update");

			Acionamento acionamento5 = fachada.acionamentoProcurar(2);

			// ATUALIZANDO DADOS //SETANDO DATAs STRING

			Date date9 = new Date();
			Date date10 = new Date();

			String teste3 = sdf.format(date9);
			String teste4 = sdf.format(date10);

			acionamento5.setDateHoraInicio(teste3);
			acionamento5.setDateHoraFim(teste4);

			// ---------------------------------------
			Bomba bomba2 = fachada.bombaProcurar(acionamento5.getIdBomba());

			acionamento5.setBomba(bomba2);

			webResource7.type(MediaType.APPLICATION_JSON).put(acionamento5);

			// TA FUNCIONANDO
			/*WebResource webResource8 = client
					.resource("http://localhost:8080/WaterLevel/WS4/acionamento/excluir/1");

			webResource8.type(MediaType.APPLICATION_JSON).delete(
					ClientResponse.class);*/

			System.out.println("list: " + list);
			System.out.println("list2: " + list2);
			System.out.println("procurarAcionamento: " + acionamento2);
			System.out.println("procurarIni: " + acionamento3);
			System.out.println("procurarFim: " + acionamento4);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
