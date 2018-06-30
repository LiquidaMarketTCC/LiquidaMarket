package org.market.servlets.categoria;

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
import org.market.entidades.Categoria;
import org.market.negocios.CategoriaNegocios;

@WebServlet("/RecuperarTodasCategoriasServlet")
public class RecuperarTodasCategoriasServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7931981450080473571L;
	CategoriaNegocios categoriaNegocios = new CategoriaNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			String json = recuperarTodasCategorias();

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String recuperarTodasCategorias() throws JsonProcessingException {

		List<Categoria> categorias = categoriaNegocios.recuperarTodasCategorias();
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(categorias);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
