package org.market.servlets.categoria;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/RecuperarCategoriaServlet")
public class RecuperarCategoriaServlet extends HttpServlet {

	private static final long serialVersionUID = -4071459464352850693L;
	CategoriaNegocios categoriaNegocios = new CategoriaNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			String nome = req.getParameter("nome");

			String json = recuperarCategoria(nome);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String recuperarCategoria(String nome) throws JsonProcessingException {

		Categoria categoria = categoriaNegocios.recuperarCategoria(nome);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(categoria);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
