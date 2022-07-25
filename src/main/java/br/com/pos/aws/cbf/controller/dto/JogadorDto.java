package br.com.pos.aws.cbf.controller.dto;

import org.springframework.data.domain.Page;

import br.com.pos.aws.cbf.modelo.Jogador;
import br.com.pos.aws.cbf.modelo.Time;

public class JogadorDto {
	private Long id;
	
	private String nome;
	private String nascimento;//dd/mm/yyyy
	private String pais;
	private Time time;
	

	public JogadorDto(Jogador jogador) {
		super();
		this.id = jogador.getId();
		this.nome = jogador.getNome();
		this.nascimento = jogador.getNascimento();
		this.pais = jogador.getPais();
		this.time = jogador.getTime();
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
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}

	public static Page<JogadorDto> converter(Page<Jogador> jogadores) {
		return jogadores.map(JogadorDto::new);
	}
	
	
	
}
