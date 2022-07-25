package br.com.pos.aws.cbf.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.pos.aws.cbf.modelo.Torneio;

public class TorneioDto {
	private Long id;
	private String nome;
	private LocalDateTime dataInicio;
	//private List<PartidaDto> partidas;
	
	
	public TorneioDto(Torneio torneio) {
		this.id = torneio.getId();
		this.nome = torneio.getNome();
		this.dataInicio = torneio.getDataInicio();
		//this.partidas = new ArrayList<>();
		//this.partidas.addAll(torneio.getPartidas().stream().map(PartidaDto::new).collect(Collectors.toList()));
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
//	public List<PartidaDto> getPartidas() {
//		return partidas;
//	}
//	public void setPartidas(List<PartidaDto> partidas) {
//		this.partidas = partidas;
//	}
	
	public static Page<TorneioDto> converter(Page<Torneio> torneios){
		return torneios.map(TorneioDto::new);
	}
	
	
}
