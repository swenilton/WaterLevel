package br.com.coffeebeans.bomba;

public class Bomba {
	private int codigo;
	private String descricao;
	private String status;
	private Double potencia;
	private Double vazao;
	private String acionamento;
	public static final String MANUAL = "MANUAL";
	public static final String AUTOMATICO = "AUTOMATICO";

	public Bomba(String descricao, String status, Double potencia,
			Double vazao, String acionamento) throws Exception {
		this.descricao = descricao;
		this.status = status;
		this.potencia = potencia;
		this.vazao = vazao;
		if (acionamento != this.MANUAL && acionamento != this.AUTOMATICO)
			throw new Exception("Modo de acionamento inválido");
		else
			this.acionamento = acionamento;
	}

	public String getAcionamento() {
		return acionamento;
	}

	public void setAcionamento(String acionamento) {
		this.acionamento = acionamento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPotencia() {
		return potencia;
	}

	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}

	public Double getVazao() {
		return vazao;
	}

	public void setVazao(Double vazao) {
		this.vazao = vazao;
	}
}
