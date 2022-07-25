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

import br.com.pos.aws.cbf.controller.dto.JogadorDto;
import br.com.pos.aws.cbf.controller.form.JogadorForm;
import br.com.pos.aws.cbf.modelo.Jogador;
import br.com.pos.aws.cbf.repository.JogadorRepository;
import br.com.pos.aws.cbf.repository.TimeRepository;


@RestController
@RequestMapping("/jogadores")
public class JogadoresController {


	@Autowired
	private JogadorRepository jogadorRepository;

	@Autowired 
	private TimeRepository timeRepository;

	@GetMapping
	@Cacheable(value = "listaJogador")
	public Page<JogadorDto> lista(@RequestParam(required = false) String nome, Pageable paginacao){
		if(nome == null) {
			Page<Jogador> jogadores = jogadorRepository.findAll(paginacao);
			return JogadorDto.converter(jogadores);
		} else {
			Page<Jogador> jogadores = jogadorRepository.findByNome(nome, paginacao);
			return JogadorDto.converter(jogadores);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaJogador", allEntries = true)
	public ResponseEntity<JogadorDto> cadastrar(@RequestBody @Valid JogadorForm form, UriComponentsBuilder uriBuilder){
		Jogador jogador = form.converter(timeRepository);
		jogadorRepository.save(jogador);

		URI uri = uriBuilder.path("/jogadores/{id}").buildAndExpand(jogador.getId()).toUri();
		return ResponseEntity.created(uri).body(new JogadorDto(jogador));
	}

	@GetMapping("/{id}")
	public ResponseEntity<JogadorDto> detalhar(@PathVariable Long id){
		Optional<Jogador> jogador = jogadorRepository.findById(id);

		if(jogador.isPresent()) {
			return ResponseEntity.ok(new JogadorDto(jogador.get()));
		}

		return ResponseEntity.notFound().build();
	}



	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaJogador", allEntries = true)
	public ResponseEntity<JogadorDto> atualizar(@PathVariable Long id, @RequestBody @Valid JogadorForm form){

		try {
			Jogador jogador = jogadorRepository.getReferenceById(id);
			jogador.setNome(form.getNome());
			jogador.setNascimento(form.getNascimento());
			jogador.setPais(form.getPais());
			jogador.setTime(timeRepository.findById(form.getIdTime()).get());

			return ResponseEntity.ok(new JogadorDto(jogador));
		} catch (Exception exception) {
			return ResponseEntity.notFound().build();
		}			
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaJogador", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			jogadorRepository.deleteById(id);		
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
