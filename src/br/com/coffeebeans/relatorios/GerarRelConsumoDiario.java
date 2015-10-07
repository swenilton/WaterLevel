package br.com.coffeebeans.relatorios;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.coffeebeans.util.Conexao;

@WebServlet("/GerarRelConsumoDiario")
public class GerarRelConsumoDiario extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String dataIniString;
	private String dataFimString;

	private Connection conectar;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			dataIniString = request.getParameter("data-inicio");
			dataFimString = request.getParameter("data-fim");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);

			conectar = Conexao.conectar("mysql");

			ServletContext context = getServletContext();
			byte[] bytes = null;

			// carrega o arquivo jasper

			JasperReport relatorioJasper = (JasperReport) JRLoader
					.loadObjectFromFile(context
							.getRealPath("/WEB-INF/RelConsumoDiario.jasper"));

			// Na variavel pathJasper ficara o caminho do diretório para
			// os relatórios compilados (.jasper)
			String pathJasper = getServletContext().getRealPath("/WEB-INF/")
					+ "/";

			// A variavel path armazena o caminho real para o contexto
			// isso é util pois o seu web container pode estar instalado em
			// lugares diferentes
			String path = getServletContext().getRealPath("/");

			// parâmetros
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dtIni", dataIniString);
			parametros.put("dtFim", dataFimString);

			// direciona a saída do relatório para um stream
			bytes = JasperRunManager.runReportToPdf(relatorioJasper,
					parametros, conectar);

			// System.out.println(path);
			
			//System.out.println(relatorioJasper.getQuery().getText().toString());
			//System.out.println(dataIniString);
			
			if (bytes != null && bytes.length > 0) {

				// envia o relatório em formato PDF para o browser
				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();

			}

			/*
			 * catch (JRException e) { e.printStackTrace(); e.getCause();
			 * e.getStackTrace(); System.out.println(e.getMessage()); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
			e.getStackTrace();
			System.out.println(e.getMessage());

			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("<script type=\"text/javascript\">");
			out.print("alert(\"Não foi possível gerar o relatório.\");");
			out.print("</script>");
			out.print(e.getMessage());
			out.print("</body>");
			out.print("</html>");
		} finally {

			try {
				conectar.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
