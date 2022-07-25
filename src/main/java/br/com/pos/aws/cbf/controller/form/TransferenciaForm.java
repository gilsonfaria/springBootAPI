package br.com.pos.aws.cbf.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pos.aws.cbf.modelo.Jogador;
import br.com.pos.aws.cbf.modelo.Time;
import br.com.pos.aws.cbf.modelo.Transferencia;
import br.com.pos.aws.cbf.repository.JogadorRepository;
import br.com.pos.aws.cbf.repository.TimeRepository;

public class TransferenciaForm {
	@NotNull
	private LocalDateTime data;
	@NotNull
	private Long idJogador;
	@NotNull
	private Long idTimeOrigem;
	@NotNull
	private Long idTimeDestino;
	@NotNull
	private BigDecimal valor;
	
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Long getIdJogador() {
		return idJogador;
	}
	public void setIdJogador(Long idJogador) {
		this.idJogador = idJogador;
	}
	public Long getIdTimeOrigem() {
		return idTimeOrigem;
	}
	public void setIdTimeOrigem(Long idTimeOrigem) {
		this.idTimeOrigem = idTimeOrigem;
	}
	public Long getIdTimeDestino() {
		return idTimeDestino;
	}
	public void setIdTimeDestino(Long idTimeDestino) {
		this.idTimeDestino = idTimeDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Transferencia converter(TimeRepository timeRepository, JogadorRepository jogadorRepository) {
		
		Optional<Jogador> jogador = jogadorRepository.findById(idJogador);
		Optional<Time> timeOrigem = timeRepository.findById(idTimeOrigem);
		Optional<Time> timeDestino = timeRepository.findById(idTimeDestino);
		
		if(!jogador.isPresent() || !timeDestino.isPresent() || !timeOrigem.isPresent()) {
			return null;
		}
		
		return new Transferencia(jogador.get(), timeOrigem.get(), timeDestino.get(), valor, data);
	}
	
	
	
	
}
