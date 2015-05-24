import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.NEW;

import br.com.coffeebeans.acionamento.Acionamento;
import br.com.coffeebeans.bomba.Bomba;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;
import br.com.coffeebeans.fachada.Fachada;

public class TesteAcionamento2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm");
			System.out.println(sdf.format(new Date()));
			Acionamento acionamento = null;
			Date inicio = null;
			Date fim = null;

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
				System.out.println("Escolha a op��o");
				opcao = in.nextInt();

				if (opcao == 1) {
					System.out.println("Digite o inicio do acionamento");
					String inicioString = in.next();
					
					sdf.setLenient(false);
					inicio = sdf.parse(inicioString);

					System.out.println("Digite o fim");
					String fimString = in.next();
					fim = sdf.parse(fimString);

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

					if (acionamento == null) {
						throw new NullPointerException();
					} else {
						acionamento2.setId(acionamento.getId());
						System.out.println("Digite o inicio do acionamento");

						String inicioString = in.next();

						inicio = sdf.parse(inicioString);

						acionamento2.setDataHoraInicio(inicio);

						System.out.println("Digite o fim");
						String fimString = in.next();
						fim = sdf.parse(fimString);

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
