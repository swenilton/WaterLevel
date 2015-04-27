package br.com.coffeebeans.atividade;

import java.sql.Timestamp;

import br.com.coffeebeans.usuario.Usuario;

public class Atividade {
	
	private int id;
	private String descricao;
	private Timestamp dataHoraInicio;
	private Timestamp dataHoraFim;
	private Usuario usuario;
	
	public Atividade(String descricao, Timestamp dataHoraInicio,
			Timestamp dataHoraFim, Usuario usuario) {
		super();
		this.descricao = descricao;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", descricao=" + descricao
				+ ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim="
				+ dataHoraFim + ", usuario=" + usuario + "]";
	}	

}
