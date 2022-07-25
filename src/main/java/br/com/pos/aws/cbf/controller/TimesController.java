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

import br.com.pos.aws.cbf.controller.dto.TimeDto;
import br.com.pos.aws.cbf.controller.form.TimeForm;
import br.com.pos.aws.cbf.modelo.Time;
import br.com.pos.aws.cbf.repository.TimeRepository;

@RestController
@RequestMapping("/times")
public class TimesController {
	
	@Autowired
	private TimeRepository timeRepository;
	
	@GetMapping
	@Cacheable(value = "listaTime")
	public Page<TimeDto> lista(@RequestParam(required = false) String nome, Pageable paginacao){
		if(nome == null) {
			Page<Time> times = timeRepository.findAll(paginacao);
			return TimeDto.converter(times);
		} else {
			Page<Time> times = timeRepository.findByNome(nome, paginacao);
			return TimeDto.converter(times);
		}
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaTime", allEntries = true)
	public ResponseEntity<TimeDto> cadastrar(@RequestBody @Valid TimeForm form, UriComponentsBuilder uriBuilder){
		Time time = form.converter();
		timeRepository.save(time);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(time.getId()).toUri();
		return ResponseEntity.created(uri).body(new TimeDto(time));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TimeDto> detalhar(@PathVariable Long id){
		Optional<Time> time = timeRepository.findById(id);
		
		if(time.isPresent()) {
			return ResponseEntity.ok(new TimeDto(time.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaTime", allEntries = true)
	public ResponseEntity<TimeDto> atualizar(@PathVariable Long id, @RequestBody @Valid TimeForm form){
		
		try {
			Time time = timeRepository.getReferenceById(id);
			time.setNome(form.getNome());
			time.setLocalidade(form.getLocalidade());
			
			return ResponseEntity.ok(new TimeDto(time));
		} catch (Exception exception) {
			return ResponseEntity.notFound().build();
		}
		
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaTime", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		timeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	

}
