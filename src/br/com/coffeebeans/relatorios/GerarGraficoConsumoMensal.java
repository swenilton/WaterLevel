package br.com.coffeebeans.relatorios;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.coffeebeans.util.Conexao;

/**
 * Servlet implementation class GerarGraficoConsumoMensal
 */
@WebServlet("/GerarGraficoConsumoMensal")
public class GerarGraficoConsumoMensal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private String sistema = "mysql";
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String dataString;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");

		try {
			connection = Conexao.conectar(sistema);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataString = "%" + request.getParameter("mes") + "%";
		try {
			String sql = "SELECT * FROM CONSUMO where data_hora like ?";

			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, dataString);

			rs = stmt.executeQuery();

			// System.out.println(stmt);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);

			/*
			 * Date date = sdf.parse(dataString);
			 * 
			 * sdf = new SimpleDateFormat("dd/MM/yyyy"); String dataString2 =
			 * sdf.format(date);
			 */

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			while (rs.next()) {

				/*
				 * Date date2 = sdf.parse(rs.getString("data_hora")); sdf = new
				 * SimpleDateFormat("dd/MM/yyyy");
				 * 
				 * String data = sdf.format(date2);
				 */

				dataset.addValue(rs.getDouble("GASTO"),
						rs.getDate("data_hora"), rs.getDate("data_hora"));

			}

			ServletOutputStream os = response.getOutputStream();

			String mes = dataString.substring(6, 8);
			String trechoTitulo = "";

			if (mes.equals("01")) {
				trechoTitulo = "Janeiro de " + dataString.substring(1, 5);
			}

			else if (mes.equals("02")) {
				trechoTitulo = "Fevereiro de " + dataString.substring(1, 5);
			} else if (mes.equals("03")) {
				trechoTitulo = "Março de " + dataString.substring(1, 5);
			} else if (mes.equals("04")) {
				trechoTitulo = "Abril de " + dataString.substring(1, 5);
			} else if (mes.equals("05")) {
				trechoTitulo = "Maio de " + dataString.substring(1, 5);
			} else if (mes.equals("06")) {
				trechoTitulo = "Junho de " + dataString.substring(1, 5);
			}

			else if (mes.equals("07")) {
				trechoTitulo = "Julho de " + dataString.substring(1, 5);
			} else if (mes.equals("08")) {
				trechoTitulo = "Agosto de " + dataString.substring(1, 5);
			} else if (mes.equals("09")) {
				trechoTitulo = "Setembro de " + dataString.substring(1, 5);
			}

			else if (mes.equals("10")) {
				trechoTitulo = "Outubro de " + dataString.substring(1, 5);
			} else if (mes.equals("11")) {
				trechoTitulo = "Novembro de " + dataString.substring(1, 5);
			} else if (mes.equals("12")) {
				trechoTitulo = "Dezembro de " + dataString.substring(1, 5);
			}

			JFreeChart chart = ChartFactory.createBarChart3D(
					"Consumo de água em " + trechoTitulo, "", "Litros",
					dataset, PlotOrientation.VERTICAL, false, true, false);
			/*
			 * JFreeChart chart = ChartFactory.createAreaChart("Area Chart", "",
			 * "Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
			 */
			RenderedImage chartImage = chart.createBufferedImage(300, 300);
			ImageIO.write(chartImage, "png", os);
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
			e.getStackTrace();
			System.out.println(e.getMessage());

			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("<script type=\"text/javascript\">");
			out.print("alert(\"Não foi possível gerar o gráfico.\");");
			out.print("</script>");
			out.print(e.getMessage());
			out.print("</body>");
			out.print("</html>");
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}
