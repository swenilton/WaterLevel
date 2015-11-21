package br.com.coffeebeans.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser jsonparser,
			DeserializationContext deserializationcontext) throws IOException,
			JsonProcessingException {

		SimpleDateFormat formatter;
		//formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date_temp = null;
		String date = jsonparser.getText();
		try {
			date_temp = formatter.parse(date);
			System.out.println(date_temp);
			System.out.println("uhu");
		} catch (ParseException ex) {
			new RuntimeException(ex);
		}
		return date_temp;

	}
}
