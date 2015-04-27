package br.com.coffeebeans.bomba;

public class Bomba {
	private int codigo;
	private String descricao;
	private String status;

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

	public Bomba(String descricao, String status) {
		this.descricao = descricao;
		this.status = status;
	}

}
