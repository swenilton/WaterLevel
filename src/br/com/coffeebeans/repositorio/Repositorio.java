package br.com.coffeebeans.repositorio;

import br.com.coffeebeans.bomba.Bomba;

public abstract class Repositorio {
	private int id;
	private String descricao;
	private Double capacidade;
	private Double profundidade;
	// private Double areaBase;
	// private Double diametro;
	private Double limiteMaximo;
	private Double limiteMinimo;
	private Bomba bomba;
	private int idBomba;

	// public static final String CIRCULAR = "CIRCULAR";
	// public static final String RETANGULAR = "RETANGULAR";

	public Repositorio(String descricao, Double capacidade,
			Double profundidade, Double limiteMinimo, Double limiteMaximo) {
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.profundidade = profundidade;
		this.limiteMinimo = limiteMinimo;
		this.limiteMaximo = limiteMaximo;

	}

	public int getIdBomba() {
		return idBomba;
	}



	public void setIdBomba(int idBomba) {
		this.idBomba = idBomba;
	}



	@Override
	public String toString() {
		return "Repositorio [id=" + id + ", descricao=" + descricao
				+ ", capacidade=" + capacidade + ", profundidade="
				+ profundidade + "limiteMaximo=" + limiteMaximo
				+ ", limiteMinimo=" + limiteMinimo + "]";
	}

	
	public Bomba getBomba() {
		return bomba;
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
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

	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public Double getLimiteMaximo() {
		return limiteMaximo;
	}

	public void setLimiteMaximo(Double limiteMaximo) {
		this.limiteMaximo = limiteMaximo;
	}

	public Double getLimiteMinimo() {
		return limiteMinimo;
	}

	public void setLimiteMinimo(Double limiteMinimo) {
		this.limiteMinimo = limiteMinimo;
	}
	
	public abstract void calcularCapacidade();
}
