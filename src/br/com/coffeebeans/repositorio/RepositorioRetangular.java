package br.com.coffeebeans.repositorio;

public class RepositorioRetangular extends Repositorio {
	private Double areaBase;

	public RepositorioRetangular(String descricao, Double capacidade,
			Double profundidade, Double limiteMinimo, Double limiteMaximo,
			Double areaBase) {
		super(descricao, capacidade, profundidade, limiteMinimo, limiteMaximo);
		this.areaBase = areaBase;
	}

   @Override
public String toString() {
	return super.toString()+ " areaBase="+areaBase +"\n";
}
	public Double getAreaBase() {
		return areaBase;
	}

	public void setAreaBase(Double areaBase) {
		this.areaBase = areaBase;
	}

	@Override
	public void calcularCapacidade() {
		
	}

}
