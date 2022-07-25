package br.com.pos.aws.cbf.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.pos.aws.cbf.modelo.Jogador;
import br.com.pos.aws.cbf.modelo.Time;
import br.com.pos.aws.cbf.modelo.Transferencia;

public class TransferenciaDto {
	
	private Long id;
	private LocalDateTime data;
	private Jogador jogador;
	private Time origem;
	private Time destino;
	private BigDecimal valor;
	
	
	
	public TransferenciaDto(Transferencia transferencia) {
		this.id = transferencia.getId();
		this.data = transferencia.getData();
		this.jogador = transferencia.getJogador();
		this.origem = transferencia.getOrigem();
		this.destino = transferencia.getDestino();
		this.valor = transferencia.getValor();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Time getOrigem() {
		return origem;
	}
	public void setOrigem(Time origem) {
		this.origem = origem;
	}
	public Time getDestino() {
		return destino;
	}
	public void setDestino(Time destino) {
		this.destino = destino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public static Page<TransferenciaDto> converter(Page<Transferencia> transferencias){
		return transferencias.map(TransferenciaDto::new);
	}
	
	
	
	
}
