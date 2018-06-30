package org.market.servlets.produtoComercializado;

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
import org.market.entidades.ProdutoComercializado;
import org.market.negocios.ProdutoComercializadoNegocios;

@WebServlet("/RecuperarTodosProdutosComercializadosServlet")
public class RecuperarTodosProdutosComercializadosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2608850552881056431L;
	ProdutoComercializadoNegocios produtoComercializadoNegocios = new ProdutoComercializadoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {
			long idCategoria = Long.parseLong(req.getParameter("idCategoria"));
			String json = recuperarTodosProdutosComercializado(idCategoria);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String recuperarTodosProdutosComercializado(long idCategoria) throws JsonProcessingException {
		List<ProdutoComercializado> produtosComercializados = null;
		if (idCategoria <= 0) {
			produtosComercializados = produtoComercializadoNegocios.recuperarTodosProdutosComercializado();
		} else {
			produtosComercializados = produtoComercializadoNegocios
					.recuperarTodosProdutosComercializadoCategoria(idCategoria);
		}
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(produtosComercializados);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
