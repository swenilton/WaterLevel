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
 * Servlet implementation class GerarGraficoConsumoHorario
 */
@WebServlet("/GerarGraficoConsumoHorario")
public class GerarGraficoConsumoHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private String sistema = "mysql";
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String dataIniString;
	private String dataFimString;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// select GASTO,DATA_HORA from consumo where DATA_HORA between
		response.setContentType("image/png");

		try {
			connection = Conexao.conectar(sistema);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataIniString = request.getParameter("hora-inicial");
		dataFimString = request.getParameter("hora-final");

		dataIniString = dataIniString.replace("T", " ");
		dataFimString = dataFimString.replace("T", " ");

		try {
			String sql = "SELECT * FROM CONSUMO where data_hora between ? and ?";

			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, dataIniString);
			stmt.setString(2, dataFimString);

			rs = stmt.executeQuery();

			System.out.println(stmt);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			sdf.setLenient(false);

			Date dateIni = sdf.parse(dataIniString);
			Date dateFim = sdf.parse(dataFimString);

			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String dataIniString2 = sdf.format(dateIni);
			String dataFimString2 = sdf.format(dateFim);

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			while (rs.next()) {
				/*sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = sdf.parse(rs.getString("data_hora"));
				sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

				String data = sdf.format(date); */

				dataset.addValue(rs.getDouble("GASTO"),rs.getDate("data_hora"),rs.getDate("data_hora"));

			}

			ServletOutputStream os = response.getOutputStream();

			JFreeChart chart = ChartFactory.createBarChart3D(
					"Consumo de água entre " + dataIniString2 + " e "
							+ dataFimString2, "", "Litros", dataset,
					PlotOrientation.VERTICAL, false, true, false);
			/*
			 * JFreeChart chart = ChartFactory.createAreaChart("Area Chart", "",
			 * "Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
			 */

			chart.setTitle(new org.jfree.chart.title.TextTitle(
					"Consumo de água entre " + dataIniString2 + " e "
							+ dataFimString2, new java.awt.Font("SansSerif",
							java.awt.Font.BOLD, 13)));

			RenderedImage chartImage = chart.createBufferedImage(1024, 800);
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
