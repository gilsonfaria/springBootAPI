package br.com.pos.aws.cbf.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pos.aws.cbf.controller.dto.PartidaDetalheDto;
import br.com.pos.aws.cbf.controller.dto.PartidaDto;
import br.com.pos.aws.cbf.controller.form.PartidaForm;
import br.com.pos.aws.cbf.modelo.Partida;
import br.com.pos.aws.cbf.repository.PartidaRepository;
import br.com.pos.aws.cbf.repository.TimeRepository;
import br.com.pos.aws.cbf.repository.TorneioRepository;

@RestController
public class PartidasController {

//		@Autowired
//		private JogadorRepository jogadorRepository;

		@Autowired 
		private TimeRepository timeRepository;
		
		@Autowired
		private TorneioRepository torneioRepository;
		
		@Autowired
		private PartidaRepository partidaRepository;

		@GetMapping
		@Cacheable(value = "listaPartidas")
		@RequestMapping("/partidas")
		public Page<PartidaDto> lista(Pageable paginacao){

			Page<Partida> partidas = partidaRepository.findAll(paginacao);
			return PartidaDto.converter(partidas);

		}
	
		@PostMapping
		@Transactional
		@CacheEvict(value = "listaPartidas", allEntries = true)
		@RequestMapping("/torneios/{idTorneio}/partidas")
		public ResponseEntity<PartidaDetalheDto> cadastrar(@PathVariable Long idTorneio, @RequestBody @Valid PartidaForm form, UriComponentsBuilder uriBuilder){
			
			
			
			Partida partida = form.converter(idTorneio, torneioRepository, timeRepository);
			partidaRepository.save(partida);
	
			URI uri = uriBuilder.path("/torneios/{idTorneio}/partidas/{idPartida}").buildAndExpand(idTorneio, partida.getId()).toUri();
			return ResponseEntity.created(uri).body(new PartidaDetalheDto(partida));
		}
	
		@GetMapping("/partidas/{id}")
		public ResponseEntity<PartidaDetalheDto> detalhar(@PathVariable Long id){
			Optional<Partida> partida = partidaRepository.findById(id);
	
			if(partida.isPresent()) {
				return ResponseEntity.ok(new PartidaDetalheDto(partida.get()));
			}
	
			return ResponseEntity.notFound().build();
		}

	
		@DeleteMapping("/partidas/{id}")
		@Transactional
		@CacheEvict(value = "listaPartidas", allEntries = true)
		public ResponseEntity<?> remover(@PathVariable Long id){
			try {
				partidaRepository.deleteById(id);		
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}
}
