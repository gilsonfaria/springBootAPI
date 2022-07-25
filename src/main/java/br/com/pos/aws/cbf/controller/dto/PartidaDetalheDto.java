package br.com.pos.aws.cbf.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.pos.aws.cbf.modelo.Partida;

public class PartidaDetalheDto {

	private Long id;
	private String local;
	private LocalDateTime dataPrevista;
	private Long golsTime1;
	private Long golsTime2;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private TorneioDto torneio;
	private TimeDto time1;
	private TimeDto time2;
	
	
	public PartidaDetalheDto(Partida partida) {
		this.id = partida.getId();
		this.local = partida.getLocal();
		this.dataPrevista = partida.getDataPrevista();
		this.golsTime1 = partida.getGolsTime1();
		this.golsTime2 = partida.getGolsTime2();
		this.horaInicio = partida.getHoraInicio();
		this.horaFim = partida.getHoraFim();
		this.torneio = new TorneioDto(partida.getTorneio());
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
	public TorneioDto getTorneio() {
		return torneio;
	}
	public TimeDto getTime1() {
		return time1;
	}
	public TimeDto getTime2() {
		return time2;
	}
	
	public static Page<PartidaDetalheDto> converter(Page<Partida> partidas){
		return partidas.map(PartidaDetalheDto::new);
	}
	
}
