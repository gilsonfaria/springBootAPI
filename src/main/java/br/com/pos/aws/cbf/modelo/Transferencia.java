package br.com.pos.aws.cbf.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transferencia {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime data = LocalDateTime.now();
	private BigDecimal valor;
	
	@ManyToOne
	private Jogador jogador;
	@ManyToOne
	private Time origem;
	@ManyToOne
	private Time destino;
	
	public Transferencia() {
		
	}

	public Transferencia(Jogador jogador, Time origem, Time destino, BigDecimal valor, LocalDateTime data) {
		super();
		this.jogador = jogador;
		this.origem = origem;
		this.destino = destino;
		this.valor = valor;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
}
