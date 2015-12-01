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
import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.fachada.Fachada;

public class ClientWebServiceAtividadeRealizada {
	static WebResource webResource3;
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

			/*
			 * WebResource webResource3 = client .resource(
			 * "http://localhost:8080/WaterLevel/WS2/atividadeRealizada/add");
			 * 
			 * Usuario usuario = new Usuario("teste", "teste", "teste",
			 * "a@b.czao", "SIM", "ADMINISTRADOR"); usuario.setId(1);
			 * 
			 * Atividade atividade = new Atividade("TESTE3");
			 * atividade.setId(1);
			 * 
			 * Date date = new Date(); Date date2 = new Date();
			 * 
			 * SimpleDateFormat sdf = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 * 
			 * String teste1 = sdf.format(date); String teste2 =
			 * sdf.format(date2);
			 * 
			 * AtividadeRealizada ar = new AtividadeRealizada(atividade, date,
			 * date2, usuario, 0.00, teste1, teste2);
			 * 
			 * //webResource3.type(MediaType.APPLICATION_JSON).post(ar);
			 */

			// ////////////////////////////////////FUNCIONA

			WebResource webResource4 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/existe/2015-11-30%2019:55:46/2015-11-30%2019:55:46/1/1");

			ClientResponse response3 = webResource4.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Boolean existe = obj.readValue(response3.getEntity(String.class),
					new TypeReference<Boolean>() {
					});

			WebResource webResource5 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/procurar/3");

			ClientResponse response4 = webResource5.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			AtividadeRealizada atividade2 = obj.readValue(
					response4.getEntity(String.class),
					new TypeReference<AtividadeRealizada>() {
					});

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date3 = sdf.parse(atividade2.getDateHoraInicio());
			Date date4 = sdf.parse(atividade2.getDateHoraFim());

			atividade2.setDataHoraInicio(date3);
			atividade2.setDataHoraFim(date4);

			WebResource webResource6 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/procurar2/ESCOVAR%20OS%20DENTES");

			ClientResponse response5 = webResource6.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<AtividadeRealizada> listAtividadesProcuradas = obj
					.readValue(response5.getEntity(String.class),
							new TypeReference<ArrayList<AtividadeRealizada>>() {
							});

			for (AtividadeRealizada ar : listAtividadesProcuradas) {

				Date date5 = sdf.parse(ar.getDateHoraInicio());
				Date date6 = sdf.parse(ar.getDateHoraFim());
				ar.setDataHoraInicio(date5);
				ar.setDataHoraFim(date6);
			}

			// FUNCIONA
			/*
			 * WebResource webResource7 = client .resource(
			 * "http://localhost:8080/WaterLevel/WS2/atividadeRealizada/update"
			 * );
			 * 
			 * AtividadeRealizada ar2 =fachada.atividadeRealizadaProcurar(2);
			 * 
			 * 
			 * //ATUALIZANDO DADOS //SETANDO DATAs STRING
			 * 
			 * Date date7 = new Date(); Date date8 = new Date();
			 * 
			 * String teste3 = sdf.format(date7); String teste4 =
			 * sdf.format(date8);
			 * 
			 * ar2.setDateHoraInicio(teste3); ar2.setDateHoraFim(teste4);
			 * 
			 * ar2.setGasto(10.00);
			 * 
			 * //---------------------------------------
			 * 
			 * Usuario usuario=fachada.usuarioProcurar(ar2.getIdUsuario());
			 * ar2.setUsuario(usuario);
			 * 
			 * Atividade
			 * atividade=fachada.atividadeProcurar(ar2.getIdAtividade());
			 * ar2.setAtividade(atividade);
			 * 
			 * webResource7.type(MediaType.APPLICATION_JSON).put(ar2);
			 */

			// TA FUNCIONANDO
			/*WebResource webResource8 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/excluir/4");

			webResource8.type(MediaType.APPLICATION_JSON).delete(
					ClientResponse.class);*/

			WebResource webResource9 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/getUltimasAtividades");

			ClientResponse response6 = webResource9.type(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			Collection<AtividadeRealizada> list3 = obj.readValue(
					response6.getEntity(String.class),
					new TypeReference<ArrayList<Object>>() {
					});

			
			System.out.println("list1: " + list);
			System.out.println("list2: " + list2);
			System.out.println("existe: " + existe);
			// System.out.println(atividade);
			System.out.println("procuraAtividade: " + atividade2);
			System.out.println("listARProcuradas " + listAtividadesProcuradas);
			// System.out.println(ar2);
			System.out.println("list3: " + list3);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
