package br.com.coffeebeans.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomJsonDateSerializer extends JsonSerializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		System.out.println("entrou de boa");
		
		/*Timestamp t = new Timestamp(System.currentTimeMillis());
		String json = new Gson().toJson(t);
		json = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
		.toJson(t);
		Date data = new Gson().fromJson(json, Date.class); */
		
		/*DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String formattedDate = dateFormat.format(arg0);
		  arg1.writeString(formattedDate); */
				
		/*Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
	    String date = "\"2013-02-10T13:45:30+0100\"";
	    Date test = gson.fromJson(date, Date.class);
	    arg1.writeObject(test); */
		
		String formattedDate = dateFormat.format(date);		 
        gen.writeString(formattedDate);
	}
}
