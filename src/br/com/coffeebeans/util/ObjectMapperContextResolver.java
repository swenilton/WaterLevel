package br.com.coffeebeans.util;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import javax.ws.rs.core.MediaType;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObjectMapperContextResolver implements
		ContextResolver<ObjectMapper> {
	final ObjectMapper mapper = new ObjectMapper();

	public ObjectMapperContextResolver() {
		SimpleModule module = new SimpleModule(null,null);
		module.addSerializer(new CustomJsonDateSerializer());
		mapper.registerModule(module);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}

}
