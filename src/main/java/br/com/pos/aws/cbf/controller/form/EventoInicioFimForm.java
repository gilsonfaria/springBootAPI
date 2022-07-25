package br.com.pos.aws.cbf.controller.form;

import java.time.LocalDateTime;

import io.micrometer.core.lang.NonNull;

public class EventoInicioFimForm {
	@NonNull
	private LocalDateTime dataHora;

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	
}
