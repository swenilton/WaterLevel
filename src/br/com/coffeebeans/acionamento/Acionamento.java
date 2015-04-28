package br.com.coffeebeans.acionamento;

import java.sql.Timestamp;

import br.com.coffeebeans.bomba.Bomba;

public class Acionamento {
	
	private int id;
	private Bomba bomba;
	private Timestamp dataHoraInicio;
	private Timestamp dataHoraFim;
	
	public Acionamento(Bomba bomba, Timestamp dataHoraInicio,
			Timestamp dataHoraFim) {
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

	public Timestamp getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Timestamp dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Timestamp getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Timestamp dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	@Override
	public String toString() {
		return "Acionamento [id=" + id + ", bomba=" + bomba
				+ ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim="
				+ dataHoraFim + "]";
	}
}
