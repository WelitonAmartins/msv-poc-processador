package br.com.lito.poc.processador.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
	}

	/**
	 * Classe que o conteúdo de um json e tenta converter para uma classe de acordo
	 * com o tipo passado.
	 * @param <T>  - Classe que representa do tipo de objeto as ser deserializado
	 * @param conteudoJson - String do Json do objeto
	 * @param tipoObjeto - Tipo do objeto
	 * @return 0bjeto gerado com o Json
	 * @throws Exception 
	 */
	public static <T> T paraObjViaJson(final String conteudoJson, final TypeReference<T> tipoObjeto) throws IOException {
		try {
			return mapper.readValue(conteudoJson, tipoObjeto);
		} catch (final IOException e) {
			throw new IOException("Erro ao tentar converter Json.");
		}
	}
}
