package br.com.pi_adocao_pet.adapter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	// SourceClassName = origem do objeto que sera convertido
	// DestinationClassName = para qual classe o objeto tera um novo formato
	// mapper.map = faz a transferencia de informação de uma classe origem para seu
	// destino

	public static <SourceClassName, DestinationClassName> DestinationClassName parseObject(SourceClassName source,
			Class<DestinationClassName> destination) {
		return mapper.map(source, destination);
	}

	public static <SourceClassName, DestinationClassName> List<DestinationClassName> parseListObject(
		List<SourceClassName> source, Class<DestinationClassName> destination) {

		List<DestinationClassName> destinationObject = new ArrayList<>();

		for (Object obj : source) {
			destinationObject.add(mapper.map(obj, destination));
		}
		return destinationObject;
	}

}