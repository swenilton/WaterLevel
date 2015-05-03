package br.com.coffeebeans.acionamento;

import java.util.Date;

import br.com.coffeebeans.bomba.Bomba;

public class Acionamento {
	
	private int id;
	private Bomba bomba;
	private Date dataHoraInicio;
	private Date dataHoraFim;
	
	public Acionamento(Bomba bomba, Date dataHoraInicio,
			Date dataHoraFim) {
		super();
		this.bomba = bomba;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bomba getBomba() {
		return bomba;
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	@Override
	public String toString() {
		return "Acionamento [id=" + id + ", bomba=" + bomba
				+ ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim="
				+ dataHoraFim + "]";
	}
}
