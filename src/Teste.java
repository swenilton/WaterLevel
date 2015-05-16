import java.util.Scanner;
import br.com.coffeebeans.fachada.Fachada;
import br.com.coffeebeans.repositorio.Repositorio;
import br.com.coffeebeans.repositorio.RepositorioCircular;
import br.com.coffeebeans.repositorio.RepositorioRetangular;

public class Teste {

	public static void main(String[] args) {
		try {
			Repositorio repositorio = null;
			String descricao = null;
			Double capacidade = null;
			Double profundidade = null;
			Double limiteMinimo = null;
			Double limiteMaximo = null;
			Double areaBase;
			Double diametroMedio = null;

			int opcao = 0;

			Fachada fachada = Fachada.getInstance();
			Scanner in = new Scanner(System.in);

			while (opcao != 5) {
				System.out.println("1-Cadastrar repositorio");
				System.out.println("2-Listar repositorio");
				System.out.println("3-Remover repositorio");
				System.out.println("4-Atualizar repositorio");
				System.out.println("5-Sair");
				System.out.println("Escolha a opção");
				opcao = in.nextInt();

				if (opcao == 1) {
					System.out.println("Digite a descricao do repositorio");
					descricao = in.next();

					System.out.println("Digite a capacidade");
					capacidade = in.nextDouble();

					System.out.println("Digite a profundidade");
					profundidade = in.nextDouble();

					System.out.println("Digite o limite minimo");
					limiteMinimo = in.nextDouble();

					System.out.println("Digite o limite maximo");
					limiteMaximo = in.nextDouble();

					System.out.println("Digite o diâmetro médio");
					diametroMedio = in.nextDouble();

					repositorio = new RepositorioCircular(descricao,
							capacidade, profundidade, limiteMinimo,
							limiteMaximo, diametroMedio);

					fachada.cadastrar(repositorio);
				}
				if (opcao == 2) {
					System.out.println(fachada.repositorioListar().toString());
				}
				if (opcao == 3) {
					fachada.repositorioRemover(7);
				}
				if (opcao == 4) {

					System.out
							.println("Digite o id do repositório a ser atualizado:");

					repositorio = fachada.repositorioProcurar(in.nextInt());
					RepositorioRetangular repositorio2 = new RepositorioRetangular(descricao, capacidade, profundidade, limiteMinimo, limiteMaximo, diametroMedio);

					if (repositorio == null) {
						throw new NullPointerException();
					} else {
						repositorio2.setId(repositorio.getId());

						System.out.println("Digite a descricao do repositorio");
						descricao = in.next();

						repositorio2.setDescricao(descricao);

						System.out.println("Digite a capacidade");
						capacidade = in.nextDouble();

						repositorio2.setCapacidade(capacidade);

						System.out.println("Digite a profundidade");
						profundidade = in.nextDouble();
						
						repositorio2.setProfundidade(profundidade);

						System.out.println("Digite o limite minimo");
						limiteMinimo = in.nextDouble();
						
						repositorio2.setLimiteMinimo(limiteMinimo);
						
						System.out.println("Digite o limite maximo");
						limiteMaximo = in.nextDouble();
						
						repositorio2.setLimiteMaximo(limiteMaximo);
						
						System.out.println("Digite a área da base");
						areaBase = in.nextDouble();
						repositorio2.setAreaBase(areaBase);
						fachada.atualizar(repositorio2);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
