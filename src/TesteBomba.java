import java.util.Scanner;

import br.com.coffeebeans.bomba.Bomba;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;
import br.com.coffeebeans.fachada.Fachada;

public class TesteBomba {

	public static void main(String[] args) {
		try {
			Bomba bomba = null;
			String descricao = null;
			String status = null;
			Double potencia = null;
			Double vazao = null;
			String acionamento = null;
			int idRepEnc = 0;
			int idRepSec = 0;

			int opcao = 0;

			Fachada fachada = Fachada.getInstance();
			Scanner in = new Scanner(System.in);

			while (opcao != 5) {
				System.out.println("1-Cadastrar bomba");
				System.out.println("2-Listar bomba");
				System.out.println("3-Remover bomba");
				System.out.println("4-Atualizar bomba");
				System.out.println("5-Sair");
				System.out.println("Escolha a opção");
				opcao = in.nextInt();

				if (opcao == 1) {
					System.out.println("Digite a descricao da bomba");
					descricao = in.next();

					System.out.println("Digite o status");
					status = in.next();

					System.out.println("Digite a potencia");
					potencia = in.nextDouble();

					System.out.println("Digite a vazao");
					vazao = in.nextDouble();

					System.out.println("Digite o modo de acionamento");
					acionamento = in.next();

					System.out
							.println("Digite o código do repositorio a se encher");
					idRepEnc = in.nextInt();

					System.out
							.println("Digite o código do repositorio a se secar");
					idRepSec = in.nextInt();

					bomba = new Bomba(descricao, status, potencia, vazao,
							acionamento, idRepEnc);
					bomba.setIdRepositorioSeca(idRepSec);
					fachada.cadastrar(bomba);
				}
				if (opcao == 2) {
					System.out.println(fachada.bombaListar().toString());
				}
				if (opcao == 3) {
					fachada.bombaRemover(1);
				}
				if (opcao == 4) {

					System.out
							.println("Digite o id da bomba a ser atualizada:");

					bomba = fachada.bombaProcurar(in.nextInt());
					Bomba bomba2 = new Bomba(descricao, status, potencia,
							vazao, acionamento, idRepEnc);
					bomba2.setIdRepositorioSeca(bomba.getIdRepositorioSeca());

					if (bomba == null) {
						throw new NullPointerException();
					} else {
						bomba2.setCodigo(bomba.getCodigo());

						System.out.println("Digite a descricao da bomba");
						descricao = in.next();
						bomba2.setDescricao(descricao);

						System.out.println("Digite o status");
						status = in.next();
						bomba2.setStatus(status);

						System.out.println("Digite a potência");
						potencia = in.nextDouble();
						bomba2.setPotencia(potencia);

						System.out.println("Digite a vazão");
						vazao = in.nextDouble();
						bomba2.setVazao(vazao);

						System.out.println("Digite o acionamento");
						acionamento = in.next();
						bomba2.setAcionamento(acionamento);

						System.out
								.println("Digite o código do repositorio a se encher");
						idRepEnc = in.nextInt();

						bomba2.setIdRepositorioEnche(idRepEnc);

						System.out
								.println("Digite o código do repositorio a se secar");
						idRepSec = in.nextInt();
						bomba2.setIdRepositorioSeca(idRepSec);

						fachada.atualizar(bomba2);

					}
				}
			}

		} catch (ViolacaoChaveEstrangeiraException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
