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

import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.util.Conexao;

/**
 * Servlet implementation class GerarGraficoConsumoDiario
 */
@WebServlet("/GerarGraficoConsumoDiario")
public class GerarGraficoConsumoDiario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private String sistema = "mysql";
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String dataIniString;
	private String dataFimString;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");

		try {
			connection = Conexao.conectar(sistema);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataIniString = "%" + request.getParameter("data-inicio") + "%";
		dataFimString = "%" + request.getParameter("data-fim") + "%";

		try {
			String sql = "SELECT * FROM CONSUMO where data_hora like ? or data_hora like ?";

			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, dataIniString);
			stmt.setString(2, dataFimString);

			rs = stmt.executeQuery();

			dataIniString = dataIniString.replace("%", " ");
			dataFimString = dataFimString.replace("%", " ");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);

			Date dateIni = sdf.parse(dataIniString);
			Date dateFim = sdf.parse(dataFimString);

			sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dataIniString2 = sdf.format(dateIni);
			String dataFimString2 = sdf.format(dateFim);

			System.out.println(stmt);

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			while (rs.next()) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(rs.getString("data_hora"));
				sdf = new SimpleDateFormat("dd/MM/yyyy");

				String data = sdf.format(date);

				dataset.addValue(rs.getDouble("GASTO"), data, data);

			}

			ServletOutputStream os = response.getOutputStream();

			JFreeChart chart = ChartFactory.createBarChart3D(
					"Consumo de �gua di�rio entre " + dataIniString2 + " e "
							+ dataFimString2, "Dias", "Litros", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			chart.setTitle(new org.jfree.chart.title.TextTitle(
					"Consumo de �gua di�rio entre " + dataIniString2 + " e "
							+ dataFimString2, new java.awt.Font("SansSerif",
							java.awt.Font.BOLD, 13)));

			// chart.setTitle("",new java.awt.Font("SansSerif",
			// java.awt.Font.BOLD, 12));
			/*
			 * JFreeChart chart = ChartFactory.createAreaChart("Area Chart", "",
			 * "Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
			 */
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
			out.print("alert(\"N�o foi poss�vel gerar o gr�fico.\");");
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
