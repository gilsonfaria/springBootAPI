package br.com.pos.aws.cbf.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.pos.aws.cbf.modelo.Torneio;

public class TorneioForm {
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull
	private LocalDateTime dataInicio;
	
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
	
	public Torneio converter() {
		return new Torneio(nome, dataInicio);
	}
	
}
