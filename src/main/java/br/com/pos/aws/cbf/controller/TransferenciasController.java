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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pos.aws.cbf.controller.dto.TransferenciaDto;
import br.com.pos.aws.cbf.controller.form.TransferenciaForm;
import br.com.pos.aws.cbf.modelo.Transferencia;
import br.com.pos.aws.cbf.repository.JogadorRepository;
import br.com.pos.aws.cbf.repository.TimeRepository;
import br.com.pos.aws.cbf.repository.TransferenciaRepository;

@RestController
@RequestMapping("/transferencias")
public class TransferenciasController {

		@Autowired
		private JogadorRepository jogadorRepository;

		@Autowired 
		private TimeRepository timeRepository;
		
		@Autowired
		private TransferenciaRepository transferenciaRepository;

		@GetMapping
		@Cacheable(value = "listaTransferencia")
		public Page<TransferenciaDto> lista(@RequestParam(required = false) Long idJogador, Pageable paginacao){
			if(idJogador == null) {
				Page<Transferencia> transferencias = transferenciaRepository.findAll(paginacao);
				return TransferenciaDto.converter(transferencias);
			} else {
				Page<Transferencia> transferencias = transferenciaRepository.findByJogadorId(idJogador, paginacao);
				return TransferenciaDto.converter(transferencias);
			}
		}

		@PostMapping
		@Transactional
		@CacheEvict(value = "listaTransferencia", allEntries = true)
		public ResponseEntity<TransferenciaDto> cadastrar(@RequestBody @Valid TransferenciaForm form, UriComponentsBuilder uriBuilder){
			Transferencia transferencia = form.converter(timeRepository, jogadorRepository);
			transferenciaRepository.save(transferencia);

			URI uri = uriBuilder.path("/transferencias/{id}").buildAndExpand(transferencia.getId()).toUri();
			return ResponseEntity.created(uri).body(new TransferenciaDto(transferencia));
		}

		@GetMapping("/{id}")
		public ResponseEntity<TransferenciaDto> detalhar(@PathVariable Long id){
			Optional<Transferencia> transferencia = transferenciaRepository.findById(id);

			if(transferencia.isPresent()) {
				return ResponseEntity.ok(new TransferenciaDto(transferencia.get()));
			}

			return ResponseEntity.notFound().build();
		}
//
//
//
//		@PutMapping("/{id}")
//		@Transactional
//		@CacheEvict(value = "listaJogador", allEntries = true)
//		public ResponseEntity<JogadorDto> atualizar(@PathVariable Long id, @RequestBody @Valid JogadorForm form){
//
//			try {
//				Jogador jogador = jogadorRepository.getReferenceById(id);
//				jogador.setNome(form.getNome());
//				jogador.setNascimento(form.getNascimento());
//				jogador.setPais(form.getPais());
//				jogador.setTime(timeRepository.findById(form.getIdTime()).get());
//
//				return ResponseEntity.ok(new JogadorDto(jogador));
//			} catch (Exception exception) {
//				return ResponseEntity.notFound().build();
//			}			
//		}
//
		@DeleteMapping("/{id}")
		@Transactional
		@CacheEvict(value = "listaTransferencia", allEntries = true)
		public ResponseEntity<?> remover(@PathVariable Long id){
			try {
				transferenciaRepository.deleteById(id);		
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}
}
