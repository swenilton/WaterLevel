package br.com.coffeebeans.acionamento;

import java.sql.Timestamp;
import java.util.Date;

import br.com.coffeebeans.bomba.Bomba;

public class Acionamento {

	private int id;
	private Bomba bomba;
	private Date dataHoraInicio;
	private Date dataHoraFim;
	private int idBomba;

	public Acionamento(Bomba bomba, Date dataHoraInicio,
			Date dataHoraFim) {
		this.bomba = bomba;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
	}

	public Acionamento(Date dataHoraInicio, Date dataHoraFim,
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
		return "Acionamento [id=" + id + ", bomba=" + bomba.getDescricao()
				+ ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim="
				+ dataHoraFim + ", idBomba=" + idBomba + "]";
	}

}
