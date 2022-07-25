package br.com.pos.aws.cbf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Time {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String localidade;
	
	public Time() {
		
	}
	
	public Time(Long id, String nome, String localidade) {
		this.id = id;
		this.nome = nome;
		this.localidade = localidade;
	}
	
	public Time(String nome, String localidade) {
		this.nome = nome;
		this.localidade = localidade;
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
	
	public String getLocalidade() {
		return localidade;
	}
	
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
	
}
