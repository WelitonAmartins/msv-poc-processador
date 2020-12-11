package br.com.lito.poc.processador.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import br.com.lito.poc.processador.dto.ColetaDto;
import lombok.extern.slf4j.Slf4j;

@Repository
@PropertySource("classpath:query/repo.properties")
@Slf4j
public class ProcessadorRepository {
	
	@Value("${SPI.COLETA}")
	private String queryInsertPedido;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void salvar(ColetaDto coletaDto) {
		final BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(coletaDto);
		namedParameterJdbcTemplate.update(queryInsertPedido, parameterSource);		
	}

	public void salvar(List<ColetaDto> lsColetaDto) {
		log.info("Inserindo {} coletas", lsColetaDto.size());
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(lsColetaDto.toArray());
		namedParameterJdbcTemplate.batchUpdate(queryInsertPedido, batch);	
		log.info("{} coletas inseridas", lsColetaDto.size());	
	}

}
