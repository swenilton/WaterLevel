import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import br.com.coffeebeans.acionamento.Acionamento;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;
import br.com.coffeebeans.fachada.Fachada;

public class TesteAcionamento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Acionamento acionamento = null;
			Timestamp inicio = null;
			Timestamp fim = null;

			int idBomba = 0;

			int opcao = 0;

			Fachada fachada = Fachada.getInstance();
			Scanner in = new Scanner(System.in);

			while (opcao != 5) {
				System.out.println("1-Cadastrar acionamento");
				System.out.println("2-Listar acionamento");
				System.out.println("3-Remover acionamento");
				System.out.println("4-Atualizar acionamento");
				System.out.println("5-Sair");
				System.out.println("Escolha a opção");
				opcao = in.nextInt();

				// limpando o buffer
				in.nextLine();

				if (opcao == 1) {
					System.out.println("Digite o inicio do acionamento");
					String inicioString = in.nextLine();

					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					sdf.setLenient(false);
					inicio = Timestamp.valueOf(inicioString);

					System.out.println("Digite o fim");
					String fimString = in.nextLine();
					fim = Timestamp.valueOf(fimString);

					System.out.println("Digite o id da bomba");
					idBomba = in.nextInt();

					acionamento = new Acionamento(inicio, fim, idBomba);

					fachada.cadastrar(acionamento);
				}
				if (opcao == 2) {
					System.out.println(fachada.acionamentoListar().toString());
				}
				if (opcao == 3) {
					fachada.acionamentoRemover(1);
				}
				if (opcao == 4) {

					System.out
							.println("Digite o id do acionamento a ser atualizado:");

					acionamento = fachada.acionamentoProcurar(in.nextInt());
					Acionamento acionamento2 = new Acionamento(inicio, fim,
							idBomba);
					// limpando o buffer
					in.nextLine();

					if (acionamento == null) {
						throw new NullPointerException();
					} else {
						acionamento2.setId(acionamento.getId());
						System.out.println("Digite o inicio do acionamento");

						String inicioString = in.nextLine();

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						sdf.setLenient(false);

						inicio = Timestamp.valueOf(inicioString);

						acionamento2.setDataHoraInicio(inicio);

						System.out.println("Digite o fim");
						String fimString = in.nextLine();
						fim = Timestamp.valueOf(fimString);

						acionamento2.setDataHoraFim(fim);

						System.out.println("Digite o id da bomba");
						idBomba = in.nextInt();
						acionamento2.setIdBomba(idBomba);
						fachada.atualizar(acionamento2);

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
