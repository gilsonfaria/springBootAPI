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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pos.aws.cbf.controller.dto.TorneioDetalheDto;
import br.com.pos.aws.cbf.controller.dto.TorneioDto;
import br.com.pos.aws.cbf.controller.form.TorneioForm;
import br.com.pos.aws.cbf.modelo.Torneio;
import br.com.pos.aws.cbf.repository.TorneioRepository;

@RestController
@RequestMapping("/torneios")
public class TorneioController {
//	@Autowired
//	private JogadorRepository jogadorRepository;
//
//	@Autowired 
//	private TimeRepository timeRepository;
	
	@Autowired
	private TorneioRepository torneioRepository;

	@GetMapping
	@Cacheable(value = "listaTorneio")
	public Page<TorneioDto> lista(@RequestParam(required = false) String nome, Pageable paginacao){
		if(nome == null) {
			Page<Torneio> torneios = torneioRepository.findAll(paginacao);
			return TorneioDto.converter(torneios);
		} else {
			Page<Torneio> jogadores = torneioRepository.findByNome(nome, paginacao);
			return TorneioDto.converter(jogadores);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaTorneio", allEntries = true)
	public ResponseEntity<TorneioDetalheDto> cadastrar(@RequestBody @Valid TorneioForm form, UriComponentsBuilder uriBuilder){
		Torneio torneio = form.converter();
		torneioRepository.save(torneio);

		URI uri = uriBuilder.path("/torneios/{id}").buildAndExpand(torneio.getId()).toUri();
		return ResponseEntity.created(uri).body(new TorneioDetalheDto(torneio));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TorneioDetalheDto> detalhar(@PathVariable Long id){
		Optional<Torneio> torneio = torneioRepository.findById(id);

		if(torneio.isPresent()) {
			return ResponseEntity.ok(new TorneioDetalheDto(torneio.get()));
		}

		return ResponseEntity.notFound().build();
	}



	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaTorneio", allEntries = true)
	public ResponseEntity<TorneioDetalheDto> atualizar(@PathVariable Long id, @RequestBody @Valid TorneioForm form){

		try {
			Torneio torneio = torneioRepository.getReferenceById(id);
			torneio.setNome(form.getNome());
			torneio.setDataInicio(form.getDataInicio());

			return ResponseEntity.ok(new TorneioDetalheDto(torneio));
		} catch (Exception exception) {
			return ResponseEntity.notFound().build();
		}			
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaTorneio", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			torneioRepository.deleteById(id);		
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
