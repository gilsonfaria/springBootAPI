package br.com.pos.aws.cbf.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.pos.aws.cbf.modelo.Partida;

public class PartidaDto {
	private Long id;
	private String local;
	private LocalDateTime dataPrevista;
	private Long golsTime1;
	private Long golsTime2;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private Long torneioId;
	private TimeDto time1;
	private TimeDto time2;
	
	
	public PartidaDto(Partida partida) {
		this.id = partida.getId();
		this.local = partida.getLocal();
		this.dataPrevista = partida.getDataPrevista();
		this.golsTime1 = partida.getGolsTime1();
		this.golsTime2 = partida.getGolsTime2();
		this.horaInicio = partida.getHoraInicio();
		this.horaFim = partida.getHoraFim();
		this.torneioId = partida.getTorneio().getId();
		this.time1 = new TimeDto(partida.getTime1());
		this.time2 = new TimeDto(partida.getTime2());
	}
	public Long getId() {
		return id;
	}
	public String getLocal() {
		return local;
	}
	public LocalDateTime getDataPrevista() {
		return dataPrevista;
	}
	public Long getGolsTime1() {
		return golsTime1;
	}
	public Long getGolsTime2() {
		return golsTime2;
	}
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	public LocalDateTime getHoraFim() {
		return horaFim;
	}
	public Long getTorneio() {
		return torneioId;
	}
	public TimeDto getTime1() {
		return time1;
	}
	public TimeDto getTime2() {
		return time2;
	}
	
	public static Page<PartidaDto> converter(Page<Partida> partidas){
		return partidas.map(PartidaDto::new);
	}
}
