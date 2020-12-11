package br.com.lito.poc.processador.consumer;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.lito.poc.processador.dto.ColetaDto;
import br.com.lito.poc.processador.service.ProcessadorService;
import br.com.lito.poc.processador.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@Autowired
	private ProcessadorService service;

	@KafkaListener(topics = "${topic.kafka.coleta}", groupId = "${topic.kafka.grupo.coleta}")
	public void consumer(String message) throws IOException {
//		log.info("Consumindo: {}", message);

		final StopWatch sw = new StopWatch();

//		sw.start("Converter mensagem");
		ColetaDto coletaDto = JsonUtils.paraObjViaJson(message, new TypeReference<ColetaDto>() {
		});
//		sw.stop();

//		sw.start("Salvar na base");
		service.salvar(coletaDto);
//		sw.stop();

//		log.debug("Fim; {}", sw.prettyPrint());
//		log.info("Fim; {}", sw.shortSummary());
	}

	@KafkaListener(topics = "${topic.kafka.coleta_lista}", groupId = "${topic.kafka.grupo.coleta_lista}")
	public void consumerLista(String message) throws IOException {
		log.info("Inicio Consumer lista ");

		final StopWatch sw = new StopWatch();

		sw.start("Converter mensagem");
		List<ColetaDto> lsColetaDto = JsonUtils.paraObjViaJson(message, new TypeReference<List<ColetaDto>>() {
		});
		sw.stop();

		sw.start("Salvar na base");
		service.salvar(lsColetaDto);
		sw.stop();

		log.debug("Fim; {}", sw.prettyPrint());
		log.info("Fim; {}", sw.shortSummary());

	}

}
