package br.com.pos.aws.cbf.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Torneio {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDateTime dataInicio;
	@OneToMany(mappedBy = "torneio")
	private List<Partida> partidas = new ArrayList<>();
	
	public Torneio() {
		
	}
	public Torneio(String nome, LocalDateTime dataInicio) {
		super();
		this.nome = nome;
		this.dataInicio = dataInicio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDateTime getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}


	public List<Partida> getPartidas() {
		return partidas;
	}


	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
	
}
