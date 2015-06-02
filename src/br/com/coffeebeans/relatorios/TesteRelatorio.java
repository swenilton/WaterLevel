package br.com.coffeebeans.relatorios;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import br.com.coffeebeans.util.Conexao;

@WebServlet("/TesteRelatorio")
public class TesteRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean status;
	ResultSet rs;
	Date dateIni;
	Date dateFim;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			sdf.setLenient(false);

			dateIni = sdf.parse(request.getParameter("data-inicio"));
			dateFim = sdf.parse(request.getParameter("data-fim"));

			Connection conectar = Conexao.conectar("mysql");

			ServletContext context = getServletContext();
			byte[] bytes = null;
			//System.out.println(context
					//.getRealPath("/WEB-INF/Acionamentos.jasper"));
			try {

				// carrega os arquivos jasper
					JasperReport relatorioJasper = (JasperReport) JRLoader
								.loadObjectFromFile(context
									.getRealPath("/WEB-INF/Acionamentos.jasper"));

				
				
				// Na variavel pathJasper ficara o caminho do diretório para
				// os relatórios compilados (.jasper)
				String pathJasper = getServletContext()
						.getRealPath("/WEB-INF/") + "/";

				// A variavel path armazena o caminho real para o contexto
				// isso é util pois o seu web container pode estar instalado em
				// lugares diferentes
				String path = getServletContext().getRealPath("/");

				// parâmetros, se houverem
				HashMap<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("dataIni", dateIni);
				parametros.put("dataFim", dateFim);

				// direciona a saída do relatório para um stream
				bytes = JasperRunManager.runReportToPdf(relatorioJasper,
						parametros, conectar);

			}

			catch (JRException e) {
				e.printStackTrace();
				e.getCause();
				e.getStackTrace();
				System.out.println(e.getMessage());
			}
			
			catch(Exception e) {
			e.printStackTrace();
			e.getCause();
			e.getStackTrace();
			System.out.println(e.getMessage());	
			}

			if (bytes != null && bytes.length > 0) {

				// envia o relatório em formato PDF para o browser
				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			}
			conectar.close();
			
		} catch (Exception erro) {
			PrintStream tela = new PrintStream(response.getOutputStream());
			tela.println("<HTML><BODY>");
			tela.println("<script>alert('Não foi possível gerar o relatório.'"
					+ erro.getMessage() + ");history.back();</script>");
			tela.println("<BR><P>");
			tela.println("</HTML></BODY>");
			return;
		}

	}
}
