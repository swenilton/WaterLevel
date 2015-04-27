package br.com.coffeebeans.repositorio;

public class RepositorioCircular extends Repositorio {
	private Double diametroMedio;

	public RepositorioCircular(String descricao, Double capacidade,
			Double profundidade, Double limiteMinimo, Double limiteMaximo,
			Double diametroMedio) {
		super(descricao, capacidade, profundidade, limiteMinimo, limiteMaximo);
		this.diametroMedio = diametroMedio;
	}

	public Double getDiametroMedio() {
		return diametroMedio;
	}

	public void setDiametroMedio(Double diametroMedio) {
		this.diametroMedio = diametroMedio;
	}

	@Override
	public void calcularCapacidade() {
		
	}

}
