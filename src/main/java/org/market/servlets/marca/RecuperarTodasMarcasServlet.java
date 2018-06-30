package org.market.servlets.marca;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.Marca;
import org.market.negocios.MarcaNegocios;

@WebServlet("/RecuperarTodasMarcasServlet")
public class RecuperarTodasMarcasServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5896895165430015732L;
	MarcaNegocios marcaNegocios = new MarcaNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			String json = recuperarTodasMarcas();

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String recuperarTodasMarcas() throws JsonProcessingException {

		List<Marca> marcas = marcaNegocios.recuperarTodasMarcas();
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(marcas);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
