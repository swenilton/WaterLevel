import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.com.coffeebeans.atividade.AtividadeRealizada;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;
import br.com.coffeebeans.fachada.Fachada;

public class TesteAtividadeRealizada {

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			System.out.println(sdf.format(new Date()));
			AtividadeRealizada atividadeRealizada = null;
			Date inicio = null;
			Date fim = null;

			int idUsuario = 0;
			int idAtividade = 0;
			int opcao = 0;

			Fachada fachada = Fachada.getInstance();
			Scanner in = new Scanner(System.in);

			while (opcao != 5) {
				System.out.println("1-Cadastrar atividade realizada");
				System.out.println("2-Listar");
				System.out.println("3-Remover");
				System.out.println("4-Atualizar");
				System.out.println("5-Sair");
				System.out.println("Escolha a opcao");
				opcao = in.nextInt();
				
				// limpando o buffer
				in.nextLine();

				if (opcao == 1) {
					System.out
							.println("Digite o inicio da atividade realizada");
					String inicioString = in.nextLine();

					sdf.setLenient(false);
					inicio = sdf.parse(inicioString);

					System.out.println("Digite o fim");
					String fimString = in.nextLine();
					fim = sdf.parse(fimString);

					System.out.println("Digite o id do usuário");
					idUsuario = in.nextInt();

					System.out.println("Digite o id da atividade");
					idAtividade = in.nextInt();

					atividadeRealizada = new AtividadeRealizada(inicio, fim,
							idUsuario, idAtividade);

					fachada.cadastrar(atividadeRealizada);
				}
				if (opcao == 2) {
					System.out.println(fachada.atividadeRealidadaListar()
							.toString());
				}
				if (opcao == 3) {
					fachada.atividadeRealizadaRemover(1);
				}
				if (opcao == 4) {

					System.out
							.println("Digite o id da atividade realizada a ser atualizada:");

					atividadeRealizada = fachada.atividadeRealizadaProcurar(in
							.nextInt());
					in.nextLine();
					AtividadeRealizada atividadeRealizada2 = new AtividadeRealizada(
							inicio, fim, idUsuario, idAtividade);

					if (atividadeRealizada2 == null)
						throw new NullPointerException();

					atividadeRealizada2.setId(atividadeRealizada.getId());
					System.out
							.println("Digite o inicio da atividade realizada ");

					String inicioString = in.nextLine();

					inicio = sdf.parse(inicioString);

					atividadeRealizada2.setDataHoraInicio(inicio);

					System.out.println("Digite o fim");
					String fimString = in.nextLine();
					fim = sdf.parse(fimString);

					atividadeRealizada2.setDataHoraFim(fim);

					System.out.println("Digite o id do usuário");
					idUsuario = in.nextInt();
					atividadeRealizada2.setIdUsuario(idUsuario);

					System.out.println("Digite o id da atividade");
					idAtividade = in.nextInt();
					atividadeRealizada2.setIdAtividade(idAtividade);

					fachada.atualizar(atividadeRealizada2);

				}
			}

		} catch (ViolacaoChaveEstrangeiraException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
