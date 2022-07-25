package br.com.pos.aws.cbf.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Partida {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String local;
	private LocalDateTime dataPrevista;
	private Long golsTime1;
	private Long golsTime2;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	@ManyToOne
	private Torneio torneio;
	@ManyToOne
	private Time time1;
	@ManyToOne
	private Time time2;
	
	public Partida() {
		
	}
	
	public Partida(String local, LocalDateTime dataPrevista, Torneio torneio, Time time1, Time time2) {
		super();
		this.local = local;
		this.dataPrevista = dataPrevista;
		this.torneio = torneio;
		this.time1 = time1;
		this.time2 = time2;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Long getGolsTime1() {
		return golsTime1;
	}
	public void setGolsTime1(Long golsTime1) {
		this.golsTime1 = golsTime1;
	}
	public Long getGolsTime2() {
		return golsTime2;
	}
	public void setGolsTime2(Long golsTime2) {
		this.golsTime2 = golsTime2;
	}
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalDateTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalDateTime horaFim) {
		this.horaFim = horaFim;
	}
	public Torneio getTorneio() {
		return torneio;
	}
	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}
	public Time getTime1() {
		return time1;
	}
	public void setTime1(Time time1) {
		this.time1 = time1;
	}
	public Time getTime2() {
		return time2;
	}
	public void setTime2(Time time2) {
		this.time2 = time2;
	}
	
	
	
}
