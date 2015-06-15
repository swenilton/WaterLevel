package br.com.coffeebeans.relatorios;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
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

@WebServlet("/GerarRelConsumoHora")
public class GerarRelConsumoHora extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ResultSet rs;
	Timestamp dataIni;
	String dataIniString;
	Timestamp dataFim;
	String dataFimString;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			sdf.setLenient(false);

			dataIniString =request.getParameter("data-inicio");
			dataFimString = request.getParameter("data-fim");

			dataIni=Timestamp.valueOf(dataIniString);
			dataFim=Timestamp.valueOf(dataFimString);
			
			//Timestamp.
			
			Connection conectar = Conexao.conectar("mysql");

			ServletContext context = getServletContext();
			byte[] bytes = null;
			
			try {

				// carrega os arquivos jasper
			
			       
				JasperReport relatorioJasper = (JasperReport) JRLoader
								.loadObjectFromFile(context
									.getRealPath("/WEB-INF/RelConsumo.jasper"));

				// Na variavel pathJasper ficara o caminho do diretório para
				// os relatórios compilados (.jasper)
				String pathJasper = getServletContext()
						.getRealPath("/WEB-INF/") + "/";

				// A variavel path armazena o caminho real para o contexto
				// isso é util pois o seu web container pode estar instalado em
				// lugares diferentes
				String path = getServletContext().getRealPath("/");
							
				// parâmetros
				HashMap<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("dtHoraIni", dataIni);
				parametros.put("dtHoraFim", dataFim);
				
				System.out.println(dataIni);
				System.out.println(dataFim);
				
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
