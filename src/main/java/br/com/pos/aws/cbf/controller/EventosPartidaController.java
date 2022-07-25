package br.com.pos.aws.cbf.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pos.aws.cbf.config.rabbitmq.QueueSender;
import br.com.pos.aws.cbf.controller.dto.PartidaDetalheDto;
import br.com.pos.aws.cbf.controller.form.EventoGolForm;
import br.com.pos.aws.cbf.controller.form.EventoInicioFimForm;
import br.com.pos.aws.cbf.modelo.Partida;
import br.com.pos.aws.cbf.modelo.Time;
import br.com.pos.aws.cbf.repository.PartidaRepository;
import br.com.pos.aws.cbf.repository.TimeRepository;

@RestController
public class EventosPartidaController {

	@Autowired
	private QueueSender queueSender;

	@Autowired
	private PartidaRepository partidaRepository;

	@Autowired
	private TimeRepository timeRepository;

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaPartidas", allEntries = true)
	@RequestMapping("/torneios/{idTorneio}/partidas/{idPartida}/inicio")
	public ResponseEntity<PartidaDetalheDto> inicioPartida(@PathVariable Long idTorneio, @PathVariable Long idPartida,
			@RequestBody @Valid EventoInicioFimForm form, UriComponentsBuilder uriBuilder) {

		try {
			Partida partida = partidaRepository.getReferenceById(idPartida);

			partida.setHoraInicio(form.getDataHora());

			queueSender.send("INICIO_PARTIDA: { \"torneioId\": " + partida.getTorneio().getId().toString() +
							 ", \"partidaId\": " + partida.getId().toString() + 
							 ", \"dataHora\": \"" + partida.getHoraInicio().toString() + "\" }");
			return ResponseEntity.ok(new PartidaDetalheDto(partida));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaPartidas", allEntries = true)
	@RequestMapping("/torneios/{idTorneio}/partidas/{idPartida}/fim")
	public ResponseEntity<PartidaDetalheDto> fimPartida(@PathVariable Long idTorneio, @PathVariable Long idPartida,
			@RequestBody @Valid EventoInicioFimForm form, UriComponentsBuilder uriBuilder) {

		try {
			Partida partida = partidaRepository.getReferenceById(idPartida);

			partida.setHoraFim(form.getDataHora());
			
			queueSender.send("FIM_PARTIDA: { \"torneioId\": " + partida.getTorneio().getId().toString() +
					 ", \"partidaId\": " + partida.getId().toString() + 
					 ", \"dataHora\": \"" + partida.getHoraFim().toString() + "\" }");

			return ResponseEntity.ok(new PartidaDetalheDto(partida));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaPartidas", allEntries = true)
	@RequestMapping("/torneios/{idTorneio}/partidas/{idPartida}/gol")
	public ResponseEntity<PartidaDetalheDto> gol(@PathVariable Long idTorneio, @PathVariable Long idPartida,
			@RequestBody @Valid EventoGolForm form, UriComponentsBuilder uriBuilder) {

		try {
			Partida partida = partidaRepository.getReferenceById(idPartida);

			Optional<Time> time = timeRepository.findById(form.getTimeId());

			if (partida.getTime1().getId() == time.get().getId()) {

				if (partida.getGolsTime1() == null) {
					partida.setGolsTime1(Long.parseLong("1"));

				} else {
					partida.setGolsTime1(partida.getGolsTime1() + 1);
				}

			} else {
				if (partida.getGolsTime2() == null) {
					partida.setGolsTime2(Long.parseLong("1"));
				} else {
					partida.setGolsTime2(partida.getGolsTime2() + 1);
				}
			}
			
			queueSender.send("GOL: { \"torneioId\": " + partida.getTorneio().getId().toString() +
					 ", \"partidaId\": " + partida.getId().toString() + 
					 ", \"timeId\": " + time.get().getId().toString() + "\" }");

			return ResponseEntity.ok(new PartidaDetalheDto(partida));
		} catch (Exception e) {
			
			return ResponseEntity.notFound().build();
		}
	}
}
