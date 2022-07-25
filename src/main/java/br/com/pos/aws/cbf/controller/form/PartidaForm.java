package br.com.pos.aws.cbf.controller.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.pos.aws.cbf.modelo.Partida;
import br.com.pos.aws.cbf.modelo.Time;
import br.com.pos.aws.cbf.modelo.Torneio;
import br.com.pos.aws.cbf.repository.TimeRepository;
import br.com.pos.aws.cbf.repository.TorneioRepository;

public class PartidaForm {
	@NotNull @NotEmpty @Length(min = 5)
	private String local;
	@NotNull
	private LocalDateTime dataPrevista;
	
	@NotNull
	private Long time1Id;
	@NotNull
	private Long time2Id;
	
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public LocalDateTime getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(LocalDateTime dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public Long getTime1Id() {
		return time1Id;
	}
	public void setTime1Id(Long time1Id) {
		this.time1Id = time1Id;
	}
	public Long getTime2Id() {
		return time2Id;
	}
	public void setTime2Id(Long time2Id) {
		this.time2Id = time2Id;
	}
	
	public Partida converter(Long torneioId, TorneioRepository torneioRepository, TimeRepository timeRepository) {
		Optional<Torneio> torneio = torneioRepository.findById(torneioId);
		Optional<Time> time1 = timeRepository.findById(time1Id);
		Optional<Time> time2 = timeRepository.findById(time2Id);
		
		if(torneio.isPresent() && time1.isPresent() && time2.isPresent()) {
			return new Partida(local, dataPrevista, torneio.get(), time1.get(), time2.get());
		}
		return null;
	}
	
}
