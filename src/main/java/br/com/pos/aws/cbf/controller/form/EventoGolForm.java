package br.com.pos.aws.cbf.controller.form;

import javax.validation.constraints.NotNull;

public class EventoGolForm {
	@NotNull
	private Long timeId;

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
}
