//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Date;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.atividade.AtividadeRealizada;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import br.com.coffeebeans.usuario.Usuario;

public class ClientWebServiceAtividadeRealizada {
	static WebResource webResource3;

	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			clientConfig.getClasses().add(JacksonJsonProvider.class);

			Client client = Client.create(clientConfig);

//			WebResource webResource = client
//					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/all");
//
//			ClientResponse response = webResource.type(
//					MediaType.APPLICATION_JSON).get(ClientResponse.class);
//
//			ObjectMapper obj = new ObjectMapper();
//
//			Collection<AtividadeRealizada> list = obj.readValue(
//					response.getEntity(String.class),
//					new TypeReference<ArrayList<Object>>() {
//					});
//
//			WebResource webResource2 = client
//					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/all/1");
//
//			ClientResponse response2 = webResource2.type(
//					MediaType.APPLICATION_JSON).get(ClientResponse.class);
//
//			Collection<AtividadeRealizada> list2 = obj.readValue(
//					response2.getEntity(String.class),
//					new TypeReference<ArrayList<Object>>() {
//					});

			WebResource webResource3 = client
					.resource("http://localhost:8080/WaterLevel/WS2/atividadeRealizada/add");

			Usuario usuario = new Usuario("teste", "teste", "teste",
					"a@b.czao", "SIM", "ADMINISTRADOR");
			usuario.setId(1);

			Atividade atividade = new Atividade("TESTE3");
			atividade.setId(1);

			/*String dataIni = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
			.format(new Date());*/
			
			
			/*Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());  
			long vai = dataDeHoje.getTime();
			
			Date data=new Date(vai);
			Date data2=new Date(vai);*/
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dataUtil = sdf.parse("2015-11-19 00:00:00");
			Date dataUtil2 = sdf.parse("2015-11-19 00:00:00");*/
			
			/*Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		    String date = "\"2013-02-10T13:45:30+0100\"";
		    Date test = gson.fromJson(date, Date.class);
		    Date test2 = gson.fromJson(date, Date.class);*/
		    
			AtividadeRealizada ar = new AtividadeRealizada(atividade,new Date(),new Date()
			,usuario, 0.00);
			
			webResource3.type(MediaType.APPLICATION_JSON).post(ar);

			//System.out.println(list);
			//System.out.println(list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void post(Object requestEntity) {
		try {
			webResource3.type(MediaType.APPLICATION_JSON).post(requestEntity);
		} catch (Exception e) {
			System.out.println("erro post " + e.getMessage());
		}
	}
}
