package br.com.pos.aws.cbf.controller.dto;

import org.springframework.data.domain.Page;

import br.com.pos.aws.cbf.modelo.Time;

public class TimeDto {

	private Long id;
	private String nome;
	private String localidade;
	
	
	public TimeDto( String nome, String localidade) {
		this.nome = nome;
		this.localidade = localidade;
	}
	
	public TimeDto(Time time) {
		this.id = time.getId();
		this.nome = time.getNome();
		this.localidade = time.getLocalidade();
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
	
	public static Page<TimeDto> converter(Page<Time> times){
		return times.map(TimeDto::new);
	}
}
