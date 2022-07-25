package br.com.pos.aws.cbf.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.pos.aws.cbf.modelo.Jogador;
import br.com.pos.aws.cbf.modelo.Time;
import br.com.pos.aws.cbf.repository.TimeRepository;

public class JogadorForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 10, max = 10)
	private String nascimento;//dd/mm/yyyy
	@NotNull @NotEmpty
	private String pais;
	
	private Long idTime;

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
	public Long getIdTime() {
		return idTime;
	}
	public void setIdTime(Long idTime) {
		this.idTime = idTime;
	}
	
	public Jogador converter(TimeRepository timeRepository) {
		Optional<Time> time = timeRepository.findById(idTime);
		if(time.isPresent()) {
			return new Jogador(nome, nascimento, pais, time.get());
		}
		return new Jogador(nome,nascimento,pais,null);
				
	}
}
