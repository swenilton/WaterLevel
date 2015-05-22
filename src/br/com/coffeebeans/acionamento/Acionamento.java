package br.com.coffeebeans.acionamento;

import java.sql.Timestamp;
import java.util.Date;

import br.com.coffeebeans.bomba.Bomba;

public class Acionamento {

	private int id;
	private Bomba bomba;
	private Timestamp dataHoraInicio;
	private Timestamp dataHoraFim;
	private int idBomba;

	public Acionamento(Bomba bomba, Timestamp dataHoraInicio,
			Timestamp dataHoraFim) {
		this.bomba = bomba;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
	}

	public Acionamento(Timestamp dataHoraInicio, Timestamp dataHoraFim,
			int idBomba) {
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.idBomba = idBomba;
	}

	public int getIdBomba() {
		return idBomba;
	}

	public void setIdBomba(int idBomba) {
		this.idBomba = idBomba;
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
		return "Acionamento [id=" + id + ", bomba=" + bomba.getDescricao()
				+ ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim="
				+ dataHoraFim + ", idBomba=" + idBomba + "]";
	}

}
